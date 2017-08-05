package com.dinghao.service.template.business.goods;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.vo.template.business.goods.GdsClsVo;

/**
 * 
  * @ClassName: GdsClsService
  * @Description: TODO   商品分类管理 service  接口 
  * @author helong 
  * @date 2016年1月5日 下午3:51:49
  * @version V1.0
  *
 */
public interface GdsClsService {
	
	/**
	  * @Title: addEGdsCls
	  * @Description: 增加商品分类信息
	  * @param @param GdsClsVo
	  * @param @return
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addGdsCls(GdsClsVo eClsVo) throws Exception;
	
	/**
	 * 
	  * @Title: delEGdsCls
	  * @Description: 删除商品分类信息
	  * @param @param GdsClsVo
	  * @param @return
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delGdsCls(GdsClsVo eClsVo) throws Exception;
	
	/**
	 * @throws Exception 
	 * 
	  * @Title: modEGdsCls
	  * @Description: 修改商品分类信息
	  * @param @param GdsClsVo
	  * @param @return
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modGdsCls(GdsClsVo eClsVo) throws Exception;
	
	/**
	 * 
	  * @Title: queryEGdsClsForParam
	  * @Description: 查询呢商品分类
	  * @param @param GdsClsVo
	  * @param @return
	  * @return CommResponse
	  * @throws
	 */
	public GdsCls queryGdsClsById(GdsClsVo eClsVo) throws Exception;
	
	/**
	 * 
	  * @Title: queryEGdsClsForParam
	  * @Description: 查询呢商品分类
	  * @param @param GdsClsVo
	  * @param @return
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryGdsClsList(GdsClsVo eClsVo) throws Exception;
	
	
	public CommResponse findGdsClsListPage(GdsClsVo gdsClsVo) throws Exception;

}
