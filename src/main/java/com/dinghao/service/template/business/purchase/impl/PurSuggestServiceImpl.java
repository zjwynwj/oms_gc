package com.dinghao.service.template.business.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.purchase.PurSuggestGoodsDao;
import com.dinghao.dao.template.business.purchase.PurchaseSuggestDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.template.business.purchase.PurchaseSuggest;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestGoodsVo;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.purchase.PurSuggestService;

@Service
@Transactional
public class PurSuggestServiceImpl implements PurSuggestService {
	@Autowired
	private PurchaseSuggestDao purchaseSuggestDao;
	@Autowired
	private PurSuggestGoodsDao purSuggestGoodsDao;




	public CommResponse add(PurSuggestDataVo eVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		PurSuggestVo purSuggestVo = eVo.getPurSuggestVo();
		List<PurSuggestGoodsVo> detailList = eVo.getPurSuggestGoodsVoList();
		int count = purchaseSuggestDao.selectCountBySuggestNo(purSuggestVo.getSuggestNo());
		if(count > 0){
			throw new DHBizException(ExceptionConstant.ERR_DH030001);
		}
		purchaseSuggestDao.insertSelective(purSuggestVo);
		for(PurSuggestGoodsVo purSuggestGoodsVo : detailList){
			purSuggestGoodsVo.setSuggestId(purSuggestVo.getId());
			purSuggestGoodsDao.insertSelective(purSuggestGoodsVo);
		}
		
		return commResponse;	
	
	}



	public CommResponse del(PurSuggestVo eVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		purchaseSuggestDao.deleteByPrimaryKey(eVo.getId());
		return commResponse;
	}



	public CommResponse mod(PurSuggestDataVo eVo) throws Exception {
		//CommResponse commResponse = new CommResponse();
		//purchaseSuggestDao.updateByPrimaryKeySelective(eVo);
		
        CommResponse commResponse = new CommResponse();
		
		PurSuggestVo purSuggestVo = eVo.getPurSuggestVo();
		List<PurSuggestGoodsVo> detailList = eVo.getPurSuggestGoodsVoList();
		/*
		int count = purchaseSuggestDao.selectCountBySuggestNo(purSuggestVo.getSuggestNo());
		if(count > 0){
			throw new DHBizException(ExceptionConstant.ERR_DH030001);
		}
		*/
		purchaseSuggestDao.updateByPrimaryKeySelective(purSuggestVo);
		
		for(PurSuggestGoodsVo purSuggestGoodsVo : detailList){
			//purSuggestGoodsVo.setSuggestId(curid);
			if (purSuggestGoodsVo.getId()>0)
			{
				purSuggestGoodsDao.updateByPrimaryKeySelective(purSuggestGoodsVo);
			}
			else
			{
				purSuggestGoodsVo.setSuggestId(purSuggestVo.getId());
				purSuggestGoodsDao.insertSelective(purSuggestGoodsVo);
			}
		}
		
		commResponse.setData(eVo);
		commResponse.setSuccess(true);
		commResponse.setErrMsg("保持采购建议单成功！");
		return commResponse;
	}


	public PurchaseSuggest queryById(Long id) throws Exception {
		PurchaseSuggest purchaseSuggest = purchaseSuggestDao.selectByPrimaryKey(id);
		return purchaseSuggest;
	}
	
	public CommResponse findDetailListBySuggestId(PurchaseSuggest purchaseSuggest) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		
		List<PurSuggestGoods> detailList = purSuggestGoodsDao.selectBySuggestId(purchaseSuggest.getId());
		
		for(PurSuggestGoods detail : detailList){ 	
			if(null != detail.getAttbs() && !"".equals(detail.getAttbs())){
				String attbName[] = detail.getAttbs().split(";");
				String attbs ="";
				for(String attb : attbName){
					attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
				}
				attbs = attbs.substring(1, attbs.length());
				detail.setAttbs(attbs);
			}else{
				detail.setAttbs("");
			}
			if (detail.getSupplierName()==null){
				detail.setSupplierName("");
			}
		}
		
		commResponse.setData(detailList);
		
		commResponse.setSuccess(true);
		return commResponse;
	}




	public CommResponse queryList(PurSuggestVo eVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		PageVo<PurSuggestVo> pageVo = new PageVo<PurSuggestVo>();
		List<PurSuggestVo> list = purchaseSuggestDao.selectList(eVo);
		pageVo.setList(list);
		pageVo.setCount(purchaseSuggestDao.selectListCount(eVo));
		pageVo.setPageNum(eVo.getPageNum());
		pageVo.setRows(eVo.getRows());
		
		commResponse.setData(pageVo);		
		
		return commResponse;
	}
	
	public PurSuggestGoods  getSugGoodsById(Long id) throws Exception{
		
		PurSuggestGoods purSuggestGoods = purSuggestGoodsDao.selectByPrimaryKey(id);
		
		return purSuggestGoods;
	}
	
	
}
