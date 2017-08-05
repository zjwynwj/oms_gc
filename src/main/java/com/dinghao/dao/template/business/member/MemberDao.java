package com.dinghao.dao.template.business.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.member.Member;
import com.dinghao.entity.vo.template.business.member.MemberVo;

@Repository
public interface MemberDao {
    int deleteByPrimaryKey(Long id)  throws Exception;

    int insert(MemberVo record)  throws Exception;

    int insertSelective(MemberVo record) throws Exception;

    Member selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(MemberVo record) throws Exception;
    
    int selectMemberCountByShopNick(MemberVo record)  throws Exception;
    
    List<MemberVo> selectMemberListPage(MemberVo record)  throws Exception;
    int selectMemberListPageCount(MemberVo record)  throws Exception;
    
       
    int updateByPrimaryKey(MemberVo record) throws Exception;
}