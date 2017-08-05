package com.dinghao.service.template.receipt;

import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.receipt.Receipt;
import com.dinghao.entity.template.receipt.ReceiptDetail;
import com.dinghao.entity.vo.template.JqGridVo;
import com.dinghao.entity.vo.template.receipt.ReceiptDetailVo;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;

public interface ReceiptService {
	/**
	 * @param templateAdmin
	 * 
	 * @Title: getWarehouse
	 * @Description: TODO(获取入库单信息)
	 * @param @param receiptVo
	 * @param @return 设定文件
	 * @return JqGridVo<Receipt> 返回类型
	 * @throws
	 */
	public JqGridVo<Receipt> getReceipts(ReceiptVo receiptVo,
			TemplateAdmin templateAdmin);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(依据主键获取receipt对象)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Receipt 返回类型
	 * @throws
	 */
	public Receipt selectByPrimaryKey(Integer id);

	/**
	 * @param templateAdmin
	 * 
	 * @Title: getReceiptDetails
	 * @Description: TODO(获取入库单详细内容)
	 * @param @param receiptDetailVo
	 * @param @return 设定文件
	 * @return JqGridVo<ReceiptDetail> 返回类型
	 * @throws
	 */
	public CommResponse getReceiptDetails(ReceiptDetailVo receiptDetailVo, TemplateAdmin templateAdmin);

	/**
	 * 
	 * @Title: updateReceiptDetail
	 * @Description: TODO(保存修改入库单明细)
	 * @param @param receiptDetailVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public int updateReceiptDetail(ReceiptDetailVo receiptDetailVo,
			TemplateAdmin admin);

	public int updateReceipt(ReceiptVo receiptVo);
	
	public CommResponse  save_receipt(ReceiptVo receiptVo) throws Exception ;
	
	/**
	 * 
	 * @Title: insertReceiptDetail
	 * @Description: TODO(新增入库单明细信息)
	 * @param @param receiptDetailVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public int insertReceiptDetail(ReceiptDetailVo receiptDetailVo,Integer warehouseId,Integer receiptType);

	public int insertReceipt(ReceiptVo receiptVo);

	/**
	 * 删除入库单明细数据
	 * 
	 * @param id
	 * @param admin
	 */
	public void deleteReceiptDetails(Integer id, TemplateAdmin admin);
}
