package com.dinghao.service.template.excel.imp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dinghao.dao.template.wmstake.WmsTakeItemDao;
import com.dinghao.entity.template.wmstake.WmsTakeItem;
import com.dinghao.entity.vo.template.wmstake.WmsTakeItemVo;
import com.dinghao.service.template.excel.ExcelManageService;
import com.dinghao.util.BeanUtils;
import com.dinghao.util.DateUtil;
import com.dinghao.util.MediaUtils;

@Transactional
@Service
public class ExcelManageServiceImp implements ExcelManageService {
	final Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	@Autowired
	WmsTakeItemDao wmsTakeItemDao;

	@Override
	public String exportExcel(String fileName, String[] Title,
			List<WmsTakeItem> listContent, HttpServletResponse response) {

		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont NormalFontRed = new WritableFont(WritableFont.ARIAL,
					10);
			NormalFontRed.setColour(Colour.RED);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行

			// 用于正文居左 显红
			WritableCellFormat wcf_left_red = new WritableCellFormat(
					NormalFontRed);
			wcf_left_red.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left_red.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left_red.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left_red.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
				sheet.setColumnView(i, 20);
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields = null;
			int i = 1;
			for (WmsTakeItem obj : listContent) {
				/*
				 * fields=obj.getClass().getDeclaredFields(); int j=0; for(Field
				 * v:fields){ v.setAccessible(true); Object va=v.get(obj);
				 * if(va==null){ va=""; } sheet.addCell(new Label(j,
				 * i,va.toString(),wcf_left)); j++; }
				 */
				sheet.addCell(new Label(0, i, obj.getId().toString(),
						wcf_left_red));
				sheet.addCell(new Label(1, i, obj.getGdsNo(), wcf_left));
				sheet.addCell(new Label(2, i, obj.getGdsFormat()
						+ obj.getAttbs(), wcf_left));
				sheet.addCell(new Label(3, i, obj.getSysQty() == null ? ""
						: obj.getSysQty().toString(), wcf_left));
				sheet.addCell(new Label(4, i, obj.getCountQty() == null ? ""
						: obj.getCountQty().toString(), wcf_left));
				sheet.addCell(new Label(5, i, obj.getDiffQty() == null ? ""
						: obj.getDiffQty().toString(), wcf_left));
				sheet.addCell(new Label(6, i, obj.getWarehousePositionsName(),
						wcf_left));
				sheet.addCell(new Label(7, i, DateUtil.format(
						obj.getCreateDate(), "yyyy-MM-dd"), wcf_left));
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回数字说明
	 * 0  文档不符合规范
	 * 1 上传的文档与当前页数据不匹配
	 * 2 操作成功
	 * 3 文件不匹配
	 */
	@SuppressWarnings("resource")
	@Override
	public int addUploadFile(MultipartFile file,Long takeIdOld) {
		String fileName = file.getOriginalFilename();
		if (StringUtils.isBlank(fileName) || fileName.split("_").length < 2) {
			return 0;
		}
		if(!".xls".equals(MediaUtils.getFileExt(fileName))){
			return 3;
		}
		fileName = new String(fileName.substring(0, fileName.indexOf(".")));
		Long takeId = Long.parseLong(fileName.split("_")[1]);
		if(takeIdOld.intValue()!=takeId.intValue()){
			return 1;
		}
		// 读取Excel
		List<String[]> dataList = new ArrayList<String[]>();
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (FileNotFoundException ex) {
			logger.error(ex.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(is);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
		Sheet sheet = wb.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = 0;
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		// 去除第一行
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			String[] rowList = new String[totalCells];
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (cell == null) {
					rowList[c] = cellValue;
					continue;
				}
				cellValue = ConvertCellStr(cell, cellValue);
				rowList[c] = cellValue;
			}
			dataList.add(rowList);
		}
		WmsTakeItemVo record = new WmsTakeItemVo();
		record.setTakeId(takeId);
		record.setRows(Integer.MAX_VALUE);
		List<WmsTakeItem> wmsTakeItems = wmsTakeItemDao
				.selectByStatement(record);
		for (WmsTakeItem wmsTakeItem : wmsTakeItems) {
			for (String[] strings : dataList) {
				if (StringUtils.isNotBlank(strings[0])
						&& strings.length > 4
						&& StringUtils.isNotBlank(strings[4])
						&& wmsTakeItem.getId().intValue() == Integer
								.parseInt(strings[0])) {
					int countQty = (int) Double.parseDouble(strings[4]);
					wmsTakeItem.setCountQty(countQty);
					wmsTakeItem.setDiffQty((wmsTakeItem.getSysQty()==null?0:wmsTakeItem.getSysQty())-countQty);
					WmsTakeItemVo wmsTakeItemVo = new WmsTakeItemVo();
					try {
						BeanUtils.copyProperties(wmsTakeItemVo, wmsTakeItem);
					} catch (IllegalArgumentException e) {
						logger.error(e.getMessage());
						return 0;
					} catch (IllegalAccessException e) {
						logger.error(e.getMessage());
						return 0;
					} catch (InvocationTargetException e) {
						logger.error(e.getMessage());
						return 0;
					}
					wmsTakeItemDao.updateByPrimaryKeySelective(wmsTakeItemVo);
				}

			}
		}
		return 2;
	}

	private String ConvertCellStr(Cell cell, String cellStr) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			// 读取String
			cellStr = cell.getStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			// 得到Boolean对象的方法
			cellStr = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			cellStr = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 读取公式
			cellStr = cell.getCellFormula().toString();
			break;
		}
		return cellStr;
	}
}
