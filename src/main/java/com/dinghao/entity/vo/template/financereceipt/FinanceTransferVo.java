/*
 * @ClassName:FinanceTransfer.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-03-03
 */
package com.dinghao.entity.vo.template.financereceipt;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class FinanceTransferVo extends PageVo<FinanceTransferVo> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .主键
	 */
	private Long id;

	/**
	 * .收款单号
	 */
	private String recNo;

	/**
	 * .业务日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	 * .结算方式1银行转账 2现金
	 */
	private String finalType;

	/**
	 * .支付方式1转出支付 2转入支付
	 */
	private String payType;

	/**
	 * .金额
	 */
	private BigDecimal amount;

	/**
	 * .手续费
	 */
	private BigDecimal poundage;

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
	 * .创建人
	 */
	private Long createBy;

	/**
	 * .创建时间
	 */
	private Date createDate;

	/**
	 * .修改人员
	 */
	private Long modifyBy;

	/**
	 * .修改时间
	 */
	private Date modifyDate;

	/**
	 * .备用(数字型)
	 */
	private Integer remarks;

	/**
	 * .备用1
	 */
	private String remarks1;

	/**
	 * .备用2
	 */
	private String remarks2;

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

	public Date getEndDate() {
		return endDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = StringUtils.isBlank(recNo) ? null : recNo.trim();
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
		this.recAccount = StringUtils.isBlank(recAccount) ? null : recAccount
				.trim();
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = StringUtils.isBlank(payAccount) ? null : payAccount
				.trim();
	}

	public String getFinalType() {
		return finalType;
	}

	public void setFinalType(String finalType) {
		this.finalType = StringUtils.isBlank(finalType) ? null : finalType
				.trim();
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = StringUtils.isBlank(payType) ? null : payType.trim();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPoundage() {
		return poundage;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}

	public String getBusiPerson() {
		return busiPerson;
	}

	public void setBusiPerson(String busiPerson) {
		this.busiPerson = StringUtils.isBlank(busiPerson) ? null : busiPerson
				.trim();
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = StringUtils.isBlank(purpose) ? null : purpose.trim();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = StringUtils.isBlank(memo) ? null : memo.trim();
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

	public Integer getRemarks() {
		return remarks;
	}

	public void setRemarks(Integer remarks) {
		this.remarks = remarks;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = StringUtils.isBlank(remarks1) ? null : remarks1.trim();
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = StringUtils.isBlank(remarks2) ? null : remarks2.trim();
	}
}