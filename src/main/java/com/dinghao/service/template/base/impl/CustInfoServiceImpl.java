package com.dinghao.service.template.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.base.CustInfoDao;
import com.dinghao.entity.template.base.CustInfo;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.CustInfoVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.base.CustInfoService;
/**
  * @ClassName: CustInfoServiceImpl
  * @Description: TODO  客户管理service  实现
  * @author helong 
  * @date 2016年1月6日 上午9:48:01
  * @version V1.0
  *
 */
@Service
@Transactional
public class CustInfoServiceImpl implements CustInfoService{
	@Autowired
	private CustInfoDao custDao;
	/**
	  * <p>Title: addCustInfo</p>
	  * <p>Description: 添加客户</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#addCustInfo(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse addCustInfo(CustInfoVo custInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		int count = custDao.selectCustInfoCountByCustNo(custInfoVo.getCustNo());
		if(count > 0){
			throw new DHBizException(ExceptionConstant.ERR_DH100001);
		}else{
			custDao.insertSelective(custInfoVo);
		}
		return commResponse;
	}

	/**
	  * <p>Title: delCustInfo</p>
	  * <p>Description: 删除客户</p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#delCustInfo(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse delCustInfo(CustInfoVo custInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		CustInfo custInfo = custDao.selectByPrimaryKey(custInfoVo.getId());
		if(custInfo == null){
			throw new DHBizException(ExceptionConstant.ERR_DH100002);
		}else{
			custDao.deleteByPrimaryKey(custInfoVo.getId());
		}
		return commResponse;
	}

	/**
	  * <p>Title: modCustInfo</p>
	  * <p>Description: 修改客户信息</p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#modCustInfo(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse modCustInfo(CustInfoVo custInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		CustInfo custInfo = custDao.selectByPrimaryKey(custInfoVo.getId());
		if(custInfo == null){
			throw new DHBizException(ExceptionConstant.ERR_DH100002);
		}else{
			custDao.updateByPrimaryKeySelective(custInfoVo);
		}
		return commResponse;
	}

	/**
	  * <p>Title: queryCustInfoById</p>
	  * <p>Description: 根据id查询 客户信息 </p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#queryCustInfoById(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse queryCustInfoById(CustInfoVo custInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		CustInfo custInfo = custDao.selectByPrimaryKey(custInfoVo.getId());
		commResponse.setData(custInfo);
		return commResponse;
	}

	/**
	  * <p>Title: findCustInfoForGrid</p>
	  * <p>Description: 查询客户分页数据</p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#findCustInfoForGrid(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse findCustInfoForGrid(CustInfoVo custInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<CustInfo> pageVo = new PageVo<CustInfo>();
		pageVo.setRows(custInfoVo.getRows());
		pageVo.setList(custDao.selectCustInfoListPage(custInfoVo));
		pageVo.setCount(custDao.selectCustInfoListCount(custInfoVo));
		commResponse.setData(pageVo);
		return commResponse;
	}

}
