package com.dinghao.service.template.business.order;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;

public interface IDownSalesOrderService {

	/**
	  * @Title: downLoadSalesOrder
	  * @Description: TODO  下载订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse downLoadSalesOrder(SalesOrderVo salesOrderVo) throws Exception;

}
