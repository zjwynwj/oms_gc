package com.dinghao.dao.template.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.BaseNumber;
import com.dinghao.entity.vo.template.base.BaseNumberVo;
/**
  * @ClassName: BaseNumberDao
  * @Description: TODO  单据号管理  dao层
  * @author helong 
  * @date 2016年1月5日 下午3:10:11
  * @version V1.0
  *
 */
@Repository
public interface BaseNumberDao {
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO 根据id删除记录
	  * @param @param id
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO 插入记录
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int insert(BaseNumberVo baseNumberVo) throws Exception;

    /**
     * @Title: insert
     * @Description: TODO 插入记录（根据字段是否存在）
     * @param @param baseNumberVo
     * @param @return
     * @param @throws Exception
     * @return int
     * @throws
    */
    int insertSelective(BaseNumberVo baseNumberVo) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询单据号信息
      * @param @param id
      * @param @return
      * @param @throws Exception
      * @return BaseNumber
      * @throws
     */
    BaseNumber selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO  根据条件修改单据号记录 
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(BaseNumberVo baseNumberVo) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO  根据主键id修改单据号记录
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int updateByPrimaryKey(BaseNumberVo baseNumberVo) throws Exception;
    
    /**
      * @Title: selectBaseNuberListCount
      * @Description: TODO  分页查询单据号的记录数
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
    int selectBaseNuberListCount(BaseNumberVo baseNumberVo) throws Exception;
    
    /**
      * @Title: selectBaseNuberListPage
      * @Description: TODO  分页查询单据号记录 集合
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return List<BaseNumber>
      * @throws
     */
    List<BaseNumber> selectBaseNuberListPage(BaseNumberVo baseNumberVo) throws Exception;
    
    /**
      * @Title: selectByPrimaryNuType
      * @Description: TODO  根据单据号类型 查询单据号记录
      * @param @param nuType
      * @param @return
      * @param @throws Exception
      * @return BaseNumber
      * @throws
     */
    BaseNumber selectByPrimaryNuType(String nuType) throws Exception;
    
}