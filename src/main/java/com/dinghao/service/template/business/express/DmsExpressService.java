package com.dinghao.service.template.business.express;

import java.util.List;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;

public interface DmsExpressService {

	public CommResponse downDmsExpressInfo(DmsExpressVo expressVo) throws Exception;
	
	public CommResponse modDmsExpressInfo(DmsExpressVo expressVo) throws Exception;
	
	public CommResponse queryDmsExpressInfo(DmsExpressVo expressVo) throws Exception;
	
	public CommResponse findDmsExpressInfoForGrid(DmsExpressVo expressVo) throws Exception;
	
	public  List<DmsExpress> getAllDmsExpress(DmsExpressVo expressVo) throws Exception;
}
