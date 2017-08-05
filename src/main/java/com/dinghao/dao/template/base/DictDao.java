package com.dinghao.dao.template.base;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.Dict;
import com.dinghao.entity.vo.template.base.DictVo;

@Repository
public interface DictDao {
    int deleteByPrimaryKey(Long dictId);

    int insert(DictVo dictVo);

    int insertSelective(DictVo dictVo);

    Dict selectByPrimaryKey(Long dictId);

    int updateByPrimaryKeySelective(DictVo dictVo);

    int updateByPrimaryKey(DictVo dictVo);
    
    Dict selectByDictVal(DictVo dictVo);
}