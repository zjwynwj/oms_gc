package com.dinghao.entity.vo.template.business.goods;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GdsAttbVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（商品可选属性值请求）
	  */
	
	private static final long serialVersionUID = -8032159678533890413L;

	private Long id;

    private String attbName;

    private Long createBy;

    private Date createDate;

    private Long modifyBy;

    private Date modifyDate;
    
    private Long clsId;
    
    private String attbs;
    
    private List<Long> clsIds;
	
	public List<Long> getClsIds() {
		return clsIds;
	}

	public void setClsIds(List<Long> clsIds) {
		this.clsIds = clsIds;
	}

	public String getAttbs() {
		return attbs;
	}

	public void setAttbs(String attbs) {
		this.attbs = attbs;
	}

	public Long getClsId() {
		return clsId;
	}

	public void setClsId(Long clsId) {
		this.clsId = clsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttbName() {
		return attbName;
	}

	public void setAttbName(String attbName) {
		this.attbName = attbName;
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
