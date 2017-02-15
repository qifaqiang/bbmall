package com.wxsoft.framework.view;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wxsoft.framework.util.Tools;

public class MemberAdminExcelView extends AbstractExcelView {

	private static Map<String, String> mapsCode;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename + ".xls");
		sheet = workbook.createSheet(model.get("times") + " 会员注册量");

		HSSFCellStyle headerStyle1 = workbook.createCellStyle(); // 标题样式
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont1 = workbook.createFont(); // 标题字体
		headerFont1.setFontHeightInPoints((short) 20);
		headerStyle1.setFont(headerFont1);

		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 1));// 指定合并区域
		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(headerStyle1);
		setText(cell, model.get("times") + " 会员注册量");

		Map<String, Object> json = (Map<String, Object>) model.get("data");

		HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont(); // 标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);
		short width = 20, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);

		// 第一列空 // 设置标题
		cell = getCell(sheet, 1, 0);
		cell.setCellStyle(headerStyle);
		setText(cell, "基地/会员量");

		cell = getCell(sheet, 1, 1);
		cell.setCellStyle(headerStyle);
		setText(cell, "数量");
		sheet.getRow(1).setHeight(height);

		HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		Iterator<Entry<String, Object>> entries2 = json.entrySet().iterator(); // 设置标题
		int iii = 2;
		while (entries2.hasNext()) {
			Entry<String, Object> entry = entries2.next();
			cell = getCell(sheet, iii, 0);
			setText(cell, entry.getKey().toString());

			cell = getCell(sheet, iii, 1);
			setText(cell, entry.getValue().toString());
			iii++;
		}
	}
}
