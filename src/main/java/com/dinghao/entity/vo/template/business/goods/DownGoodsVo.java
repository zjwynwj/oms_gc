package com.dinghao.entity.vo.template.business.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DownGoodsVo implements Serializable{
    /**
	  * @Fields serialVersionUID : TODO（商品下载规格表  请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String platType;

    private String numIid;

    private String outerId;

    private String title;

    private BigDecimal price;

    private String shopTitle;

    private Long shopId;

    private String downNotice;

    private String picUrl;

    private String detailUrl;

    private String barcode;

    private Integer specNum;

    private String status;

    private String artNo;

    private String shopNick;

    private String downTime;
    
    private BigDecimal standWeight;

    private List<DownGoodsSkuVo> skuList;

    public BigDecimal getStandWeight() {
		return standWeight;
	}

	public void setStandWeight(BigDecimal standWeight) {
		this.standWeight = standWeight;
	}

	public List<DownGoodsSkuVo> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<DownGoodsSkuVo> skuList) {
		this.skuList = skuList;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle == null ? null : shopTitle.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDownNotice() {
        return downNotice;
    }

    public void setDownNotice(String downNotice) {
        this.downNotice = downNotice == null ? null : downNotice.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo == null ? null : artNo.trim();
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick == null ? null : shopNick.trim();
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime == null ? null : downTime.trim();
    }
}