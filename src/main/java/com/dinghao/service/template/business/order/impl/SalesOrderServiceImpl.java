package com.dinghao.service.template.business.order.impl;

import java.math.BigDecimal;
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

import com.dinghao.dao.template.base.BaseAreaDao;
import com.dinghao.dao.template.base.ShopDao;
import com.dinghao.dao.template.business.express.DmsExpressDao;
import com.dinghao.dao.template.business.express.DmsExpressareaDao;
import com.dinghao.dao.template.business.order.SalesOrderDao;
import com.dinghao.dao.template.business.order.SalesOrderactionDao;
import com.dinghao.dao.template.business.order.SalesOrderitemDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.template.business.express.DmsExpressarea;
import com.dinghao.entity.template.business.order.SalesOrderitem;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.BaseAreaVo;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressareaVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderListVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderactionVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.base.BaseNumberService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.util.BeanUtils;
import com.dinghao.util.DateUtil;
import com.dinghao.util.StringUtil;
import com.dinghao.util.TBUtil;
import com.dinghao.util.WSCUtil;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.response.LogisticsOfflineSendResponse;

/**
 * @ClassName: SalesOrderServiceImpl
 * @Description: TODO 订单管理service实现
 * @author helong
 * @date 2016年1月25日 下午6:53:02
 * @version V1.0
 *
 */
@Service
@Transactional
public class SalesOrderServiceImpl implements ISalesOrderService {

	/** 日志记录 */
	private static final Log logger = LogFactory.getLog(SalesOrderServiceImpl.class);
	@Autowired
	private SalesOrderDao orderDao;
	@Autowired
	private SalesOrderitemDao orderitemDao;
	@Autowired
	private BaseNumberService numberService;
	@Autowired
	private SalesOrderactionDao orderActionDao;
	@Autowired
	private LocStockDao locStockDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private DmsExpressDao expressDao;
	@Autowired
	private BaseAreaDao areaDao;
	@Autowired
	private DmsExpressareaDao expressareaDao;

