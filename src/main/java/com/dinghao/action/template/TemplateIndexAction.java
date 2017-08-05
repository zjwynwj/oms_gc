/*



 */

package com.dinghao.action.template;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.manage.menu.Menu;
import com.dinghao.entity.template.admin.Parents;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.template.admin.TemplateAdminMenu;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.manage.menuvo.MenuVo;
import com.dinghao.service.manage.menu.MenuService;
import com.dinghao.service.template.login.TemplateAdminService;

/**
 * 首页
 * 
 * @author ZiHan
 */
@Controller
public class TemplateIndexAction extends BaseAction {

	@Autowired
	TemplateAdminService templateAdminService;
	@Autowired
	MenuService menuService;

	/**
	 * 首页
	 * 
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = { "/", "/index.jhtml" }, method = RequestMethod.GET)
	public String home(HttpServletRequest request, ModelMap modelMap) {
		//return themeService.getTemplatePath("index");
		// modify gucong 2016-03-26
		return myPage(modelMap,request);

	}

	@RequestMapping(value = "/login.jhtml", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap,
			String requestUrlString) {
		modelMap.put("requestUrlString", requestUrlString);
		// 如果已登录,就不在跳转到登录页面
		if (request.getSession().getAttribute(SystemConstant.TEMPLATE_ADMIN) != null) {
			if (StringUtils.isBlank(requestUrlString)) {

				return themeService.getTemplatePath("index");
			} else {
				return "redirect:" + requestUrlString;
			}
		}
		return themeService.getTemplatePath("login");
	}
	@RequestMapping(value = "/logout.jhtml", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap modelMap) {
		request.getSession().removeAttribute(SystemConstant.TEMPLATE_ADMIN);
		return themeService.getTemplatePath("login");
		//return "redirect:" + HttpUtils.getBasePath(request);
	}
	/**
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping(value = "/404.htm", method = RequestMethod.GET)
	public String pageNotFound(ModelMap modelMap) {
		modelMap.addAttribute("g_folderId", 0);
		return themeService.get404();
	}

	@ResponseBody
	@RequestMapping(value = "/loginJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> adminLogin(String userName, String password,
			String captcha, HttpServletRequest request, ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();

		try {
			String kaptcha = (String) request.getSession().getAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			} else if (password.length() < 6 && password.length() > 30) {
				json.getErrors().put("password", "密码最少6个字符，最多30个字符");
			}
			// 校验验证码
			if (StringUtils.isNotBlank(kaptcha)
					&& kaptcha.equalsIgnoreCase(captcha)) {

			} else {
				json.getErrors().put("captcha", "验证码错误");
			}
			json.check();

			templateAdminService.adminLogin(userName, password, request);

		} catch (Exception e) {
			// 异常，重置验证码
			request.getSession().removeAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			json.setResult(false);
			json.getErrors().put("password", "用户名或密码错误");
			json.setMsg("change_captcha");
		}
		return json;
	}

	/**
	 * 500
	 * 
	 * @return
	 */
	@RequestMapping(value = "/500.htm", method = RequestMethod.GET)
	public String error(ModelMap modelMap) {
		modelMap.addAttribute("g_folderId", 0);
		return themeService.get500();
	}

	/**
	 * 
	 * 方法名: myPage<br/>
	 * 方法描述: 首次加载页面<br/>
	 * 修改时间：2015年12月11日 下午2:45:46
	 * 
	 * @param id
	 * @param modelMap
	 * @param request
	 * @return 参数说明 返回类型 String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/template/my_page.jhtml", method = RequestMethod.GET)
	public String myPage(ModelMap modelMap, HttpServletRequest request) {
		TemplateAdmin admin = this.getAdmin(request);
		List<TemplateAdminMenu> templateAdminMenus = new ArrayList<TemplateAdminMenu>();
		List<Menu> menusGrandParent = new ArrayList<Menu>();
		List<Menu> menusParent      = new ArrayList<Menu>();
		// 内置管理员
		if (admin.getId() == 1l) {
			// 获取所有导航
			MenuVo menuVo = new MenuVo();
			menuVo.setDelFlag(null);
			menuVo.setMenuType(1l);
			menuVo.setRows(Integer.MAX_VALUE);
			menusParent = menuService.findParentMenu(menuVo).getList();
		} else {
			// 获取父节点
			menusParent = menuService.findParentMenuByAdminId(admin.getId()
					.intValue());
		}
		Set<Long> grandprandId = new HashSet<Long>();
		for (Menu menu : menusParent) {
			grandprandId.add(menu.getParentId());
		}
		menusGrandParent = menuService
				.findGrandParentsMenuByAdminId(grandprandId);
		List<Menu> menusChildren = menuService.findChildrenMenu(null,
				admin.getId());
		// 遍历整理数据
		Iterator<Menu> iterator = menusGrandParent.iterator();
		while (iterator.hasNext()) {
			Menu menu = (Menu) iterator.next();
			TemplateAdminMenu templateAdminMenu = new TemplateAdminMenu();
			List<Parents> parents = new ArrayList<Parents>();
			templateAdminMenu.setGrandparents(menu.getName());
			// 父级
			Iterator<Menu> iterator1 = menusParent.iterator();
			while (iterator1.hasNext()) {
				List<Menu> children = new ArrayList<Menu>();
				Parents parents2 = new Parents();
				Menu menusparent = (Menu) iterator1.next();
				if (menusparent.getParentId().intValue() == menu.getId()
						.intValue()) {
					parents2.setParents(menusparent.getName());
					// iterator1.remove();
				}
				Iterator<Menu> iterator2 = menusChildren.iterator();
				while (iterator2.hasNext()) {
					Menu menuschildren = (Menu) iterator2.next();
					if (menuschildren.getParentId().intValue() == menusparent
							.getId().intValue()
							&& menusparent.getParentId().intValue() == menu
									.getId().intValue()) {
						children.add(menuschildren);
						// iterator2.remove();
					}
				}
				if (children.size() > 0) {
					parents2.setChildren(children);
					parents.add(parents2);
				}
			}
			templateAdminMenu.setParents(parents);
			templateAdminMenus.add(templateAdminMenu);
		}
		modelMap.put("templateAdminMenus", templateAdminMenus);
		return themeService.getTemplatePath("newindex");
	}

}
