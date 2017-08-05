package com.dinghao.dao.template.business.goods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.DownGoodsSku;
import com.dinghao.entity.vo.template.business.goods.DownGoodsSkuVo;
@Repository
public interface DownGoodsSkuDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(DownGoodsSkuVo downGoodsSkuVo) throws Exception;

    int insertSelective(DownGoodsSkuVo downGoodsSkuVo) throws Exception;

    DownGoodsSku selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DownGoodsSkuVo downGoodsSkuVo) throws Exception;

    int updateByPrimaryKey(DownGoodsSkuVo downGoodsSkuVo) throws Exception;
    
    List<DownGoodsSku> selectByParam(DownGoodsSkuVo downGoodsSkuVo) throws Exception;
    
    List<DownGoodsSku> selectBySkuId(DownGoodsSkuVo skuId) throws Exception;
}