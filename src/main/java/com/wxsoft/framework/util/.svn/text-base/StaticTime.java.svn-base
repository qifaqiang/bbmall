package com.wxsoft.framework.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class StaticTime {
	/**
	 * 获取最近7天的开始时间和结束时间
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getLastSevenDays() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -7);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("startDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));

		calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("endDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		return map;
	}

	/**
	 * 获取昨天的开始日期和结束日期
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getYesterDays() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -2);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("startDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		map.put("endDate", Tools.date2Str(new Date(), "yyyy-MM-dd")
				+ " 00:00:00");
		return map;
	}

	/**
	 * 获取最近一月的开始日期和结束日期
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getLastMonthDays() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -30);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("startDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));

		calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("endDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		return map;
	}

	/**
	 * 获取今天的开始日期和结束日期
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getTodayDays() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("startDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));

		calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		map.put("endDate", Tools.date2Str(calendar.getTime(), "yyyy-MM-dd"));
		return map;
	}

}
