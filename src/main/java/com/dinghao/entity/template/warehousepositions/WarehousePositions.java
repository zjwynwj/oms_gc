package com.dinghao.entity.template.warehousepositions;

import com.dinghao.entity.manage.BaseEntity;

public class WarehousePositions extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792667190103946241L;
	



	/**
	 * 仓库主键
	 */
	private Integer dhWarehousePositions;
	/**
	 * 库位编号
	 */
	private String warehousePositionsCode;
	/**
	 * 状态 0 停用 ; 1 启用
	 */
	private Integer isDelete;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 备用字段
	 */
	private String remarks1;

	public Integer getDhWarehousePositions() {
		return dhWarehousePositions;
	}

	public void setDhWarehousePositions(Integer dhWarehousePositions) {
		this.dhWarehousePositions = dhWarehousePositions;
	}

	public String getWarehousePositionsCode() {
		return warehousePositionsCode;
	}

	public void setWarehousePositionsCode(String warehousePositionsCode) {
		this.warehousePositionsCode = warehousePositionsCode == null ? null
				: warehousePositionsCode.trim();
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
}