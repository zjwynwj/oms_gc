package com.dinghao.service.template.business.express;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.express.DmsExpressareaVo;
import com.dinghao.entity.vo.template.business.express.ExpressareaVoList;

public interface DmsExpressareaService {
	
	public CommResponse queryIsArriveAppointArea(DmsExpressareaVo expressareaVo) throws Exception;

	public CommResponse findDmsExpressareaInfoForGrid(DmsExpressareaVo expressareaVo) throws Exception;
	
	public CommResponse findDmsExpressareaInfoList(DmsExpressareaVo expressareaVo) throws Exception;
	
	public CommResponse modDmsExpressareaInfo(ExpressareaVoList expressareaVoList) throws Exception;
	
	public CommResponse modDmsExpressareaDelivery(DmsExpressareaVo expressareaVo) throws Exception;
}
