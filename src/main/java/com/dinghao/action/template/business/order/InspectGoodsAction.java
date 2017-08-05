package com.dinghao.action.template.business.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.service.template.business.order.ISalesOrderService;

@Controller
@RequestMapping("/template")
public class InspectGoodsAction extends BaseAction{
	
	@Autowired
	private ISalesOrderService salesOrderService;
	
	//跳转到验货出库页面
	@RequestMapping(value = {"/orderMgr/turnInspectGoods.jhtml"})
	public String turnOrderAudit() throws Exception{
		return "/template/front/ftls/order/inspectGoods";
	}
	
	//跳转到批量称重
	@RequestMapping(value = {"/orderMgr/turnBatchWeight.jhtml"})
	public String turnBatchWeight() throws Exception{
		return "/template/front/ftls/order/batchWeight";
	}
	
	//保存扫描出货
	@RequestMapping(value = {"/orderMgr/saveInspectGoods.jhtml"})
	public void saveInspectGoods(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.saveInspectGoods(salesOrderVo));
	}
	
	//查询订单的标准重量
	@RequestMapping(value = {"/orderMgr/queryOrderStandWeight.jhtml"})
	public void queryOrderStandWeight(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.queryOrderStandWeight(salesOrderVo));
	}
	
	//保存称重结果
	@RequestMapping(value = {"/orderMgr/saveOrderWeight.jhtml"})
	public void saveOrderWeight(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.saveOrderWeight(salesOrderVo));
	}
}
