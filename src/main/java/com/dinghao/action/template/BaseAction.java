/*



 */
package com.dinghao.action.template;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dinghao.constant.SystemConstant;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.TemplateService;

/**
 * 
 * @author Herbert
 * 
 */
public class BaseAction {
	
	public static String CONTENT_TYPE_JPG = "image/jpeg";
	public static String CONTENT_TYPE_PJPEG = "image/pjpeg";
	public static String CONTENT_TYPE_X_PNG = "image/x-png";
	public static String CONTENT_TYPE_JPEG = "image/jpeg";
	public static String CONTENT_TYPE_BMP = "image/bmp";
	public static String CONTENT_TYPE_PNG = "image/png";
	public static String CONTENT_TYPE_TXT = "text/plain";
	public static String CONTENT_TYPE_DOC = "application/msword";
	public static String CONTENT_TYPE_PDF = "application/pdf";
	public static String FILE_PNG = "PNG";
	public static String FILE_JPEG = "JPEG";
	public static String FILE_JPG = "JPG";
	public static String FILE_GRF = "GRF";
	public static String FILE_BMP = "BMP";
	
	@Autowired
	protected TemplateService themeService;

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void returnData(HttpServletRequest request,
			HttpServletResponse response, CommResponse commResponse)
			throws Exception {
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(commResponse));
		out.close();
	}

	/**
	 * 从session中获得管理员的信息 (后期修改为相应的实体类)
	 * 
	 * @param request
	 * @return
	 */
	protected TemplateAdmin getAdmin(HttpServletRequest request) {
		TemplateAdmin admin = (TemplateAdmin) request.getSession().getAttribute(
				SystemConstant.TEMPLATE_ADMIN);
		//测试使用
		if (admin == null) {
			admin = new TemplateAdmin();
			admin.setId(2l);
		}
		return admin;
	}
	
	public String checkFileReasonable(MultipartFile file){
		 if(null==file){
			 throw new DHBizException("文件不存在");
		 }
		 
		 String errMsg = "";
		 List<String> contenTypeList = new ArrayList<String>();
		 contenTypeList.add(CONTENT_TYPE_JPG);
		 contenTypeList.add(CONTENT_TYPE_JPEG);
		 contenTypeList.add(CONTENT_TYPE_PNG);
		 contenTypeList.add(CONTENT_TYPE_BMP);
		 contenTypeList.add(CONTENT_TYPE_PJPEG);
		 contenTypeList.add(CONTENT_TYPE_X_PNG);
		 
		 List<String> endFileName = new ArrayList<String>();
		 endFileName.add(FILE_JPEG);
		 endFileName.add(FILE_JPG);
		 endFileName.add(FILE_PNG);
		 endFileName.add(FILE_BMP);
		 boolean isTrue = false;
		 for(String fileName : endFileName){
		 	 if(file.getOriginalFilename().toUpperCase().endsWith(fileName)) {
		 		 isTrue = true;
			 }
		 }
		 if(!isTrue){
			 errMsg = "上传文件不符合类型";
			 return errMsg;
		 }
		 isTrue = false;
		 for(String type : contenTypeList){
		 	 if(file.getContentType().equalsIgnoreCase(type)) {
		 		 isTrue = true;
			 }
		 }
		 if(!isTrue){
			 errMsg = "上传文件不符合类型";
			 return errMsg;
		 }
		 if(file.getSize() > 2*1024 * 1024) {
			 errMsg = "最大允许导入2M的文件";
		 }
		 return errMsg;
	 }
}
