package com.dinghao.service.template.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.base.FinanceAccountDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;
import com.dinghao.service.template.base.FinanceAccountService;

/**
 * @ClassName: FinanceAccountServiceImpl
 * @Description: TODO 客户管理service 实现
 * @author gucong
 * @date 2016年1月6日 上午9:48:01
 * @version V1.0
 *
 */
@Service
@Transactional
public class FinanceAccountServiceImpl implements FinanceAccountService {
	@Autowired
	FinanceAccountDao financeAccountDao;

	/**
	 * <p>
	 * Title: findFinanceAccountForGrid
	 * </p>
	 * <p>
	 * Description: 查询客户分页数据
	 * </p>
	 * 
	 * @param custInfoVo
	 * @return
	 * @throws Exception
	 * @see com.dinghao.service.base.FinanceAccountService#findFinanceAccountForGrid(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse findFinanceAccountForGrid(FinanceAccountVo financeAccount)
			throws Exception {
		CommResponse commResponse = new CommResponse();

		PageVo<FinanceAccount> pageVo = new PageVo<FinanceAccount>();

		List<FinanceAccount> mlist = financeAccountDao
				.selectAccountListPage(financeAccount);
		int count =financeAccountDao.selectByStatementCount(financeAccount);
		pageVo.setRows(financeAccount.getRows());
		pageVo.setList(mlist);
		pageVo.setCount(count);

		commResponse.setData(pageVo);

		return commResponse;
	}
	
	@Override
	public List<FinanceAccount> getFinanceAccounts(FinanceAccountVo financeAccount){
		List<FinanceAccount> mlist = new ArrayList<FinanceAccount>();
		try {
			mlist = financeAccountDao
					.selectAccountListPage(financeAccount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}

}
