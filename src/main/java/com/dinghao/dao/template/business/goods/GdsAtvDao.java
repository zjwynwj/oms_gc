package com.dinghao.dao.template.business.goods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.goods.GdsAtv;
import com.dinghao.entity.vo.template.business.goods.GdsAtvVo;
/**
  * @ClassName: GdsAtvDao
  * @Description: TODO  商品可选属性值管理  dao层
  * @author helong 
  * @date 2016年1月5日 下午3:33:07
  * @version V1.0
  *
 */
@Repository
public interface GdsAtvDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据id删除商品可选属性值表
	  * @param @param id
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  一般插入商品可选属性值表方法
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insert(GdsAtvVo record) throws Exception;

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空 插入商品可选属性值表方法 
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insertSelective(GdsAtvVo record) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO 根据id查询商品可选属性值表记录
      * @param @param id
      * @param @return
      * @param @throws Exception
      * @return GdsAtv
      * @throws
     */
    GdsAtv selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO  一般修改商品可选属性值表方法
      * @param @param record  
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(GdsAtvVo record) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据id修改商品可选属性值表
      * @param @param record
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKey(GdsAtvVo record) throws Exception;
    
    /**
      * @Title: queryEGdsAtvListByClsIds
      * @Description: TODO  根据分类id集合 查询商品可选属性值表记录
      * @param @param clsId
      * @param @return
      * @param @throws Exception
      * @return List<GdsAtv>
      * @throws
     */
    List<GdsAtv> queryEGdsAtvListByClsIds(GdsAtvVo record) throws Exception;
}