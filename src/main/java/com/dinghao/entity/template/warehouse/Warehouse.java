package com.dinghao.entity.template.warehouse;

import com.dinghao.entity.manage.BaseEntity;

public class Warehouse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 仓库编号
	 */
	private String warehouseCode;

	/**
	 * 仓库名称
	 */
	private String warehouseName;

	/**
	 * 管理员名称
	 */
	private String managerName;

	/**
	 * 管理员id
	 */
	private Long managerId;

	/**
	 * 状态
	 */
	private Boolean isDelete;

	/**
	 * 地址
	 */
	private String address;

	private String remarks1;

	private String remarks2;

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode == null ? null : warehouseCode
				.trim();
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName == null ? null : warehouseName
				.trim();
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getAddress() {
		return address;
	}

	public void setAddres(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1 == null ? null : remarks1.trim();
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2 == null ? null : remarks2.trim();
	}
}