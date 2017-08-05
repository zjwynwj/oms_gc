package com.dinghao.entity.vo.template.base;

import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class ShopVo extends PageVo<ShopVo>{
    /**
	 * 单据号生成
	 */
	private static final long serialVersionUID = -9038517860796162952L;

	   private Long id;
	   private Long warehouseId;
	   private Long printId;
	   
		 public Long getWarehouseId() {
		return warehouseId;
	}

	public Long getPrintId() {
			return printId;
		}

		public void setPrintId(Long printId) {
			this.printId = printId;
		}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

		private String code;

	    private String name;

	    private String titile;

	    private Long cid;

	    private String nickname;

	    private String sellerAccount;

	    private String sellerPwd;

	    private String sellerName;

	    private String phone;

	    private String mobile;

	    
	    private String provName;
	    private String cityName;
	    private String countyName;
	    private String areaName;

	    public String getProvName() {
			return provName;
		}

		public void setProvName(String provName) {
			this.provName = provName;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getCountyName() {
			return countyName;
		}

		public void setCountyName(String countyName) {
			this.countyName = countyName;
		}

		private String planType;

		 
	    public String getPlanType() {
			return planType;
		}

		public void setPlanType(String planType) {
			this.planType = planType;
		}

		private String address;

	    private String zipcode;

	    private Boolean beactive;

	    private Integer expid;

	    private String appkey;

	    private String appsecret;

	    private String sessionkey;

	    private String memo;

	    private Date timestamp;

	    private Long createBy;

	    private Date createDate;

	    private Long modifyBy;

	    private Date modifyDate;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code == null ? null : code.trim();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public String getTitile() {
	        return titile;
	    }

	    public void setTitile(String titile) {
	        this.titile = titile == null ? null : titile.trim();
	    }

	    public Long getCid() {
	        return cid;
	    }

	    public void setCid(Long cid) {
	        this.cid = cid;
	    }

	    public String getNickname() {
	        return nickname;
	    }

	    public void setNickname(String nickname) {
	        this.nickname = nickname == null ? null : nickname.trim();
	    }

	    public String getSellerAccount() {
	        return sellerAccount;
	    }

	    public void setSellerAccount(String sellerAccount) {
	        this.sellerAccount = sellerAccount == null ? null : sellerAccount.trim();
	    }

	    public String getSellerPwd() {
	        return sellerPwd;
	    }

	    public void setSellerPwd(String sellerPwd) {
	        this.sellerPwd = sellerPwd == null ? null : sellerPwd.trim();
	    }

	    public String getSellerName() {
	        return sellerName;
	    }

	    public void setSellerName(String sellerName) {
	        this.sellerName = sellerName == null ? null : sellerName.trim();
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone == null ? null : phone.trim();
	    }

	    public String getMobile() {
	        return mobile;
	    }

	    public void setMobile(String mobile) {
	        this.mobile = mobile == null ? null : mobile.trim();
	    }

	  

	    public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
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

	    public Boolean getBeactive() {
	        return beactive;
	    }

	    public void setBeactive(Boolean beactive) {
	        this.beactive = beactive;
	    }

	    public Integer getExpid() {
	        return expid;
	    }

	    public void setExpid(Integer expid) {
	        this.expid = expid;
	    }

	    public String getAppkey() {
	        return appkey;
	    }

	    public void setAppkey(String appkey) {
	        this.appkey = appkey == null ? null : appkey.trim();
	    }

	    public String getAppsecret() {
	        return appsecret;
	    }

	    public void setAppsecret(String appsecret) {
	        this.appsecret = appsecret == null ? null : appsecret.trim();
	    }

	    public String getSessionkey() {
	        return sessionkey;
	    }

	    public void setSessionkey(String sessionkey) {
	        this.sessionkey = sessionkey == null ? null : sessionkey.trim();
	    }

	    public String getMemo() {
	        return memo;
	    }

	    public void setMemo(String memo) {
	        this.memo = memo == null ? null : memo.trim();
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(Date timestamp) {
	        this.timestamp = timestamp;
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

	    public Date getModifyDate() {
	        return modifyDate;
	    }

	    public void setModifyDate(Date modifyDate) {
	        this.modifyDate = modifyDate;
	    }
}