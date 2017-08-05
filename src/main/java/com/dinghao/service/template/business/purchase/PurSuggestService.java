package com.dinghao.service.template.business.purchase;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.template.business.purchase.PurchaseSuggest;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestVo;

public interface PurSuggestService {

	CommResponse add(PurSuggestDataVo eVo) throws Exception;

	CommResponse del(PurSuggestVo eVo) throws Exception;

	CommResponse mod(PurSuggestDataVo eVo) throws Exception;

	PurchaseSuggest queryById(Long id) throws Exception;

	CommResponse queryList(PurSuggestVo eVo) throws Exception;
	
	CommResponse findDetailListBySuggestId(PurchaseSuggest purchaseSuggest) throws Exception;
	
	PurSuggestGoods  getSugGoodsById(Long id) throws Exception;
}