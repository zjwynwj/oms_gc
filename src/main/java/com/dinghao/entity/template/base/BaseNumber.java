package com.dinghao.entity.template.base;

import com.dinghao.entity.manage.BaseEntity;

public class BaseNumber extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（用于 生成单据号）
	  */
	
	private static final long serialVersionUID = 2308285420797475368L;

	//单据类型
    private String nuType;
    //单据名称
    private String nuName;
    //单据前缀
    private String nuPrefix;
    //是否使用年份规则
    private Boolean yearRule;
    //是否使用月份规则
    private Boolean montRule;
    //是否使用天规则
    private Boolean dayRule;
    //是否审核
    private Boolean isCheck;
    //编码位数
    private Long nuDigit;
    //当前编号
    private Long nuCurrent;
    //步长
    private Long nuStep;
    //编码样例
    private String nuDemo;

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
}