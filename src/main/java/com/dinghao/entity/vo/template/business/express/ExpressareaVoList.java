package com.dinghao.entity.vo.template.business.express;

import java.io.Serializable;
import java.util.List;

public class ExpressareaVoList implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO（用于批量修改的配送区域信息请求）
	  */
	
	private static final long serialVersionUID = 1L;
	private List<DmsExpressareaVo> list;

	public List<DmsExpressareaVo> getList() {
		return list;
	}

	public void setList(List<DmsExpressareaVo> list) {
		this.list = list;
	}
	
}
