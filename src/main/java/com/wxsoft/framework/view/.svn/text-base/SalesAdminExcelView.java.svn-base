package com.wxsoft.framework.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.util.Tools;

public class SalesAdminExcelView extends AbstractExcelView {

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
		sheet = workbook.createSheet(model.get("times")+" 销量");

		HSSFCellStyle headerStyle1 = workbook.createCellStyle(); // 标题样式
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont1 = workbook.createFont(); // 标题字体
		headerFont1.setFontHeightInPoints((short) 20);
		headerStyle1.setFont(headerFont1);

		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));// 指定合并区域
		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(headerStyle1);
		setText(cell, model.get("times")+" 销量");

		// 第一行
		JSONObject json = (JSONObject) model.get("data");
		Map<String, Object> headPB = (Map<String, Object>) json.get("mapsPB");

		// 其他数据
		JSONObject jsonO = (JSONObject) model.get("data");
		Map<String, LinkedHashMap> results = (Map<String, LinkedHashMap>) jsonO
				.get("sql1");

		int len = headPB.size();
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

		// 第一列空
		cell = getCell(sheet, 1, 0);
		cell.setCellStyle(headerStyle);
		setText(cell, "基地/字典商品");

		List<String> prodBasicId = new ArrayList<String>();

		Iterator<Entry<String, Object>> entries = headPB.entrySet().iterator(); // 设置标题
		int ii = 1;
		while (entries.hasNext()) {
			Entry<String, Object> entry = entries.next();
			cell = getCell(sheet, 1, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, entry.getValue().toString());
			prodBasicId.add(entry.getKey());
			ii++;
		}

		sheet.getRow(1).setHeight(height);

		HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		Iterator<Entry<String, LinkedHashMap>> entries2 = results.entrySet()
				.iterator(); // 设置标题
		int iii = 2;
		while (entries2.hasNext()) {
			Entry<String, LinkedHashMap> entry = entries2.next();
			cell = getCell(sheet, iii, 0);
			setText(cell, entry.getKey().toString().split("===")[1]);
			LinkedHashMap tempMap = entry.getValue();
			int count = 1;
			for (String string : prodBasicId) {
				cell = getCell(sheet, iii, count);
				// cell.setCellStyle(headerStyle);
				setText(cell, tempMap.get(string).toString());
				count++;
			}
			iii++;
		}
	}
}
