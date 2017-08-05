package com.dinghao.service.template.business.purchase;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;

/**
  * @ClassName: PurOrderService
  * @Description: TODO   采购订单管理 service接口
  * @author helong 
  * @date 2016年1月7日 上午11:00:21
  * @version V1.0
  *
 */
public interface PurOrderService {

	/**
	  * @Title: addPurOrder
	  * @Description: TODO   添加采购订单
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addPurOrder(PurOrderDataVo purOrderDataVo) throws Exception;
	
	/**
	  * @Title: modPurOrder
	  * @Description: TODO   修改采购订单
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modPurOrder(PurOrderDataVo purOrderDataVo) throws Exception;
	
	/**
	  * @Title: queryPurOrderById
	  * @Description: TODO  根据id查询采购订单信息
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryPurOrderById(PurOrderVo purOrderVo) throws Exception;
	
	/**
	  * @Title: findPurOrderForGrid
	  * @Description: TODO  分页查询订单信息
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findPurOrderForGrid(PurOrderVo purOrderVo) throws Exception;
	
	/**
	  * @Title: findPurOrderDetailList
	  * @Description: TODO  查询采购 订单明细列表
	  * @param @param purOrderVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findPurOrderDetailListByPurOrderId(PurOrderVo purOrderVo) throws Exception;
	
	/**
	  * @Title: savePurOrderReceiptOrOutBound
	  * @Description: TODO 生成入库或出库单
	  * @param @param receiptVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse savePurOrderReceiptOrOutBound(ReceiptVo receiptVo) throws Exception;
	
	
	public CommResponse getDetailListBySugIds(String suggestGoodsids) throws Exception; 
}
