package com.dinghao.dao.template.business.purchase;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.purchase.PurOrderDetail;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDetailVo;

/**
  * @ClassName: PurOrderDetailDao
  * @Description: TODO  采购订单明细管理
  * @author helong 
  * @date 2016年1月7日 上午10:27:12
  * @version V1.0
  *
 */
@Repository
public interface PurOrderDetailDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据id删除订单明细
	  * @param @param id
	  * @param @return
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  普通插入订单明细表方法
      * @param @param purOrderDetailVo
      * @param @return
      * @return int
      * @throws
     */
    int insert(PurOrderDetailVo purOrderDetailVo) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空插入订单明细方法
      * @param @param purOrderDetailVo
      * @param @return
      * @return int
      * @throws
     */
    int insertSelective(PurOrderDetailVo purOrderDetailVo) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询 订单明细
      * @param @param id
      * @param @return
      * @return PurOrderDetail
      * @throws
     */
    PurOrderDetail selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO   普通修改订单明细方法
      * @param @param purOrderDetailVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(PurOrderDetailVo purOrderDetailVo) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO   根据id查询订单明细
      * @param @param purOrderDetailVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKey(PurOrderDetailVo purOrderDetailVo) throws Exception;
    
    /**
     * 
      * @Title: deleteByPrimaryByPurId
      * @Description: TODO 根据采购订单id删除明细数据
      * @param @param id
      * @param @return
      * @return int
      * @throws
     */
    int deleteByPrimaryByPurOrderId(Long id);
    
    /**
      * @Title: findPurOrderDetailListByPurOrderId
      * @Description: TODO  根据采购订单id查询订单明细列表
      * @param @param purOrderDetailVo
      * @param @return
      * @return List<PurOrderDetail>
      * @throws
     */
    List<PurOrderDetail> findPurOrderDetailListByPurOrderId(Long purOrderId) throws Exception;
}