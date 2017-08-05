package com.dinghao.entity.vo.template.business.purchase;

import java.io.Serializable;
import java.util.List;

public class PurOrderDataVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（采购订单数据请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private PurOrderVo purOrderVo;
	
	private List<PurOrderDetailVo> purOrderDetailVoList;

	public PurOrderVo getPurOrderVo() {
		return purOrderVo;
	}

	public void setPurOrderVo(PurOrderVo purOrderVo) {
		this.purOrderVo = purOrderVo;
	}

	public List<PurOrderDetailVo> getPurOrderDetailVoList() {
		return purOrderDetailVoList;
	}

	public void setPurOrderDetailVoList(List<PurOrderDetailVo> purOrderDetailVoList) {
		this.purOrderDetailVoList = purOrderDetailVoList;
	}
	
}
