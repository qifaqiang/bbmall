package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.AES;
import com.wxsoft.framework.util.ConvertPassword;
import com.wxsoft.framework.util.SystemUtil;
import com.wxsoft.xyd.quartz.service.QuartzManagerService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.SysMenu;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.SysMenuService;

/**
 * 
 * @类功能说明：系统管理入口
 * @类修改者：kasiait
 * @修改日期：2013-3-25
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-25 下午02:45:10
 */
@RestController
@RequestMapping(SystemConfig.FXSHOP_ANNOTATION_SYSTEM)
public class LoginController extends BaseController {

	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CompanyService companyService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private QuartzManagerService quartzManagerService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		logger.debug("request get the system index page");
		return SystemConfig.SYSTEM_PATH_ADMIN + "/login";
		// return WEIXIN.SYSTEM_PATH_ADMIN + "/logout";
	}

	/**
	 * 是否存在登录名
	 */
	@RequestMapping(value = "/isuselogin")
	public void isUseLogin(HttpServletRequest request, HttpServletResponse res,
			String login, HttpSession session) {
		String result = "false";
		try {
			if (!SystemUtil.isEmpty(login)) {
				Company company = new Company();
				company.setLogin(login);
				System.out.println(companyService.ifExistByLoginName(company));
				if (companyService.ifExistByLoginName(company) == null) {
					result = "success";
				}
			}
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述:保存用户
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/save")
	public void saveRole(String username, String password, String rpassword,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ResultModel rm = null;
		if (SystemUtil.isEmpty(username) && SystemUtil.isEmpty(password)
				&& SystemUtil.isEmpty(rpassword)) {
			if (username.matches("/^.*?[\\d]+.*$/")
					&& username.matches("/^.*?[A-Za-z].*$/")
					&& username.matches("/^.{6,16}$/")) {
				if (!password.equals(rpassword)) {
					rm = new ResultModel("友情提示", "保存失败", "error", "");
				}
				rm = new ResultModel("友情提示", "保存失败", "error", "");
			} else {
				rm = new ResultModel("友情提示", "保存失败", "error", "");
			}

		}
		if (null == rm) {
			Company user = new Company();
			user.setLogin(username);
			user.setPasswd(ConvertPassword.getMyPassword(password));
			try {
				if (companyService.insert(user, 49) > 0) {
					user = companyService.getUserByNameAndPwd(username,
							user.getPasswd());
					session.setAttribute(SystemConfig.SESSION_USER, user);
					session.setAttribute(SystemConfig.SESSION_USER_ROLE_ID, user
							.getUserRole().getId()); // 角色Id
					session.removeAttribute(SystemConfig.SESSION_SECURITY_CODE);
					session.setAttribute(SystemConfig.SESSION_USER_ROLE_CODE,
							"system");

					SysMenu sm = new SysMenu();
					sm.setRoleId(Integer.parseInt(session.getAttribute(
							SystemConfig.SESSION_USER_ROLE_ID).toString()));
					List<SysMenu> menulist = sysMenuService
							.getSysMenuWithRole(sm, Integer.parseInt(session
									.getAttribute(
											SystemConfig.SESSION_USER_ROLE_ID)
									.toString()));
					List<String> roleUrllist = sysMenuService
							.getAllUserSysMenu(sm);
					session.setAttribute(SystemConfig.ROLEWIXIN, menulist);
					session.setAttribute(SystemConfig.SESSION_USER_ROLE_URL,
							roleUrllist);
					rm = new ResultModel("友情提示", "保存成功", "success",
							BaseConfig.FX_DOMAIN_WWW + "/index.html");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 请求登录，验证用户
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost(HttpSession session, HttpServletResponse response,
			String DATA) {

		logger.debug("admin login the system manager login page");
		JSONObject jsonObject = JSONObject.parseObject(DATA);

		Company userTemp = new Company();
		userTemp.setLogin(jsonObject.getString("loginname"));
		userTemp.setPasswd(ConvertPassword.getMyPassword(jsonObject
				.getString("password")));
		Company user = companyService.getUserByNameAndPwd(userTemp.getLogin(),
				userTemp.getPasswd());
		String result = "{'flag':false}";
		if (user != null && user.getUserRole() != null) {
			session.setAttribute(SystemConfig.SESSION_USER, user);
			session.setAttribute(SystemConfig.SESSION_USER_ROLE_ID, user
					.getUserRole().getId()); // 角色Id
			session.removeAttribute(SystemConfig.SESSION_SECURITY_CODE);
			session.setAttribute(SystemConfig.SESSION_USER_ROLE_CODE, "system");

			SysMenu sm = new SysMenu();
			sm.setRoleId(Integer.parseInt(session.getAttribute(
					SystemConfig.SESSION_USER_ROLE_ID).toString()));

			List<SysMenu> menulist = sysMenuService.getSysMenuWithRole(sm, user
					.getUserRole().getId());
			List<String> roleUrllist = sysMenuService.getAllUserSysMenu(sm);
			session.setAttribute(SystemConfig.ROLEWIXIN, menulist);
			session.setAttribute(SystemConfig.SESSION_USER_ROLE_URL, roleUrllist);

			result = "{'flag':true}";
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 访问系统首页
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@Access(intercepter = true, rule = "system", path = "/system")
	@RequestMapping(value = "/index")
	public String index(HttpSession session, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		Company user = (Company) session.getAttribute(SystemConfig.SESSION_USER);
		model.addAttribute("user", user);

		String resultUrl = "";
		// resultUrl = "redirect:"
		// +list.get(0).getSysMenuList().get(0).getLinkUrl();
		resultUrl = "redirect:" + BaseConfig.FX_DOMAIN_WWW
				+ "/system/main.html";
		return resultUrl;

	}

	/**
	 * 进入首页后的默认页面
	 * 
	 * @return
	 */
	@Access(intercepter = true, rule = "main", path = "/system")
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN + "/main");
		return mv;
	}

	/**
	 * 进入首页后的默认页面
	 * 
	 * @return
	 */
	@Access(intercepter = true, rule = "system", path = "/system")
	@RequestMapping(value = "/default")
	public String defaultPage() {
		return "redirect:/system/user/list.html";
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@Access(intercepter = true, rule = "system", path = "/system")
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(SystemConfig.SESSION_USER);
		session.removeAttribute(SystemConfig.SESSION_USER_ROLE_CODE);
		session.removeAttribute(SystemConfig.ROLEWIXIN);
		session.removeAttribute(SystemConfig.SESSION_USER_ROLE_NAME);
		session.removeAttribute(SystemConfig.SysMenu);
		return SystemConfig.SYSTEM_PATH_ADMIN + "/login";
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toshopsystem", method = RequestMethod.GET)
	public String toshopsystem(HttpSession session, HttpServletResponse response,String token) {
		logger.debug("总后台管理员进入商城后台链接");
		
		byte[] results = AES.decrypt(Base64.decode(token),
				"weijiazhuangemall/systempwd");
		String kr = new String(results);
		String[] paravalues = kr.split("_");
		String[] usernamepwd = paravalues[0].split("\\:");
		long timeTemp = Long.parseLong(paravalues[1]);
		long nowTimes = new Date().getTime();
		System.out.println("timeTemp:" + timeTemp);
		System.out.println("nowTimes:" + nowTimes);
		if (nowTimes - timeTemp < 1000 * 60 * 5) {//说明验证成功
			Company userTemp = new Company();
			userTemp.setLogin(usernamepwd[0]);
			userTemp.setPasswd(ConvertPassword.getMyPassword(usernamepwd[1]));
			Company user = companyService.getUserByNameAndPwd(userTemp.getLogin(),
					userTemp.getPasswd());
			if (user != null && user.getUserRole() != null) {
				session.setAttribute(SystemConfig.SESSION_USER, user);
				session.setAttribute(SystemConfig.SESSION_USER_ROLE_ID, user
						.getUserRole().getId()); // 角色Id
				session.removeAttribute(SystemConfig.SESSION_SECURITY_CODE);
				session.setAttribute(SystemConfig.SESSION_USER_ROLE_CODE, "system");

				SysMenu sm = new SysMenu();
				sm.setRoleId(Integer.parseInt(session.getAttribute(
						SystemConfig.SESSION_USER_ROLE_ID).toString()));

				List<SysMenu> menulist = sysMenuService.getSysMenuWithRole(sm, user
						.getUserRole().getId());
				List<String> roleUrllist = sysMenuService.getAllUserSysMenu(sm);
				session.setAttribute(SystemConfig.ROLEWIXIN, menulist);
				session.setAttribute(SystemConfig.SESSION_USER_ROLE_URL, roleUrllist);
				
				return SystemConfig.SYSTEM_PATH_ADMIN + "/main";
			}else{
				return SystemConfig.SYSTEM_PATH_ADMIN + "/login";
			}
			
		} else {
			return SystemConfig.SYSTEM_PATH_ADMIN + "/login";
		}
	}
	
	
	/**
	 * 查询机器码是否可用
	 * 
	 * @param request
	 * @param response
	 * @param us
	 */
	@RequestMapping("/testtest")
	public void testtest(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("result", 1);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 访问登录页
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/testtoshopsystem", method = RequestMethod.GET)
//	public String testtoshopsystem() {
//		logger.debug("总后台管理员进入商城后台链接");
//		String tokenFront = "wjzadmin:123456_";//用户名密码_    后面_必须的
//		String aesKey = "weijiazhuangemall/systempwd";//这个固定死的
//		String urls = "http://192.168.0.11/wjzemall/system/toshopsystem.html";
//		
//		String token = tokenFront + new Date().getTime();
//		byte[] tokenBytes = AES.encrypt(token, aesKey);
//		String result_token = Base64.encode(tokenBytes);
//		
//		String tokenEncode = null;
//		try {
//			tokenEncode = java.net.URLEncoder.encode(result_token, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "redirect:"+ SystemConfig.SYSTEM_PATH_ADMIN + "/toshopsystem.html?token="+tokenEncode;
//		
//	}
	
}
