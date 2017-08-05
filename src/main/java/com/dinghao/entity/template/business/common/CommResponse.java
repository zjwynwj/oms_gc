package com.dinghao.entity.template.business.common;

import java.io.Serializable;

public class CommResponse implements Serializable{

	private static final long serialVersionUID = 3419694795622677491L;
	
	/** 是否成功 */
	private boolean success = true;
	/** 错误码 */
	private String errCode = "000000";
	/** 错误信息 */
	private String errMsg = "业务交易成功";
	/** 响应数据 */
	private Object data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
