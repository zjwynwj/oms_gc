package com.dinghao.entity.htobject;


public class resultOrderDetail {
	
	private String adjustFee;//手工调整金额.格式为:1.01;单位:元;精确到小数点后两位.
	private String buyerNick; //买家昵称
	private String discountFee;//子订单级订单优惠金额
	private String modified;//订单修改时间
	private String num;//购买数量。取值范围:大于零的整数
	private String outerIid;//商品编码
	private String oid;//子订单编号
	private String outerSkuId;//外部网店自己定义的Sku编号
	private String payment;//子订单实付金额
	private String price;//商品价格
	private String refundId;//最近退款ID
	/*
	 * 退款状态。退款状态。可选值 
	 * WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意) 
	 * WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货) 
	 * WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货) 
	 * SELLER_REFUSE_BUYER(卖家拒绝退款) 
	 * CLOSED(退款关闭) 
	 * SUCCESS(退款成功)
	 * */
	private String refundStatus;
	private String title;//商品标题
	/*
	 * 应付金额（商品价格 * 商品数量 + 手工调整金额 - 子订单级订单优惠金额）。
	 * 精确到2位小数;单位:元。如:200.07，表示:200元7分
	 * */
	private String totalFee;
	
	public String getAdjustFee() {
		return adjustFee;
	}
	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public String getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getOuterIid() {
		return outerIid;
	}
	public void setOuterIid(String outerIid) {
		this.outerIid = outerIid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOuterSkuId() {
		return outerSkuId;
	}
	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
}
