package com.dinghao.action.template.business.purchase;



import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.business.purchase.PurchaseSuggest;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestVo;
import com.dinghao.exception.FolderNotFoundException;
import com.dinghao.service.template.business.purchase.PurSuggestService;
import com.dinghao.util.DYCKUtil;
import com.dinghao.util.DateUtil;

@Controller
@RequestMapping("/template")
public class SuggestAction extends BaseAction{
	@Autowired
	private PurSuggestService purSuggestService;
	/*
	@Autowired
	private PurchaseSuggestDao purchaseSuggestDao;
	@Autowired
	private PurSuggestGoodsDao purSuggestGoodsDao;
	*/
	
	@RequestMapping(value = "/suggest/turnSuggest.jhtml", method = RequestMethod.GET)
	public String purchaseSuggestMgr(HttpServletRequest request, ModelMap modelMap,
			PurSuggestVo eVo) throws FolderNotFoundException {
		try {
			DYCKUtil.BCProDec("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelMap.put("end_date", DateUtil.getDate(new Date()));
		modelMap.put("start_date", DateUtil.getDate(DateUtil.addDate( new Date(),-30))  );
		return "/template/front/ftls/purchase/suggest/suggest";
	}
	
	@RequestMapping(value = {"/suggest/queryList.jhtml"})
	public void queryList(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, PurSuggestVo eVo) throws Exception{
 		this.returnData(request, response, purSuggestService.queryList(eVo));
	}
	
	@RequestMapping(value = "/suggest/purSuggestAdd.jhtml", method = RequestMethod.GET)
	public String purchaseSuggestAdd(HttpServletRequest request, ModelMap modelMap,
			PurSuggestVo eVo) throws FolderNotFoundException {
			
		modelMap.put("busi_date", DateUtil.getDate(new Date()));
		modelMap.put("plan_date", DateUtil.getDate(DateUtil.addDate( new Date(),30))  );
		modelMap.put("busi_person","admin");
		
		modelMap.put("stockId", request.getParameter("stockId"));
		//单据编号 自动生成
		
		return "/template/front/ftls/purchase/suggest/add";
	}
	@RequestMapping(value = "/suggest/purSuggestEdit.jhtml", method = RequestMethod.GET)
	public String purchaseSuggestEdit(HttpServletRequest request, ModelMap modelMap,
			PurSuggestVo eVo) throws Exception {
	
		Long SuggestId = eVo.getId();//Long.parseLong(request.getParameterValues("id").toString());
	
		PurchaseSuggest purchaseSuggest = purSuggestService.queryById(SuggestId);
		
		//List<PurSuggestGoods> purSuggestGoodsList = purSuggestGoodsDao.selectBySuggestId(SuggestId);
			
		modelMap.put("suggest",purchaseSuggest);
		//modelMap.put("suggestGoods",purSuggestGoodsList);
	    return "/template/front/ftls/purchase/suggest/edit";
	}
	
	@RequestMapping(value = "/suggest/insertSuggest.jhtml", method = RequestMethod.POST)
	public void  insertSuggest(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,
			PurSuggestDataVo eVo) throws Exception {
	  	this.returnData(request, response, purSuggestService.add(eVo));	

	}
	@RequestMapping(value = "/suggest/saveSuggest.jhtml", method = RequestMethod.POST)
	public void  saveSuggest(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,
			PurSuggestDataVo eVo) throws Exception {
	  	this.returnData(request, response, purSuggestService.mod(eVo));	

	}
	//查询采购建议明细信息
	@RequestMapping(value = {"/suggest/querySuggestDetailList.jhtml"})
	public void querySuggestDetailList(HttpServletRequest request ,HttpServletResponse response ,
				ModelMap modelMap , PurchaseSuggest purchaseSuggest) throws Exception{
		
			this.returnData(request, response, purSuggestService.findDetailListBySuggestId(purchaseSuggest));
	}
	


}
