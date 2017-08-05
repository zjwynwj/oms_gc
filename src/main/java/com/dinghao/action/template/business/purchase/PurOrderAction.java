package com.dinghao.action.template.business.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
/**
  * @ClassName: PurOrderAction
  * @Description: TODO  采购订单管理action层
  * @author helong 
  * @date 2016年1月7日 上午11:19:04
  * @version V1.0
  *
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;
import com.dinghao.service.template.business.purchase.PurOrderService;
import com.dinghao.service.template.business.purchase.PurSuggestService;
@Controller
@RequestMapping("/template")
public class PurOrderAction extends BaseAction{

	@Autowired
	private PurOrderService purOrderService;
	@Autowired
	private PurSuggestService purSuggestService;

	//跳转到采购订单管理页面
	@RequestMapping(value = {"/purOrder/turnPurMgr.jhtml"})
	public String turnPurMgr() {
		return "/template/front/ftls/purchase/purOrder/purMgr";
	}
	
	//跳转到新增采购订单页面
	@RequestMapping(value = {"/purOrder/turnAddPurOrder.jhtml"})
	public String turnAddPurOrder() {
		return "/template/front/ftls/purchase/purOrder/addPurOrder";
	}
	//通过采购建议生成采购订单页面
	@RequestMapping(value = {"/purOrder/generatePurOrder.jhtml"})
	public String generatePurOrder(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String suggestGoodsids) throws Exception {
		modelMap.put("suggestGoodsids", suggestGoodsids);
		//根据suggestGoodsids 获取第一个供应商
		String ids[] = suggestGoodsids.split(",");
		Long goodsId = Long.parseLong(ids[0]);
		PurSuggestGoods  purSuggestGoods  = purSuggestService.getSugGoodsById(goodsId);
		modelMap.put("purSuggestGoods", purSuggestGoods);
		return "/template/front/ftls/purchase/purOrder/addPurOrder";
	}
	
	//跳转到修改采购订单页面
	@RequestMapping(value = {"/purOrder/turnModPurOrder.jhtml"})
	public String turnModPurOrder(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , String id) {
		modelMap.put("id", id);
		return "/template/front/ftls/purchase/purOrder/modPurOrder";
	}
	
	//添加采购订单信息
	@RequestMapping(value = {"/purOrder/addPurOrder.jhtml"})
	public void addPurOrder(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderDataVo purOrderDataVo) throws Exception{
		this.returnData(request, response, purOrderService.addPurOrder(purOrderDataVo));
	}
	
	//修改采购订单信息
	@RequestMapping(value = {"/purOrder/modPurOrder.jhtml"})
	public void modPurOrder(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderDataVo purOrderDataVo) throws Exception{
	   
	    
		this.returnData(request, response, purOrderService.modPurOrder(purOrderDataVo));
	}
	
	//查询采购订单信息
	@RequestMapping(value = {"/purOrder/queryPurOrderById.jhtml"})
	public void queryPurOrderById(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderVo purOrderVo) throws Exception{
		this.returnData(request, response, purOrderService.queryPurOrderById(purOrderVo));
	}
	
	//查询采购订单明细信息
	@RequestMapping(value = {"/purOrder/queryPurOrderDetailList.jhtml"})
	public void queryPurOrderDetailList(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderVo purOrderVo) throws Exception{
		this.returnData(request, response, purOrderService.findPurOrderDetailListByPurOrderId(purOrderVo));
	}
	
	//查询引入采购建议单明细信息
	@RequestMapping(value = {"/purOrder/queryDetailListBySugIds.jhtml"})
	public void queryDetailListBySugIds(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderVo purOrderVo,String suggestGoodsids) throws Exception{
		
		this.returnData(request, response, purOrderService.getDetailListBySugIds(suggestGoodsids));
	}

	//分页查询采购订单信息
	@RequestMapping(value = {"/purOrder/findPurOrderForGrid.jhtml"})
	public void findPurOrderForGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , PurOrderVo purOrderVo) throws Exception{
		this.returnData(request, response, purOrderService.findPurOrderForGrid(purOrderVo));
	}

	//保存入库或出库单
	@RequestMapping(value = {"/purOrder/savePurOrderReceiptOrOutBound.jhtml"})
	public void savePurOrderReceiptOrOutBound(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap , ReceiptVo receiptVo) throws Exception{
		this.returnData(request, response, purOrderService.savePurOrderReceiptOrOutBound(receiptVo));
	}
}
