package com.dinghao.service.template.business.goods;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.goods.GdsAttbVo;

/**
  * @ClassName: GdsAttbService
  * @Description: TODO   商品属性名称管理 service 接口
  * @author helong 
  * @date 2016年1月5日 下午3:39:45
  * @version V1.0
  *
 */
public interface GdsAttbService {

	/**
	  * @Title: addGdsAttb
	  * @Description: TODO  添加商品属性名称
	  * @param @param gdsAttbVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addGdsAttb(GdsAttbVo gdsAttbVo) throws Exception;
	
	/**
	  * @Title: delGdsAttb
	  * @Description: TODO  删除 属性名称
	  * @param @param gdsAttbVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delGdsAttb(GdsAttbVo gdsAttbVo) throws Exception;
	
	/**
	  * @Title: modGdsAttb
	  * @Description: TODO  修改属性名称
	  * @param @param gdsAttbVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modGdsAttb(GdsAttbVo gdsAttbVo) throws Exception;
	
	/**
	  * @Title: queryAttbForTable
	  * @Description: TODO  查询根据前台  table所需要的数据格式相关的数据
	  * @param @param gdsAttbVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryAttbForTable(GdsAttbVo gdsAttbVo) throws Exception;
}
