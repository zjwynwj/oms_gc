package com.dinghao.service.template.financereceipt.imp;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinghao.dao.template.base.FinanceAccountDao;
import com.dinghao.dao.template.financereceipt.FinancePaymentDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.financereceipt.FinancePayment;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinancePaymentVo;
import com.dinghao.service.template.financereceipt.FinancePaymentService;

@Service
public class FinancePaymentServiceImp implements FinancePaymentService {
	@Autowired
	FinancePaymentDao financePaymentDao;
	@Autowired
	FinanceAccountDao financeAccountDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FinancePaymentVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse insertSelective(FinancePaymentVo record) throws Exception {
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
						0) : financeAccount.getAmount()).subtract(record.getAmount()));
		try {
			financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		record.setCreateDate(new Date());
		financePaymentDao.insertSelective(record);
		return commResponse;
	}

	@Override
	public FinancePayment selectByPrimaryKey(Integer id) {
		return selectByPrimaryKey(id.longValue());
	}

	@Override
	public JqGridVo<FinancePayment> selectByStatement(FinancePaymentVo record) {
		JqGridVo<FinancePayment> jqGridVo = new JqGridVo<FinancePayment>();
		jqGridVo.setList(financePaymentDao.selectByStatement(record));
		jqGridVo.setRecords(financePaymentDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}

	@Override
	public int selectByStatementCount(FinancePaymentVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse updateByPrimaryKeySelective(FinancePaymentVo record) {
		CommResponse commResponse = new CommResponse();
		financePaymentDao.updateByPrimaryKeySelective(record);
		return commResponse;
	}

	@Override
	public int deleteWmsTakes(Integer[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FinancePayment selectByPrimaryKey(Long id) {
		return financePaymentDao.selectByPrimaryKey(id);
	}

}
