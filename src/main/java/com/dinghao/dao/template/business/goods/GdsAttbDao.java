package com.dinghao.dao.template.business.goods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.GdsAttb;
import com.dinghao.entity.vo.template.business.goods.GdsAttbVo;
/**
  * @ClassName: GdsAttbDao
  * @Description: TODO   商品属性名称管理dao层
  * @author helong 
  * @date 2016年1月5日 下午3:33:33
  * @version V1.0
  *
 */
@Repository
public interface GdsAttbDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据id删除属性名称
	  * @param @param id
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  一般插入商品属性名称  方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insert(GdsAttbVo record) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空插入  商品名称属性表
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insertSelective(GdsAttbVo record) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询商品属性名称记录
      * @param @param id
      * @param @return
      * @param @throws Exception
      * @return GdsAttb
      * @throws
     */
    GdsAttb selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO  一般修改商品属性名称表
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(GdsAttbVo record) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据id修改商品属性名称表
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKey(GdsAttbVo record) throws Exception;
    
    /**
      * @Title: queryEGdsAttbListByClsIds
      * @Description: TODO  根据分类id集合  查询商品属性名称表
      * @param @param clsId
      * @param @return
      * @param @throws Exception
      * @return List<GdsAttb>
      * @throws
     */
    List<GdsAttb> queryEGdsAttbListByClsIds(GdsAttbVo record) throws Exception;
}