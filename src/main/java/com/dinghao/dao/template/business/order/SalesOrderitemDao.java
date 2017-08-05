package com.dinghao.dao.template.business.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.order.SalesOrderitem;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
@Repository
public interface SalesOrderitemDao {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(SalesOrderitemVo salesOrderitemVo) throws Exception;

    int insertSelective(SalesOrderitemVo salesOrderitemVo) throws Exception;

    SalesOrderitem selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(SalesOrderitemVo salesOrderitemVo) throws Exception;

    int updateByPrimaryKey(SalesOrderitemVo salesOrderitemVo) throws Exception;
    
    int selectOrderItemListCount(SalesOrderitemVo salesOrderitemVo) throws Exception;
    
    List<SalesOrderitemVo> selectOrderItemList(SalesOrderitemVo salesOrderitemVo) throws Exception;
    
    List<SalesOrderitem> selectOrderItemListGrid(SalesOrderitemVo salesOrderitemVo) throws Exception;
    
    List<SalesOrderitem> selectOrderItemListByOid(String oid) throws Exception;
    
    List<SalesOrderitemVo> selectOrderItemListByOrderId(String orderId) throws Exception;
    
    List<SalesOrderitem> selectOrderItemListByParam(SalesOrderitemVo salesOrderitemVo) throws Exception;
    
    int deleteByOrderId(String orderId) throws Exception;
}