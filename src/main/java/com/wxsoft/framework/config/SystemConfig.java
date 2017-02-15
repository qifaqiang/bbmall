/**   
 * @文件名称: FXSHOP.java
 * @类路径: com.wxsoft.framework.config
 * @描述: 系统常量说明
 * @作者：kasiaits
 * @时间：2013-3-7 上午11:12:10  
 */

package com.wxsoft.framework.config;

/**
 * @类功能说明：常量说明
 * @类修改者：kasiait
 * @修改日期：2013-3-7 @修改说明： @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-7 上午11:12:10
 */

public interface SystemConfig {

	/** Controller注解命名空间字段 */
	/** 系统首页命名空间注解字段 */
	public final static String FXSHOP_ANNTATION_DEFAULT = "/";
	/** 前端展示命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_FRONT = "/front";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM = "/system";
	/** 管理端命名空间注解字段 */
	public final static String WEBSERVICE = "/webservice";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM_RESOURCE = "/system/topic";
	/** 管理端命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_SYSTEM_MENUS = "/menus";
	/** 用户个人命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_USER_PROFILE = "/user/profile";
	/** 文本编辑器命名空间注解字段 */
	public final static String FXSHOP_ANNOTATION_NAMESPACE_EDITOR = "/kindeditor";

	/** front登陆的公司id */
	public final static String COMPANY_ID = "company_idk";
	/** Access注解命名空间字段 */
	/** 前端展示命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_USER = "customer";
	/** 管理端命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_SYSTEM = "system";
	public final static String FXSHOP_ACCESS_ANNOTATION_INTERFACES = "interfaces";
	/** 用户个人命名空间权限注解字段 */
	public final static String FXSHOP_ACCESS_ANNOTATION_USER_PROFILE = "customer";

	/** 发送邮件用户名和密码配置信息 */
	public final static String FXSHOP_EMAIL_PROPERTIES_HOST = "smtp.163.com";
	public final static String FXSHOP_EMAIL_PROPERTIES_FROM = "xxxx@163.com";
	public final static String FXSHOP_EMAIL_PROPERTIES_USERNAME = "";
	public final static String FXSHOP_EMAIL_PROPERTIES_USERPASSWORD = "";

	/** Session中变量声明 **/
	/** 上一引用页面从session中获取的变量 */
	public final static String FXSHOP_SESSION_REFERRER_PAGE_URL = "referrer";
	/** 存放在SESSION里面的验证码 */
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	/** 存放在SESSION里面的前台登陆用户 */
	public static final String SESSION_FRONT_USER = "sessionFrontUser";
	public static final String SESSION_FRONT_WX_USER = "sessionFrontWxUser";
	/** 存放在SESSION里面的登陆用户 */
	public static final String SESSION_USER = "sessionUser";
	/** 存放在SESSION里面的登陆用户角色Id */
	public static final String SESSION_USER_ROLE_ID = "sessionRoleId";
	/** 存放在SESSION里面的登陆用户角色名称 */
	public static final String SESSION_USER_ROLE_CODE = "sessionRoleCode";
	/** 存放在SESSION里面的登陆用户角色的url */
	public static final String SESSION_USER_ROLE_URL = "sessionRoleURL";
	/** 存放在SESSION里面的登陆用户角色名称 */
	public static final String SESSION_USER_ROLE_NAME = "sessionRoleName";
	/** 存放文档管理的menu_id */
	public static final Integer WEN_DANG_GUAN_LI = 5;
	/** 存放预约管理的menu_id */
	public static final Integer YU_YUE_GUAN_LI = 7;
	/** 存放企业品牌站的menu_id */
	public static final Integer QI_YE_PING_PAI_ZHAN = 1;

	/** 处理文件上传排除关键字 */
	public final static String FXSHOP_FRAMEWORK_FILEUPLOAD_FILTER = "jsonfile";

	/** jsp viewresolver 目录字段 */
	/** 管理员目录 **/
	public final static String SYSTEM_PATH_ADMIN = "/system";
	public final static String SYSTEM_PATH_ADMIN_PRODUCT = "/system/product";
	/** 用户属性空间 **/
	public final static String SYSTEM_PATH_USER = "/user";
	/** 网站前台页面 **/
	public final static String SYSTEM_PATH_FRONT = "/front";
	public final static String SYSTEM_PATH_FRONT_WAP = "/wap/";

	/** 角色目录 **/
	public final static String SYSTEM_PATH_ROLE = "/role";
	/** 订单目录 **/
	public final static String SYSTEM_PATH_ORDER = "/order";

	/** Paypal付款页面 */
	public final static String SYSTEM_PATH_PAYPAL = "/front/paypal";

	/** 基于url拦截器的匹配正则表达式 */
	public static final String NO_INTERCEPTOR_PATH = ".([^system])*/((login) | (logout) | (code) |(index)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String INTERCEPTOR_SYSTEM_PATH = "/system/[^login].*";

	/** JSON数据key */
	public static final String FXSHOP_JSON_LOGIN_FASLE_MESSAGE = "json:";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_RETCODE = "retCode";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_SUCCESS_CODE = "0";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_FAIL_CODE = "-1";
	/** 鉴权失败信息返回码 */
	public static final String FXSHOP_JSON_INTERCEPTER_RET_MESSAGE = "retMsg";

	/** SysMenu返回码 */
	public static final String SysMenu = "SysMenu";

	/** ROLE返回码 */
	public static final String ROLEWIXIN = "ROLEWIXIN";

	/** ROLE返回码 */
	public static final String SECOND_ROLEWIXIN = "SECONDROLEWIXIN";

	public static final String SYSTEM_PATH_WX = "/wx";
	public static final String SYSTEM_PATH_SHOW = "/show";
	public static final String SYSTEM_REFERER = "SYSTEM_REFERER";
	public static final String SYSTEM_PATH_WX_USER = "/wxuser";
	/** ROLE返回码 */
	public static final String WEIXINSYS_DEL_SUB_MENU = "delete_sub_menu";
	public static final String AES_MICHI = "eyegongyis";
	public static final String DES_MICHI = "eyegongy111isdes";
	public static final String AES_XIANGLIANG = "wxxinskey111";

	/** 信鸽推送相关 */
	public static final long IOS_ACCESS_ID = 2200129412l;
	public static final String IOS_ACCESS_KEY = "IT6I2G84F9DS";
	public static final String IOS_SECRET_KEY = "0733db60b999342b9733702b6f15be1a";

	public static final long ANDROID_ACCESS_ID = 2100129471l;
	public static final String ANDROID_ACCESS_KEY = "A6T7V44JDS1H";
	public static final String ANDROID_SECRET_KEY = "ac67701694fd44b253dfb29a40079564";

	/**微信支付*/
	public static final String APPID = "wx19f84a5ebf6ad6e5";
	public static final String APPSECRET = "ef45325365550dfe9fc037bd7b87de0e";
	public static final String PARTNERKEY = "wanglizheng15820000977jinshanlin";
	public static final String PARTNER = "1278776601";
	public static final String GH = "gh_b244502adde3";

	/**银联支付*/
	public static final String UNION_MID = "777290058129941";
	
	
	/**支付宝*/
	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088221648515293";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "wj52unc8nsd05y0370i2p8pkuvdmro4k";

	
	/**
	 * 短信接口
	 */
	public static final String SMS_USER = "xianyida";
	public static final String SMS_PASSWD = "xianyida123";
	/**
	 * 短信超时时间
	 */
	public static final long SMS_TIMEOUT = 1000 * 60 * 5;// 5分钟
}
