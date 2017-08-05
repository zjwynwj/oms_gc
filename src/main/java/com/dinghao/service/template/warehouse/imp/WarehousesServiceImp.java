package com.dinghao.service.template.warehouse.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.warehouse.WarehouseDao;
import com.dinghao.dao.template.warehousepositions.WarehousePositionsDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.template.warehousepositions.WarehousePositions;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.warehouse.WarehouseVo;
import com.dinghao.entity.vo.template.warehousepositions.WarehousePositionsVo;
import com.dinghao.service.template.warehouse.WarehousesService;

@Service
@Transactional
public class WarehousesServiceImp implements WarehousesService {

	@Autowired
	WarehouseDao warehouseDao;
	@Autowired
	WarehousePositionsDao warehousePositionsDao;

	@Override
	public JqGridVo<Warehouse> getWarehouse(WarehouseVo warehouseVo) {
		JqGridVo<Warehouse> jqGridVo = new JqGridVo<Warehouse>();
		jqGridVo.setList(warehouseDao.getWarehouse(warehouseVo));
		jqGridVo.setRecords(warehouseDao.getWarehouseCount(warehouseVo));
		jqGridVo.setPageNum(warehouseVo.getPageNum());
		jqGridVo.setRows(warehouseVo.getRows());
		return jqGridVo;
	}

	@Override
	public int saveWarehouse(WarehouseVo warehouseVo) {
		if (StringUtils.isNotBlank(warehouseVo.getRemarks1())
				&& "1".equals(warehouseVo.getRemarks1())) {
			warehouseDao.updateRemarks1ToNull();
		}
		warehouseDao.insertSelective(warehouseVo);
		return warehouseVo.getId();
	}

	@Override
	public int updateWarehouse(WarehouseVo warehouseVo) {
		/**
		 * 设为默认(先去出默认的仓库)
		 */
		if (StringUtils.isNotBlank(warehouseVo.getRemarks1())
				&& "1".equals(warehouseVo.getRemarks1())) {
			warehouseDao.updateRemarks1ToNull();
		}
		return warehouseDao.updateByPrimaryKeySelective(warehouseVo);
	}

	@Override
	public Warehouse selectByPrimaryKey(Integer id) {
		return warehouseDao.selectByPrimaryKey(id);
	}

	@Override
	public JqGridVo<WarehousePositions> getWarehousePositions(
			WarehousePositionsVo vo) {
		JqGridVo<WarehousePositions> jqGridVo = new JqGridVo<WarehousePositions>();
		jqGridVo.setList(warehousePositionsDao.getWarehousePositions(vo));
		jqGridVo.setRecords(warehousePositionsDao
				.getWarehousePositionsCount(vo));
		jqGridVo.setPageNum(vo.getPageNum());
		jqGridVo.setRows(vo.getRows());
		return jqGridVo;
	}

	@Override
	public WarehousePositions selectWarehousePositionsByPrimaryKey(Integer id) {
		return warehousePositionsDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateWarehousePositions(
			WarehousePositionsVo warehousePositionsVo) {
		if (StringUtils.isNotBlank(warehousePositionsVo.getRemarks1())
				&& "1".equals(warehousePositionsVo.getRemarks1())) {
			warehousePositionsDao.updateByRemarks1(warehousePositionsVo);
		}
		return warehousePositionsDao
				.updateByPrimaryKeySelective(warehousePositionsVo);
	}

	@Override
	public int saveWarehousePositions(WarehousePositionsVo warehousePositionsVo) {
		if (StringUtils.isNotBlank(warehousePositionsVo.getRemarks1())
				&& "1".equals(warehousePositionsVo.getRemarks1())) {
			warehousePositionsDao.updateByRemarks1(warehousePositionsVo);
		}
		return warehousePositionsDao.insertSelective(warehousePositionsVo);
	}

	@Override
	public int deleteWarehousePositions(Integer id) {
		return warehousePositionsDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Warehouse> getWarehouses(WarehouseVo warehouseVo) {
		return warehouseDao.getWarehouse(warehouseVo);
	}
	@Override
	public CommResponse getDefalutWarehousePosinton(WarehouseVo warehouseVo){
		CommResponse commResponse = new CommResponse();
		WarehousePositionsVo warehousePositionsVo = new WarehousePositionsVo();
		warehousePositionsVo.setDhWarehousePositions(warehouseVo.getId());
		commResponse.setSuccess(true);
		commResponse.setData(warehousePositionsDao.queryDefaultPosition(warehousePositionsVo));
		
		return  commResponse;
	}

}
