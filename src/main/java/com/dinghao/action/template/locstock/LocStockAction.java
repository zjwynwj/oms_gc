package com.dinghao.action.template.locstock;

import java.util.ArrayList;
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
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.service.template.warehouse.WarehousesService;

@Controller
@RequestMapping("/template/locstock")
public class LocStockAction extends BaseAction {
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	LocStockService locStockService;

	/**
	 * 
	 * @Title: login
	 * @Description: TODO(现货表主页)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param requestUrlString
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap,
			String requestUrlString) {
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		warehouseVo.setIsDelete(false);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		return themeService.getTemplatePath("ftls/locstock/index");
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: detailWmstake
	 * @Description: TODO(现货表首页)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param locStockVo
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/detail_locstock.jhtml")
	public void detailWmstake(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, LocStockVo locStockVo) throws Exception {
		/*
		JqGridVo<LocStockVo> jqGridVo = locStockService
				.selectByStatement(locStockVo);
		return jqGridVo.getJSONObject();
		*/
		this.returnData(request, response, locStockService.selectByStatement(locStockVo));
	}
	
	@RequestMapping(value = "/queryLowerStockList.jhtml")
	public void queryLowerStockList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, LocStockVo locStockVo) throws Exception {
	
		
		this.returnData(request, response, locStockService.queryLowerStockList(locStockVo));
	}
	
	@RequestMapping(value = "/detail_locstock_items.jhtml", method = RequestMethod.GET)
	public String detailWmstakeItems(HttpServletRequest request, ModelMap modelMap,
			LocStockVo locStockVo) {
		modelMap.put("locStockVo", locStockVo);
		return themeService.getTemplatePath("ftls/locstock/detail_locstock_items");
	}
	/**
	 * 
	* @Title: detail_Wmstake_items 
	* @Description: TODO(查看详情页) 
	* @param @param request
	* @param @param modelMap
	* @param @param locStockVo
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/detail_locstock_items_js.jhtml")
	public void detailWmstakeItemsJs(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, LocStockVo locStockVo) throws Exception {

		 this.returnData(request, response, locStockService.selectByStatementDetail(locStockVo));
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail_locstock_items_select2.jhtml")
	public List<LocStockVo> detailWmstakeItemsSelete2(HttpServletRequest request,
			ModelMap modelMap, LocStockVo locStockVo) {
		locStockVo.setRows(15);
		if(locStockVo.getStockId()==null){
			return new ArrayList<LocStockVo>();
		}
		JqGridVo<LocStockVo> jqGridVo = locStockService
				.selectByStatementDetailSelect2(locStockVo);
		return jqGridVo.getList();
	}
	
	@RequestMapping(value = "/synGoodsQty.jhtml")
	public void synGoodsQty(HttpServletRequest request,HttpServletResponse response,
			ModelMap modelMap, LocStockVo locStockVo) throws Exception {
		
		 this.returnData(request, response, locStockService.synGoodsQty(locStockVo));
	}
}
