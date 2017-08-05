package com.dinghao.service.template.business.goods.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.goods.GdsClsDao;
import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.goods.GdsClsVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.goods.GdsClsService;

/**
  * @ClassName: GdsClsServiceImpl
  * @Description: TODO  商品分类管理ｓｅｒｖｉｃｅ　实现
  * @author helong 
  * @date 2016年1月7日 上午11:13:27
  * @version V1.0
  *
 */
@Service
@Transactional
public class GdsClsServiceImpl implements GdsClsService{
	@Autowired
	private GdsClsDao gdsClsDao;
	@Autowired
	private GdsInfoDao gdsDao;
	
	/**
	  * <p>Title: addGdsCls</p>
	  * <p>Description: 添加商品分类</p>
	  * @param eClsVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsClsService#addGdsCls(com.dinghao.entity.vo.template.business.goods.GdsClsVo)
	 */
	public CommResponse addGdsCls(GdsClsVo eClsVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		String clsNo = calClsNo(eClsVo);
		eClsVo.setClsNo(clsNo);
		eClsVo.setClsStatus("1");
		gdsClsDao.insertSelective(eClsVo);
		return commResponse;
	}

	/**
	  * <p>Title: delGdsCls</p>
	  * <p>Description: 删除商品分类</p>
	  * @param eClsVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsClsService#delGdsCls(com.dinghao.entity.vo.template.business.goods.GdsClsVo)
	 */
	public CommResponse delGdsCls(GdsClsVo eClsVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/**1.查询 是否存在商品分类*/
		GdsCls gdsCls = gdsClsDao.selectByPrimaryKey(eClsVo.getId());
		if(gdsCls == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020001);
		}
		/**2.查询 是否存在子分类*/
		List<GdsCls> allChildList = gdsClsDao.selectGdsClsAllChild(gdsCls.getClsNo());
		if(allChildList.size() >1){
			throw new DHBizException(ExceptionConstant.ERR_DH020002);
		}
		
		/**3.查询 是否存在相关的商品*/
		GdsInfoVo gdsInfo = new GdsInfoVo();
		gdsInfo.setClsId(eClsVo.getId());
		List<GdsInfo> gdsList = gdsDao.selectGdsInfoGridListNoPage(gdsInfo);
		if(gdsList!= null && gdsList.size()>0){
			throw new DHBizException(ExceptionConstant.ERR_DH020003);
		}
		/**4.删除分类*/
		gdsClsDao.deleteByPrimaryKey(eClsVo.getId());
		return commResponse;
	}

	/**
	  * <p>Title: modGdsCls</p>
	  * <p>Description: 修改商品分类</p>
	  * @param eClsVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsClsService#modGdsCls(com.dinghao.entity.vo.template.business.goods.GdsClsVo)
	 */
	public CommResponse modGdsCls(GdsClsVo eClsVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		/**1.查询 是否存在商品分类*/
		GdsCls gdsCls = gdsClsDao.selectByPrimaryKey(eClsVo.getId());
		if(gdsCls == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020001);
		}
		/**2.修改分类*/
		gdsClsDao.updateByPrimaryKeySelective(eClsVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryGdsClsById</p>
	  * <p>Description: 根据id查询商品分类</p>
	  * @param eClsVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsClsService#queryGdsClsById(com.dinghao.entity.vo.template.business.goods.GdsClsVo)
	 */
	public GdsCls queryGdsClsById(GdsClsVo eClsVo) throws Exception {
		GdsCls gdsCls = gdsClsDao.selectByPrimaryKey(eClsVo.getId());
		return gdsCls;
	}

	/**
	  * <p>Title: queryGdsClsList</p>
	  * <p>Description: 查询商品分类列表信息 </p>
	  * @param eClsVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsClsService#queryGdsClsList(com.dinghao.entity.vo.template.business.goods.GdsClsVo)
	 */
	public CommResponse queryGdsClsList(GdsClsVo eClsVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		commResponse.setData(gdsClsDao.selectGdsClsList(eClsVo));
		
		
		
		return commResponse;
	}
	
	/**
	  * @Title: calClsNo
	  * @Description: TODO   计算该分类下下个分类编号
	  * @param @param eClsVo
	  * @param @return
	  * @param @throws Exception
	  * @return String
	  * @throws
	 */
	private String calClsNo(GdsClsVo eClsVo) throws Exception{
		String clsNo = gdsClsDao.findMaxClsNo(eClsVo);
		String clsMaxNo = "";
		if(clsNo == null ){
			clsMaxNo = eClsVo.getClsPno()+"000";
		}else{
			clsMaxNo = clsNo;
		}
		String clsNo1 = "";
		String clsNo2 = "";
		if(!("0").equals(clsMaxNo)){
			clsNo1 = clsMaxNo.substring(0,clsMaxNo.length()-3);
			clsNo2 = clsMaxNo.substring(clsMaxNo.length()-3,clsMaxNo.length());
		}else{
			return "001";
		}
		clsNo2 = String.valueOf((Integer.parseInt(clsNo2)+1));
		if(clsNo2.length()==1){
			clsNo2 = "00"+clsNo2;
		}else if(clsNo2.length()==2){
			clsNo2 = "0"+clsNo2;
		}
		String returnClsNo = clsNo1+clsNo2;
		returnClsNo.replaceAll("000", "00");
		return returnClsNo;
	}
	
	/**
	  * <p>Title: findGdsClsListPage</p>
	  * <p>Description: 分页商品子分类列表</p>
	  * @param gdsClsVo
	  * @return
	  * @throws Exception
	  * @see 
	 */
	public CommResponse findGdsClsListPage(GdsClsVo gdsClsVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		//查询当前选择 分类下  所有的子分类(包括自己)
		List<GdsCls> clsList = null ;
		clsList = gdsClsDao.selectGdsClsChild(gdsClsVo);	
		
		PageVo<GdsCls> pageVo = new PageVo<GdsCls>();
		pageVo.setList(clsList); 
		if(clsList!=null)
			 pageVo.setCount(clsList.size());
		
		commResponse.setData(pageVo);
		return commResponse;
	}
	
}
