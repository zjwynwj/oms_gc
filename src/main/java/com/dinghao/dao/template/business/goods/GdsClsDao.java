package com.dinghao.dao.template.business.goods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.vo.template.business.goods.GdsClsVo;
/**
  * @ClassName: GdsClsDao
  * @Description: TODO   商品分类dao层
  * @author helong 
  * @date 2016年1月5日 下午3:32:49
  * @version V1.0
  *
 */
@Repository
public interface GdsClsDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据id删除商品分类
	  * @param @param id
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  一般插入商品分类方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insert(GdsClsVo record) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空插入商品分类方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insertSelective(GdsClsVo record) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询商品分类方法
      * @param @param id
      * @param @return
      * @param @throws Exception
      * @return GdsCls
      * @throws
     */
    GdsCls selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO  一般修改商品分类方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(GdsClsVo record) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据id修改商品分类方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKey(GdsClsVo record) throws Exception;
    
    /**
      * @Title: selectGdsClsList  
      * @Description: TODO   一般查询商品分类集合
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return List<GdsCls>
      * @throws
     */
    List<GdsCls> selectGdsClsList(GdsClsVo record) throws Exception;
    
    /**
      * @Title: selectGdsClsAllChild
      * @Description: TODO  根据clsNo  查询所有子分类（包括自己）
      * @param @param clsNo
      * @param @return
      * @param @throws Exception
      * @return List<GdsCls>
      * @throws
     */
    List<GdsCls> selectGdsClsAllChild(String clsNo) throws Exception;
    
    /**
     * @Title: selectGdsClsChild
     * @Description: TODO  根据clsNo  查询次级分类
     * @param @param clsNo
     * @param @return
     * @param @throws Exception
     * @return List<GdsCls>
     * @throws
    */
   List<GdsCls> selectGdsClsChild(GdsClsVo record) throws Exception;

    /**
      * @Title: findMaxClsNo
      * @Description: TODO  查询当前分类下最大的编号
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return String
      * @throws
     */
    String findMaxClsNo(GdsClsVo record) throws Exception;
    
   /**
     * @Title: findParentCLsInfo
     * @Description: TODO  查询父节点的分类信息
     * @param @param record
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
    */
   GdsCls findParentCLsInfo(Long id) throws Exception;
}