package com.dinghao.service.template.salesrtnorder;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.salesrtnorder.SalesRtnOrder;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderVo;

public interface SalesRtnOrderService {
	/**
	 * 
	 * @Title: selectByStatement
	 * @Description: TODO(依据条件获取信息)
	 * @param @param record
	 * @param @return 设定文件
	 * @return JqGridVo<T> 返回类型
	 * @throws
	 */
	public JqGridVo<SalesRtnOrder> selectByStatement(SalesRtnOrderVo record);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(保存信息)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public CommResponse insertSelective(SalesRtnOrderVo record);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(依据主键更新对象)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */

	public CommResponse updateByPrimaryKeySelective(SalesRtnOrderVo record) throws Exception  ;

	/**
	 * 依据主键获取对象
	 * 
	 * @param id
	 * @return
	 */
	public SalesRtnOrder selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int selectByStatementCount(SalesRtnOrderVo record);

	public CommResponse saveReceivedSalesRtnOrder(SalesRtnOrderVo salesRtnOrderVo) throws Exception ;

	public CommResponse  selectSalesRtnOrderItemsByStatement(SalesRtnOrderVo rtnId) throws Exception ;
}
