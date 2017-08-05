package com.dinghao.service.template.business.express.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.express.PrintTemplateDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.PrintTemplate;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.express.PrintTemplateVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.express.PrintTemplateService;

@Service
@Transactional
public class PrintTemplateServiceImpl implements PrintTemplateService{

	@Autowired
	private PrintTemplateDao printTemplateDao;
	
	@Override
	public CommResponse addPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		printTemplateVo.setStatus("1");
		printTemplateVo.setUpdateTime(new Date());
		printTemplateDao.insertSelective(printTemplateVo);
		return commResponse;
	}

	@Override
	public CommResponse modPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PrintTemplate printTemplate = printTemplateDao.selectByPrimaryKey(printTemplateVo.getId());
		if(printTemplate == null){
			throw new DHBizException("打印模板不存在，请联系管理员");
		}
		printTemplateDao.updateByPrimaryKeySelective(printTemplateVo);
		return commResponse;
	}

	@Override
	public CommResponse delPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		printTemplateDao.deleteByPrimaryKey(printTemplateVo.getId());
		return commResponse;
	}

	@Override
	public CommResponse queryPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		commResponse.setData(printTemplateDao.selectByPrimaryKey(printTemplateVo.getId()));
		return commResponse;
	}

	@Override
	public CommResponse findPrintTemplate(PrintTemplateVo printTemplateVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<PrintTemplate> pageVo = new PageVo<PrintTemplate>();
		pageVo.setRows(printTemplateVo.getRows());
		pageVo.setList(printTemplateDao.selectPrintTemplateGridListPage(printTemplateVo));
		pageVo.setCount(printTemplateDao.selectPrintTemplateGridListCount(printTemplateVo));
		commResponse.setData(pageVo);
		return commResponse;
	}

}
