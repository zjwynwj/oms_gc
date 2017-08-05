package com.dinghao.entity.template.business.goods;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class DownGoods extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（商品下载主表 返回类）
	  */
	
	private static final long serialVersionUID = 1L;
	//id
	private Long id;
	//平台类型
    private String platType;
    //平台商品id
    private String numIid;
    //商家外部编码
    private String outerId;
    //商品名称
    private String title;
    //商品价格(商品级别)
    private BigDecimal price;
    //店铺名称
    private String shopTitle;
    //店铺id
    private Long shopId;
    //下载商品相关  异常信息
    private String downNotice;
    //商品图片url
    private String picUrl;
    //商品详情页面url	
    private String detailUrl;
    //商品级别条形码
    private String barcode;
    //规格数
    private Integer specNum;
    //状态
    private String status;
    //货号
    private String artNo;
    //店铺昵称
    private String shopNick;
    //下载时间戳
    private String downTime;

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