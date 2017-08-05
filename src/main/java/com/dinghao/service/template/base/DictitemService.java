package com.dinghao.service.template.base;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.base.DictVo;

public interface DictitemService {

	public CommResponse queryDictItem(DictVo dictVo) throws Exception;
}
