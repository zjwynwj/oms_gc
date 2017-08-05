package com.dinghao.service.template.business.goods.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.base.ShopDao;
import com.dinghao.dao.template.business.goods.DownGoodsDao;
import com.dinghao.dao.template.business.goods.DownGoodsSkuDao;
import com.dinghao.dao.template.business.goods.GdsClsDao;
import com.dinghao.dao.template.business.goods.GdsInfoDao;
import com.dinghao.dao.template.locstock.LocStockDao;
import com.dinghao.dao.template.warehouse.WarehouseDao;
import com.dinghao.dao.template.warehousepositions.WarehousePositionsDao;
import com.dinghao.entity.htobject.attrStruct;
import com.dinghao.entity.htobject.resultEntityProduct;
import com.dinghao.entity.htobject.resultProduct;
import com.dinghao.entity.htobject.rowStruct;
import com.dinghao.entity.htobject.tableStruct;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.DownGoods;
import com.dinghao.entity.template.business.goods.DownGoodsSku;
import com.dinghao.entity.template.business.goods.GdsCls;
import com.dinghao.entity.template.business.goods.GdsInfo;
import com.dinghao.entity.template.warehouse.Warehouse;
import com.dinghao.entity.template.warehousepositions.WarehousePositions;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.entity.vo.template.business.goods.DownGoodsParamVo;
import com.dinghao.entity.vo.template.business.goods.DownGoodsSkuVo;
import com.dinghao.entity.vo.template.business.goods.DownGoodsVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.warehousepositions.WarehousePositionsVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.goods.GdsInfoService;
import com.dinghao.service.template.business.order.impl.SalesOrderServiceImpl;
import com.dinghao.util.DateUtil;
import com.dinghao.util.StringUtil;
import com.dinghao.util.TBUtil;
import com.dinghao.util.WSCUtil;
import com.google.gson.Gson;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.PropImg;
import com.taobao.api.domain.Sku;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.request.ItemsSellerListGetRequest;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import com.taobao.api.response.ItemsSellerListGetResponse;
/**
  * @ClassName: GdsInfoServiceImpl
  * @Description: TODO  商品管理servcei实现
  * @author helong 
  * @date 2016年1月5日 下午3:54:52
  * @version V1.0
  *
 */
@Service
@Transactional
public class GdsInfoServiceImpl implements GdsInfoService{
	
