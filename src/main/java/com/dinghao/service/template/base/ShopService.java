package com.dinghao.service.template.base;

import java.util.List;

import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.ShopVo;

/**
 * @ClassName: BaseNumberService
 * @Description: TODO 单据号管理service 接口
 * @author helong
 * @date 2016年1月5日 下午3:35:59
 * @version V1.0
 *
 */
public interface ShopService {

	/**
	 * @Title: add
	 * @Description: TODO 添加单据号
	 * @param @param baseNumberVo
	 * @param @return
	 * @param @throws Exception
	 * @return CommResponse
	 * @throws
	 */
	public CommResponse add(ShopVo shopVo) throws Exception;

	/**
	 * @Title: mod
	 * @Description: TODO 修改单据号
	 * @param @param baseNumberVo
	 * @param @return
	 * @param @throws Exception
	 * @return CommResponse
	 * @throws
	 */
	public CommResponse mod(ShopVo shopVo) throws Exception;

	/**
	 * @Title: queryById
	 * @Description: TODO 根据id查询单据号
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return CommResponse
	 * @throws
	 */
	public Shop queryById(Long id) throws Exception;

	/**
	 * @Title: findForGrid
	 * @Description: TODO 分页查询单据号
	 * @param @param baseNumberVo
	 * @param @return
	 * @param @throws Exception
	 * @return CommResponse
	 * @throws
	 */
	public CommResponse findForGrid(ShopVo shopVo) throws Exception;

	/**
	 * 
	 * @Title: getShops
	 * @Description: TODO(获取所有店铺信息)
	 * @param @param shopVo
	 * @param @return 设定文件
	 * @return List<Shop> 返回类型
	 * @throws
	 */
	public List<Shop> getShops(ShopVo shopVo);
}