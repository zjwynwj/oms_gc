package com.dinghao.service.template.salesrtnorder.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.dao.template.business.order.SalesOrderDao;
import com.dinghao.dao.template.business.order.SalesOrderitemDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.dao.template.salesrtnorder.SalesRtnOrderDao;
import com.dinghao.dao.template.salesrtnorder.SalesRtnOrderItemDao;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.template.salesrtnorder.SalesRtnOrder;
import com.dinghao.entity.template.salesrtnorder.SalesRtnOrderItem;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderItemVo;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderVo;
import com.dinghao.service.template.base.BaseNumberService;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.service.template.salesrtnorder.SalesRtnOrderService;
import com.dinghao.util.BeanUtils;

@Service
public class SalesRtnOrderServiceImp implements SalesRtnOrderService {
	@Autowired
	private LocStockDao locStockDao;
	@Autowired
	private SalesOrderitemDao orderitemDao;
	@Autowired
	private SalesOrderDao orderDao;
	@Autowired
	private GdsInfoDao gdsDao;
	@Autowired
	SalesRtnOrderDao salesRtnOrderDao;
	@Autowired
	SalesRtnOrderItemDao salesRtnOrderItemDao;
	@Autowired
	LocStockService locStockService;
	@Autowired
	ISalesOrderService salesOrderService;
	@Autowired
	BaseNumberService numberService;
	@Autowired
	ShopService shopService;
	@Override
	public JqGridVo<SalesRtnOrder> selectByStatement(SalesRtnOrderVo record) {
		JqGridVo<SalesRtnOrder> jqGridVo = new JqGridVo<SalesRtnOrder>();
		jqGridVo.setList(salesRtnOrderDao.selectByStatement(record));
		jqGridVo.setRecords(salesRtnOrderDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}

	@Transactional
	@Override
	public CommResponse insertSelective(SalesRtnOrderVo record) {
		CommResponse commResponse = new CommResponse();
		salesRtnOrderDao.insertSelective(record);

		for (SalesRtnOrderItemVo salesRtnOrderItemVo : record
				.getSalesRtnOrderItemVos()) {
			salesRtnOrderItemVo.setRtnId(record.getRtnId());
			salesRtnOrderItemDao.insertSelective(salesRtnOrderItemVo);
		}
		return commResponse;
	}

	@Override
	@Transactional
	public CommResponse updateByPrimaryKeySelective(SalesRtnOrderVo record) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		SalesRtnOrder mSalesRtnOrder = salesRtnOrderDao.selectByPrimaryKey(record.getRtnId());
		if(mSalesRtnOrder.getReceived())
		{   commResponse.setSuccess(false);
		    commResponse.setErrMsg("已入库不能修改退货单");
			return commResponse;
		}
		salesRtnOrderDao.updateByPrimaryKeySelective(record);
		// 清除列表数据
		//salesRtnOrderItemDao.deleteByRtnId(record.getRtnId());
        int hasType2 = 0;
		for (SalesRtnOrderItemVo salesRtnOrderItemVo : record.getSalesRtnOrderItemVos()) {
			salesRtnOrderItemVo.setRtnId(record.getRtnId());
			if(salesRtnOrderItemVo.getRtnType()==2){
				hasType2 =2;
			}
			SalesRtnOrderItemVo mItem = new SalesRtnOrderItemVo();
			mItem.setId(salesRtnOrderItemVo.getId());
			if (salesRtnOrderItemDao.selectByStatementCount(mItem)>0 ){
				
				salesRtnOrderItemDao.updateByPrimaryKeySelective(salesRtnOrderItemVo);
			}
			else
			{
				salesRtnOrderItemDao.insertSelective(salesRtnOrderItemVo);
			}
			if (record.getReceived()){
			
			// 收货
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setStockId(record.getStockId().longValue());
			locStockVo.setLocId(salesRtnOrderItemVo.getLocid());
			locStockVo.setGdsId(salesRtnOrderItemVo.getGdsId().longValue());
			locStockVo.setTotalQty(salesRtnOrderItemVo.getRtnQty());
			locStockVo.setInAmount(salesRtnOrderItemVo.getPrice().multiply( new BigDecimal ( salesRtnOrderItemVo.getRtnQty() )) );
			locStockService.saveLocStock(locStockVo, 1);
		
			}

		}
		
		if (record.getReceived() && hasType2==2){
			//如果是换货则生成新的订单
			addSalesOrder(record);
		}
		return commResponse;
	}

