/*
 * @ClassName:SalesRtnOrderDao.java
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: 
 *-----------Zihan--鼎好科技 版权所有------------
 * @date 2016-03-15
 */
package com.dinghao.dao.template.salesrtnorder;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.salesrtnorder.SalesRtnOrder;
import com.dinghao.entity.vo.template.salesrtnorder.SalesRtnOrderVo;

@Repository
public interface SalesRtnOrderDao {
	int deleteByPrimaryKey(Long rtnId);

	int insert(SalesRtnOrderVo record);

	int insertSelective(SalesRtnOrderVo record);

	SalesRtnOrder selectByPrimaryKey(Long rtnId);

	List<SalesRtnOrder> selectByStatement(SalesRtnOrderVo record);

	int selectByStatementCount(SalesRtnOrderVo record);

	int updateByPrimaryKeySelective(SalesRtnOrderVo record);

	int updateByPrimaryKeyWithBLOBs(SalesRtnOrderVo record);
}