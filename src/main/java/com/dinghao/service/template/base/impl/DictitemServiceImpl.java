package com.dinghao.service.template.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.base.DictDao;
import com.dinghao.dao.template.base.DictitemDao;
import com.dinghao.entity.template.base.Dict;
import com.dinghao.entity.template.base.Dictitem;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.DictVo;
import com.dinghao.service.template.base.DictitemService;

@Service
@Transactional
public class DictitemServiceImpl implements DictitemService{
	@Autowired
	private DictDao dictDao;
	@Autowired
	private DictitemDao dictitemDao;
	public CommResponse queryDictItem(DictVo dictVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		Dict dict = dictDao.selectByDictVal(dictVo);
		List<Dictitem> dictitemList = dictitemDao.selectDictitemListByDictId(dict.getDictId());
		commResponse.setData(dictitemList);
		return commResponse;
	}

}
