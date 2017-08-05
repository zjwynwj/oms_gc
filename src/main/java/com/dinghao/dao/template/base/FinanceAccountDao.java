package com.dinghao.dao.template.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;

@Repository
public interface FinanceAccountDao {
	int deleteByPrimaryKey(Long id) throws Exception;

	int insert(FinanceAccount record) throws Exception;

	int insertSelective(FinanceAccount record) throws Exception;

	FinanceAccount selectByPrimaryKey(Long id) throws Exception;

	int updateByPrimaryKeySelective(FinanceAccountVo record) throws Exception;

	int updateByPrimaryKey(FinanceAccountVo record) throws Exception;
	 int selectByStatementCount(FinanceAccountVo record);
	List<FinanceAccount> selectAccountListPage(FinanceAccountVo financeAccount)
			throws Exception;

}