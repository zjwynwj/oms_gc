/*



 */
package com.dinghao.action.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.dinghao.constant.SystemConstant;
import com.dinghao.util.HttpUtils;
import com.dinghao.util.MediaUtils;
import com.dinghao.util.PropertyUtils;

@Controller
@RequestMapping("/manage")
public class ManageUEditorAction extends ManageBaseAction {

	@ResponseBody
	@RequestMapping(value = "/ueditor.htm")
	public String config(@RequestParam(value = "action") String action,
			HttpServletResponse response, HttpServletRequest request) {
//		response.setContentType("text/html;charset=UTF-8");
		// String root = HttpUtils.getBasePath(request);
		String root = PropertyUtils.getRoot()
				+ java.io.File.separatorChar;
		// root = root.replace("\\", "/");
		// if (!root.endsWith("/")) {
		// root += "/";
		// }
		logger.info("ueditor root:"+root);
		return new ActionEnter(request, root).exec();
	}

	/**
	 * @see imageManager.jsp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/photo/manager.htm", method = RequestMethod.POST)
	public String photoManager(HttpServletRequest request) {
		String photoUploadPath = HttpUtils.getRealPath()
				+ SystemConstant.UPLOAD_FOLDER;
		List<java.io.File> fileList = MediaUtils.getFiles(
				photoUploadPath, new ArrayList<java.io.File>(),
				MediaUtils.PHOTO_TYPE);
		String imgStr = "";
		for (java.io.File file : fileList) {
			imgStr += file.getPath().replace(
					HttpUtils.getRealPath(), "")
					+ "ue_separate_ue";
		}
		if (imgStr != "") {
			imgStr = imgStr.substring(0,
					imgStr.lastIndexOf("ue_separate_ue"))
					.replace(java.io.File.separator, "/")
					.trim();
		}
		return imgStr;
	}
}
