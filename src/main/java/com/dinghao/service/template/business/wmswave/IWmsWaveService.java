package com.dinghao.service.template.business.wmswave;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.wmswave.WmsWave;
import com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo;

public interface IWmsWaveService {

	/**
	  * @Title: generateWaveNo
	  * @Description: TODO 生成拣货单
	  * @param @param waveVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse generateWaveNo(WmsWaveVo waveVo) throws Exception;
	
	/**
	  * @Title: queryWmsWaveDetail
	  * @Description: TODO   查询拣货单
	  * @param @param waveVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public WmsWave queryWmsWaveDetail(WmsWaveVo waveVo) throws Exception;
	
	/**
	  * @Title: queryWmsWaveGridList
	  * @Description: TODO  查询grid的list数据
	  * @param @param waveVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryWmsWaveGridList(WmsWaveVo waveVo) throws Exception;
	
	/**
	  * @Title: savePickScanWmsWave
	  * @Description: TODO  保存扫描拣货
	  * @param @param waveVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse savePickScanWmsWave(WmsWaveVo waveVo) throws Exception;
	
	/**
	  * @Title: queryWavePrintData
	  * @Description: TODO  查询拣货单打印的订单数据
	  * @param @param waveVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryWavePrintData(WmsWaveVo waveVo) throws Exception;
}
