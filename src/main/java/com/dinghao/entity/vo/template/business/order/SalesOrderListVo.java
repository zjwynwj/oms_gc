package com.dinghao.entity.vo.template.business.order;

import java.io.Serializable;
import java.util.List;

public class SalesOrderListVo implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（传输订单列表）
	  */
	
	private static final long serialVersionUID = 1L;
	private List<SalesOrderVo> salesOrderList;

	public List<SalesOrderVo> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrderVo> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}
	
}
