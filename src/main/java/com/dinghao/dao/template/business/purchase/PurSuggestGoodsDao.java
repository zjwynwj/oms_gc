package com.dinghao.dao.template.business.purchase;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestGoodsVo;

@Repository
public interface PurSuggestGoodsDao {
    int deleteByPrimaryKey(Long id);

    int insert(PurSuggestGoodsVo record);

    int insertSelective(PurSuggestGoodsVo record);

    PurSuggestGoods selectByPrimaryKey(Long id);
    
    List<PurSuggestGoods> selectBySuggestId(Long SuggestId);

    int updateByPrimaryKeySelective(PurSuggestGoodsVo record);

    int updateByPrimaryKey(PurSuggestGoodsVo record);
}