package com.dinghao.entity.vo.template.business.member;

import java.math.BigDecimal;

import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.CustInfoVo;

public class MemberVo extends PageVo<MemberVo> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private Long id;
	 
	 
	 public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	private String keyWord;
	    /**
		 * 用户id，由平台决定，允许为空
		 */
	    private String userid;
	    /**
	   	 * 店铺id
	   	 */
	    private Long shopId;
	    private String shopName;
	    
	    public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

			/**
	   	 * 平台昵称
	   	 */
	    private String nick;
	    /**
	   	 * 平台用户名
	   	 */
	    private String username;
	    /**
	   	 * 用户地址
	   	 */
	    private String address;
	    /**
	   	 * 邮编
	   	 */
	    private String zip;
	    /**
	   	 * 固定电话
	   	 */
	    private String recvphone;
	    /**
	   	 * 手机
	   	 */
	    private String recvmobile;
	    /**
	   	 * 生日
	   	 */
	    private String birthday;
	    /**
	   	 * 邮箱
	   	 */
	    private String email;
	    /**
	   	 * 性别 男 女
	   	 */
	    private String sex;
	    /**
	   	 * 省份
	   	 */
	    private String provname;
	    /**
	   	 * 城市
	   	 */
	    private String cityname;
	    /**
	   	 * 区县
	   	 */
	    private String countyname;
	    /**
	   	 * 省份id
	   	 */
	    private Long provId;
	    /**
	   	 * 城市id
	   	 */
	    private Long cityId;
	    /**
	   	 * 区县id
	   	 */
	    private Long countyId;
	    /**
	   	 * 客单价
	   	 */
	    private BigDecimal averagecost;
	    /**
	   	 * 购买次数
	   	 */
	    private Integer buytimes;


	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getShopId() {
			return shopId;
		}

		public void setShopId(Long shopId) {
			this.shopId = shopId;
		}

		public Long getProvId() {
			return provId;
		}

		public void setProvId(Long provId) {
			this.provId = provId;
		}

		public Long getCityId() {
			return cityId;
		}

		public void setCityId(Long cityId) {
			this.cityId = cityId;
		}

		public Long getCountyId() {
			return countyId;
		}

		public void setCountyId(Long countyId) {
			this.countyId = countyId;
		}

		public String getUserid() {
	        return userid;
	    }

	    public void setUserid(String userid) {
	        this.userid = userid == null ? null : userid.trim();
	    }

	  

	    public String getNick() {
	        return nick;
	    }

	    public void setNick(String nick) {
	        this.nick = nick == null ? null : nick.trim();
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address == null ? null : address.trim();
	    }

	    public String getZip() {
	        return zip;
	    }

	    public void setZip(String zip) {
	        this.zip = zip == null ? null : zip.trim();
	    }

	    public String getRecvphone() {
	        return recvphone;
	    }

	    public void setRecvphone(String recvphone) {
	        this.recvphone = recvphone == null ? null : recvphone.trim();
	    }

	    public String getRecvmobile() {
	        return recvmobile;
	    }

	    public void setRecvmobile(String recvmobile) {
	        this.recvmobile = recvmobile == null ? null : recvmobile.trim();
	    }

	    public String getBirthday() {
	        return birthday;
	    }

	    public void setBirthday(String birthday) {
	        this.birthday = birthday == null ? null : birthday.trim();
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email == null ? null : email.trim();
	    }

	    public String getSex() {
	        return sex;
	    }

	    public void setSex(String sex) {
	        this.sex = sex == null ? null : sex.trim();
	    }

	    public String getProvname() {
	        return provname;
	    }

	    public void setProvname(String provname) {
	        this.provname = provname == null ? null : provname.trim();
	    }

	    public String getCityname() {
	        return cityname;
	    }

	    public void setCityname(String cityname) {
	        this.cityname = cityname == null ? null : cityname.trim();
	    }

	    public String getCountyname() {
	        return countyname;
	    }

	    public void setCountyname(String countyname) {
	        this.countyname = countyname == null ? null : countyname.trim();
	    }





	    public BigDecimal getAveragecost() {
	        return averagecost;
	    }

	    public void setAveragecost(BigDecimal averagecost) {
	        this.averagecost = averagecost;
	    }

	    public Integer getBuytimes() {
	        return buytimes;
	    }

	    public void setBuytimes(Integer buytimes) {
	        this.buytimes = buytimes;
	    }
}