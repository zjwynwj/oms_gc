/*
 * @ClassName:WmsTakeDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-01-21
 */
package com.dinghao.dao.template.wmstake;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.wmstake.WmsTake;
import com.dinghao.entity.vo.template.wmstake.WmsTakeVo;

@Repository
public interface WmsTakeDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WmsTakeVo record);

	int insertSelective(WmsTakeVo record);

	WmsTake selectByPrimaryKey(Integer id);

	List<WmsTake> selectByStatement(WmsTakeVo record);

	int selectByStatementCount(WmsTakeVo record);

	int updateByPrimaryKeySelective(WmsTakeVo record);

	int delteWmsTakeByTag(Integer id);
}