	@Override
	public SalesRtnOrder selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return salesRtnOrderDao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectByStatementCount(SalesRtnOrderVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	@Override
	public CommResponse saveReceivedSalesRtnOrder(SalesRtnOrderVo salesRtnOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		SalesRtnOrder mSalesRtnOrder = salesRtnOrderDao.selectByPrimaryKey(salesRtnOrderVo.getRtnId());
		if(mSalesRtnOrder.getReceived())
		{   commResponse.setSuccess(false);
		    commResponse.setErrMsg("退货单已入库");
			return commResponse;
		}
		BeanUtils.copyProperties(salesRtnOrderVo, mSalesRtnOrder);
		
		salesRtnOrderVo.setReceived(true);
		salesRtnOrderDao.updateByPrimaryKeySelective(salesRtnOrderVo);

		SalesRtnOrderItemVo record = new SalesRtnOrderItemVo();
		record.setRtnId(salesRtnOrderVo.getRtnId());
		int hasType2 = 0;
		List<SalesRtnOrderItem> salesRtnOrderItems = salesRtnOrderItemDao.selectByStatement(record);
		List<SalesRtnOrderItemVo> salesRtnOrderItemsVo = new ArrayList<SalesRtnOrderItemVo>(); 
		for (SalesRtnOrderItem salesRtnOrderItem : salesRtnOrderItems) {
			SalesRtnOrderItemVo salesRtnOrderItemVo = new SalesRtnOrderItemVo();
			salesRtnOrderItemVo.setId(salesRtnOrderItem.getGdsId());
			BeanUtils.copyProperties(salesRtnOrderItemVo, salesRtnOrderItem);
			//salesRtnOrderItemsVo.add(salesRtnOrderItemVo);
		
			if(salesRtnOrderItem.getRtnType()==2){
				hasType2 =2;
			}
			// 收货
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setStockId(salesRtnOrderVo.getStockId().longValue());
			locStockVo.setLocId(salesRtnOrderItem.getLocid());
			locStockVo.setGdsId(salesRtnOrderItem.getGdsId().longValue());
			locStockVo.setTotalQty(salesRtnOrderItem.getRtnQty());
			locStockVo.setInAmount(salesRtnOrderItem.getPrice().multiply( new BigDecimal ( salesRtnOrderItem.getRtnQty() )) );
			
			locStockService.saveLocStock(locStockVo, 1);

		}
		salesRtnOrderVo.setSalesRtnOrderItemVos(salesRtnOrderItemsVo);
		if (salesRtnOrderVo.getReceived() && hasType2==2){
			//如果是换货则生成新的订单
			addSalesOrder(salesRtnOrderVo);
		}
		return commResponse;
	}

	@Override
	public CommResponse selectSalesRtnOrderItemsByStatement(SalesRtnOrderVo record) throws Exception {
		
		CommResponse commResponse = new CommResponse();
		
		if(record.getRtnId()==null && null ==record.getOrderId()){
			commResponse.setData(null);
		
		}	
		else if(null!=record.getOrderId()){
			SalesOrderVo salesOrderVo = new SalesOrderVo();
			salesOrderVo.setId(Long.parseLong(record.getOrderId().toString()));
			CommResponse mcommResponse = salesOrderService.querySalesOrderItemList(salesOrderVo);
			List<SalesOrderitemVo> 	salesOrderitemList = 	(List<SalesOrderitemVo>) mcommResponse.getData();
			List<SalesRtnOrderItem> salesRtnOrderItemList = new ArrayList<SalesRtnOrderItem>();
			for (SalesOrderitemVo item : salesOrderitemList){
				SalesRtnOrderItem salesRtnOrderItem = new SalesRtnOrderItem();
				salesRtnOrderItem.setGdsId(Integer.parseInt(item.getGdsId().toString()));
				salesRtnOrderItem.setGdsShowInfo(item.getOuterIid());
				salesRtnOrderItem.setGdsName(item.getGdsName());
				salesRtnOrderItem.setGdsFormat(item.getGdsFormat());
				salesRtnOrderItem.setSkuOuterId(item.getSkuOuterId());
				salesRtnOrderItem.setRtnQty(item.getQty());
				salesRtnOrderItem.setOriginalPrice(item.getSalsePrice());
				salesRtnOrderItem.setPrice(item.getSalsePrice().multiply(BigDecimal.valueOf(item.getQty())));
				salesRtnOrderItem.setId(item.getId());
				if(null != item.getAttbs() && !"".equals(item.getAttbs())){
					String attbName[] = item.getAttbs().split(";");
					String attbs ="";
					for(String attb : attbName){
						attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
					}
					attbs = attbs.substring(1, attbs.length());
					item.setAttbs(attbs);
				}else{
					item.setAttbs("");
				}
				
				salesRtnOrderItem.setAttbs(item.getAttbs());
				salesRtnOrderItemList.add(salesRtnOrderItem);
			}
			commResponse.setData(salesRtnOrderItemList);
		
		}else{
			SalesRtnOrderItemVo  salesRtnOrderItemVo = new SalesRtnOrderItemVo();
			salesRtnOrderItemVo.setRtnId(record.getRtnId());
			commResponse.setData(salesRtnOrderItemDao.selectByStatement(salesRtnOrderItemVo));
		
		}
		
		return commResponse;
	}
	
	
	private CommResponse addSalesOrder(SalesRtnOrderVo salesRtnOrderVo) throws Exception{
		
		CommResponse commResponse = new CommResponse();
		SalesOrderVo salesOrderVo = new SalesOrderVo();
		salesOrderVo.setHasfaudit("0");  //未审核
	
		//1、通过来源订单获取订单信息
		String topTips = "";
		if (null!=salesRtnOrderVo.getOrderId() && salesRtnOrderVo.getOrderId()>0)
		{
			topTips = orderDao.selectByPrimaryKey(salesRtnOrderVo.getOrderId().longValue()).getTopTids();
		}
		
		//2.生成订单的单据号
		BaseNumberVo baseNumberVo = new BaseNumberVo();
		baseNumberVo.setNuType("08");
		CommResponse numRes = numberService.findBaseNumberNext(baseNumberVo);
		salesOrderVo.setOrderNum(numRes.getData().toString());
	

		Shop shop = shopService.queryById(salesRtnOrderVo.getShopId().longValue());
		
		salesOrderVo.setPlatType(salesRtnOrderVo.getPlatType());
		salesOrderVo.setShopId(salesRtnOrderVo.getShopId().longValue());
		salesOrderVo.setExpid(shop.getExpid().longValue());
		salesOrderVo.setRemark1(shop.getPrintId() +"");
		salesOrderVo.setStockId(shop.getWarehouseId());
		salesOrderVo.setCity(salesRtnOrderVo.getCityId());
		salesOrderVo.setProv(salesRtnOrderVo.getProvId());
		salesOrderVo.setCounty(salesRtnOrderVo.getCountyId());
		salesOrderVo.setCustNick(salesRtnOrderVo.getCustNick());
		salesOrderVo.setRecvmobile(salesRtnOrderVo.getRecvMobile());
		salesOrderVo.setAddress(salesRtnOrderVo.getAddress());
		salesOrderVo.setCreateDate(new Date());
		salesOrderVo.setModified(new Date());
		salesOrderVo.setHasmerge("0");
		salesOrderVo.setSendStatus("0");
		salesOrderVo.setExceptionStatus("0");
		salesOrderVo.setDisabled("0");
		salesOrderVo.setMark("换货订单");
		salesOrderVo.setTopTids(topTips);
		try{
			orderDao.insertSelective(salesOrderVo);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}
	
		List<LocStockVo> locStockList = new ArrayList<LocStockVo>();
		BigDecimal weight = new BigDecimal("0.00");

		
		for(SalesRtnOrderItemVo rtorderItem : salesRtnOrderVo.getSalesRtnOrderItemVos())
		{
			if (rtorderItem.getRtnType()!= 2)
			{
				continue;
			}
			SalesOrderitemVo orderItem = new SalesOrderitemVo();
			
			GdsInfo gdsInfo = gdsDao.queryGdsInfoById(rtorderItem.getGdsId().longValue());
        	try {
        		
				//计算重量
					weight = weight.add(gdsInfo.getStandWeight().multiply(new BigDecimal(rtorderItem.getRtnQty())));
					//查询现货表有无库存
					LocStockVo locStockVo = new LocStockVo();
					locStockVo.setStockId(salesOrderVo.getStockId());
					locStockVo.setGdsId(gdsInfo.getId());
			
					LocStockVo mlocStockVo = locStockDao.selectMastuseQtyByParam(locStockVo);
					//if(mlocStockVo != null && mlocStockVo.getUseQty() > rtorderItem.getRtnQty()){
						orderItem.setGdsId(gdsInfo.getId());//设置商品id
						orderItem.setGdsName(gdsInfo.getGdsName());
						orderItem.setLocId(mlocStockVo.getLocId());
						orderItem.setQty(rtorderItem.getRtnQty());
						LocStockVo locStockMod = new LocStockVo();
						locStockMod.setId(mlocStockVo.getId());
						locStockMod.setOrderQty(mlocStockVo.getOrderQty() + orderItem.getQty());
						locStockList.add(locStockMod);
					/*
        			}else{
						orderItem.setGdsId(gdsInfo.getId());//设置商品id
						if("1".equals(salesOrderVo.getExceptionStatus())  || "3".equals(salesOrderVo.getExceptionStatus()) ){
							salesOrderVo.setExceptionStatus("3");
						}else{
							salesOrderVo.setExceptionStatus("2");//商品不存在   回填表头商品不存在标记
						}
					}
					*/
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			orderItem.setOrderId(salesOrderVo.getId().toString());
            orderItem.setStatus("0");//正常明细商品
		    orderItem.setRefundStatus("0");//未退款
			
			
			orderitemDao.insertSelective(orderItem);
		
		}
		
		salesOrderVo.setStandardWeight(weight);
		orderDao.updateByPrimaryKeySelective(salesOrderVo);
		
		if("0".equals(salesOrderVo.getExceptionStatus())){
			for(LocStockVo locStockVo : locStockList){
				//正常订单   重新设置现货表的待发货数量
				locStockDao.updateByPrimaryKeySelective(locStockVo);
			}
		}
	  
		return commResponse;
	}
}
