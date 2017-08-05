package com.dinghao.service.template.business.express;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.express.SenderInfoVo;

public interface SenderInfoService {

	public CommResponse addSenderInfo(SenderInfoVo senderInfoVo) throws Exception;
	
	public CommResponse modSenderInfo(SenderInfoVo senderInfoVo) throws Exception;
	
	public CommResponse querySenderInfo(SenderInfoVo senderInfoVo) throws Exception;
}
