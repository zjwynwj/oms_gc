package com.dinghao.entity.htobject;

import java.util.List;

public class resultEntityProduct {

	protected boolean isSuccess;
	protected int totalCount;
	protected String msg;
	protected List<resultProduct> Entity;
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<resultProduct> getEntity() {
		return Entity;
	}
	public void setEntity(List<resultProduct> entity) {
		Entity = entity;
	}
	
	
}
