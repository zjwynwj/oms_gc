package com.dinghao.action.template.business.express;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressareaVo;
import com.dinghao.entity.vo.template.business.express.ExpressareaVoList;
import com.dinghao.service.template.business.express.DmsExpressareaService;
import com.dinghao.util.StringUtil;

@Controller
@RequestMapping("/template")
public class DmsExpressareaAction extends BaseAction{

	@Autowired
	private DmsExpressareaService expressareaService;
	
	//跳转到物流配送信息管理页面
	@RequestMapping(value = {"/expressareaMgr/turnExpressareaMgr.jhtml"})
	public String turnExpressareaMgr(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo dmsExpressVo) throws Exception{
		if(StringUtil.getEncoding(dmsExpressVo.getName()) == "ISO-8859-1"){
		 dmsExpressVo.setName(new String(dmsExpressVo.getName().getBytes("ISO-8859-1"),"UTF-8"));
		}
		modelMap.put("dmsExpressVo", dmsExpressVo);
		return "/template/front/ftls/express/expressareaMgr";
	}
	
	//修改配送区域信息
	@RequestMapping(value = {"/expressareaMgr/modExpressareaInfo.jhtml"})
	public void modExpressareaInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, ExpressareaVoList expressareaVoList) throws Exception {
         this.returnData(request, response, expressareaService.modDmsExpressareaInfo(expressareaVoList));
	}
	
	//修改配送区域是否到达
	@RequestMapping(value = {"/expressareaMgr/modDmsExpressareaDelivery.jhtml"})
	public void modDmsExpressareaDelivery(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressareaVo dmsExpressareaVo) throws Exception {
         this.returnData(request, response, expressareaService.modDmsExpressareaDelivery(dmsExpressareaVo));
	}
	
	//分页查询配送区域信息
	@RequestMapping(value = {"/expressareaMgr/findExpressareaInfoGrid.jhtml"})
	public void findExpressareaInfoGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressareaVo dmsExpressareaVo) throws Exception {
         this.returnData(request, response, expressareaService.findDmsExpressareaInfoForGrid(dmsExpressareaVo));
	}
	
	//查询配送区域信息list
	@RequestMapping(value = {"/expressareaMgr/findDmsExpressareaInfoList.jhtml"})
	public void findDmsExpressareaInfoList(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressareaVo dmsExpressareaVo) throws Exception {
         this.returnData(request, response, expressareaService.findDmsExpressareaInfoList(dmsExpressareaVo));
	}
	
	//查询物流是否到达指定区域
	@RequestMapping(value = {"/expressareaMgr/queryIsArriveAppointArea.jhtml"})
	public void queryIsArriveAppointArea(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressareaVo dmsExpressareaVo) throws Exception {
         this.returnData(request, response, expressareaService.queryIsArriveAppointArea(dmsExpressareaVo));
	}
}
