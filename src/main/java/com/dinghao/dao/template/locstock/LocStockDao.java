/*
 * @ClassName:LocStockDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-01-26
 */
package com.dinghao.dao.template.locstock;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.locstock.LocStock;
import com.dinghao.entity.vo.template.locstock.LocStockVo;

@Repository
public interface LocStockDao {
	int deleteByPrimaryKey(Long id);

	int insert(LocStockVo record);

	int insertSelective(LocStockVo record);

	LocStock selectByPrimaryKey(Long id);

	List<LocStockVo> selectByStatement(LocStockVo record);

	List<LocStockVo> selectByStatementDetail(LocStockVo record);

	int selectByStatementCount(LocStockVo record);
	


	int updateByPrimaryKeySelective(LocStockVo record);
	
	int updateByKeySelective(LocStockVo record);

	int selectByStatementDetailCount(LocStockVo locStockVo);

	List<LocStockVo> selectByStatementDetailSelect2(LocStockVo locStockVo);
	
	//何龙添加(查询最大可用库存库位)
	LocStockVo selectMastuseQtyByParam(LocStockVo locStockVo);
	
	//何龙添加(根据库位  仓库  商品等参数  查询现货信息)
	List<LocStockVo> selectLocStockByParam(LocStockVo locStockVo);
}