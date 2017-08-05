package com.dinghao.action.template.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.dao.template.base.CustInfoDao;
import com.dinghao.entity.template.base.CustInfo;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.manage.adminvo.AdminVo;
import com.dinghao.entity.vo.template.base.CustInfoVo;
import com.dinghao.service.manage.AdminService;
import com.dinghao.service.template.base.CustInfoService;

/**
  * @ClassName: CustInfoAction
  * @Description: TODO  客户管理action层
  * @author helong 
  * @date 2016年1月6日 上午10:02:13
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class CustInfoAction extends BaseAction{
	@Autowired
	private CustInfoService custService;
	@Autowired
	AdminService adminService;
	@Autowired
	private CustInfoDao custInfoDao ;
	//跳转到客户管理页面
	@RequestMapping(value = {"/custInfo/turnCustInfoMgr.jhtml"})
	public String turnCutsInfoMgr() throws Exception{
		return "/template/front/ftls/base/custInfoMgr";
	}
	/**
	 * 
	* @Title: turnGetCutsInfoMgr 
	* @Description: TODO(弹出框获取客户信息) 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = {"/custInfo/get_custinfomgr.jhtml"})
	public String turnGetCutsInfoMgr() throws Exception{
		return "/template/front/ftls/base/getCustInfoMgr";
	}
	//跳转到添加客户页面
	@RequestMapping(value = {"/custInfo/turnAddCustInfo.jhtml"})
	public String turnAddCutsInfo() throws Exception{
		return "/template/front/ftls/base/addCustInfo";
	}
	
	//跳转到修改客户页面
	@RequestMapping(value = {"/custInfo/turnModCustInfo.jhtml"})
	public String turnModCutsInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, CustInfoVo custInfoVo) throws Exception{
		
	   Long Id = custInfoVo.getId();//Long.parseLong(request.getParameterValues("id").toString());
		
	   CustInfo custInfo = custInfoDao.selectByPrimaryKey(Id);
	
		modelMap.put("custInfo",custInfo);
		
		return "/template/front/ftls/base/modCustInfo";
	}
	
	//添加客户
	@RequestMapping(value = {"/custInfo/addCustInfo.jhtml"})
	public void addCustInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, CustInfoVo custInfoVo) throws Exception{
		 this.returnData(request, response, custService.addCustInfo(custInfoVo));
	}
	
	//修改客户
	@RequestMapping(value = {"/custInfo/modCustInfo.jhtml"})
	public void modCustInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, CustInfoVo custInfoVo) throws Exception{
		 this.returnData(request, response, custService.modCustInfo(custInfoVo));
	}
	
	//查询客户信息
	@RequestMapping(value = {"/custInfo/queryCustInfoById.jhtml"})
	public void queryCustInfoById(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, CustInfoVo custInfoVo) throws Exception{
		 this.returnData(request, response, custService.queryCustInfoById(custInfoVo));
	}
	
	//分页查询客户信息
	@RequestMapping(value = {"/custInfo/findCustInfoForGrid.jhtml"})
	public void findCustInfoForGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, CustInfoVo custInfoVo) throws Exception{	
		/*
		if(null != custInfoVo.getKeyWord() && !"".equals(custInfoVo.getKeyWord())){
			custInfoVo.setKeyWord(new String(custInfoVo.getKeyWord().getBytes("ISO-8859-1"), "UTF-8"));
		}
		*/
		//System.out.println(custInfoVo.getKeyWord());
		//System.out.println();
		this.returnData(request, response, custService.findCustInfoForGrid(custInfoVo));
	}
	
	@RequestMapping(value = {"/custInfo/findUserList.jhtml"})
	public void findUserList(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, AdminVo adminVo) throws Exception{		 
		
		//adminVo.(new String(adminVo.getKeyWord().getBytes("ISO-8859-1"), "UTF-8"));
		
		//System.out.println(custInfoVo.getKeyWord());
		//System.out.println();
		
		CommResponse commResponse = new CommResponse();
	    commResponse.setData(adminService.getAllListPage(1));
	    commResponse.setSuccess(true);
		this.returnData(request, response, commResponse);
	}
}
