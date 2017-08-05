package com.dinghao.service.template.business.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dinghao.constant.ExceptionConstant;
import com.dinghao.dao.template.business.member.MemberDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.member.Member;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.business.member.MemberVo;
import com.dinghao.exception.DHBizException;
import com.dinghao.service.template.business.member.MemberService;
import com.dinghao.util.BeanUtils;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	/**
	  * <p>Title: addMember</p>
	  * <p>Description: 添加会员</p>
	  * @param member
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#addCustInfo(com.dinghao.entity.vo.base.BaseNumberVo)
	 */
	public CommResponse addMember(MemberVo memberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		
		int count = memberDao.selectMemberCountByShopNick(memberVo);
		if(count > 0){
			throw new DHBizException(ExceptionConstant.ERR_DH100003);
		}else{
			memberDao.insertSelective(memberVo);
		}
		
		return commResponse;
	}

	/**
	  * <p>Title: delMember</p>
	  * <p>Description: 删除会员</p>
	  * @param member
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#delCustInfo(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse delMember(MemberVo memberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		Member  member = new Member();
		member = memberDao.selectByPrimaryKey(memberVo.getId());
		if(member == null){
			throw new DHBizException(ExceptionConstant.ERR_DH100004);
		}else{
			memberDao.deleteByPrimaryKey(member.getId());
		}
		return commResponse;
	}

	/**
	  * <p>Title: modMember</p>
	  * <p>Description: 修改客户信息</p>
	  * @param member
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#modCustInfo(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse modMember(MemberVo memberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		Member  member = new Member();
		member = memberDao.selectByPrimaryKey(memberVo.getId());
		if(member == null){
			throw new DHBizException(ExceptionConstant.ERR_DH100004);
		}else{
			BeanUtils.copyProperties(memberVo, member);
			memberDao.updateByPrimaryKeySelective(memberVo);
		}
		
		return commResponse;
	}

	/**
	  * <p>Title: queryCustInfoById</p>
	  * <p>Description: 根据id查询 客户信息 </p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#queryCustInfoById(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse queryMemberById(Long Id) throws Exception {
		CommResponse commResponse = new CommResponse();
		commResponse.setData(memberDao.selectByPrimaryKey(Id));
		return commResponse;
	}

	/**
	  * <p>Title: findCustInfoForGrid</p>
	  * <p>Description: 查询客户分页数据</p>
	  * @param custInfoVo
	  * @return
	  * @throws Exception
	  * @see com.dinghao.service.base.CustInfoService#findMemberForGrid(com.dinghao.entity.vo.base.CustInfoVo)
	 */
	public CommResponse findMemberForGrid(MemberVo memberVo) throws Exception {
		CommResponse commResponse = new CommResponse();
		PageVo<MemberVo> pageVo = new PageVo<MemberVo>();
		
		List<MemberVo> memberList =  memberDao.selectMemberListPage(memberVo	);
		pageVo.setRows(memberVo.getRows());
		pageVo.setList(memberList);
		pageVo.setCount(memberDao.selectMemberListPageCount(memberVo	));
    	pageVo.setPageNum(memberVo.getPageNum());
		pageVo.setRows(memberVo.getRows());
	
		commResponse.setData(pageVo);
		return commResponse;
	}

}
