package com.dinghao.action.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dinghao.constant.SystemConstant;
import com.dinghao.exception.FolderNotFoundException;

/**
 * @author Zihan
 * @author 进入网站后台首页
 * 
 */

@Controller
@RequestMapping("/manage")
public class ManageAction extends ManageBaseAction {

	/**
	 * 
	* 方法名: index
	* <p/>
	* 方法描述: 系统默认页面
	* <p/>
	* 修改时间：2015年12月4日 下午1:58:41
	* @param request
	* @param modelMap
	* @return
	* @throws FolderNotFoundException 参数说明
	* 返回类型 String 返回类型
	* @throws
	 */
	@RequestMapping(value = "/index.jhtml", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap)
			throws FolderNotFoundException {
		return"redirect:/manage/admin/myPage.jhtml";
	}
	/**
	 * 对应的请求(http://localhost:8088/manage)
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String defalut(HttpServletRequest request,
			ModelMap modelMap) {
		try {
			return index(request, modelMap);
		} catch (FolderNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/login.jhtml", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap,String requestUrlString) {
		modelMap.put("requestUrlString", requestUrlString);
		//如果已登录,就不在跳转到登录页面
		if (request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN)!=null) {
			if(StringUtils.isBlank(requestUrlString))
				//指定到默认页面
				requestUrlString="/manage/admin/myPage.jhtml";
			return "redirect:" + requestUrlString;
		}
		return "/manage/login";
	}
}
