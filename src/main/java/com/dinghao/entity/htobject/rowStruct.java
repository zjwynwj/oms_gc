package com.dinghao.entity.htobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;



public class rowStruct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<attrStruct> column;
	int stock;
	BigDecimal price;
	String sku;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public List<attrStruct> getColumn() {
		return column;
	}

	public void setColumn(List<attrStruct> column) {
		this.column = column;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public boolean has(attrStruct obj) {
		boolean result = false;
		for(attrStruct attr:this.column)
		{
			if(attr.value.equals(obj.value))
			{
				result=true;break;
			}
		}
		return result;
	}
	
	public boolean isEquals(List<attrStruct> obj) 
	{
		boolean result = true;
		for(attrStruct attr:obj)
		{
			if(!has(attr))
			{
				result=false; break;
			}
		}
		return result;
	}
}
