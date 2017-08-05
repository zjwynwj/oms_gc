package com.dinghao.entity.vo.template.business.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dinghao.entity.vo.manage.PageVo;

public class SalesOrderVo extends PageVo<SalesOrderVo>{
    /**
	  * @Fields serialVersionUID : TODO（订单主表请求类）
	  */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String sidx = ""; // 排序的列名
	private String sord  = ""; // 排序的方式
	
    private String orderNum;
    private String  shopName;
    private String  identityCard;
    private BigDecimal  taxAmount;
    private Date  payTime;
    private String warehouseName;
       
    public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	private String  printTemplateName ;
    
    public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	@SuppressWarnings("unused")
	private String recvArea;//收件人省市区
	
    public String getRecvArea() {
		return prov + "-" + city +  "-" + (null ==  county?"":county)  ;
	}

	public void setRecvArea(String recvArea) {
		this.recvArea = recvArea;
	}
	/*
	 * 物流公司名称/
	 */
	private String  expressName;
	/*
	 * 物流公司代码/
	 */
	private String  expressNo;
	  
    public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	private Long shopId;
    private Long shopNick;
    private String startTime;
    private String endTime;
    
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getShopNick() {
		return shopNick;
	}

	public void setShopNick(Long shopNick) {
		this.shopNick = shopNick;
	}

	private String fromcodes;

    private String topTids;

    private String platType;

    private Long stockId;

    private String custNick;

    private String recvname;

    private String recvmobile;

    private String recvphone;

    private String prov;

    private String city;

    private String county;

    private String address;

    private String zipcode;

    private Long expid;

    private BigDecimal deliverycost;

    private BigDecimal deliveryfee;

    private BigDecimal adjustfee;

    private BigDecimal discountfee;

    private BigDecimal totalFee;

    private BigDecimal payedMoney;

    private BigDecimal standardWeight;

    private BigDecimal realWeight;

    private String needinvoice;

    private String invoicememo;

    private String expcode;

    private Long orderPoint;

    private String hassend;

    private String hasmerge;

    private String sendBy;

    private Date sendTime;

    private String buyerFlag;

    private String buyerMemo;

    private String sellerFlag;
    
    public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	private String sellerNick;

    private String sellerMemo;

    private String downNotice;

    private String mark;

    private Date plansendDate;

    private String hasfaudit;

    private String disabled;

    private String hasrefund;

    private String ecprinted;

    private String sendprinted;

    private String hasscaned;

    private Integer waveId;
    
    private String waveNo;

    private Date buyTime;

    private String hasprom;

    private String timestamp;

    private BigDecimal rtnMoney;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private String modifyDate;

    private String remark1;

    private String remark2;

    private String remark3;

    private String remark4;
    
    private String exceptionStatus;
    
    private String sendStatus;
    
    private Date modified;
    
    private String platStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date downStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date downEndTime;
    
    private String ids[];
    
    private String exceptionStatusArr[];
    
    private String expcodeArr[];
    
    private List<SalesOrderitemVo> itemList;

    public String[] getExpcodeArr() {
		return expcodeArr;
	}

	public void setExpcodeArr(String[] expcodeArr) {
		this.expcodeArr = expcodeArr;
	}

	public String[] getExceptionStatusArr() {
		return exceptionStatusArr;
	}

	public void setExceptionStatusArr(String[] exceptionStatusArr) {
		this.exceptionStatusArr = exceptionStatusArr;
	}

