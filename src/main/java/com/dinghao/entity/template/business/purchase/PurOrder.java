package com.dinghao.entity.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;

import com.dinghao.entity.manage.BaseEntity;

public class PurOrder extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（采购订单返回类）
	  */
	
	private static final long serialVersionUID = 1L;
	//采购编码
    private String purNo;
    //客户id
    private Long custId;
    //业务日期
    private Date busiDate;
    //采购类型
    private String purType;
    //订单金额
    private BigDecimal payMoney;
    //采购订单状态(1-新建 2-进行中 3-完成 4-作废)
    private String purOrderStatus;
    //入库状态(1-未入库 2-部分入库 3-入库完成)
    private String purImStatus;
    //付款状态(1-未付款 2-部分付款 3-付款完成)
    private String purPayStatus;
    //已付金额
    private BigDecimal totalPayAmt;
    //应付日期
    private Date dueDate;
    //文件路径	
    private String filePath;
    //备注
    private String remark;
    //预留字段1
    private String remark1;
    //预留字段2
    private String remark2;
    //预留字段3
    private String custName;

    public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPurNo() {
        return purNo;
    }

    public void setPurNo(String purNo) {
        this.purNo = purNo == null ? null : purNo.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Date getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    public String getPurType() {
        return purType;
    }

    public void setPurType(String purType) {
        this.purType = purType == null ? null : purType.trim();
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPurOrderStatus() {
        return purOrderStatus;
    }

    public void setPurOrderStatus(String purOrderStatus) {
        this.purOrderStatus = purOrderStatus == null ? null : purOrderStatus.trim();
    }

    public String getPurImStatus() {
        return purImStatus;
    }

    public void setPurImStatus(String purImStatus) {
        this.purImStatus = purImStatus == null ? null : purImStatus.trim();
    }

    public String getPurPayStatus() {
        return purPayStatus;
    }

    public void setPurPayStatus(String purPayStatus) {
        this.purPayStatus = purPayStatus == null ? null : purPayStatus.trim();
    }

    public BigDecimal getTotalPayAmt() {
        return totalPayAmt;
    }

    public void setTotalPayAmt(BigDecimal totalPayAmt) {
        this.totalPayAmt = totalPayAmt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}