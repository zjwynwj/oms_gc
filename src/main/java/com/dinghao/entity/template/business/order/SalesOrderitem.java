package com.dinghao.entity.template.business.order;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class SalesOrderitem extends BaseEntity{

    /**
	  * @Fields serialVersionUID : TODO（订单明细返回类）
	  */
	
	private static final long serialVersionUID = 1L;

	private String oid;

    private String orderId;

    private String tbId;

    private String outerIid;

    private String outerSkuId;

    private Long gdsId;

    private Long qty;

    private BigDecimal salsePrice;

    private BigDecimal totsalMoney;

    private Boolean isgift;

    private String memo;

    private Long numiid;

    private String topSkuid;

    private String refundStatus;

    private String remark1;

    private String remark2;

    private String remark3;
    
    private String status;
    
    private String platStatus;
    
    private String platRefundStatus;
    
    private String gdsName;
    
    private String gdsFormat;
    
    private String gdsNo;
    
    private Long locId;
    
    private String gdsInfo;
    
    private String stockName;
    
    private String locNo;
    
    private String expressName;
    
    private String expcode;

    private String orderNum;
    
    private Integer waveId;
    
    private String skuOuterId;
    
    private BigDecimal gdsStandWeight;
    
    public BigDecimal getGdsStandWeight() {
		return gdsStandWeight;
	}

	public void setGdsStandWeight(BigDecimal gdsStandWeight) {
		this.gdsStandWeight = gdsStandWeight;
	}

	public String getSkuOuterId() {
		return skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	public Integer getWaveId() {
		return waveId;
	}

	public void setWaveId(Integer waveId) {
		this.waveId = waveId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getGdsInfo() {
		return gdsInfo;
	}

	public void setGdsInfo(String gdsInfo) {
		this.gdsInfo = gdsInfo;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getLocNo() {
		return locNo;
	}

	public void setLocNo(String locNo) {
		this.locNo = locNo;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpcode() {
		return expcode;
	}

	public void setExpcode(String expcode) {
		this.expcode = expcode;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public String getGdsNo() {
		return gdsNo;
	}

	public void setGdsNo(String gdsNo) {
		this.gdsNo = gdsNo;
	}

    public String getGdsFormat() {
		return gdsFormat;
	}

	public void setGdsFormat(String gdsFormat) {
		this.gdsFormat = gdsFormat;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

    public String getPlatRefundStatus() {
		return platRefundStatus;
	}

	public void setPlatRefundStatus(String platRefundStatus) {
		this.platRefundStatus = platRefundStatus;
	}

	public String getPlatStatus() {
		return platStatus;
	}

	public void setPlatStatus(String platStatus) {
		this.platStatus = platStatus;
	}

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTbId() {
        return tbId;
    }

    public void setTbId(String tbId) {
        this.tbId = tbId == null ? null : tbId.trim();
    }

    public String getOuterIid() {
        return outerIid;
    }

    public void setOuterIid(String outerIid) {
        this.outerIid = outerIid == null ? null : outerIid.trim();
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId == null ? null : outerSkuId.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public BigDecimal getSalsePrice() {
        return salsePrice;
    }

    public void setSalsePrice(BigDecimal salsePrice) {
        this.salsePrice = salsePrice;
    }

    public BigDecimal getTotsalMoney() {
        return totsalMoney;
    }

    public void setTotsalMoney(BigDecimal totsalMoney) {
        this.totsalMoney = totsalMoney;
    }

    public Boolean getIsgift() {
        return isgift;
    }

    public void setIsgift(Boolean isgift) {
        this.isgift = isgift;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Long getNumiid() {
        return numiid;
    }

    public void setNumiid(Long numiid) {
        this.numiid = numiid;
    }

    public String getTopSkuid() {
        return topSkuid;
    }

    public void setTopSkuid(String topSkuid) {
        this.topSkuid = topSkuid;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
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

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }
}