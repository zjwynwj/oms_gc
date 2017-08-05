package com.dinghao.entity.template.receipt;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class ReceiptDetail extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 入库单ID
	 */
	private Integer receiptId;
	/**
	 * 商品管理主键
	 */
	private Long gdsInfoId;
	/**
	 * 商品编号/名称/规格
	 */
	private String gdsInfoDetail;
	/**
	 * 商品规格
	 */
	private String gdsInfoFormat;
	
	public String getGdsFormat() {
		return gdsFormat;
	}

	public void setGdsFormat(String gdsFormat) {
		this.gdsFormat = gdsFormat;
	}

	private String gdsFormat;
	
	private String gdsName;
	private String skuOuterId;
	
	
    private BigDecimal price;
    private BigDecimal taxPrice;
    
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

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getSkuOuterId() {
		return skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	/**
	 * 计量单位
	 */
	private String gdsInfoCal;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 库位ID
	 */
	private Integer warehousePositionsId;
	/**
	 * 库位编号
	 */
	private String warehousePositionsName;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 备用1
	 */
	private String remarks1;
	/**
	 * 备用2
	 */
	private String remarks2;
	/**
	 * 商品管理快照
	 */
	private String gdsInfoSnapshot;

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
		this.remarks1 = remarks1 == null ? null : remarks1.trim();
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