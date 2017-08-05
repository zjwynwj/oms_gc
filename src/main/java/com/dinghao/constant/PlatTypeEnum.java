package com.dinghao.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PlatTypeEnum {
	// SG-手工 TB-淘宝 WSC—微商城
	SG("SG", "手工"), TB("TB", "淘宝"), WSC("WSC", "微商城");
	/**
	 * 名称
	 */
	private String value;
	/**
	 * 说明
	 */
	private String text;

	private PlatTypeEnum(String value, String text) {
		this.setValue(value);
		this.setText(text);
	}

	// 获取所有平台名称
	public static Map<String, String> getValues() {
		Map<String, String> values  =new HashMap<String, String>();
		for (PlatTypeEnum c : PlatTypeEnum.values()) {
			values.put(c.value, c.text);
		}
		return values;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
