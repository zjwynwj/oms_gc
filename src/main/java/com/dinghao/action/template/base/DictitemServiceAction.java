package com.dinghao.action.template.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.base.DictVo;
import com.dinghao.service.template.base.DictitemService;

@Controller
@RequestMapping("/template")
public class DictitemServiceAction extends BaseAction {

	@Autowired
	private DictitemService dictitemService ;
	
	//查询数据字典
	@RequestMapping(value = {"/dict/queryDictItem.jhtml"})
	public void queryDictItem(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, DictVo dictVo) throws Exception{
		 this.returnData(request, response, dictitemService.queryDictItem(dictVo));
	}
}
