package com.dinghao.entity.htobject;

public class requestEntity {
	
	private String nick;//卖家昵称
	private String name;//表示oms 调用，固定一个值就可以
	private String method;//方法名称
	private String format;//格式（json/xml）
	private String timestamp;//时间戳   具体看是否需要
	private String sign;//验证码  具体看是否需要
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public boolean isValidTime() {
		 return true;
		//return platUtil.isValidTimestamp(this.timestamp);
	}
	
	public boolean isValidSign() {
		 return true;
	//	String signServerString = platUtil.getSign(this);
	//	System.out.println("服务器签名：" + signServerString);
	//	System.out.println("接收到的签名：" + this.sign);
	//	return signServerString.equals(this.sign);
	}
}
