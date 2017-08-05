package com.dinghao.entity.template.base;

import java.io.Serializable;

public class Dict implements Serializable{
    /**
	  * @Fields serialVersionUID : TODO（数据字典请求类）
	  */
	private static final long serialVersionUID = 1L;

	private Long dictId;

    private String dictName;

    private String dictValue;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }
}