	/** 日志记录  */
	private static final Log logger = LogFactory.getLog(SalesOrderServiceImpl.class);
	@Autowired
	private GdsInfoDao gdsDao;
	@Autowired
	private GdsClsDao clsDao;
	@Autowired
	private DownGoodsDao downGoodsDao;
	@Autowired
	private DownGoodsSkuDao downGoodsSkuDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	WarehouseDao warehouseDao;
	@Autowired
	WarehousePositionsDao warehousePositionsDao;
	@Autowired
	private LocStockDao locStockDao;
	/**
	  * <p>Title: addGdsInfo</p>
	  * <p>Description: 添加商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#addGdsInfo(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse addGdsInfo(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if(null != gdsInfoVo.getGdsPact() && !"".equals(gdsInfoVo.getGdsPact())){
			GdsInfoVo queryGds = new GdsInfoVo();
			queryGds.setGdsPact(gdsInfoVo.getGdsPact());
			int count = gdsDao.selectGdsInfoGridListCount(queryGds);
			if(count > 0){
				throw new DHBizException(ExceptionConstant.ERR_DH020012);
			}
		}
		
		String gdsNoSkuOuterId = "";
		if(gdsInfoVo.getGdsNo() != null && !"".equals(gdsInfoVo.getGdsNo())){
			gdsNoSkuOuterId += gdsInfoVo.getGdsNo();
		}
		if(gdsInfoVo.getSkuOuterId() != null && !"".equals(gdsInfoVo.getSkuOuterId())){
			gdsNoSkuOuterId += gdsInfoVo.getSkuOuterId();
		}
		List<GdsInfo> gdsInfoList = gdsDao.queryGdsInfoByGdsNoSkuOuterId(gdsNoSkuOuterId);
		if(null == gdsInfoList || gdsInfoList.size()==0){
			gdsInfoVo.setGdsFlag("0");
			gdsDao.insertSelective(gdsInfoVo);
			
			Warehouse warehouse = warehouseDao.queryDefaultWareHouse();
			WarehousePositionsVo warehousePositionsVo = new WarehousePositionsVo();
			warehousePositionsVo.setDhWarehousePositions(Integer.parseInt(warehouse.getId()+""));
			WarehousePositions warehousePositions = warehousePositionsDao.queryDefaultPosition(warehousePositionsVo);
			
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setGdsId(gdsInfoVo.getId());
			locStockVo.setStockId(warehouse.getId());
			locStockVo.setLocId(warehousePositions.getId());
			locStockVo.setMaxQty(10);
			locStockVo.setMinQty(0);
			locStockVo.setSafeQty(0L);
			locStockVo.setTotalQty(0L);
			locStockVo.setLocked(false);
			locStockVo.setIsDeleted(false);
			locStockVo.setOrderQty(0L);
			locStockVo.setTimestamp(new Date());
			locStockVo.setCreateDate(new Date());
			locStockVo.setModifyDate(new Date());
			locStockDao.insertSelective(locStockVo);
		}else{
			throw new DHBizException(ExceptionConstant.ERR_DH020006);
		}
		
		return commResponse;
	}

	/**
	  * <p>Title: modGdsInfo</p>
	  * <p>Description: 修改商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#modGdsInfo(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse modGdsInfo(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		if(null != gdsInfoVo.getGdsPact() && !"".equals(gdsInfoVo.getGdsPact())){
			GdsInfoVo queryGds = new GdsInfoVo();
			queryGds.setGdsPact(gdsInfoVo.getGdsPact());
			List<GdsInfo> gdsList = gdsDao.selectGdsInfoGridListNoPage(queryGds);
			if(gdsList != null && gdsList.size()>1){
				throw new DHBizException(ExceptionConstant.ERR_DH020012);
			}else if(gdsList != null && gdsList.size()==1){
				if(!gdsList.get(0).getId().equals(gdsInfoVo.getId())){
					throw new DHBizException(ExceptionConstant.ERR_DH020012);
				}
			}
		}
		
		GdsInfo gdsInfo = gdsDao.selectByPrimaryKey(gdsInfoVo.getId());
		if(gdsInfo == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020007);
		}else{
			String gdsNoSkuOuterId = "";
			if(gdsInfoVo.getGdsNo() != null && !"".equals(gdsInfoVo.getGdsNo())){
				gdsNoSkuOuterId += gdsInfoVo.getGdsNo();
			}
			if(gdsInfoVo.getSkuOuterId() != null && !"".equals(gdsInfoVo.getSkuOuterId())){
				gdsNoSkuOuterId += gdsInfoVo.getSkuOuterId();
			}
			List<GdsInfo> gdsInfoList = gdsDao.queryGdsInfoByGdsNoSkuOuterId(gdsNoSkuOuterId);
			if( null !=gdsInfoList  && gdsInfoList.size() >0 && !gdsInfoVo.getId().equals(gdsInfoList.get(0).getId()) ){
				throw new DHBizException(ExceptionConstant.ERR_DH020006);
			}else{
				gdsDao.updateByPrimaryKeySelective(gdsInfoVo);
			}
		}
		return commResponse;
	}

	/**
	  * <p>Title: delGdsInfo</p>
	  * <p>Description: 删除商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#delGdsInfo(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse delGdsInfo(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		GdsInfo gdsInfo = gdsDao.queryGdsInfoById(gdsInfoVo.getId());
		if(gdsInfo == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020007);
		}else{
			gdsDao.deleteByPrimaryKey(gdsInfoVo.getId());
		}
		return commResponse;
	}

	/**
	  * <p>Title: queryGdsInfoById</p>
	  * <p>Description: 根据id查询商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#queryGdsInfoById(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse queryGdsInfoById(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		commResponse.setData(gdsDao.queryGdsInfoById(gdsInfoVo.getId()));
		return commResponse;
	}

	/**
	  * <p>Title: findGdsInfoListPage</p>
	  * <p>Description: 分页查询商品信息</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#findGdsInfoListPage(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse findGdsInfoListPage(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		//查询当前选择 分类下  所有的子分类(包括自己)
		if(null != gdsInfoVo.getClsNo() && !"".equals(gdsInfoVo.getClsNo())){
			List<GdsCls> clsList = clsDao.selectGdsClsAllChild(gdsInfoVo.getClsNo());
			StringBuffer clsIdList = new StringBuffer();
			for(GdsCls cls : clsList){
				clsIdList.append("," + cls.getId());
			}
			String clsIds[] = clsIdList.toString().substring(1, clsIdList.length()).split(",");
			gdsInfoVo.setClsIds(clsIds);
			gdsInfoVo.setClsId(null);
		}
		
		PageVo<GdsInfo> pageVo = new PageVo<GdsInfo>();
		pageVo.setRows(gdsInfoVo.getRows());
		pageVo.setList(gdsDao.selectGdsInfoGridListPage(gdsInfoVo));
		pageVo.setCount(gdsDao.selectGdsInfoGridListCount(gdsInfoVo));
		commResponse.setData(pageVo);
		return commResponse;
	}
	
	/**
	  * <p>Title: stockOperatGds</p>
	  * <p>Description: 批量操作商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsInfoService#stockOperatGds(com.dinghao.entity.vo.business.goods.GdsInfoVo)
	 */
	public CommResponse stockOperatGds(GdsInfoVo gdsInfoVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		String ids = gdsInfoVo.getIds();
		if(!StringUtil.isEmpty(ids)){
			String idArray[] = ids.split(",");
			for(String id : idArray){
				GdsInfoVo gdsInfo = new GdsInfoVo();
				gdsInfo.setId(Long.parseLong(id));
				gdsInfo.setGdsStatus(gdsInfoVo.getGdsStatus());
				gdsDao.updateByPrimaryKeySelective(gdsInfo);
			}
		}
		return commResponse;
	}

