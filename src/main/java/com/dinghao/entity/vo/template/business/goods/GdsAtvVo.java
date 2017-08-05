package com.dinghao.entity.vo.template.business.goods;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GdsAtvVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（商品可选属性名类请求）
	  */
	
	private static final long serialVersionUID = 6795537616159097734L;

	private Long id;

    private Long attbId;

    private String attbValue;

    private Date createDate;

    private Long createBy;

    private Date modifyDate;

    private Long modifyBy;
    
    private String delAtvId;
    
    private Long clsId;
    
    private List<Long> clsIds;
	
	public List<Long> getClsIds() {
		return clsIds;
	}

	public void setClsIds(List<Long> clsIds) {
		this.clsIds = clsIds;
	}
	
	public Long getClsId() {
		return clsId;
	}

	public void setClsId(Long clsId) {
		this.clsId = clsId;
	}

	public String getDelAtvId() {
		return delAtvId;
	}

	public void setDelAtvId(String delAtvId) {
		this.delAtvId = delAtvId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAttbId() {
		return attbId;
	}

	public void setAttbId(Long attbId) {
		this.attbId = attbId;
	}

	public String getAttbValue() {
		return attbValue;
	}

	public void setAttbValue(String attbValue) {
		this.attbValue = attbValue;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}
    
}
