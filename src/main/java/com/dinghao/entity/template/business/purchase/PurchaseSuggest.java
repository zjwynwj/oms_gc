package com.dinghao.entity.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;

import com.dinghao.entity.manage.BaseEntity;

public class PurchaseSuggest  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -970130164819129102L;

	private Long id;

    private String suggestNo;

    private Date busiDate;

    private Date planDate;

    private String status;

    private String busiPerson;

    private BigDecimal amount;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuggestNo() {
        return suggestNo;
    }

    public void setSuggestNo(String suggestNo) {
        this.suggestNo = suggestNo == null ? null : suggestNo.trim();
    }

    public Date getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBusiPerson() {
        return busiPerson;
    }

    public void setBusiPerson(String busiPerson) {
        this.busiPerson = busiPerson == null ? null : busiPerson.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}