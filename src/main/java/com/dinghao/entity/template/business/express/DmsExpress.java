package com.dinghao.entity.template.business.express;

import com.dinghao.entity.manage.BaseEntity;

public class DmsExpress extends BaseEntity{

    /**
	  * @Fields serialVersionUID : TODO（物流公司响应类）
	  */
	
	private static final long serialVersionUID = 1L;
	/** 物流编码 */
	private String code;
	/** 名称 */
    private String name;
	/** 联系号码 */
    private String linkPhone;
	/** 联系人 */
    private String linkMan;
	/** 启用状态(0-停用 1-启用) */
    private String actived;
	/** 普通面单格式文件地址 */
    private Long commonId;
	/** 电子面单格式文件地址 */
    private Long virtualId;
	/** 物流查询网址（暂不用） */
    private String queryUrl;
	/** 物流号正则 表达式 */
    private String expressReg;

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

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getActived() {
        return actived;
    }

    public void setActived(String actived) {
        this.actived = actived;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(Long virtualId) {
        this.virtualId = virtualId;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl == null ? null : queryUrl.trim();
    }

    public String getExpressReg() {
        return expressReg;
    }

    public void setExpressReg(String expressReg) {
        this.expressReg = expressReg == null ? null : expressReg.trim();
    }
}