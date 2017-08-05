package com.dinghao.service.template.warehouse;

import java.util.List;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.template.warehousepositions.WarehousePositions;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.entity.vo.template.warehousepositions.WarehousePositionsVo;

public interface WarehousesService {
	/**
	 * 依据条件获取仓库信息
	 * 
	 * @param warehouseVo
	 * @return
	 */
	public JqGridVo<Warehouse> getWarehouse(WarehouseVo warehouseVo);

	/**
	 * 保存仓库信息
	 * 
	 * @param warehouseVo
	 * @return
	 */
	public int saveWarehouse(WarehouseVo warehouseVo);

	/**
	 * 修改仓库信息
	 * 
	 * @param warehouseVo
	 * @return
	 */
	public int updateWarehouse(WarehouseVo warehouseVo);

	/**
	 * 依据主键获取对象
	 * 
	 * @param id
	 * @return
	 */
	public Warehouse selectByPrimaryKey(Integer id);

	/**
	 * 依据条件获取库位信息
	 * 
	 * @param warehouseVo
	 * @return
	 */
	public JqGridVo<WarehousePositions> getWarehousePositions(
			WarehousePositionsVo vo);

	/**
	 * 获取仓位信息
	 * 
	 * @param id
	 * @return
	 */
	public WarehousePositions selectWarehousePositionsByPrimaryKey(Integer id);

	/**
	 * 修改仓位信息
	 * 
	 * @param warehousePositionsVo
	 * @return
	 */
	public int updateWarehousePositions(
			WarehousePositionsVo warehousePositionsVo);

	/**
	 * 新增仓位信息
	 * 
	 * @param warehousePositionsVo
	 * @return
	 */
	public int saveWarehousePositions(WarehousePositionsVo warehousePositionsVo);

	/**
	 * 删除仓位信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteWarehousePositions(Integer id);
	/**
	 * 
	* @Title: getWarehouses 
	* @Description: TODO(获取Warehouse对象) 
	* @param @param warehouseVo
	* @param @return    设定文件 
	* @return List<Warehouse>    返回类型 
	* @throws
	 */
	public List<Warehouse> getWarehouses(WarehouseVo warehouseVo);
	
	public CommResponse getDefalutWarehousePosinton(WarehouseVo warehouseVo);
}
