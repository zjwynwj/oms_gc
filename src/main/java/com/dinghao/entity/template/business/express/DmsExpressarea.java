package com.dinghao.entity.template.business.express;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class DmsExpressarea extends  BaseEntity {
    /**
	  * @Fields serialVersionUID : TODO（物流配送区域响应类）
	  */
	
	private static final long serialVersionUID = 1L;
	/** 物流编码 */
    private Long expressId;
    /** 省份id */
    private Long provId;
    /** 城市id */
    private Long cityId;
    /** 区县id */
    private Long countyId;
    /** 起步价 */
    private BigDecimal startPrice;
    /** 起步重量 */
    private BigDecimal startWeight;
    /** 续价 */
    private BigDecimal addPrice;
    /** 续重 */
    private BigDecimal addWeight;
    /** 是否到达(0-不到 1-到) */
    private Short isdelivery;
    /** 物流公司名称  */
    private String expressName;
    /** 省份名称 */
    private String provName;

    public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public Long getProvId() {
        return provId;
    }

    public void setProvId(Long provId) {
        this.provId = provId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(BigDecimal startWeight) {
        this.startWeight = startWeight;
    }

    public BigDecimal getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(BigDecimal addPrice) {
        this.addPrice = addPrice;
    }

    public BigDecimal getAddWeight() {
        return addWeight;
    }

    public void setAddWeight(BigDecimal addWeight) {
        this.addWeight = addWeight;
    }

    public Short getIsdelivery() {
        return isdelivery;
    }

    public void setIsdelivery(Short isdelivery) {
        this.isdelivery = isdelivery;
    }
}