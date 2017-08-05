/*
 * @ClassName:WmsTakeItemDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-01-22
 */
package com.dinghao.dao.template.wmstake;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.wmstake.WmsTakeItem;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;

@Repository
public interface WmsTakeItemDao {
	int deleteByPrimaryKey(Long id);

	int insert(WmsTakeItemVo record);

	int insertSelective(WmsTakeItemVo record);

	WmsTakeItem selectByPrimaryKey(Long id);

	List<WmsTakeItem> selectByStatement(WmsTakeItemVo record);

	int selectByStatementCount(WmsTakeItemVo record);

	int updateByPrimaryKeySelective(WmsTakeItemVo record);

	int delteWmsTakeItemByTag(Integer wmsTakeId);

	int delteWmsTakeItemById(Integer id);
	
	List<WmsTakeItem>  getDiffItemlistByTakeid(Long id);
	
	int updateSysQty(Long wmsTakeId);
	
	int updateBySelective(WmsTakeItemVo record);
}