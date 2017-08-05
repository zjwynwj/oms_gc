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
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.service.template.business.order.IDownSalesOrderService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.service.template.warehouse.WarehousesService;

@Controller
@RequestMapping("/template")
public class SalesOrderAuditAction extends BaseAction{
	@Autowired
	private ISalesOrderService salesOrderService;
	@Autowired
	private IDownSalesOrderService DownSalesOrderService;
	@Autowired
	private DmsExpressService dmsExpressService;
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	private ShopService shopService;
	//跳转到订单审核页面
	@RequestMapping(value = {"/orderMgr/turnOrderAudit.jhtml"})
	public String turnOrderAudit(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) throws Exception{
		//获取店铺信息
		ShopVo shopVo  = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		shopVo.setBeactive(true);
		List<Shop> shoplist =shopService.getShops(shopVo);
		modelMap.put("shoplist", shoplist);
		return "/template/front/ftls/order/orderAudit";
	}
	
	//跳转到添加订单页面
	@RequestMapping(value = {"/orderMgr/turnAddSalesOrder.jhtml"})
	public String turnAddSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) 
			throws Exception{
		//获取物流公司
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
		return "/template/front/ftls/order/addSalesOrder";
	}
	
	//跳转到订单下载页面
	@RequestMapping(value = {"/orderMgr/turnOrderDown.jhtml"})
	public String turnOrderDown(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) throws Exception{
		//获取店铺信息
		ShopVo shopVo  = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		shopVo.setBeactive(true);
		List<Shop> shoplist =shopService.getShops(shopVo);
		modelMap.put("shoplist", shoplist);
		return "/template/front/ftls/order/orderDown";
	}
	
	//跳转到修改订单页面
	@RequestMapping(value = {"/orderMgr/turnModSalesOrder.jhtml"})
	public String turnModSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
		SalesOrderVo msalesOrderVo = salesOrderService.querySalesOrder(salesOrderVo);
		
		modelMap.put("salesOrder", msalesOrderVo);
	//获取物流公司
		DmsExpressVo expressVo  = new DmsExpressVo();
		expressVo.setOffset(0);
		expressVo.setRows(Integer.MAX_VALUE);
		expressVo.setActived("1");
		List<DmsExpress> expresslist = dmsExpressService.getAllDmsExpress(expressVo);
		modelMap.put("expresslist", expresslist);
	
		// 仓库
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		warehouseVo.setIsDelete(false);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
				
		return "/template/front/ftls/order/modSalesOrder";
	}
	
	//跳转到订单拆分
	@RequestMapping(value = {"/orderMgr/turnOrderSplit.jhtml"})
	public String turnOrderSplit(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
		SalesOrderVo msalesOrderVo = salesOrderService.querySalesOrder(salesOrderVo);
		modelMap.put("salesOrder", msalesOrderVo);
		return "/template/front/ftls/order/orderSplit";
	}
	
	//保存订单拆分
	@RequestMapping(value = {"/orderMgr/saveOrderSplit.jhtml"})
	public void saveOrderSplit(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.saveOrderSplit(salesOrderVo));
	}
	
	//查询订单明细数据
	@RequestMapping(value = {"/orderMgr/querySalesOrderItemList.jhtml"})
	public void querySalesOrderItemList(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.querySalesOrderItemList(salesOrderVo));
	}
	
	//下载订单
	@RequestMapping(value = {"/orderMgr/downLoadSalesOrder.jhtml"})
	public void downLoadGoods(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, DownSalesOrderService.downLoadSalesOrder(salesOrderVo));
	}
	
	//分页查询订单数据
	@RequestMapping(value = {"/orderMgr/querySalesOrderGrid.jhtml"})
	public void querySalesOrderGrid(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.findSalesOrderForGrid(salesOrderVo));
	}
	
	//合并订单
	@RequestMapping(value = {"/orderMgr/combineSalesOrder.jhtml"})
	public void combineSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.combineSalesOrder(salesOrderVo));
	}
	
	//合并订单
	@RequestMapping(value = {"/orderMgr/cancelComSalesOrder.jhtml"})
	public void cancelComSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.cancelComSalesOrder(salesOrderVo));
	}
	
	//审核订单
	@RequestMapping(value = {"/orderMgr/auditSalesOrder.jhtml"})
	public void auditSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.auditSalesOrder(salesOrderVo));
	}
	
	//取消审核订单
	@RequestMapping(value = {"/orderMgr/cancelAuditSalesOrder.jhtml"})
	public void cancelAuditSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.cancelAuditSalesOrder(salesOrderVo));
	}
		
	//修改订单信息
	@RequestMapping(value = {"/orderMgr/saveModSalesOrder.jhtml"})
	public void saveModSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.modSalesOrder(salesOrderVo));
	}
	
	//添加订单信息
	@RequestMapping(value = {"/orderMgr/saveAddSalesOrder.jhtml"})
	public void saveAddSalesOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.addSalesOrder(salesOrderVo));
	}
	
	//根据参数查询订单信息list
	@RequestMapping(value = {"/orderMgr/findOrderDataByParam.jhtml"})
	public void findOrderDataByParam(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, salesOrderService.findOrderDataByParam(salesOrderVo));
	}
	
	//根据参数查询订单明细信息list
	@RequestMapping(value = {"/orderMgr/findOrderItemListByParam.jhtml"})
	public void findOrderItemListByParam(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderitemVo salesOrderitemVo) throws Exception{
        this.returnData(request, response, salesOrderService.findOrderItemListByParam(salesOrderitemVo));
	}
}
