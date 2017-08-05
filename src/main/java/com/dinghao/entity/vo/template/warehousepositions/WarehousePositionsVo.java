package com.dinghao.entity.vo.template.warehousepositions;

import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class WarehousePositionsVo extends PageVo<WarehousePositionsVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 856679239974973888L;

	private Integer id = -1;

	private Integer dhWarehousePositions = -1;

	private Date createDate = null;

	private Date modifyDate = null;

	private String modifyBy = null;

	private String createBy = null;

	private String warehousePositionsCode = null;

	private Integer isDelete = null;

	private String remarks = null;

	private String remarks1 = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDhWarehousePositions() {
		return dhWarehousePositions;
	}

	public void setDhWarehousePositions(Integer dhWarehousePositions) {
		this.dhWarehousePositions = dhWarehousePositions;
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

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
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