	/**
	  * <p>Title: downLoadGoods</p>
	  * <p>Description: 下载商品</p>
	  * @param gdsInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsInfoService#downLoadGoods(com.dinghao.entity.vo.template.business.goods.GdsInfoVo)
	 */
	public CommResponse downLoadGoods(DownGoodsParamVo goodsParamVo) throws Exception {
		CommResponse  commResponse = new CommResponse();
		String downTime = DateUtil.getCurrentTimeMillis()+"";
		goodsParamVo.setDownTime(downTime);
		//获得默认分类id
		goodsParamVo.setClsId(1L);
		goodsParamVo.setCal("件");
		/**1.获取所有店铺的信息(判断sessionKey是否过期   。。。。)*/
		List<Shop> shopList = shopDao.selectListPage(new ShopVo());
		for(Shop shop:shopList){
			/**2.开始根据店铺信息下载各个平台的商品*/
			goodsParamVo.setPlatType(shop.getPlanType());
			goodsParamVo.setShopId(shop.getId());
			goodsParamVo.setShopTitle(shop.getName());
			goodsParamVo.setShopNick(shop.getNickname());
			try{
				startDownGoodsInfo(goodsParamVo ,shop);
			}catch(Exception e){
				logger.error("店铺："+shop.getName()+"下载失败");
			}
		}
		commResponse.setErrMsg("商品同步成功!");
		return commResponse;
	}
	
	/**
	 * 
	  * @Title: startDownGoodsInfo
	  * @Description: TODO
	  * @param @param goodsParamVo
	  * @param @param sessionKey
	  * 步骤 	1.根据平台类型获取 商品数据
	  * 	2.遍历商品集合到e_busi_goods表中查询是否存在相同  numIid  （1.存在:修改      2.不存在:增加）
	  * 		2.1    查看是否存在sku信息            (1.存在：遍历sku 2.不存在)
	  * 			2.1.1  是否存在相同skuId  (1.存在:修改  2.不存在：增加) --->判断是否存在sku.outerId和 outerId(1.至少存在一个：插入到本地商品信息  2.都不存在：请补全商家编码) 
	  * 			2.1.2 判断是否存在outerId (1.存在：插入到本地商品信息  2.不存在：请补全商家编码) 
	  * 
	  * 		2.2   查看是否存在sku信息             (1.不存在: 循环增加sku   2.不存在)
	  * 			2.2.1   判断是否存在sku.outerId和outerId(1.至少存在一个：插入到本地商品信息  2.不存在：请补全商家编码)
	  * 			2.2.2    判断是否存在outerId (1.存在：插入到本地商品信息  2.不存在：请补全商家编码)
	  * 		3.开始铺货
	  * 		4.回写铺货信息到铺货表
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	private CommResponse startDownGoodsInfo(DownGoodsParamVo goodsParamVo , Shop shop) throws Exception{
		CommResponse  commResponse = new CommResponse();
		String distributionTime = DateUtil.getCurDate();
		List<DownGoodsVo> allDownGoodsList = null;
		/**1.根据平台类型获取 商品数据*/
		if(("TB").equals(goodsParamVo.getPlatType())){
			allDownGoodsList = downGoodsForTaobao(goodsParamVo , shop.getSessionkey() ,shop.getAppkey() , shop.getAppsecret());
		}
		
		if(("WSC").equals(goodsParamVo.getPlatType())){
			allDownGoodsList = downGoodsForWSC(goodsParamVo );
		}
		
