package com.wxsoft.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemUtil {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @描述: 从对象转化成字符串
	 * @作者: kasiaits
	 * @日期:2013-4-2
	 * @修改内容
	 * @参数： @param obj
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	public static String objToStr(Object obj)
	{
		if(obj != null)
			return obj.toString();
		return "";
	}

	/**
	 * 
	 * @描述: 生成邮件发送体
	 * @作者: kasiaits
	 * @日期:2013-4-2
	 * @修改内容
	 * @参数： @param mailTpl
	 * @参数： @param param
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	public static String generateMail(String mailTpl, Map<String, String> param) {
		Set set = param.keySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			StringBuffer sb = new StringBuffer();
			String expression = (String) it.next();
			String expvalue = param.get(expression);
			Pattern p = Pattern.compile(expression); // 正则表达式
			Matcher m = p.matcher(mailTpl); //操作的字符串

			while (m.find()) {
				m.appendReplacement(sb, expvalue);
			}
			m.appendTail(sb); //从截取点将后面的字符串接上
			mailTpl = sb.toString();
		}

		return mailTpl;
	}
}
