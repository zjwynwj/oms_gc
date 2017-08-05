package com.dinghao.service.template.wmstake;

import java.util.List;

import com.dinghao.entity.template.wmstake.WmsTakeItem;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;

public interface WmsTakeItemService {
	/**
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO(按照主键删除数据)
	 * @param @param id
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int deleteByPrimaryKey(Integer id);

	int insert(WmsTakeItemVo record);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(有条件的插入盘点单数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int insertSelective(List<WmsTakeItemVo> list);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(依据主键获取盘点单相关数据)
	 * @param @param id
	 * @param @return 设定文件
	 * @return WmsTake 返回类型
	 * @throws
	 */
	WmsTakeItem selectByPrimaryKey(Long id);

	/**
	 * 
	 * @Title: selectByStatement
	 * @Description: TODO(依据条件获取盘点单数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return List<WmsTake> 返回类型
	 * @throws
	 */
	JqGridVo<WmsTakeItem> selectByStatement(WmsTakeItemVo record);

	
	List<WmsTakeItem> getDiffItemlistByTakeid (long takeId);
	/**
	 * 
	 * @Title: selectByStatementCount
	 * @Description: TODO(统计数量)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int selectByStatementCount(WmsTakeItemVo record);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(更新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int updateByPrimaryKeySelective(WmsTakeItemVo record);

	/*
	 * 同步系统库存
	 */
	int updateSysQty(Long takeId);

	int deleteWmsItemTakes(Integer[] id);

	int updateBySelective(WmsTakeItemVo record);

	int updateByPrimaryKeySelectives(List<WmsTakeItemVo> list);

}
