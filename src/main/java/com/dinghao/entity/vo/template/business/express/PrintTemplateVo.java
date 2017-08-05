package com.dinghao.entity.vo.template.business.express;

import java.util.Date;

import com.dinghao.entity.vo.manage.PageVo;

public class PrintTemplateVo extends PageVo<PrintTemplateVo>{
    /**
	  * @Fields serialVersionUID : TODO（打印模板请求类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long logisId;

    private String templatePic;

    private String name;

    private Integer templateDivW;

    private Integer templateDivH;

    private Integer divW;

    private Integer divH;

    private String itemVals;

    private String propVals;

    private Date updateTime;

    private String status;

    private String defaultFlag;

    private String type;

    private String logisName;
    
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

    public Long getLogisId() {
        return logisId;
    }

    public void setLogisId(Long logisId) {
        this.logisId = logisId;
    }

    public String getTemplatePic() {
        return templatePic;
    }

    public void setTemplatePic(String templatePic) {
        this.templatePic = templatePic == null ? null : templatePic.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTemplateDivW() {
        return templateDivW;
    }

    public void setTemplateDivW(Integer templateDivW) {
        this.templateDivW = templateDivW;
    }

    public Integer getTemplateDivH() {
        return templateDivH;
    }

    public void setTemplateDivH(Integer templateDivH) {
        this.templateDivH = templateDivH;
    }

    public Integer getDivW() {
        return divW;
    }

    public void setDivW(Integer divW) {
        this.divW = divW;
    }

    public Integer getDivH() {
        return divH;
    }

    public void setDivH(Integer divH) {
        this.divH = divH;
    }

    public String getItemVals() {
        return itemVals;
    }

    public void setItemVals(String itemVals) {
        this.itemVals = itemVals == null ? null : itemVals.trim();
    }

    public String getPropVals() {
        return propVals;
    }

    public void setPropVals(String propVals) {
        this.propVals = propVals == null ? null : propVals.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag == null ? null : defaultFlag.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLogisName() {
        return logisName;
    }

    public void setLogisName(String logisName) {
        this.logisName = logisName == null ? null : logisName.trim();
    }
}