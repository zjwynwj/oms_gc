package com.dinghao.dao.template.business.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
@Repository
public interface SalesOrderDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(SalesOrderVo salesOrderVo) throws Exception;

    int insertSelective(SalesOrderVo salesOrderVo) throws Exception;

    SalesOrderVo selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(SalesOrderVo salesOrderVo) throws Exception;

    int updateByPrimaryKey(SalesOrderVo salesOrderVo) throws Exception;
    
    int selectOrderListCount(SalesOrderVo salesOrderVo) throws Exception;
    
    List<SalesOrderVo> selectOrderListGrid(SalesOrderVo salesOrderVo) throws Exception;
    
    List<SalesOrderVo> selectOrderListByTid(String topTids) throws Exception;
    
    SalesOrderVo selectByOrderNum(String orderNum) throws Exception;
    
    List<SalesOrderVo> selectByParam(SalesOrderVo salesOrderVo) throws Exception;
    
}