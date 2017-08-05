package com.dinghao.action.template.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.template.business.express.PrintTemplate;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.entity.vo.template.business.express.PrintTemplateVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.service.template.business.express.PrintTemplateService;
import com.dinghao.service.template.warehouse.WarehousesService;
import com.dinghao.util.StringUtil;

/**
  * @ClassName: ShopAction
  * @Description: TODO  店铺管理
  * @author gucong 
  * @date 2016年1月19日 下午4:01:02
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class ShopAction extends BaseAction{
	@Autowired
	WarehousesService warehousesService;
	@Autowired
	DmsExpressService dmsExpressService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private PrintTemplateService printTemplateService;
	
	//跳转到单据号管理页面
	@RequestMapping(value = {"/shop/turnShopMgr.jhtml"})
	public String turnShopMgr() throws Exception{
		return "/template/front/ftls/base/ShopMgr";
	}
	
	//跳转到修改单据号页面
	@RequestMapping(value = {"/shop/turnModShop.jhtml"})
	public String turnModShop(HttpServletRequest request ,HttpServletResponse response,ModelMap modelMap, Shop shop) throws Exception{
		
		WarehouseVo warehouseVo = new WarehouseVo();
		warehouseVo.setOffset(0);
		warehouseVo.setRows(Integer.MAX_VALUE);
		List<Warehouse> warehouses = warehousesService.getWarehouses(warehouseVo);
		modelMap.put("warehouses", warehouses);
		
		DmsExpressVo expressVo  = new DmsExpressVo();
		expressVo.setOffset(0);
		expressVo.setRows(Integer.MAX_VALUE);
		expressVo.setActived("1");
		List<DmsExpress> expresslist =dmsExpressService.getAllDmsExpress(expressVo);
		modelMap.put("expresslist", expresslist);
		
		//获取打印模板
		PrintTemplateVo printTemplateVo  = new PrintTemplateVo();
		printTemplateVo.setOffset(0);
		printTemplateVo.setRows(Integer.MAX_VALUE);
		@SuppressWarnings("unchecked")
		PageVo<PrintTemplate>  pageVo = (PageVo<PrintTemplate> )printTemplateService.findPrintTemplate(printTemplateVo).getData();
		List<PrintTemplate> printTemplatelist = pageVo.getList();
		modelMap.put("printTemplatelist", printTemplatelist);
		
		modelMap.put("shop", shopService.queryById(shop.getId()));
		return "/template/front/ftls/base/modShop";
	}
	

	//修改店铺
	@RequestMapping(value = {"/shop/modShopMgr.jhtml"})
	public void modShopMgr(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, ShopVo shopVo) throws Exception{
		 this.returnData(request, response, shopService.mod(shopVo));
	}
	
	
	
	//分页查询单据号信息
	@RequestMapping(value = {"/shop/findForGrid.jhtml"})
	public void findForGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, ShopVo shopVo) throws Exception{
		
		if(null != shopVo.getName() && !"".equals(shopVo.getName())){
			if(StringUtil.getEncoding(shopVo.getName()) == "ISO-8859-1"){
				shopVo.setName(new String(shopVo.getName().getBytes("ISO-8859-1"), "UTF-8"));
			}
		}
		this.returnData(request, response, shopService.findForGrid(shopVo));
	}
}
