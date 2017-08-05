package com.dinghao.dao.template.business.express;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.express.DmsExpressarea;
import com.dinghao.entity.vo.template.business.express.DmsExpressareaVo;
@Repository
public interface DmsExpressareaDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(DmsExpressareaVo expressareaVo) throws Exception;

    int insertSelective(DmsExpressareaVo expressareaVo) throws Exception;

    DmsExpressarea selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DmsExpressareaVo expressareaVo) throws Exception;

    int updateByPrimaryKey(DmsExpressareaVo expressareaVo) throws Exception;
    
    List<DmsExpressarea> selectExpressAreaList(DmsExpressareaVo expressareaVo) throws Exception;
    
    List<DmsExpressarea> selectExpressAreaListGrid(DmsExpressareaVo expressareaVo) throws Exception;
    
    int	selectExpressAreaListCount(DmsExpressareaVo expressareaVo) throws Exception; 
    
    DmsExpressarea selectByParam(DmsExpressareaVo expressareaVo) throws Exception;
    
}