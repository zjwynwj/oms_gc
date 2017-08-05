package com.dinghao.service.template.base;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.CustInfoVo;

/**
  * @ClassName: CustInfoService
  * @Description: TODO  客户管理service  接口
  * @author helong 
  * @date 2016年1月6日 上午9:45:59
  * @version V1.0
  *
 */
public interface CustInfoService {
	
	/**
	  * @Title: addCustInfo
	  * @Description: TODO  添加客户
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addCustInfo(CustInfoVo custInfoVo) throws Exception;
	
	/**
	  * @Title: delCustInfo
	  * @Description: TODO  删除客户
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delCustInfo(CustInfoVo custInfoVo) throws Exception;
	
	/**
	  * @Title: modCustInfo
	  * @Description: TODO  修改客户
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modCustInfo(CustInfoVo custInfoVo) throws Exception;
	
	/**
	  * @Title: queryCustInfoById
	  * @Description: TODO   根据id查询 客户信息
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryCustInfoById(CustInfoVo custInfoVo) throws Exception;
	
	/**
	  * @Title: findCustInfoForGrid
	  * @Description: TODO  查询客户分页数据
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findCustInfoForGrid(CustInfoVo custInfoVo) throws Exception;
}
