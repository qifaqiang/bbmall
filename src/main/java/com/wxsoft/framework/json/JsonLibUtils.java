package com.wxsoft.framework.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.wxsoft.xyd.common.model.User;

public class JsonLibUtils {

	private static SerializeConfig mapping = new SerializeConfig();
	private static String dateFormat;
	static {
		dateFormat = "yyyy-MM-dd HH:mm:ss";
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
	}

	/**
	 * java object convert to json string
	 */
	public static String pojo2json(Object obj) {
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		return JSONObject.toJSONString(obj, mapping).toString();// 可以用toString(1)来实现格式化，便于阅读
	}

	/**
	 * json string convert to javaBean
	 * 
	 * @param <T>
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
		T jsonObj = JSONObject.parseObject(jsonStr, clazz);
		return jsonObj;
	}

	/**
	 * json string convert to map
	 */
	public static Map<String, Object> json2map(String jsonStr) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) JSON.parse(jsonStr);
		return map1;
	}

	/**
	 * json string convert to list
	 * 
	 * @param <T>
	 */
	public static <T> List<T> json2list(String jsonString, Class<T> pojoClass) {
		List<T> jsonArray = JSONArray.parseArray(jsonString, pojoClass);
		return jsonArray;
	}

	public static PropertyFilter filter(final String[] acceptKey) {

		PropertyFilter filter = new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				// 对象为空。直接放行
				if (source == null || value == null) {
					return true;
				}

				if (isHave(acceptKey, name)) {
					return true;
				}
				return false;
			}
		};
		return filter;
	}

	/*
	 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
	 */
	public static boolean isHave(String[] strs, String s) {

		for (int i = 0; i < strs.length; i++) {
			// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
			if (strs[i].equals(s)) {
				// 查找到了就返回真，不在继续查询
				return true;
			}
		}

		// 没找到返回false
		return false;
	}

	// 把list 根据传入所需要的列转化为json
	public static JSONArray listToStringWithConfig(List<?> list,
			String[] acceptColum) {
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(sw);
		serializer.getPropertyFilters().add(filter(acceptColum));
		serializer.write(list);
		String result = sw.toString();
		System.out.println(JSONObject.parseArray(result));
		return JSONObject.parseArray(result);

	}

	// 把Object 根据传入所需要的列转化为json
	public static JSONObject ObjectToStringWithConfig(Object object,
			String[] acceptColum) {
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(sw);
		serializer.getPropertyFilters().add(filter(acceptColum));
		serializer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		serializer.write(object);
		String result = sw.toString();
		System.out.println(result);
		return JSONObject.parseObject(result);

	}

	public static void main(String[] args) {
		List<User> ulist = new ArrayList<User>();
		User u = new User();
		u.setId(5);
		u.setName("55");
		u.setEmail("fsdfsdf");
		ulist.add(u);
		ObjectToStringWithConfig(u, new String[] { "name", "addtime" });
	}

}