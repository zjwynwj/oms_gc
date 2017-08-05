package com.dinghao.entity.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;

import com.dinghao.entity.manage.BaseEntity;

public class PurSuggestGoods extends  BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（采购订单明细请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long suggestId;

    private Long gdsId;

    private BigDecimal price;

    private BigDecimal num;

    private Long supplierId;

    private int status;

    
    //gdsNo gdsName gdsFormat  组合信息
    private String gdsShowInfo;
    
    private String attbs;
    private String skuOuterId;
    private String gdsNo;
    private String gdsName;
    private String gdsFormat;
       
    public String getGdsNo() {
		return gdsNo;
	}

	public void setGdsNo(String gdsNo) {
		this.gdsNo = gdsNo;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getGdsFormat() {
		return gdsFormat;
	}

	public void setGdsFormat(String gdsFormat) {
		this.gdsFormat = gdsFormat;
	}

	public String getSkuOuterId() {
		return skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	public String getAttbs() {
		return attbs;
	}

	public void setAttbs(String attbs) {
		this.attbs = attbs;
	}

	private String supplierName;
    
    
    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;
    
	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getGdsShowInfo() {
		return gdsShowInfo;
	}

	public void setGdsShowInfo(String gdsShowInfo) {
		this.gdsShowInfo = gdsShowInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSuggestId() {
		return suggestId;
	}

	public void setSuggestId(Long suggestId) {
		this.suggestId = suggestId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

   

   
}