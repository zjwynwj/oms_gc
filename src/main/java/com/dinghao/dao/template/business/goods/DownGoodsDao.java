package com.dinghao.dao.template.business.goods;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.DownGoods;
import com.dinghao.entity.vo.template.business.goods.DownGoodsVo;
@Repository
public interface DownGoodsDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(DownGoodsVo downGoodsVo) throws Exception;

    int insertSelective(DownGoodsVo downGoodsVo) throws Exception;

    DownGoods selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DownGoodsVo downGoodsVo) throws Exception;

    int updateByPrimaryKey(DownGoodsVo downGoodsVo) throws Exception;
    
    DownGoods selectByParam(DownGoodsVo downGoodsVo) throws Exception;
}