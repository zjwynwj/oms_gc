package com.dinghao.entity.vo.template.receipt;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.dinghao.entity.vo.manage.PageVo;

public class ReceiptDetailVo extends PageVo<ReceiptDetailVo> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;

	private Date createDate;

	private String modifyDate;

	private Long modifyBy;

	private Long createBy;
	/**
	 * 出/入库表ID
	 */
	private Integer receiptId;
	/**
	 * 货物主键
	 */
	private Long gdsInfoId;
	/**
	 * 货物详细
	 */
	private String gdsInfoDetail;

	private String gdsInfoFormat;

	private String gdsInfoCal;
	/**
	 * 数量
	 */
	private Integer amount;
	
	private BigDecimal price;
	private BigDecimal taxPrice;
	/**
	 * 库位
	 */
	private Integer warehousePositionsId;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	private String warehousePositionsName;

	private String remarks;

	private String remarks1;

	private String remarks2;

	private String gdsInfoSnapshot;
	
	//采购明细id(何龙添加)
	private Long  putOrderDetailId;

	public Long getPutOrderDetailId() {
		return putOrderDetailId;
	}

	public void setPutOrderDetailId(Long putOrderDetailId) {
		this.putOrderDetailId = putOrderDetailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate == null ? null : modifyDate.trim();
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

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Long getGdsInfoId() {
		return gdsInfoId;
	}

	public void setGdsInfoId(Long gdsInfoId) {
		this.gdsInfoId = gdsInfoId;
	}

	public String getGdsInfoDetail() {
		return gdsInfoDetail;
	}

	public void setGdsInfoDetail(String gdsInfoDetail) {
		this.gdsInfoDetail = gdsInfoDetail == null ? null : gdsInfoDetail
				.trim();
	}

	public String getGdsInfoFormat() {
		return gdsInfoFormat;
	}

	public void setGdsInfoFormat(String gdsInfoFormat) {
		this.gdsInfoFormat = gdsInfoFormat == null ? null : gdsInfoFormat
				.trim();
	}

	public String getGdsInfoCal() {
		return gdsInfoCal;
	}

	public void setGdsInfoCal(String gdsInfoCal) {
		this.gdsInfoCal = gdsInfoCal == null ? null : gdsInfoCal.trim();
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getWarehousePositionsId() {
		return warehousePositionsId;
	}

	public void setWarehousePositionsId(Integer warehousePositionsId) {
		this.warehousePositionsId = warehousePositionsId;
	}

	public String getWarehousePositionsName() {
		return warehousePositionsName;
	}

	public void setWarehousePositionsName(String warehousePositionsName) {
		this.warehousePositionsName = warehousePositionsName == null ? null
				: warehousePositionsName.trim();
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
		this.remarks1 = StringUtils.isBlank(remarks1) ? null : remarks1.trim();
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2 == null ? null : remarks2.trim();
	}

	public String getGdsInfoSnapshot() {
		return gdsInfoSnapshot;
	}

	public void setGdsInfoSnapshot(String gdsInfoSnapshot) {
		this.gdsInfoSnapshot = gdsInfoSnapshot == null ? null : gdsInfoSnapshot
				.trim();
	}
}