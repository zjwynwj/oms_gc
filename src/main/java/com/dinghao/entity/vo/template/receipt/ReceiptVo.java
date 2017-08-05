package com.dinghao.entity.vo.template.receipt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class ReceiptVo extends PageVo<ReceiptVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer warehouseId;
	
	private String warehouseName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date createDate;

	private String beginDate;

	private String endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date modifyDate;

	private Long modifyBy;

	private Long createBy;

	private String providerId;

	private String receiptCode;

	private Integer serviceType;

	private String serviceNum;

	private String handledPerson;

	private String company;

	private String remarks;

	private String remarks1;
	//采购id
	private Long purOrderId;
	
	public Long getPurOrderId() {
		return purOrderId;
	}

	public void setPurOrderId(Long purOrderId) {
		this.purOrderId = purOrderId;
	}

	/**
	 * 单类别 1 入库单 2 出库单
	 */
	private Integer receiptType;
	private List<ReceiptDetailVo> receiptDetailVos ;
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName == null ? null : warehouseName
				.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId == null ? null : providerId.trim();
	}

	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode == null ? null : receiptCode.trim();
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum == null ? null : serviceNum.trim();
	}

	public String getHandledPerson() {
		return handledPerson;
	}

	public void setHandledPerson(String handledPerson) {
		this.handledPerson = handledPerson == null ? null : handledPerson
				.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1 == null ? null : remarks1.trim();
	}

	public List<ReceiptDetailVo> getReceiptDetailVos() {
		return receiptDetailVos;
	}

	public void setReceiptDetailVos(List<ReceiptDetailVo> receiptDetailVos) {
		this.receiptDetailVos = receiptDetailVos;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}
}