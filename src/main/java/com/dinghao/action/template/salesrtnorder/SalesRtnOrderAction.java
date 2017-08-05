package com.dinghao.action.template.salesrtnorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dinghao.action.template.BaseAction;
import com.dinghao.constant.PlatTypeEnum;
import com.dinghao.dao.template.salesrtnorder.SalesRtnOrderItemDao;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.template.salesrtnorder.SalesRtnOrder;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.service.template.business.order.ISalesOrderService;
import com.dinghao.service.template.salesrtnorder.SalesRtnOrderService;
import com.dinghao.service.template.warehouse.WarehousesService;

@Controller
@RequestMapping("/template/salesrtnorder")
public class SalesRtnOrderAction extends BaseAction {
	@Autowired
	SalesRtnOrderService salesRtnOrderService;
	@Autowired
	ISalesOrderService salesOrderService;
	@Autowired
	ShopService shopService;
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	SalesRtnOrderItemDao salesRtnOrderItemDao;
	@Autowired
	private DmsExpressService dmsExpressService;
	
	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap,
			String requestUrlString) {
		// 获取所有商铺信息
		ShopVo shopVo = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		modelMap.put("shops", shopService.getShops(shopVo));
		// 获取平台信息
		modelMap.put("plattypes", PlatTypeEnum.getValues());
		return themeService.getTemplatePath("ftls/salesrtnorder/index");
	}

	/**
	 * 
	 * @Title: getSalesRtnOrders
	 * @Description: TODO(首页JS加载数据)
	 * @param @param request
	 * @param @param salesRtnOrderVo
	 * @param @param modelMap
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getSalesRtnOrders.jhtml", method = RequestMethod.POST)
	public JSONObject getSalesRtnOrders(HttpServletRequest request,
			SalesRtnOrderVo salesRtnOrderVo, ModelMap modelMap) {
		JqGridVo<SalesRtnOrder> jqGridVo = salesRtnOrderService
				.selectByStatement(salesRtnOrderVo);
		return jqGridVo.getJSONObject();
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: addSalesRtnOrderItem
	 * @Description: TODO(添加首页)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param salesRtnOrderItemVo
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add_sales_rtn_order_item.jhtml", method = RequestMethod.GET)
	public String addSalesRtnOrderItem(HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		// 获取来源订单号
		if (null!=request.getParameter("orderId") && !request.getParameter("orderId").isEmpty())
		{
			Long orderId = Long.parseLong(request.getParameter("orderId"));
			SalesOrderVo salesOrderVo = new SalesOrderVo();
			salesOrderVo.setId(orderId);
			salesOrderVo = salesOrderService.querySalesOrder(salesOrderVo);
			modelMap.put("salesOrderVo", salesOrderVo);
		}
		//获取物流公司
		DmsExpressVo expressVo  = new DmsExpressVo();
		expressVo.setOffset(0);
		expressVo.setRows(Integer.MAX_VALUE);
		expressVo.setActived("1");
		List<DmsExpress> expresslist =dmsExpressService.getAllDmsExpress(expressVo);
		modelMap.put("expresslist", expresslist);
				
		// 获取所有商铺信息
		ShopVo shopVo = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		modelMap.put("shops", shopService.getShops(shopVo));
		// 获取平台信息
		modelMap.put("plattypes", PlatTypeEnum.getValues());
		// 仓库
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		warehouseVo.setIsDelete(false);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		return themeService
				.getTemplatePath("ftls/salesrtnorder/add_sales_rtn_order_item");
	}

	@RequestMapping(value = "/saveSalesRtnOrderItem.jhtml", method = RequestMethod.POST)
	public void saveSalesRtnOrderItem(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap
			, SalesRtnOrderVo salesRtnOrderVo) throws Exception {

		if (salesRtnOrderVo.getRtnId() != null) {

			
		 	this.returnData(request, response, salesRtnOrderService	.updateByPrimaryKeySelective(salesRtnOrderVo));	
		} else {
			// 新增操作

		 	this.returnData(request, response, salesRtnOrderService.insertSelective(salesRtnOrderVo));	

			
		}

	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: saveSalesRtnOrder
	 * @Description: TODO(执行收货操作)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param salesRtnOrderVo
	 * @param @return 设定文件
	 * @return JsonVo<String> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/save_received_sales_rtn_order.jhtml", method = RequestMethod.POST)
	public void saveReceivedSalesRtnOrder(HttpServletRequest request,HttpServletResponse response ,
			ModelMap modelMap, SalesRtnOrderVo salesRtnOrderVo) throws Exception {
		
			this.returnData(request, response, salesRtnOrderService.saveReceivedSalesRtnOrder(salesRtnOrderVo));

	}

	/**
	 * @throws Exception 
	 * 
	* @Title: updateSalesRtnOrderItem 
	* @Description: TODO(修改详细页数据) 
	* @param @param request
	* @param @param modelMap
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/update_sales_rtn_order_item.jhtml", method = RequestMethod.GET)
	public String updateSalesRtnOrderItem(HttpServletRequest request,
			ModelMap modelMap, Long id) throws Exception {
		// 获取所有商铺信息
		ShopVo shopVo = new ShopVo();
		shopVo.setOffset(0);
		shopVo.setRows(Integer.MAX_VALUE);
		modelMap.put("shops", shopService.getShops(shopVo));
		// 获取平台信息
		modelMap.put("plattypes", PlatTypeEnum.getValues());
		
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
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		SalesRtnOrder salesRtnOrder = salesRtnOrderService
				.selectByPrimaryKey(id);
		modelMap.put("salesRtnOrder", salesRtnOrder);
		return themeService
				.getTemplatePath("ftls/salesrtnorder/update_sales_rtn_order_item");
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: getSalesRtnOrderItems
	 * @Description: TODO(修改,详细页列表数据加载)
	 * @param @param request
	 * @param @param salesRtnOrderVo
	 * @param @param modelMap
	 * @param @param salesRtnOrderItemVo
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/getSalesRtnOrderItems.jhtml", method = RequestMethod.POST)
	public void getSalesRtnOrderItems(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap ,	SalesRtnOrderVo salesRtnOrderVo) throws Exception {
	
		this.returnData(request, response, salesRtnOrderService.selectSalesRtnOrderItemsByStatement(salesRtnOrderVo));
	}

}
