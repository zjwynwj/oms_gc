package com.dinghao.entity.template.base;

import com.dinghao.entity.manage.BaseEntity;

public class BaseArea extends BaseEntity{
    /**
	  * @Fields serialVersionUID : TODO（地区类响应类）
	  */
	
	private static final long serialVersionUID = 1L;

    private Long parentId;

    private Long tmallAreaid;

    private Long tmallParentid;

    private String regionName;

    private Integer regionType;

    private String zipcode;

    private String quhao;

    private Boolean status;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTmallAreaid() {
        return tmallAreaid;
    }

    public void setTmallAreaid(Long tmallAreaid) {
        this.tmallAreaid = tmallAreaid;
    }

    public Long getTmallParentid() {
        return tmallParentid;
    }

    public void setTmallParentid(Long tmallParentid) {
        this.tmallParentid = tmallParentid;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getQuhao() {
        return quhao;
    }

    public void setQuhao(String quhao) {
        this.quhao = quhao == null ? null : quhao.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}