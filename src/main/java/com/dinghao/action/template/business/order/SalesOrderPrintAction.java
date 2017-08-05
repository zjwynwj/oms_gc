package com.dinghao.action.template.business.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderListVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.service.template.business.order.ISalesOrderService;

@Controller
@RequestMapping("/template")
public class SalesOrderPrintAction extends BaseAction{
	@Autowired
	DmsExpressService dmsExpressService;
	@Autowired
	private ISalesOrderService salesOrderService;
	@Autowired
	private ShopService shopService;
	//跳转到订单打印页面
	@RequestMapping(value = {"/orderMgr/turnOrderPrint.jhtml"})
	public String turnOrderPrint(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) throws Exception{
		
		DmsExpressVo expressVo  = new DmsExpressVo();
		expressVo.setOffset(0);
		expressVo.setRows(Integer.MAX_VALUE);
		expressVo.setActived("1");
		List<DmsExpress> expresslist =dmsExpressService.getAllDmsExpress(expressVo);
		modelMap.put("expresslist", expresslist);
		
		//获取店铺信息
		ShopVo shopVo  = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		shopVo.setBeactive(true);
		List<Shop> shoplist =shopService.getShops(shopVo);
		modelMap.put("shoplist", shoplist);
		return "/template/front/ftls/order/orderPrint";
	}

	//跳转到修改物流公司页面
	@RequestMapping(value = {"/orderMgr/turnModPrintTemplate.jhtml"})
	public String turnModPrintTemplate() throws Exception{
		return "/template/front/ftls/order/modPrintTemplate";
	}
	
	//查询存在物流单号记录
	@RequestMapping(value = {"/orderMgr/findExistExpcode.jhtml"})
	public void findExistExpcode(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.findExistExpcode(salesOrderVo));
	}
	
	//修改订单的物流编号
	@RequestMapping(value = {"/orderMgr/modSalesOrderExpcode.jhtml"})
	public void modSalesOrderExpcode(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderListVo salesOrderListVo) throws Exception{
        this.returnData(request, response, salesOrderService.modSalesOrderExpcode(salesOrderListVo));
	}
	
	//修改订单的物流公司
	@RequestMapping(value = {"/orderMgr/modSalesOrderPrintTemplate.jhtml"})
	public void modSalesOrderPrintTemplate(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderListVo salesOrderListVo) throws Exception{
        this.returnData(request, response, salesOrderService.modSalesOrderPrintTemplate(salesOrderListVo));
	}
	
	//订单发货
	@RequestMapping(value = {"/orderMgr/deliverGoods.jhtml"})
	public void deliverGoods(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderListVo salesOrderListVo) throws Exception{
        this.returnData(request, response, salesOrderService.deliverGoods(salesOrderListVo));
	}
	
	//获得订单批量打印物流的数据
	@RequestMapping(value = {"/orderMgr/queryOrderBatchPrintData.jhtml"})
	public void queryOrderBatchPrintData(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.queryOrderBatchPrintData(salesOrderVo));
	}
	
	//修改订单的物流公司
	@RequestMapping(value = {"/orderMgr/savePrintStatus.jhtml"})
	public void savePrintStatus(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.savePrintStatus(salesOrderVo));
	}

}
