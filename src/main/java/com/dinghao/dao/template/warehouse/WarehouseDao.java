package com.dinghao.dao.template.warehouse;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
@Repository
public interface WarehouseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseVo record);

    int insertSelective(WarehouseVo record);

    Warehouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarehouseVo record);

    int updateByPrimaryKey(WarehouseVo record);
    
    /**
     * 获取相关条件下的Warehouse 集合
     * @param warehouseVo
     * @return
     */
    List<Warehouse> getWarehouse(WarehouseVo warehouseVo);
    /**
     * 统计相关条件下的Warehouse个数
     * @param warehouseVo
     * @return
     */
	int getWarehouseCount(WarehouseVo warehouseVo);
	
	Warehouse queryDefaultWareHouse();

	int updateRemarks1ToNull();
}