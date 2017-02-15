/**   
 * @文件名称: BaseController.java
 * @类路径: com.wxsoft.framework.controller
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-15 上午10:07:47  
 */

package com.wxsoft.framework.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.util.ExceptionProcessUtils;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysSmsCheck;
import com.wxsoft.xyd.common.service.SysSmsCheckService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.SysLog;

/**
 * @类功能说明：controller基类
 * @类修改者：kasiait
 * @修改日期：2013-3-15
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-15 上午10:07:47
 */

public class BaseController {
	@Autowired
	private SysSmsCheckService sysSmsCheckService;
	public final static String SYS_DEFAULT_CHARSET = "utf-8";
	public final static String SYS_RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";
	public final static String SYS_RESPONSE_CONTENT_JSON_TYPE = "application/json;charset=UTF-8";
	public final static byte[] EMPTY_BYTES = new byte[0];

	/**
	 * response二进制数据
	 * 
	 * @param bytes
	 * @param response
	 */
	protected void responseByte(byte[] bytes, HttpServletResponse response) {
		try {
			if (bytes == null || bytes.length == 0)
				return;
			response.setContentType(SYS_RESPONSE_CONTENT_TYPE);
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
		}
	}

	/**
	 * 输出字符集设置
	 * 
	 * @param string
	 *            输出字符串
	 * @param response
	 *            输出对象
	 * @throws UnsupportedEncodingException
	 *             编码异常输出
	 */
	protected void responseString(String string, HttpServletResponse response)
			throws UnsupportedEncodingException {
		if (string == null)
			responseByte(EMPTY_BYTES, response);
		else
			responseByte(string.getBytes(SYS_DEFAULT_CHARSET), response);
	}

	/**
	 * 反馈没有这个这个Event
	 * 
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	protected void responseNoEvent(HttpServletResponse response)
			throws UnsupportedEncodingException {
		responseAjax(getNoEventResult(), response);
	}

	/**
	 * 输出Ajax请求
	 * 
	 * @param object
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	protected void responseAjax(Object object, HttpServletResponse response)
			throws UnsupportedEncodingException {
		if (object == null)
			responseByte(EMPTY_BYTES, response);
		else if (object instanceof JSONObject || object instanceof JSONArray)
			responseAjax(object.toString(), response);
		else if (object instanceof Map)
			responseString(JSON.toJSONString(object), response);
		else if (object instanceof List)
			responseString(JSON.toJSONString(object), response);
		else if (object instanceof String)
			responseString((String) object, response);
		else if (object instanceof Throwable)
			responseString(ExceptionProcessUtils.getStack((Throwable) object),
					response);
		else
			responseString(JSON.toJSONString(object), response);
	}

	/**
	 * 获取一个通用result
	 * 
	 * @param result
	 * @param isSuccess
	 * @param count
	 * @return
	 */
	protected JSONObject getResult(Object result, boolean isSuccess,
			Integer count) {
		return isSuccess ? getSuccessResult(result, count) : getFailureResult(
				result, count);
	}

	/**
	 * 获取一个通用result
	 * 
	 * @param result
	 * @param isSuccess
	 * @return
	 */
	protected JSONObject getResult(Object result, boolean isSuccess) {
		return isSuccess ? getSuccessResult(result) : getFailureResult(result);
	}

	/**
	 * 重载方法
	 * 
	 * @param result
	 * @param count
	 * @return
	 */
	protected JSONObject getSuccessResult(Object result, Integer count) {
		JSONObject jsonObject = getSuccessResult(result);
		jsonObject.put("count", count);
		return jsonObject;
	}

	/**
	 * 获取成功的Result
	 * 
	 * @param result
	 * @return
	 */
	protected JSONObject getSuccessResult(Object result) {
		JSONObject object = createResult(true);
		object.put("root", result);
		return object;
	}

	/**
	 * 获取失败的Reuslt
	 * 
	 * @param result
	 * @return
	 */
	protected JSONObject getFailureResult(Object result) {
		JSONObject object = createResult(false);
		object.put("root", result);
		return object;
	}

	/**
	 * 获取失败的Reuslt
	 * 
	 * @param result
	 * @param count
	 * @return
	 */
	protected JSONObject getFailureResult(Object result, Integer count) {
		JSONObject jsonObject = getFailureResult(result);
		jsonObject.put("count", count);
		return jsonObject;
	}

	/**
	 * 无相关event的结果
	 * 
	 * @return
	 */
	protected JSONObject getNoEventResult() {
		return getFailureResult("请求的参数无效，被服务器端拒绝");
	}

	/**
	 * 根据参数得到一个返回值
	 * 
	 * @param isSuccess
	 * @return
	 */
	private JSONObject createResult(boolean isSuccess) {
		JSONObject object = new JSONObject();
		if (isSuccess) {
			object.put("success", true);
			object.put("failure", false);
		} else {
			object.put("success", false);
			object.put("failure", true);
		}
		return object;
	}

	/**
	 * deode encoded params, return null , if string is blank.
	 * 
	 * @param str
	 * @return
	 */
	public String decode(String str) {
		String result = null;
		if (StringUtils.isNotBlank(str)) {
			try {
				result = URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述: java转义html form表单提交的数据
	 * @作者: kasiaits
	 * @日期:2013-3-22
	 * @修改内容
	 * @参数： @param str
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	public String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 
	 * @描述: 验证是否为空或""
	 * @作者: like
	 * @日期:2015-7-17
	 * @修改内容
	 * @参数： @param str
	 * @参数： @return
	 * @return Boolean
	 * @throws
	 */
	public Boolean validateString(String str) {
		if (str == null || "".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	public String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookie = request.getCookies();
		for (int i = 0; i < cookie.length; i++) {
			Cookie cook = cookie[i];
			if (cook.getName().equalsIgnoreCase(key)) { // 获取键
				System.out.println("account:" + cook.getValue().toString()); // 获取值
				return cook.getValue().toString();
			}
		}
		return null;
	}

	public SysLog getSysLog(HttpServletRequest request, String detail) {
		SysLog sl = new SysLog();
		Company company = (Company) request.getSession().getAttribute(
				SystemConfig.SESSION_USER);
		sl.setCreatetime(new Date());
		sl.setIp(Tools.getRemortIP(request));
		sl.setUserId(company.getCompanyId());
		sl.setUserName(company.getCompanyName());
		sl.setDetail(detail);
		return sl;
	}

	public JSONObject phoneValidate(SysSmsCheck ssc) {
		JSONObject json = new JSONObject();

		SysSmsCheck selectSsc = new SysSmsCheck();
		selectSsc.setMobile(ssc.getMobile());
		SysSmsCheck sysSmsCheck = sysSmsCheckService
				.selectBySysSmsCheck(selectSsc);
		if (sysSmsCheck == null) {
			// 没有查询到当前用户的验证码信息
			json.put(BaseConfig.RESCODE, "ER1017");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1017").toString());
		} else {
			long addtime = sysSmsCheck.getAddtime().getTime();
			long newtime = new Date().getTime();
			if (!sysSmsCheck.getCode().equals(ssc.getCode())) {
				// 手机验证码不正确
				json.put(BaseConfig.RESCODE, "ER1018");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1018").toString());
			} else if ((addtime + SystemConfig.SMS_TIMEOUT) < newtime) {
				// 手机验证码超时
				json.put(BaseConfig.RESCODE, "ER1019");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1019").toString());
			} else {
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "");
			}
		}
		return json;
	}
}
