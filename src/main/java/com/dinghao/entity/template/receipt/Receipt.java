package com.dinghao.entity.template.receipt;

import com.dinghao.entity.manage.BaseEntity;
/**
 * 库存管理 入库单管理
 * @author Administrator
 *
 */

public class Receipt extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 432536692209530554L;
	/**
	 * 仓库编号
	 */
	private Integer warehouseId;
	/**
	 * 仓库名字
	 */
	private String warehouseName;
	/**
	 * 供应商ID
	 */
	private String providerId;
	/**
	 * 入库单号
	 */
	private String receiptCode;
	/**
	 * 业务类型
	 */
	private Integer serviceType;
	/**
	 * 业务单号
	 */
	private String serviceNum;
	/**
	 * 经手人
	 */
	private String handledPerson;
	/**
	 * 往来单位
	 */
	private String company;
	/**
	 * 备注1
	 */
	private String remarks;
	/**
	 * 备注2
	 */

	private String remarks1;
	/**
	 * 单类别 1 入库单 2 出库单
	 */
	private Integer receiptType;

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

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}
}