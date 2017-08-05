package com.dinghao.action.template.business.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.business.goods.GdsAttbVo;
import com.dinghao.service.template.business.goods.GdsAttbService;
import com.dinghao.util.StringUtil;

/**
  * @ClassName: GdsAttbAction
  * @Description: TODO   商品属性名称管理控制层
  * @author helong 
  * @date 2016年1月5日 下午4:03:08
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class GdsAttbAction extends BaseAction{
	@Autowired
	private GdsAttbService gdsAttbService;
	
	//跳转到商品属性管理页面
	@RequestMapping(value = {"/gdsAttb/gdsAttr.jhtml"})
	public String gdsclsMgr() {
		return "/template/front/ftls/goods/gdsAttr/gdsAttr";
	}
	
	//跳转到商品属性选择页面
	@RequestMapping(value = {"/gdsAttb/attrSelect.jhtml"})
	public String attrSelect(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAttbVo gdsAttbVo) throws Exception{
		if(null==gdsAttbVo.getAttbs() && "".equals(gdsAttbVo.getAttbs())){
			gdsAttbVo.setAttbs("");
		}else{
			if(StringUtil.getEncoding(gdsAttbVo.getAttbs()) == "ISO-8859-1"){
			  gdsAttbVo.setAttbs(new String(gdsAttbVo.getAttbs().getBytes("ISO-8859-1"), "UTF-8"));
			}
		}
		return "/template/front/ftls/goods/gdsAttr/attrSelect";
	}
	
	//查询商品属性
	@RequestMapping(value = {"/gdsAttb/queryAttbTable.jhtml"})
	public void queryAttbTable(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAttbVo gdsAttbVo) throws Exception {
        this.returnData(request, response, gdsAttbService.queryAttbForTable(gdsAttbVo));
	}
	
	//添加商品属性名称
	@RequestMapping(value = {"/gdsAttb/saveAttb.jhtml"})
	public void saveAttb(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAttbVo gdsAttbVo) throws Exception {
         this.returnData(request, response, gdsAttbService.addGdsAttb(gdsAttbVo));
	}
	
	//删除商品属性名称
	@RequestMapping(value = {"/gdsAttb/delAttb.jhtml"})
	public void delAttb(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAttbVo gdsAttbVo) throws Exception {
        this.returnData(request, response, gdsAttbService.delGdsAttb(gdsAttbVo));
	}
	
	//修改商品属性名称
	@RequestMapping(value = {"/gdsAttb/modAttb.jhtml"})
	public void modAttb(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAttbVo gdsAttbVo) throws Exception {
        this.returnData(request, response, gdsAttbService.modGdsAttb(gdsAttbVo));
	}
}
