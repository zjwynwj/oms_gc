package com.dinghao.service.template.wmstocklog.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinghao.dao.template.wmswtocklog.WmsStockLogDao;
import com.dinghao.entity.template.wmswtocklog.WmsStockLog;
import com.dinghao.entity.vo.template.wmswtocklog.WmsStockLogVo;
import com.dinghao.service.template.wmstocklog.WmsStockLogService;
import com.dinghao.util.BeanUtils;

@Service
public class WmsStockLogServiceImp implements WmsStockLogService {
	@Autowired
	WmsStockLogDao wmsStockLogDao;

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int SaveWmsStockLog(WmsStockLogVo wmsStockLogVo, Long newqty,
			String type, int flag) {
		int result = 0;
		wmsStockLogVo.setOldqty(0l);
		wmsStockLogVo.setAddqty(0l);
		wmsStockLogVo.setNewqty(newqty);
		wmsStockLogVo.setType(type);
		List<WmsStockLog> wmsStockLog = wmsStockLogDao
				.selectByStatementByParam(wmsStockLogVo);
		if (wmsStockLog.size() > 0) {
			try {
				BeanUtils.copyProperties(wmsStockLogVo, wmsStockLog.get(0));
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage());
			}
			wmsStockLogVo.setOldqty(wmsStockLogVo.getNewqty());// 原始
			if (flag == 0) {//盘点
				wmsStockLogVo.setNewqty(newqty);
				wmsStockLogVo.setAddqty((wmsStockLogVo.getNewqty() == null ? 0l
						: wmsStockLogVo.getNewqty())
						- (wmsStockLogVo.getOldqty() == null ? 0l
								: wmsStockLogVo.getOldqty()));
			} else if (flag == 1) {//入库
				wmsStockLogVo.setNewqty((long) (wmsStockLogVo.getNewqty()
						.intValue() + newqty.intValue()));// 实际库存
				wmsStockLogVo.setAddqty(newqty);// 新增库存
			}else if(flag==2){//出库
				wmsStockLogVo.setNewqty((long) (wmsStockLogVo.getNewqty()
						.intValue() - newqty.intValue()));// 实际库存
				wmsStockLogVo.setAddqty((long)(0-newqty.intValue()));// 新增库存
			}
			result = wmsStockLogDao.updateBySelective(wmsStockLogVo);

		} else {
			result = wmsStockLogDao.insertSelective(wmsStockLogVo);
		}
		return result;
	}

}
