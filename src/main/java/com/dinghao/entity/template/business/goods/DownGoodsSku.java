package com.dinghao.entity.template.business.goods;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class DownGoodsSku extends  BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（商品下载规格表  返回类）
	  */
	
	private static final long serialVersionUID = 1L;
	//id
	private Long id;
	//平台商品 规格级别id
    private String skuId;
    //规格
    private String spec;
    //规格级别  商家外部编码	
    private String outerSkuId;
    //规格级别  商品价格
    private BigDecimal price;
    //平台类型
    private String platType;
    //成本价格
    private BigDecimal costPrice;
    //平台商品级别id
    private String numIid;
    //sku属性级别图片链接地址
    private String propUrl;
    //sku级别条形码
    private String barcode;
    //下载时间戳
    private String downTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId == null ? null : outerSkuId.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
    }

    public String getPropUrl() {
        return propUrl;
    }

    public void setPropUrl(String propUrl) {
        this.propUrl = propUrl == null ? null : propUrl.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime == null ? null : downTime.trim();
    }
}