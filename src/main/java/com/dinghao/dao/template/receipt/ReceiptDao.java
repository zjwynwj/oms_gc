package com.dinghao.dao.template.receipt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.receipt.Receipt;
import com.dinghao.entity.vo.template.receipt.ReceiptVo;

@Repository
public interface ReceiptDao {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(ReceiptVo receiptVo);

	Receipt selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ReceiptVo receiptVo);

	/**
	 * 
	 * @Title: getReceipts
	 * @Description: TODO(依据条件获取入库单相关数据)
	 * @param @param receiptVo
	 * @param @return 设定文件
	 * @return List<Receipt> 返回类型
	 * @throws
	 */
	List<Receipt> getReceipts(ReceiptVo receiptVo);

	/**
	 * 
	 * @Title: getReceiptsCount
	 * @Description: TODO(依据条件统计入库单相关数据个数)
	 * @param @param receiptVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	int getReceiptsCount(ReceiptVo receiptVo);
}