/*



 */

package com.dinghao.action.manage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.manage.admin.Admin;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.exception.ValidateException;
import com.dinghao.service.ConfigService;
import com.dinghao.service.manage.AdminService;

/**
 * @author 所有action的父类
 * 
 */
@Controller
public class ManageBaseAction {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ConfigService configService;
	@Autowired
	protected AdminService adminService;


	/**
	 * 参数校验
	 * 
	 * @param json
	 *            json数据Bean
	 * @throws ValidateException
	 */
	protected <T> void validate(JsonVo<T> json) throws ValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ValidateException(json.getErrors().values().toString());
		} else {
			json.setResult(true);
		}
	}

	/**
	 * 从session中获得管理员的信息
	 * 
	 * @param request
	 * @return
	 */
	protected Admin getAdmin(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		return admin;
	}
	
	public void returnData(HttpServletRequest request,
			HttpServletResponse response, CommResponse commResponse)
			throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(commResponse));
		out.close();
	}

}
