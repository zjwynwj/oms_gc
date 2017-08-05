package com.dinghao.dao.template.receipt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.receipt.ReceiptDetail;
import com.dinghao.entity.vo.template.receipt.ReceiptDetailVo;

@Repository
public interface ReceiptDetailDao {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(ReceiptDetailVo record);

	ReceiptDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ReceiptDetailVo record);

	/**
	 * 
	 * @Title: getReceiptDetails
	 * @Description: TODO(依据条件获取入库单明细详细)
	 * @param @param record
	 * @param @return 设定文件
	 * @return List<ReceiptDetail> 返回类型
	 * @throws
	 */
	List<ReceiptDetail> getReceiptDetails(ReceiptDetailVo record);

	int getReceiptDetailsCount(ReceiptDetailVo record);

	void deleteByReceiptId(Map map);

}