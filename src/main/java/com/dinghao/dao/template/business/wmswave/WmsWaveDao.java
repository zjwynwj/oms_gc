package com.dinghao.dao.template.business.wmswave;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.wmswave.WmsWave;
import com.dinghao.entity.vo.template.business.wmswave.WmsWaveVo;
@Repository
public interface WmsWaveDao {
    int deleteByPrimaryKey(Long id);

    int insert(WmsWaveVo wemWaveVo);

    int insertSelective(WmsWaveVo wemWaveVo);

    WmsWave selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WmsWaveVo wemWaveVo);

    int updateByPrimaryKey(WmsWaveVo wemWaveVo);
    
    List<WmsWave> selectWmsWaveList(WmsWaveVo wemWaveVo) throws Exception;
    
    int selectWmsWaveCount(WmsWaveVo wemWaveVo) throws Exception;
}