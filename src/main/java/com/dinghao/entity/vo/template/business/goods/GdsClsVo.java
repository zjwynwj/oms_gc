package com.dinghao.entity.vo.template.business.goods;

import java.io.Serializable;
import java.util.Date;

public class GdsClsVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO (商品分类请求)
	  */
	
	private static final long serialVersionUID = -1208252126972299259L;

	private Long id;

    private String clsNo;

    private String clsPno;

    private Integer level;

    private String clsName;

    private Date createDate;

    private Long createBy;

    private Date modifyDate;

    private Long modifyBy;

    private String clsStatus;
    
    private Double taxRate;

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClsNo() {
		return clsNo;
	}

	public void setClsNo(String clsNo) {
		this.clsNo = clsNo;
	}

	public String getClsPno() {
		return clsPno;
	}

	public void setClsPno(String clsPno) {
		this.clsPno = clsPno;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getClsStatus() {
		return clsStatus;
	}

	public void setClsStatus(String clsStatus) {
		this.clsStatus = clsStatus;
	}
    
}
