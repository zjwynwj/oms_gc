package com.dinghao.entity.vo.template.business.express;

import java.math.BigDecimal;

import com.dinghao.entity.vo.manage.PageVo;

public class DmsExpressareaVo extends PageVo<DmsExpressareaVo>{
    /**
	  * @Fields serialVersionUID : TODO（物流配送区域请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long expressId;

    private Long provId;
    
    private Long cityId;

    private Long countyId;

    private BigDecimal startPrice;

    private BigDecimal startWeight;

    private BigDecimal addPrice;

    private BigDecimal addWeight;

    private Short isdelivery;
    
    private String provName;

    public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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