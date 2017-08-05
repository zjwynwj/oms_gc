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
import com.dinghao.entity.template.business.order.SalesOrder;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.service.template.business.order.IExceptionOrderService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.service.template.warehouse.WarehousesService;

@Controller
@RequestMapping("/template")
public class ExceptionOrderAction extends BaseAction{
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	private IExceptionOrderService exceptionSalesOrderService;
	@Autowired
	private ISalesOrderService salesOrderService;
	@Autowired
	private DmsExpressService dmsExpressService;
	@Autowired
	private ShopService shopService;
	//跳转到异常订单页面
	@RequestMapping(value = {"/orderMgr/turnExceptionOrdert.jhtml"})
	public String turnOrderAudit(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) throws Exception{
		
		//获取店铺信息
				ShopVo shopVo  = new ShopVo();
				shopVo.setOffset(0);
				shopVo.setRows(Integer.MAX_VALUE);
				shopVo.setBeactive(true);
				List<Shop> shoplist =shopService.getShops(shopVo);
				modelMap.put("shoplist", shoplist);
				
		return "/template/front/ftls/order/exceptionOrder";
	}
	
	//跳转到修改订单页面
	@RequestMapping(value = {"/orderMgr/turnModExceptionOrder.jhtml"})
	public String turnModExceptionOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
		
		//获取物流公司
		DmsExpressVo expressVo  = new DmsExpressVo();
		expressVo.setOffset(0);
		expressVo.setRows(Integer.MAX_VALUE);
		expressVo.setActived("1");
		List<DmsExpress> expresslist =dmsExpressService.getAllDmsExpress(expressVo);
		modelMap.put("expresslist", expresslist);
		//获取仓库
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		warehouseVo.setIsDelete(false);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		
		SalesOrderVo salesOrder = salesOrderService.querySalesOrder(salesOrderVo);
		modelMap.put("salesOrder", salesOrder);
		return "/template/front/ftls/order/modExceptionOrder";
	}
	
	//分页查询异常订单数据
	@RequestMapping(value = {"/orderMgr/findExceptionSalesOrderForGrid.jhtml"})
	public void findExceptionSalesOrderForGrid(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, exceptionSalesOrderService.findExceptionSalesOrderForGrid(salesOrderVo));
	}
	
	//修改异常订单信息
	@RequestMapping(value = {"/orderMgr/saveModExceptionOrder.jhtml"})
	public void saveModExceptionOrder(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, SalesOrderVo salesOrderVo) throws Exception{
        this.returnData(request, response, exceptionSalesOrderService.modExceptionOrder(salesOrderVo));
	}
}
