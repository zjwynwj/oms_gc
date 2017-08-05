package com.dinghao.action.template.business.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.vo.template.business.goods.GdsClsVo;
import com.dinghao.service.template.business.goods.GdsClsService;
import com.dinghao.util.BeanUtils;
/**
  * @ClassName: GdsClsAction
  * @Description: TODO  商品分类管理控制层
  * @author helong 
  * @date 2016年1月5日 下午4:07:03
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class GdsClsAction extends BaseAction{
	@Autowired
	private GdsClsService gdsClsService;

	//跳转到添加商品分类页面
	@RequestMapping(value = {"/gdscls/turnAddcls.jhtml"})
	public String gdsclsMgr(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception {
		GdsCls gdsCls = new GdsCls();
		gdsCls = gdsClsService.queryGdsClsById(gdsClsVo);
		BeanUtils.copyProperties(gdsClsVo,gdsCls);
		modelMap.put("gdsClsVo", gdsClsVo);
		return "/template/front/ftls/goods/gdscls/addGdsCls";
	}
	
	//跳转到修改商品分类页面
	@RequestMapping(value = {"/gdscls/turnModcls.jhtml"})
	public String turnModcls(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception {
		GdsCls gdsCls =  gdsClsService.queryGdsClsById(gdsClsVo);
		modelMap.put("gdsCls", gdsCls);
		return "/template/front/ftls/goods/gdscls/modGdsCls";
	}
	
	//跳转到选择商品分类页面
	@RequestMapping(value = {"/gdscls/turnGdsclsSelect.jhtml"})
	public String turnGdsclsSelect(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception {
		return "/template/front/ftls/goods/gdscls/gdsClsSelect";
	}
	
	//添加商品分类
	@RequestMapping(value = {"/gdscls/addGdsCls.jhtml"})
	public void addGdsCls(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception{
        this.returnData(request, response, gdsClsService.addGdsCls(gdsClsVo));
	}
	
	//修改商品分类
	@RequestMapping(value = {"/gdscls/modGdsCls.jhtml"})
	public void modGdsCls(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception{
        this.returnData(request, response, gdsClsService.modGdsCls(gdsClsVo));
	}
	
	//删除商品分类
	@RequestMapping(value = {"/gdscls/delGdsCls.jhtml"})
	public void delGdsCls(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception{
        this.returnData(request, response, gdsClsService.delGdsCls(gdsClsVo));
	}
	
	//查询商品分类  列表
	@RequestMapping(value = {"/gdscls/queryGdsClsList.jhtml"})
	public void queryGdsClsList(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception{
        this.returnData(request, response, gdsClsService.queryGdsClsList(gdsClsVo));
	}
	//查询商品次级分类  列表
	@RequestMapping(value = {"/gdscls/findGdsClsListPage.jhtml"})
	public void findGdsClsListPage(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, GdsClsVo gdsClsVo) throws Exception{
	        this.returnData(request, response, gdsClsService.findGdsClsListPage(gdsClsVo));
    }
	
	
}
