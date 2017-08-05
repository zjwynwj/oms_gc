package com.dinghao.dao.template.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.base.CustInfo;
import com.dinghao.entity.vo.template.base.CustInfoVo;
/**
  * @ClassName: CustInfoDao
  * @Description: TODO  客户管理dao层
  * @author helong 
  * @date 2016年1月6日 上午9:35:41
  * @version V1.0
  *
 */
@Repository
public interface CustInfoDao {
	
	/**
	  * @Title: deleteByPrimaryKey
	  * @Description: TODO  根据 id删除客户
	  * @param @param id
	  * @param @return
	  * @return int
	  * @throws
	 */
    int deleteByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: insert
      * @Description: TODO  普通插入客户记录方法
      * @param @param custInfoVo
      * @param @return
      * @return int
      * @throws
     */
    int insert(CustInfoVo custInfoVo) throws Exception; 

    /**
      * @Title: insertSelective
      * @Description: TODO  根据属性是否为空  插入客户记录
      * @param @param custInfoVo
      * @param @return
      * @return int
      * @throws
     */
    int insertSelective(CustInfoVo custInfoVo) throws Exception;

    /**
      * @Title: selectByPrimaryKey
      * @Description: TODO  根据id查询客户记录
      * @param @param id
      * @param @return
      * @return CustInfo
      * @throws
     */
    CustInfo selectByPrimaryKey(Long id) throws Exception;

    /**
      * @Title: updateByPrimaryKeySelective
      * @Description: TODO    普通 修改客户信息
      * @param @param custInfoVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKeySelective(CustInfoVo custInfoVo) throws Exception;

    /**
      * @Title: updateByPrimaryKey
      * @Description: TODO   根据id修改客户信息
      * @param @param custInfoVo
      * @param @return
      * @return int
      * @throws
     */
    int updateByPrimaryKey(CustInfoVo custInfoVo) throws Exception;
    
    /**
      * @Title: selectCustInfoListCount
      * @Description: TODO  分页查询客户的记录数
      * @param @param baseNumberVo
      * @param @return
      * @param @throws Exception
      * @return int
      * @throws
     */
   int selectCustInfoListCount(CustInfoVo custInfoVo) throws Exception;
   
   /**
     * @Title: selectCustInfoListPage
     * @Description: TODO  分页查询客户记录 集合
     * @param @param baseNumberVo
     * @param @return
     * @param @throws Exception
     * @return List<BaseNumber>
     * @throws
    */
   List<CustInfo> selectCustInfoListPage(CustInfoVo custInfoVo) throws Exception;
   
   /**
     * @Title: selectCustInfoCountByCustNo
     * @Description: TODO  根据custNo查询 记录数
     * @param @param custInfoVo
     * @param @return
     * @param @throws Exception
     * @return int
     * @throws
    */
   int selectCustInfoCountByCustNo(String custNo) throws Exception;
}