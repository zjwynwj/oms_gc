/*
* @ClassName:FinanceAccount.java
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: 
*-----------Zihan--鼎好科技 版权所有------------
* @date 2016-02-26
*/
package com.dinghao.entity.vo.template.base;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.dinghao.entity.vo.manage.PageVo;

public class FinanceAccountVo extends PageVo<FinanceAccountVo> {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
     * .主键
     */
    private Long id;

    /**
     * .用户id-暂不用
     */
    private Long userId;

    /**
     * .账户类型 1银行 2现金
     */
    private String type;

    /**
     * .银行名称
     */
    private String bankName;

    /**
     * .账号
     */
    private String bankNo;

    /**
     * .账户名称
     */
    private String bankAccount;

    /**
     * .期初余额
     */
    private BigDecimal amount;

    /**
     * .状态 0 停用 1 启用
     */
    private Integer status;

    /**
     * .默认账户
     */
    private Integer isdefault;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = StringUtils.isBlank(type)? null : type.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = StringUtils.isBlank(bankName)? null : bankName.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = StringUtils.isBlank(bankNo)? null : bankNo.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = StringUtils.isBlank(bankAccount)? null : bankAccount.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
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