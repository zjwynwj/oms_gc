package com.dinghao.dao.template.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.Dictitem;
import com.dinghao.entity.vo.template.base.DictitemVo;

@Repository
public interface DictitemDao {
    int deleteByPrimaryKey(Long dictitemId);

    int insert(DictitemVo dictitemVo);

    int insertSelective(DictitemVo dictitemVo);

    Dictitem selectByPrimaryKey(Long dictitemId);

    int updateByPrimaryKeySelective(DictitemVo dictitemVo);

    int updateByPrimaryKey(DictitemVo dictitemVo);
    
    List<Dictitem> selectDictitemListByDictId(Long dictId);
}