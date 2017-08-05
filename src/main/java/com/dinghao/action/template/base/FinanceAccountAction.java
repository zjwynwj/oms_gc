package com.dinghao.action.template.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.dao.template.base.FinanceAccountDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.purchase.PurchaseSuggest;
import com.dinghao.entity.vo.template.base.CustInfoVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.service.template.base.FinanceAccountService;

/**
  * @ClassName: FinanceAccountAction
  * @Description: TODO  客户管理action层
  * @author gucong 
  * @date 2016年1月23日 上午10:02:13
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class FinanceAccountAction extends BaseAction{
	
	@Autowired
	private FinanceAccountService financeAccountService ;
	@Autowired
	private FinanceAccountDao financeAccountDao ;
	
	//跳转到账户管理页面
	@RequestMapping(value = {"/finance/turnAccountMgr.jhtml"})
	public String turnAccountMgr() throws Exception{
		return "/template/front/ftls/base/accountMgr";
	}
	//分页查询账户信息
	@RequestMapping(value = {"/finance/findAccountForGrid.jhtml"})
	public void findAccountForGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, FinanceAccountVo financeAccount) throws Exception{

		
		CommResponse commResponse = new CommResponse();
		commResponse = financeAccountService.findFinanceAccountForGrid(financeAccount);
	   
	
		this.returnData(request, response, commResponse);
	}
	
	//跳转到添加账户页面
	@RequestMapping(value = {"/finance/turnAddAccount.jhtml"})
	public String turnAddAccount() throws Exception{
			return "/template/front/ftls/base/addAccount";
	}
	
	
	//添加账户
	@RequestMapping(value = {"/finance/addAccount.jhtml"})
	public void addAccount(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, FinanceAccount financeAccount) throws Exception{
	
		CommResponse commResponse = new CommResponse();
		commResponse.setData(financeAccountDao.insertSelective(financeAccount));
		commResponse.setSuccess(true);
		commResponse.setErrMsg("新增账户成功！");
		//commResponse = financeAccountService.findFinanceAccountForGrid(financeAccount);
		 
		 this.returnData(request, response, commResponse);
	}
	//跳转到修改账户页面
	@RequestMapping(value = {"/finance/turnModAccount.jhtml"})
	public String turnModAccount(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, FinanceAccount financeAccount) throws Exception{
		
		
		Long Id = financeAccount.getId();//Long.parseLong(request.getParameterValues("id").toString());
		
		financeAccount = financeAccountDao.selectByPrimaryKey(Id);
	
		modelMap.put("financeAccount",financeAccount);
		
		return "/template/front/ftls/base/modAccount";
	}
	
	//修改账号
		@RequestMapping(value = {"/finance/modAccount.jhtml"})
		public void modAccount(HttpServletRequest request ,HttpServletResponse response ,
				ModelMap modelMap, FinanceAccountVo financeAccount) throws Exception{
			
			CommResponse commResponse = new CommResponse();
		  
		
		    commResponse.setData(financeAccountDao.selectByPrimaryKey(financeAccount.getId()));
			financeAccountDao.updateByPrimaryKeySelective(financeAccount);
			commResponse.setSuccess(true);
			this.returnData(request, response, commResponse);
		}

	
}
