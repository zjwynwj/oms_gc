package com.dinghao.entity.vo.template.business.goods;

import java.math.BigDecimal;
import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class GdsInfoVo extends PageVo<GdsInfoVo> {
    /**
	  * @Fields serialVersionUID : TODO（商品信息请求类）
	  */
	
	private static final long serialVersionUID = 5730572124179952786L;

	private Long id;

    private String gdsNo;

    private String gdsName;

    private String gdsFormat;

    private String gdsPact;

    private String brand;
    private String ingredient;
    private String purpose;
    private String producer;
    private String countrycode;
    private String countryname;
    private String expiration;
          
    public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	private Long clsId;

    private BigDecimal gdsSelPrice;
    
    private BigDecimal standWeight;

    private String cal;

    private String attbs;

    private String gdsStatus;

    private BigDecimal gdsLowAmount;

    private BigDecimal gdsHighAmount;

    private String imgPath;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;
    
    private String keyWord;
    
    private String clsIds[];
    
    private String clsNo;
    
    private String ids;
    
    private String skuOuterId;
    
    private String gdsFlag;
    
    private String artNo;
    
    private String generatePact;
    
	public BigDecimal getStandWeight() {
		return standWeight;
	}

	public void setStandWeight(BigDecimal standWeight) {
		this.standWeight = standWeight;
	}

	public String getGdsFlag() {
		return gdsFlag;
	}

	public void setGdsFlag(String gdsFlag) {
		this.gdsFlag = gdsFlag;
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getGeneratePact() {
		return generatePact;
	}

	public void setGeneratePact(String generatePact) {
		this.generatePact = generatePact;
	}

	public String getSkuOuterId() {
		return skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getClsNo() {
		return clsNo;
	}

	public void setClsNo(String clsNo) {
		this.clsNo = clsNo;
	}

	public String[] getClsIds() {
		return clsIds;
	}

	public void setClsIds(String[] clsIds) {
		this.clsIds = clsIds;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGdsNo() {
        return gdsNo;
    }

    public void setGdsNo(String gdsNo) {
        this.gdsNo = gdsNo == null ? null : gdsNo.trim();
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getGdsFormat() {
        return gdsFormat == null ? null : gdsFormat.trim();
    }

    public void setGdsFormat(String gdsFormat) {
        this.gdsFormat = gdsFormat == null ? null : gdsFormat.trim();
    }

    public String getGdsPact() {
        return gdsPact;
    }

    public void setGdsPact(String gdsPact) {
        this.gdsPact = gdsPact == null ? null : gdsPact.trim();
    }

    public Long getClsId() {
        return clsId;
    }

    public void setClsId(Long clsId) {
        this.clsId = clsId;
    }

    public BigDecimal getGdsSelPrice() {
        return gdsSelPrice;
    }

    public void setGdsSelPrice(BigDecimal gdsSelPrice) {
        this.gdsSelPrice = gdsSelPrice;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getAttbs() {
        return attbs;
    }

    public void setAttbs(String attbs) {
        this.attbs = attbs == null ? null : attbs.trim();
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public BigDecimal getGdsLowAmount() {
        return gdsLowAmount;
    }

    public void setGdsLowAmount(BigDecimal gdsLowAmount) {
        this.gdsLowAmount = gdsLowAmount;
    }

    public BigDecimal getGdsHighAmount() {
        return gdsHighAmount;
    }

    public void setGdsHighAmount(BigDecimal gdsHighAmount) {
        this.gdsHighAmount = gdsHighAmount;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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