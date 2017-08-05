package com.dinghao.service.template.business.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.order.SalesOrderDao;
import com.dinghao.dao.template.business.order.SalesOrderactionDao;
import com.dinghao.dao.template.business.order.SalesOrderitemDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.order.SalesOrder;
import com.dinghao.entity.template.business.order.SalesOrderitem;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderactionVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.order.IExceptionOrderService;
import com.dinghao.util.BeanUtils;

@Service
@Transactional
public class ExceptionOrderServiceImpl implements IExceptionOrderService{

	/** 日志记录  */
	private static final Log logger = LogFactory.getLog(SalesOrderServiceImpl.class);
	@Autowired
	private SalesOrderDao orderDao;
	@Autowired
	private SalesOrderitemDao orderitemDao;
	@Autowired
	private SalesOrderactionDao orderActionDao;
	@Autowired
	private LocStockDao locStockDao;

	/**
	  * <p>Title: findExceptionSalesOrderForGrid</p>
	  * <p>Description: 分页查询异常订单数据</p>
	  * @param salesOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.order.IExceptionOrderService#findExceptionSalesOrderForGrid(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse findExceptionSalesOrderForGrid(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<SalesOrderVo> pageVo = new PageVo<SalesOrderVo>();
		pageVo.setRows(salesOrderVo.getRows());
		pageVo.setList(orderDao.selectOrderListGrid(salesOrderVo));
		pageVo.setCount(orderDao.selectOrderListCount(salesOrderVo));
		commResponse.setData(pageVo);
		return commResponse;
	}

	@Override
	public CommResponse modExceptionOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if(null == salesOrderVo.getId() || "".equals(salesOrderVo.getId())){
			throw new DHBizException("修改的订单信息有误");
		}
		/**1.得到要修改订单 的信息*/
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		if(salesOrder == null){
			throw new DHBizException("订单不存在，请联系管理员!");
		}
		if("0".equals(salesOrder.getExceptionStatus())){
			throw new DHBizException("订单已变为正常状态，请刷新页面!");
		}
		
		/**2.验证订单是否符合修改条件*/
		if("1".equals(salesOrder.getDisabled())){
			throw new DHBizException("订单"+salesOrderVo.getOrderNum()+"已作废,不能进行修改操作!");
		}
		if("1".equals(salesOrder.getHasfaudit())){
			throw new DHBizException("订单"+salesOrderVo.getOrderNum()+"已审核,不能进行修改操作!");
		}
		if("1".equals(salesOrder.getHasrefund())){
			throw new DHBizException("订单"+salesOrderVo.getOrderNum()+"已退款,不能进行修改操作!");
		}
		
		/**3.得到原订单数据*/
		List<SalesOrderitemVo> orderItemList = orderitemDao.selectOrderItemListByOrderId(salesOrderVo.getId()+"");
		/*把原订单明细放入一个map集合中
		 * 当遍历前台传的订单商品明细时 ,根据明细id取  oldOrderItemMap同时删除
		 * 剩余的没有被取走的明细则为 需要删除的明细   */
		Map<String,SalesOrderitemVo> oldOrderItemMap= new HashMap<String, SalesOrderitemVo>();
		for(SalesOrderitemVo item : orderItemList){
			oldOrderItemMap.put(item.getId()+"", item);
		}
		
		/**4.修改订单信息*/
		List<LocStockVo> locStockList = new ArrayList<LocStockVo>();
		//假设已经变为正常订单了
		salesOrder.setExceptionStatus("0");
		
