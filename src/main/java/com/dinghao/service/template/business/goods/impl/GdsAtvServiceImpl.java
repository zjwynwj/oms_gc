package com.dinghao.service.template.business.goods.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.goods.GdsAtvDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.goods.GdsAtv;
import com.dinghao.entity.vo.template.business.goods.GdsAtvVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.goods.GdsAtvService;

/**
 * @ClassName: GdsAtvService
 * @Description: TODO   商品属性可选属性值管理 service 实现
 * @author helong 
 * @date 2016年1月5日 下午3:46:00
 * @version V1.0
 *
*/
@Service
@Transactional
public class GdsAtvServiceImpl implements GdsAtvService{
	@Autowired
	private GdsAtvDao atvDao;
	
	/**
	  * <p>Title: addGdsAtv</p>
	  * <p>Description: 添加属性可选值</p>
	  * @param gdsAtvVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAtvService#addGdsAtv(com.dinghao.entity.vo.business.goods.GdsAtvVo)
	 */
	public CommResponse addGdsAtv(GdsAtvVo gdsAtvVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		atvDao.insertSelective(gdsAtvVo);
		return commResponse;
	}

	/**
	  * <p>Title: delGdsAtv</p>
	  * <p>Description: 删除属性可选值</p>
	  * @param gdsAtvVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAtvService#delGdsAtv(com.dinghao.entity.vo.business.goods.GdsAtvVo)
	 */
	public CommResponse delGdsAtv(GdsAtvVo gdsAtvVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		String []delAtvId = gdsAtvVo.getDelAtvId().split(",");
		StringBuffer sb = new StringBuffer();
		boolean isDelete = true;
		
		/**1.判断 商品属性值是否全都存在*/
		for(String id1 : delAtvId){
			Long queryId = Long.parseLong(id1.split(":")[0]);
			GdsAtv atv = atvDao.selectByPrimaryKey(queryId);
			if(atv == null){
				isDelete = false;
				sb.append(id1.split(":")[1]);
			}
		}
		if(!isDelete){
			throw new DHBizException("以下商品属性值:"+sb.toString()+"不存在!");
		}
		/**2.删除商品属性值*/
		for(String id2:delAtvId){
			Long delId = Long.parseLong(id2.split(":")[0]); 
			atvDao.deleteByPrimaryKey(delId);
		}
		return commResponse;
	}

	/**
	  * <p>Title: modGdsAtv</p>
	  * <p>Description: 修改属性可选值</p>
	  * @param gdsAtvVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.business.goods.GdsAtvService#modGdsAtv(com.dinghao.entity.vo.business.goods.GdsAtvVo)
	 */
	public CommResponse modGdsAtv(GdsAtvVo gdsAtvVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		/**1.查询 商品属性值是否全都存在*/
		GdsAtv atv = atvDao.selectByPrimaryKey(gdsAtvVo.getId());
		if(atv == null){
			throw new DHBizException(ExceptionConstant.ERR_DH020005);
		}
		/**2.修改商品属性值*/
		atvDao.updateByPrimaryKeySelective(gdsAtvVo);
		return commResponse;
	}
	
}
