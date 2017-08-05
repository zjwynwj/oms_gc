/*
 * @ClassName:TemplateAdminDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-03-07
 */
package com.dinghao.dao.template.templateadmin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.vo.template.templateadmin.TemplateAdminVo;

@Repository
public interface TemplateAdminDao {
	int deleteByPrimaryKey(Long id);

	int insert(TemplateAdmin record);

	int insertSelective(TemplateAdminVo record);

	TemplateAdmin selectByPrimaryKey(Long id);

	List<TemplateAdmin> selectByStatement(TemplateAdminVo record);

	int selectByStatementCount(TemplateAdminVo record);

	int updateByPrimaryKeySelective(TemplateAdminVo record);

	TemplateAdmin getAdminByName(String userName);
}