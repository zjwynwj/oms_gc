package com.dinghao.entity.template.business.goods;

import com.dinghao.entity.manage.BaseEntity;

public class GdsCls extends BaseEntity{

    /**
	  * @Fields serialVersionUID : TODO（商品分类返回类）
	  */
	private static final long serialVersionUID = -1789046071901322996L;
	//分类 编号
	private String clsNo;
	//父分类编号
    private String clsPno;
    //分类级别
    private Integer level;
    //分类名称
    private String clsName;
    //分类状态
    private String clsStatus;
    //税率
    private Double taxRate;

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getClsNo() {
		return clsNo;
	}

	public void setClsNo(String clsNo) {
		this.clsNo = clsNo;
	}

	public String getClsPno() {
		return clsPno;
	}

	public void setClsPno(String clsPno) {
		this.clsPno = clsPno;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public String getClsStatus() {
		return clsStatus;
	}

	public void setClsStatus(String clsStatus) {
		this.clsStatus = clsStatus;
	}
    
}