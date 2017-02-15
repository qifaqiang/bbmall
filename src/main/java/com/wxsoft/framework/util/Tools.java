package com.wxsoft.framework.util;

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemDef;
import com.wxsoft.xyd.system.web.KindeditorController;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	public static String getRandomCharAndNumr(Integer length) {
		return RandomStringUtils.random(length, true, true);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符型日期转化util.Date型日期
	 * 
	 * @param p_strDate
	 *            字符型日期
	 * @param p_format
	 *            格式:"yyyy-MM-dd" / "yyyy-MM-dd hh:mm:ss"
	 * @return java.util.Date util.Date型日期
	 * @throws ParseException
	 */
	public static java.util.Date toUtilDateFromStrDateByFormat(
			String p_strDate, String p_format) throws ParseException {
		java.util.Date l_date = null;
		java.text.DateFormat df = new java.text.SimpleDateFormat(p_format);
		if (p_strDate != null && (!"".equals(p_strDate)) && p_format != null
				&& (!"".equals(p_format))) {
			l_date = df.parse(p_strDate);
		}
		return l_date;
	}

	/**
	 * 获取指定日期的毫秒
	 * 
	 * @param p_date
	 *            util.Date日期
	 * @return long 毫秒
	 */
	public static long getMillisOfDate(java.util.Date p_date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(p_date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate)
			throws ParseException {

		Date l_startDate = toUtilDateFromStrDateByFormat(p_startDate,
				"yyyy-MM-dd");
		Date l_endDate = toUtilDateFromStrDateByFormat(p_endDate, "yyyy-MM-dd");
		long l_startTime = getMillisOfDate(l_startDate);
		long l_endTime = getMillisOfDate(l_endDate);
		long betweenDays = (l_endTime - l_startTime) / (1000 * 60 * 60 * 24);
		return betweenDays;
	}

	/**
	 * 
	 * @描述: 校验上传文件后缀名是否合法 @作者: kasiaits @日期:2013-3-29 @修改内容 @参数： @param fileName
	 * @参数： @return @return boolean @throws
	 */
	public static boolean checkUploadFile(String fileName) {
		String suffix = getFileSuffix(fileName);

		if (SystemDef.fileSuffix.contains(suffix) && checkMagicNumber(fileName))
			return true;

		return false;
	}

	/**
	 * 
	 * @描述: 检查上传文件头是否合法 @作者: kasiaits @日期:2013-3-29 @修改内容 @参数： @param fileName
	 * @参数： @return @return boolean @throws
	 */
	public static boolean checkMagicNumber(String fileName) {
		byte[] magic = getMagicNumber(fileName);

		if (magic == null)
			return false;

		String magicStr = bytesToHexString(magic);
		if (SystemDef.fileMagicNumber.contains(magicStr.toLowerCase()))
			return true;
		return false;
	}

	/**
	 * 
	 * @描述: 获取文件扩展名称 @作者: kasiaits @日期:2013-3-28 @修改内容 @参数： @param fileName @参数： @return @return
	 *      String @throws
	 */
	public static String getFileSuffix(String fileName) {
		String extention = "";
		if (fileName.length() > 0 && fileName != null) { // --截取文件名
			int i = fileName.lastIndexOf(".");
			if (i > -1 && i < fileName.length()) {
				extention = fileName.substring(i + 1); // --扩展名
			}
		}

		return extention;
	}

	/**
	 * 
	 * @描述: 字节数组转化成字符串 @作者: kasiaits @日期:2013-3-29 @修改内容 @参数： @param src @参数： @return @return
	 *      String @throws
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @描述: 获取文件头内容 @作者: kasiaits @日期:2013-3-29 @修改内容 @参数： @param fileName @参数： @return @return
	 *      byte[] @throws
	 */
	public static byte[] getMagicNumber(String fileName) {
		try {
			FileInputStream is = new FileInputStream(fileName);
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			is.close();
			return b;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getAllDayByMonth(int maxDay) {
		List<String> result = new ArrayList<String>();
		for (int i = 1; i < maxDay + 1; i++) {
			if (i < 10) {
				result.add("0" + i);
			} else {
				result.add(String.valueOf(i));
			}

		}
		return result;
	}

	/**
	 * 获取可变日期内的所有天数
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getVariableDaysList(String startDate,
			String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -checkday);// 把日期往后增加一天.整数往后推,负数往前移动
		if (checkday > 31) {
			for (int i = 0; i < checkday; i = i + 2) {
				calendar.add(Calendar.DATE, 2);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
			if (checkday % 2 == 0) {
				calendar.add(Calendar.DATE, 2);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
		} else {
			for (int i = 0; i < checkday; i = i + 1) {
				calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
			}
		}

		return list;
	}

	/**
	 * 获取可变日期内的所有天数
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static List<String> getVariableDaysListUserJishu(String startDate,
			String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		Calendar calendar = new GregorianCalendar();
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
			calendar.setTime(formatter.parse(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < checkday; i = i + 1) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}

		return list;
	}

	/**
	 * 获取可变日期内的所有天数
	 * 
	 * @return
	 */
	public static List<String> getVariableDays(String startDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0; // 开始结束相差天数
		Calendar calendar = new GregorianCalendar();
		try {
			checkday = (int) ((formatter.parse(endDate).getTime() - formatter
					.parse(startDate).getTime()) / (1000 * 24 * 60 * 60));
			calendar.setTime(formatter.parse(startDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < checkday - 1; i = i + 1) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}

		return list;
	}

	/**
	 * 返回最近7天内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getLastSevenDaysList() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -8);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		}
		return list;
	}

	/**
	 * 返回最近7天内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getLastSevenDaysListNoYears() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -8);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	/**
	 * 返回指定月份内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getMonthDaysList(String time) {
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(time.substring(5, 7)) - 1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= maxDay; i++) {
			list.add(time + "-" + (i < 10 ? "0" + i : i));
		}
		return list;
	}

	/**
	 * 返回指定月份开始和结束
	 * 
	 * @return
	 */
	public static List<String> getMonthDaysStartEnd(String time) {
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Tools.str2Date(time + "-01 00:00:00"));
		calendar.add(Calendar.MONTH, 1);
		list.add(time + "-01");
		list.add(Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		return list;
	}

	/**
	 * 返回指定月份内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getMonthDaysListNoYears(String time) {
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		String tempMonth = time.substring(5, 7);
		calendar.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(time.substring(5, 7)) - 1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= maxDay; i++) {
			list.add(tempMonth + "-" + (i < 10 ? "0" + i : i));
		}
		return list;
	}

	/**
	 * 获取最近一个月内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getLastMonthDaysList() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		}
		return list;
	}

	/**
	 * 获取最近一个月内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getLastMonthDaysListNoYears() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(getMonthDaysList("2016-01"));
	}

	/**
	 * 获取最近一个月内的所有日期
	 * 
	 * @return
	 */
	public static List<String> getLastMonthDaysListUserJishu() {
		List<String> list = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		for (int i = 0; i < 30; i = i + 1) {
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			list.add(Tools.date2Str(calendar.getTime(), "MM-dd"));
		}
		return list;
	}

	public static List<String> get24Hours() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				list.add("0" + String.valueOf(i) + ":00");
			} else {
				list.add(String.valueOf(i) + ":00");
			}
		}
		return list;

	}

	/**
	 * 判断给定时间在否在给定两个时间之前
	 */
	public static boolean do7(String star, String end) {
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		String daynow = day.format(new Date());
		SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date sdate = localTime.parse(daynow + " " + star);
			Date edate = localTime.parse(daynow + " " + end);
			long time = System.currentTimeMillis();
			if (time >= sdate.getTime() && time <= edate.getTime()) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 上传图片
	 * 
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpic(MultipartFile myfile, String picdir) {
		String saveUrl = "/attached/";
		String originalFilename = myfile.getOriginalFilename();
		Map<String, String> maps = KindeditorController.mkdir(saveUrl, picdir);
		String endName = originalFilename.substring(
				originalFilename.lastIndexOf("."), originalFilename.length());
		String filename = UUID.randomUUID() + endName;
		if (endName.equals(".png") || endName.equals(".gif")
				|| endName.equals(".jpg") || endName.equals(".jpeg")) {
			try {
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(maps.get("savePath"), filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return maps.get("saveUrl") + filename;
		} else if (endName.equals(".log")) {
			try {
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(maps.get("savePath"), originalFilename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 上传图片
	 * 
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpicByRectangle(MultipartFile myfile,
			String picdir, Integer[] widthTemp, Integer[] heightTemp) {
		String saveUrl = "/attached/";
		String originalFilename = myfile.getOriginalFilename();
		Map<String, String> maps = KindeditorController.mkdir(saveUrl, picdir);
		String endName = originalFilename.substring(
				originalFilename.lastIndexOf("."), originalFilename.length());
		String filename = GenerateNo.payRecordNo() + endName;
		if (endName.equals(".png") || endName.equals(".gif")
				|| endName.equals(".jpg") || endName.equals(".jpeg")) {
			for (int i = 0; i < widthTemp.length; i++) {
				try {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							new File(maps.get("savePath"), filename));
					BufferedImage sourceImg = ImageIO.read(myfile
							.getInputStream());
					Double height = (double) sourceImg.getHeight();
					Double width = (double) sourceImg.getWidth();
					if (width > height) {
						// if (height > 300) {
						height = height * (widthTemp[i] / width);
						width = widthTemp[i] * 1.0;
						// }
					} else {
						// if (width > 300) {
						width = width * (heightTemp[i] / height);
						height = heightTemp[i] * 1.0;
						// }
					}
					String imgpath = BaseConfig.FX_PIC_PATH
							+ maps.get("saveUrl") + filename;
					int quality = 85;
					ThumbNailHelper.createThumbnailByRectangle(imgpath,
							width.intValue(), height.intValue(), quality,
							imgpath, widthTemp[i], heightTemp[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return maps.get("saveUrl") + filename;
		} else {
			return null;
		}
	}

	/**
	 * 上传图片
	 * 
	 * @param myfile
	 * @param picdir
	 * @return
	 */
	public static String uploadpicByShow(MultipartFile myfile, String picdir,
			Integer... edges) {
		String saveUrl = "/attached/";
		String originalFilename = myfile.getOriginalFilename();
		Map<String, String> maps = KindeditorController.mkdir(saveUrl, picdir);
		String endName = originalFilename.substring(
				originalFilename.lastIndexOf("."), originalFilename.length());
		String filename = GenerateNo.payRecordNo() + endName;
		for (Integer edge : edges) {
			try {
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(maps.get("savePath"), filename));
				BufferedImage sourceImg = ImageIO.read(myfile.getInputStream());
				Double height = (double) sourceImg.getHeight();
				Double width = (double) sourceImg.getWidth();
				if (width > height) {
					// if (height > 300) {
					height = height * (edge / width);
					width = edge * 1.0;
					// }
				} else {
					// if (width > 300) {
					width = width * (edge / height);
					height = edge * 1.0;
					// }
				}
				String imgpath = BaseConfig.FX_PIC_PATH + maps.get("saveUrl")
						+ filename;
				int quality = 85;
				ThumbNailHelper.createThumbnailByPShow(imgpath,
						width.intValue(), height.intValue(), quality, imgpath,
						edge);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return maps.get("saveUrl") + filename;

	}

	/**
	 * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void moveFile(String resFilePath, String distFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		if (resFile.isDirectory()) {
			FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
		} else if (resFile.isFile()) {
			FileUtils.moveFile(resFile, distFile);
		}
	}

	/**
	 * 复制
	 * 
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void cpFile(String resFilePath, String distFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		FileUtils.copyFile(resFile, distFile);
	}

	public static String imgZuhe(String imgurl, String size) {
		String fisrt = imgurl.substring(0, imgurl.indexOf('.'));
		String end = imgurl.substring(imgurl.indexOf('.'), imgurl.length());
		return fisrt + size + end;
	}

	/**
	 * 刪除
	 * 
	 * @param targetPath
	 * @throws IOException
	 */
	public static void deleteFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		if (targetFile.isDirectory()) {
			FileUtils.deleteDirectory(targetFile);
		} else if (targetFile.isFile()) {
			targetFile.delete();
		}
	}

	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	public static Date getdate(int i) // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
	{
		Date dat = null;
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, i);
		dat = cd.getTime();
		return dat;
	}

	public static Map<String, Object> ListToMap(List<Map<String, Object>> list,
			String key1, String key2) {
		Map<String, Object> mo = new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			mo.put(null != map.get(key1) ? map.get(key1).toString() : "0",
					null != map.get(key2) ? map.get(key2) : "0");
		}
		return mo;
	}

	public static LinkedHashMap<String, Object> ListToLinkedHashMap(
			List<Map<String, Object>> list, String key1, String key2,
			String tempStart) {
		LinkedHashMap<String, Object> mo = new LinkedHashMap<String, Object>();
		for (Map<String, Object> map : list) {
			mo.put((null != map.get(key1) ? tempStart
					+ map.get(key1).toString() : tempStart + "0"),
					null != map.get(key2) ? map.get(key2) : "0");
		}
		return mo;
	}

	public static Map<String, LinkedHashMap<String, Integer>> ListToMapString(
			List<Map<String, Object>> list, String key1, String key2,
			LinkedHashMap<String, Integer> dateListMap) {
		Map<String, LinkedHashMap<String, Integer>> mo = new HashMap<String, LinkedHashMap<String, Integer>>();
		for (Map<String, Object> map : list) {
			String tempCompanyId = null != map.get(key1) ? String.valueOf(map
					.get(key1)) : "0";
			if (null != mo.get(tempCompanyId)) {
				String key2Temp = String.valueOf(map.get(key2))
						.substring(0, 10);
				LinkedHashMap<String, Integer> temp = mo.get(tempCompanyId);
				Integer tempCount = temp.get(key2Temp);
				if (null != tempCount) {
					temp.put(key2Temp, temp.get(key2Temp) + 1);
				} else {
					temp.put(key2Temp, 1);
				}
				mo.put(tempCompanyId, temp);
			} else {
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Integer> temp = (LinkedHashMap<String, Integer>) dateListMap
						.clone();
				String key2Temp = String.valueOf(map.get(key2))
						.substring(0, 10);
				temp.put(key2Temp, 1);
				mo.put(tempCompanyId, temp);
			}
		}
		return mo;
	}

	// map value 排序
	public static Map sortMap(Map oldMap) {
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				oldMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}
		});
		LinkedHashMap newMap = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			newMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return newMap;
	}

	/**
	 * * 转换图片 * *
	 */
	public static void changeImge(File img) {
		try {
			Image image = ImageIO.read(img);
			int srcH = image.getHeight(null);
			int srcW = image.getWidth(null);
			BufferedImage bufferedImage = new BufferedImage(srcW, srcH,
					BufferedImage.TYPE_3BYTE_BGR);
			bufferedImage.getGraphics()
					.drawImage(image, 0, 0, srcW, srcH, null);
			bufferedImage = new ColorConvertOp(
					ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(
					bufferedImage, null);
			FileOutputStream fos = new FileOutputStream(img);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(bufferedImage);
			fos.close();
			// System.out.println("转换成功...");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("图片转换出错！", e);
		}
	}

	public static void grayImage(File file) throws IOException {
		// System.out.println(file);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}
		ImageIO.write(grayImage, "jpg", file);
	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				System.out.println("ServletUtil类247行  temp数据的键==" + en
						+ "     值===" + value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
