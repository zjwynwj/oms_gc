package com.dinghao.service.template.wmstake.imp;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.wmstake.WmsTakeDao;
import com.dinghao.dao.template.wmstake.WmsTakeItemDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.wmstake.WmsTake;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDetailVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;
import com.dinghao.entity.vo.template.wmstake.WmsTakeVo;
import com.dinghao.service.template.wmstake.WmsTakeService;

@Service
@Transactional
public class WmsTakeServiceImp implements WmsTakeService {
	@Autowired
	WmsTakeDao wmsTakeDao;
	@Autowired
	WmsTakeItemDao wmsTakeItemDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(WmsTakeVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse insertSelective(WmsTakeVo record) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		record.setIsDeleted(true);
		
		if(record.getId()!=null){
			wmsTakeDao.updateByPrimaryKeySelective(record);
		}else{
			//wmsTakeDao.insertSelective(record);
			
			//PurOrderVo purOrderVo = purOrderDataVo.getPurOrderVo();
			List<WmsTakeItemVo> detailList = record.getWmsTakeItem();
			/*
			int count = purOrderDao.selectPurOrderCountByPurNo(purOrderDataVo.getPurOrderVo().getPurNo());
			if(count > 0){
				throw new DHBizException(ExceptionConstant.ERR_DH030001);
			}
			*/
			
			wmsTakeDao.insertSelective(record);
			
			for(WmsTakeItemVo wmsTakeItemVo : detailList){
				wmsTakeItemVo.setTakeId(record.getId());
				wmsTakeItemVo.setGdsId(wmsTakeItemVo.getGdsId());
				wmsTakeItemVo.setLocId(wmsTakeItemVo.getLocId());
				wmsTakeItemVo.setMemo(wmsTakeItemVo.getMemo());
				wmsTakeItemVo.setIsDeleted(false);
				wmsTakeItemVo.setIsLocked(false);
				wmsTakeItemDao.insertSelective(wmsTakeItemVo);
				/*
				wmsTakeItemVo.setPurOutAmount(new BigDecimal("0"));
				wmsTakeItemVo.setPurOutMoney(new BigDecimal("0"));
				wmsTakeItemVo.setPurRealAmount(new BigDecimal("0"));
				wmsTakeItemVo.setPurRealMoney(new BigDecimal("0"));
				wmsTakeItemVo.insertSelective(purOrderDetailVo);
				*/
			}
			
		}
		
		return commResponse;
	}

	@Override
	public WmsTake selectByPrimaryKey(Integer id) {
		return wmsTakeDao.selectByPrimaryKey(id);
	}

	@Override
	public JqGridVo<WmsTake> selectByStatement(WmsTakeVo record) {
		JqGridVo<WmsTake> jqGridVo = new JqGridVo<WmsTake>();
		jqGridVo.setList(wmsTakeDao.selectByStatement(record));
		jqGridVo.setRecords(wmsTakeDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}

	@Override
	public int selectByStatementCount(WmsTakeVo record) {
		return wmsTakeDao.selectByStatementCount(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WmsTakeVo record) {
		return wmsTakeDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteWmsTakes(Integer[] ids) {
		int flag = 0;
		for (Integer integer : ids) {
			//删除明细表的内容
			wmsTakeItemDao.delteWmsTakeItemByTag(integer);
			flag = wmsTakeDao.delteWmsTakeByTag(integer);
		}
		return flag;
	}

	@Override
	public WmsTake selectByPrimaryKey(Long id) {
		return this.selectByPrimaryKey(id.intValue());
	}

}
