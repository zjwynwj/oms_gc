package com.dinghao.entity.htobject;

import java.io.Serializable;
import java.util.List;

public class tableStruct implements Serializable{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<rowStruct> row;
	List<String> column;

	public List<rowStruct> getRow() {
		return row;
	}

	public void setRow(List<rowStruct> row) {
		this.row = row;
	}

	public List<String> getColumn() {
		return column;
	}

	public void setColumn(List<String> column) {
		this.column = column;
	}
}