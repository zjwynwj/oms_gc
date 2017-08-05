/*
 * @ClassName:LocStock.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-01-26
 */
package com.dinghao.entity.template.locstock;

import java.util.Date;

import com.dinghao.entity.manage.BaseEntity;

public class LocStock extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .仓库编号
	 */
	private Long stockId;

	/**
	 * .库位号
	 */
	private Long locId;

	/**
	 * .商品SKU
	 */
	private Long gdsId;

	/**
	 * .最大量容
	 */
	private Integer maxQty;

	/**
	 * .最小库存
	 */
	private Integer minQty;

	/**
	 * .安全库存
	 */
	private Long safeQty;

	/**
	 * .实际库存
	 */
	private Long totalQty;

	/**
	 * .是否锁定,0未锁定;1锁定
	 */
	private Boolean locked;

	/**
	 * .逻辑删除,0删除,1未删除
	 */
	private Boolean isDeleted;

	/**
	 * .时间戳
	 */
	private Date timestamp;

	/**
	 * 商品编号
	 */
	private String gdsNo;

	/**
	 * 商品名称
	 */
	private String gdsName;

	/**
	 * 商品规格
	 */
	private String gdsFormat;

	/**
	 * 商品属性
	 */
	private String attbs;

	/**
	 * 计量的单位
	 */
	private String cal;

	/**
	 * 库位名称
	 */
	private String warehousePositionsName;
	/**
	 * 最低库存
	 */
	private Long gdsLowAmount;
	/**
	 * 最高库存
	 */
	private Long gdsHighAmount;
	
	/**
	 * 待发货库存
	 */
	private Long orderQty;
	
	/**
	 * 可用库存
	 */
	private Long useQty;

	public Long getUseQty() {
		return useQty;
	}

	public void setUseQty(Long useQty) {
		this.useQty = useQty;
	}

	public Long getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Long orderQty) {
		this.orderQty = orderQty;
	}

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

	public String getAttbs() {
		return attbs;
	}

	public void setAttbs(String attbs) {
		this.attbs = attbs;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getWarehousePositionsName() {
		return warehousePositionsName;
	}

	public void setWarehousePositionsName(String warehousePositionsName) {
		this.warehousePositionsName = warehousePositionsName;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public Integer getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}

	public Integer getMinQty() {
		return minQty;
	}

	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}

	public Long getSafeQty() {
		return safeQty;
	}

	public void setSafeQty(Long safeQty) {
		this.safeQty = safeQty;
	}

	public Long getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Long totalQty) {
		this.totalQty = totalQty;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getGdsLowAmount() {
		return gdsLowAmount;
	}

	public void setGdsLowAmount(Long gdsLowAmount) {
		this.gdsLowAmount = gdsLowAmount;
	}

	public Long getGdsHighAmount() {
		return gdsHighAmount;
	}

	public void setGdsHighAmount(Long gdsHighAmount) {
		this.gdsHighAmount = gdsHighAmount;
	}

}