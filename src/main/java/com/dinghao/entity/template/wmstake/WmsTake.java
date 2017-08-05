/*
 * @ClassName:WmsTake.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-01-21
 */
package com.dinghao.entity.template.wmstake;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.manage.BaseEntity;

public class WmsTake extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .盘点单号
	 */
	private String takeNo;

	/**
	 * .仓库ID
	 */
	private Integer stockId;

	/**
	 * .开始盘点
	 */
	private Boolean status;

	/**
	 * .盘点时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;

	/**
	 * .盘点人
	 */
	private Boolean busiPerson;

	/**
	 * .结束盘点
	 */
	private Date endTime;

	/**
	 * .备注
	 */
	private String memo;

	/**
	 * .时间戳
	 */
	private Date timestamp;

	/**
	 * .逻辑删除 0 删除 ;1 未删除
	 */
	private Boolean isDeleted;

	/**
	 * .盘点单名称
	 */
	private String takeName;

	/**
	 * .备用
	 */
	private String remarks;

	/**
	 * .备用1
	 */
	private String remarks1;

	/**
	 * .备用2
	 */
	private String remarks2;
	/**
	 * 仓库名称
	 */
	private String warehouseName;

	public String getTakeNo() {
		return takeNo;
	}

	public void setTakeNo(String takeNo) {
		this.takeNo = StringUtils.isBlank(takeNo) ? null : takeNo.trim();
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Boolean getBusiPerson() {
		return busiPerson;
	}

	public void setBusiPerson(Boolean busiPerson) {
		this.busiPerson = busiPerson;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = StringUtils.isBlank(memo) ? null : memo.trim();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getTakeName() {
		return takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = StringUtils.isBlank(takeName) ? null : takeName.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = StringUtils.isBlank(remarks) ? null : remarks.trim();
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = StringUtils.isBlank(remarks1) ? null : remarks1.trim();
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = StringUtils.isBlank(remarks2) ? null : remarks2.trim();
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
}