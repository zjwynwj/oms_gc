/*
* @ClassName:FinancePayment.java
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: 
*-----------Zihan--鼎好科技 版权所有------------
* @date 2016-02-24
*/
package com.dinghao.entity.vo.template.financereceipt;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class FinancePaymentVo extends PageVo<FinancePaymentVo> {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
     * .主键
     */
    private Long id;

    /**
     * .付款单号
     */
    private String payNo;

    /**
     * .业务日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date busiDate;

    /**
     * .往来单位
     */
    private Long custId;

    /**
     * .收款账号
     */
    private String recAccount;

    /**
     * .付款账号
     */
    private String payAccount;

    /**
     * .结算方式
     */
    private String finalType;

    /**
     * .金额
     */
    private BigDecimal amount;

    /**
     * .业务员
     */
    private String busiPerson;

    /**
     * .用途
     */
    private String purpose;

    /**
     * .备注
     */
    private String memo;

    /**
     * .是否确认，确认后不能修改
     */
    private Integer status;

    /**
     * .
     */
    private Long createBy;

    /**
     * .
     */
    private Date createDate;

    /**
     * .
     */
    private Long modifyBy;

    /**
     * .
     */
    private Date modifyDate;

    /**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;

	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = StringUtils.isBlank(payNo)? null : payNo.trim();
    }

    public Date getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRecAccount() {
        return recAccount;
    }

    public void setRecAccount(String recAccount) {
        this.recAccount = StringUtils.isBlank(recAccount)? null : recAccount.trim();
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = StringUtils.isBlank(payAccount)? null : payAccount.trim();
    }

    public String getFinalType() {
        return finalType;
    }

    public void setFinalType(String finalType) {
        this.finalType = StringUtils.isBlank(finalType)? null : finalType.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBusiPerson() {
        return busiPerson;
    }

    public void setBusiPerson(String busiPerson) {
        this.busiPerson = StringUtils.isBlank(busiPerson)? null : busiPerson.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = StringUtils.isBlank(purpose)? null : purpose.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = StringUtils.isBlank(memo)? null : memo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}