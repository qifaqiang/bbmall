package com.wxsoft.framework.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.bean.MailTplBean;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.SysProportion;

/**
 * 
 * @类功能说明：系统基本配置
 * @类修改者：kasiait @修改日期：2013-3-20 @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits @创建时间：2013-3-20 下午03:00:28
 */
@SuppressWarnings({ "unchecked" })
public class BaseConfig {
	/**
	 * 数据库连接对象
	 */
	static JdbcTemplate jdbc;
	/**
	 * 系统基本配置链表
	 */
	public static String FX_DOMAIN_WWW = "http://***/";
	public static String FX_DOMAIN_IMAGE8000 = "http://image.xxx.cn";
	public static String FX_DOMAIN_IMAGE8888 = "http://image.xxx.cn";
	public static String FX_DOMAIN_CDN = "http://image.xxx.cn";
	public static String FX_PIC_PATH = "f:\\MyEclipse\\***";
	public static String FX_FTL_PATH = "f:\\MyEclipse\\***";
	public static String QRCODE = "";

	public static Properties MESSAGE = null;
	public static final String RESCODE = "res_code";
	public static final String RESURL = "res_url";
	public static final String RESMESSAGE = "message";
	public static final String RESLIST = "list";
	public static final String RESDATA = "data";
	public static final String RESTYPE = "type";
	public static final String RESCOUNT = "count";

	public static Map<String, Map<String, String>> ACCESS_TOKEN_COMPANY = new HashMap<String, Map<String, String>>();
	public static Map<String, Map<String, String>> JS_TOKEN_COMPANY = new HashMap<String, Map<String, String>>();
	public static Map<String, Long> ACCESS_TOKEN_TIME = new HashMap<String, Long>();
	public static Map<String, Long> JS_TOKEN_TIME = new HashMap<String, Long>();

	private static Map<String, String> CONFIG_K_V;
	public static SysProportion sysProportion;
	public static Map<Integer, ProductBasic> productBasicMap;

	/** 系统权限模型对应值 */
	private static Map<String, Integer> CONFIG_SYSROLE_DICT = new HashMap<String, Integer>(
			8);

	/** 邮件发送模版 */
	private static Map<String, MailTplBean> MAIL_TEMPLATE_LIST = new HashMap<String, MailTplBean>(
			16);
	/**
	 * 抓取字典配置数据sql
	 */

	/** 系统配置信息 */
	private final static String GET_SYS_CONF_SQL = "";
	public static Map<String, Double> GET_SYS_CONF_SQL_DICT = new HashMap<String, Double>(
			2);
	/**
	 * 类初始化局域代码块
	 */
	static {
		initSystemRoleDict();

	}

	/**
	 * 
	 * @描述: 初始化字典数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param jdbcTemplate @return
	 *      void @throws
	 */
	public static void initCache(JdbcTemplate jdbcTemplate) {
		jdbc = jdbcTemplate;
		// refreshCache();
	}

	//
	// /**
	// *
	// * @描述: 刷新字典数据
	// * @作者: kasiaits
	// * @日期:2013-3-20
	// * @修改内容
	// * @参数：
	// * @return void
	// * @throws
	// */
	// @SuppressWarnings("rawtypes")
	// public static void refreshCache() {
	// final Map<String, String> temp_k_v = new HashMap<String, String>(128);
	// CONFIG_LIST = jdbc.query(GET_CONFIG_SQL, new RowMapper() {
	// public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	// BaseConfigBean baseConfig = new BaseConfigBean(rs
	// .getString("cfgkey"), rs.getString("cfgvalue"), rs
	// .getString("cfgdesc"));
	// temp_k_v.put(baseConfig.getKey(), baseConfig.getValue());
	// return baseConfig;
	// }
	// });
	// CONFIG_K_V = temp_k_v;
	// }

	/**
	 * 获取系统基本配置
	 */
	@SuppressWarnings("rawtypes")
	public static void refreshCache() {
		final Map<String, Double> temp_k_v = new HashMap<String, Double>(2);
		jdbc.query(GET_SYS_CONF_SQL, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				temp_k_v.put("min_withdrawal",
						Double.parseDouble(rs.getString("min_withdrawal")));
				return temp_k_v;
			}
		});
		GET_SYS_CONF_SQL_DICT = temp_k_v;
	}

	/**
	 * 
	 * @描述: 取系统角色信息 @作者: kasiaits @日期:2013-4-1 @修改内容 @参数： @return void @throws
	 */
	public static void initSystemRoleDict() {
		CONFIG_SYSROLE_DICT.put("front", 1);
		CONFIG_SYSROLE_DICT.put("customer", 4);
		CONFIG_SYSROLE_DICT.put("system", 16);
	}

	/**
	 * 
	 * @描述: 获取邮件模版 @作者: kasiaits @日期:2013-4-3 @修改内容 @参数： @param mailcode @参数： @return @return
	 *      MailTplBean @throws
	 */
	public static MailTplBean getMailTplByCode(String mailcode) {
		if (MAIL_TEMPLATE_LIST.size() != 0)
			return MAIL_TEMPLATE_LIST.get(mailcode);
		return null;
	}

	/**
	 * 
	 * @描述: 返回系统角色 @作者: kasiaits @日期:2013-4-1 @修改内容 @参数： @return @return Map
	 *      <String,Integer> @throws
	 */
	public static Map<String, Integer> getSysRole() {
		return CONFIG_SYSROLE_DICT;
	}

	/**
	 * 
	 * @描述: 获取字典字符串数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param key @参数： @return @return
	 *      String @throws
	 */
	public static String getString(String key) {
		checkBaseList();
		return CONFIG_K_V.get(key);
	}

	/**
	 * 
	 * @描述: 获取字典整形数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param key @参数： @return @return
	 *      Integer @throws
	 */
	public static Integer getInteger(String key) {
		String value = getString(key);
		return value == null ? null : Integer.valueOf(value);
	}

	/**
	 * 
	 * @描述: 获取字典长整形字典数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param key @参数： @return @return
	 *      Long @throws
	 */
	public static Long getLong(String key) {
		String value = getString(key);
		return value == null ? null : Long.valueOf(value);
	}

	/**
	 * 
	 * @描述: 获取字典浮点型字典数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param key @参数： @return @return
	 *      Double @throws
	 */
	public static Double getDouble(String key) {
		String value = getString(key);
		return value == null ? null : Double.valueOf(value);
	}

	/**
	 * 
	 * @描述: 获取字典json字典数据 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @param key @参数： @return @return
	 *      JSONObject @throws
	 */
	public static JSONObject getJSON(String key) {
		String value = getString(key);
		return value == null ? null : JSONObject.parseObject(value);
	}

	/**
	 * 
	 * @描述: 校验字典数据是否存在 @作者: kasiaits @日期:2013-3-20 @修改内容 @参数： @return void @throws
	 */
	private static void checkBaseList() {
		if (GET_SYS_CONF_SQL_DICT == null)
			throw new RuntimeException(
					"base config init error,config list is null");
	}

	public static ResultModel CheckReslut(ResultModel rm) {
		if (null == rm) {
			rm = new ResultModel("友情提示", "保存失败", "error", "");
		}
		return rm;
	}

}
