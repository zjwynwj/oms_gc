package com.dinghao.service.template.business.order;

import java.util.List;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.order.SalesOrderListVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;

/**
  * @ClassName: ISalesOrderService
  * @Description: TODO  订单管理service接口
  * @author helong 
  * @date 2016年1月25日 下午6:51:19
  * @version V1.0
  *
 */
public interface ISalesOrderService {
	
	/**
	  * @Title: querySalesOrder
	  * @Description: TODO 查询订单信息
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public SalesOrderVo querySalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	public int updateSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	 * 
	 * @param topTids
	 * @return
	 * @throws Exception
	 */
	public List<SalesOrderVo>  querySalesOrderbyTid(String topTids) throws Exception;
	
	/**
	  * @Title: querySalesOrderItemList
	  * @Description: TODO  查询订单明细列表
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse querySalesOrderItemList(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: findSalesOrderForGrid
	  * @Description: TODO  分页查询订单数据
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findSalesOrderForGrid(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: combineSalesOrder
	  * @Description: TODO  合并订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse combineSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: cancelComSalesOrder
	  * @Description: TODO取消拆分订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse cancelComSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: auditSalesOrder
	  * @Description: TODO  审核订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse auditSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: cancelAuditSalesOrder
	  * @Description: TODO  取消订单审核
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse cancelAuditSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: findExistExpcode
	  * @Description: TODO   查询存在物流单号记录 
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findExistExpcode(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: addSalesOrder
	  * @Description: TODO  添加订单
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: modSalesOrder
	  * @Description: TODO 修改订单信息 
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modSalesOrder(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: modSalesOrderExpcode
	  * @Description: TODO   修改订单物流单号
	  * @param @param salesOrderListVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modSalesOrderExpcode(SalesOrderListVo salesOrderListVo) throws Exception;
	
	/**
	  * @Title: modSalesOrderExpress
	  * @Description: TODO  修改 订单物流公司 
	  * @param @param salesOrderListVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modSalesOrderPrintTemplate(SalesOrderListVo salesOrderListVo) throws Exception;
	
	/**
	  * @Title: deliverGoods
	  * @Description: TODO  订单发货
	  * @param @param salesOrderListVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse deliverGoods(SalesOrderListVo salesOrderListVo) throws Exception;
	
	/**
	  * @Title: findOrderDataByParam
	  * @Description: TODO  根据参数查询订单信息list
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findOrderDataByParam(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: findOrderItemListByParam
	  * @Description: TODO根据参数查询订单明细list
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findOrderItemListByParam(SalesOrderitemVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: saveInspectGoods
	  * @Description: TODO 保存扫描出库
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse saveInspectGoods(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: queryOrderStandWeight
	  * @Description: TODO  查询订单的标准重量
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryOrderStandWeight(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: saveOrderWeight
	  * @Description: TODO 保存订单重量
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse saveOrderWeight(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: queryOrderBatchPrintData
	  * @Description: TODO  查询订单批量打印数据
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryOrderBatchPrintData(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: savePrintStatus
	  * @Description: TODO  保存打印状态
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse savePrintStatus(SalesOrderVo salesOrderVo) throws Exception;
	
	/**
	  * @Title: saveOrderSplit
	  * @Description: TODO  保存订单拆分
	  * @param @param salesOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse saveOrderSplit(SalesOrderVo salesOrderVo) throws Exception;
}
