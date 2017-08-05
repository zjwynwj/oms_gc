package com.dinghao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.util.HttpUtils;

/**
 * 
 * Ftl文件安全过滤器
 * 
 * @author Herbert
 * 
 */
public class FtlFilter implements Filter {

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.sendRedirect(HttpUtils.getBasePath(request) + "/404.htm");
		//ServletContext.getRequestDispatcher(String url);
		
		/*
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		servletRequest.setCharacterEncoding("UTF-8");
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		String url = req.getRequestURI();
		
		TemplateAdmin admin = (TemplateAdmin) request.getSession().getAttribute(
				SystemConstant.TEMPLATE_ADMIN);
		
		
		if (null == admin) {
		if (!url.isEmpty() && (url.endsWith("login.jhtml")  || url.endsWith("login.jsp") )) {
			chain.doFilter(servletRequest, servletResponse);
		} else {
		
			req.getRequestDispatcher(HttpUtils.getBasePath(request)  + "/login.jhtml").forward(req, res);
		}
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
		*/
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
