package com.dinghao.entity.template.business.goods;

import com.dinghao.entity.manage.BaseEntity;

public class GdsAttb extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（商品属性名称返回类）
	  */
	private static final long serialVersionUID = 816495665980969398L;
	//属性名称 
	private String attbName;
	//所属商品分类id
	private Long clsId;
	
	public Long getClsId() {
		return clsId;
	}

	public void setClsId(Long clsId) {
		this.clsId = clsId;
	}

	public String getAttbName() {
		return attbName;
	}
	
	public void setAttbName(String attbName) {
		this.attbName = attbName;
	}

}