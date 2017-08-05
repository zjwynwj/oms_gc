package com.dinghao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.vo.manage.menuvo.MenuVo;
import com.dinghao.service.manage.menu.MenuService;
import com.dinghao.service.manage.role.RoleService;
import com.dinghao.util.HttpUtils;

@Component
public class TemplateInterceptor extends HandlerInterceptorAdapter {
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
		
		TemplateAdmin admin = (TemplateAdmin) request.getSession().getAttribute(
				SystemConstant.TEMPLATE_ADMIN);
		String redirectUrl = request.getQueryString() != null ? request
				.getRequestURI() + "?" + request.getQueryString() : request
				.getRequestURI();
		if (admin == null) {
			// 请求的URL
	
			response.sendRedirect(HttpUtils.getBasePath(request)+ "/login.jhtml?requestUrlString=" + redirectUrl);
			//request.getRequestDispatcher(HttpUtils.getBasePath(request)  + "/login.jhtml?requestUrlString=" + redirectUrl).forward(request, response);
		
		
		} else {
			
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
