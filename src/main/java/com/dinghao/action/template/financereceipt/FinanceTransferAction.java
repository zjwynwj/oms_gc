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
import com.dinghao.entity.template.financereceipt.FinanceTransfer;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinanceTransferVo;
import com.dinghao.service.template.base.FinanceAccountService;
import com.dinghao.service.template.financereceipt.FinanceTransferService;
import com.dinghao.util.DateUtil;

@Controller
@RequestMapping("/template/finance_transfer")
public class FinanceTransferAction extends BaseAction {
	@Autowired
	FinanceTransferService financeTransferService;
	@Autowired
	FinanceAccountService financeAccountService;
	
	@RequestMapping(value = { "", "/index.jhtml" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap) {
		return themeService.getTemplatePath("ftls/financeTransfer/index");
	}

	@ResponseBody
	@RequestMapping(value = "/get_finance_transfers.jhtml")
	public JSONObject getWmstake(HttpServletRequest request, ModelMap modelMap,
			FinanceTransferVo record) {
		JqGridVo<FinanceTransfer> jqGridVo = financeTransferService
				.selectByStatement(record);
		return jqGridVo.getJSONObject();
	}
	
	@RequestMapping(value = "add_financetransfer", method = RequestMethod.GET)
	public String add(HttpServletRequest request, ModelMap modelMap) {
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeAccounts", financeAccounts);
		modelMap.put("busiDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		return themeService.getTemplatePath("ftls/financetransfer/add_financetransfer");
	}

	@RequestMapping(value = "/save_financetransfer.jhtml")
	public void saveReceiptDetails(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap, FinanceTransferVo record)throws Exception {
		
		 this.returnData(request, response,financeTransferService.insertSelective(record));
		 
		 /*
		JsonVo<String> json = new JsonVo<String>();
		int flag = 0;
		flag=financeTransferService.insertSelective(record);
		if(flag>1){
			json.setSuccess(true);
			json.setT(String.valueOf(flag));
			json.setMsg("操作成功!");
		}else{
			json.setSuccess(false);
			json.setMsg("操作失败!");
		}		
		return json;
		*/
	}
		
	@RequestMapping(value = "view_financetransfer.jhtml", method = RequestMethod.GET)
	public String view(HttpServletRequest request, ModelMap modelMap,long id) {
		FinanceTransfer financeTransfer =financeTransferService.selectByPrimaryKey(id);
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setUserId(super.getAdmin(request).getId());
		financeAccountVo.setOffset(0);
		financeAccountVo.setRows(Integer.MAX_VALUE);
		List<FinanceAccount> financeAccounts = financeAccountService.getFinanceAccounts(financeAccountVo);
		modelMap.put("financeAccounts", financeAccounts);
		modelMap.put("financeTransfer", financeTransfer);
		return themeService.getTemplatePath("ftls/financetransfer/view_financetransfer");
	}
}
