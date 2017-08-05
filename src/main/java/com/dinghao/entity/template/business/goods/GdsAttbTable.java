package com.dinghao.entity.template.business.goods;

import java.io.Serializable;
import java.util.List;

public class GdsAttbTable implements Serializable{
	
	/**
	  * @Fields serialVersionUID : TODO（用于组装前台属性显示所需要的数据格式）
	  */
	
	private static final long serialVersionUID = 1L;
	//存放该属性下的  可选属性值集合
	private List<GdsAtv> gdsAtvList;
	//属性id
	private Long attbId;
	//属性名称
	private String attbName;

	public List<GdsAtv> getGdsAtvList() {
		return gdsAtvList;
	}

	public void setGdsAtvList(List<GdsAtv> gdsAtvList) {
		this.gdsAtvList = gdsAtvList;
	}

	public Long getAttbId() {
		return attbId;
	}

	public void setAttbId(Long attbId) {
		this.attbId = attbId;
	}

	public String getAttbName() {
		return attbName;
	}

	public void setAttbName(String attbName) {
		this.attbName = attbName;
	}
}
