package com.dinghao.action.template.financereceipt;

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
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.financereceipt.FinanceReceipt;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinanceReceiptVo;
import com.dinghao.service.template.base.FinanceAccountService;
import com.dinghao.service.template.financereceipt.FinanceReceiptService;
import com.dinghao.util.DateUtil;

@Controller
@RequestMapping("/template/finance_receipt")
public class FinanceReceiptAction extends BaseAction {
	@Autowired
	FinanceReceiptService financeReceiptService;
	@Autowired
	FinanceAccountService financeAccountService;

	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap) {
		return themeService.getTemplatePath("ftls/financereceipt/index");
	}

	@ResponseBody
	@RequestMapping(value = "/get_finance_receipts.jhtml")
	public JSONObject getWmstake(HttpServletRequest request, ModelMap modelMap,
			FinanceReceiptVo record) {
		JqGridVo<FinanceReceipt> jqGridVo = financeReceiptService
				.selectByStatement(record);
		return jqGridVo.getJSONObject();
	}
	
	@RequestMapping(value = "add_financereceipt", method = RequestMethod.GET)
	public String add(HttpServletRequest request, ModelMap modelMap) {
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("busiDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		modelMap.put("financeAccounts", financeAccounts);
		return themeService.getTemplatePath("ftls/financereceipt/add_financereceipt");
	}

	@RequestMapping(value = "/save_financereceipt.jhtml")
	public void saveReceiptDetails(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, FinanceReceiptVo record)  throws Exception {
		
		//return this.returnData(request, response, shopService.mod(shopVo));
		
		//JsonVo<String> json = new JsonVo<String>();
		//int flag = 0;
	   //  this.returnData(request, response,financeReceiptService.updateByPrimaryKeySelective(record));
	
		if(record.getId()!=null){
	        this.returnData(request, response,financeReceiptService.updateByPrimaryKeySelective(record));
		}else{
			this.returnData(request, response,financeReceiptService.insertSelective(record));
		}

	}
	@RequestMapping(value = "update_financereceipt.jhtml", method = RequestMethod.GET)
	public String update(HttpServletRequest request, ModelMap modelMap,long id) {
		FinanceReceipt financeReceipt =financeReceiptService.selectByPrimaryKey(id);
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeReceipt", financeReceipt);
		modelMap.put("financeAccounts", financeAccounts);
		return themeService.getTemplatePath("ftls/financereceipt/update_financereceipt");
	}
	@RequestMapping(value = "view_financereceipt.jhtml", method = RequestMethod.GET)
	public String view(HttpServletRequest request, ModelMap modelMap,long id) {
		FinanceReceipt financeReceipt =financeReceiptService.selectByPrimaryKey(id);
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeReceipt", financeReceipt);
		modelMap.put("financeAccounts", financeAccounts);
		return themeService.getTemplatePath("ftls/financereceipt/view_financereceipt");
	}
}
