package com.dinghao.dao.template.base;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.BaseArea;
import com.dinghao.entity.vo.template.base.BaseAreaVo;

@Repository
public interface BaseAreaDao {
    int deleteByPrimaryKey(Long id);

    int insert(BaseAreaVo baseAreaVo);
    
    int insertSelective(BaseAreaVo baseAreaVo);

    BaseArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseAreaVo baseAreaVo);

    int updateByPrimaryKey(BaseAreaVo baseAreaVo);
    
    BaseArea selectAreaIdByParam(BaseAreaVo baseAreaVo);
}