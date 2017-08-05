package com.dinghao.action.template.wmstake;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.dinghao.action.template.BaseAction;
import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.template.wmstake.WmsTake;
import com.dinghao.entity.template.wmstake.WmsTakeItem;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeVo;
import com.dinghao.service.template.excel.ExcelManageService;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.service.template.warehouse.WarehousesService;
import com.dinghao.service.template.wmstake.WmsTakeItemService;
import com.dinghao.service.template.wmstake.WmsTakeService;
import com.dinghao.util.DateUtil;

@Controller
@RequestMapping("/template/wmstake")
public class WmsTakeAction extends BaseAction {
	@Autowired
	WmsTakeService wmsTakeService;
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	WmsTakeItemService wmsTakeItemService;
	@Autowired
	ExcelManageService excelManageService;
	@Autowired
	GdsInfoDao gdsInfoDao;
	@Autowired
	LocStockService locStockService;
	
	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap) {
		
		// 盘点单主页
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		return themeService.getTemplatePath("ftls/wmstake/index");

	}
    /**
     * 盘点扫描条码
     * @throws Exception 
     */
	@ResponseBody
	@RequestMapping(value = "/spscanWmsTake.jhtml",method = RequestMethod.GET)
	public JsonVo<String> spscanWmsTake(HttpServletRequest request, ModelMap modelMap,
			WmsTakeVo wmsTakeVo) throws Exception {
		
		JsonVo<String> json = new JsonVo<String>();
		String gdsPact = request.getParameter("gdsPact");
        //查找code是否在商品库存在
		GdsInfoVo gdsInfoVo = new GdsInfoVo();
		gdsInfoVo.setGdsPact(gdsPact);
		
		WmsTakeItemVo wmsTakeItemVo = new WmsTakeItemVo();
		wmsTakeItemVo.setGdsPact(gdsPact);
		wmsTakeItemVo.setTakeId(wmsTakeVo.getId().longValue());
		
		int count = gdsInfoDao.selectGdsInfoGridListCount(gdsInfoVo);
		if (count ==0 ) 
		{
			json.setResult(true);
			json.setSuccess(false);
			json.setT("{gdsPact:'"+gdsPact+"',spexists:'false'}");
			json.setMsg("不存在该商品！");
			return json;
		}
		
		//查找code是否在盘点单存在
		WmsTakeItemVo curTakeItemVo = new WmsTakeItemVo(); //扫描后定位的盘点明细
		WmsTakeItem curTakeItem = new WmsTakeItem();
		
		count = wmsTakeItemService.selectByStatementCount(wmsTakeItemVo);
		if (count == 0 ) 
		{
			json.setResult(true);
			json.setSuccess(false);
			json.setT("{gdsPact:'"+gdsPact+"',itemexists:'false'}");
			json.setMsg("商品不在盘点单！");
			return json;
		}
		else
		{ //存在获取盘点库位
		  JqGridVo<WmsTakeItem> mlistscan =  wmsTakeItemService.selectByStatement(wmsTakeItemVo);
		 
		  //定位哪个盘点明细需要增加扫描数量
		  List<WmsTakeItem> curlist = mlistscan.getList();
		
		  for(WmsTakeItem tmp:curlist)  
	        {  
		       int scanQty =  tmp.getScanQty();
		       int sysQty =  tmp.getSysQty();
		       int countQty =  tmp.getCountQty();
		       curTakeItemVo.setId(tmp.getId());
		       tmp.setScanQty(tmp.getScanQty() + 1);
		       curTakeItemVo.setScanQty(tmp.getScanQty());
		       
		       if (countQty + scanQty + 1 <= sysQty)
		       {
		    	  break;
		       }
		      
			}
		     
		}
		 wmsTakeItemService.updateByPrimaryKeySelective(curTakeItemVo);
		
		//获取当前盘点明细的locid
		
		curTakeItem = wmsTakeItemService.selectByPrimaryKey(curTakeItemVo.getId());
		
		json.setSuccess(true);
	    json.setT("{gdsPact:'"+gdsPact+"',locId:'"+curTakeItem.getLocId().toString()+"',"
	    		+ "gdsNo:'"+curTakeItem.getGdsNo()+"',"
	    		+ "Countqty:'"+curTakeItem.getCountQty()+"',gdsId:'"+curTakeItem.getGdsId()+"'  }");
		json.setResult(true);
		return json;
	}
	
	/**
	 * 
	 * @Title: detailWmstake
	 * @Description: TODO(盘点详细页面)
	 * @param @param request
	 * @param @param modelMap
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "detail_wmstake.jhtml", method = RequestMethod.GET)
	public String detailWmstake(HttpServletRequest request, ModelMap modelMap,Long id) {
		// 盘点单主页
		modelMap.put("takeId",id);
		return themeService.getTemplatePath("ftls/wmstake/detail_wmstake");

	}
	/**
	 * 
	* @Title: addWmstakeButton 
	* @Description: TODO(盘点) 
	* @param @param request
	* @param @param modelMap
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "add_wmstake_button.jhtml", method = RequestMethod.GET)
	public String addWmstakeButton(HttpServletRequest request, ModelMap modelMap,Integer id) {
		
		//先将扫描数据清零
		
		WmsTakeItemVo wmsTakeItemVo = new WmsTakeItemVo();
		wmsTakeItemVo.setTakeId((long)id);
		wmsTakeItemVo.setScanQty(0);
		wmsTakeItemService.updateBySelective(wmsTakeItemVo);
		
		// 盘点单主页
		WmsTake wmsTake = wmsTakeService.selectByPrimaryKey(id);
		modelMap.put("wmsTake", wmsTake);
		return themeService.getTemplatePath("ftls/wmstake/add_wmstake_button");

	}

	/**
	 * 
	 * @Title: detailWmstake
	 * @Description: TODO(盘点详细页面明细内容)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param wmsTakeVo
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/detail_wmstake_item.jhtml")
	public JSONObject detailWmstake(HttpServletRequest request,HttpServletResponse response,
			ModelMap modelMap, WmsTakeItemVo wmsTakeItemVo) {
		// 没有被删除
		wmsTakeItemVo.setIsDeleted(false);
		JqGridVo<WmsTakeItem> jqGridVo = wmsTakeItemService.selectByStatement(wmsTakeItemVo);
		return jqGridVo.getJSONObject();
	}

	@ResponseBody
	@RequestMapping(value = "/get_wmstake.jhtml")
	public JSONObject getWmstake(HttpServletRequest request, ModelMap modelMap,
			WmsTakeVo wmsTakeVo) {
		// 没有被删除
		wmsTakeVo.setIsDeleted(true);
		JqGridVo<WmsTake> jqGridVo = wmsTakeService
				.selectByStatement(wmsTakeVo);
		return jqGridVo.getJSONObject();
	}

	@ResponseBody
	@RequestMapping(value = "/get_wmstake_details.jhtml")
	public JSONObject getWmstakeDetails(HttpServletRequest request,
			ModelMap modelMap, WmsTakeItemVo wmsTakeItemVo) {
		
		wmsTakeItemVo.setIsDeleted(true);
		JqGridVo<WmsTakeItem> jqGridVo = wmsTakeItemService
				.selectByStatement(wmsTakeItemVo);
		return jqGridVo.getJSONObject();
	}

	@ResponseBody
	@RequestMapping(value = "/delete_wmstakes.jhtml")
	public JsonVo<String> deleteWmsTakes(HttpServletRequest request,
			ModelMap modelMap, Integer[] id) {
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		// 清除历史数据
		flag = wmsTakeService.deleteWmsTakes(id);
		if (flag > 0) {
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}

	/**
	 * 
	* @Title: startWmstakes 
	* @Description: TODO(开始盘点) 
	* @param @param request
	* @param @param modelMap
	* @param @param id
	* @param @return    设定文件 
	* @return JsonVo<String>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/start_wmstakes.jhtml")
	public JsonVo<String> startWmstakes(HttpServletRequest request,
			ModelMap modelMap, WmsTakeVo record ) {
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		record.setStatus(true);
		record.setStartTime(new Date());
		flag = wmsTakeService.updateByPrimaryKeySelective(record);
		if (flag > 0) {
			//锁定明细表
			WmsTakeItemVo wmsTakeItemVo = new WmsTakeItemVo();
			wmsTakeItemVo.setTakeId(record.getId().longValue());
			wmsTakeItemVo.setLockedDate(new Date());
			wmsTakeItemVo.setIsLocked(true);
			wmsTakeItemVo.setLockedPeople(super.getAdmin(request).getId());
			wmsTakeItemService.updateByPrimaryKeySelective(wmsTakeItemVo);
			
			//同步现货数量
			wmsTakeItemService.updateSysQty(record.getId());
			
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}
	//结束询盘
	@ResponseBody
	@RequestMapping(value = "/end_wmstakes.jhtml")
	public JsonVo<String> endWmstakes(HttpServletRequest request,
			ModelMap modelMap, WmsTakeVo record ) {
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		record.setStatus(false);
		//设置结束时间
		record.setEndTime(new Date());
		flag = wmsTakeService.updateByPrimaryKeySelective(record);
		if (flag > 0) {
			WmsTakeItemVo wmsTakeItemVo = new WmsTakeItemVo();
			wmsTakeItemVo.setTakeId(record.getId().longValue());
			
			//根据盘点结果差异调整 
			List<WmsTakeItem> changItems  = wmsTakeItemService.getDiffItemlistByTakeid(record.getId().longValue());
			for(WmsTakeItem tmp:changItems){
				//根据差异逐个调整现货表，记录库存调整日志
				LocStockVo locStockVo = new LocStockVo();
				locStockVo.setGdsId(tmp.getGdsId());//商品ID
				locStockVo.setStockId(tmp.getStockId().longValue());//仓库ID
				locStockVo.setLocId(tmp.getLocId());//库位ID
				locStockVo.setTotalQty(tmp.getCountQty().longValue());//盘点的数量
				locStockService.updateByPrimaryKeySelective(locStockVo);
			}
			//解锁锁定明细表
		
			wmsTakeItemVo.setIsLocked(false);
			wmsTakeItemVo.setLockedPeople(super.getAdmin(request).getId());
			wmsTakeItemService.updateBySelective(wmsTakeItemVo);
			
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}
	/**
	 * 
	 * @Title: addWmstake
	 * @Description: TODO(新增盘点单)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param id
	 * @param @param receiptType
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add_wmstake.jhtml", method = RequestMethod.GET)
	public String addWmstake(HttpServletRequest request, ModelMap modelMap,
			Integer id, Integer receiptType) {
		
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		
		modelMap.put("date", DateUtil.format(new Date(), "yyyy-MM-dd"));
		return themeService.getTemplatePath("ftls/wmstake/add_wmstake");
	}

	@RequestMapping(value = "/addWmstake.jhtml")
	public void saveWmstake(HttpServletRequest request,HttpServletResponse response ,
			ModelMap modelMap, WmsTakeVo wmsTakeVo)  throws Exception {
	
		this.returnData(request, response, wmsTakeService.insertSelective(wmsTakeVo));
		/*
		JsonVo<String> json = new JsonVo<String>();
		int id = 0;
		// 清除历史数据
		id = wmsTakeService.insertSelective(wmsTakeVo);
		if (id > 0) {
			json.setT(String.valueOf(id));
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
		*/
	}

	/**
	 * 
	 * @Title: saveWmsTakeItem
	 * @Description: TODO(保存添加盘点详情信息)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param wmsTakeVo
	 * @param @return 设定文件
	 * @return JsonVo<String> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/save_wmsTake_item.jhtml")
	public JsonVo<String> saveWmsTakeItem(HttpServletRequest request,
			ModelMap modelMap, WmsTakeVo wmsTakeVo) {
		JsonVo<String> json = new JsonVo<String>();
		int id = 0;
		// 清除历史数据
		id = wmsTakeItemService.insertSelective(wmsTakeVo.getWmsTakeItem());
		if (id == wmsTakeVo.getWmsTakeItem().size()) {
			json.setT(String.valueOf(id));
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}
	@ResponseBody
	@RequestMapping(value = "/delete_wmstake_item.jhtml")
	public JsonVo<String> deleteWmstakeItem(HttpServletRequest request,
			ModelMap modelMap, Integer[] id) {
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		// 清除历史数据
		flag = wmsTakeItemService.deleteWmsItemTakes(id);
		if (flag >0) {
			json.setT(String.valueOf(id));
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}
	/**
	 * 
	* @Title: exportExcel 
	* @Description: TODO(Excle 导出功能) 
	* @param @param request
	* @param @param response
	* @param @param modelMap
	* @param @param id
	* @param @param receiptType
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/export_excel.jhtml")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			Integer id, WmsTakeItemVo wmsTakeItemVo) {
		// 没有被删除
		wmsTakeItemVo.setIsDeleted(true);
		//获取全部数据
		wmsTakeItemVo.setRows(Integer.MAX_VALUE);
		JqGridVo<WmsTakeItem> jqGridVo = wmsTakeItemService
				.selectByStatement(wmsTakeItemVo);
		WmsTake wmsTake = wmsTakeService.selectByPrimaryKey(wmsTakeItemVo.getTakeId().intValue());
		String[] title={"主键(请勿改动)","商品编号","规格属性","盘点前数量","盘点数量","差异","库位","时间"};
		excelManageService.exportExcel(wmsTake.getTakeNo()+"_"+wmsTake.getId()+".xls", title, jqGridVo.getList(), response);

	}
	
	@RequestMapping(value = "/import_excel.jhtml")
	@ResponseBody
	public JsonVo<String> importExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam("file") MultipartFile file,Long takeId) {
		int flag = excelManageService.addUploadFile(file,takeId);
		JsonVo<String> json = new JsonVo<String>();
		if (flag == 0) {
			json.setSuccess(false);
			json.setMsg("上传文档不符合规范!");
		} else if(flag==1) {
			json.setSuccess(false);
			json.setMsg("上传的文档与当前页数据不匹配!");
		}else if(flag==2){
			json.setSuccess(true);
			json.setMsg("导入成功");
		}else if(flag==3){
			json.setSuccess(false);
			json.setMsg("上传的文档格式不正确!");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update_wmsTake_item.jhtml")
	public JsonVo<String> updateWmsTakeItem(HttpServletRequest request,
			ModelMap modelMap, WmsTakeVo wmsTakeVo) {
		JsonVo<String> json = new JsonVo<String>();
		int id = 0;
		// 更新数据
		id = wmsTakeItemService.updateByPrimaryKeySelectives(wmsTakeVo.getWmsTakeItem());
		if (id == wmsTakeVo.getWmsTakeItem().size()) {
			json.setT(String.valueOf(id));
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}
	
	/**
	 * 
	* @Title: checkWmsTake 
	* @Description: TODO(监测该仓库是不是正在盘点中) 
	* @param @param request
	* @param @param modelMap
	* @param @param wmsTakeVo
	* @param @return    设定文件 
	* @return JsonVo<String>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/check_wmstake.jhtml")
	public JsonVo<String> checkWmsTake(HttpServletRequest request,
			ModelMap modelMap, WmsTakeVo wmsTakeVo) {
		JsonVo<String> json = new JsonVo<String>();
		int id = 0;
		// 更新数据
		wmsTakeVo.setStatus(true);
		id = wmsTakeService.selectByStatementCount(wmsTakeVo);
		if (id>0) {
			json.setSuccess(false);
			json.setMsg("该仓库正在盘点中,不能操作!");
		} else {
			json.setSuccess(true);
		}
		return json;
	}
}
