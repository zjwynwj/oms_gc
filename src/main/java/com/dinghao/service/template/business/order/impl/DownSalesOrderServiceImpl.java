package com.dinghao.service.template.business.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.base.BaseAreaDao;
import com.dinghao.dao.template.base.ShopDao;
import com.dinghao.dao.template.business.express.DmsExpressareaDao;
import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.dao.template.business.member.MemberDao;
import com.dinghao.dao.template.business.order.SalesOrderDao;
import com.dinghao.dao.template.business.order.SalesOrderitemDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.entity.htobject.resultEntity;
import com.dinghao.entity.htobject.resultOrder;
import com.dinghao.entity.htobject.resultOrderDetail;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.template.business.order.PageResult;
import com.dinghao.entity.template.business.order.SalesOrderitem;
import com.dinghao.entity.template.business.order.TotalResult;
import com.dinghao.entity.template.business.order.TradeStatus;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
import com.dinghao.entity.vo.template.business.member.MemberVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.base.BaseNumberService;
import com.dinghao.service.template.business.order.IDownSalesOrderService;
import com.dinghao.util.BeanUtils;
import com.dinghao.util.DateUtil;
import com.dinghao.util.OrderMgrUtil;
import com.dinghao.util.WSCUtil;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.StringUtils;
@Service
@Transactional
public class DownSalesOrderServiceImpl implements IDownSalesOrderService{

