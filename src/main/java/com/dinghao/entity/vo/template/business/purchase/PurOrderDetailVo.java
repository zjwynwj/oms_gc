package com.dinghao.entity.vo.template.business.purchase;

import java.math.BigDecimal;

import com.dinghao.entity.vo.manage.PageVo;

public class PurOrderDetailVo extends PageVo<PurOrderDetailVo> {
    /**
	  * @Fields serialVersionUID : TODO（采购订单明细请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long purOrderId;

    private Long gdsId;

    private BigDecimal purPrice;

    private BigDecimal purAmount;

    private BigDecimal purMoney;

    private BigDecimal purInAmount;

    private BigDecimal purInMoney;

    private BigDecimal purOutAmount;

    private BigDecimal purOutMoney;

    private BigDecimal purRealAmount;

    private BigDecimal purRealMoney;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurOrderId() {
        return purOrderId;
    }

    public void setPurOrderId(Long purOrderId) {
        this.purOrderId = purOrderId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public BigDecimal getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(BigDecimal purPrice) {
        this.purPrice = purPrice;
    }

    public BigDecimal getPurAmount() {
        return purAmount;
    }

    public void setPurAmount(BigDecimal purAmount) {
        this.purAmount = purAmount;
    }

    public BigDecimal getPurMoney() {
        return purMoney;
    }

    public void setPurMoney(BigDecimal purMoney) {
        this.purMoney = purMoney;
    }

    public BigDecimal getPurInAmount() {
        return purInAmount;
    }

    public void setPurInAmount(BigDecimal purInAmount) {
        this.purInAmount = purInAmount;
    }

    public BigDecimal getPurInMoney() {
        return purInMoney;
    }

    public void setPurInMoney(BigDecimal purInMoney) {
        this.purInMoney = purInMoney;
    }

    public BigDecimal getPurOutAmount() {
        return purOutAmount;
    }

    public void setPurOutAmount(BigDecimal purOutAmount) {
        this.purOutAmount = purOutAmount;
    }

    public BigDecimal getPurOutMoney() {
        return purOutMoney;
    }

    public void setPurOutMoney(BigDecimal purOutMoney) {
        this.purOutMoney = purOutMoney;
    }

    public BigDecimal getPurRealAmount() {
        return purRealAmount;
    }

    public void setPurRealAmount(BigDecimal purRealAmount) {
        this.purRealAmount = purRealAmount;
    }

    public BigDecimal getPurRealMoney() {
        return purRealMoney;
    }

    public void setPurRealMoney(BigDecimal purRealMoney) {
        this.purRealMoney = purRealMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}