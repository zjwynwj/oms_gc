package com.dinghao.entity.template.business.goods;

import java.math.BigDecimal;

import com.dinghao.entity.manage.BaseEntity;

public class GdsInfo extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（商品信息返回类）
	  */
	
	private static final long serialVersionUID = 3260481369407286917L;
	//商品编号
    private String gdsNo;
    //商品名称
    private String gdsName;
    //商品规格
    private String gdsFormat;
    //商品编码
    private String gdsPact;
    //商品分类id
    private Long clsId;
    //销售价格
    private BigDecimal gdsSelPrice;
    //标准重量
    private BigDecimal standWeight;
    //计量的单位
    private String cal;
    //商品属性
    private String attbs;
    //商品状态
    private String gdsStatus;
    //最低库存
    private BigDecimal gdsLowAmount;
    //最高库存
    private BigDecimal gdsHighAmount;
    //图片路径
    private String imgPath;
    //备注
    private String remark;
    //商品分类名称
    private String clsName;
    //gdsNo gdsName gdsFormat  组合的字段  用于下拉显示
    private String gdsShowInfo;
    //sku级别 商家外部编码
    private String skuOuterId;
    //商品标识(1-下载商品  其他为平台生成)
    private String gdsFlag;
    //货号
    private String artNo;
    //生成的条码
    private String generatePact;

    
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
		return skuOuterId==null?"":skuOuterId;
	}

	public void setSkuOuterId(String skuOuterId) {
		this.skuOuterId = skuOuterId;
	}

	public String getGdsShowInfo() {
		return gdsShowInfo;
	}

	public void setGdsShowInfo(String gdsShowInfo) {
		this.gdsShowInfo = gdsShowInfo;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
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
        return gdsFormat;
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

}