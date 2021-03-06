/**   
 * @文件名称: .java
 * @类路径: com.wxsoft.framework.interceptor
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-12 下午01:14:45  
 */

package com.wxsoft.framework.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.IMethodIntercepterHolder;
import org.springframework.web.servlet.handler.IMethodInvokerIntercepter;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.util.HTTPSClient;
import com.wxsoft.framework.util.Md5Encrypt;
import com.wxsoft.framework.util.SHA1;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.service.UserService;

/**
 * @类功能说明：方法级拦截器使用注解实现
 * @类修改者：kasiait
 * @修改日期：2013-3-12
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * 
 * @创建时间：2013-3-12 下午01:14:45
 */
public class FxShopMethodPrivilegeIntercepter implements
		IMethodInvokerIntercepter {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Override
	public Object invokeHandlerMethod(Method handlerMethod, Object handler,
			HttpServletRequest request, HttpServletResponse response,
			Model model, IMethodIntercepterHolder chain) throws Exception {

		// User user=new User();
		// user.setId(1);
		// user.setMobile("13255681878");
		// user.setLogin("13255681878");
		// request.getSession().setAttribute(SystemConfig.SESSION_FRONT_USER,
		// user);
		logger.debug("---->handler:=" + handler);
		logger.debug("---->handlerMethod:=" + handlerMethod);
		logger.debug("---->method:=" + request.getMethod());
		logger.debug("---->uri:=" + request.getRequestURI());

		String strBackUrl = "http://" + request.getServerName() // 服务器地址
				+ request.getContextPath() // 项目名称
				+ request.getServletPath();// 请求页面或其他地址

		if (null != request.getQueryString()) {
			strBackUrl += "?" + (request.getQueryString());
		}

		// jsAPI
		if (request.getServletPath().contains("/wap")
				|| request.getServletPath().contains("/front")) {

			if (null == request.getSession().getAttribute(
					SystemConfig.SESSION_FRONT_USER)) {
				// 自动登陆
				Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
				String loginName = "";
				String token = "";
				if (null != cookies) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("username")) {
							loginName = cookie.getValue();
						}
						if (cookie.getName().equals("token")) {
							token = cookie.getValue();
						}
					}
				}
				if (Tools.notEmpty(loginName) && Tools.notEmpty(token)) {
					User record = new User();
					record.setLogin(loginName);
					record = userService.selectByUser(record);
					if (record != null) {
						String md5 = Md5Encrypt.md5(record.getPassword()
								+ record.getLogin()
								+ request.getHeader("user-agent"));
						if (token.equals(md5)) {
							request.getSession().setAttribute(
									SystemConfig.SESSION_FRONT_USER, record);
						}
					}
				}
			}
			if (request.getServletPath().contains("/wap/index.html")
					|| request.getServletPath()
							.contains("/wap/produtlist.html")
					|| request.getServletPath().contains(
							"/wap/produtcatalog.html")
					|| request.getServletPath()
							.contains("/wap/produtShow.html")
					|| request.getServletPath().contains("/wap/active.html")
					|| request.getServletPath().contains("/wap/redBag.html")
					|| request.getServletPath().contains(
							"/wap/wxShare/redBagReceived.html")
					|| request.getServletPath().contains(
							"/wap/redBagReceived.html")) {
				String js_token;
				HttpSession session = request.getSession();
				try {
					js_token = HTTPSClient.getJsToken().get("ticket");
					String nonceStr = Tools.getRandomCharAndNumr(17);
					String timestamp = String.valueOf(System
							.currentTimeMillis() / 1000);
					String[] str = { "noncestr=" + nonceStr,
							"jsapi_ticket=" + js_token,
							"timestamp=" + timestamp, "url=" + strBackUrl };
					Arrays.sort(str);
					String bigStr = str[0] + "&" + str[1] + "&" + str[2] + "&"
							+ str[3];
					System.out.println(bigStr);
					String signature = new SHA1().getDigestOfString(
							bigStr.getBytes()).toLowerCase();
					session.setAttribute("timestamp", timestamp);
					session.setAttribute("appId", SystemConfig.APPID);
					session.setAttribute("nonceStr", nonceStr);
					session.setAttribute("signature", signature);
					System.out.println("signature:" + signature);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// 是否有访问权限
		boolean canAccess = false;
		// 判断用户是否登录
		Object obj = null;
		if (handlerMethod.isAnnotationPresent(Access.class)) {
			boolean userLoggedIn = isUserLoggedIn(request);
			String urole = getLoginRole(request);
			Access access = handlerMethod.getAnnotation(Access.class);
			if (access != null) {
				String rule = access.rule();
				boolean intercepter = access.intercepter();
				// 需要拦截
				if (intercepter) {
					logger.debug("userLoggedIn:" + userLoggedIn);
					logger.debug("urole:" + urole);
					logger.debug("rule:" + rule);
					if (rule.equals("front")) {
						canAccess = true;
					} else {
						if (userLoggedIn && checkRuleFlag(rule, urole, request)) {// urole.equals(rule))
							canAccess = true;
							// 继续链式调用
							if (null != request.getParameter("typeid")
									&& !request.getParameter("typeid").equals(
											"")) {
								request.getSession().setAttribute("typeid",
										request.getParameter("typeid"));
							}
						} else {
							canAccess = false;
						}
					}
				} else {
					canAccess = true;
				}
			} else {
				canAccess = true;
			}
		} else if (handler.getClass().isAnnotationPresent(Access.class)) {
			boolean userLoggedIn = isUserLoggedIn(request);
			String urole = getLoginRole(request);
			// 未标记，则使用全局配置。全局配置存在时，必须用户登录，否则不允许使用该功能
			Access access = handler.getClass().getAnnotation(Access.class);
			if (access != null) {
				String rule = access.rule();
				boolean intercepter = access.intercepter();
				// 需要拦截
				if (intercepter) {
					logger.debug("userLoggedIn:" + userLoggedIn);
					logger.debug("urole:" + urole);
					logger.debug("rule:" + rule);
					if (rule.equals("front")) {
						canAccess = true;
					} else {
						if (userLoggedIn && checkRuleFlag(rule, urole, request)) // urole.equals(rule))
						{
							if (request.getRequestURI().contains("login.html")
									|| request.getRequestURI().contains(
											"logout.html")
									|| request.getRequestURI().contains(
											"code.html")) {
								obj = chain.doChain(handlerMethod, handler,
										request, response, model);
								return obj;
							} else {
								// 继续链式调用
								if (null != request.getParameter("typeid")
										&& !request.getParameter("typeid")
												.equals("")) {
									request.getSession().setAttribute("typeid",
											request.getParameter("typeid"));
								}
							}
							canAccess = true;
						} else {
							canAccess = false;
						}
					}
				} else {
					canAccess = true;
				}
			} else {
				canAccess = true;
			}
		} else {
			// 全局也没有配置，则可以访问
			canAccess = true;
		}

		if (canAccess) {
			obj = chain.doChain(handlerMethod, handler, request, response,
					model);
		} else if (handlerMethod.isAnnotationPresent(ResponseBody.class)) {
			// Json格式的回复。这个由系统框架决定了的。
			obj = "{\"retCode\":\"-1\"}"; // JSONUtil.getFailJsonString(FXSHOP.FXSHOP_JSON_INTERCEPTER_FAIL_CODE,
											// "您无权访问！请先登录");
											// } else if (userLoggedIn) {
			// // forward资源
			// obj = "common/error.html";
		} else {
			// redirect资源
			obj = "redirect:" + BaseConfig.FX_DOMAIN_WWW + "/system/login.html";
		}
		logger.debug("=====>result=" + obj);
		return obj;
	}

	/**
	 * 
	 * @描述: 判断用户是否已经登录。从Session中获取数据。
	 * @作者: kasiaits
	 * @日期:2013-3-14
	 * @修改内容
	 * @参数： @param request
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	private boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Company loginUser = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);

		return loginUser != null;
	}

	private boolean isUserFrontLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);

		return loginUser != null;
	}

	/**
	 * 
	 * @描述: 获取登陆用户的角色
	 * @作者: kasiaits
	 * @日期:2013-3-12
	 * @修改内容
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	private String getLoginRole(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String) session
				.getAttribute(SystemConfig.SESSION_USER_ROLE_CODE);
	}

	/**
	 * 
	 * @描述: 根据权限权重判断权限是否满足
	 * @作者: kasiaits
	 * @日期:2013-4-1
	 * @修改内容
	 * @参数： @param accessrule
	 * @参数： @param loginrule
	 * @参数： @return
	 * @return boolean
	 * @throws
	 */
	private boolean checkRuleFlag(String accessrule, String loginrule,
			HttpServletRequest request) {

		String requestUri = request.getRequestURI();

		@SuppressWarnings("unchecked")
		List<String> roleList = (List<String>) request.getSession()
				.getAttribute(SystemConfig.SESSION_USER_ROLE_URL);
		if (roleList.contains(requestUri.replace("/bbmall", ""))) {
			return true;
		}

		if (requestUri.contains("system/index.html")
				|| requestUri.contains("system/main.html")
				|| requestUri.contains("system/logout.html")) {
			return true;
		}
		/*
		 * if(login != access) { HttpSession session = request.getSession();
		 * session.removeAttribute(FXSHOP.SESSION_USER);
		 * session.removeAttribute("user");
		 * session.removeAttribute(FXSHOP.SESSION_USER_ROLE_CODE);
		 * session.removeAttribute(FXSHOP.SESSION_USER_ROLE_NAME);
		 * session.removeAttribute(FXSHOP.SESSION_USER_ROLE_ID); }
		 */
		return false;
	}
}
