package com.dinghao.service.template.business.purchase.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.purchase.PurOrderDao;
import com.dinghao.dao.template.business.purchase.PurOrderDetailDao;
import com.dinghao.dao.template.business.purchase.PurSuggestGoodsDao;
import com.dinghao.dao.template.receipt.ReceiptDao;
import com.dinghao.dao.template.receipt.ReceiptDetailDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.purchase.PurOrder;
import com.dinghao.entity.template.business.purchase.PurOrderDetail;
import com.dinghao.entity.template.business.purchase.PurSuggestGoods;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.goods.GdsInfoVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDataVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderDetailVo;
import com.dinghao.entity.vo.template.business.purchase.PurOrderVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.receipt.ReceiptDetailVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.purchase.PurOrderService;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.util.StringUtil;

/**
  * @ClassName: PurOrderServiceImpl
  * @Description: TODO  采购订单管理service实现
  * @author helong 
  * @date 2016年1月7日 上午11:17:40
  * @version V1.0
  *
 */
@Service
@Transactional
public class PurOrderServiceImpl implements PurOrderService{
	@Autowired
	private PurOrderDao purOrderDao;
	@Autowired
	private PurOrderDetailDao purOrderDetailDao;
	@Autowired
	ReceiptDao receiptDao;
	@Autowired
	ReceiptDetailDao receiptDetailDao;
	@Autowired
	LocStockService locStockService;
	@Autowired
	private PurSuggestGoodsDao purSuggestGoodsDao;
	/**
	  * <p>Title: addPurOrder</p>
	  * <p>Description: 添加采购订单</p>
	  * @param purOrderDataVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#addPurOrder(com.dinghao.entity.vo.template.business.purchase.PurOrderDataVo)
	 */
	public CommResponse addPurOrder(PurOrderDataVo purOrderDataVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PurOrderVo purOrderVo = purOrderDataVo.getPurOrderVo();
		List<PurOrderDetailVo> detailList = purOrderDataVo.getPurOrderDetailVoList();
		int count = purOrderDao.selectPurOrderCountByPurNo(purOrderDataVo.getPurOrderVo().getPurNo());
		if(count > 0){
			throw new DHBizException(ExceptionConstant.ERR_DH030001);
		}
		purOrderVo.setPurImStatus("1");
		purOrderVo.setPurOrderStatus("1");
		purOrderVo.setPurPayStatus("1");
		purOrderVo.setTotalPayAmt(new BigDecimal("0"));
		purOrderDao.insertSelective(purOrderVo);
		for(PurOrderDetailVo purOrderDetailVo : detailList){
			purOrderDetailVo.setPurOrderId(purOrderVo.getId());
			purOrderDetailVo.setPurInMoney(new BigDecimal("0"));
			purOrderDetailVo.setPurOutAmount(new BigDecimal("0"));
			purOrderDetailVo.setPurOutMoney(new BigDecimal("0"));
			purOrderDetailVo.setPurRealAmount(new BigDecimal("0"));
			purOrderDetailVo.setPurRealMoney(new BigDecimal("0"));
			purOrderDetailDao.insertSelective(purOrderDetailVo);
		}
		return commResponse;
	}
	
