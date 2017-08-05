package com.dinghao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dinghao.constant.ConfigConstant;
import com.dinghao.service.ConfigService;
import com.dinghao.util.HttpUtils;
import com.dinghao.util.PropertyUtils;

/**
 * @author gucong
 * 
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		
		
		
		return true;
	
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		modelAndView.addObject("BASE_PATH", basePath);
		modelAndView.addObject("BASE_PATH_TEMPLATE", basePath+"/template");
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
		modelAndView
				.addObject(
						"TEMPLATE_BASE_PATH",
						basePath
								+ "/dinghao/template/"
								+ configService
										.getStringByKey(ConfigConstant.DINGHAO_TEMPLATE));
		modelAndView.addObject("dinghao_seo_title",
				configService.getStringByKey("dinghao_seo_title"));
		modelAndView.addObject("dinghao_seo_description",
				configService.getStringByKey("dinghao_seo_description"));
		modelAndView.addObject("config_front",
				PropertyUtils.getValue("dinghao.config_v"));
		MDC.put("ip", HttpUtils.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
