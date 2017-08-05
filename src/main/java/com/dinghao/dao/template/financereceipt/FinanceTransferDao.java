/*
 * @ClassName:FinanceTransferDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-03-03
 */
package com.dinghao.dao.template.financereceipt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.financereceipt.FinanceTransfer;
import com.dinghao.entity.vo.template.financereceipt.FinanceTransferVo;

@Repository
public interface FinanceTransferDao {
	int deleteByPrimaryKey(Long id);

	int insert(FinanceTransferVo record);

	int insertSelective(FinanceTransferVo record);

	FinanceTransfer selectByPrimaryKey(Long id);

	List<FinanceTransfer> selectByStatement(FinanceTransferVo record);

	int selectByStatementCount(FinanceTransferVo record);

	int updateByPrimaryKeySelective(FinanceTransferVo record);
}