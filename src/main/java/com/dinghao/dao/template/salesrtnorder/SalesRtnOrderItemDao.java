/*
 * @ClassName:SalesRtnOrderItemDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-03-16
 */
package com.dinghao.dao.template.salesrtnorder;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.salesrtnorder.SalesRtnOrderItem;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderItemVo;

@Repository
public interface SalesRtnOrderItemDao {
	int deleteByPrimaryKey(Integer id);

	int insert(SalesRtnOrderItemVo record);

	int insertSelective(SalesRtnOrderItemVo record);

	SalesRtnOrderItem selectByPrimaryKey(Integer id);

	List<SalesRtnOrderItem> selectByStatement(SalesRtnOrderItemVo record);

	int selectByStatementCount(SalesRtnOrderItemVo record);

	int updateByPrimaryKeySelective(SalesRtnOrderItemVo record);

	int deleteByRtnId(Long rtnId);
}