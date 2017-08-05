package com.dinghao.action.template.business.express;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.util.StringUtil;

@Controller
@RequestMapping("/template")
public class DmsExpressAction extends BaseAction{

	@Autowired
	private DmsExpressService expressService;
	
	//跳转到物流公司管理页面
	@RequestMapping(value = {"/expressMgr/turnExpressMgr.jhtml"})
	public String turnExpressMgr() {
		return "/template/front/ftls/express/expressMgr";
	}
	
	//跳转到修改物流公司信息管理页面
	@RequestMapping(value = {"/expressMgr/turnModExpress.jhtml"})
	public String turnModExpress(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String id) {
		modelMap.put("id", id);
		return "/template/front/ftls/express/modExpress";
	}
	
	//下载物流公司信息
	@RequestMapping(value = {"/expressMgr/downExpressInfo.jhtml"})
	public void downExpressInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo expressVo) throws Exception {
         this.returnData(request, response, expressService.downDmsExpressInfo(expressVo));
	}
	
	//修改单条物流公司信息
	@RequestMapping(value = {"/expressMgr/modExpressInfo.jhtml"})
	public void modExpressInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo expressVo) throws Exception {
         this.returnData(request, response, expressService.modDmsExpressInfo(expressVo));
	}
	
	//查询 物流公司信息
	@RequestMapping(value = {"/expressMgr/queryExpressInfo.jhtml"})
	public void queryExpressInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo expressVo) throws Exception {
         this.returnData(request, response, expressService.queryDmsExpressInfo(expressVo));
	}
	
	//查询 物流公司信息列表
	@RequestMapping(value = {"/expressMgr/getAllDmsExpress.jhtml"})
	public void getAllDmsExpress(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo expressVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		commResponse.setData(expressService.getAllDmsExpress(expressVo));
        this.returnData(request, response, commResponse);
	}
	
	//分页查询物流公司信息
	@RequestMapping(value = {"/expressMgr/findExpressInfoGrid.jhtml"})
	public void findExpressInfoGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DmsExpressVo expressVo) throws Exception {
		if(null != expressVo.getKeyWord() && !"".equals(expressVo.getKeyWord())){
			if(StringUtil.getEncoding(expressVo.getKeyWord()) == "ISO-8859-1"){
				expressVo.setKeyWord(new String(expressVo.getKeyWord().getBytes("ISO-8859-1"), "UTF-8"));
			}
		}
        this.returnData(request, response, expressService.findDmsExpressInfoForGrid(expressVo));
	}
}
