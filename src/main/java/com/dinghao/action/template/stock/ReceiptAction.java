package com.dinghao.action.template.stock;

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
import com.dinghao.dao.template.base.CustInfoDao;
import com.dinghao.entity.template.base.CustInfo;
import com.dinghao.entity.template.receipt.Receipt;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.CustInfoVo;
import com.dinghao.entity.vo.template.receipt.ReceiptDetailVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.base.CustInfoService;
import com.dinghao.service.template.receipt.ReceiptService;
import com.dinghao.service.template.warehouse.WarehousesService;
import com.dinghao.util.StringUtil;

/**
 * 
 * @ClassName: ReceiptAction
 * @Description: TODO(库存管理 入库单)
 * @author Zihan
 * @date 2016年1月13日 下午6:08:54
 *
 */
@Controller
@RequestMapping("/template/receipt")
public class ReceiptAction extends BaseAction {
	@Autowired
	ReceiptService receiptService;
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	private CustInfoDao custDao;

	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap,
			Integer receiptType) {
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		warehouseVo.setIsDelete(false);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		if (receiptType != null && receiptType == 2) {
			// 出库单
			return themeService.getTemplatePath("ftls/outbound/index");
		} else {
			// 入库单
			return themeService.getTemplatePath("ftls/receipt/index");
		}

	}

	/**
	 * 
	 * @Title: getReceipts
	 * @Description: TODO(获取入库单信息)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param receiptVo
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/get_receipts.jhtml")
	public JSONObject getReceipts(HttpServletRequest request,
			ModelMap modelMap, ReceiptVo receiptVo) {
		JqGridVo<Receipt> jqGridVo = receiptService.getReceipts(receiptVo,
				this.getAdmin(request));
		return jqGridVo.getJSONObject();
	}

	/**
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * 
	 * @Title: addOrUpdate
	 * @Description: TODO(修改或删除出库单信息)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add_update.jhtml", method = RequestMethod.GET)
	public String addOrUpdate(HttpServletRequest request, ModelMap modelMap,
			Integer id, Integer receiptType  , String purOrderIds) throws NumberFormatException, Exception {
		// 入库单的主键
		Receipt receipt = new Receipt();
		if (id != null) {
			receipt = receiptService.selectByPrimaryKey(id);
			//获取客户信息
		
			if (null != receipt.getProviderId() && !"".equals(receipt.getProviderId()))
			{
				CustInfo custInfo = custDao.selectByPrimaryKey(Long.parseLong(receipt.getProviderId()));
			    modelMap.put("custInfo", custInfo);
			}
		}
		else
		{
			receipt.setCreateDate(new Date() );
			receipt.setServiceType(2);		
		}
		//设置仓库列表
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setIsDelete(false);
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		
		if (!StringUtil.isEmpty(purOrderIds)) {
			receipt.setServiceType(1);
			modelMap.put("purOrderIds", purOrderIds);
			
		}
		modelMap.put("receipt", receipt);
		if (receiptType == 2) {
			if (id != null) {
				
				 return themeService.getTemplatePath("ftls/outbound/add_update");			     
			}
			else
			{
				 return themeService.getTemplatePath("ftls/outbound/add");
			}

		} else {
			if (id != null) {
				
				 return themeService.getTemplatePath("ftls/receipt/update");			     
			}
			else
			{
				 return themeService.getTemplatePath("ftls/receipt/add");
			}
		}
	}
	
	@RequestMapping(value = "/add_pur_receipt.jhtml", method = RequestMethod.GET)
	public String addPurReceipt(HttpServletRequest request, ModelMap modelMap, String purOrderId,
			String custName, String custId , String purNo) throws Exception{
		
		//设置仓库列表
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setIsDelete(false);
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
				
		modelMap.put("purOrderId", purOrderId);
		
		if(!StringUtil.isEmpty(custName)){
			if(StringUtil.getEncoding(custName) == "ISO-8859-1"){
				custName = new String(custName.getBytes("ISO-8859-1"), "UTF-8");
				modelMap.put("custName", custName);
			}
			else{
				modelMap.put("custName", custName);
			}
		}
		modelMap.put("custId", custId);
		modelMap.put("purNo", purNo);
		return themeService.getTemplatePath("ftls/receipt/addPurReceipt");
	}
	
	@RequestMapping(value = "/add_pur_outbound.jhtml", method = RequestMethod.GET)
	public String addPurOutBound(HttpServletRequest request, ModelMap modelMap, String purOrderId,
			String custName, String custId , String purNo) throws Exception{
		//设置仓库列表
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setIsDelete(false);
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService
				.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
				
		modelMap.put("purOrderId", purOrderId);
		if(!StringUtil.isEmpty(custName)){
			if(StringUtil.getEncoding(custName) == "ISO-8859-1"){
				custName = new String(custName.getBytes("ISO-8859-1"), "UTF-8");
				modelMap.put("custName", custName);
			}
		}
		modelMap.put("custId", custId);
		modelMap.put("purNo", purNo);
		return themeService.getTemplatePath("ftls/outbound/addPurOutBound");
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: getReceiptDetails
	 * @Description: TODO(获取出库单详细内容)
	 * @param @param request
	 * @param @param modelMap
	 * @param @param receiptVo
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/get_receipt_details.jhtml")
	public void getReceiptDetails(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, ReceiptDetailVo receiptDetailVo) throws Exception {

	   this.returnData(request, response,receiptService.getReceiptDetails(receiptDetailVo,this.getAdmin(request)));


	}

	@ResponseBody
	@RequestMapping(value = "/save_receipt_details.jhtml")
	public JsonVo<String> saveReceiptDetails(HttpServletRequest request,
			ModelMap modelMap, ReceiptVo receiptVo) {
		
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		// 清除历史数据，这里有问题，以后调整，正常应该只删除实际删除的，目前先在前台控制不能编辑
		receiptService.deleteReceiptDetails(receiptVo.getId(),this.getAdmin(request));
		
		
		for (ReceiptDetailVo receiptDetailVo : receiptVo.getReceiptDetailVos()) {
			// 入库单明细更新
			flag = receiptService.insertReceiptDetail(receiptDetailVo,receiptVo.getWarehouseId(),receiptVo.getReceiptType());
		}

		//
		if (flag > 0) {
			json.setSuccess(true);
			json.setMsg("操作成功!");
		} else {
			json.setResult(false);
			json.setMsg("操作失败!");
		}
		return json;
	}


	@RequestMapping(value = "/saveReceipt.jhtml", method = RequestMethod.POST)
	public void saveReceipt(HttpServletRequest request,HttpServletResponse response,
			ModelMap modelMap, ReceiptVo receiptVo) throws Exception {
		this.returnData(request, response, receiptService.save_receipt(receiptVo));	
		
		
	}
}
