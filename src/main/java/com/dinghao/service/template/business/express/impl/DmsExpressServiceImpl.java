package com.dinghao.service.template.business.express.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.express.DmsExpressDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.DmsExpress;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.express.DmsExpressService;
import com.dinghao.util.TBUtil;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.LogisticsCompany;
import com.taobao.api.request.LogisticsCompaniesGetRequest;
import com.taobao.api.response.LogisticsCompaniesGetResponse;
@Service
@Transactional
public class DmsExpressServiceImpl implements DmsExpressService{

	@Autowired
	private DmsExpressDao expressDao;
	
	public CommResponse downDmsExpressInfo(DmsExpressVo expressVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		int count = expressDao.selectExpressListCount(expressVo);
		if(count > 0 ){
			throw new DHBizException("已经下载过了!");
		}
		TaobaoClient client = TBUtil.getTaobaoClient("23295806" , "456a7ca626134b530cb23e68ac381d6e");
    	LogisticsCompaniesGetRequest   req = new LogisticsCompaniesGetRequest  ();//实例化具体API对应的Request类
        req.setFields("id,code,name,reg_mail_no");
        LogisticsCompaniesGetResponse response;
        try {
            response = client.execute(req); //执行API请求并打印结果
            List<LogisticsCompany> logisticsList = response.getLogisticsCompanies();
            for(LogisticsCompany logis : logisticsList){
            	expressVo = new DmsExpressVo(); 
            	expressVo.setCode(logis.getCode());
            	expressVo.setName(logis.getName());
            	expressVo.setActived("1");
            	expressVo.setExpressReg(logis.getRegMailNo());
            	expressDao.insertSelective(expressVo);
            }
        } catch (ApiException e) {
        	
        }
		return commResponse;
	}
	
	public CommResponse modDmsExpressInfo(DmsExpressVo expressVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		expressDao.updateByPrimaryKeySelective(expressVo);
		return commResponse;
	}
	
	public CommResponse queryDmsExpressInfo(DmsExpressVo expressVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		DmsExpress dmsExpress = expressDao.selectByPrimaryKey(expressVo.getId());
		commResponse.setData(dmsExpress);
		return commResponse;
	}

	public CommResponse findDmsExpressInfoForGrid(DmsExpressVo expressVo)  throws Exception{
		CommResponse commResponse = new CommResponse();
		PageVo<DmsExpress> pageVo = new PageVo<DmsExpress>();
		pageVo.setRows(expressVo.getRows());
		pageVo.setList(expressDao.selectExpressListGrid(expressVo));
		pageVo.setCount(expressDao.selectExpressListCount(expressVo));
		commResponse.setData(pageVo);
		return commResponse;
	}
	
	@Cacheable(value = "getAllDmsExpress")
	public List<DmsExpress> getAllDmsExpress(DmsExpressVo expressVo)  throws Exception{
		return expressDao.selectExpressList(expressVo);
	}
}
