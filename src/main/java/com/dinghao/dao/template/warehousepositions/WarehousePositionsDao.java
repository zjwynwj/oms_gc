package com.dinghao.dao.template.warehousepositions;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.warehousepositions.WarehousePositions;
import com.dinghao.entity.vo.template.warehousepositions.WarehousePositionsVo;

@Repository
public interface WarehousePositionsDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WarehousePositionsVo record);

	int insertSelective(WarehousePositionsVo record);

	WarehousePositions selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WarehousePositionsVo record);

	int updateByPrimaryKey(WarehousePositionsVo record);

	/**
	 * 获取相关条件下的WarehousePositions集合
	 * 
	 * @param warehouseVo
	 * @return
	 */
	List<WarehousePositions> getWarehousePositions(WarehousePositionsVo warehouseVo);

	/**
	 * 统计相关条件下的WarehousePositions个数
	 * 
	 * @param warehouseVo
	 * @return
	 */
	int getWarehousePositionsCount(WarehousePositionsVo warehouseVo);
	
	WarehousePositions queryDefaultPosition(WarehousePositionsVo warehouseVo);

	int updateByRemarks1(WarehousePositionsVo warehousePositionsVo);
}