package com.dinghao.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.manage.admin.Admin;
import com.dinghao.entity.manage.menu.Menu;
import com.dinghao.entity.manage.role.Role;
import com.dinghao.entity.vo.manage.menuvo.MenuVo;
import com.dinghao.service.manage.menu.MenuService;
import com.dinghao.service.manage.role.RoleService;

@Component
public class ManageInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	RoleService roleService;
	@Autowired
	MenuService menuService;
	private static MenuVo menuVo;
	static {
		menuVo = new MenuVo();
		menuVo.setDelFlag(null);
		menuVo.setRows(Integer.MAX_VALUE);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		if (admin == null) {
			// 请求的URL
			String redirectUrl = request.getQueryString() != null ? request
					.getRequestURI() + "?" + request.getQueryString() : request
					.getRequestURI();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
	
			response.sendRedirect(basePath + "/admin/login.jhtml?requestUrlString="
					+ redirectUrl);
		} else {	
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 存放导航信息
		Admin admin = (Admin) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		int menuParentId=-1;
		if(request.getSession().getAttribute("menuParentId")!=null)
			menuParentId = ((Long)request.getSession().getAttribute("menuParentId")).intValue();
		List<Menu> menusParent = new ArrayList<Menu>();
		// 内置管理员
		if (admin.getId() == 1l) {
			// 获取所有导航
			menuVo.setMenuType(1l);
			menusParent = menuService.findParentMenu(menuVo)
					.getList();
		} else {
			// 获取父节点
			menusParent=menuService.findParentMenuByAdminId(admin.getId().intValue());
		}
		// 依据父获取所有子节点
		if(menuParentId<0){
			menuParentId=menusParent.get(0).getId().intValue();
		}
		List<Menu> menusChildren = menuService.findChildrenMenu(menuParentId,admin.getId());
		modelAndView.addObject("menusParent", menusParent);
		modelAndView.addObject("menusChildren", menusChildren);		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
