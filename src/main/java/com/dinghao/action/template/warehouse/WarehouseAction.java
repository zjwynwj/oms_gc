package com.dinghao.action.template.warehouse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.template.warehousepositions.WarehousePositions;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.entity.vo.template.warehousepositions.WarehousePositionsVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeVo;
import com.dinghao.service.template.warehouse.WarehousesService;
import com.dinghao.service.template.wmstake.WmsTakeService;
import com.dinghao.util.StringUtil;

@Controller
@RequestMapping("/template/warehouse")
public class WarehouseAction extends BaseAction {

	@Autowired
	WarehousesService warehousesService;
	@Autowired
	WmsTakeService wmsTakeService;

	/**
	 * 进入仓库的主页面
	 * 
	 * @param request
	 * @param modelMap
	 * @param requestUrlString
	 * @return
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap,
			String requestUrlString) {
		return themeService.getTemplatePath("ftls/warehouse/index");
	}

	@ResponseBody
	@RequestMapping(value = "/getWarehouses.jhtml", method = RequestMethod.POST)
	public JSONObject getWarehouses(WarehouseVo warehouseVo,
			HttpServletRequest request, Long[] menus) {
		JqGridVo<Warehouse> jqGridVo = warehousesService
				.getWarehouse(warehouseVo);
		return jqGridVo.getJSONObject();

	}

	/**
	 * 跳转到仓库新增/修改页面
	 * 
	 * @param request
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateWarehouse.jhtml", method = RequestMethod.GET)
	public String addOrUpdateWarehouse(HttpServletRequest request,
			ModelMap modelMap, Integer id) {
		// 仓库的主键
		Warehouse warehouse = new Warehouse();
		if (id != null) {
			warehouse = warehousesService.selectByPrimaryKey(id);
		}
		modelMap.put("warehouse", warehouse);
		return themeService.getTemplatePath("ftls/warehouse/addWarehouse");
	}

	@ResponseBody
	@RequestMapping(value = "/saveAddOrUpdateWarehouse.jhtml", method = RequestMethod.POST)
	public CommResponse saveAddOrUpdateWarehouse(HttpServletRequest request,
			ModelMap modelMap, WarehouseVo warehouseVo) {
		CommResponse json = new CommResponse();
		int flage = 0;
		// 监测该仓库是否已在盘点中
		WmsTakeVo record = new WmsTakeVo();
		record.setStockId(warehouseVo.getId());
		record.setStatus(true);
		int haveRecord = wmsTakeService.selectByStatementCount(record);
		if (haveRecord > 0) {
			json.setSuccess(false);
			json.setErrMsg("该仓库正在盘点中,不能操作!");
	
			return json;
		}		
		// 修改状态
		if (warehouseVo.getId() != null && warehouseVo.getId() > 0) {
			warehouseVo.setModifyBy(super.getAdmin(request).getId());
			warehouseVo.setModifyDate(new Date());
			flage = warehousesService.updateWarehouse(warehouseVo);
		} else {// 新增
			warehouseVo.setCreateBy(super.getAdmin(request).getId());
			warehouseVo.setCreateDate(new Date());
			flage = warehousesService.saveWarehouse(warehouseVo);
		}
		if (flage > 0) {
			json.setSuccess(true);
			json.setErrMsg("操作成功!");

		} else {
			json.setSuccess(false);
			json.setErrMsg("操作失败!");

		}
		return json;
	}

	/**
	 * 仓位信息加载
	 * 
	 * @param request
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/warehouse_positions_index.jhtml", method = RequestMethod.GET)
	public String warehousePositionsIndex(HttpServletRequest request,
			ModelMap modelMap, Integer id) {
		if (id == null) {
			return themeService.get404();
		}
		Warehouse warehouse = warehousesService.selectByPrimaryKey(id);
		modelMap.put("warehouse", warehouse);
		return themeService
				.getTemplatePath("ftls/warehouse/warehouse_positions");
	}

	/**
	 * 仓位管理列表页数据加载
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_warehouse_positions.jhtml", method = RequestMethod.POST)
	public JSONObject getWarehousePositions(WarehousePositionsVo vo,
			HttpServletRequest request) {
		JqGridVo<WarehousePositions> jqGridVo = warehousesService
				.getWarehousePositions(vo);
		return jqGridVo.getJSONObject();

	}

	/**
	 * 仓位信息修改或新增
	 * 
	 * @param request
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/save_update_warehouse_positions.jhtml", method = RequestMethod.GET)
	public String saveOrUpdateWarehousePositions(HttpServletRequest request,
			ModelMap modelMap, Integer id, Integer dhWarehousePositions) {
		// 仓库的主键
		WarehousePositions warehousePositions = new WarehousePositions();
		if (id != null) {
			warehousePositions = warehousesService
					.selectWarehousePositionsByPrimaryKey(id);
			if (dhWarehousePositions == null) {
				dhWarehousePositions = warehousePositions
						.getDhWarehousePositions();
			}
		}
		modelMap.put("warehousePositions", warehousePositions);
		modelMap.put("dhWarehousePositions", dhWarehousePositions);
		return themeService
				.getTemplatePath("ftls/warehouse/save_update_warehouse_positions");
	}

	@ResponseBody
	@RequestMapping(value = "/add_update_warehouse_positions.jhtml", method = RequestMethod.POST)
	public CommResponse addUpdateWarehousePositions(
			HttpServletRequest request, ModelMap modelMap,
			WarehousePositionsVo warehousePositionsVo) {
		CommResponse json = new CommResponse();
		int flage = 0;
		// 修改状态
		if (warehousePositionsVo.getId() != null
				&& warehousePositionsVo.getId() > 0) {
			warehousePositionsVo.setModifyBy(super.getAdmin(request).getId()
					.toString());
			warehousePositionsVo.setModifyDate(new Date());
			flage = warehousesService
					.updateWarehousePositions(warehousePositionsVo);
		} else {// 新增
			warehousePositionsVo.setCreateBy(super.getAdmin(request).getId()
					.toString());
			warehousePositionsVo.setCreateDate(new Date());
			warehousePositionsVo.setIsDelete(0);
			flage = warehousesService
					.saveWarehousePositions(warehousePositionsVo);
		}
		if (flage > 0) {
			json.setSuccess(true);
			json.setErrMsg("操作成功!");
		} else {
			json.setSuccess(false);
			json.setErrMsg("操作失败!");
		}
		return json;
	}
    /*
     * 获取指定仓库的默认库位
     */
	@RequestMapping(value = "/queryDefaultPosition.jhtml")
	public void queryDefaultPosition(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WarehouseVo warehouseVo) throws Exception{
		this.returnData(request, response, warehousesService.getDefalutWarehousePosinton(warehouseVo));

	}
	/**
	 * 删除仓位信息
	 * 
	 * @param request
	 * @param modelMap
	 * @param warehousePositionsVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete_warehouse_positions.jhtml", method = RequestMethod.POST)
	public JsonVo<String> deleteWarehousePositions(HttpServletRequest request,
			ModelMap modelMap, Integer id) {
		JsonVo<String> json = new JsonVo<String>();
		if (id == null) {
			json.setResult(false);
			json.setMsg("操作失败!");
			return json;
		}

		int flage = 0;
		// 删除标记个数
		flage = warehousesService.deleteWarehousePositions(id);
		if (flage > 0) {
			json.setResult(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/get_warehouses_select2.jhtml", method = RequestMethod.GET)
	public List<Warehouse> getWarehousesSelect2(HttpServletRequest request,
			ModelMap modelMap, WarehouseVo warehouseVo) {
		try {
			if(StringUtil.getEncoding(warehouseVo.getWarehouseName()) == "ISO-8859-1"){
				warehouseVo.setWarehouseName((java.net.URLDecoder.decode(
						new String(warehouseVo.getWarehouseName().getBytes(
								"ISO-8859-1"), "UTF-8"), "UTF-8")));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		return warehouses;
	}

	/**
	 * 
	 * @Title: getWarehousesSelect2
	 * @Description: TODO(获取库位编码 智能提示功能)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param warehouseVo
	 * @param @return 设定文件
	 * @return List<Warehouse> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/get_warehouses_detail_select2.jhtml", method = RequestMethod.GET)
	public List<WarehousePositions> getWarehousesDetailSelect2(
			HttpServletRequest request, ModelMap modelMap,
			WarehousePositionsVo vo) {
		List<WarehousePositions> warehousesPositions = new ArrayList<WarehousePositions>();
		// 没有仓库 就不显示库位信息
		if (vo.getDhWarehousePositions() < 0) {
			return warehousesPositions;
		}
		try {
			vo.setWarehousePositionsCode((java.net.URLDecoder.decode(
					new String(vo.getWarehousePositionsCode().getBytes(
							"ISO-8859-1"), "UTF-8"), "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		warehousesPositions = warehousesService.getWarehousePositions(vo)
				.getList();
		return warehousesPositions;
	}
}
