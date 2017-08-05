package com.dinghao.service.template.financereceipt.imp;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinghao.dao.template.base.FinanceAccountDao;
import com.dinghao.dao.template.financereceipt.FinanceTransferDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.financereceipt.FinanceTransfer;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.entity.vo.template.financereceipt.FinanceTransferVo;
import com.dinghao.service.template.financereceipt.FinanceTransferService;

@Service
public class FinanceTransferServiceImp implements FinanceTransferService {
	@Autowired
	FinanceTransferDao financeTransferDao;
	@Autowired
	FinanceAccountDao financeAccountDao;

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FinanceTransferVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommResponse insertSelective(FinanceTransferVo record) throws Exception {
		CommResponse commResponse = new CommResponse();
		// 更新账户钱
		FinanceAccount financeAccountRecAccount = null, financeAccountPayAccount = null;
		try {
			financeAccountRecAccount = financeAccountDao
					.selectByPrimaryKey(Long.parseLong(record.getRecAccount()));
			financeAccountPayAccount = financeAccountDao
					.selectByPrimaryKey(Long.parseLong(record.getPayAccount()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			commResponse.setSuccess(false);
		}
		FinanceAccountVo financeAccountVo = new FinanceAccountVo();
		if ("2".equals(record.getPayType())) {// 转入支付
			financeAccountVo.setId(financeAccountRecAccount.getId());
			financeAccountVo
					.setAmount((financeAccountRecAccount.getAmount() == null ? new BigDecimal(
							0) : financeAccountRecAccount.getAmount())
							.subtract(record.getAmount().add(record.getPoundage())));
			try {
				financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
				commResponse.setSuccess(false);
			}
			financeAccountVo.setId(financeAccountPayAccount.getId());
			financeAccountVo
					.setAmount((financeAccountPayAccount.getAmount() == null ? new BigDecimal(
							0) : financeAccountPayAccount.getAmount())
							.add(record.getAmount()));
			try {
				financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
				commResponse.setSuccess(false);
			}
		} else {// 转出支付
			financeAccountVo.setId(financeAccountRecAccount.getId());
			financeAccountVo
					.setAmount((financeAccountRecAccount.getAmount() == null ? new BigDecimal(
							0) : financeAccountRecAccount.getAmount())
							.add(record.getAmount()));
			try {
				financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
				commResponse.setSuccess(false);
			}
			financeAccountVo.setId(financeAccountPayAccount.getId());
			financeAccountVo
					.setAmount((financeAccountPayAccount.getAmount() == null ? new BigDecimal(
							0) : financeAccountPayAccount.getAmount())
							.subtract(record.getAmount().add(record.getPoundage())));
			try {
				financeAccountDao.updateByPrimaryKeySelective(financeAccountVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
				commResponse.setSuccess(false);
			}
		}
		record.setCreateDate(new Date());
		financeTransferDao.insertSelective(record);
		return commResponse;
	}

	@Override
	public FinanceTransfer selectByPrimaryKey(Integer id) {
		return financeTransferDao.selectByPrimaryKey(id.longValue());
	}

	@Override
	public JqGridVo<FinanceTransfer> selectByStatement(FinanceTransferVo record) {
		JqGridVo<FinanceTransfer> jqGridVo = new JqGridVo<FinanceTransfer>();
		jqGridVo.setList(financeTransferDao.selectByStatement(record));
		jqGridVo.setRecords(financeTransferDao.selectByStatementCount(record));
		jqGridVo.setPageNum(record.getPageNum());
		jqGridVo.setRows(record.getRows());
		return jqGridVo;
	}

	@Override
	public int selectByStatementCount(FinanceTransferVo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(FinanceTransferVo record) {
		financeTransferDao.updateByPrimaryKeySelective(record);
		return record.getId().intValue();
	}

	@Override
	public int deleteWmsTakes(Integer[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FinanceTransfer selectByPrimaryKey(Long id) {
		return financeTransferDao.selectByPrimaryKey(id);
	}

}
