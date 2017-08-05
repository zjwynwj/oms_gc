package com.dinghao.action.template.business.express;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.business.express.SenderInfoVo;
import com.dinghao.service.template.business.express.SenderInfoService;
import com.dinghao.util.StringUtil;

@Controller
@RequestMapping("/template")
public class SenderInfoAction extends BaseAction{

	@Autowired
	private SenderInfoService senderInfoService;
	
	//跳转到打印模板管理页面
	@RequestMapping(value = {"/senderInfoMgr/turnSenderInfo.jhtml"})
	public String turnSenderInfo() throws Exception{
		return "/template/front/ftls/express/senderInfo";
	}
	
	//保存信息
	@RequestMapping(value = {"/senderInfoMgr/saveSenderInfo.jhtml"})
	public void modSenderInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, SenderInfoVo senderInfoVo) throws Exception {
		if(senderInfoVo.getId() == null ){
			this.returnData(request, response, senderInfoService.addSenderInfo(senderInfoVo));
		}else{
			this.returnData(request, response, senderInfoService.modSenderInfo(senderInfoVo));
		}
	}
	
	//查询信息
	@RequestMapping(value = {"/senderInfoMgr/querySenderInfo.jhtml"})
	public void querySenderInfo(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, SenderInfoVo senderInfoVo) throws Exception {
         this.returnData(request, response, senderInfoService.querySenderInfo(senderInfoVo));
	}
}
