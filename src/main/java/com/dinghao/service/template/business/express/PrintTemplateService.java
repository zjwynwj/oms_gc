package com.dinghao.service.template.business.express;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.express.PrintTemplateVo;

public interface PrintTemplateService {


	public CommResponse addPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception;
	
	public CommResponse modPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception;
	
	public CommResponse delPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception;
	
	public CommResponse queryPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception;
	
	public CommResponse findPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception;
}
