package com.dinghao.service.template.business.wmswave.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.order.SalesOrderDao;
import com.dinghao.dao.template.business.order.SalesOrderitemDao;
import com.dinghao.dao.template.business.wmswave.WmsWaveDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.order.SalesOrder;
import com.dinghao.entity.template.business.wmswave.WmsWave;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderVo;
import com.dinghao.entity.vo.template.business.order.SalesOrderitemVo;
import com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.order.impl.SalesOrderServiceImpl;
import com.dinghao.service.template.business.wmswave.IWmsWaveService;

@Service
@Transactional
public class WmsWaveServiceImpl implements IWmsWaveService{

	@Autowired
	private WmsWaveDao wmsWaveDao;
	@Autowired
	private SalesOrderDao salesOrderDao;
	@Autowired
	private SalesOrderitemDao salesOrderitemDao;
	/** 日志记录  */
	private static final Log logger = LogFactory.getLog(SalesOrderServiceImpl.class);
	/**
	  * <p>Title: generateWaveNo</p>
	  * <p>Description: 生成拣货单</p>
	  * @param waveVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.wmswave.IWmsWaveService#generateWaveNo(com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo)
	 */
	public CommResponse generateWaveNo(WmsWaveVo waveVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		String orderIds[] = waveVo.getOrderIds().split(",");
		WmsWaveVo newWmsWave = new WmsWaveVo();

		newWmsWave.setWaveNo(waveVo.getWaveNo());
		newWmsWave.setIspickprint(new Short("0"));
		newWmsWave.setIsscaned(new Short("0"));
		newWmsWave.setCreateDate(waveVo.getCreateDate());
		wmsWaveDao.insertSelective(newWmsWave);
		int i=1;
		for(String id : orderIds){
			SalesOrderVo salesOrder = salesOrderDao.selectByPrimaryKey(Long.parseLong(id));
			if(salesOrder == null){
				logger.error("订单id："+id+"没查询到");
				throw new DHBizException("系统异常请联系管理员!");
			}
			if(null != salesOrder.getWaveNo() && !"".equals(salesOrder.getWaveNo())){
				throw new DHBizException("订单"+salesOrder.getOrderNum()+"已生成拣货单,不能重复生成!");
			}
			
			SalesOrderVo salesOrderVo = new SalesOrderVo();
			salesOrderVo.setId(Long.parseLong(id));
			salesOrderVo.setWaveId(i++);
			salesOrderVo.setWaveNo(newWmsWave.getWaveNo());
			salesOrderDao.updateByPrimaryKeySelective(salesOrderVo);
		}
		return commResponse;
	}
	
	/**
	  * <p>Title: queryWmsWaveGridList</p>
	  * <p>Description: 查询grid的list数据</p>
	  * @param waveVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.wmswave.IWmsWaveService#queryWmsWaveGridList(com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo)
	 */
	public CommResponse queryWmsWaveGridList(WmsWaveVo waveVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<WmsWave> pageVo = new PageVo<WmsWave>();
		pageVo.setRows(waveVo.getRows());
		pageVo.setList(wmsWaveDao.selectWmsWaveList(waveVo));
		pageVo.setCount(wmsWaveDao.selectWmsWaveCount(waveVo));
		commResponse.setData(pageVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryWmsWaveDetail</p>
	  * <p>Description: 查询拣货单</p>
	  * @param waveVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.wmswave.IWmsWaveService#queryWmsWaveDetail(com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo)
	 */
	public WmsWave queryWmsWaveDetail(WmsWaveVo waveVo) throws Exception {
		WmsWave wave = wmsWaveDao.selectByPrimaryKey(waveVo.getId());
		return wave;
	}

	/**
	  * <p>Title: savePickScanWmsWave</p>
	  * <p>Description: savePickScanWmsWave</p>
	  * @param waveVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.wmswave.IWmsWaveService#savePickScanWmsWave(com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo)
	 */
	public CommResponse savePickScanWmsWave(WmsWaveVo waveVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		WmsWave wave = wmsWaveDao.selectByPrimaryKey(waveVo.getId()); 
		if(wave == null){
			throw new DHBizException("系统不存在此拣货单,请联系管理员!");
		}		
		System.out.println(wave.getIsscaned().toString());
		if("1".equals(wave.getIsscaned().toString())){
			throw new DHBizException("拣货单"+wave.getWaveNo()+"已经分拣完成,请勿重复操作!");
		}
		
		SalesOrderVo queryOrder = new SalesOrderVo();
		queryOrder.setWaveNo(wave.getWaveNo());
		List<SalesOrderVo> orderList = salesOrderDao.selectByParam(queryOrder);
		for(SalesOrderVo order : orderList){
			SalesOrderVo moOrderVo = new SalesOrderVo();
			moOrderVo.setId(order.getId());
			moOrderVo.setHasscaned("1");
			salesOrderDao.updateByPrimaryKeySelective(moOrderVo);
		}
		waveVo.setIsscaned(new Short("1"));
		wmsWaveDao.updateByPrimaryKeySelective(waveVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryWavePrintData</p>
	  * <p>Description: 查询拣货单打印的订单数据</p>
	  * @param waveVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.wmswave.IWmsWaveService#queryWavePrintData(com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo)
	 */
	public CommResponse queryWavePrintData(WmsWaveVo waveVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		SalesOrderVo queryOrder = new SalesOrderVo();
		queryOrder.setWaveNo(waveVo.getWaveNo());
		List<SalesOrderVo> orderList = salesOrderDao.selectByParam(queryOrder);
		for(SalesOrderVo order : orderList){
			SalesOrderitemVo salesOrderitemVo = new SalesOrderitemVo();
			salesOrderitemVo.setOrderId(order.getId() +"");
			order.setItemList(salesOrderitemDao.selectOrderItemList(salesOrderitemVo));
		}
		commResponse.setData(orderList);
		return commResponse;
	}
}
