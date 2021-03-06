/*
 * @ClassName:SalesRtnOrder.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-03-15
 */
package com.dinghao.entity.vo.template.salesrtnorder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.dinghao.entity.vo.manage.PageVo;

public class SalesRtnOrderVo extends PageVo<SalesRtnOrderVo> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .退货单ID
	 */
	private Long rtnId;

	/**
	 * .退货单据号
	 */
	private String rtnNo;

	

	/**
	 * .关联订单号
	 */
	private Integer orderId;

	/**
	 * .仓库编号
	 */
	private Integer stockId;

	/**
	 * .店铺名称
	 */
	private Integer shopId;

	/**
	 * .客户昵称（唯一）
	 */
	private String custNick;

	/**
	 * .总货款
	 */
	private BigDecimal goodsMoney;

	/**
	 * .实退总金额[rtnCash]=货款GoodsMoney+运费carriage
	 */
	private BigDecimal rtncash;
	/**
	 * 退货运费
	 */
	private BigDecimal carriage;
	/**
	 * .物流公司编号
	 */
	private Integer expId;

	/**
	 * .收货人
	 */
	private String recvName;

	/**
	 * .收货手机
	 */
	private String recvMobile;

	/**
	 * .收货电话
	 */
	private String recvPhone;

	/**
	 * .省
	 */
	private String provId;

	/**
	 * .市
	 */
	private String cityId;

	/**
	 * .县与区
	 */
	private String countyId;

	/**
	 * .收货地址
	 */
	private String address;

	/**
	 * .邮编
	 */
	private String zipcode;

	/**
	 * .物流单号
	 */
	private String expcode;

	/**
	 * .退货原因描述
	 */
	private String rtnreason;

	/**
	 * .退货单状态 1新建,2确认,3取货,4入库,5审核,-1取消
	 */
	private Integer status;

	/**
	 * .物流费用
	 */
	private BigDecimal deliveryfeeee;

	/**
	 * .逻辑删除
	 */
	private Boolean isdeleted;

	/**
	 * .收货
	 */
	private Boolean received;

	/**
	 * .是否扫描
	 */
	private Boolean hasscaned;

	/**
	 * .时间戳
	 */
	private Date timestamp;

	/**
	 * .
	 */
	private Long createBy;

	/**
	 * .
	 */
	private Date createDate;

	/**
	 * .
	 */
	private Long modifyBy;

	/**
	 * .
	 */
	private Date modifyDate;

	/**
	 * .内部留言
	 */
	private String interiormemo;
	/**
	 * .订单类型(SG-手工 TB-淘宝 WSC—微商城)
	 */
	private String platType;

	/**
	 * 订单编号
	 */
	private String orderNum;

	/**
	 * .物流ID
	 */
	private Long expid;

	/**
	 * 地区
	 */
	private String area;

	List<SalesRtnOrderItemVo> salesRtnOrderItemVos = new ArrayList<SalesRtnOrderItemVo>();

	public List<SalesRtnOrderItemVo> getSalesRtnOrderItemVos() {
		return salesRtnOrderItemVos;
	}

	public void setSalesRtnOrderItemVos(
			List<SalesRtnOrderItemVo> salesRtnOrderItemVos) {
		this.salesRtnOrderItemVos = salesRtnOrderItemVos;
	}

	public Long getRtnId() {
		return rtnId;
	}

	public void setRtnId(Long rtnId) {
		this.rtnId = rtnId;
	}

	public String getRtnNo() {
		return rtnNo;
	}

	public void setRtnNo(String rtnNo) {
		this.rtnNo = StringUtils.isBlank(rtnNo) ? null : rtnNo.trim();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getCustNick() {
		return custNick;
	}

	public void setCustNick(String custNick) {
		this.custNick = StringUtils.isBlank(custNick) ? null : custNick.trim();
	}

	public BigDecimal getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(BigDecimal goodsMoney) {
		this.goodsMoney = goodsMoney;
	}

	public BigDecimal getRtncash() {
		//货款GoodsMoney+运费carriage
		if(goodsMoney!=null && carriage!=null){
			return this.goodsMoney.add(this.carriage);
		}else{
			return rtncash;
		}
	}

	public void setRtncash(BigDecimal rtncash) {
		this.rtncash = rtncash;
	}

	public Integer getExpId() {
		return expId;
	}

	public void setExpId(Integer expId) {
		this.expId = expId;
	}

	public String getRecvName() {
		return recvName;
	}

	public void setRecvName(String recvName) {
		this.recvName = StringUtils.isBlank(recvName) ? null : recvName.trim();
	}

	public String getRecvMobile() {
		return recvMobile;
	}

	public void setRecvMobile(String recvMobile) {
		this.recvMobile = StringUtils.isBlank(recvMobile) ? null : recvMobile
				.trim();
	}

	public String getRecvPhone() {
		return recvPhone;
	}

	public void setRecvPhone(String recvPhone) {
		this.recvPhone = StringUtils.isBlank(recvPhone) ? null : recvPhone
				.trim();
	}

	public String getProvId() {
		if (StringUtils.isNotBlank(area) && area.split("-").length > 0) {
			this.provId = area.split("-")[0];
		} else {
			this.provId = null;
		}
		return provId;
	}

	public void setProvId(String provId) {
		// area
		this.provId = StringUtils.isBlank(provId) ? null : provId.trim();
	}

	public String getCityId() {
		if (StringUtils.isNotBlank(area) && area.split("-").length > 1) {
			this.cityId = area.split("-")[1];
		} else {
			this.cityId = null;
		}
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = StringUtils.isBlank(cityId) ? null : cityId.trim();
	}

	public String getCountyId() {
		if (StringUtils.isNotBlank(area) && area.split("-").length > 2) {
			this.countyId = area.split("-")[2];
		} else {
			this.countyId = null;
		}
		return countyId;
	}

	public void setCountyId(String countyId) {

		this.countyId = StringUtils.isBlank(countyId) ? null : countyId.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = StringUtils.isBlank(address) ? null : address.trim();
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = StringUtils.isBlank(zipcode) ? null : zipcode.trim();
	}

	public String getExpcode() {
		return expcode;
	}

	public void setExpcode(String expcode) {
		this.expcode = StringUtils.isBlank(expcode) ? null : expcode.trim();
	}

	public String getRtnreason() {
		return rtnreason;
	}

	public void setRtnreason(String rtnreason) {
		this.rtnreason = StringUtils.isBlank(rtnreason) ? null : rtnreason
				.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getDeliveryfeeee() {
		return deliveryfeeee;
	}

	public void setDeliveryfeeee(BigDecimal deliveryfeeee) {
		this.deliveryfeeee = deliveryfeeee;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Boolean getReceived() {
		return received;
	}

	public void setReceived(Boolean received) {
		this.received = received;
	}

	public Boolean getHasscaned() {
		return hasscaned;
	}

	public void setHasscaned(Boolean hasscaned) {
		this.hasscaned = hasscaned;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getInteriormemo() {
		return interiormemo;
	}

	public void setInteriormemo(String interiormemo) {
		this.interiormemo = StringUtils.isBlank(interiormemo) ? null
				: interiormemo.trim();
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = StringUtils.isBlank(platType) ? null : platType.trim();
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = StringUtils.isBlank(orderNum) ? null : orderNum.trim();
	}

	public Long getExpid() {
		return expid;
	}

	public void setExpid(Long expid) {
		this.expid = expid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getCarriage() {
		return carriage;
	}

	public void setCarriage(BigDecimal carriage) {
		this.carriage = carriage;
	}
}