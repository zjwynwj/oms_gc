/*
 * @ClassName:FinancePaymentDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-02-24
 */
package com.dinghao.dao.template.financereceipt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.financereceipt.FinancePayment;
import com.dinghao.entity.vo.template.financereceipt.FinancePaymentVo;

@Repository
public interface FinancePaymentDao {
	int deleteByPrimaryKey(Long id);

	int insert(FinancePaymentVo record);

	int insertSelective(FinancePaymentVo record);

	FinancePayment selectByPrimaryKey(Long id);

	List<FinancePayment> selectByStatement(FinancePaymentVo record);

	int selectByStatementCount(FinancePaymentVo record);

	int updateByPrimaryKeySelective(FinancePaymentVo record);
}