package com.dinghao.service.template.business.goods;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.goods.GdsAtvVo;

/**
  * @ClassName: GdsAtvService
  * @Description: TODO   商品属性可选属性值管理 service 接口
  * @author helong 
  * @date 2016年1月5日 下午3:46:00
  * @version V1.0
  *
 */
public interface GdsAtvService {

	/**
	  * @Title: addGdsAtv
	  * @Description: TODO  添加属性可选属性值
	  * @param @param gdsAtvVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addGdsAtv(GdsAtvVo gdsAtvVo) throws Exception;
	
	
	/**
	  * @Title: delGdsAtv
	  * @Description: TODO  删除属性可选属性值
	  * @param @param gdsAtvVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delGdsAtv(GdsAtvVo gdsAtvVo) throws Exception;
	
	
	/**
	  * @Title: modGdsAtv
	  * @Description: TODO  修改属性可选属性值
	  * @param @param gdsAtvVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modGdsAtv(GdsAtvVo gdsAtvVo) throws Exception;
	
}
