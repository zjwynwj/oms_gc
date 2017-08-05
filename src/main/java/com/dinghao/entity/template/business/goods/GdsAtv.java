package com.dinghao.entity.template.business.goods;

import com.dinghao.entity.manage.BaseEntity;

public class GdsAtv extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（商品可选属性值返回类）
	  */
	
	private static final long serialVersionUID = -2141028261267724025L;
	//属性id	
    private Long attbId;
    //可选属性值
    private String attbValue;
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

	public Long getAttbId() {
		return attbId;
	}

	public void setAttbId(Long attbId) {
		this.attbId = attbId;
	}

	public String getAttbValue() {
		return attbValue;
	}

	public void setAttbValue(String attbValue) {
		this.attbValue = attbValue;
	}

}