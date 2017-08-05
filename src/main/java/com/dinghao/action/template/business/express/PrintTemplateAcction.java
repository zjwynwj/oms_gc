package com.dinghao.action.template.business.express;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dinghao.action.template.BaseAction;
import com.dinghao.constant.ExceptionConstant;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.express.PrintTemplateVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.express.PrintTemplateService;
import com.dinghao.util.StringUtil;

@Controller
@RequestMapping("/template")
public class PrintTemplateAcction  extends BaseAction{

	@Autowired
	private PrintTemplateService printTemplateService;
	
	//跳转到打印模板管理页面
	@RequestMapping(value = {"/printMgr/turnPrintTemplateMgr.jhtml"})
	public String turnExpressTemplate() throws Exception{
		return "/template/front/ftls/express/printTemplateMgr";
	}
	
	//跳转到打印模板添加页面
	@RequestMapping(value = {"/printMgr/turnPrintTemplateAdd.jhtml"})
	public String turnPrintTemplateAdd() throws Exception{
		return "/template/front/ftls/express/printTemplateAdd";
	}
	
	//跳转到打印模板修改页面
	@RequestMapping(value = {"/printMgr/turnPrintTemplateMod.jhtml"})
	public String turnPrintTemplateMod(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String id) throws Exception {
		modelMap.put("id", id);
		return "/template/front/ftls/express/printTemplateMod";
	}
	
	//跳转到打印模板预览页面
	@RequestMapping(value = {"/printMgr/turnPrintPreView.jhtml"})
	public String turnPrintPreView(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String id) throws Exception {
		modelMap.put("id", id);
		return "/template/front/ftls/express/printPreView";
	}
	
	//跳转到批量打印物流单页面
	@RequestMapping(value = {"/printMgr/turnBatchPrint.jhtml"})
	public String turnBatchPrint(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String orderIds , String printId,String orderNums) throws Exception{
		modelMap.put("orderIds", orderIds);
		modelMap.put("printId", printId);
		modelMap.put("orderNums", orderNums);
		return "/template/front/ftls/express/batchPrint";
	}
	
	//上传图片文件
	@RequestMapping(value = {"/printMgr/templateImgUpLoadFile.jhtml"})
	public void templateImgUpLoadFile(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap) throws Exception{
		String errMsg = ""; 
		String imgPath = "";
		errMsg = this.checkFileReasonable(file);
		if(!StringUtil.isEmpty(errMsg)){
			throw new DHBizException(errMsg);
		}
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dinghao.properties");
		Properties p = new Properties();   
		try {   
		    p.load(inputStream);   
		} catch (IOException e1) {   
		    throw new DHBizException(ExceptionConstant.ERR_DH000001);
		} 
		String filePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/"+p.getProperty("dinghao.imgPath");
		
		try {
         	//重置文件名
         	long time = System.currentTimeMillis();
         	String timeStr = String.valueOf(time);
         	String[] originalFileName = file.getOriginalFilename().split("\\.");
         	String fileName = timeStr+"."+originalFileName[1];
         	FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, fileName));
			//配置图片访问路径					
			imgPath = p.getProperty("dinghao.imgPath")+"/"+fileName;
        } catch (IOException e) {
			e.printStackTrace();
		}  
		 
		CommResponse commResponse = new CommResponse();
		commResponse.setData(imgPath);
        this.returnData(request, response, commResponse);
	}
	
	//添加打印模板
	@RequestMapping(value = {"/printMgr/addPrintTemplate.jhtml"})
	public void addPrintTemplate(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, PrintTemplateVo printTemplateVo) throws Exception {
         this.returnData(request, response, printTemplateService.addPrintTemplate(printTemplateVo));
	}
	
	//修改打印模板
	@RequestMapping(value = {"/printMgr/modPrintTemplate.jhtml"})
	public void modPrintTemplate(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, PrintTemplateVo printTemplateVo) throws Exception {
         this.returnData(request, response, printTemplateService.modPrintTemplate(printTemplateVo));
	}
	
	//查询打印模板
	@RequestMapping(value = {"/printMgr/queryPrintTemplate.jhtml"})
	public void queryPrintTemplate(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, PrintTemplateVo printTemplateVo) throws Exception {
         this.returnData(request, response, printTemplateService.queryPrintTemplate(printTemplateVo));
	}
	
	//分页查询打印模板
	@RequestMapping(value = {"/printMgr/findPrintTemplate.jhtml"})
	public void findPrintTemplate(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, PrintTemplateVo printTemplateVo) throws Exception {
		if(null != printTemplateVo.getKeyWord() && !"".equals(printTemplateVo.getKeyWord())){
			if(StringUtil.getEncoding(printTemplateVo.getKeyWord()) == "ISO-8859-1"){
				printTemplateVo.setKeyWord(new String(printTemplateVo.getKeyWord().getBytes("ISO-8859-1"), "UTF-8"));
			}
		}
        this.returnData(request, response, printTemplateService.findPrintTemplate(printTemplateVo));
	}
}
