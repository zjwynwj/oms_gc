package com.dinghao.dao.template.business.express;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dinghao.entity.template.business.express.PrintTemplate;
import com.dinghao.entity.vo.template.business.express.PrintTemplateVo;
@Repository
public interface PrintTemplateDao {
    int deleteByPrimaryKey(Long id);

    int insert(PrintTemplateVo printTemplateVo);

    int insertSelective(PrintTemplateVo printTemplateVo);

    PrintTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintTemplateVo printTemplateVo);

    int updateByPrimaryKey(PrintTemplateVo printTemplateVo);
    
    List<PrintTemplate> selectPrintTemplateGridListPage(PrintTemplateVo printTemplateVo);
    
    int selectPrintTemplateGridListCount(PrintTemplateVo printTemplateVo);
    
}