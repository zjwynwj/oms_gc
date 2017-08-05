package com.dinghao.entity.template.business.purchase;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class PurOrderDetail extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（采购订单明细返回类）
	  */
	
	private static final long serialVersionUID = 1L;
	//采购订单id
    private Long purOrderId;
    //商品id
    private Long gdsId;
    //采购价格
    private BigDecimal purPrice;
    //采购数量
    private BigDecimal purAmount;
    //采购金额
    private BigDecimal purMoney;
    //入库数量
    private BigDecimal purInAmount;
    //入库金额
    private BigDecimal purInMoney;
    //出库数量
    private BigDecimal purOutAmount;
    //出库金额
    private BigDecimal purOutMoney;
    //实际入库数量
    private BigDecimal purRealAmount;
    //数据入库金额
    private BigDecimal purRealMoney;
    //备注
    private String remark;
    //商品名称
    private String gdsName;
    //商品属性
    private String attbs;
    //商品规格
    private String gdsFormat;
    private String skuOuterId;
    
    public String getSkuOuterId() {
		return skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	//gdsNo gdsName gdsFormat  组合信息
    private String gdsShowInfo;
    
    private String cal;
    
    public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getGdsFormat() {
		return gdsFormat;
	}

	public void setGdsFormat(String gdsFormat) {
		this.gdsFormat = gdsFormat;
	}

	public String getGdsShowInfo() {
		return gdsShowInfo;
	}

	public void setGdsShowInfo(String gdsShowInfo) {
		this.gdsShowInfo = gdsShowInfo;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getAttbs() {
		return attbs;
	}

	public void setAttbs(String attbs) {
		this.attbs = attbs;
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