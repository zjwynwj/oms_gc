package com.dinghao.entity.vo.template.business.express;

import com.dinghao.entity.vo.manage.PageVo;

public class DmsExpressVo extends PageVo<DmsExpressVo>{
    /**
	  * @Fields serialVersionUID : TODO（物流公司请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String code;

    private String name;

    private String linkPhone;

    private String linkMan;

    private String actived;

    private Long commonId;

    private Long virtualId;

    private String queryUrl;

    private String expressReg;
    
    private String keyWord;

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