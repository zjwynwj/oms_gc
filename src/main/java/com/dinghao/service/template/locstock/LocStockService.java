package com.dinghao.service.template.locstock;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.locstock.LocStock;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;

public interface LocStockService {
	public CommResponse selectByStatement(LocStockVo locStockVo);
	public CommResponse selectByStatementDetail(LocStockVo locStockVo) throws Exception;
	public CommResponse queryLowerStockList(LocStockVo locStockVo) throws Exception;
	
	public CommResponse synGoodsQty(LocStockVo locStockVo) throws Exception;
	public int setLocStock(LocStockVo locStockVo);
	
	public JqGridVo<LocStockVo> selectByStatementDetailSelect2(
			LocStockVo locStockVo);
	int updateByPrimaryKeySelective(LocStockVo locStockVo);
	public int saveLocStock(LocStockVo locStockVo, int i);
}