	public CommResponse getDetailListBySugIds(String suggestGoodsids) throws Exception{
		CommResponse commResponse = new CommResponse();
		List<PurOrderDetail> detailList = new ArrayList<PurOrderDetail>();
		 if(!StringUtil.isEmpty(suggestGoodsids)){
				String idArray[] = suggestGoodsids.split(",");
				for(String id : idArray){
					PurSuggestGoods purSuggestGoods= purSuggestGoodsDao.selectByPrimaryKey(Long.parseLong(id));
					PurOrderDetail purOrderDetail = new PurOrderDetail();
					purOrderDetail.setGdsId(purSuggestGoods.getGdsId());
					purOrderDetail.setGdsShowInfo(purSuggestGoods.getGdsShowInfo());
					purOrderDetail.setPurAmount(purSuggestGoods.getNum());
					purOrderDetail.setPurPrice(purSuggestGoods.getPrice());
					purOrderDetail.setPurMoney(purSuggestGoods.getNum().multiply(purSuggestGoods.getPrice()));
					
					
					if(null != purSuggestGoods.getAttbs() && !"".equals(purSuggestGoods.getAttbs())){
						String attbName[] = purSuggestGoods.getAttbs().split(";");
						String attbs ="";
						for(String attb : attbName){
							attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
						}
						attbs = attbs.substring(1, attbs.length());
						purSuggestGoods.setAttbs(attbs);
					}else{
						purSuggestGoods.setAttbs("");
					}
					
					purOrderDetail.setAttbs(purSuggestGoods.getAttbs());
					
					detailList.add(purOrderDetail);
				}
			}
		commResponse.setData(detailList);
		commResponse.setSuccess(true);
		return commResponse;
		
	}
	/**
	  * <p>Title: modPurOrder</p>
	  * <p>Description: 修改采购订单</p>
	  * @param purOrderDataVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#modPurOrder(com.dinghao.entity.vo.template.business.purchase.PurOrderDataVo)
	 */
	public CommResponse modPurOrder(PurOrderDataVo purOrderDataVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PurOrderVo purOrderVo = purOrderDataVo.getPurOrderVo();
		List<PurOrderDetailVo> detailList = purOrderDataVo.getPurOrderDetailVoList();
		
		PurOrder purOrder = purOrderDao.selectByPrimaryKey(purOrderVo.getId());
		if(purOrder == null){
			throw new DHBizException(ExceptionConstant.ERR_DH030002);
		}
		if(!"1".equals(purOrder.getPurOrderStatus())){
			throw new DHBizException("订单"+purOrder.getPurNo()+"不为新增状态,不支持修改操作!");
		}
		
		purOrderDao.updateByPrimaryKeySelective(purOrderVo);
		purOrderDetailDao.deleteByPrimaryByPurOrderId(purOrderVo.getId());
		for(PurOrderDetailVo purOrderDetailVo : detailList){
			purOrderDetailVo.setPurOrderId(purOrderVo.getId());
			purOrderDetailDao.insertSelective(purOrderDetailVo);
		}
		return commResponse;
	}

	/**
	  * <p>Title: queryPurOrderById</p>
	  * <p>Description: 根据id查询采购主表信息</p>
	  * @param purOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#queryPurOrderById(com.dinghao.entity.vo.template.business.purchase.PurOrderVo)
	 */
	public CommResponse queryPurOrderById(PurOrderVo purOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PurOrder purOrder = purOrderDao.selectByPrimaryKey(purOrderVo.getId());
		commResponse.setData(purOrder);
		return commResponse;
	}

	/**
	  * <p>Title: findPurOrderForGrid</p>
	  * <p>Description: 分页查询采购订单信息</p>
	  * @param purOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#findPurOrderForGrid(com.dinghao.entity.vo.template.business.purchase.PurOrderVo)
	 */
	public CommResponse findPurOrderForGrid(PurOrderVo purOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<PurOrder> pageVo = new PageVo<PurOrder>();
		pageVo.setRows(purOrderVo.getRows());
		pageVo.setList(purOrderDao.selectPurOrderGridListPage(purOrderVo));
		pageVo.setCount(purOrderDao.selectPurOrderGridListCount(purOrderVo));
		commResponse.setData(pageVo);
		return commResponse;
	}

	/**
	  * <p>Title: findPurOrderDetailListByPurOrderId</p>
	  * <p>Description: 根据采购订单id查询采购明细列表信息 </p>
	  * @param purOrderVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#findPurOrderDetailListByPurOrderId(com.dinghao.entity.vo.template.business.purchase.PurOrderVo)
	 */
	public CommResponse findPurOrderDetailListByPurOrderId(PurOrderVo purOrderVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		List<PurOrderDetail> detailList = purOrderDetailDao.findPurOrderDetailListByPurOrderId(purOrderVo.getId());
		for(PurOrderDetail detail : detailList){
			if(null != detail.getAttbs() && !"".equals(detail.getAttbs())){
				String attbName[] = detail.getAttbs().split(";");
				String attbs ="";
				for(String attb : attbName){
					attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
				}
				attbs = attbs.substring(1, attbs.length());
				detail.setAttbs(attbs);
			}else{
				detail.setAttbs("");
			}
		}
		commResponse.setData(detailList);
		return commResponse;
	}

