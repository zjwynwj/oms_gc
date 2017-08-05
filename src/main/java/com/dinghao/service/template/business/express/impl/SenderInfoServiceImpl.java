package com.dinghao.service.template.business.express.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.business.express.SenderInfoDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.SenderInfo;
import com.dinghao.entity.vo.template.business.express.SenderInfoVo;
import com.dinghao.service.template.business.express.SenderInfoService;
import com.dinghao.util.StringUtil;
@Service
@Transactional
public class SenderInfoServiceImpl implements SenderInfoService{
	
	@Autowired
	private SenderInfoDao sendinfoDao;
	@Override
	public CommResponse addSenderInfo(SenderInfoVo senderInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		sendinfoDao.insertSelective(senderInfoVo);
		return commResponse;
	}

	@Override
	public CommResponse modSenderInfo(SenderInfoVo senderInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		sendinfoDao.updateByPrimaryKeySelective(senderInfoVo);
		return commResponse;
	}

	@Override
	public CommResponse querySenderInfo(SenderInfoVo senderInfoVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		List<SenderInfo> senderInfolist = sendinfoDao.querySenderInfo();
		if(senderInfolist != null && senderInfolist.size()>0){
			SenderInfo senderInfo = sendinfoDao.querySenderInfo().get(0);
			String area = senderInfo.getState();
			if(!StringUtil.isEmpty(senderInfo.getCity())){
				area += "-"+ senderInfo.getCity();
			}
			if(!StringUtil.isEmpty(senderInfo.getDistrict())){
				area += "-"+ senderInfo.getDistrict();
			}
			senderInfo.setArea(area);
			commResponse.setData(senderInfo);
			
		}
		return commResponse;
	}

}
