package com.dinghao.entity.vo.template.base;

import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class BaseNumberVo extends PageVo<BaseNumberVo>{
    /**
	 * 单据号生成
	 */
	private static final long serialVersionUID = -9038517860796162952L;

	private Long id;

    private String nuType;

    private String nuName;

    private String nuPrefix;

    private Boolean yearRule;

    private Boolean montRule;

    private Boolean dayRule;

    private Boolean isCheck;

    private Long nuDigit;

    private Long nuCurrent;

    private Long nuStep;

    private String nuDemo;

    private String createBy;

    private Date createDate;

    private String modifyBy;

    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNuType() {
        return nuType;
    }

    public void setNuType(String nuType) {
        this.nuType = nuType == null ? null : nuType.trim();
    }

    public String getNuName() {
        return nuName;
    }

    public void setNuName(String nuName) {
        this.nuName = nuName == null ? null : nuName.trim();
    }

    public String getNuPrefix() {
        return nuPrefix;
    }

    public void setNuPrefix(String nuPrefix) {
        this.nuPrefix = nuPrefix == null ? null : nuPrefix.trim();
    }

    public Boolean getYearRule() {
        return yearRule;
    }

    public void setYearRule(Boolean yearRule) {
        this.yearRule = yearRule;
    }

    public Boolean getMontRule() {
        return montRule;
    }

    public void setMontRule(Boolean montRule) {
        this.montRule = montRule;
    }

    public Boolean getDayRule() {
        return dayRule;
    }

    public void setDayRule(Boolean dayRule) {
        this.dayRule = dayRule;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Long getNuDigit() {
        return nuDigit;
    }

    public void setNuDigit(Long nuDigit) {
        this.nuDigit = nuDigit;
    }

    public Long getNuCurrent() {
        return nuCurrent;
    }

    public void setNuCurrent(Long nuCurrent) {
        this.nuCurrent = nuCurrent;
    }

    public Long getNuStep() {
        return nuStep;
    }

    public void setNuStep(Long nuStep) {
        this.nuStep = nuStep;
    }

    public String getNuDemo() {
        return nuDemo;
    }

    public void setNuDemo(String nuDemo) {
        this.nuDemo = nuDemo == null ? null : nuDemo.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}