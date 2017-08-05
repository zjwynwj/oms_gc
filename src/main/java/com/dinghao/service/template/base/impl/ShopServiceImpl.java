package com.dinghao.service.template.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.dao.template.base.ShopDao;
import com.dinghao.entity.template.base.FinanceAccount;
import com.dinghao.entity.template.base.Shop;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.base.ShopVo;
import com.dinghao.service.template.base.ShopService;
/**
  * @ClassName: BaseNumberServiceImpl
  * @Description: TODO  店铺管理service层  实现
  * @author gucong 
  * @date 2016年1月18日 下午3:34:02
  * @version V1.0
  *
 */
@Service
@Transactional
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	
	/**
	  * <p>Title: addBaseNumber</p>
	  * <p>Description: 添加单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#addBaseNumber(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse add(ShopVo shopVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		shopDao.insertSelective(shopVo);
		return commResponse;
	}

	/**
	  * <p>Title: modBaseNumber</p>
	  * <p>Description: 修改单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#modBaseNumber(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse mod(ShopVo shopVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		shopDao.updateByPrimaryKeySelective(shopVo);
		return commResponse;
	}

	/**
	  * <p>Title: queryBaseNumberById</p>
	  * <p>Description: 根据id查询单据号</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.BaseNumberService#queryBaseNumberById(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public Shop queryById(Long id) throws Exception {
		//CommResponse commResponse = new CommResponse();
	//	commResponse.setData(shopDao.selectByPrimaryKey(id));
		Shop shop = shopDao.selectByPrimaryKey(id);
		return shop;
	}


	/**
	  * <p>Title: findBaseNumberForGrid</p>
	  * <p>Description: 分页查询单据号数据</p>
	  * @param baseNumberVo
	  * @return
	  * @throws Exception
	  * @see findForGrid()
	 */
	public CommResponse findForGrid(ShopVo shopVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<Shop> pageVo = new PageVo<Shop>();
		
		List<Shop> mlist = shopDao.selectListPage(shopVo) ;
		pageVo.setRows(shopVo.getRows());
		pageVo.setList( mlist );
		pageVo.setCount(shopDao.selectListPage(shopVo).size());
		commResponse.setData(pageVo);
		return commResponse;
	}

	@Override
	public List<Shop> getShops(ShopVo shopVo) {
		try {
			return shopDao.selectListPage(shopVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