		salesOrder.setExpid(salesOrderVo.getExpid());
		salesOrder.setMark(salesOrderVo.getMark());
		salesOrder.setSellerMemo(salesOrderVo.getSellerMemo());
		salesOrder.setProv(salesOrderVo.getProv());
		salesOrder.setCity(salesOrderVo.getCity());
		salesOrder.setCounty(salesOrderVo.getCounty());
		for(SalesOrderitemVo orderItem : salesOrderVo.getItemList()){
			Long locId = null;
			if(orderItem.getLocId()==null && null != orderItem.getGdsId() && !"".equals(orderItem.getGdsId())){
				LocStockVo locStockVo = new LocStockVo();
				locStockVo.setStockId(salesOrder.getStockId());
				locStockVo.setGdsId(orderItem.getGdsId());
				LocStockVo mlocStock = locStockDao.selectMastuseQtyByParam(locStockVo);
				if(mlocStock != null && mlocStock.getUseQty() > orderItem.getQty()){
					locId = orderItem.getLocId();
					LocStockVo locStockMod = new LocStockVo();
					locStockMod.setId(mlocStock.getId());
					if(mlocStock.getOrderQty() == null){
						mlocStock.setOrderQty(0L);
					}
					locStockMod.setOrderQty(mlocStock.getOrderQty() + orderItem.getQty());
					locStockList.add(locStockMod);
				}else{
					if("1".equals(salesOrder.getExceptionStatus())  || "3".equals(salesOrder.getExceptionStatus()) ){
						salesOrder.setExceptionStatus("3");
					}else{
						salesOrder.setExceptionStatus("2");//系统没有此商品
					}
				}
			}
			else
			{
				locId = orderItem.getLocId();
			}
			
			if(orderItem.getId() == null || "".equals(orderItem.getId())){
				orderItem.setOrderId(salesOrder.getId()+"");
				orderItem.setTbId(salesOrder.getTopTids());
				orderItem.setLocId(locId);
				orderitemDao.insertSelective(orderItem);
			}else{
				SalesOrderitemVo salesOrderitem = oldOrderItemMap.remove(orderItem.getId()+"");
				salesOrderitem.setGdsId(orderItem.getGdsId());
				salesOrderitem.setQty(orderItem.getQty());
				salesOrderitem.setIsgift(orderItem.getIsgift());
				salesOrderitem.setRefundStatus("0");
				salesOrderitem.setStatus("0");
				salesOrderitem.setLocId(locId);
				salesOrderitem.setGdsName(orderItem.getGdsName());
				SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
				//BeanUtils.copyProperties(salesOrderitemVo, salesOrderitem);
				salesOrderitemVo = salesOrderitem;
				orderitemDao.updateByPrimaryKeySelective(salesOrderitemVo);
				
				if(null == orderItem.getGdsId() || ("").equals(orderItem.getGdsId())){
					if("2".equals(salesOrder.getExceptionStatus())  || "3".equals(salesOrder.getExceptionStatus()) ){
						salesOrder.setExceptionStatus("3");
					}else{
						salesOrder.setExceptionStatus("1");//系统没有此商品
					}
					continue;
				}
			}
		}
		
		//删除改订单商品明细   --  oldOrderItemMap 中还有剩余的明细 则表示这些明细是需要被 删除
		Set<String> delItemSet = oldOrderItemMap.keySet();
		for (String id : delItemSet) {
			orderitemDao.deleteByPrimaryKey(Long.parseLong(id));
		}
		//修改订单主表
		SalesOrderVo modOrder = new SalesOrderVo();
		//BeanUtils.copyProperties(modOrder, salesOrder);
		modOrder = salesOrder;
		orderDao.updateByPrimaryKeySelective(modOrder);
		//正常订单   重新设置现货表的待发货数量
		if("0".equals(modOrder.getExceptionStatus())){
			for(LocStockVo locStockVo : locStockList){
				locStockDao.updateByPrimaryKeySelective(locStockVo);
			}
		}
		
		/**5.添加订单日志*/
		SalesOrderactionVo salesOrderactionVo = new SalesOrderactionVo();
		salesOrderactionVo.setOrderId(salesOrderVo.getId());
		salesOrderactionVo.setActionType("修改");
		salesOrderactionVo.setActionMemo("订单:"+salesOrderVo.getOrderNum()+"修改成功");
		salesOrderactionVo.setActionTime(new Date());
		orderActionDao.insertSelective(salesOrderactionVo);
		
		commResponse.setErrMsg("订单:"+salesOrder.getOrderNum()+"修改通过!");
		return commResponse;
	}

}
