package com.dinghao.service.template.receipt.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.receipt.ReceiptDao;
import com.dinghao.dao.template.receipt.ReceiptDetailDao;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.receipt.Receipt;
import com.dinghao.entity.template.receipt.ReceiptDetail;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.locstock.LocStockVo;
import com.dinghao.entity.vo.template.receipt.ReceiptDetailVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;
import com.dinghao.service.template.locstock.LocStockService;
import com.dinghao.service.template.receipt.ReceiptService;

@Service
@Transactional
public class ReceiptServiceImp implements ReceiptService {
	@Autowired
	ReceiptDao receiptDao;
	@Autowired
	ReceiptDetailDao receiptDetailDao;
	@Autowired
	LocStockService locStockService;

	@Override
	public JqGridVo<Receipt> getReceipts(ReceiptVo receiptVo,
			TemplateAdmin admin) {
		JqGridVo<Receipt> jqGridVo = new JqGridVo<Receipt>();
		//receiptVo.setCreateBy(admin.getId());
		jqGridVo.setList(receiptDao.getReceipts(receiptVo));
		jqGridVo.setRecords(receiptDao.getReceiptsCount(receiptVo));
		jqGridVo.setPageNum(receiptVo.getPageNum());
		jqGridVo.setRows(receiptVo.getRows());
		return jqGridVo;
	}

	@Override
	public Receipt selectByPrimaryKey(Integer id) {
		return receiptDao.selectByPrimaryKey(id);
	}

	/**
	 * <p>
	 * Title: 获取入库单详细内容
	 * </p>
	 * <p>
	 * Description:获取入库单详细内容
	 * </p>
	 * *
	 * 
	 * @Title: getReceiptDetails
	 * @Description: TODO(获取入库单详细内容)
	 * @param @param receiptDetailVo
	 * @param @return 设定文件
	 * @return JqGridVo<ReceiptDetail> 返回类型
	 * @throws
	 */
	@Override
	public CommResponse getReceiptDetails(ReceiptDetailVo receiptDetailVo, TemplateAdmin admin) {
		CommResponse commResponse = new CommResponse();
		
		List<ReceiptDetail> detailList = receiptDetailDao.getReceiptDetails(receiptDetailVo);
		
		for(ReceiptDetail detail : detailList){ 	
			if(null != detail.getGdsInfoFormat() && !"".equals(detail.getGdsInfoFormat())){
				String attbName[] = detail.getGdsInfoFormat().split(";");
				String attbs ="";
				for(String attb : attbName){
					attbs += ";"+attb.split(":")[2]+":"+attb.split(":")[3];
				}
				attbs = attbs.substring(1, attbs.length());
				detail.setGdsInfoFormat(attbs);
			}else{
				detail.setGdsInfoFormat("");
			}
		
		}
		
		commResponse.setData(detailList);
		commResponse.setSuccess(true);
		/*
		jqGridVo.setList(detailList);
		jqGridVo.setRecords(receiptDetailDao
				.getReceiptDetailsCount(receiptDetailVo));
		jqGridVo.setPageNum(receiptDetailVo.getPageNum());
		jqGridVo.setRows(receiptDetailVo.getRows());
		*/
		return commResponse;
	}

	@Override
	public int updateReceiptDetail(ReceiptDetailVo receiptDetailVo,
			TemplateAdmin admin) {
		receiptDetailVo.setModifyBy(admin.getId());
		return receiptDetailDao.updateByPrimaryKeySelective(receiptDetailVo);
	}

	@Override
	public int updateReceipt(ReceiptVo receiptVo) {
		receiptDao.updateByPrimaryKeySelective(receiptVo);
		return receiptVo.getId();
	}

	public CommResponse  save_receipt(ReceiptVo receiptVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		List<ReceiptDetailVo> receiptDetailVoList = receiptVo.getReceiptDetailVos();
		

		Integer id = 0;
		// 入库单明细更新
		if (receiptVo.getId() != null) {
			id = this.updateReceipt(receiptVo);
		} else {
			id = this.insertReceipt(receiptVo);
		}
		
		for (ReceiptDetailVo receiptDetailVo : receiptDetailVoList ) {
			// 入库单明细更新
			receiptDetailVo.setReceiptId(id);
			this.insertReceiptDetail(receiptDetailVo,receiptVo.getWarehouseId(),receiptVo.getReceiptType());
		}

	
		return commResponse;
	}
	
	@Override
	public int insertReceiptDetail(ReceiptDetailVo receiptDetailVo, Integer warehouseId, Integer receiptType) {
	//	receiptDetailVo.setCreateBy(admin.getId());
		receiptDetailVo.setCreateDate(new Date());
		// 更新现货表
		LocStockVo locStockVo = new LocStockVo();
		locStockVo.setGdsId(receiptDetailVo.getGdsInfoId());
		locStockVo.setStockId(warehouseId.longValue());
		locStockVo.setLocId(receiptDetailVo.getWarehousePositionsId().longValue());
		locStockVo.setTotalQty(receiptDetailVo.getAmount() == null ? 0l
				: receiptDetailVo.getAmount().longValue());
		locStockVo.setLocked(false);
		
		locStockVo.setIsDeleted(true);
		// 单类别 1 入库单 2 出库单
		if (receiptType == 1) {
			locStockVo.setInAmount(new BigDecimal(0));  //入库金额 to do
			locStockService.saveLocStock(locStockVo, 1);
		} else {
			locStockVo.setOutAmount(new BigDecimal(0));  //出库金额 to do
			locStockService.saveLocStock(locStockVo, 2);
		}
		return receiptDetailDao.insertSelective(receiptDetailVo);
	}

	/**
	 * 保存入库单信息
	 */
	@Override
	public int insertReceipt(ReceiptVo receiptVo) {
		//receiptVo.setCreateBy(admin.getId());
		receiptDao.insertSelective(receiptVo);
		return receiptVo.getId();
	}

	@Override
	public void deleteReceiptDetails(Integer id, TemplateAdmin admin) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("adminId", admin.getId());
		receiptDetailDao.deleteByReceiptId(map);
	}
}
