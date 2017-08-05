package com.dinghao.service.template.business.order;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;

public interface IExceptionOrderService {

	/**
	  * @Title: findExceptionSalesOrderForGrid
	  * @Description: TODO  分页查询异常订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findExceptionSalesOrderForGrid(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: modExceptionOrder
	  * @Description: TODO 修改异常订单信息
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modExceptionOrder(SalesOrderVo salesOrderVo) throws Exception;
}
