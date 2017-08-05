/*
 * @ClassName:SalesRtnOrderItem.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-03-16
 */
package com.dinghao.entity.vo.template.salesrtnorder;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.dinghao.entity.vo.manage.PageVo;

public class SalesRtnOrderItemVo extends PageVo<SalesRtnOrderItemVo> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .自增ID
	 */
	private Integer id;

	/**
	 * .退货单id
	 */
	private Long rtnId;

	/**
	 * .品号
	 */
	private Integer gdsId;

	/**
	 * .订单上的最终售价
	 */
	private BigDecimal price;

	/**
	 * .退货数量
	 */
	private Long rtnQty;

	/**
	 * .积分
	 */
	private Long point;

	/**
	 * .商品原始标价
	 */
	private BigDecimal originalPrice;

	/**
	 * .是否赠品
	 */
	private Boolean isgift;

	/**
	 * .退款单类型
	 */
	private Integer rtnType;

	/**
	 * .备注
	 */
	private String memo;

	/**
	 * .是否取消档案
	 */
	private Boolean isdeleted;

	/**
	 * .库位
	 */
	private Long locid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getRtnId() {
		return rtnId;
	}

	public void setRtnId(Long rtnId) {
		this.rtnId = rtnId;
	}

	public Integer getGdsId() {
		return gdsId;
	}

	public void setGdsId(Integer gdsId) {
		this.gdsId = gdsId;
	}

	public BigDecimal getPrice() {
		if (originalPrice != null && rtnQty != null) {
			return originalPrice.multiply(new BigDecimal(rtnQty));
		}
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getRtnQty() {
		return rtnQty;
	}

	public void setRtnQty(Long rtnQty) {
		this.rtnQty = rtnQty;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Boolean getIsgift() {
		return isgift;
	}

	public void setIsgift(Boolean isgift) {
		this.isgift = isgift;
	}

	public Integer getRtnType() {
		return rtnType;
	}

	public void setRtnType(Integer rtnType) {
		this.rtnType = rtnType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = StringUtils.isBlank(memo) ? null : memo.trim();
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Long getLocid() {
		return locid;
	}

	public void setLocid(Long locid) {
		this.locid = locid;
	}
}