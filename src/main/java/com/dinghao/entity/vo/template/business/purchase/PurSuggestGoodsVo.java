package com.dinghao.entity.vo.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class PurSuggestGoodsVo extends PageVo<PurSuggestGoodsVo> {
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