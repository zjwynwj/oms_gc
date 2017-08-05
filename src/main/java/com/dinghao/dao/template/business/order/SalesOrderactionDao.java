package com.dinghao.dao.template.business.order;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.order.SalesOrderaction;
import com.dinghao.entity.vo.template.business.order.SalesOrderactionVo;
@Repository
public interface SalesOrderactionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SalesOrderactionVo salesOrderactionVo);

    int insertSelective(SalesOrderactionVo salesOrderactionVo);

    SalesOrderaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SalesOrderactionVo salesOrderactionVo);

    int updateByPrimaryKey(SalesOrderactionVo salesOrderactionVo);
}