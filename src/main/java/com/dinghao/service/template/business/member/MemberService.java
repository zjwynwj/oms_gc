package com.dinghao.service.template.business.member;

import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.template.business.member.MemberVo;

/**
  * @ClassName: MemberService
  * @Description: TODO  会员管理service  接口
  * @author 古聪
  * @date 2016年1月28日 上午9:45:59
  * @version V1.0
  *
 */
public interface MemberService {
	
	/**
	  * @Title: addCustInfo
	  * @Description: TODO  添加会员
	  * @param @param baseNumberVo
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse addMember(MemberVo memberVo) throws Exception;
	
	/**
	  * @Title: addMember
	  * @Description: TODO  删除会员
	  * @param @param member
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse delMember(MemberVo memberVo) throws Exception;
	
	/**
	  * @Title: modMember
	  * @Description: TODO  修改会员
	  * @param @param member
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	/**
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public CommResponse modMember(MemberVo memberVo) throws Exception;
	
	/**
	  * @Title: queryMemberById
	  * @Description: TODO   根据id查询 会员信息
	  * @param @param member
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse queryMemberById(Long Id) throws Exception;
	
	/**
	  * @Title: findMemberForGrid
	  * @Description: TODO  查询客户分页数据
	  * @param @param member
	  * @param @return
	  * @param @throws Exception
	  * @return CommResponse
	  * @throws
	 */
	public CommResponse findMemberForGrid(MemberVo memberVo) throws Exception;
}