	public SalesOrderVo querySalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		return salesOrder;
	}
	
	public int updateSalesOrder(SalesOrderVo salesOrderVo) throws Exception{
		return orderDao.updateByPrimaryKeySelective(salesOrderVo);
	
	}
	/**
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 */
	public List<SalesOrderVo>  querySalesOrderbyTid(String topTids) throws Exception {
		List<SalesOrderVo>  salesOrderList = orderDao.selectOrderListByTid(topTids);
		return salesOrderList;
	}

	public CommResponse querySalesOrderItemList(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
		salesOrderitemVo.setOrderId(salesOrderVo.getId() + "");
		
		List<SalesOrderitemVo> detailList = orderitemDao.selectOrderItemList(salesOrderitemVo);
	
		for(SalesOrderitemVo detail : detailList){ 	
			if(null != detail.getAttbs() && !"".equals(detail.getAttbs())){
				String attbName[] = detail.getAttbs().split(";");
				String attbs ="";
				for(String attb : attbName){
					attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
				}
				attbs = attbs.substring(1, attbs.length());
				detail.setAttbs(attbs);
			}else{
				detail.setAttbs("");
			}
		
		}
		
		commResponse.setData(detailList);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: findSalesOrderForGrid
	 * </p>
	 * <p>
	 * Description: 分页查询订单数据
	 * </p>
	 * 
	 * @param purOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#findSalesOrderForGrid(com.dinghao.entity.vo.template.business.purchase.PurOrderVo)
	 */
	public CommResponse findSalesOrderForGrid(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<SalesOrderVo> pageVo = new PageVo<SalesOrderVo>();
		pageVo.setRows(salesOrderVo.getRows());
		List<SalesOrderVo> mSalesOrderVo =  orderDao.selectOrderListGrid(salesOrderVo);
		
		pageVo.setList(mSalesOrderVo);
		pageVo.setCount(orderDao.selectOrderListCount(salesOrderVo));
		pageVo.setPageNum(salesOrderVo.getPageNum());
		//pageVo.setOffset(salesOrderVo.get());
		commResponse.setData(pageVo);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: combineSalesOrder
	 * </p>
	 * <p>
	 * Description: 合并订单
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#combineSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse combineSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo combineSalesOrderVo = new SalesOrderVo();
		Long orderId = null;
		String fromcodes = "";
		try {
			String toptips[] = salesOrderVo.getTopTids().split(",");
			List<SalesOrderitemVo> detailList = new ArrayList<SalesOrderitemVo>();
			// 需要重新计算的费用
			BigDecimal deliverycost = new BigDecimal(0); // 物流费
			BigDecimal adjustfee = new BigDecimal(0); // 调整金额
			BigDecimal discountfee = new BigDecimal(0); // 优惠金额
			BigDecimal totalFee = new BigDecimal(0); // 商品金额
			BigDecimal payedMoney = new BigDecimal(0); // 已付金额

			int index = 0;
			// 遍历所需要合并的订单
			for (String tid : toptips) {
				index++;
				/** 1.检查每个订单是否支持合并操作 */
				List<SalesOrderVo> list = orderDao.selectOrderListByTid(tid);
				if (null == list) {
					throw new DHBizException("订单:" + tid + "不存在系统中!");
				}
				if (list.size() != 1) {
					throw new DHBizException("订单:" + tid + "在系统中异常!");
				}
				SalesOrderVo salesOrder = list.get(0);
				if ("1".equals(salesOrder.getHasfaudit())) {
					throw new DHBizException("订单:" + tid + "已审核,不能进行合并操作!");
				}
				if ("1".equals(salesOrder.getDisabled())) {
					throw new DHBizException("订单:" + tid + "已作废,不能进行合并操作!");
				}

				/** 2.假若是合并的第一个订单则给生成的新订单赋上相关初始属性 */
				if (index == 1) {
					//BeanUtils.copyProperties(combineSalesOrderVo, salesOrder);
					combineSalesOrderVo = salesOrder;
					// 生成订单的单据号
					BaseNumberVo baseNumberVo = new BaseNumberVo();
					baseNumberVo.setNuType("06");
					CommResponse numRes = numberService.findBaseNumberNext(baseNumberVo);
					combineSalesOrderVo.setOrderNum(numRes.getData().toString());
				}

				/** 3.修改被合并订单的状态 */
				SalesOrderVo combinedOrder = new SalesOrderVo();
				//BeanUtils.copyProperties(combinedOrder, salesOrder);
				combinedOrder = salesOrder;
				combinedOrder.setHasmerge("1");
				combinedOrder.setDisabled("1");
				orderDao.updateByPrimaryKeySelective(combinedOrder);
				fromcodes += "," + salesOrder.getOrderNum();

				/** 4.查询订单明细数据 */
				SalesOrderitemVo itemParam = new SalesOrderitemVo();
				itemParam.setTbId(tid);
				itemParam.setOrderId(salesOrder.getId() + "");
				List<SalesOrderitem> orderItemList = orderitemDao.selectOrderItemListByParam(itemParam);
				for (SalesOrderitem orderItem : orderItemList) {
					SalesOrderitemVo newDetail = new SalesOrderitemVo();
					BeanUtils.copyProperties(newDetail, orderItem);
					detailList.add(newDetail);
				}
				/** 5.重新计算费用 */
				deliverycost = deliverycost
						.add(salesOrder.getDeliverycost() == null ? new BigDecimal(0) : salesOrder.getDeliverycost());
				adjustfee = adjustfee
						.add(salesOrder.getAdjustfee() == null ? new BigDecimal(0) : salesOrder.getAdjustfee());
				discountfee = discountfee
						.add(salesOrder.getDiscountfee() == null ? new BigDecimal(0) : salesOrder.getDiscountfee());
				totalFee = totalFee
						.add(salesOrder.getTotalFee() == null ? new BigDecimal(0) : salesOrder.getTotalFee());
				payedMoney = payedMoney
						.add(salesOrder.getPayedMoney() == null ? new BigDecimal(0) : salesOrder.getPayedMoney());
			}
			/** 6.组装参数插入主表 */
			combineSalesOrderVo.setId(null);
			combineSalesOrderVo.setTopTids(salesOrderVo.getTopTids());
			combineSalesOrderVo.setFromcodes(fromcodes.substring(1, fromcodes.length()));
			combineSalesOrderVo.setDeliverycost(deliverycost);
			combineSalesOrderVo.setAdjustfee(adjustfee);
			combineSalesOrderVo.setDiscountfee(discountfee);
			combineSalesOrderVo.setTotalFee(totalFee);
			combineSalesOrderVo.setPayedMoney(payedMoney);
			orderDao.insertSelective(combineSalesOrderVo);
			orderId = combineSalesOrderVo.getId();
			/** 7.插入明细表 */
			for (SalesOrderitemVo detail : detailList) {
				detail.setId(null);
				detail.setOrderId(orderId + "");
				detail.setTbId(salesOrderVo.getTopTids());
				orderitemDao.insertSelective(detail);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new DHBizException("合并订单失败 !");
		}
		/** 8.添加订单日志 */
		SalesOrderactionVo salesOrderactionVo = new SalesOrderactionVo();
		salesOrderactionVo.setOrderId(orderId);
		salesOrderactionVo.setActionType("合并");
		salesOrderactionVo.setActionMemo(
				"由:" + fromcodes.substring(1, fromcodes.length()) + "合并成:" + combineSalesOrderVo.getOrderNum());
		salesOrderactionVo.setActionTime(new Date());
		orderActionDao.insertSelective(salesOrderactionVo);
		commResponse.setErrMsg("订单合并成功!");
		return commResponse;
	}

	/**
	 * <p>
	 * Title: cancelComSalesOrder
	 * </p>
	 * <p>
	 * Description: 取消合并订单
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#cancelComSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse cancelComSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/** 1.得到要取消拆分订单 的信息 */
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		/** 2.验证订单是否符合拆分条件 */
		if (null == salesOrder.getFromcodes() || "".equals(salesOrder.getFromcodes())) {
			throw new DHBizException("订单:" + salesOrderVo.getOrderNum() + "有误,不能进行取消合并操作!");
		}
		String fromcodes[] = salesOrder.getFromcodes().split(",");
		if (fromcodes.length < 2) {
			throw new DHBizException("订单+salesOrderVo.getOrderNum()+有误,不能进行取消合并操作!");
		}
		if ("1".equals(salesOrder.getDisabled())) {
			throw new DHBizException("订单+salesOrderVo.getOrderNum()+已作废,不能进行取消合并操作!");
		}
		if ("1".equals(salesOrder.getHasfaudit())) {
			throw new DHBizException("订单+salesOrderVo.getOrderNum()+已审核,不能进行取消合并操作!");
		}
		SalesOrderVo morderVo = null;
		SalesOrderVo orderVo = new SalesOrderVo();
		/** 3.遍历原合并的订单，恢复其状态 */
		for (String orderNum : fromcodes) {
			morderVo = orderDao.selectByOrderNum(orderNum);
			if (morderVo == null) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "有误,原订单不存在!");
			}
			morderVo.setDisabled("0");
			morderVo.setHasmerge("0");
			//BeanUtils.copyProperties(orderVo, morderVo);
			orderVo = morderVo;
			orderDao.updateByPrimaryKeySelective(orderVo);
		}
		/** 4.删除合并订单 */
		orderDao.deleteByPrimaryKey(salesOrder.getId());
		/** 5.删除合并订单 明细 */
		orderitemDao.deleteByOrderId(salesOrder.getId() + "");
		commResponse.setErrMsg("取消合并订单操作成功!");
		return commResponse;
	}

	/**
	 * <p>
	 * Title: auditSalesOrder
	 * </p>
	 * <p>
	 * Description: 审核订单
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#auditSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse auditSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if (null == salesOrderVo.getIds() || "".equals(salesOrderVo.getIds())) {
			throw new DHBizException("请选择需要审核的订单");
		}
		String ids[] = salesOrderVo.getIds();
		String orderNums = "";
		for (String id : ids) {
			/** 1.得到要审核订单 的信息 */
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(Long.parseLong(id));
			if (salesOrder == null) {
				throw new DHBizException("订单不存在，请联系管理员!");
			}
			/** 2.验证订单是否符合审核条件 */
			if ("1".equals(salesOrder.getDisabled())) {
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "已作废,不能进行审核操作!");
			}
			if ("1".equals(salesOrder.getHasfaudit())) {
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "已审核,请勿重复操作!");
			}
			if ("1".equals(salesOrder.getHasrefund())) {
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "已退款,不能进行审核操作!");
			}
			if ("".equals(salesOrder.getProv())) {
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "收货地址不能为空");
			}
			/** 3.判断物流是否到达 */
			BaseAreaVo area = new BaseAreaVo();
			area.setRegionName(salesOrder.getProv());
			area.setRegionType(1);
			// 查询省份id
			Long provId = areaDao.selectAreaIdByParam(area).getId();

			DmsExpressareaVo expressareaVo = new DmsExpressareaVo();
			expressareaVo.setExpressId(salesOrder.getExpid());
			expressareaVo.setProvId(provId);
			DmsExpressarea dmsExpressarea = expressareaDao.selectByParam(expressareaVo);
			if (dmsExpressarea == null || dmsExpressarea.getExpressId() == null) {
				logger.error("物流公司id:" + salesOrder.getExpid() + "没有地区 :" + salesOrderVo.getProv() + "的信息");
				throw new DHBizException("系统物流数据有误，请联系管理员");
			}

			if (("0").equals(dmsExpressarea.getIsdelivery())) {
				throw new DHBizException("订单:" + salesOrder.getOrderNum() + "不到达收货地区,请修改打印模板");
			}

			/** 4.改变审核状态 */
			salesOrder.setHasfaudit("1");
			SalesOrderVo orderVo = new SalesOrderVo();
			//BeanUtils.copyProperties(orderVo, salesOrder);
			orderVo = salesOrder;
			orderDao.updateByPrimaryKeySelective(orderVo);
			orderNums += "," + orderVo.getOrderNum();
			/** 5.添加订单日志 */
			SalesOrderactionVo salesOrderactionVo = new SalesOrderactionVo();
			salesOrderactionVo.setOrderId(orderVo.getId());
			salesOrderactionVo.setActionType("审核");
			salesOrderactionVo.setActionMemo("订单:" + orderVo.getOrderNum() + "审核成功");
			salesOrderactionVo.setActionTime(new Date());
			orderActionDao.insertSelective(salesOrderactionVo);
		}
		commResponse.setErrMsg("订单:" + orderNums.substring(1, orderNums.length()) + "审核通过!");
		return commResponse;
	}

	/**
	 * <p>
	 * Title: cancelAuditSalesOrder
	 * </p>
	 * <p>
	 * Description: 取消审核
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#cancelAuditSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse cancelAuditSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if (null == salesOrderVo.getIds() || "".equals(salesOrderVo.getIds())) {
			throw new DHBizException("请选择需要取消审核的订单");
		}
		String ids[] = salesOrderVo.getIds();
		String orderNums = "";
		for (String id : ids) {
			/** 1.得到取消 审核订单 的信息 */
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(Long.parseLong(id));
			if (salesOrder == null) {
				throw new DHBizException("订单不存在，请联系管理员!");
			}
			/** 2.验证订单是否符合取消审核条件 */
			if ("1".equals(salesOrder.getDisabled())) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已作废,不能进行取消审核操作!");
			}
			if ("0".equals(salesOrder.getHasfaudit())) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "未审核,不能进行取消审核操作!");
			}
			if ("1".equals(salesOrder.getHassend())) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已发货,不能进行取消审核操作!");
			}
			/** 3.改变审核状态 */
			salesOrder.setHasfaudit("0");
			SalesOrderVo orderVo = new SalesOrderVo();
			//BeanUtils.copyProperties(orderVo, salesOrder);
			orderVo = salesOrder;
			orderDao.updateByPrimaryKeySelective(orderVo);
			orderNums += "," + orderVo.getOrderNum();
		}
		commResponse.setErrMsg("订单:" + orderNums.substring(1, orderNums.length()) + "取消审核通过!");
		return commResponse;
	}

	/**
	 * <p>
	 * Title: modSalesOrder
	 * </p>
	 * <p>
	 * Description: 修改订单信息
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#modSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse modSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if (null == salesOrderVo.getId() || "".equals(salesOrderVo.getId())) {
			throw new DHBizException("修改的订单信息有误");
		}
		/** 1.得到要修改订单 的信息 */
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		if (salesOrder == null) {
			throw new DHBizException("订单不存在，请联系管理员!");
		}
		if (!"0".equals(salesOrder.getExceptionStatus())) {
			throw new DHBizException("订单已变为异常状态，请刷新页面!");
		}

		/** 2.验证订单是否符合修改条件 */
		if ("1".equals(salesOrder.getDisabled())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已作废,不能进行修改操作!");
		}
		if ("1".equals(salesOrder.getHasfaudit())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已审核,不能进行修改操作!");
		}
		if ("1".equals(salesOrder.getHasrefund())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已退款,不能进行修改操作!");
		}

		/** 3.回收占用库存 */
		List<SalesOrderitemVo> orderItemList = orderitemDao.selectOrderItemListByOrderId(salesOrderVo.getId() + "");
		/*
		 * 把原订单明细放入一个map集合中 当遍历前台传的订单商品明细时 ,根据明细id取 oldOrderItemMap同时删除
		 * 剩余的没有被取走的明细则为 需要删除的明细
		 */
		Map<String, SalesOrderitemVo> oldOrderItemMap = new HashMap<String, SalesOrderitemVo>();
		for (SalesOrderitemVo item : orderItemList) {
			oldOrderItemMap.put(item.getId() + "", item);
		}
		// 循环释放库存
		for (SalesOrderitemVo orderitem : orderItemList) {
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setStockId(salesOrder.getStockId());
			locStockVo.setLocId(orderitem.getLocId() == null ? 0 : orderitem.getLocId());
			locStockVo.setGdsId(orderitem.getGdsId());
			List<LocStockVo> locStockList = locStockDao.selectByStatementDetail(locStockVo);
			if (locStockList == null || locStockList.size() == 0) {
				// 增加库存现货表记录
				locStockVo.setTotalQty(0L);
				locStockVo.setOrderQty(0L);
				locStockVo.setSafeQty(0L);
				locStockDao.insertSelective(locStockVo);

			} else if (locStockList.size() > 1) {
				logger.error("库存记录有误!stockId=" + salesOrder.getStockId() + "locId=" + orderitem.getLocId());
				throw new DHBizException("修改订单过程发生异常");
			} else {
				Long locStockId = locStockList.get(0).getId();
				Long orderQty = locStockList.get(0).getOrderQty();
				locStockVo.setId(locStockId);
				locStockVo.setOrderQty(orderQty - orderitem.getQty());
				locStockDao.updateByPrimaryKeySelective(locStockVo);
			}
		}

		/** 4.修改订单明细信息 */
		List<LocStockVo> locStockList = new ArrayList<LocStockVo>();
		salesOrder.setExpid(salesOrderVo.getExpid());
		salesOrder.setMark(salesOrderVo.getMark());
		salesOrder.setSellerMemo(salesOrderVo.getSellerMemo());
		salesOrder.setProv(salesOrderVo.getProv());
		salesOrder.setCity(salesOrderVo.getCity());
		salesOrder.setCounty(salesOrderVo.getCounty());
		salesOrder.setIdentityCard(salesOrderVo.getIdentityCard());
		salesOrder.setRecvmobile(salesOrderVo.getRecvmobile());
		salesOrder.setStockId(salesOrderVo.getStockId());
		for (SalesOrderitemVo orderItem : salesOrderVo.getItemList()) {
			if (null == orderItem.getGdsId() || "".equals(orderItem.getGdsId())) {
				throw new DHBizException("数据有误：没有 商品信息,请重新刷新页面!");
			}
			Long locId = null;
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setStockId(salesOrder.getStockId());
			locStockVo.setGdsId(orderItem.getGdsId());
			LocStockVo mlocStockVo = locStockDao.selectMastuseQtyByParam(locStockVo);
			if (mlocStockVo != null && mlocStockVo.getUseQty() > orderItem.getQty()) {
				locId = mlocStockVo.getLocId();
				LocStockVo locStockMod = new LocStockVo();
				locStockMod.setId(mlocStockVo.getId());
				if (mlocStockVo.getOrderQty() == null) {
					mlocStockVo.setOrderQty(0L);
				}
				locStockMod.setOrderQty(mlocStockVo.getOrderQty() + orderItem.getQty());
				locStockList.add(locStockMod);
			} else {
				salesOrder.setExceptionStatus("2");// 商品缺货 回填表头商品缺货
			}

			if (orderItem.getId() == null || "".equals(orderItem.getId())) {
				orderItem.setOrderId(salesOrder.getId() + "");
				orderItem.setTbId(salesOrder.getTopTids());
				orderItem.setLocId(locId);
				orderitemDao.insertSelective(orderItem);
			} else {
				SalesOrderitemVo salesOrderitem = oldOrderItemMap.remove(orderItem.getId() + "");
				salesOrderitem.setGdsId(orderItem.getGdsId());
				salesOrderitem.setQty(orderItem.getQty());
				salesOrderitem.setIsgift(orderItem.getIsgift());
				salesOrderitem.setRefundStatus("0");
				salesOrderitem.setStatus("0");
				orderItem.setLocId(locId);
				salesOrderitem.setGdsName(orderItem.getGdsName());
				//SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
				//BeanUtils.copyProperties(salesOrderitemVo, salesOrderitem);
				orderitemDao.updateByPrimaryKeySelective(salesOrderitem);
			}
		}

		// 删除改订单商品明细 -- oldOrderItemMap 中还有剩余的明细 则表示这些明细是需要被 删除
		Set<String> delItemSet = oldOrderItemMap.keySet();
		for (String id : delItemSet) {
			orderitemDao.deleteByPrimaryKey(Long.parseLong(id));
		}
		// 修改订单主表
		//SalesOrderVo modOrder = new SalesOrderVo();
		//modOrder = salesOrder;
		//BeanUtils.copyProperties(modOrder, salesOrder);
		orderDao.updateByPrimaryKeySelective(salesOrder);
		// 正常订单 重新设置现货表的待发货数量
		if ("0".equals(salesOrder.getExceptionStatus())) {
			for (LocStockVo locStockVo : locStockList) {
				locStockDao.updateByPrimaryKeySelective(locStockVo);
			}
		}

		/** 5.添加订单日志 */
		SalesOrderactionVo salesOrderactionVo = new SalesOrderactionVo();
		salesOrderactionVo.setOrderId(salesOrderVo.getId());
		salesOrderactionVo.setActionType("修改");
		salesOrderactionVo.setActionMemo("订单:" + salesOrderVo.getOrderNum() + "修改成功");
		salesOrderactionVo.setActionTime(new Date());
		orderActionDao.insertSelective(salesOrderactionVo);

		commResponse.setErrMsg("订单:" + salesOrder.getOrderNum() + "修改通过!");
		return commResponse;
	}

	/**
	 * <p>
	 * Title: addSalesOrder
	 * </p>
	 * <p>
	 * Description: 添加订单
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#addSalesOrder(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse addSalesOrder(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();

		salesOrderVo.setAdjustfee(new BigDecimal("0.00"));
		salesOrderVo.setDeliverycost(new BigDecimal("0.00"));// 计算
		salesOrderVo.setDiscountfee(new BigDecimal("0.00"));
		salesOrderVo.setHassend("0");
		salesOrderVo.setHasmerge("0");
		salesOrderVo.setHasfaudit("0");
		salesOrderVo.setDisabled("0");
		salesOrderVo.setHasrefund("0");
		salesOrderVo.setEcprinted("0");
		salesOrderVo.setSendprinted("0");
		salesOrderVo.setHasscaned("0");
		salesOrderVo.setBuyTime(new Date());
		salesOrderVo.setTimestamp(DateUtil.getCurDate());
		salesOrderVo.setHasscaned("0");
		salesOrderVo.setHasscaned("0");
		salesOrderVo.setSendStatus("0");
		salesOrderVo.setDisabled("0");
		salesOrderVo.setExceptionStatus("0");
		salesOrderVo.setModified(new Date());
		orderDao.insertSelective(salesOrderVo);

		List<LocStockVo> locStockList = new ArrayList<LocStockVo>();
		for (SalesOrderitemVo orderItem : salesOrderVo.getItemList()) {
			// 查询现货表有无库存
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setStockId(salesOrderVo.getStockId());
			locStockVo.setGdsId(orderItem.getId());
			LocStockVo mlocStockVo = locStockDao.selectMastuseQtyByParam(locStockVo);

			if (mlocStockVo != null && mlocStockVo.getUseQty() > orderItem.getQty()) {
				LocStockVo locStockMod = new LocStockVo();
				locStockMod.setId(mlocStockVo.getId());
				if (mlocStockVo.getOrderQty() == null) {
					mlocStockVo.setOrderQty(0L);
				}
				locStockMod.setOrderQty(mlocStockVo.getOrderQty() + orderItem.getQty());
				locStockList.add(locStockMod);
			} else {
				salesOrderVo.setExceptionStatus("2");
			}
			orderItem.setOrderId(salesOrderVo.getId().toString());
			orderItem.setStatus("0");
			orderItem.setRefundStatus("0");
			orderitemDao.insertSelective(orderItem);
		}

		if ("0".equals(salesOrderVo.getExceptionStatus())) {
			for (LocStockVo locStockVo : locStockList) {
				// 正常订单 重新设置现货表的待发货数量
				locStockDao.updateByPrimaryKeySelective(locStockVo);
			}
		} else {
			// 异常订单改变订单状态
			orderDao.updateByPrimaryKeySelective(salesOrderVo);
		}
		return commResponse;
	}

	/**
	 * <p>
	 * Title: findExistExpcode
	 * </p>
	 * <p>
	 * Description: 查询存在物流单号记录
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#findExistExpcode(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse findExistExpcode(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo queryOrder = new SalesOrderVo();
		queryOrder.setExpcodeArr(salesOrderVo.getExpcodeArr());
		commResponse.setData(orderDao.selectByParam(queryOrder));
		return commResponse;
	}

	/**
	 * <p>
	 * Title: modSalesOrderExpcode
	 * </p>
	 * <p>
	 * Description: 修改订单物流公司
	 * </p>
	 * 
	 * @param salesOrderListVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#modSalesOrderExpcode(com.dinghao.entity.vo.template.business.order.SalesOrderListVo)
	 */
	public CommResponse modSalesOrderExpcode(SalesOrderListVo salesOrderListVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		List<SalesOrderVo> salesList = salesOrderListVo.getSalesOrderList();
		for (SalesOrderVo salesOrderVo : salesList) {
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
			if (salesOrder == null) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "不存在，请联系管理员!");
			}
			SalesOrderVo modSalesOrder = new SalesOrderVo();
			modSalesOrder.setId(salesOrder.getId());
			modSalesOrder.setExpcode(salesOrderVo.getExpcode());
			orderDao.updateByPrimaryKeySelective(modSalesOrder);
		}
		return commResponse;
	}

	/**
	 * <p>
	 * Title: modSalesOrderExpress
	 * </p>
	 * <p>
	 * Description: 修改订物流公司
	 * </p>
	 * 
	 * @param salesOrderListVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#modSalesOrderExpress(com.dinghao.entity.vo.template.business.order.SalesOrderListVo)
	 */
	public CommResponse modSalesOrderPrintTemplate(SalesOrderListVo salesOrderListVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		List<SalesOrderVo> salesList = salesOrderListVo.getSalesOrderList();
		for (SalesOrderVo salesOrderVo : salesList) {
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
			if (salesOrder == null) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "不存在，请联系管理员!");
			}
			if ("1".equals(salesOrderVo.getHassend())) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已发货，请刷新页面数据!");
			}

			SalesOrderVo modSalesOrder = new SalesOrderVo();
			modSalesOrder.setId(salesOrder.getId());
			modSalesOrder.setExpid(salesOrderVo.getExpid());
			modSalesOrder.setRemark1(salesOrderVo.getRemark1());
			modSalesOrder.setExpcode("");
			orderDao.updateByPrimaryKeySelective(modSalesOrder);
			/**
			 * 需要判断修改的物流公司是否到达收获地区
			 */
		}
		return commResponse;
	}

	/**
	 * <p>
	 * Title: deliverGoods
	 * </p>
	 * <p>
	 * Description: 订单发货
	 * </p>
	 * 
	 * @param salesOrderListVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#deliverGoods(com.dinghao.entity.vo.template.business.order.SalesOrderListVo)
	 */
	public CommResponse deliverGoods(SalesOrderListVo salesOrderListVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		List<SalesOrderVo> salesList = salesOrderListVo.getSalesOrderList();
		String noSendOrder = "";
		for (SalesOrderVo salesOrderVo : salesList) {
			/** 1.校验是否符合发货的要求 */
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
			if (salesOrder == null) {
				throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "不存在，请联系管理员!");
			}
			if ("1".equals(salesOrderVo.getHassend())) {
				//throw new DHBizException("订单" + salesOrder.getOrderNum() + "已发货，请刷新页面数据!");
				commResponse.setSuccess(false);
				commResponse.setErrMsg("订单" + salesOrder.getOrderNum() + "已发货!");
				return commResponse;
			}
			if ("1".equals(salesOrderVo.getDisabled())) {
				//throw new DHBizException("订单" + salesOrder.getOrderNum() + "已作废，请刷新页面数据!");
				commResponse.setSuccess(false);
				commResponse.setErrMsg("订单" + salesOrder.getOrderNum() + "已作废!");
				return commResponse;
			}
			if (StringUtil.isEmpty(salesOrderVo.getExpcode())) {
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "没有设置物流单号，请刷新页面数据!");
			}
			SalesOrderVo modSalesOrderVo = new SalesOrderVo();
			modSalesOrderVo.setId(salesOrder.getId());
			modSalesOrderVo.setHassend("1");
			modSalesOrderVo.setSendTime(new Date());
			orderDao.updateByPrimaryKeySelective(modSalesOrderVo);
			/** 2.修改明细相应库存 */
			List<SalesOrderitemVo> itemList = orderitemDao.selectOrderItemListByOrderId(salesOrder.getId() + "");
			for (SalesOrderitemVo orderItem : itemList) {
				if (StringUtil.isEmpty(orderItem.getGdsId() + "")) {
					logger.error("订单id:" + salesOrder.getId() + "订单明细id:" + orderItem.getId() + "商品id不存在");
					///throw new DHBizException("订单" + salesOrder.getOrderNum() + "数据异常，请联系管理员");
					
					commResponse.setSuccess(false);
					commResponse.setErrMsg("订单id:" + salesOrder.getId() + "订单明细id:" + orderItem.getId() + "商品id不存在");
					return commResponse;
					
				}
				LocStockVo locStockVo = new LocStockVo();
				locStockVo.setStockId(salesOrder.getStockId());
				locStockVo.setLocId(orderItem.getLocId());
				locStockVo.setGdsId(orderItem.getGdsId());
				List<LocStockVo> locStockList = locStockDao.selectLocStockByParam(locStockVo);
				if (null != locStockList && locStockList.size() > 1) {
					logger.error("仓库id:" + salesOrder.getStockId() + "库位id:" + orderItem.getLocId() + "商品id"
							+ orderItem.getGdsId() + "现货表出现多条数据");
					//throw new DHBizException("订单" + salesOrder.getOrderNum() + "数据异常，请联系管理员");
					commResponse.setSuccess(false);
					commResponse.setErrMsg("仓库id:" + salesOrder.getStockId() + "库位id:" + orderItem.getLocId() + "商品id"
							+ orderItem.getGdsId() + "现货表出现多条数据");
					return commResponse;
					
					
				}
				LocStockVo locStock = locStockList.get(0);
				if (locStock.getOrderQty() == null) {
					locStock.setOrderQty(0L);
				}
				if (locStock.getOrderQty() < orderItem.getQty()) {
					logger.error("订单id:" + salesOrder.getId() + "订单明细id:" + orderItem.getId() + "商品数量超过现货占用库存数量");
					throw new DHBizException("订单" + salesOrder.getOrderNum() + "数据异常，请联系管理员");
				}
				LocStockVo modLocStockVo = new LocStockVo();
				modLocStockVo.setId(locStock.getId());
				modLocStockVo.setOrderQty(locStock.getOrderQty() - orderItem.getQty());
				modLocStockVo.setTotalQty(locStock.getTotalQty() - orderItem.getQty());
				locStockDao.updateByPrimaryKeySelective(modLocStockVo);
			}
			/** 3.同步淘宝发货状态 */
			Shop shop = shopDao.selectByPrimaryKey(salesOrder.getShopId());
			if (shop == null) {
				logger.error("订单id:" + salesOrder.getId() + "店铺不存在");
				throw new DHBizException("订单" + salesOrder.getOrderNum() + "数据异常，请联系管理员");
			}
			DmsExpress expresses = expressDao.selectByPrimaryKey(salesOrder.getExpid());
			String topTids[] = salesOrder.getTopTids().split(",");
			if ("TB".equals(shop.getPlanType()))
			{
				TaobaoClient client = TBUtil.getTaobaoClient(shop.getAppkey(), shop.getAppsecret());
				for (String tid : topTids) {
					
					
					LogisticsOfflineSendRequest req = new LogisticsOfflineSendRequest();
					req.setTid(Long.parseLong(tid));
					req.setOutSid(salesOrder.getExpcode());// 运单号
					req.setCompanyCode(expresses.getCode());// 物流公司编码
					LogisticsOfflineSendResponse response = client.execute(req, shop.getSessionkey());
					if (response.getErrorCode() != null) {
						noSendOrder += "," + salesOrder.getOrderNum();
					}
				}
			}
			if ("WSC".equals(shop.getPlanType()))
			{
				for (String tid : topTids) {
					WSCUtil.sendOrder(shop.getNickname(),tid,
						new Date(),salesOrder.getExpcode(),
						salesOrder.getExpressNo(),salesOrder.getExpressName());
				}
			}
			
			

		}
		if (!StringUtil.isEmpty(noSendOrder)) {
			commResponse.setSuccess(false);
			commResponse.setErrMsg("发货成功,但订单:" + noSendOrder.substring(1, noSendOrder.length()) + "同步淘宝发货失败!");
		}
		return commResponse;
	}

	/**
	 * <p>
	 * Title: findOrderDataByParam
	 * </p>
	 * <p>
	 * Description: 根据参数查询订单信息list
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#findOrderDataByParam(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse findOrderDataByParam(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo queryOrder = new SalesOrderVo();
		if (null != salesOrderVo.getIds() && salesOrderVo.getIds().length != 0) {
			queryOrder.setIds(salesOrderVo.getIds());
		}
		if (null != salesOrderVo.getWaveNo() && !"".equals(salesOrderVo.getWaveNo())) {
			queryOrder.setWaveNo(salesOrderVo.getWaveNo());
		}
		if (null != salesOrderVo.getExpcode() && !"".equals(salesOrderVo.getExpcode())) {
			queryOrder.setExpcode(salesOrderVo.getExpcode());
		}
		commResponse.setData(orderDao.selectByParam(queryOrder));
		return commResponse;
	}

	/**
	 * <p>
	 * Title: findOrderItemListByParam
	 * </p>
	 * <p>
	 * Description: 根据参数查询订单明细list
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#findOrderItemListByParam(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse findOrderItemListByParam(SalesOrderitemVo salesOrderitemVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderitemVo queryOrderItem = new SalesOrderitemVo();
		if (null != salesOrderitemVo.getOrderIds() && salesOrderitemVo.getOrderIds().length != 0) {
			queryOrderItem.setOrderIds(salesOrderitemVo.getOrderIds());
		}
		
		if (null != salesOrderitemVo.getWaveNo() && !"".equals(salesOrderitemVo.getWaveNo())) {
			queryOrderItem.setWaveNo(salesOrderitemVo.getWaveNo());
		}
		if (null != salesOrderitemVo.getGdsId() && !"".equals(salesOrderitemVo.getGdsId())) {
			queryOrderItem.setGdsId(salesOrderitemVo.getGdsId());
		}
		commResponse.setData(orderitemDao.selectOrderItemListByParam(queryOrderItem));
		return commResponse;
	}

	/**
	 * <p>
	 * Title: saveInspectGoods
	 * </p>
	 * <p>
	 * Description: 保存扫描出库
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#saveInspectGoods(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse saveInspectGoods(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		if (salesOrder == null) {
			throw new DHBizException("订单不存在,请联系管理员!");
		}
		if ("1".equals(salesOrder.getHasscaned())) {
			throw new DHBizException("订单已扫描出库,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getDisabled())) {
			throw new DHBizException("订单已作废,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getHasrefund())) {
			throw new DHBizException("订单已退款,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getSendStatus())) {
			throw new DHBizException("订单已发货,请刷新页面数据!");
		}
		SalesOrderVo moOrderVo = new SalesOrderVo();
		moOrderVo.setId(salesOrderVo.getId());
		moOrderVo.setHasscaned("1");
		orderDao.updateByPrimaryKeySelective(moOrderVo);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: queryOrderStandWeight
	 * </p>
	 * <p>
	 * Description: 查询订单的标准重量
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#queryOrderStandWeight(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse queryOrderStandWeight(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
		salesOrderitemVo.setOrderId(salesOrderVo.getId() + "");
		List<SalesOrderitem> itemList = orderitemDao.selectOrderItemListByParam(salesOrderitemVo);
		BigDecimal standWeight = new BigDecimal("0.00");
		for (SalesOrderitem item : itemList) {
			BigDecimal qty = new BigDecimal(item.getQty());
			standWeight = standWeight.add(qty.multiply(item.getGdsStandWeight()));
		}
		commResponse.setData(standWeight);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: saveOrderWeight
	 * </p>
	 * <p>
	 * Description: 保存订单重量
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#saveOrderWeight(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse saveOrderWeight(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		if (salesOrder == null) {
			throw new DHBizException("订单不存在,请联系管理员!");
		}
		if ("1".equals(salesOrder.getHasscaned())) {
			throw new DHBizException("订单已扫描出库,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getDisabled())) {
			throw new DHBizException("订单已作废,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getHasrefund())) {
			throw new DHBizException("订单已退款,请刷新页面数据!");
		}
		if ("1".equals(salesOrder.getSendStatus())) {
			throw new DHBizException("订单已发货,请刷新页面数据!");
		}
		SalesOrderVo moOrderVo = new SalesOrderVo();
		moOrderVo.setId(salesOrderVo.getId());
		moOrderVo.setStandardWeight(salesOrderVo.getStandardWeight());
		moOrderVo.setRealWeight(salesOrderVo.getRealWeight());
		orderDao.updateByPrimaryKeySelective(moOrderVo);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: queryOrderBatchPrintData
	 * </p>
	 * <p>
	 * Description: 查询订单批量打印数据
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#queryOrderBatchPrintData(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse queryOrderBatchPrintData(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo queryOrder = new SalesOrderVo();
		queryOrder.setIds(salesOrderVo.getIds());
		List<SalesOrderVo> orderList = orderDao.selectByParam(queryOrder);
		for (SalesOrderVo order : orderList) {
			String recvArea = order.getProv() + "-" + order.getCity() + "-" + order.getCounty();
			order.setRecvArea(recvArea);
			SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
			salesOrderitemVo.setOrderId(order.getId() + "");
			order.setItemList(orderitemDao.selectOrderItemList(salesOrderitemVo));
		}
		commResponse.setData(orderList);
		return commResponse;
	}

	/**
	 * <p>
	 * Title: savePrintStatus
	 * </p>
	 * <p>
	 * Description: 保存打印状态
	 * </p>
	 * 
	 * @param salesOrderVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.template.business.order.ISalesOrderService#savePrintStatus(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse savePrintStatus(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		String[] ids = salesOrderVo.getIds();
		for (String id : ids) {
			SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(Long.parseLong(id));
			if (salesOrder == null) {
				throw new DHBizException("订单不存在,请联系管理员!");
			}
			if ("1".equals(salesOrder.getHasscaned())) {
				throw new DHBizException("订单已扫描出库,请刷新页面数据!");
			}
			if ("1".equals(salesOrder.getDisabled())) {
				throw new DHBizException("订单已作废,请刷新页面数据!");
			}
			if ("1".equals(salesOrder.getHasrefund())) {
				throw new DHBizException("订单已退款,请刷新页面数据!");
			}
			if ("1".equals(salesOrder.getSendStatus())) {
				throw new DHBizException("订单已发货,请刷新页面数据!");
			}
			SalesOrderVo moOrderVo = new SalesOrderVo();
			moOrderVo.setId(Long.parseLong(id));
			moOrderVo.setEcprinted("1");
			orderDao.updateByPrimaryKeySelective(moOrderVo);
		}
		return commResponse;
	}

	/**
	  * <p>Title: saveOrderSplit</p>
	  * <p>Description: </p>  保存订单拆分
	  * @param salesOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.order.ISalesOrderService#saveOrderSplit(com.dinghao.entity.vo.template.business.order.SalesOrderVo)
	 */
	public CommResponse saveOrderSplit(SalesOrderVo salesOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if (null == salesOrderVo.getId() || "".equals(salesOrderVo.getId())) {
			throw new DHBizException("拆分的订单信息有误");
		}
		// 需要重新计算的费用
		BigDecimal adjustfee = new BigDecimal(0); // 调整金额
		BigDecimal totalFee = new BigDecimal(0); // 商品金额
		BigDecimal payedMoney = new BigDecimal(0); // 已付金额
					
		/** 1.得到要拆分订单 的信息 */
		SalesOrderVo salesOrder = orderDao.selectByPrimaryKey(salesOrderVo.getId());
		SalesOrderVo newSalesOrder = salesOrder;
		newSalesOrder.setId(null);
		newSalesOrder.setDiscountfee(new BigDecimal("0"));
		newSalesOrder.setTotalFee(new BigDecimal("0"));
		newSalesOrder.setPayedMoney(new BigDecimal("0"));
		
		if (salesOrder == null) {
			throw new DHBizException("订单不存在，请联系管理员!");
		}
		if (!"0".equals(salesOrder.getExceptionStatus())) {
			throw new DHBizException("订单已变为异常状态，请刷新页面!");
		}

		/** 2.验证订单是否符合修改条件 */
		if ("1".equals(salesOrder.getDisabled())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已作废,不能进行拆分操作!");
		}
		if ("1".equals(salesOrder.getHasfaudit())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已审核,不能进行拆分操作!");
		}
		if ("1".equals(salesOrder.getHasrefund())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已退款,不能进行拆分操作!");
		}
		if ("1".equals(salesOrder.getSendStatus())) {
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "已退款,不能进行拆分操作!");
		}
		if(!StringUtil.isEmpty(salesOrder.getFromcodes())){
			throw new DHBizException("订单" + salesOrderVo.getOrderNum() + "是合并订单，不能拆分!");
		}
		
		/** 3.添加拆分订单 并修改原订单*/
		List<SalesOrderitemVo> newOrderItemVoList = new ArrayList<SalesOrderitemVo>();
		for (SalesOrderitemVo orderItem : salesOrderVo.getItemList()) {
			//查分订单明细相关操作
			SalesOrderitem salesOrderitem = orderitemDao.selectByPrimaryKey(orderItem.getId());
			
			SalesOrderitemVo newSalesItemVo = new SalesOrderitemVo();
			BeanUtils.copyProperties(newSalesItemVo, salesOrderitem);
			newSalesItemVo.setId(null);
			newSalesItemVo.setQty(orderItem.getQty());
			newOrderItemVoList.add(newSalesItemVo);
			BigDecimal price = new BigDecimal("0");
			if(newSalesItemVo.getSalsePrice() != null){
				price = newSalesItemVo.getSalsePrice().multiply(new BigDecimal(newSalesItemVo.getQty()));
			}
			newSalesOrder.setTotalFee(newSalesOrder.getTotalFee().add(price));
			newSalesOrder.setPayedMoney(newSalesOrder.getPayedMoney().add(price));
			
			//原订单明细相关操作
			if(salesOrderitem.getQty() - orderItem.getQty() != 0){
				salesOrderitem.setQty(salesOrderitem.getQty() - orderItem.getQty());
				SalesOrderitemVo modSalesItemVo = new SalesOrderitemVo();
				BeanUtils.copyProperties(modSalesItemVo, salesOrderitem);
				orderitemDao.updateByPrimaryKeySelective(modSalesItemVo);
			}else{
				orderitemDao.deleteByOrderId(orderItem.getId()+"");
			}
		}
		orderDao.insertSelective(newSalesOrder);
		for(SalesOrderitemVo salesOrderitemVo : newOrderItemVoList){
			salesOrderitemVo.setOrderId(newSalesOrder.getId()+"");
			orderitemDao.insertSelective(salesOrderitemVo);
		}

		// 修改原订单主表
		salesOrder.setTotalFee(salesOrder.getTotalFee().subtract(newSalesOrder.getTotalFee()));
		salesOrder.setPayedMoney(salesOrder.getPayedMoney().subtract(newSalesOrder.getPayedMoney()));
		orderDao.updateByPrimaryKeySelective(salesOrder);

		/** 4.添加订单日志 */
		SalesOrderactionVo salesOrderactionVo = new SalesOrderactionVo();
		salesOrderactionVo.setOrderId(salesOrderVo.getId());
		salesOrderactionVo.setActionType("拆分");
		salesOrderactionVo.setActionMemo("订单:" + salesOrderVo.getOrderNum() + "拆分成功");
		salesOrderactionVo.setActionTime(new Date());
		orderActionDao.insertSelective(salesOrderactionVo);

		commResponse.setErrMsg("订单:" + salesOrder.getOrderNum() + "拆分通过!");
		return commResponse;
	}
}
