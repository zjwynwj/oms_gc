package com.dinghao.service.template.excel;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.dinghao.entity.template.wmstake.WmsTakeItem;

public interface ExcelManageService {
	
	String exportExcel(String fileName,String[] title, List<WmsTakeItem> list,HttpServletResponse response);

	int addUploadFile(MultipartFile file, Long takeId);

}
