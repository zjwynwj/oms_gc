package com.dinghao.entity.htobject;

import java.io.Serializable;


public class attrStruct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String attr;

	String data;
	String value;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}