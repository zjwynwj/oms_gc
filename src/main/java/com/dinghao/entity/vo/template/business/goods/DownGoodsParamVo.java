package com.dinghao.entity.vo.template.business.goods;

import java.io.Serializable;

public class DownGoodsParamVo implements Serializable{

	 /**
	  * @Fields serialVersionUID : TODO（下载商品   参数类）
	  */
	
	private static final long serialVersionUID = 1L;

	private String platType;
	 
	private String shopTitle;
	 
	private String shopNick;
	 
	private Long shopId;
	 
	private String sessionKey;
	 
	private String downTime;
	
	private Long clsId;
	
	private String cal; 

	public Long getClsId() {
		return clsId;
	}

	public void setClsId(Long clsId) {
		this.clsId = clsId;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public String getShopNick() {
		return shopNick;
	}

	public void setShopNick(String shopNick) {
		this.shopNick = shopNick;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	 
}
