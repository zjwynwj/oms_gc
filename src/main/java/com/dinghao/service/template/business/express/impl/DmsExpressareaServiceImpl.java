package com.dinghao.service.template.business.express.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.base.BaseAreaDao;
import com.dinghao.dao.template.business.express.DmsExpressareaDao;
import com.dinghao.entity.template.base.BaseArea;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.express.DmsExpressarea;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.BaseAreaVo;
import com.dinghao.entity.vo.template.business.express.DmsExpressareaVo;
import com.dinghao.entity.vo.template.business.express.ExpressareaVoList;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.express.DmsExpressareaService;
import com.dinghao.util.BeanUtils;

@Service
@Transactional
public class DmsExpressareaServiceImpl implements DmsExpressareaService{
	@Autowired
	private DmsExpressareaDao expressareaDao;
	@Autowired
	private BaseAreaDao areaDao;
	
	public CommResponse findDmsExpressareaInfoForGrid(DmsExpressareaVo expressareaVo)  throws Exception{
		CommResponse commResponse = new CommResponse();
		PageVo<DmsExpressarea> pageVo = new PageVo<DmsExpressarea>();
		pageVo.setRows(expressareaVo.getRows());
		pageVo.setList(expressareaDao.selectExpressAreaListGrid(expressareaVo));
		pageVo.setCount(expressareaDao.selectExpressAreaListCount(expressareaVo));
		commResponse.setData(pageVo);
		return commResponse;
	}
	
	public CommResponse findDmsExpressareaInfoList(DmsExpressareaVo expressareaVo)  throws Exception{
		CommResponse commResponse = new CommResponse();
		commResponse.setData(expressareaDao.selectExpressAreaList(expressareaVo));
		return commResponse;
	}
	
	public CommResponse modDmsExpressareaInfo(ExpressareaVoList expressareaVoList) throws Exception{
		CommResponse commResponse = new CommResponse();
		List<DmsExpressareaVo> list = expressareaVoList.getList();
		if(null == list || list.size()==0){
			throw new DHBizException("请选择操作记录!");
		}
		for(DmsExpressareaVo expressareaVo : list){
			DmsExpressarea dmsExpressarea = expressareaDao.selectByPrimaryKey(expressareaVo.getId());
			dmsExpressarea.setStartPrice(expressareaVo.getStartPrice());
			dmsExpressarea.setAddPrice(expressareaVo.getAddPrice());
			dmsExpressarea.setStartWeight(expressareaVo.getStartWeight());
			dmsExpressarea.setAddWeight(expressareaVo.getAddWeight());
			dmsExpressarea.setIsdelivery(expressareaVo.getIsdelivery());
			BeanUtils.copyProperties(expressareaVo, dmsExpressarea);
			expressareaDao.updateByPrimaryKeySelective(expressareaVo);
		}
		commResponse.setErrMsg("修改配送区域信息成功!");
		return commResponse;
	}
	
	public CommResponse modDmsExpressareaDelivery(DmsExpressareaVo expressareaVo) throws Exception{
		CommResponse commResponse = new CommResponse();
		DmsExpressarea dmsExpressarea = expressareaDao.selectByPrimaryKey(expressareaVo.getId());
		dmsExpressarea.setIsdelivery(expressareaVo.getIsdelivery());
		BeanUtils.copyProperties(expressareaVo, dmsExpressarea);
		expressareaDao.updateByPrimaryKeySelective(expressareaVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryIsArriveAppointArea</p>
	  * <p>Description: 查询物流是否到达指定区域</p>
	  * @param expressareaVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.template.business.express.DmsExpressareaService#queryIsArriveAppointArea(com.dinghao.entity.vo.template.business.express.DmsExpressareaVo)
	 */
	public CommResponse queryIsArriveAppointArea(DmsExpressareaVo expressareaVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		BaseAreaVo area = new BaseAreaVo();
		area.setRegionName(expressareaVo.getProvName());
		area.setRegionType(1);
		//查询省份id
		BaseArea baseArea = areaDao.selectAreaIdByParam(area);
		Long provId = 0L;
		if ( baseArea!=null)
		{
		   provId = baseArea.getId();
		}
		DmsExpressareaVo queryExpressarea = new DmsExpressareaVo();
		queryExpressarea.setExpressId(expressareaVo.getExpressId());
		queryExpressarea.setProvId(provId);
		DmsExpressarea dmsExpressarea =  expressareaDao.selectByParam(queryExpressarea);
		
		if(dmsExpressarea != null && dmsExpressarea.getExpressId()!=null){
			commResponse.setData(dmsExpressarea);
		}else{
			throw new DHBizException("系统错误,请联系管理员!");
		}
		return commResponse;
	}

}