	/** 日志记录  */
	private static final Log logger = LogFactory.getLog(SalesOrderServiceImpl.class);
	@Autowired
	private SalesOrderDao orderDao;
	@Autowired
	private SalesOrderitemDao orderitemDao;
	@Autowired
	private GdsInfoDao gdsDao;
	@Autowired
	private BaseNumberService numberService;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private BaseAreaDao areaDao;
	@Autowired
	private DmsExpressareaDao expressareaDao;
	@Autowired
	private LocStockDao locStockDao;
	/**
	  * <p>Title: downLoadSalesOrder</p>
	  * <p>Description: 订单下载</p>
	  * @param salesOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.order.ISalesOrderService#downLoadSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse downLoadSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/**1.查询店铺*/
		Shop shop = shopDao.selectByPrimaryKey(salesOrderVo.getShopId());
		/**2.准备下载使用的条件*/
		Date startTime = salesOrderVo.getDownStartTime();//查询开始时间
		Date endTime = salesOrderVo.getDownEndTime();//查询结束时间
		salesOrderVo.setDownStartTime(startTime);
		salesOrderVo.setDownEndTime(endTime);
		salesOrderVo.setTimestamp(DateUtil.getCurDate());
		/**3.开始下载*/
		try{
			downPlatSalesOrder(salesOrderVo , shop);
		}catch(DHBizException e){
			throw new DHBizException(e.getErrorMsg());
		}catch (Exception e) {
			throw new DHBizException("系统未知错误,请联系管理员");
		}
		return commResponse;
	}
	
	private  CommResponse downPlatSalesOrder(SalesOrderVo salesOrderVo ,Shop shop) throws Exception{
		CommResponse commResponse = new CommResponse();
		List<SalesOrderVo> saleOrderList = null;
		/**1.根据平台调用不用接口下载订单数据*/
		if("TB".equals(shop.getPlanType())){
			saleOrderList = downTBSalesOrder(salesOrderVo , shop);
		}
		if("WSC".equals(shop.getPlanType())){
			saleOrderList = downWSCSalesOrder(salesOrderVo , shop);
		}
		/**2.下载订单到本地*/
		CommResponse downResponse = new CommResponse();
		for(SalesOrderVo orderVo : saleOrderList){
			
			List<SalesOrderVo> list = orderDao.selectOrderListByTid(orderVo.getTopTids());
		
			if(list==null||list.size()==0){
				
				/*本地不存在订单，则新增*/
				downResponse = addToLocalOrder(orderVo , shop);
			}else if(list.size()==1){
				/*本地存在订单，则修改*/
				SalesOrderVo msalesOrderVo = list.get(0);
				
				downResponse = updateToLocalOrder(orderVo , msalesOrderVo);
			}else{
				/*本地存在多条订单，则打印错误日志*/
				logger.error("订单："+orderVo.getTopTids()+ExceptionConstant.ERR_DH000005);
				continue;
			}
			/*生成会员信息*/
			
			/*判断是否下载正确*/
			if(!downResponse.isSuccess()){
				logger.error("订单："+orderVo.getTopTids()+"下载失败");
			}else{
				//成功则添加或修改会员信息
				MemberVo memberVo = new MemberVo();
				memberVo.setShopId(salesOrderVo.getShopId());
				memberVo.setNick(orderVo.getCustNick());
				memberVo.setRecvmobile(orderVo.getRecvmobile());
				//假如不存在会员信息则添加
				int count = memberDao.selectMemberCountByShopNick(memberVo);
				if(count == 0){
					memberVo.setUsername(orderVo.getRecvname());
					memberVo.setNick(orderVo.getCustNick());
					memberVo.setAddress(orderVo.getAddress());
					memberVo.setZip(orderVo.getZipcode());
					memberVo.setRecvphone(orderVo.getRecvphone());
					memberVo.setRecvmobile(orderVo.getRecvmobile());
					memberVo.setProvname(orderVo.getProv());
					memberVo.setCityname(orderVo.getCity());
					memberVo.setCountyname(orderVo.getCounty());
					memberVo.setBuytimes(1);
					try{
						memberDao.insertSelective(memberVo);
					}catch(Exception e){
						e.printStackTrace();
						throw e;
					}
				}
				logger.debug("订单："+orderVo.getTopTids()+"下载成功");
			}
		}
		return commResponse;
	}
	
	private CommResponse addToLocalOrder(SalesOrderVo salesOrderVo , Shop shop) throws Exception{
	
		CommResponse commResponse = new CommResponse();
		/**1.根据订单的状态判断是否下载此订单（目前支持等待卖家发货的订单）*/
		if(salesOrderVo.getPlatType() =="TB" && !TradeStatus.WAIT_SELLER_SEND_GOODS.equals(salesOrderVo.getPlatStatus())){
			commResponse.setSuccess(false);
			logger.debug(salesOrderVo.getPlatType()+ "订单:"+salesOrderVo.getTopTids()+"不满足下载条件");
			return commResponse;
		}
		else if(salesOrderVo.getPlatType() =="WSC" && salesOrderVo.getPlatStatus() != "3" ){
			commResponse.setSuccess(false);
			logger.debug(salesOrderVo.getPlatType()+ "订单:"+salesOrderVo.getTopTids()+"不满足下载条件");
			return commResponse;
		}else{
			salesOrderVo.setHasfaudit("0");  //未审核
		}
		/**2.生成订单的单据号*/
		BaseNumberVo baseNumberVo = new BaseNumberVo();
		baseNumberVo.setNuType("08");
		CommResponse numRes = numberService.findBaseNumberNext(baseNumberVo);
		salesOrderVo.setOrderNum(numRes.getData().toString());
		/**3.根据订单的区域等信息查询默认物流公司是否到达（如果不到达，物流公司为空）*/
		Long expId = Long.valueOf(shop.getExpid());
		salesOrderVo.setExpid(expId);
		salesOrderVo.setRemark1(shop.getPrintId() +"");
//		BaseAreaVo area = new BaseAreaVo();
//		area.setRegionName(salesOrderVo.getProv());
//		area.setRegionType(1);
//		//查询省份id
//		Long provId = areaDao.selectAreaIdByParam(area).getId();
//		
//		DmsExpressareaVo expressareaVo = new DmsExpressareaVo();
//		expressareaVo.setExpressId(expId);
//		expressareaVo.setProvId(provId);
//		DmsExpressarea dmsExpressarea =  expressareaDao.selectByParam(expressareaVo);
//		if(dmsExpressarea != null && dmsExpressarea.getExpressId()!=null){
//			salesOrderVo.setExpid(dmsExpressarea.getExpressId());
//		}
		/**4.根据店铺信息填充默认仓库*/
		salesOrderVo.setStockId(shop.getWarehouseId());
		
		//(等待店铺模块)
		/**5.插入到订单主表*/
		try{
			orderDao.insertSelective(salesOrderVo);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		/**6.遍历订单明细*/
		List<LocStockVo> locStockList = new ArrayList<LocStockVo>();
		BigDecimal weight = new BigDecimal("0.00");
		for(SalesOrderitemVo orderItem : salesOrderVo.getItemList()){
			if(StringUtils.isEmpty(orderItem.getOuterIid())&&StringUtils.isEmpty(orderItem.getOuterSkuId())){
				//商品外部编码 和sku编码同时为空时  不查询商品 直接标记为异常
				salesOrderVo.setExceptionStatus("1");//商品不存在   回填表头商品不存在标记
			}else{
				try {
					String gdsNoSkuOuterId = "";
					if(!StringUtils.isEmpty(orderItem.getOuterIid())){
						gdsNoSkuOuterId += orderItem.getOuterIid();
					}
					if(!StringUtils.isEmpty(orderItem.getOuterSkuId())){
						gdsNoSkuOuterId += orderItem.getOuterSkuId();
					}
				//	GdsInfo gdsInfo = gdsDao.queryGdsInfoByGdsNoSkuOuterId(gdsNoSkuOuterId);
					
					List<GdsInfo> gdsInfoList = gdsDao.queryGdsInfoByGdsNoSkuOuterId(gdsNoSkuOuterId);
					GdsInfo gdsInfo = new GdsInfo();
					if (null != gdsInfoList && gdsInfoList.size()>0)
					{
						gdsInfo = gdsInfoList.get(0);
					}
					
					if(null == gdsInfo|| null == gdsInfo.getId()){
						if("2".equals(salesOrderVo.getExceptionStatus()) || "3".equals(salesOrderVo.getExceptionStatus())){
							salesOrderVo.setExceptionStatus("3");
						}else{
							salesOrderVo.setExceptionStatus("1");//商品不存在   回填表头商品不存在标记
						}
					}else{
						//计算重量
						weight = weight.add(gdsInfo.getStandWeight().multiply(new BigDecimal(orderItem.getQty())));
						//查询现货表有无库存
						LocStockVo locStockVo = new LocStockVo();
						locStockVo.setStockId(salesOrderVo.getStockId());
						locStockVo.setGdsId(gdsInfo.getId());
				
						LocStockVo mlocStockVo = locStockDao.selectMastuseQtyByParam(locStockVo);
						if(mlocStockVo != null && mlocStockVo.getUseQty() > orderItem.getQty()){
							orderItem.setGdsId(gdsInfo.getId());//设置商品id
							orderItem.setLocId(mlocStockVo.getLocId());
							LocStockVo locStockMod = new LocStockVo();
							locStockMod.setId(mlocStockVo.getId());
							locStockMod.setOrderQty(mlocStockVo.getOrderQty() + orderItem.getQty());
							locStockList.add(locStockMod);
						}else{
							orderItem.setGdsId(gdsInfo.getId());//设置商品id
							if("1".equals(salesOrderVo.getExceptionStatus())  || "3".equals(salesOrderVo.getExceptionStatus()) ){
								salesOrderVo.setExceptionStatus("3");
							}else{
								salesOrderVo.setExceptionStatus("2");//商品不存在   回填表头商品不存在标记
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("查询商品信息有误");
				}
			}
			orderItem.setOrderId(salesOrderVo.getId().toString());
			/**淘宝的明细状态*/
			if("TB".equals(salesOrderVo.getPlatType()) && TradeStatus.ORDER_NO_USE_STORE.contains(orderItem.getPlatStatus())){
				//表示在订单未付款或者待发货的状态下此条商品明细  已经被发货或者关闭
				orderItem.setStatus("9");
			}else{
				orderItem.setStatus("0");//正常明细商品
			}
			/**淘宝的明细退款状态*/
			if(!StringUtils.isEmpty(orderItem.getPlatStatus()) && "SUCCESS".equals(orderItem.getPlatRefundStatus())){
				orderItem.setRefundStatus("1");//退款成功
			}else{
				orderItem.setRefundStatus("0");//未退款
			}
			
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
		
		logger.info("订单:"+salesOrderVo.getTopTids()+"下载成功,生成订单:"+salesOrderVo.getOrderNum());
		return commResponse;
	}
	
	private CommResponse updateToLocalOrder(SalesOrderVo salesOrderVo , SalesOrderVo oldSalesOrder) throws Exception{
		CommResponse commResponse = new CommResponse();
		/**1.判断订单是否已经审核*/
		if("1".equals(salesOrderVo.getHasfaudit())){
			logger.debug("订单:"+salesOrderVo.getTopTids()+"已经审核,不再变更!");
			commResponse.setSuccess(false);
			return commResponse;
		}
		/**2.判断订单是否修改了*/
		Date oldUpdateTime = oldSalesOrder.getModified();//原来订单 的最后修改时间
		Date newUpdateTime =	salesOrderVo.getModified();//淘宝订单 的最后修改时间
		if( null!=oldUpdateTime && oldUpdateTime.compareTo(newUpdateTime)>=0 ){
			commResponse.setSuccess(false);
			logger.debug("订单:"+salesOrderVo.getTopTids()+"无修改,不再变更!");
			return commResponse;
		}
		/**3.订单留言等字段变更*/
		//设置新的备注相关信息
		oldSalesOrder.setBuyerFlag(salesOrderVo.getBuyerFlag());
		oldSalesOrder.setBuyerMemo(salesOrderVo.getBuyerMemo());
		oldSalesOrder.setSellerFlag(salesOrderVo.getSellerFlag());
		oldSalesOrder.setSellerMemo(salesOrderVo.getSellerMemo());
		//设置新的订单修改时间
		oldSalesOrder.setModified(salesOrderVo.getModified());
		
		/**4.判断订单订单是否关闭了*/
		if(TradeStatus.TRADE_CLOSED.equals(salesOrderVo.getPlatStatus()) || TradeStatus.TRADE_CLOSED_BY_TAOBAO.equals(salesOrderVo.getPlatStatus()) ){
			oldSalesOrder.setDisabled("1"); //订单状态更新为作废
		}else if(TradeStatus.ORDER_ALL_SEND.contains(salesOrderVo.getPlatStatus())){
			oldSalesOrder.setSendStatus("1");//订单状态更新为已发货
		}	
		/**5.修改订单主表*/
		SalesOrderVo updateSalesOrder = new SalesOrderVo();
		//BeanUtils.copyProperties(updateSalesOrder, oldSalesOrder);
		updateSalesOrder = oldSalesOrder;
		orderDao.updateByPrimaryKeySelective(updateSalesOrder);
		/**6.明细遍历开始*/
		for(SalesOrderitemVo orderItemVo : salesOrderVo.getItemList()){
			List<SalesOrderitem> orderItemList = orderitemDao.selectOrderItemListByOid(orderItemVo.getOid());
			
			for(SalesOrderitem oldOrderItem : orderItemList){//可能会有合并订单 ，所以使用集合
				if(StringUtils.isEmpty(oldOrderItem.getPlatStatus()) || !oldOrderItem.getPlatStatus().equals(orderItemVo.getPlatStatus())){
					oldOrderItem.setPlatStatus(orderItemVo.getPlatStatus());//更新明细淘宝的外部状态
					if(StringUtils.isEmpty(oldOrderItem.getPlatRefundStatus())||!oldOrderItem.getPlatRefundStatus().equals(orderItemVo.getPlatRefundStatus())){
						oldOrderItem.setPlatRefundStatus(orderItemVo.getPlatRefundStatus());//更新明细淘宝的退款状态
						/**如果新的退款状态 为 SUCCESS  表示此条明细已经被退款成功 */
						if("SUCCESS".equals(oldOrderItem.getRefundStatus())){
							oldOrderItem.setRefundStatus("1");//明细已被退款
							oldOrderItem.setStatus("9");//淘宝关闭
						}
					}
					SalesOrderitemVo  updateSalesOrderItem = new SalesOrderitemVo();
					BeanUtils.copyProperties(updateSalesOrderItem, oldOrderItem);
					orderitemDao.updateByPrimaryKeySelective(updateSalesOrderItem);
				}
			}
		}
		orderDao.updateByPrimaryKeySelective(salesOrderVo);//更新原订单到数据库
		logger.info("tb订单:"+salesOrderVo.getTopTids()+"已更新");
		return commResponse;
	}
	
	private  List<SalesOrderVo> downTBSalesOrder(SalesOrderVo salesOrderVo , Shop shop) throws Exception{
		List<SalesOrderVo> saleOrderList = new ArrayList<SalesOrderVo>();
		/*分页信息*/
		Boolean hasNext=true;//是否还有下一页
		Long page=1l;//起始页
		Long pageSize=20l;//每页条目数
		while(hasNext){
			/**订单分页数据*/
			TotalResult total = OrderMgrUtil.getTotalNum(salesOrderVo.getDownStartTime(), salesOrderVo.getDownEndTime(), shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret());
			PageResult pageResult = OrderMgrUtil.getTrades(salesOrderVo.getDownStartTime(), salesOrderVo.getDownEndTime(), shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret(),hasNext,page,pageSize);//订单分页数据
			if(pageResult.isSuccess()){
				//下载成功
				hasNext = pageResult.isHasNext();//是否仍有下一页
				page=page+1;//目标页数+1
				List<?> trades= pageResult.getList();//订单列表
				//2.解析淘宝返回数据 组装数据
				if (trades == null) {
					break;
				}
				for(Object trade : trades ){
					//淘宝订单  转化为本地订单
					Trade tbTrade=(Trade)trade;
					SalesOrderVo order = new SalesOrderVo();
					order.setCreateDate(new Date());
					order.setShopId(shop.getId());
					order.setTopTids(tbTrade.getTid()+"");
					order.setPlatType(shop.getPlanType());
					order.setStockId(shop.getWarehouseId());
					order.setCustNick(tbTrade.getBuyerNick());
					order.setRecvname(tbTrade.getReceiverName());
					order.setRecvmobile(tbTrade.getReceiverMobile());
					order.setRecvphone(tbTrade.getReceiverPhone());
					order.setProv(tbTrade.getReceiverState());
					order.setCity(tbTrade.getReceiverCity());
					order.setCounty(tbTrade.getReceiverDistrict());
					order.setAddress(tbTrade.getReceiverAddress());
					order.setZipcode(tbTrade.getReceiverZip());
					order.setAdjustfee(new BigDecimal(tbTrade.getAdjustFee()));
					order.setDeliveryfee(new BigDecimal(tbTrade.getPostFee()));
					order.setDiscountfee(new BigDecimal(tbTrade.getDiscountFee()));
					order.setTotalFee(new BigDecimal(tbTrade.getTotalFee()));
					order.setPayedMoney(new BigDecimal(tbTrade.getPayment()));
					order.setPlatStatus(tbTrade.getStatus());
					order.setTaxAmount(new BigDecimal(tbTrade.getOrderTaxFee()));
					order.setPayTime(tbTrade.getPayTime());
					order.setHassend("0");
					order.setHasmerge("0");
					order.setHasfaudit("0");
					order.setDisabled("0");
					order.setHasrefund("0");
					order.setEcprinted("0");
					order.setSendprinted("0");
					order.setHasscaned("0");
					order.setBuyTime(tbTrade.getCreated());
					order.setTimestamp(salesOrderVo.getTimestamp());
					order.setHasscaned("0");
					order.setHasscaned("0");
					order.setSendStatus("0");
					order.setDisabled("0");
					order.setExceptionStatus("0");
					order.setModified(tbTrade.getModified());
					order.setModifyDate(DateUtil.getDateTime(tbTrade.getModified()));
					//假若备注等信息  需要调用接口 获得
					if((tbTrade.getHasBuyerMessage()!=null && tbTrade.getHasBuyerMessage()) || tbTrade.getSellerFlag()>0){
						Trade fulltrade=OrderMgrUtil.getFullTrade(tbTrade.getTid() , shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret());
						order.setBuyerFlag(fulltrade.getBuyerFlag()+"");
						order.setBuyerMemo(fulltrade.getBuyerMessage());
						order.setSellerFlag(fulltrade.getSellerFlag()+"");
						order.setSellerMemo(fulltrade.getSellerMemo());
					}
					
					
					List<SalesOrderitemVo> orderItemList = new ArrayList<SalesOrderitemVo>();
					List<Order> itemList = tbTrade.getOrders();
					if(itemList != null && itemList.size()>0){
						for(Order item : itemList){
							SalesOrderitemVo orderItem = new SalesOrderitemVo();
							orderItem.setOid(item.getOid() + "");
							orderItem.setTbId(tbTrade.getTid()+"");
							orderItem.setOuterIid(item.getOuterIid());
							orderItem.setOuterSkuId(item.getOuterSkuId());
							orderItem.setQty(item.getNum());
							orderItem.setSalsePrice(new BigDecimal(item.getPrice()));
							orderItem.setTotsalMoney(new BigDecimal(item.getTotalFee()));
							orderItem.setIsgift(false);
							orderItem.setGdsName(item.getTitle());
							orderItem.setNumiid(item.getNumIid());
							orderItem.setTopSkuid(item.getSkuId());
							orderItem.setPlatRefundStatus(item.getRefundStatus());
							orderItem.setPlatStatus(item.getStatus());
							orderItem.setAdjustFee(new BigDecimal(item.getAdjustFee()));
							orderItem.setDiscountFee(new BigDecimal(item.getDiscountFee()));
							orderItem.setPayment(new BigDecimal(item.getPayment()));
							orderItemList.add(orderItem);
						
						}
					}
					order.setItemList(orderItemList);
					saleOrderList.add(order);
				}
			}else{
				throw new DHBizException(ExceptionConstant.ERR_DH000004);
			}	
		}
		return saleOrderList;
	}
	
	
	private  List<SalesOrderVo> downWSCSalesOrder(SalesOrderVo salesOrderVo , Shop shop) throws Exception{
		List<SalesOrderVo> saleOrderList = new ArrayList<SalesOrderVo>();
		/*分页信息*/
		Boolean hasNext=true;//是否还有下一页
		Long page=1l;//起始页
		Long pageSize=20l;//每页条目数
		while(hasNext){
			/**订单分页数据*/
			//TotalResult total = OrderMgrUtil.getTotalNum(salesOrderVo.getDownStartTime(), salesOrderVo.getDownEndTime(), shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret());
			//PageResult pageResult = OrderMgrUtil.getTrades(salesOrderVo.getDownStartTime(), salesOrderVo.getDownEndTime(), shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret(),hasNext,page,pageSize);//订单分页数据
			
			resultEntity retobjects =  WSCUtil.searchOrder(shop,salesOrderVo.getDownStartTime(), salesOrderVo.getDownEndTime(),page );
			
			if( retobjects != null){
				//下载成功
				if(retobjects.getTotalCount()<= page * pageSize )
				{
				   hasNext = false;//是否仍有下一页
				}
				page=page+1;//目标页数+1
			
				for(resultOrder row : retobjects.getEntity()){
					//淘宝订单  转化为本地订单
					//Trade tbTrade=(Trade)trade;
				    List<resultOrderDetail> itemList =  row.getDetails();
				    SalesOrderVo order = new SalesOrderVo();
				    
				    if("M2016050609180775296".equals(row.getTid()))
				    {
				    	order.setTopTids(row.getTid());
				    }
				
					
					order.setShopId(shop.getId());
					order.setCreateDate(row.getCreated()+""==""?new Date():DateUtil.getDate(row.getCreated()));
					
					order.setTopTids(row.getTid());
					order.setPlatType(shop.getPlanType());
					order.setStockId(shop.getWarehouseId());
					order.setCustNick(row.getBuyerNick());
					order.setIdentityCard(row.getIdentityCard());
					order.setRecvname(row.getReceiverName());
					order.setRecvmobile(row.getReceiverMobile());
					order.setRecvphone(row.getReceiverPhone());
					order.setProv(row.getReceiverState());
					order.setCity(row.getReceiverCity());
					order.setCounty(row.getReceiverDistrict());
					order.setAddress(row.getReceiverAddress());
					order.setZipcode(row.getReceiverZip());
					order.setAdjustfee(new BigDecimal(row.getAdjustFee()));
					order.setDeliveryfee(new BigDecimal(row.getPostFee()));
					order.setDiscountfee(new BigDecimal(row.getDiscountFee()));
					order.setTotalFee(new BigDecimal(row.getTotalFee()));
					order.setPayedMoney(new BigDecimal(row.getPayment()));
					order.setPayTime(DateUtil.getDate(row.getPayTime()));
					order.setPlatStatus(row.getStatus());
					order.setHassend("0");
					order.setHasmerge("0");
					order.setHasfaudit("0");
					order.setDisabled("0");
					order.setHasrefund("0");
					order.setEcprinted("0");
					order.setSendprinted("0");
					order.setHasscaned("0");
			
					order.setBuyTime(DateUtil.getDate(row.getCreated()));
					order.setTimestamp(salesOrderVo.getTimestamp());
					order.setHasscaned("0");
					order.setHasscaned("0");
					order.setSendStatus("0");
					order.setDisabled("0");
					order.setExceptionStatus("0");

					if (row.getModified() == null)
					{
						row.setModified(row.getCreated());
					}
					order.setModified(DateUtil.getDate(row.getModified()));
					order.setModifyDate(row.getModified());
					
					//假若备注等信息  需要调用接口 获得
					/*
					if((row.getHasBuyerMessage()!=null && tbTrade.getHasBuyerMessage()) || tbTrade.getSellerFlag()>0){
						Trade fulltrade=OrderMgrUtil.getFullTrade(tbTrade.getTid() , shop.getSessionkey(),shop.getAppkey(),shop.getAppsecret());
						order.setBuyerFlag(fulltrade.getBuyerFlag()+"");
						order.setBuyerMemo(fulltrade.getBuyerMessage());
						order.setSellerFlag(fulltrade.getSellerFlag()+"");
						order.setSellerMemo(fulltrade.getSellerMemo());
					}
					*/
					
					List<SalesOrderitemVo> orderItemList = new ArrayList<SalesOrderitemVo>();
					
					//List<Order> itemList = tbTrade.getOrders();
					
					if(itemList != null && itemList.size()>0){
						for(resultOrderDetail item : itemList){
							SalesOrderitemVo orderItem = new SalesOrderitemVo();
							orderItem.setOid(item.getOid());
							orderItem.setTbId(row.getTid());
							orderItem.setOuterIid(item.getOuterIid());
							orderItem.setOuterSkuId(item.getOuterSkuId());
							orderItem.setQty(Long.parseLong(item.getNum()));
							orderItem.setSalsePrice(new BigDecimal(item.getPrice()));
							orderItem.setTotsalMoney(new BigDecimal(item.getTotalFee()));
							orderItem.setIsgift(false);
							orderItem.setGdsName(item.getTitle());
							//orderItem.setNumiid(item.getOuterIid());
							orderItem.setTopSkuid(item.getOuterSkuId());
							orderItem.setPlatRefundStatus(item.getRefundStatus());
							orderItem.setPlatStatus(item.getRefundStatus());
							orderItemList.add(orderItem);
						}
					}
					order.setItemList(orderItemList);
					saleOrderList.add(order);
				}
			}else{
				throw new DHBizException(ExceptionConstant.ERR_DH000004);
			}	
		}
		return saleOrderList;
	}

}
