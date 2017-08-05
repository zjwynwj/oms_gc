package com.dinghao.dao.template.business.express;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
@Repository
public interface DmsExpressDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(DmsExpressVo expressVo) throws Exception;

    int insertSelective(DmsExpressVo expressVo) throws Exception;

    DmsExpress selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DmsExpressVo expressVo) throws Exception;

    int updateByPrimaryKey(DmsExpressVo expressVo) throws Exception;
    
    List<DmsExpress> selectExpressListGrid(DmsExpressVo expressVo) throws Exception;
    
    int	selectExpressListCount(DmsExpressVo expressVo) throws Exception; 
    
    List<DmsExpress> selectExpressList(DmsExpressVo expressVo) throws Exception;
}