package com.dinghao.action.template.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
import com.dinghao.service.template.base.BaseNumberService;

/**
  * @ClassName: BaseNumberAction
  * @Description: TODO  单据号管理控制层
  * @author helong 
  * @date 2016年1月5日 下午4:01:02
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class BaseNumberAction extends BaseAction{

	@Autowired
	private BaseNumberService numberService;
	
	//跳转到单据号管理页面
	@RequestMapping(value = {"/baseNumber/turnNumberMgr.jhtml"})
	public String turnNumberMgr() throws Exception{
		return "/template/front/ftls/base/numberMgr";
	}
	
	//跳转到修改单据号页面
	@RequestMapping(value = {"/baseNumber/turnModNumber.jhtml"})
	public String turnModNumber(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, BaseNumberVo baseNumberVo) throws Exception{
		return "/template/front/ftls/base/modNumber";
	}
	
	//查询单据号信息
	@RequestMapping(value = {"/baseNumber/queryBaseNumber.jhtml"})
	public void queryBaseNumber(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, BaseNumberVo baseNumberVo) throws Exception{
		modelMap.put("baseNumber", numberService.queryBaseNumberById(baseNumberVo));
		 this.returnData(request, response, numberService.queryBaseNumberById(baseNumberVo));
	}
	
	//修改单据号
	@RequestMapping(value = {"/baseNumber/modNumberMgr.jhtml"})
	public void modNumberMgr(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, BaseNumberVo baseNumberVo) throws Exception{
		 this.returnData(request, response, numberService.modBaseNumber(baseNumberVo));
	}
	
	//根据单据号类型 生成单据号
	@RequestMapping(value = {"/baseNumber/findBaseNumberNext.jhtml"})
	public void findBaseNumberNext(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, BaseNumberVo baseNumberVo) throws Exception{
		 this.returnData(request, response, numberService.findBaseNumberNext(baseNumberVo));
	}
	
	//分页查询单据号信息
	@RequestMapping(value = {"/baseNumber/findBaseNumberForGrid.jhtml"})
	public void findBaseNumberForGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, BaseNumberVo baseNumberVo) throws Exception{
		 this.returnData(request, response, numberService.findBaseNumberForGrid(baseNumberVo));
	}
}
