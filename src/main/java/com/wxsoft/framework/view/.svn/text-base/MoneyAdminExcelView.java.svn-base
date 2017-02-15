package com.wxsoft.framework.view;

import java.text.DecimalFormat;
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

public class MoneyAdminExcelView extends AbstractExcelView {

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
		sheet = workbook.createSheet(model.get("times") + " 月入账资金统计");

		HSSFCellStyle headerStyle1 = workbook.createCellStyle(); // 标题样式
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont1 = workbook.createFont(); // 标题字体
		headerFont1.setFontHeightInPoints((short) 20);
		headerStyle1.setFont(headerFont1);

		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));// 指定合并区域
		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(headerStyle1);
		setText(cell, model.get("times") + " 月入账资金统计");

		// 第一行
		JSONObject json = (JSONObject) model.get("data");

		// 獲取城市
		Map<String, Object> headPB = (Map<String, Object>) json
				.get("mapCompanyName");

		// 其他数据
		Map<String, Map<String, Map<String, Object>>> mapAll = (Map<String, Map<String, Map<String, Object>>>) json
				.get("mapAll");

		int len = headPB.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
		// headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont(); // 标题字体
		// headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);
		short width = 10, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);

		// 第一列空
		cell = getCell(sheet, 1, 0);
		cell.setCellStyle(headerStyle);
		setText(cell, "基地/资金");

		// sheet.addMergedRegion(new Region(0, (short) 0, 4, (short) 8));//
		// 指定合并区域
		cell = getCell(sheet, 1, 1);
		cell.setCellStyle(headerStyle);
		setText(cell, "微信支付");
		cell = getCell(sheet, 1, 8);
		cell.setCellStyle(headerStyle);
		setText(cell, "支付宝");
		cell = getCell(sheet, 1, 15);
		cell.setCellStyle(headerStyle);
		setText(cell, "银联");
		cell = getCell(sheet, 1, 22);
		cell.setCellStyle(headerStyle);
		setText(cell, "合计");

		Iterator<Entry<String, Map<String, Map<String, Object>>>> entries = mapAll
				.entrySet().iterator(); // 设置标题

		int iii = 2;

		// 总计微信支付
		double allweixinzhifuyingshou = 0;
		double allweixinzhifutuikuan = 0;
		double allweixinzhifushishou = 0;

		// 总计支付宝支付
		double allzhifubaozhifuyingshou = 0;
		double allzhifubaozhifutuikuan = 0;
		double allzhifubaozhifushishou = 0;

		// 总计银联支付
		double allyinlianzhifuyingshou = 0;
		double allyinlianzhifutuikuan = 0;
		double allyinlianzhifushishou = 0;

		// 总计所有支付
		double allzhifuyingshou = 0;
		double allzhifutuikuan = 0;
		double allzhifushishou = 0;

		DecimalFormat df = new DecimalFormat("#.00");

		while (entries.hasNext()) {
			int ii = 0;
			Entry<String, Map<String, Map<String, Object>>> entry = entries
					.next();

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, headPB.get(entry.getKey()).toString());
			ii++;

			// 微信
			double weixinzhifuyingshou = 0;
			double weixinzhifutuikuan = 0;
			double weixinzhifushishou = 0;

			// 支付宝
			double zhifubaozhifuyingshou = 0;
			double zhifubaozhifutuikuan = 0;
			double zhifubaozhifushishou = 0;

			// 银联
			double yinlianzhifuyingshou = 0;
			double yinlianzhifutuikuan = 0;
			double yinlianzhifushishou = 0;

			// 合计
			double hezhifuyingshou = 0;
			double hezhifutuikuan = 0;
			double hezhifushishou = 0;

			// 1pc(支付宝) 2pc（微信扫码） 3pc（银联支付） 4wap（支付宝）
			// 5wap（微信） 6wap（银联支付）

			if (null != entry.getValue().get("1")
					&& null != entry.getValue().get("1").get("yinshou")) {
				zhifubaozhifuyingshou = Double.parseDouble(entry.getValue()
						.get("1").get("yinshou").toString());
				hezhifuyingshou += zhifubaozhifuyingshou;
			}
			if (null != entry.getValue().get("2")
					&& null != entry.getValue().get("2").get("yinshou")) {
				weixinzhifuyingshou = Double.parseDouble(entry.getValue()
						.get("2").get("yinshou").toString());
				hezhifuyingshou += weixinzhifuyingshou;
			}
			if (null != entry.getValue().get("3")
					&& null != entry.getValue().get("3").get("yinshou")) {
				yinlianzhifuyingshou = Double.parseDouble(entry.getValue()
						.get("3").get("yinshou").toString());
				hezhifuyingshou += yinlianzhifuyingshou;
			}
			if (null != entry.getValue().get("4")
					&& null != entry.getValue().get("4").get("yinshou")) {
				zhifubaozhifuyingshou = Double.parseDouble(entry.getValue()
						.get("4").get("yinshou").toString());
				hezhifuyingshou += zhifubaozhifuyingshou;
			}
			if (null != entry.getValue().get("5")
					&& null != entry.getValue().get("5").get("yinshou")) {
				weixinzhifuyingshou = Double.parseDouble(entry.getValue()
						.get("5").get("yinshou").toString());
				hezhifuyingshou += weixinzhifuyingshou;
			}
			if (null != entry.getValue().get("6")
					&& null != entry.getValue().get("6").get("yinshou")) {
				yinlianzhifuyingshou = Double.parseDouble(entry.getValue()
						.get("6").get("yinshou").toString());
				hezhifuyingshou += yinlianzhifuyingshou;
			}

			if (null != entry.getValue().get("1")
					&& null != entry.getValue().get("1").get("tuikuan")) {
				zhifubaozhifutuikuan = Double.parseDouble(entry.getValue()
						.get("1").get("tuikuan").toString());
				hezhifutuikuan += zhifubaozhifutuikuan;
			}
			if (null != entry.getValue().get("2")
					&& null != entry.getValue().get("2").get("tuikuan")) {
				weixinzhifutuikuan = Double.parseDouble(entry.getValue()
						.get("2").get("tuikuan").toString());
				hezhifutuikuan += weixinzhifutuikuan;
			}
			if (null != entry.getValue().get("3")
					&& null != entry.getValue().get("3").get("tuikuan")) {
				yinlianzhifutuikuan = Double.parseDouble(entry.getValue()
						.get("3").get("tuikuan").toString());
				hezhifutuikuan += yinlianzhifutuikuan;
			}
			if (null != entry.getValue().get("4")
					&& null != entry.getValue().get("4").get("tuikuan")) {
				zhifubaozhifutuikuan = Double.parseDouble(entry.getValue()
						.get("4").get("tuikuan").toString());
				hezhifutuikuan += zhifubaozhifutuikuan;
			}
			if (null != entry.getValue().get("5")
					&& null != entry.getValue().get("5").get("tuikuan")) {
				weixinzhifutuikuan = Double.parseDouble(entry.getValue()
						.get("5").get("tuikuan").toString());
				hezhifutuikuan += weixinzhifutuikuan;
			}
			if (null != entry.getValue().get("6")
					&& null != entry.getValue().get("6").get("tuikuan")) {
				yinlianzhifutuikuan = Double.parseDouble(entry.getValue()
						.get("6").get("tuikuan").toString());
				hezhifutuikuan += yinlianzhifutuikuan;
			}

			// 计算收入
			weixinzhifushishou = weixinzhifuyingshou - weixinzhifutuikuan;
			zhifubaozhifushishou = zhifubaozhifuyingshou - zhifubaozhifutuikuan;
			yinlianzhifushishou = yinlianzhifuyingshou - yinlianzhifutuikuan;
			hezhifushishou = hezhifuyingshou - hezhifutuikuan;

			// 计算全局
			allzhifuyingshou += hezhifuyingshou;
			allzhifutuikuan += hezhifutuikuan;
			allzhifushishou += hezhifushishou;

			allweixinzhifushishou += weixinzhifushishou;
			allweixinzhifuyingshou += weixinzhifuyingshou;
			allweixinzhifutuikuan += weixinzhifutuikuan;

			allzhifubaozhifushishou += zhifubaozhifushishou;
			allzhifubaozhifuyingshou += zhifubaozhifuyingshou;
			allzhifubaozhifutuikuan += zhifubaozhifutuikuan;

			allyinlianzhifushishou += yinlianzhifushishou;
			allyinlianzhifuyingshou += yinlianzhifuyingshou;
			allyinlianzhifutuikuan += yinlianzhifutuikuan;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "实收：");
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(weixinzhifuyingshou));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "退款：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(weixinzhifutuikuan));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "入账：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(weixinzhifushishou));
			ii++;

			cell = getCell(sheet, iii, ++ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "实收：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(zhifubaozhifuyingshou));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "退款：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(zhifubaozhifutuikuan));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "入账：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(zhifubaozhifushishou));
			ii++;

			cell = getCell(sheet, iii, ++ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "实收：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(yinlianzhifuyingshou));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "退款：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(yinlianzhifutuikuan));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "入账：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(yinlianzhifushishou));
			ii++;

			cell = getCell(sheet, iii, ++ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "实收：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(hezhifuyingshou));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(hezhifutuikuan));
			ii++;

			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "退款：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, "入账：");
			ii++;
			cell = getCell(sheet, iii, ii);
			cell.setCellStyle(headerStyle);
			setText(cell, dateFormtWx(hezhifushishou));
			ii++;
			iii++;
		}

		iii++;

		cell = getCell(sheet, iii, 0);
		cell.setCellStyle(headerStyle);
		setText(cell, "总计");

		iii++;
		int ii = 0;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "微信支付");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "实收：");
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allweixinzhifuyingshou));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "退款：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allweixinzhifutuikuan));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "入账：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allweixinzhifushishou));
		ii++;
		iii++;

		ii = 0;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "支付宝支付");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "实收：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifubaozhifuyingshou));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "退款：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifubaozhifutuikuan));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "入账：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifubaozhifushishou));
		ii++;
		iii++;

		ii = 0;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "银联支付");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "实收：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allyinlianzhifuyingshou));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "退款：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allyinlianzhifutuikuan));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "入账：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allyinlianzhifushishou));
		ii++;
		iii++;

		ii = 0;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "合计");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "实收：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifuyingshou));
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "退款：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifutuikuan));
		ii++;

		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, "入账：");
		ii++;
		cell = getCell(sheet, iii, ii);
		cell.setCellStyle(headerStyle);
		setText(cell, dateFormtWx(allzhifushishou));
		ii++;
		iii++;

	}

	// 金额格式化 两位小数
	private static String dateFormtWx(double price) {
		DecimalFormat df = new DecimalFormat("#.00");
		if (price == 0) {
			return "0.00";
		} else {
			return df.format(price);
		}
	}
}
