package com.dinghao.service.template.locstock.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.dao.template.wmswtocklog.WmsStockLogDao;
import com.dinghao.entity.htobject.resultEntityProduct;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.wmswtocklog.WmsStockLogVo;
import com.dinghao.service.template.base.ShopService;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.service.template.wmstocklog.WmsStockLogService;
import com.dinghao.util.WSCUtil;

@Service
public class LocStockServiceImp implements LocStockService {
	@Autowired
	LocStockDao locStockDao;
	@Autowired
	WmsStockLogDao wmsStockLogDao;
	@Autowired
	WmsStockLogService wmsStockLogService;
	@Autowired
	ShopService shopService;

	@Override
	public CommResponse selectByStatement(LocStockVo locStockVo) {
		CommResponse commResponse = new CommResponse();
		
		PageVo<LocStockVo> pageVo = new PageVo<LocStockVo>();
		pageVo.setRows(locStockVo.getRows());
		pageVo.setList(locStockDao.selectByStatement(locStockVo));
		pageVo.setCount(locStockDao.selectByStatementCount(locStockVo));
		commResponse.setData(pageVo);
		
		
		
		return commResponse;
	}

	@Override
	public int updateByPrimaryKeySelective(LocStockVo locStockVo) {
		WmsStockLogVo wmsStockLogVo = new WmsStockLogVo();
		wmsStockLogVo.setGdsId(locStockVo.getGdsId().intValue());
		wmsStockLogVo.setStockId(locStockVo.getStockId().intValue());
		wmsStockLogVo.setLocId(locStockVo.getLocId().intValue());
		// 操作日志表(可能会失败,待完善)
		wmsStockLogService.SaveWmsStockLog(wmsStockLogVo,
				locStockVo.getTotalQty(), "结束盘点", 0);
		return locStockDao.updateByPrimaryKeySelective(locStockVo);
	}

	@Override
	public CommResponse selectByStatementDetail(LocStockVo locStockVo) throws Exception {
		
		CommResponse commResponse = new CommResponse();
		PageVo<LocStockVo> pageVo = new PageVo<LocStockVo>();
		pageVo.setRows(locStockVo.getRows());
		pageVo.setList(locStockDao.selectByStatementDetail(locStockVo));
		pageVo.setCount(locStockDao.selectByStatementDetailCount(locStockVo));
	
		commResponse.setData(pageVo);
		return commResponse;
	}
	@Override
	public CommResponse queryLowerStockList(LocStockVo locStockVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		
		locStockVo.setRows(Integer.MAX_VALUE);
		locStockVo.setMinQty(-1);
		/*
		PageVo<LocStockVo> pageVo = new PageVo<LocStockVo>();
		pageVo.setRows(Integer.MAX_VALUE);
		pageVo.setList(locStockDao.selectByStatement(locStockVo));
		pageVo.setCount(locStockDao.selectByStatementCount(locStockVo));
		*/
		//将返回的只设置为采购建议明细
		List<LocStockVo> locList = locStockDao.selectByStatement(locStockVo);
		List<PurSuggestGoods>  mGoodsList = new ArrayList<PurSuggestGoods>() ;
		
		for(LocStockVo item:locList ){
			PurSuggestGoods mGood = new PurSuggestGoods();
			mGood.setGdsId(item.getGdsId());
			mGood.setGdsShowInfo(item.getGdsNo() +" " +  item.getGdsName() + " "+item.getGdsFormat());
			
			if(null != item.getAttbs() && !"".equals(item.getAttbs())){
				String attbName[] = item.getAttbs().split(";");
				String attbs ="";
				for(String attb : attbName){
					attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
				}
				attbs = attbs.substring(1, attbs.length());
				mGood.setAttbs(attbs);
			}else{
				mGood.setAttbs("");
			}
			
			
			
			Long minQ = item.getGdsLowAmount()==null?0:item.getGdsLowAmount();
			Long total = item.getTotalQty()==null?0:item.getTotalQty();
				
			mGood.setNum( new BigDecimal(minQ - total));
			mGood.setPrice(BigDecimal.valueOf(100.0));
			mGood.setSupplierId(1L);
		
			mGoodsList.add(mGood);
		}
		commResponse.setData(mGoodsList);
		commResponse.setSuccess(true);
		return commResponse;
	}
	@Override
	public JqGridVo<LocStockVo> selectByStatementDetailSelect2(LocStockVo locStockVo) {
		JqGridVo<LocStockVo> jqGridVo = new JqGridVo<LocStockVo>();
		int count = locStockDao.selectByStatementDetailCount(locStockVo);
		if (count < 1) {
			jqGridVo.setList(locStockDao
					.selectByStatementDetailSelect2(locStockVo));
		} else {
			jqGridVo.setList(locStockDao.selectByStatementDetail(locStockVo));
		}

		return jqGridVo;
	}

