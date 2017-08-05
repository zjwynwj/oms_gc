package com.dinghao.service.template.business.goods;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.goods.DownGoodsParamVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;

/**
  * @ClassName: GdsInfoService
  * @Description: TODO  商品管理servcei  接口 
  * @author helong 
  * @date 2016年1月5日 下午3:52:19
  * @version V1.0
  *
 */
public interface GdsInfoService {
	/**
	  * @Title: addGdsInfo
	  * @Description: TODO  添加商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addGdsInfo(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: modGdsInfo
	  * @Description: TODO  修改商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse modGdsInfo(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: delGdsInfo
	  * @Description: TODO  删除商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delGdsInfo(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: queryGdsInfoById
	  * @Description: TODO   根据id查询商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryGdsInfoById(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: findGdsInfoListPage
	  * @Description: TODO  分页查询商品数据
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findGdsInfoListPage(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: stockOperatGds
	  * @Description: TODO   批量操作商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse stockOperatGds(GdsInfoVo gdsInfoVo) throws Exception;
	
	/**
	  * @Title: downLoadGoods
	  * @Description: TODO   下载商品
	  * @param @param gdsInfoVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse downLoadGoods(DownGoodsParamVo goodsParamVo) throws Exception;
	
	/**
	  * @Title: selectGdsInfoGridListNoPage
	  * @Description: TODO  根据条件查询符合要求的商品
	  * @param @param goodsParamVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse selectGdsInfoGridListNoPage(GdsInfoVo gdsInfoVo) throws Exception;
}
