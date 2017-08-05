package com.dinghao.dao.template.business.express;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.express.SenderInfo;
import com.dinghao.entity.vo.template.business.express.SenderInfoVo;
@Repository
public interface SenderInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(SenderInfoVo senderInfoVo);

    int insertSelective(SenderInfoVo senderInfoVo);

    SenderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SenderInfoVo senderInfoVo);

    int updateByPrimaryKey(SenderInfoVo senderInfoVo);
    
    List<SenderInfo> querySenderInfo();
}