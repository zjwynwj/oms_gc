package com.dinghao.entity.htobject;


public class requestSend extends requestEntity {
	
	private String Orderno;//订单编号
	private String sendTime;//格式：yyyy-MM-dd HH:mm:ss 发货时间
 
	private String Expno;//物流公司代码
	private String Expcode;//物流单号 
	private String sendRemark; //发货备注
	private String ExpCompany; //物流公司名单
	
	public String getOrderno() {
		return Orderno;
	}
	public void setOrderno(String orderno) {
		Orderno = orderno;
	}
	
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getExpno() {
		return Expno;
	}
	public void setExpno(String expno) {
		Expno = expno;
	}
	public String getExpcode() {
		return Expcode;
	}
	public void setExpcode(String expcode) {
		Expcode = expcode;
	}
	public String getSendRemark() {
		return sendRemark;
	}
	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}
	public String getExpCompany() {
		return ExpCompany;
	}
	public void setExpCompany(String expCompany) {
		ExpCompany = expCompany;
	}
	

}
