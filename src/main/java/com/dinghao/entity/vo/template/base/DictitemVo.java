package com.dinghao.entity.vo.template.base;

import java.io.Serializable;

public class DictitemVo implements Serializable{
    /**
	  * @Fields serialVersionUID : TODO（数据字典选项返回类）
	  */
	
	private static final long serialVersionUID = 1L;

	private Long dictitemId;

    private Long dictId;

    private String dictitemName;

    private String dictitemValue;

    private Integer sortNum;

    public Long getDictitemId() {
        return dictitemId;
    }

    public void setDictitemId(Long dictitemId) {
        this.dictitemId = dictitemId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictitemName() {
        return dictitemName;
    }

    public void setDictitemName(String dictitemName) {
        this.dictitemName = dictitemName == null ? null : dictitemName.trim();
    }

    public String getDictitemValue() {
        return dictitemValue;
    }

    public void setDictitemValue(String dictitemValue) {
        this.dictitemValue = dictitemValue == null ? null : dictitemValue.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}