	/**
	  * <p>Title: savePurOrderReceiptOrOutBound</p>
	  * <p>Description: 生成入库或出库单</p>
	  * @param receiptVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.purchase.PurOrderService#savePurOrderReceiptOrOutBound(com.dinghao.entity.vo.template.receipt.ReceiptVo)
	 */
	public CommResponse savePurOrderReceiptOrOutBound(ReceiptVo receiptVo ) throws Exception {
		PurOrder purOrder = purOrderDao.selectByPrimaryKey(receiptVo.getPurOrderId());
		if(purOrder == null){
			throw new DHBizException(ExceptionConstant.ERR_DH030002);
		}
		if("3".equals(purOrder.getPurImStatus())){
			throw new DHBizException("采购订单:"+purOrder.getPurNo()+"已入库完毕，不能进行出入库操作!");
		}
		if("3".equals(purOrder.getPurOrderStatus())){
			throw new DHBizException("采购订单:"+purOrder.getPurNo()+"已结束，不能进行出入库操作!");
		}
		
		CommResponse commResponse = new CommResponse();
		receiptDao.insertSelective(receiptVo);
		Integer receiptId = receiptVo.getId();
		boolean purOrderEnd = true;
		
		for(ReceiptDetailVo detailVo : receiptVo.getReceiptDetailVos()){
			detailVo.setReceiptId(receiptId);
			
//			detailVo.setCreateBy(admin.getId());
			detailVo.setCreateDate(new Date());
			// 更新现货表
			LocStockVo locStockVo = new LocStockVo();
			locStockVo.setGdsId(detailVo.getGdsInfoId());
			locStockVo.setStockId(receiptVo.getWarehouseId().longValue());
			locStockVo.setLocId(detailVo.getWarehousePositionsId().longValue());
			locStockVo.setTotalQty(detailVo.getAmount() == null ? 0l	: detailVo.getAmount().longValue());
			locStockVo.setLocked(false);
			locStockVo.setIsDeleted(true);
			
			PurOrderDetailVo purOrderDetailVo = new PurOrderDetailVo();
			purOrderDetailVo.setId(detailVo.getPutOrderDetailId());
			PurOrderDetail purOrderDetail = purOrderDetailDao.selectByPrimaryKey(detailVo.getPutOrderDetailId());
			// 单类别 1 入库单 2 出库单
			if (receiptVo.getReceiptType() == 1) {
				
				BigDecimal purInAmount = purOrderDetail.getPurInAmount().add(new BigDecimal(detailVo.getAmount()));
				BigDecimal purRealAmount = purOrderDetail.getPurRealAmount().add(new BigDecimal(detailVo.getAmount()));
				purOrderDetailVo.setPurInAmount(purInAmount);
				purOrderDetailVo.setPurInMoney(purInAmount.multiply(purOrderDetail.getPurPrice()));
				purOrderDetailVo.setPurRealAmount(purRealAmount);
				purOrderDetailVo.setPurRealMoney(purRealAmount.multiply(purOrderDetail.getPurPrice()));
				
				//调整入库金额
				BigDecimal inAmount =  purOrderDetail.getPurPrice().multiply(new BigDecimal(detailVo.getAmount()));
				locStockVo.setInAmount(inAmount);
				
				locStockService.saveLocStock(locStockVo, 1);
			} else {
			
				BigDecimal purOutAmount = purOrderDetail.getPurOutAmount().add(new BigDecimal(detailVo.getAmount()));
				BigDecimal purRealAmount = purOrderDetail.getPurRealAmount().subtract(new BigDecimal(detailVo.getAmount()));
				purOrderDetailVo.setPurOutAmount(purOutAmount);
				purOrderDetailVo.setPurOutAmount(purOutAmount.multiply(purOrderDetail.getPurPrice()));
				purOrderDetailVo.setPurRealAmount(purRealAmount);
				purOrderDetailVo.setPurRealMoney(purRealAmount.multiply(purOrderDetail.getPurPrice()));
				
				//调出库金额
				BigDecimal outAmount =   purOrderDetail.getPurPrice().multiply(new BigDecimal(detailVo.getAmount()));
				locStockVo.setOutAmount(outAmount);
				locStockService.saveLocStock(locStockVo, 2);
				
			}
			if(purOrderDetail.getPurRealAmount().compareTo(purOrderDetail.getPurAmount()) == -1){
				purOrderEnd = false;
			}
			if(purOrderDetail.getPurRealAmount().compareTo(purOrderDetail.getPurAmount()) == 1){
				throw new DHBizException("采购数据已变更,请刷新页面数据!");
			}
			receiptDetailDao.insertSelective(detailVo);
			purOrderDetailDao.updateByPrimaryKeySelective(purOrderDetailVo);
		}
		PurOrderVo purOrderVo = new PurOrderVo();
		purOrderVo.setId(receiptVo.getPurOrderId());
		if(purOrderEnd){
			purOrderVo.setPurOrderStatus("3");
		}else{
			purOrderVo.setPurOrderStatus("2");
		}
		
		purOrderDao.updateByPrimaryKeySelective(purOrderVo);
		return commResponse;
	}
}
