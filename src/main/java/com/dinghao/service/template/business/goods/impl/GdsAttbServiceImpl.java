package com.dinghao.service.template.business.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.goods.GdsAttbDao;
import com.dinghao.dao.template.business.goods.GdsAtvDao;
import com.dinghao.dao.template.business.goods.GdsClsDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsAttb;
import com.dinghao.entity.template.business.goods.GdsAttbTable;
import com.dinghao.entity.template.business.goods.GdsAtv;
import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.vo.template.business.goods.GdsAttbVo;
import com.dinghao.entity.vo.template.business.goods.GdsAtvVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.goods.GdsAttbService;

/**
  * @ClassName: GdsAttbServiceImpl
  * @Description: TODO   商品属性名称管理 service 实现
  * @author helong 
  * @date 2016年1月5日 下午3:45:32
  * @version V1.0
  *
 */
@Service
@Transactional
public class GdsAttbServiceImpl implements GdsAttbService{
	@Autowired
	private GdsAttbDao attbDao;
	@Autowired
	private GdsAtvDao atvDao;
	@Autowired
	private GdsClsDao clsDao;

	/**
	  * <p>Title: queryAttbForTable</p>
	  * <p>Description: 查询根据前台  table所需要的数据格式相关的数据</p>
	  * @param gdsAttbVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAttbService#queryAttbForTable(com.dinghao.entity.vo.business.goods.GdsAttbVo)
	 */
	public CommResponse queryAttbForTable(GdsAttbVo gdsAttbVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/*1.查询分类的所有父节点id*/
		List<Long> clsIds = new ArrayList<Long>();
		clsIds.add(gdsAttbVo.getClsId()); 
		Long clsId = gdsAttbVo.getClsId();
		boolean continueQuery = true;
		while(continueQuery){
			GdsCls gdsCls = clsDao.findParentCLsInfo(clsId);
			clsIds.add(gdsCls.getId()); 
			if("0".equals(gdsCls.getClsPno())){
				continueQuery = false;
			}else{
				clsId = gdsCls.getId();
			}
		}
		
		/*2.根据商品分类id 查询属性名称和属性可选值的集合*/
		gdsAttbVo.setClsIds(clsIds);
		List<GdsAttb> attbList = attbDao.queryEGdsAttbListByClsIds(gdsAttbVo);
		GdsAtvVo gdsAtvVo = new GdsAtvVo();
		gdsAtvVo.setClsIds(clsIds);
		List<GdsAtv> atvList = atvDao.queryEGdsAtvListByClsIds(gdsAtvVo);
		List<GdsAttbTable> attbTableList = new ArrayList<GdsAttbTable>();
		
		/*3.根据获取到的数据  封装 成为前台table所需要的数据格式*/
		for(GdsAttb gdsAttb : attbList){
			Long attbId = gdsAttb.getId();
			String attbName = gdsAttb.getAttbName();
			GdsAttbTable attbTable =new GdsAttbTable();
			List<GdsAtv> tempAtvList = new ArrayList<GdsAtv>();
			for(GdsAtv gdsAtv : atvList){
				if(gdsAtv.getAttbId().equals(attbId)){
					tempAtvList.add(gdsAtv);
				}
			}
			attbTable.setAttbId(attbId);
			attbTable.setAttbName(attbName);
			attbTable.setGdsAtvList(tempAtvList);
			attbTableList.add(attbTable);
		}
		commResponse.setData(attbTableList);
		return commResponse;
	}

	/**
	  * <p>Title: addGdsAttb</p>
	  * <p>Description: 查询根据前台  table所需要的数据格式相关的数据</p>
	  * @param gdsAttbVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAttbService#queryAttbForTable(com.dinghao.entity.vo.business.goods.GdsAttbVo)
	 */
	public CommResponse addGdsAttb(GdsAttbVo gdsAttbVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/*1.查询分类的所有父节点id  包括自己*/
		List<Long> clsIds = new ArrayList<Long>();
		clsIds.add(gdsAttbVo.getClsId()); 
		Long clsId = gdsAttbVo.getClsId();
		boolean continueQuery = true;
		while(continueQuery){
			GdsCls gdsCls = clsDao.findParentCLsInfo(clsId);
			clsIds.add(gdsCls.getId()); 
			if("0".equals(gdsCls.getClsPno())){
				continueQuery = false;
			}else{
				clsId = gdsCls.getId();
			}
		}
		
		/*2.根据商品分类id 属性名查询   是否存在相同的记录*/
		gdsAttbVo.setClsIds(clsIds);
		List<GdsAttb> attbList = attbDao.queryEGdsAttbListByClsIds(gdsAttbVo);
		
		if(attbList != null && attbList.size()>0){
			throw new DHBizException(ExceptionConstant.ERR_DH020011);
		}
		/*3.添加属性名称*/
		attbDao.insertSelective(gdsAttbVo);
		return commResponse;
	}

	/**
	  * <p>Title: delGdsAttb</p>
	  * <p>Description: 删除 属性名称</p>
	  * @param gdsAttbVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAttbService#delGdsAttb(com.dinghao.entity.vo.business.goods.GdsAttbVo)
	 */
	public CommResponse delGdsAttb(GdsAttbVo gdsAttbVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/**1.查询 是否存在商品属性*/
		GdsAttb attb = attbDao.selectByPrimaryKey(gdsAttbVo.getId());
		if(attb == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020004);
		}
		/**2.删除该商品属性*/
		attbDao.deleteByPrimaryKey(gdsAttbVo.getId());
		return commResponse;
	}

	/**
	  * <p>Title: modGdsAttb</p>
	  * <p>Description: 修改属性名称</p>
	  * @param gdsAttbVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAttbService#modGdsAttb(com.dinghao.entity.vo.business.goods.GdsAttbVo)
	 */
	public CommResponse modGdsAttb(GdsAttbVo gdsAttbVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		/**1.查询 是否存在商品属性*/
		GdsAttb attb = attbDao.selectByPrimaryKey(gdsAttbVo.getId());
		if(attb == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020004);
		}
		/**2.查询分类的所有父节点id  包括自己*/
		List<Long> clsIds = new ArrayList<Long>();
		clsIds.add(gdsAttbVo.getClsId()); 
		Long clsId = gdsAttbVo.getClsId();
		boolean continueQuery = true;
		while(continueQuery){
			GdsCls gdsCls = clsDao.findParentCLsInfo(clsId);
			clsIds.add(gdsCls.getId()); 
			if("0".equals(gdsCls.getClsPno())){
				continueQuery = false;
			}else{
				clsId = gdsCls.getId();
			}
		}
		
		/**3.根据商品分类id 属性名查询  父类的属性 是否存在几条记录*/
		gdsAttbVo.setClsIds(clsIds);
		List<GdsAttb> attbList = attbDao.queryEGdsAttbListByClsIds(gdsAttbVo);
		boolean isReapt = false;
		for(GdsAttb gdsAttb : attbList){
			if(gdsAttb.getId() != gdsAttbVo.getId()){
				isReapt = true;
			}
		}
		if(isReapt){
			throw new DHBizException(ExceptionConstant.ERR_DH020011);
		}
		
		/**4.修改该商品属性*/
		attbDao.updateByPrimaryKeySelective(gdsAttbVo);
		return commResponse;
	}

}
