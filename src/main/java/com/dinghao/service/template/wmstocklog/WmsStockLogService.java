package com.dinghao.service.template.wmstocklog;

import com.dinghao.entity.vo.template.wmswtocklog.WmsStockLogVo;

public interface WmsStockLogService {
	/**
	 * 
	* @Title: SaveWmsStockLog 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wmsStockLogVo
	* @param @param newqty
	* @param @param type
	* @param @param flag 0直接注入数量,1原有加(+),2原有减(-)
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int SaveWmsStockLog(WmsStockLogVo wmsStockLogVo,Long newqty,String type,int flag);
}
