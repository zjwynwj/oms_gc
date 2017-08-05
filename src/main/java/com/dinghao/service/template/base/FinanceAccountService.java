package com.dinghao.service.template.base;

import java.util.List;

import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.FinanceAccountVo;

/**
  * @ClassName: FinanceAccountService
  * @Description: TODO  账户管理service  接口
  * @author gucong 
  * @date 2016年1月24日 上午9:45:59
  * @version V1.0
  *
 */
public interface FinanceAccountService {
	
	
	/**
	  * @Title: findFinanceAccountForGrid
	  * @Description: TODO  查询客户分页数据
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findFinanceAccountForGrid(FinanceAccountVo financeAccount) throws Exception;
	
	public List<FinanceAccount> getFinanceAccounts(FinanceAccountVo financeAccount);
}
