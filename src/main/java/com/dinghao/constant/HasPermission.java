package com.dinghao.constant;

public enum HasPermission {
	UPDATE("update", new int[] { 4, 5, 6, 7 }), DELETE("delete", new int[] { 2,
			3, 4, 7 }), ADD("add", new int[] { 1, 3, 5, 7 });
	private String name;
	int[] values = new int[4];

	public int[] getValues() {
		return values;
	}

	HasPermission(String name, int[] values) {
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}
}
