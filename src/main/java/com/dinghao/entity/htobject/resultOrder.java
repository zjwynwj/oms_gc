package com.dinghao.entity.htobject;

import java.util.List;

public class resultOrder {

	/*
	 * 卖家手工调整金额，精确到2位小数，单位：元。
	 * 如：200.07，表示：200元7分。
	 * 来源于订单价格修改，如果有多笔子订单的时候，这个为0，单笔的话则跟[order].adjust_fee一样
	 * */
	private String adjustFee;
	private String Tid;//平台（微商城）交易号，如：2009112081173831
	private String availableConfirmFee;//交易中剩余的确认收货金额
	private String buyerMemo;//买家备注
	private String buyerNick;//买家昵称
	private String identityCard;//身份证号
	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	private String created;//交易创建时间。格式:yyyy-MM-dd HH:mm:ss
	private String discountFee;//系统优惠金额
	private String endTime;//交易结束时间。交易成功时间(更新交易状态为成功的同时更新)/确认收货时间或者交易关闭时间 。格式:yyyy-MM-dd HH:mm:ss
	private String invoiceName;//发票抬头
	private String modified;//交易修改时间(用户对订单的任何修改都会更新此字段)。格式:yyyy-MM-dd HH:mm:ss
	private String payTime;//付款时间。格式:yyyy-MM-dd HH:mm:ss。订单的付款时间即为物流订单的创建时间。
	private String payment;//实付金额
	private String postFee;//邮费
	private String receivedPayment;//卖家实际收到的款金额
	private String receiverAddress;//收货人的详细地址
	private String receiverCity;//收货人的所在城市
	private String receiverDistrict;//收货人的所在地区
	private String receiverMobile;//收货人的手机号码
	private String receiverName;//收货人的姓名
	private String receiverPhone;//收货人的电话号码
	private String receiverState;//收货人的所在省份
	private String receiverZip;//收货人的邮编
	private String sellerMemo;//卖家备注
	private String sellerNick;//卖家昵称
	private String sellerPhone;//卖家电话
	private String status;//交易状态
	private String totalFee; //商品金额（商品价格乘以数量的总金额）。精确到2位小数;单位:元。如:200.07，表示:200元7分
	private String tradeMemo;//交易备注。
	private String refundId;//退款ID
	private String refundStatus;//退款状态
	private String refundMoney;
	
	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}
	private List<resultOrderDetail> details;
	
	
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public List<resultOrderDetail> getDetails() {
		return details;
	}
	
	public void setDetails(List<resultOrderDetail> details) {
		this.details = details;
	}
	public String getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}
	public String getTid() {
		return Tid;
	}
	public void setTid(String tid) {
		Tid = tid;
	}
	public String getAvailableConfirmFee() {
		return availableConfirmFee;
	}
	public void setAvailableConfirmFee(String availableConfirmFee) {
		this.availableConfirmFee = availableConfirmFee;
	}
	public String getBuyerMemo() {
		return buyerMemo;
	}
	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPostFee() {
		return postFee;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public String getReceivedPayment() {
		return receivedPayment;
	}
	public void setReceivedPayment(String receivedPayment) {
		this.receivedPayment = receivedPayment;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	public String getSellerMemo() {
		return sellerMemo;
	}
	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}
	public String getSellerNick() {
		return sellerNick;
	}
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getTradeMemo() {
		return tradeMemo;
	}
	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	
}
