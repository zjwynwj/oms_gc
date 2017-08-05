package com.dinghao.service.template.base;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.BaseNumberVo;

/**
  * @ClassName: BaseNumberService
  * @Description: TODO  单据号管理service  接口
  * @author helong 
  * @date 2016年1月5日 下午3:35:59
  * @version V1.0
  *
 */
public interface BaseNumberService {
	
	/**
	  * @Title: addBaseNumber
	  * @Description: TODO  添加单据号
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addBaseNumber(BaseNumberVo baseNumberVo) throws Exception;
	
	/**
	  * @Title: modBaseNumber
	  * @Description: TODO  修改单据号
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modBaseNumber(BaseNumberVo baseNumberVo) throws Exception;
	
	/**
	  * @Title: queryBaseNumberById
	  * @Description: TODO  根据id查询单据号
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryBaseNumberById(BaseNumberVo baseNumberVo) throws Exception;
	
	/**
	  * @Title: findBaseNumberNext
	  * @Description: TODO   根据单据号类型  生成下一个单据号
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findBaseNumberNext(BaseNumberVo baseNumberVo) throws Exception;
	
	/**
	  * @Title: findBaseNumberForGrid
	  * @Description: TODO   分页查询单据号
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findBaseNumberForGrid(BaseNumberVo baseNumberVo) throws Exception;
}