package com.dinghao.action.template.business.wmswave;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.template.business.wmswave.WmsWave;
import com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo;
import com.dinghao.service.template.business.wmswave.IWmsWaveService;

@Controller
@RequestMapping("/template")
public class WmsWaveAction extends BaseAction{

	@Autowired
	private IWmsWaveService waveService;

	//跳转到拣货管理页面
	@RequestMapping(value = {"/wmswave/turnWmsWaveMgr.jhtml"})
	public String turnOrderAudit() throws Exception{
		return "/template/front/ftls/wmswave/wmswaveMgr";
	}
	
	//跳转到生成拣货单页面
	@RequestMapping(value = {"/wmswave/turnAddWmsWave.jhtml"})
	public String turnAddWmsWave(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
		modelMap.put("orderIds", waveVo.getOrderIds());
		return "/template/front/ftls/wmswave/addWmsWave";
	}
	
	//跳转到拣货详情页面
	@RequestMapping(value = {"/wmswave/turnWmsWaveDetail.jhtml"})
	public String turnWmsWaveDetail(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
		WmsWave wave = waveService.queryWmsWaveDetail(waveVo);
		modelMap.put("wave", wave);
		return "/template/front/ftls/wmswave/waveDetail";
	}
	
	//跳转到拣货页面
	@RequestMapping(value = {"/wmswave/turnWavePick.jhtml"})
	public String turnWavePick(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
		WmsWave wave = waveService.queryWmsWaveDetail(waveVo);
		modelMap.put("wave", wave);
		return "/template/front/ftls/wmswave/wavePick";
	}
		
	//生成拣货单
	@RequestMapping(value = {"/wmswave/generateWaveNo.jhtml"})
	public void generateWaveNo(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
        this.returnData(request, response, waveService.generateWaveNo(waveVo));
	}
	
	//查询grid的list数据
	@RequestMapping(value = {"/wmswave/queryWmsWaveGridList.jhtml"})
	public void queryWmsWaveGridList(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
        this.returnData(request, response, waveService.queryWmsWaveGridList(waveVo));
	}
	
	//保存拣货扫描完成的订单
	@RequestMapping(value = {"/wmswave/savePickScanWmsWave.jhtml"})
	public void savePickScanWmsWave(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
        this.returnData(request, response, waveService.savePickScanWmsWave(waveVo));
	}
	
	//查询拣货单的  打印数据
	@RequestMapping(value = {"/wmswave/queryWavePrintData.jhtml"})
	public void queryWavePrintData(HttpServletRequest request ,HttpServletResponse response ,ModelMap modelMap, WmsWaveVo waveVo) throws Exception{
        this.returnData(request, response, waveService.queryWavePrintData(waveVo));
	}
	
}
