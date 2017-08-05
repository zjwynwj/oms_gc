package com.dinghao.service.template.wmstake.imp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.wmstake.WmsTakeItemDao;
import com.dinghao.entity.template.wmstake.WmsTake;
import com.dinghao.entity.template.wmstake.WmsTakeItem;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;
import com.dinghao.service.template.wmstake.WmsTakeItemService;

@Service
@Transactional
public class WmsTakeServiceItemImp implements WmsTakeItemService {
	@Autowired
	WmsTakeItemDao wmsTakeItemDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(WmsTakeItemVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(List<WmsTakeItemVo> list) {
		int flag = 0;
		//数据筛选(只有新增,没有更新的情况)
		Iterator<WmsTakeItemVo> iterator = list.iterator();
		while (iterator.hasNext()) {
			WmsTakeItemVo wmsTakeItemVo = (WmsTakeItemVo) iterator.next();
			if (!wmsTakeItemVo.getIsDeleted()) {
				for (WmsTakeItemVo wmsTakeItemVo1 : list) {
					if (wmsTakeItemVo1.getGdsId() == wmsTakeItemVo.getGdsId()
							&& wmsTakeItemVo1.getLocId() == wmsTakeItemVo
									.getLocId()) {
						wmsTakeItemVo1.setIsDeleted(false);// 设置无效
						wmsTakeItemVo.setSysQty(wmsTakeItemVo.getSysQty()
								+ wmsTakeItemVo1.getSysQty());
					}
					;
				}
			}
		}

		for (WmsTakeItemVo wmsTakeItemVo : list) {
			if (!wmsTakeItemVo.getIsDeleted()) {
				wmsTakeItemVo.setIsDeleted(true);
				flag++;
				wmsTakeItemVo.setCreateDate(new Date());
				wmsTakeItemDao.insertSelective(wmsTakeItemVo);
			}
		}
		return flag;
	}

	@Override
	public WmsTakeItem selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return wmsTakeItemDao.selectByPrimaryKey(id);
	}

	@Override
	public JqGridVo<WmsTakeItem> selectByStatement(WmsTakeItemVo record) {
		JqGridVo<WmsTakeItem> jqGridVo = new JqGridVo<WmsTakeItem>();
		jqGridVo.setList(wmsTakeItemDao.selectByStatement(record));
		jqGridVo.setRecords(wmsTakeItemDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}
	
	@Override
	public List<WmsTakeItem> getDiffItemlistByTakeid(long takeId) {
			
	
		return wmsTakeItemDao.getDiffItemlistByTakeid(takeId);
	}
	@Override
	public int selectByStatementCount(WmsTakeItemVo record) {
		// TODO Auto-generated method stub
		
		return wmsTakeItemDao.selectByStatementCount(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WmsTakeItemVo record) {
		// TODO Auto-generated method stub
		return wmsTakeItemDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateByPrimaryKeySelectives(List<WmsTakeItemVo> list) {
		int i = 0;
		int sysQty = 0;//系统库存
		int countQty =0;//盘点库存
		for (WmsTakeItemVo wmsTakeItemVo : list) {
			sysQty = wmsTakeItemVo.getSysQty()==null?0:wmsTakeItemVo.getSysQty();
			countQty =wmsTakeItemVo.getCountQty()==null?0:wmsTakeItemVo.getCountQty();
			wmsTakeItemVo.setDiffQty(sysQty-countQty);
			wmsTakeItemDao.updateByPrimaryKeySelective(wmsTakeItemVo);
			i++;
		}
		return i;
	}
	
	@Override
	public int updateBySelective(WmsTakeItemVo record) {
		return wmsTakeItemDao.updateBySelective(record);
	}
	@Override
	public int deleteWmsItemTakes(Integer[] ids) {
		int flag = 0;
		for (Integer integer : ids) {
			// 删除明细表的内容
			flag = wmsTakeItemDao.delteWmsTakeItemById(integer);
		}
		return flag;
	}

	@Override
	public int updateSysQty(Long takeId) {
		// TODO Auto-generated method stub
		
		wmsTakeItemDao.updateSysQty(takeId);
		return 0;
	}

}
