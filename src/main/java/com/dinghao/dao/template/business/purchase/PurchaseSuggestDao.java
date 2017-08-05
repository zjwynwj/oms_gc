package com.dinghao.dao.template.business.purchase;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.purchase.PurchaseSuggest;
import com.dinghao.entity.vo.template.business.purchase.PurSuggestVo;

@Repository
public interface PurchaseSuggestDao {
    int deleteByPrimaryKey(Long id);

    int insert(PurSuggestVo record);

    int insertSelective(PurSuggestVo record);

    PurchaseSuggest selectByPrimaryKey(Long id);
    
    PurchaseSuggest getPurSuggestByNo(String suggestNo);

    int updateByPrimaryKeySelective(PurSuggestVo record);

    int updateByPrimaryKeyWithBLOBs(PurSuggestVo record);

    int updateByPrimaryKey(PurSuggestVo record);
    
    int selectCountBySuggestNo(String suggestNo) throws Exception;
    
    List<PurSuggestVo> selectList(PurSuggestVo record);
    int selectListCount(PurSuggestVo record);
   
}