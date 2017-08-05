package com.dinghao.entity.vo.template.business.purchase;

import java.io.Serializable;
import java.util.List;

public class PurSuggestDataVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（采购建议单数据请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private PurSuggestVo purSuggestVo;
	
	private List<PurSuggestGoodsVo> purSuggestGoodsVoList;

	public PurSuggestVo getPurSuggestVo() {
		return purSuggestVo;
	}

	public void setPurSuggestVo(PurSuggestVo purSuggestVo) {
		this.purSuggestVo = purSuggestVo;
	}

	public List<PurSuggestGoodsVo> getPurSuggestGoodsVoList() {
		return purSuggestGoodsVoList;
	}

	public void setPurSuggestGoodsVoList(List<PurSuggestGoodsVo> purSuggestGoodsVoList) {
		this.purSuggestGoodsVoList = purSuggestGoodsVoList;
	}


	
}