	public String getWaveNo() {
		return waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getPlatStatus() {
		return platStatus;
	}

	public void setPlatStatus(String platStatus) {
		this.platStatus = platStatus;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Date getDownStartTime() {
		return downStartTime;
	}

	public void setDownStartTime(Date downStartTime) {
		this.downStartTime = downStartTime;
	}

	public Date getDownEndTime() {
		
		return downEndTime;
	}

	public void setDownEndTime(Date downEndTime) {
		if(downEndTime != null){
			long afterTime=(downEndTime.getTime()/1000)+60*60*24;    
			downEndTime.setTime(afterTime*1000);    
			this.downEndTime = downEndTime;
		}
	}

	public String getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public List<SalesOrderitemVo> getItemList() {
		return itemList;
	}

	public void setItemList(List<SalesOrderitemVo> itemList) {
		this.itemList = itemList;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getFromcodes() {
        return fromcodes;
    }

    public void setFromcodes(String fromcodes) {
        this.fromcodes = fromcodes == null ? null : fromcodes.trim();
    }

    public String getTopTids() {
        return topTids;
    }

    public void setTopTids(String topTids) {
        this.topTids = topTids == null ? null : topTids.trim();
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getCustNick() {
        return custNick;
    }

    public void setCustNick(String custNick) {
        this.custNick = custNick == null ? null : custNick.trim();
    }

    public String getRecvname() {
        return recvname;
    }

    public void setRecvname(String recvname) {
        this.recvname = recvname == null ? null : recvname.trim();
    }

    public String getRecvmobile() {
        return recvmobile;
    }

    public void setRecvmobile(String recvmobile) {
        this.recvmobile = recvmobile == null ? null : recvmobile.trim();
    }

    public String getRecvphone() {
        return recvphone;
    }

    public void setRecvphone(String recvphone) {
        this.recvphone = recvphone == null ? null : recvphone.trim();
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public Long getExpid() {
        return expid;
    }

    public void setExpid(Long expid) {
        this.expid = expid;
    }

    public BigDecimal getDeliverycost() {
        return deliverycost;
    }

    public void setDeliverycost(BigDecimal deliverycost) {
        this.deliverycost = deliverycost;
    }

    public BigDecimal getDeliveryfee() {
        return deliveryfee;
    }

    public void setDeliveryfee(BigDecimal deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    public BigDecimal getAdjustfee() {
        return adjustfee;
    }

    public void setAdjustfee(BigDecimal adjustfee) {
        this.adjustfee = adjustfee;
    }

    public BigDecimal getDiscountfee() {
        return discountfee;
    }

    public void setDiscountfee(BigDecimal discountfee) {
        this.discountfee = discountfee;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPayedMoney() {
        return payedMoney;
    }

    public void setPayedMoney(BigDecimal payedMoney) {
        this.payedMoney = payedMoney;
    }

    public BigDecimal getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(BigDecimal standardWeight) {
        this.standardWeight = standardWeight;
    }

    public BigDecimal getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(BigDecimal realWeight) {
        this.realWeight = realWeight;
    }

    public String getNeedinvoice() {
        return needinvoice;
    }

    public void setNeedinvoice(String needinvoice) {
        this.needinvoice = needinvoice == null ? null : needinvoice.trim();
    }

    public String getInvoicememo() {
        return invoicememo;
    }

    public void setInvoicememo(String invoicememo) {
        this.invoicememo = invoicememo == null ? null : invoicememo.trim();
    }

    public String getExpcode() {
        return expcode;
    }

    public void setExpcode(String expcode) {
        this.expcode = expcode == null ? null : expcode.trim();
    }

    public Long getOrderPoint() {
        return orderPoint;
    }

    public void setOrderPoint(Long orderPoint) {
        this.orderPoint = orderPoint;
    }

    public String getHassend() {
        return hassend;
    }

    public void setHassend(String hassend) {
        this.hassend = hassend == null ? null : hassend.trim();
    }

    public String getHasmerge() {
        return hasmerge;
    }

    public void setHasmerge(String hasmerge) {
        this.hasmerge = hasmerge == null ? null : hasmerge.trim();
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy == null ? null : sendBy.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getBuyerFlag() {
        return buyerFlag;
    }

    public void setBuyerFlag(String buyerFlag) {
        this.buyerFlag = buyerFlag == null ? null : buyerFlag.trim();
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo == null ? null : buyerMemo.trim();
    }

    public String getSellerFlag() {
        return sellerFlag;
    }

    public void setSellerFlag(String sellerFlag) {
        this.sellerFlag = sellerFlag == null ? null : sellerFlag.trim();
    }

    public String getSellerMemo() {
        return sellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo == null ? null : sellerMemo.trim();
    }

    public String getDownNotice() {
        return downNotice;
    }

    public void setDownNotice(String downNotice) {
        this.downNotice = downNotice == null ? null : downNotice.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Date getPlansendDate() {
        return plansendDate;
    }

    public void setPlansendDate(Date plansendDate) {
        this.plansendDate = plansendDate;
    }

    public String getPrintTemplateName() {
		return printTemplateName;
	}

	public void setPrintTemplateName(String printTemplateName) {
		this.printTemplateName = printTemplateName;
	}

	public String getHasfaudit() {
        return hasfaudit;
    }

    public void setHasfaudit(String hasfaudit) {
        this.hasfaudit = hasfaudit == null ? null : hasfaudit.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public String getHasrefund() {
        return hasrefund;
    }

    public void setHasrefund(String hasrefund) {
        this.hasrefund = hasrefund == null ? null : hasrefund.trim();
    }

    public String getEcprinted() {
        return ecprinted;
    }

    public void setEcprinted(String ecprinted) {
        this.ecprinted = ecprinted == null ? null : ecprinted.trim();
    }

    public String getSendprinted() {
        return sendprinted;
    }

    public void setSendprinted(String sendprinted) {
        this.sendprinted = sendprinted == null ? null : sendprinted.trim();
    }

    public String getHasscaned() {
        return hasscaned;
    }

    public void setHasscaned(String hasscaned) {
        this.hasscaned = hasscaned == null ? null : hasscaned.trim();
    }

    public Integer getWaveId() {
        return waveId;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getHasprom() {
        return hasprom;
    }

    public void setHasprom(String hasprom) {
        this.hasprom = hasprom == null ? null : hasprom.trim();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getRtnMoney() {
        return rtnMoney;
    }

    public void setRtnMoney(BigDecimal rtnMoney) {
        this.rtnMoney = rtnMoney;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }
}