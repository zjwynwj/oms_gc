package com.dinghao.service.template.financereceipt.imp;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinghao.dao.template.base.FinanceAccountDao;
import com.dinghao.dao.template.financereceipt.FinanceReceiptDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.financereceipt.FinanceReceipt;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinanceReceiptVo;
import com.dinghao.service.template.financereceipt.FinanceReceiptService;

@Service
public class FinanceReceiptServiceImp implements FinanceReceiptService {
	@Autowired
	FinanceReceiptDao financeReceiptDao;
	@Autowired
	FinanceAccountDao financeAccountDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FinanceReceiptVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse insertSelective(FinanceReceiptVo record)  throws Exception {
		
		CommResponse commResponse = new CommResponse();
		// 更新账户钱
		FinanceAccount financeAccount = null;
		try {
			financeAccount = financeAccountDao.selectByPrimaryKey(Long
					.parseLong(record.getRecAccount()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		financeAccountVo.setId(financeAccount.getId());
		financeAccountVo
				.setAmount((financeAccount.getAmount() == null ? new BigDecimal(
						0) : financeAccount.getAmount()).add(record.getAmount()));
		try {
			financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		record.setCreateDate(new Date());
		financeReceiptDao.insertSelective(record);
		return commResponse;//record.getId().intValue();
	}

	@Override
	public FinanceReceipt selectByPrimaryKey(Integer id) {
		return selectByPrimaryKey(id.longValue());
	}

	@Override
	public JqGridVo<FinanceReceipt> selectByStatement(FinanceReceiptVo record) {
		JqGridVo<FinanceReceipt> jqGridVo = new JqGridVo<FinanceReceipt>();
		jqGridVo.setList(financeReceiptDao.selectByStatement(record));
		jqGridVo.setRecords(financeReceiptDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}

	@Override
	public int selectByStatementCount(FinanceReceiptVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse updateByPrimaryKeySelective(FinanceReceiptVo record)  throws Exception {
		CommResponse commResponse = new CommResponse();
		financeReceiptDao.updateByPrimaryKeySelective(record);
		return commResponse ;//record.getId().intValue();
	}

	@Override
	public int deleteWmsTakes(Integer[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FinanceReceipt selectByPrimaryKey(Long id) {
		return financeReceiptDao.selectByPrimaryKey(id);
	}

}
