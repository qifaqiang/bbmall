package com.wxsoft.framework.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.wxsoft.framework.bean.JsonBean;

/**
 * 
 * @类功能说明： JSON格式数据转换
 * @类修改者：kasiait
 * @修改日期：2013-4-2
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-4-2 下午12:05:43
 */
public class JSONUtil {

	public static final String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss" };

	public static final String dataFormat = "yyyy-MM-dd HH:mm:ss";
	private final static String SUCCESS_STATUS = "success";
	/** 分页json中的key */
	private final static String DATA_TOTAL = "total";
	/** 分页json中的key */
	private final static String DATA_ROWS = "rows";

	/**
	 * 
	 * @描述: 获取分页json信息
	 * @作者: kasiaits
	 * @日期:2013-4-2
	 * @修改内容
	 * @参数： @param list
	 * @参数： @param count
	 * @参数： @return
	 * @return JSONObject
	 * @throws
	 */
	public static JSONObject getPageJSON(List<? extends Object> list, int count) {
		JSONObject object = new JSONObject();
		object.put(SUCCESS_STATUS, true);
		object.put(DATA_TOTAL, count);
		object.put(DATA_ROWS, list);
		return object;
	}


	/**
	 * 
	 * @描述: Java对象转换JSON格式
	 * @作者: kasiaits
	 * @日期:2013-4-2
	 * @修改内容
	 * @参数： @param obj
	 * @参数： @param jsonConfig
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@SuppressWarnings("static-access")
	public static String beanToJSONAll(Object obj,
			SimplePropertyPreFilter jsonConfig) {
		String json = null;
		JSONObject jsonObject = new JSONObject();
		json = jsonObject.toJSONString(obj, jsonConfig).toString();
		return json;
	}

	/**
	 * json to map
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.parseObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<Object> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = (JSONObject) it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, Object> maps = parseJSON2Map("{\"subscribe\":1,\"openid\":\"oofRSs9a2nt3LH6YXrrIxuInjkO0\",\"nickname\":\"匡亚洲\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"\",\"province\":\"\",\"country\":\"马其顿\",\"headimgurl\":\"http://wx.qlogo.cn/mmopen/KetjXWSVppvhdZyqgcGMcLelZ1HAZeEraUHjJf9tPLnakFzQTKGxC8sRYMhLIcX9ZW8VBSHqoibHtH4IK35Z6lw/0\",\"subscribe_time\":1432719503,\"remark\":\"\",\"groupid\":0}");
		System.out.println(maps.get("sex"));
	}
}