		for(DownGoodsVo downGoodsVo : allDownGoodsList){ 
			/**2.遍历商品集合到e_busi_goods表中查询是否存在相同  numIid*/
			if (downGoodsVo.getNumIid().equals("20160302")){
				
				int a =1;
				a=a;
				
						
			}
        	DownGoodsVo dGoodsVo = new DownGoodsVo();
        	dGoodsVo.setNumIid(downGoodsVo.getNumIid());
        	DownGoods dGoods =  downGoodsDao.selectByParam(dGoodsVo);
        	//定义后续操作需要的公共对象
        	List<DownGoodsSkuVo> downGoodsSkuList = null;
        	DownGoodsSkuVo dGoodsSkuVo = new DownGoodsSkuVo();
        	List<DownGoodsSku> dGoodsSkuList = new ArrayList<DownGoodsSku>();
        	
        	GdsInfoVo gdsInfoVo = new GdsInfoVo();
        	CommResponse resInfo = null;
        	
        	
        	//本地存在相同numIid ：存在
        	if(null != dGoods && null !=dGoods.getId()){
        		//遍历 sku
        		/**2.1    查看是否存在sku信息  */

        		if(downGoodsVo.getSkuList() !=null && downGoodsVo.getSkuList().size()!=0){
        			downGoodsSkuList = downGoodsVo.getSkuList();
        			for(DownGoodsSkuVo skuDto : downGoodsSkuList){
        				/** 2.1.1是否存在相同skuId */
        				dGoodsSkuVo.setSkuId(skuDto.getSkuId());
        				dGoodsSkuVo.setNumIid(skuDto.getNumIid());
        				dGoodsSkuList = downGoodsSkuDao.selectBySkuId(dGoodsSkuVo);
        				//dGoodsSkuList = downGoodsSkuDao.selectByParam(dGoodsSkuVo);
            			
        		
        				if(null != dGoodsSkuList &&  dGoodsSkuList.size()>0){
	        				//存在  修改信息
        					skuDto.setId(dGoodsSkuList.get(0).getId());
        					skuDto.setDownTime(dGoodsSkuList.get(0).getDownTime());
        					downGoodsSkuDao.updateByPrimaryKeySelective(skuDto);
	        			}else{
	        				//不存在  添加信息
	        				skuDto.setDownTime(goodsParamVo.getDownTime());
	        				downGoodsSkuDao.insertSelective(skuDto);
	        			}
        				/**2.1.2 判断是否存在sku.outerId outerId  */
	        			if((null == skuDto.getOuterSkuId() || ("").equals(skuDto.getOuterSkuId())) &&
	        					(null == downGoodsVo.getOuterId() || ("").equals(downGoodsVo.getOuterId()))
	        				)	
	        				{
	        				//不存在:请补全商家编码
	        				downGoodsVo.setDownNotice(distributionTime+":请补全商品商家编码 !");
	        			}else{
	        				//至少存在一个 ：插入到本地商品信息
	        				gdsInfoVo = new GdsInfoVo();
	        				gdsInfoVo.setGdsNo(downGoodsVo.getOuterId());
	        				gdsInfoVo.setGdsFormat(skuDto.getSpec());
	        				gdsInfoVo.setSkuOuterId(skuDto.getOuterSkuId());
	        				gdsInfoVo.setGdsSelPrice(skuDto.getPrice());
	        				resInfo = addGdsInfoForPlat(gdsInfoVo ,shop , downGoodsVo , goodsParamVo);
	        				if(("999999").equals(resInfo.getErrCode())){
	        					downGoodsVo.setDownNotice(distributionTime+":已存在相同商家编码和规格代码组合!");
	        				}
	        			}
        			}
        		}else{
        			//判断是否存在sku.outerId 和outerId
        			
        			if((null == downGoodsVo.getOuterId() || ("").equals(downGoodsVo.getOuterId()))){
        				downGoodsVo.setDownNotice(distributionTime+":请补全商品商家编码 !");
        			}else{
        				
        			    gdsInfoVo = new GdsInfoVo();
        				gdsInfoVo.setGdsSelPrice(downGoodsVo.getPrice());
        				gdsInfoVo.setGdsNo(downGoodsVo.getOuterId());
        				resInfo= addGdsInfoForPlat(gdsInfoVo , shop , downGoodsVo , goodsParamVo);
        				if(("999999").equals(resInfo.getErrCode())){
        					downGoodsVo.setDownNotice(distributionTime+":已存在相同商家编码和规格代码组合!");
        				}
        			}
        		}
        		downGoodsVo.setId(dGoods.getId());
        		if(downGoodsVo.getDownNotice() == null || ("").equals(downGoodsVo.getDownNotice())){
        			downGoodsVo.setDownNotice(distributionTime+":商品同步成功");
        		}
        		downGoodsVo.setDownTime(goodsParamVo.getDownTime());
        		downGoodsDao.updateByPrimaryKeySelective(downGoodsVo);
        	}else{//不存在相同numIid
        		/** 2.2   查看是否存在sku信息*/
        		if(downGoodsVo.getSkuList() != null&& downGoodsVo.getSkuList().size()!=0){//存在
        			downGoodsSkuList = downGoodsVo.getSkuList();
        			for(DownGoodsSkuVo skuDto : downGoodsSkuList){//遍历sku
        				//添加sku信息
        				skuDto.setDownTime(goodsParamVo.getDownTime());
        				String propUrl = skuDto.getPropUrl();
        				String prop[] = propUrl.split(";");
        				propUrl = prop[0];
        				skuDto.setPropUrl(propUrl);
        				
        				skuDto.setCostPrice(new BigDecimal("0.00"));
        				
            		    downGoodsSkuDao.insertSelective(skuDto);
        				
        				/**2.2.1   判断是否存在sku.outerId和outerId*/
        				if((null == skuDto.getOuterSkuId() || ("").equals(skuDto.getOuterSkuId())) &&
	        				(null == downGoodsVo.getOuterId() || ("").equals(downGoodsVo.getOuterId()))
	        			){
        					//不存在:请补全商家编码
	        				downGoodsVo.setDownNotice(distributionTime+":请补全商品商家编码 !");
	        			}else{
	        				//至少存在一个 ：插入到本地商品信息
        					gdsInfoVo = new GdsInfoVo();
	        				gdsInfoVo.setGdsFormat(skuDto.getSpec());
	        				gdsInfoVo.setGdsNo(skuDto.getOuterSkuId());
	        				gdsInfoVo.setSkuOuterId(skuDto.getOuterSkuId());
	        				gdsInfoVo.setGdsSelPrice(skuDto.getPrice());
	        				resInfo= addGdsInfoForPlat(gdsInfoVo , shop , downGoodsVo , goodsParamVo);
	        				if(("999999").equals(resInfo.getErrCode())){
	        					downGoodsVo.setDownNotice(distributionTime+":已存在相同商家编码和规格代码组合!");
	        				}
	        			}
        			}
        		}else{
        			/**2.2.2   判断是否存在outerId*/
        			if(null != downGoodsVo.getOuterId() && !("").equals(downGoodsVo.getOuterId())){
        				//存在 ：插入到本地商品信息
        				gdsInfoVo = new GdsInfoVo();
        				gdsInfoVo.setGdsSelPrice(downGoodsVo.getPrice());
        				gdsInfoVo.setGdsNo(downGoodsVo.getOuterId());
        				resInfo= addGdsInfoForPlat(gdsInfoVo , shop , downGoodsVo ,goodsParamVo);
        				if(("999999").equals(resInfo.getErrCode())){
        					downGoodsVo.setDownNotice(distributionTime+":已存在相同商家编码和规格代码组合!");
        				}
        			}else{
        				//不存在:请补全商家编码
        				downGoodsVo.setDownNotice(distributionTime+":请补全商品商家编码 !");
        			}
        		}
        		//添加商品item信息
        		if(downGoodsVo.getDownNotice() == null || ("").equals(downGoodsVo.getDownNotice())){
        			downGoodsVo.setDownNotice(distributionTime+":商品同步成功");
        		}
        		downGoodsVo.setDownTime(goodsParamVo.getDownTime());
        		downGoodsDao.insertSelective(downGoodsVo);
        	}
		}
		return commResponse;
	}
	
	private List<DownGoodsVo> downGoodsForTaobao(DownGoodsParamVo goodsParamVo , String sessionKey , String appkey , String secret) throws Exception{
		//淘宝下载
		List<DownGoodsVo> allDownGoodsList = new ArrayList<DownGoodsVo>();
		/**1.获得所有的num_iid*/
		TaobaoClient client = TBUtil.getTaobaoClient(appkey , secret);//实例化TopClient类
		List<Item> itemList = new ArrayList<Item>();
		boolean hashNext = true;
		Long pageNo = 1L;
		//获得出售中的商品
		while(hashNext){
	    	ItemsOnsaleGetRequest onSaleReq = new ItemsOnsaleGetRequest ();//实例化具体API对应的Request类
	    	onSaleReq.setFields("barcode,num_iid,pic_url,detail_url,title,props,price,modified,outer_id");
	    	onSaleReq.setPageNo(pageNo++);
	    	onSaleReq.setPageSize(200L);
	        ItemsOnsaleGetResponse onSaleResponse;
	        onSaleResponse = client.execute(onSaleReq,sessionKey); //执行API请求并打印结果
	        if(("27").equals(onSaleResponse.getErrorCode()) || ("44").equals(onSaleResponse.getErrorCode())){
	        	throw new DHBizException(ExceptionConstant.ERR_DH000002);
	        }
	        if(onSaleResponse.getItems() != null){
	        	itemList.addAll(onSaleResponse.getItems());
	        }else{
	        	hashNext = false;
	        }
	        if(hashNext){
		        if(onSaleResponse.getItems().size() < 200L){
		        	hashNext = false;
		        }
	        }
		}
       
		hashNext = true;
		pageNo = 1L;
		//获得库存中的商品
		while(hashNext){
			ItemsInventoryGetRequest invReq=new ItemsInventoryGetRequest();
			invReq.setFields("barcode,num_iid,pic_url,detail_url,title,props,price,modified,outer_id");
			invReq.setPageNo(pageNo++);
			invReq.setPageSize(200L);
	        ItemsInventoryGetResponse InvResponse;
	        InvResponse = client.execute(invReq,sessionKey); //执行API请求并打印结果
	        
	        if(("27").equals(InvResponse.getErrorCode()) || ("44").equals(InvResponse.getErrorCode())){
	        	throw new DHBizException(ExceptionConstant.ERR_DH000002);
	        }
	        if(InvResponse.getItems() != null){
	        	itemList.addAll(InvResponse.getItems());
	        }else{
	        	hashNext = false;
	        }
	        if(hashNext){
		        if(InvResponse.getItems().size() < 200L){
		        	hashNext = false;
		        }
	        }
		}
       String numIids = "";
       
       if(itemList.size() == 0){
    	   throw new DHBizException(ExceptionConstant.ERR_DH020008);
       }
       for(Item item : itemList){
      	   numIids += "," + item.getNumIid();
       }
       
       /**2.根据拿到的num_iid获得所有所有item（包含sku）,得到所有的商品信息集合*/
		ItemsSellerListGetResponse response;
		String []allNum = numIids.split(",");
		for(int i=0 ; i < numIids.split(",").length ; ){
			try{
		   	    ItemsSellerListGetRequest  req=new ItemsSellerListGetRequest ();
		        req.setFields("barcode,num_iid,pic_url,detail_url,title,props,price,modified,outer_id,props_name,sku,approve_status,prop_img");
		        StringBuffer numS = new StringBuffer();
		        for(int j=0 ; j<20; j++){
		        	if(i+j == numIids.split(",").length) break;
		        	numS.append(","+allNum[i+j]);
		        }
		        req.setNumIids(numS.substring(1 , numS.length()));
		    	response = client.execute(req,sessionKey);
			}catch (Exception e) {
				throw new DHBizException(ExceptionConstant.ERR_DH020009);
			}
		    	
	    	if(("27").equals(response.getErrorCode()) || ("44").equals(response.getErrorCode())){
	    		throw new DHBizException(ExceptionConstant.ERR_DH000002);
	        }
	    	 /**3.解析数据转换为本地对象集合*/
			List<Item> itemList2 = response.getItems();
	        if(itemList.size() == 0){
	        	throw new DHBizException(ExceptionConstant.ERR_DH020008);
	        }
			
	        try{
		        for(Item item : itemList2){
		        	DownGoodsVo goodsItem = new DownGoodsVo();
		        	goodsItem.setPlatType(goodsParamVo.getPlatType());
		        	goodsItem.setNumIid(item.getNumIid().toString());
		        	goodsItem.setOuterId(item.getOuterId());
		        	goodsItem.setTitle(item.getTitle());
		        	goodsItem.setPrice(new BigDecimal(item.getPrice()));
		        	goodsItem.setShopTitle(goodsParamVo.getShopTitle());
		        	goodsItem.setShopNick(goodsParamVo.getShopNick());
		        	goodsItem.setShopId(goodsParamVo.getShopId());
		        	goodsItem.setPicUrl(item.getPicUrl());
		        	goodsItem.setDetailUrl(item.getDetailUrl());
		        	goodsItem.setBarcode(item.getBarcode());
		        	goodsItem.setStandWeight(new BigDecimal(item.getItemWeight()==null?"0.00":item.getItemWeight()));
		        	if(("onsale").equals(item.getApproveStatus())){
		        		goodsItem.setStatus("1");
		        	}else{
		        		goodsItem.setStatus("0");
		        	}
		        	//从属性中取得货号
		        	String propsName = item.getPropsName()+";";
		        	if(propsName.indexOf("货号") != -1){
		        		goodsItem.setArtNo(propsName.substring(propsName.indexOf("货号")+3, propsName.indexOf(";" ,propsName.indexOf("货号"))));
		        	}
		        	
		        	List<Sku> skuList = item.getSkus();
		        	List<PropImg> propImgList = item.getPropImgs();
		        	List<DownGoodsSkuVo> localSkuList = new ArrayList<DownGoodsSkuVo>();
		        	if(skuList != null){
		        		for(Sku sku : skuList){
		        			DownGoodsSkuVo goodsSku = new DownGoodsSkuVo();
		        			goodsSku.setSkuId(sku.getSkuId().toString());
		        			//goodsSku.setSpec(sku.getPropertiesName());
		        			String properNam = sku.getPropertiesName();
			           		String proper[] = sku.getProperties().split(";");
			           		if(("").equals(properNam)){
			           			goodsSku.setSpec("");
			           		}else{
				           		for(String p1 : proper){
				           			properNam = properNam.replaceAll(p1+":", "");
				           		}
				           		goodsSku.setSpec(properNam);
			           		}
		        			
		        			goodsSku.setOuterSkuId(sku.getOuterId());
		        			goodsSku.setPrice(new BigDecimal(sku.getPrice()));
		        			goodsSku.setPlatType(goodsParamVo.getPlatType());
		        			goodsSku.setNumIid(item.getNumIid().toString());
		        			String propImgUrl = "";
		        			if(propImgList != null){
			        			for(PropImg propImg : propImgList){
			        				String properties[] = sku.getProperties().split(";");
			        				for(String propertie : properties){
			        					if(propertie.indexOf("1627207") !=  -1){
			        						propImgUrl = propImg.getUrl();
			        					}
			        				}
			        			}
		        			}
		        			goodsSku.setPropUrl(propImgUrl);
		        			goodsSku.setBarcode(sku.getBarcode());
		        			localSkuList.add(goodsSku);
		        		}
		        		goodsItem.setSkuList(localSkuList);
		        		goodsItem.setSpecNum(skuList==null ? 0:skuList.size());
		        	}
		        	allDownGoodsList.add(goodsItem);
		        }
	        }catch(Exception e){
	        	throw new DHBizException(ExceptionConstant.ERR_DH020010);
	        }
	        i = i + 20;
	   }
		return allDownGoodsList;
	}
	
	private List<DownGoodsVo> downGoodsForWSC(DownGoodsParamVo goodsParamVo ) throws Exception{

		List<DownGoodsVo> allDownGoodsList = new ArrayList<DownGoodsVo>();
		
		List<resultProduct> itemList = new ArrayList<resultProduct>(); //微商城商品实体
		
		boolean hashNext = true;
		Long pageNo = 0L;
	
		while(hashNext){
			
			//Calendar c = Calendar.getInstance(); 
	       // c.add(Calendar.MONTH, -1);
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");  
		
			Calendar theCa = Calendar.getInstance();
			 theCa.setTime(new Date());
			 theCa.add(theCa.DATE, -30);
			 Date startD = theCa.getTime();
			 
	       
			resultEntityProduct onGoodsResponse = WSCUtil.searchGoods(goodsParamVo.getShopNick(),startD,new Date(),pageNo++);
	       
	        if(onGoodsResponse.getEntity() != null){
	        	itemList.addAll(onGoodsResponse.getEntity());
	        }else{
	        	hashNext = false;
	        }
	        if(hashNext){
		        if(onGoodsResponse.getEntity().size() < 20L){
		        	hashNext = false;
		        }
	        }
	       
		}
		
		 for(resultProduct item : itemList){
			 DownGoodsVo goodsItem = new DownGoodsVo();
			 goodsItem.setPlatType(goodsParamVo.getPlatType());
	         goodsItem.setNumIid(item.getGdsNo());
	         goodsItem.setOuterId(item.getGdsNo());
	        
	         goodsItem.setTitle(item.getGdsName());
	        	goodsItem.setPrice(item.getPrice());
	        	goodsItem.setShopTitle(goodsParamVo.getShopTitle());	
	        	goodsItem.setShopNick(goodsParamVo.getShopNick());
	        	goodsItem.setShopId(goodsParamVo.getShopId());
	        	goodsItem.setPicUrl(item.getIMG_PATH());
	        	goodsItem.setDetailUrl(item.getIMG_PATH());
	        	goodsItem.setBarcode(item.getGdsPact());
	        	goodsItem.setStandWeight(item.getStandWeight());
	        	goodsItem.setStatus("1");
	        	
	        	//从属性中取得货号
	        	//String propsName = item.getPropsName()+";";
	        	//if(propsName.indexOf("货号") != -1){
	        	//	goodsItem.setArtNo(propsName.substring(propsName.indexOf("货号")+3, propsName.indexOf(";" ,propsName.indexOf("货号"))));
	        	//}
	        	Gson gson = new Gson();
	        	tableStruct retobjects = gson.fromJson(item.getGdsFormat(), tableStruct.class);
	    		
	        	List<rowStruct> skuList = retobjects.getRow();
	        	List<DownGoodsSkuVo> localSkuList = new ArrayList<DownGoodsSkuVo>();
	        	if(skuList != null){
	        		for(rowStruct sku : skuList){
	        			DownGoodsSkuVo goodsSku = new DownGoodsSkuVo();
	        			goodsSku.setSkuId(null ==sku.getSku()?"":sku.getSku());
	        	  		goodsSku.setOuterSkuId(null ==sku.getSku()?"":sku.getSku());
	        			goodsSku.setPrice(sku.getPrice());
	        			goodsSku.setPlatType(goodsParamVo.getPlatType());
	        			goodsSku.setNumIid(item.getGdsNo());
	        			List<attrStruct> attrStructList =  sku.getColumn();
	        			String Spec = "";
	        			for( attrStruct attr:attrStructList){
	        				Spec += attr.getAttr() + ":" + attr.getValue() + " ";
	        			}
	        			goodsSku.setSpec(Spec);
	        			goodsSku.setPropUrl(item.getIMG_PATH());
	        			goodsSku.setBarcode(item.getGdsNo() + sku.getSku());
	        			localSkuList.add(goodsSku);
	        		}
	        		goodsItem.setSkuList(localSkuList);
	        		goodsItem.setSpecNum(skuList==null ? 0:skuList.size());
	        	}
	        goodsItem.setArtNo(item.getGdsNo());
			 allDownGoodsList.add(goodsItem);
		 }
		
		
		
		return allDownGoodsList;
	}
	
	public CommResponse addGdsInfoForPlat(GdsInfoVo gdsInfoVo , Shop shop ,DownGoodsVo downGoodsVo ,DownGoodsParamVo goodsParamVo) throws Exception {
	
	
		
		CommResponse commResponse = new CommResponse();
		String gdsNoSkuOuterId = "";
		if(gdsInfoVo.getGdsNo() != null && !"".equals(gdsInfoVo.getGdsNo())){
			gdsNoSkuOuterId += gdsInfoVo.getGdsNo();
		}
		if(gdsInfoVo.getSkuOuterId() != null && !"".equals(gdsInfoVo.getSkuOuterId())){
			gdsNoSkuOuterId += gdsInfoVo.getSkuOuterId();
		}
		
		List<GdsInfo> gdsInfoList = gdsDao.queryGdsInfoByGdsNoSkuOuterId(gdsNoSkuOuterId);
		
		if( null ==gdsInfoList || gdsInfoList.size()==0){
			gdsInfoVo.setGdsNo(downGoodsVo.getOuterId());
			gdsInfoVo.setGdsName(downGoodsVo.getTitle());
			gdsInfoVo.setClsId(goodsParamVo.getClsId());
			gdsInfoVo.setCal(goodsParamVo.getCal());
			gdsInfoVo.setStandWeight(downGoodsVo.getStandWeight());
			gdsInfoVo.setGdsStatus("1");
			gdsInfoVo.setGdsLowAmount(new BigDecimal("0"));
			gdsInfoVo.setGdsHighAmount(new BigDecimal("0"));
			String picUrl[] = downGoodsVo.getPicUrl().split(";");
			gdsInfoVo.setImgPath(picUrl[0]);
			gdsInfoVo.setArtNo(downGoodsVo.getArtNo());
			gdsInfoVo.setGdsFlag("1");
			gdsDao.insertSelective(gdsInfoVo);
			
			WarehousePositionsVo warehousePositionsVo = new WarehousePositionsVo();
			warehousePositionsVo.setDhWarehousePositions(Integer.parseInt(shop.getWarehouseId()+""));
			WarehousePositions warehousePositions = warehousePositionsDao.queryDefaultPosition(warehousePositionsVo);
			
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setGdsId(gdsInfoVo.getId());
			locStockVo.setStockId(shop.getWarehouseId());
			locStockVo.setLocId(warehousePositions.getId());
			locStockVo.setMaxQty(10);
			locStockVo.setMinQty(0);
			locStockVo.setSafeQty(0L);
			locStockVo.setTotalQty(0L);
			locStockVo.setLocked(false);
			locStockVo.setIsDeleted(false);
			locStockVo.setOrderQty(0L);
			locStockVo.setTimestamp(new Date());
			locStockVo.setCreateDate(new Date());
			locStockVo.setModifyDate(new Date());
			locStockDao.insertSelective(locStockVo);
		}else{
			commResponse.setErrCode("999999");
		}
		return commResponse;
	}

	/**
	  * <p>Title: selectGdsInfoGridListNoPage</p>
	  * <p>Description: 根据条件查询符合要求的商品</p>
	  * @param goodsParamVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.goods.GdsInfoService#selectGdsInfoGridListNoPage(com.dinghao.entity.vo.template.business.goods.DownGoodsParamVo)
	 */
	public CommResponse selectGdsInfoGridListNoPage(GdsInfoVo gdsInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		GdsInfoVo queryGds = new GdsInfoVo();
		if(null != gdsInfoVo.getGdsPact() && !"".equals(gdsInfoVo.getGdsPact())){
			queryGds.setGdsPact(gdsInfoVo.getGdsPact());
		}
		commResponse.setData(gdsDao.selectGdsInfoGridListNoPage(queryGds));
		return commResponse;
	}
}
