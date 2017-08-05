package com.dinghao.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dinghao.constant.ExceptionConstant;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.exception.DHBizException;

/**
  * @ClassName: ControlExceptionHandler
  * @Description: TODO  统一异常处理器
  * @author helong 
  * @date 2015年11月3日 下午3:19:32
  * @version V1.0
  *
  */
public class ControlExceptionHandler implements HandlerExceptionResolver {
	/** 日志记录  */
	private static final Log logger = LogFactory
			.getLog(ControlExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		String errMsg = ExceptionConstant.ERR_DH000001;  //网络繁忙，请稍后再试!
		if(e instanceof DHBizException) {
			errMsg = e.getMessage();
			logger.error("<捕捉到控制层的异常>", e);
		}else {
    		logger.error("<捕捉到控制层的异常>平台未规划的运行时异常:", e);
		}
		ModelAndView modelAndView = null;
    	//ajax请求响应头会有x-requested-with
    	if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
    		modelAndView = this.executeAjax(response, errMsg);
    	}else {
    		modelAndView = this.executeForm(request, response, errMsg);
    	}
    	return modelAndView;
	}
	
	//执行ajax异常
	private ModelAndView executeAjax(HttpServletResponse resp, String errMsg) {
		CommResponse commResponse = new CommResponse();
		commResponse.setSuccess(false);
		commResponse.setErrCode("999999");
		commResponse.setErrMsg(errMsg);
		ModelAndView modelAndView = new ModelAndView();
		resp.setContentType("text/json; charset=UTF-8");  
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        out.print(JSON.toJSONString(commResponse)); 
        out.close();
		return modelAndView;
	}
	
	//执行页面异常
	private ModelAndView executeForm(HttpServletRequest req, HttpServletResponse resp, String errMsg) {
		CommResponse commResponse = new CommResponse();
		commResponse.setSuccess(false);
		commResponse.setErrCode("999999");
		commResponse.setErrMsg(errMsg);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/template/front/errMsg");
		modelAndView.addObject("commResponse", commResponse);
		return modelAndView;
	}
}
