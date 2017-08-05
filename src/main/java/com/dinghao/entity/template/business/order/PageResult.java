package com.dinghao.entity.template.business.order;

import java.io.Serializable;
import java.util.List;

/**
 * @category 获取下载总条目的放回结果信息类
 * @author helong
 *
 */
public class PageResult  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/** 是否成功 */
	private boolean success = true;
	/** 是否有下一页 */
	private boolean hasNext = true;
	/** 查询结果集合*/
	private List<?> list;
	/** 错误码 */
	private String errCode = "000000";//错误码
	/** 错误信息 */
	private String errMsg = "业务交易成功";//错误信息
	/** 总条目数*/
	
	
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
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
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	 

}
