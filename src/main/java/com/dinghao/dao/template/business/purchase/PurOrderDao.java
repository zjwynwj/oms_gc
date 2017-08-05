package com.dinghao.dao.template.business.purchase;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.purchase.PurOrder;
import com.dinghao.entity.vo.template.business.purchase.PurOrderVo;

/**
  * @ClassName: PurOrderDao
  * @Description: TODO  订单管理dao层
  * @author helong 
  * @date 2016年1月7日 上午10:13:43
  * @version V1.0
  *
 */
@Repository
public interface PurOrderDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据id删除采购订单
	  * @param @param id
	  * @param @return
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  普通删除采购订单
      * @param @param purOrderVo
      * @param @return
      * @return int
      * @throws
     */
    int insert(PurOrderVo purOrderVo) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO   根据属性是否为空删除采购订单
      * @param @param purOrderVo
      * @param @return
      * @return int
      * @throws
     */
    int insertSelective(PurOrderVo purOrderVo) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询采购订单
      * @param @param id
      * @param @return
      * @return PurOrder
      * @throws
     */
    PurOrder selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO   普通修改采购订单
      * @param @param purOrderVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(PurOrderVo purOrderVo) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据id修改订单
      * @param @param purOrderVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKey(PurOrderVo purOrderVo) throws Exception;
    
    /**
      * @Title: selectPurOrderGridListPage
      * @Description: TODO   查询分页订单数据
      * @param @param purOrderVo
      * @param @return
      * @param @throws Exception
      * @return List<PurOrder>
      * @throws
     */
    List<PurOrder> selectPurOrderGridListPage(PurOrderVo purOrderVo) throws Exception;
    
    /**
      * @Title: selectPurOrderGridListCount
      * @Description: TODO  根据条件查询订单数量
      * @param @param purOrderVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int selectPurOrderGridListCount(PurOrderVo purOrderVo) throws Exception;
    
    /**
      * @Title: selectPurOrderGridListCountByPurNo
      * @Description: TODO  根据purNo条件查询订单数量
      * @param @param purOrderVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int selectPurOrderCountByPurNo(String purNo) throws Exception;
}