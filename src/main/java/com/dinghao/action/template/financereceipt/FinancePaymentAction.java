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
import com.dinghao.entity.template.financereceipt.FinancePayment;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinancePaymentVo;
import com.dinghao.service.template.base.FinanceAccountService;
import com.dinghao.service.template.financereceipt.FinancePaymentService;
import com.dinghao.util.DateUtil;

@Controller
@RequestMapping("/template/finance_payment")
public class FinancePaymentAction extends BaseAction {
	@Autowired
	FinancePaymentService financePaymentService;
	@Autowired
	FinanceAccountService financeAccountService;
	
	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap) {
		return themeService.getTemplatePath("ftls/financepayment/index");
	}

	@ResponseBody
	@RequestMapping(value = "/get_finance_payments.jhtml")
	public JSONObject getWmstake(HttpServletRequest request, ModelMap modelMap,
			FinancePaymentVo record) {
		JqGridVo<FinancePayment> jqGridVo = financePaymentService
				.selectByStatement(record);
		return jqGridVo.getJSONObject();
	}
	
	@RequestMapping(value = "add_financepayment", method = RequestMethod.GET)
	public String add(HttpServletRequest request, ModelMap modelMap) {
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeAccounts", financeAccounts);
		modelMap.put("busiDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		return themeService.getTemplatePath("ftls/financepayment/add_financepayment");
	}

	@RequestMapping(value = "/save_financepayment.jhtml")
	public void saveReceiptDetails(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, FinancePaymentVo record) throws Exception {

		if(record.getId()!=null){
	        this.returnData(request, response,financePaymentService.updateByPrimaryKeySelective(record));
		}else{
			this.returnData(request, response,financePaymentService.insertSelective(record));
		}
	}
	@RequestMapping(value = "update_financepayment.jhtml", method = RequestMethod.GET)
	public String update(HttpServletRequest request, ModelMap modelMap,long id) {
		FinancePayment financePayment =financePaymentService.selectByPrimaryKey(id);
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeAccounts", financeAccounts);
		modelMap.put("financePayment", financePayment);
		return themeService.getTemplatePath("ftls/financepayment/update_financepayment");
	}
	
	@RequestMapping(value = "view_financepayment.jhtml", method = RequestMethod.GET)
	public String view(HttpServletRequest request, ModelMap modelMap,long id) {
		FinancePayment financePayment =financePaymentService.selectByPrimaryKey(id);
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeAccounts", financeAccounts);
		modelMap.put("financePayment", financePayment);
		return themeService.getTemplatePath("ftls/financepayment/view_financepayment");
	}
}