	/**
	 * i : 1入库,2出库
	 */ 	 	
	@Override
	public int saveLocStock(LocStockVo locStockVo, int i) {
		LocStockVo filterLocStockVo = new LocStockVo();
		filterLocStockVo.setStockId(locStockVo.getStockId() );
		filterLocStockVo.setLocId(locStockVo.getLocId());
		filterLocStockVo.setGdsId(locStockVo.getGdsId());
		List<LocStockVo> locStockList = locStockDao.selectLocStockByParam(filterLocStockVo);
		
		// 添加日志
		WmsStockLogVo wmsStockLogVo = new WmsStockLogVo();
		wmsStockLogVo.setGdsId(locStockVo.getGdsId().intValue());
		wmsStockLogVo.setStockId(locStockVo.getStockId().intValue());
		wmsStockLogVo.setLocId(locStockVo.getLocId().intValue());
		if (locStockList.size() == 0) {// 没有数据(插入数据)
			locStockDao.insertSelective(locStockVo);
		} else if (locStockList.size() == 1) {
			// 出入库数量
			long amount = locStockVo.getTotalQty();
			BigDecimal inAmount = locStockVo.getInAmount();
			BigDecimal outAmount = locStockVo.getOutAmount();
			
			ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
			//BeanUtils.copyProperties(locStockVo, locStockList.get(0));
			locStockVo = locStockList.get(0);
			Long totalQty = locStockVo.getTotalQty()==null?0l:locStockVo.getTotalQty();
			BigDecimal cur_inAmount = locStockVo.getInAmount();
			BigDecimal cur_outAmount = locStockVo.getOutAmount();
				
			if(i==1){
				locStockVo.setTotalQty(totalQty+amount);
				locStockVo.setInAmount(cur_inAmount.add(inAmount));
			}else if(i==2){
				locStockVo.setTotalQty(totalQty-amount);
				locStockVo.setOutAmount(cur_outAmount.add(outAmount));
			}
			locStockDao.updateByPrimaryKeySelective(locStockVo);
		}
		wmsStockLogService.SaveWmsStockLog(wmsStockLogVo,
				locStockVo.getTotalQty(), "出入库操作", i);
		return 0;
	}
	
	public CommResponse synGoodsQty(LocStockVo locStockVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		commResponse.setSuccess(true);
		commResponse.setErrMsg("库存同步成功");
		
		locStockVo.setRows(Integer.MAX_VALUE);
		List<LocStockVo> locList = locStockDao.selectByStatement(locStockVo);
		
		ShopVo shopVo = new ShopVo();
		shopVo.setPlanType("WSC");
		List<Shop> shopList = shopService.getShops(shopVo);
		
		String shopNick = "" ;
		if(null !=shopList)
		{
			shopNick = shopList.get(0).getNickname();
		}
		for(LocStockVo item:locList ){
			String gdsNo = item.getGdsNo();
	
			String skuNo = item.getSkuOuterId();
			Long qty = item.getTotalQty();
			resultEntityProduct ret = WSCUtil.synGoodsQty(shopNick, gdsNo, skuNo, qty);
			if (!ret.isSuccess()){
				commResponse.setSuccess(false);
				commResponse.setErrMsg("商品编码："+gdsNo + "规格代码:" + skuNo + " "+ret.getMsg());
				continue;
			}
			
		}
		
		return commResponse;
	}

	
	@Override
	public int setLocStock(LocStockVo locStockVo) {
		LocStockVo filterLocStockVo = new LocStockVo();
		filterLocStockVo.setStockId(locStockVo.getStockId() );
		filterLocStockVo.setLocId(locStockVo.getLocId());
		filterLocStockVo.setGdsId(locStockVo.getGdsId());
		List<LocStockVo> locStockList = locStockDao.selectLocStockByParam(filterLocStockVo);
		
		// 添加日志
		WmsStockLogVo wmsStockLogVo = new WmsStockLogVo();
		wmsStockLogVo.setGdsId(locStockVo.getGdsId().intValue());
		wmsStockLogVo.setStockId(locStockVo.getStockId().intValue());
		wmsStockLogVo.setLocId(locStockVo.getLocId().intValue());
		if (locStockList.size() == 0) {// 没有数据(插入数据)
			locStockDao.insertSelective(locStockVo);
		} else if (locStockList.size() == 1) {
			// 出入库数量
			long amount = locStockVo.getTotalQty();
			BigDecimal inAmount = locStockVo.getInAmount();
			BigDecimal outAmount = locStockVo.getOutAmount();
			
			ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
			//BeanUtils.copyProperties(locStockVo, locStockList.get(0));
			locStockVo = locStockList.get(0);
			Long totalQty = locStockVo.getTotalQty()==null?0l:locStockVo.getTotalQty();
			
			BigDecimal cur_inAmount = locStockVo.getInAmount();
			BigDecimal cur_outAmount = locStockVo.getOutAmount();
				

			locStockVo.setTotalQty(amount);
			locStockVo.setOutAmount(inAmount);
	
			locStockDao.updateByPrimaryKeySelective(locStockVo);
		}
		wmsStockLogService.SaveWmsStockLog(wmsStockLogVo,
				locStockVo.getTotalQty(), "供应商同步库库存", 1);
		return 0;
	}
}
