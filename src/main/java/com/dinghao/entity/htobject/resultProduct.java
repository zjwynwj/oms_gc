package com.dinghao.entity.htobject;

import java.math.BigDecimal;
import java.util.Date;

public class resultProduct {
	
	private String gdsNo;//商品编码
	private String gdsName;//商品名称
	private String gdsFormat;//商品规格
	private String gdsPact;//商品条码
	private BigDecimal Price;//商品价格
	private String Cal;//计量单位 如 件 、个
	private BigDecimal standWeight;//商品标准重量
	private int Amount;//商品数量
	private String IMG_PATH;//商品图片路径，多个图片路径通过子集合返回
	private Date created;//创建时间。格式:yyyy-MM-dd HH:mm:ss
	private Date modified;//修改时间(用户对商品的任何修改都会更新此字段)。格式:yyyy-MM-dd HH:mm:ss
	
	public String getGdsNo() {
		return gdsNo;
	}
	public void setGdsNo(String gdsNo) {
		this.gdsNo = gdsNo;
	}
	public String getGdsName() {
		return gdsName;
	}
	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
	public String getGdsFormat() {
		return gdsFormat;
	}
	public void setGdsFormat(String gdsFormat) {
		this.gdsFormat = gdsFormat;
	}
	public String getGdsPact() {
		return gdsPact;
	}
	public void setGdsPact(String gdsPact) {
		this.gdsPact = gdsPact;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	public String getCal() {
		return Cal;
	}
	public void setCal(String cal) {
		Cal = cal;
	}
	public BigDecimal getStandWeight() {
		return standWeight;
	}
	public void setStandWeight(BigDecimal standWeight) {
		this.standWeight = standWeight;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public String getIMG_PATH() {
		return IMG_PATH;
	}
	public void setIMG_PATH(String iMGPATH) {
		IMG_PATH = iMGPATH;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	


}
