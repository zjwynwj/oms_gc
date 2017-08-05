package com.dinghao.dao.template.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.vo.template.base.ShopVo;

@Repository
public interface ShopDao {
  
	 int deleteByPrimaryKey(Long id) throws Exception;

	    int insert(ShopVo record) throws Exception;

	    int insertSelective(ShopVo record) throws Exception;

	    Shop selectByPrimaryKey(Long id) throws Exception;

	    int updateByPrimaryKeySelective(ShopVo record)throws Exception;

	    int updateByPrimaryKey(ShopVo record) throws Exception;
	    
	    List<Shop> selectListPage(ShopVo shopVo) throws Exception;
}