package com.dinghao.action.template.business.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.dao.template.business.member.MemberDao;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.template.business.member.Member;
import com.dinghao.entity.vo.template.business.member.MemberVo;
import com.dinghao.service.template.business.member.MemberService;

@Controller
@RequestMapping("/template")
public class MemberAction extends BaseAction{

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDao memberDao;
	  
	//跳转到会员管理页面
	@RequestMapping(value = {"/memberMgr/turnMemberMgr.jhtml"})
	public String turnMemberMgr() {
		return "/template/front/ftls/member/memberMgr";
	}
	
	//跳转到修改会员信息管理页面
	@RequestMapping(value = {"/memberMgr/turnModMember.jhtml"})
	public String turnModMember(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap ,MemberVo memberVo) {
	
	   
		CommResponse commResponse;
		try {
			commResponse = memberService.queryMemberById(memberVo.getId());
			Member member = (Member)commResponse.getData();
			modelMap.put("member",member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return "/template/front/ftls/member/modMerber";
	}
	
	
	
	//修改单条会员信息
	@RequestMapping(value = {"/memberMgr/modMember.jhtml"})
	public void modMember(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, MemberVo memberVo) throws Exception {
         this.returnData(request, response, memberService.modMember(memberVo));
	}
	
	//分页查询会员信息
	@RequestMapping(value = {"/memberMgr/findMemberGrid.jhtml"})
	public void findMemberGrid(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, MemberVo memberVo) throws Exception {
         this.returnData(request, response, memberService.findMemberForGrid(memberVo));
	}
	
}
