/*
 * @ClassName:WmsStockLogDao.java
 * @Description: TODO(������һ�仰��������������) 
 * @author: 
 *-----------Zihan--���ÿƼ� ��Ȩ����------------
 * @date 2016-02-29
 */
package com.dinghao.dao.template.wmswtocklog;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.wmswtocklog.WmsStockLog;
import com.dinghao.entity.vo.template.wmswtocklog.WmsStockLogVo;

@Repository
public interface WmsStockLogDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WmsStockLogVo record);

	int insertSelective(WmsStockLogVo record);

	WmsStockLog selectByPrimaryKey(Integer id);

	List<WmsStockLog> selectByStatement(WmsStockLogVo record);

	int selectByStatementCount(WmsStockLogVo record);

	int updateByPrimaryKeySelective(WmsStockLogVo record);

	int updateBySelective(WmsStockLogVo wmsStockLogVo);

	/**
	 * 
	 * @Title: selectByStatementByParam
	 * @Description: TODO(监测是否已有日志信息)
	 * @param @param wmsStockLogVo
	 * @param @return 设定文件
	 * @return List<WmsStockLog> 返回类型
	 * @throws
	 */
	List<WmsStockLog> selectByStatementByParam(WmsStockLogVo wmsStockLogVo);
}