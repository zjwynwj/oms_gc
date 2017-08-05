package com.dinghao.entity.template.business.order;

import java.io.Serializable;

/**
 * @category 获取下载总条目的放回结果信息类
 * @author helong
 *
 */
public class TotalResult  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/** 是否成功 */
	private boolean success = true;
	/** 错误码 */
	private String errCode = "000000";//错误码
	/** 错误信息 */
	private String errMsg = "业务交易成功";//错误信息
	/** 总条目数*/
	private long totalNum=0;

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

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
