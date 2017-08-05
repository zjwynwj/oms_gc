/*
* @ClassName:FinanceReceiptDao.java
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: 
*-----------Zihan--鼎好科技 版权所有------------
* @date 2016-02-23
*/
package com.dinghao.dao.template.financereceipt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.financereceipt.FinanceReceipt;
import com.dinghao.entity.vo.template.financereceipt.FinanceReceiptVo;

@Repository
public interface FinanceReceiptDao {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceReceiptVo record);

    int insertSelective(FinanceReceiptVo record);

    FinanceReceipt selectByPrimaryKey(Long id);

    List<FinanceReceipt> selectByStatement(FinanceReceiptVo record);

    int selectByStatementCount(FinanceReceiptVo record);

    int updateByPrimaryKeySelective(FinanceReceiptVo record);
}