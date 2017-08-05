package com.dinghao.dao.template.business.goods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
/**
  * @ClassName: GdsInfoDao
  * @Description: TODO   商品管理dao层
  * @author helong 
  * @date 2016年1月5日 下午3:32:24
  * @version V1.0
  *
 */
@Repository
public interface GdsInfoDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO   根据id删除商品
	  * @param @param id
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  一般插入商品方法
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insert(GdsInfoVo gdsInfoVo) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空 插入商品
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insertSelective(GdsInfoVo gdsInfoVo) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询商品
      * @param @param id
      * @param @return
      * @param @throws Exception
      * @return GdsInfo
      * @throws
     */
    GdsInfo selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO  一般修改商品方法
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(GdsInfoVo gdsInfoVo) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据id修改商品方法
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKey(GdsInfoVo gdsInfoVo) throws Exception;
    
    /**
      * @Title: selectGdsInfoGridListPage
      * @Description: TODO   分页查询商品集合方法
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return List<GdsInfo>
      * @throws
     */
    List<GdsInfo> selectGdsInfoGridListPage(GdsInfoVo gdsInfoVo) throws Exception;
    
    /**
      * @Title: selectGdsInfoGridListNoPage
      * @Description: TODO    一般查询商品集合方法 
      * @param @param gdsInfoVo
      * @param @return
      * @param @throws Exception
      * @return List<GdsInfo>
      * @throws
     */
    List<GdsInfo> selectGdsInfoGridListNoPage(GdsInfoVo gdsInfoVo) throws Exception;
    
    /**
     * @Title: selectGdsInfoGridListCount
     * @Description: TODO    根据条件查询商品 
     * @param @param gdsInfoVo
     * @param @return
     * @param @throws Exception
     * @return List<GdsInfo>
     * @throws
    */
    int selectGdsInfoGridListCount(GdsInfoVo gdsInfoVo) throws Exception;
    
    /**
     * @Title: queryEGdsInfoById
     * @Description: TODO    根据条件查询商品(关联出分类等信息) 
     * @param @param gdsInfoVo
     * @param @return
     * @param @throws Exception
     * @return List<GdsInfo>
     * @throws
    */
    GdsInfo queryGdsInfoById(Long id) throws Exception;
    
    /**
      * @Title: queryEGdsInfoByGdsNo
      * @Description: TODO  根据商品编码查询商品集合 
      * @param @param gdsNo  
      * @param @return
      * @param @throws Exception
      * @return GdsInfo
      * @throws
     */
    List<GdsInfo> queryGdsInfoByGdsNo(String gdsNo) throws Exception;
    
    /**
      * @Title: queryGdsInfoByGdsNoSkuOuterId
      * @Description: TODO   根据组合代码  查询商品
      * @param @param gdsNoSkuOuterId
      * @param @return
      * @param @throws Exception
      * @return GdsInfo
      * @throws
     */
    List<GdsInfo> queryGdsInfoByGdsNoSkuOuterId(String gdsNoSkuOuterId) throws Exception;
}