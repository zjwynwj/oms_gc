package com.dinghao.entity.vo.template.business.goods;

import java.math.BigDecimal;

import com.dinghao.entity.vo.manage.PageVo;

public class DownGoodsSkuVo extends PageVo<DownGoodsSkuVo>{
	
    /**
	  * @Fields serialVersionUID : TODO（商品下载主表  请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String skuId;

    private String spec;

    private String outerSkuId;

    private BigDecimal price;

    private String platType;

    private BigDecimal costPrice;

    private String numIid;

    private String propUrl;

    private String barcode;

    private String downTime;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuId() {
        return "undefined".equals(skuId)&& null==skuId?"":skuId;
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
        return outerSkuId.equals("undefined")?"":outerSkuId;
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