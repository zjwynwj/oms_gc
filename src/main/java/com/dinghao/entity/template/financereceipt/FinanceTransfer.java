/*
 * @ClassName:FinanceTransfer.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-03-03
 */
package com.dinghao.entity.template.financereceipt;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.dinghao.entity.manage.BaseEntity;

public class FinanceTransfer extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .收款单号
	 */
	private String recNo;

	/**
	 * .业务日期
	 */
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
	 * 收款账号账户名
	 */
	private String recAccountBankNO;

	/**
	 * .付款账号
	 */
	private String payAccount;

	/**
	 * .付款账号账户名
	 */
	private String payAccountBankNO;

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

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = StringUtils.isBlank(recNo) ? null : recNo.trim();
	}

	public String getRecAccountBankNO() {
		return recAccountBankNO;
	}

	public void setRecAccountBankNO(String recAccountBankNO) {
		this.recAccountBankNO = recAccountBankNO;
	}

	public String getPayAccountBankNO() {
		return payAccountBankNO;
	}

	public void setPayAccountBankNO(String payAccountBankNO) {
		this.payAccountBankNO = payAccountBankNO;
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