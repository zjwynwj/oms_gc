package com.dinghao.entity.vo.template.business.purchase;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class PurOrderVo extends PageVo<PurOrderVo> {
    /**
	  * @Fields serialVersionUID : TODO（采购订单主表请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String purNo;

    private Long custId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date busiDate;

    private String purType;

    private BigDecimal payMoney;

    private String purOrderStatus;

    private String purImStatus;

    private String purPayStatus;

    private BigDecimal totalPayAmt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date dueDate;

    private String filePath;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;

    private String remark1;

    private String remark2;
    
    private String keyWord;

    public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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