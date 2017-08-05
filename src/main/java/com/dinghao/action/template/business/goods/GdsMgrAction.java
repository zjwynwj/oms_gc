package com.dinghao.action.template.business.goods;

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
import com.dinghao.entity.vo.template.business.goods.DownGoodsParamVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.goods.GdsInfoService;
import com.dinghao.util.StringUtil;

/**
  * @ClassName: GdsMgrAction
  * @Description: TODO  商品管理控制层
  * @author helong 
  * @date 2016年1月5日 下午4:08:53
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class GdsMgrAction extends BaseAction{
	@Autowired
	private GdsInfoService gdsService;

	//跳转到商品管理页面
	@RequestMapping(value = {"/gdsMgr/turnGdsMgr.jhtml"})
	public String gdsMgr() {
		return "/template/front/ftls/goods/gdsMgr/gdsMgr";
	}
	
	/**
	 * 
	* @Title: gdsMgr 
	* @Description: TODO(商品选择) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = {"/gdsMgr/get_turn_gdsmgr.jhtml"})
	public String getGdsMgr() {
		return "/template/front/ftls/goods/gdsMgr/getGdsMgr";
	}
	//跳转到商品添加页面
	@RequestMapping(value = {"/gdsMgr/turnAddGdsInfo.jhtml"})
	public String turnAddGdsInfo() {
		return "/template/front/ftls/goods/gdsMgr/addGdsInfo";
	}
	
	//跳转到商品修改页面
	@RequestMapping(value = {"/gdsMgr/turnModGdsInfo.jhtml"})
	public String turnModGdsInfo(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, GdsInfoVo gdsInfoVo) {
		return "/template/front/ftls/goods/gdsMgr/modGdsInfo";
	}
	
	//跳转到商品详情页面
	@RequestMapping(value = {"/gdsMgr/turnDetailGdsInfo.jhtml"})
	public String turnDetailGdsInfo(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, GdsInfoVo gdsInfoVo) {
		return "/template/front/ftls/goods/gdsMgr/detailGdsInfo";
	}
	
	//添加商品信息
	@RequestMapping(value = {"/gdsMgr/addGdsInfo.jhtml"})
	public void addGdsInfo(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
        this.returnData(request, response, gdsService.addGdsInfo(gdsInfoVo));
	}
	
	//修改商品信息
	@RequestMapping(value = {"/gdsMgr/modGdsInfo.jhtml"})
	public void modGdsInfo(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
        this.returnData(request, response, gdsService.modGdsInfo(gdsInfoVo));
	}
	
	//根据id查询商品信息
	@RequestMapping(value = {"/gdsMgr/queryGdsInfoById.jhtml"})
	public void queryGdsInfoById(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
        this.returnData(request, response, gdsService.queryGdsInfoById(gdsInfoVo));
	}
	
	//分页查询商品信息 
	@RequestMapping(value = {"/gdsMgr/findGdsInfoForGrid.jhtml"})
	public void findGdsInfoForGrid(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
		if(null != gdsInfoVo.getKeyWord() && !"".equals(gdsInfoVo.getKeyWord())){
			if(StringUtil.getEncoding(gdsInfoVo.getKeyWord()) == "ISO-8859-1"){
				gdsInfoVo.setKeyWord(new String(gdsInfoVo.getKeyWord().getBytes("ISO-8859-1"), "UTF-8"));
			}
		}
		this.returnData(request, response, gdsService.findGdsInfoListPage(gdsInfoVo));
	}
	
	//批量操作商品
	@RequestMapping(value = {"/gdsMgr/stockOperateGds.jhtml"})
	public void stockOperatGds(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
        this.returnData(request, response, gdsService.stockOperatGds(gdsInfoVo));
	}
	
	//下载商品
	@RequestMapping(value = {"/gdsMgr/downLoadGoods.jhtml"})
	public void downLoadGoods(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, DownGoodsParamVo downGoodsParamVo) throws Exception{
        this.returnData(request, response, gdsService.downLoadGoods(downGoodsParamVo));
	}
	
	//上传图片文件
	@RequestMapping(value = {"/gdsMgr/goodsImgUpLoadFile.jhtml"})
	public void goodsImgUpLoadFile(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, 
			DownGoodsParamVo downGoodsParamVo) throws Exception{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dinghao.properties");
		Properties p = new Properties();   
		try {   
		    p.load(inputStream);   
		} catch (IOException e1) {   
		    throw new DHBizException(ExceptionConstant.ERR_DH000001);
		} 
		String filePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/"+p.getProperty("dinghao.imgPath");
		
		String errMsg = ""; 
		String imgPath = "";
		errMsg = this.checkFileReasonable(file);
		if(!StringUtil.isEmpty(errMsg)){
			throw new DHBizException(errMsg);
		}
		
		String realPath = "D:/doctorImg";
		try {
         	//重置文件名
         	long time = System.currentTimeMillis();
         	String timeStr = String.valueOf(time);
         	String[] originalFileName = file.getOriginalFilename().split("\\.");
         	String fileName = timeStr+"."+originalFileName[1];
         	FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, fileName));
			//配置图片访问路径					
			//String ip = "http://localhost:8080/path";
			imgPath = p.getProperty("dinghao.imgPath")+"/"+fileName;
        } catch (IOException e) {
			e.printStackTrace();
		}  
		 
		CommResponse commResponse = new CommResponse();
		commResponse.setData(imgPath);
        this.returnData(request, response, commResponse);
	}

	//根据条件查询符合要求的商品
	@RequestMapping(value = {"/gdsMgr/selectGdsInfoGridListNoPage.jhtml"})
	public void selectGdsInfoGridListNoPage(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsInfoVo gdsInfoVo) throws Exception{
        this.returnData(request, response, gdsService.selectGdsInfoGridListNoPage(gdsInfoVo));
	}
}
