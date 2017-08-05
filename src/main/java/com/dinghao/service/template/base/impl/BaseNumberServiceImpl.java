package com.dinghao.service.template.base.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.base.BaseNumberDao;
import com.dinghao.entity.template.base.BaseNumber;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.base.BaseNumberService;
import com.dinghao.util.BeanUtils;
import com.dinghao.util.DateUtil;
import com.dinghao.util.StringUtil;
/**
  * @ClassName: BaseNumberServiceImpl
  * @Description: TODO  单据号管理service层  实现
  * @author helong 
  * @date 2016年1月5日 下午3:34:02
  * @version V1.0
  *
 */
@Service
@Transactional
public class BaseNumberServiceImpl implements BaseNumberService{
	@Autowired
	private BaseNumberDao numberDao;
	
	/**
	  * <p>Title: addBaseNumber</p>
	  * <p>Description: 添加单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#addBaseNumber(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse addBaseNumber(BaseNumberVo baseNumberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		numberDao.insertSelective(baseNumberVo);
		return commResponse;
	}

	/**
	  * <p>Title: modBaseNumber</p>
	  * <p>Description: 修改单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#modBaseNumber(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse modBaseNumber(BaseNumberVo baseNumberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		numberDao.updateByPrimaryKeySelective(baseNumberVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryBaseNumberById</p>
	  * <p>Description: 根据id查询单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#queryBaseNumberById(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse queryBaseNumberById(BaseNumberVo baseNumberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		BaseNumber baseNumber = numberDao.selectByPrimaryKey(baseNumberVo.getId());
		commResponse.setData(baseNumber);
		return commResponse;
	}

	/**
	  * <p>Title: findBaseNumberNext</p>
	  * <p>Description: 根据单据号类型  生成下一个单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#findBaseNumberNext(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public synchronized CommResponse findBaseNumberNext(BaseNumberVo baseNumberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		try{
			/*1.根据类型查询当前数据库中单据号的数据*/
			BaseNumber number = numberDao.selectByPrimaryNuType(baseNumberVo.getNuType());
			if(number == null){
				throw new Exception();
			}
			/*2.修改数据库中当前编号*/
			BaseNumberVo numberVo = new BaseNumberVo();
			Long current = number.getNuCurrent();
			Long digit = number.getNuDigit();
			Long next = current + number.getNuStep();
			BeanUtils.copyProperties(numberVo, number);
			numberVo.setNuCurrent(next);
			numberVo.setModifyDate(new Date());
			numberDao.updateByPrimaryKeySelective(numberVo);
			/*3.生成本次产生的单据号*/
			StringBuffer sb = new StringBuffer(number.getNuPrefix());
			if(number.getYearRule()) {//表示使用年份规则
				sb.append(DateUtil.getCurYear());
			}
			if(number.getMontRule()) {//表示使用月份规则
				sb.append(DateUtil.getCurMonth());
			}
			if(number.getDayRule()) {//表示使用日期规则
				sb.append(DateUtil.getCurDay());
			}
			sb.append(StringUtil.lpad(digit, next));
			commResponse.setData(sb);
		}catch(Exception e){
			commResponse.setErrMsg(ExceptionConstant.ERR_DH010001);
			throw new DHBizException(e , ExceptionConstant.ERR_DH010001);
		}
		return commResponse;
	}

	/**
	  * <p>Title: findBaseNumberForGrid</p>
	  * <p>Description: 分页查询单据号数据</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#findBaseNumberForGrid(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse findBaseNumberForGrid(BaseNumberVo baseNumberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<BaseNumber> pageVo = new PageVo<BaseNumber>();
		pageVo.setRows(baseNumberVo.getRows());
		pageVo.setList(numberDao.selectBaseNuberListPage(baseNumberVo));
		pageVo.setCount(numberDao.selectBaseNuberListCount(baseNumberVo));
		commResponse.setData(pageVo);
		return commResponse;
	}
}
