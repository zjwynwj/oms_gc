package com.dinghao.service.template.financereceipt;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.financereceipt.FinancePayment;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.financereceipt.FinancePaymentVo;

public interface FinancePaymentService {
	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO(按照主键删除数据)
	 * @param @param id
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int deleteByPrimaryKey(Integer id);

	int insert(FinancePaymentVo record);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(有条件的插入盘点单数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	CommResponse insertSelective(FinancePaymentVo record) throws Exception;

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(依据主键获取盘点单相关数据)
	 * @param @param id
	 * @param @return 设定文件
	 * @return WmsTake 返回类型
	 * @throws
	 */
	FinancePayment selectByPrimaryKey(Integer id);

	/**
	 * 
	 * @Title: selectByStatement
	 * @Description: TODO(依据条件获取盘点单数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return List<WmsTake> 返回类型
	 * @throws
	 */
	JqGridVo<FinancePayment> selectByStatement(FinancePaymentVo record);

	/**
	 * 
	 * @Title: selectByStatementCount
	 * @Description: TODO(统计数量)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int selectByStatementCount(FinancePaymentVo record);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(更新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	CommResponse updateByPrimaryKeySelective(FinancePaymentVo record) throws Exception;

	int deleteWmsTakes(Integer[] id);

	FinancePayment selectByPrimaryKey(Long id);
}
