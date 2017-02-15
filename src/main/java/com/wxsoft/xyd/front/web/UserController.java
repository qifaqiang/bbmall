package com.wxsoft.xyd.front.web;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.HTMLInputFilter;
import com.wxsoft.framework.util.Md5Encrypt;
import com.wxsoft.framework.util.ThumbNailHelper;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.SysSmsCheck;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.SysSmsCheckService;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 用户中心控制器
 * 
 * @author kyz
 * 
 */
@Controller
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysSmsCheckService sysSmsCheckService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Resource(name = "captchaService")
	private ImageCaptchaService captchaService;

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public void logout(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		session.removeAttribute(SystemConfig.SESSION_FRONT_USER);
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (su == null) {
			json.put("res_code", "0");
			json.put("message", "注销成功");
			json.put(BaseConfig.RESURL, "index.html");
		} else {
			json.put("res_code", "ER1017");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1017"));
		}

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logoutpc")
	public String logoutpc(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		session.removeAttribute(SystemConfig.SESSION_FRONT_USER);
		return "redirect:" + BaseConfig.FX_DOMAIN_WWW + "/index.html";
	}

	@RequestMapping("/areaPage")
	public ModelAndView areaPage(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/wap/areaPage");
		return mv;
	}

	/**
	 * 保存密码
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @param renewpassword
	 * @param request
	 * @param mstat
	 * @param session
	 * @return
	 */
	@Access(intercepter = true, rule = "front")
	@RequestMapping("/saveupdatesecret")
	public void saveUpdatesecret(String oldpassword, String newpassword,
			String renewpassword, String urls, HttpServletResponse response,
			HttpServletRequest request, String mstat, HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (su == null) {
			if (Md5Encrypt.md5(Md5Encrypt.md5(oldpassword)).equals(
					su.getPassword())) {
				su.setPassword(Md5Encrypt.md5(Md5Encrypt.md5(newpassword)));
				if (userService.updateByPrimaryKeySelective(su) > 0) {
					json.put("res_code", "0");
					json.put("message", "修改成功");
					if (Tools.notEmpty(urls)) {
						json.put(BaseConfig.RESURL, urls);
					} else {
						json.put(BaseConfig.RESURL,
								"personalCenter-myAccount.html");
					}
				} else {
					json.put("res_code", "ER1006");
					json.put("message", BaseConfig.MESSAGE.get("user.ER1006"));
				}
				
			} else {
				json.put("res_code", "ER1107");
				json.put("message", BaseConfig.MESSAGE.get("user.ER1107"));
			}
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1023"));
		}
		
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改昵称
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @param renewpassword
	 * @param request
	 * @param mstat
	 * @param session
	 * @return
	 */
	@Access(intercepter = true, rule = "front")
	@RequestMapping("/updatenicheng")
	public void updatenicheng(String nickname, HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		su.setName(new HTMLInputFilter().filter(nickname));
		if (userService.updateByPrimaryKeySelective(su) > 0) {
			json.put("res_code", "0");
			json.put("message", "修改成功");
			session.setAttribute(SystemConfig.SESSION_FRONT_USER, su);
			json.put(BaseConfig.RESURL, "personalCenter-myAccount.html");
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1023"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改头像
	 * 
	 * @param oldpassword
	 * @param newpassword
	 * @param renewpassword
	 * @param request
	 * @param mstat
	 * @param session
	 * @return
	 */
	@Access(intercepter = true, rule = "front")
	@RequestMapping("/updatetouxiang")
	public void updatetouxiang(HttpServletResponse response, String image,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();

		/** 获取用户信息 */
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		String path = BaseConfig.FX_PIC_PATH;
		String paths = "/attached/userPic/";
		path = path + paths;
		if (!new File(path).isDirectory()) {
			new File(path).mkdirs();
		}
		long finame = System.currentTimeMillis();
		String picname = Long.toString(finame) + ".jpg";
		ThumbNailHelper.decodeBase64ToImage(
				image.replace("data:image/jpeg;base64,", ""), path, picname);
		su.setPicUrl(paths + picname);
		if (userService.updateByPrimaryKeySelective(su) > 0) {
			json.put("res_code", "0");
			json.put("message", "success");
			session.setAttribute(SystemConfig.SESSION_FRONT_USER, su);
		} else {
			json.put("res_code", "ER1006");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1006"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param phone
	 * @param pwd
	 * @param code
	 * @param session
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public void userRegister(HttpServletRequest request,
			HttpServletResponse response, String phone, String pwd,
			Integer fromCompanyId, String code, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			// 空验证
			if (Tools.isEmpty(phone) || Tools.isEmpty(pwd)
					|| Tools.isEmpty(code)) {
				json.put(BaseConfig.RESCODE, "ER1001");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1001").toString());
				responseAjax(json, response);
				return;
			}
			// 手机验证码验证
			SysSmsCheck ssc = new SysSmsCheck();
			ssc.setMobile(phone);
			ssc.setCode(code);
			JSONObject jr = phoneValidate(ssc);
			if (jr.get(BaseConfig.RESCODE) != "0") {
				responseAjax(jr, response);
				return;
			}

			// 验证用户是否已经注册
			User record = new User();
			record.setLogin(phone);
			record = userService.selectByUser(record);
			if (record != null) {
				json.put(BaseConfig.RESCODE, "ER1009");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1009").toString());
				responseAjax(jr, response);
				return;
			} else {
				// 添加注册
				User user = new User();
				user.setIsDel(1);
				user.setCompanyId(fromCompanyId);
				user.setMobile(phone);
				user.setLogin(phone);
				user.setName(user.getMobile().substring(0, 3) + "****"
						+ user.getMobile().substring(8, 11));
				user.setSex(1);
				user.setAllowedPush(1);
				user.setPassword(Md5Encrypt.md5(Md5Encrypt.md5(pwd)));
				user.setIp(Tools.getRemortIP(request));
				user.setPicUrl("/front/images/user.jpg");
				userService.insertSelective(user);
				SysCouponsRecord scr = new SysCouponsRecord();
				scr.setUserId(user.getId());
				scr.setMobile(phone);
				sysCouponsRecordService.updateByRegMobile(scr);
				session.setAttribute(SystemConfig.SESSION_FRONT_USER, user);
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "注册成功！");
				json.put(BaseConfig.RESURL, "login.html");
				responseAjax(json, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/userIsuse", method = RequestMethod.POST)
	public void userIsuse(HttpServletRequest request,
			HttpServletResponse response, String phone, String type,
			HttpSession session) {
		try {
			JSONObject json = new JSONObject();
			if (Tools.notEmpty(phone) && Tools.notEmpty(type)) {
				// 验证用户是否已经注册
				User record = new User();
				record.setLogin(phone);
				record = userService.selectByUser(record);

				if (type.equals("1")) {// 注册判断
					if (record != null) {
						json.put(BaseConfig.RESCODE, "ER1009");
						json.put(BaseConfig.RESMESSAGE,
								BaseConfig.MESSAGE.get("user.ER1009")
										.toString());
					} else {
						json.put(BaseConfig.RESCODE, "0");
						json.put(BaseConfig.RESMESSAGE, "");
					}
				} else {// 找回密码判断
					if (record != null) {
						json.put(BaseConfig.RESCODE, "0");
						json.put(BaseConfig.RESMESSAGE, "");
					} else {
						json.put(BaseConfig.RESCODE, "ER1005");
						json.put(BaseConfig.RESMESSAGE,
								BaseConfig.MESSAGE.get("user.ER1005")
										.toString());

					}
				}
			} else {
				json.put(BaseConfig.RESCODE, "ER1010");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1010").toString());
			}
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public void userLogin(HttpServletRequest request,
			HttpServletResponse response, String loginName, String loginPwd,
			String url, String token, HttpSession session) {
		try {
			JSONObject json = new JSONObject();
			// 空验证
			if (Tools.isEmpty(loginName) || Tools.isEmpty(loginPwd)) {
				json.put(BaseConfig.RESCODE, "ER1103");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1103").toString());
				responseAjax(json, response);
				return;
			}
			User user = new User();
			user.setLogin(loginName);
			user.setPassword(Md5Encrypt.md5(Md5Encrypt.md5(loginPwd)));
			user = userService.selectByUser(user);

			try {
				if (Tools.notEmpty(token)) {
					User record = new User();
					record.setLogin(loginName);
					record = userService.selectByUser(record);
					if (record != null) {
						String md5 = Md5Encrypt.md5(record.getPassword()
								+ record.getLogin()
								+ request.getHeader("user-agent"));
						if (token.equals(md5)) {
							user = record;
						}
					}
				}
			} catch (Exception e) {

			}
			if (user == null) {

				json.put(BaseConfig.RESCODE, "ER1103");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1103").toString());
				responseAjax(json, response);
				return;
			} else if (user.getIsDel() == 1) {
				session.setAttribute(SystemConfig.SESSION_FRONT_USER, user);
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "登录成功");
				json.put("name", user.getName());
				if (null != url) {
					json.put(BaseConfig.RESURL, url);
				} else {
					json.put(BaseConfig.RESURL, "personalCenter.html");
				}
				json.put("userName", user.getName());
				json.put("userPic",
						BaseConfig.FX_DOMAIN_IMAGE8888 + user.getPicUrl());
				json.put("userId", user.getId());
				json.put(
						"token",
						Md5Encrypt.md5(user.getPassword() + user.getLogin()
								+ request.getHeader("user-agent")));
				responseAjax(json, response);

			} else {
				json.put(BaseConfig.RESCODE, "ER1012");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1012").toString());
				responseAjax(json, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断用户是否登陆
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/isuserLogin", method = RequestMethod.GET)
	public void isuserLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_USER);
		if (null != user) {
			String name = user.getName();
			json.put("res_code", "0");
			String names = request.getParameter("jsonpcallback") + "(" + name
					+ ")";
			session.setAttribute(SystemConfig.SESSION_FRONT_USER, names);
		} else {
			json.put("res_code", "1");
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 找回密码 WAP
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/retrievePassword", method = RequestMethod.POST)
	public void retrievePassword(HttpServletRequest request,
			HttpServletResponse response, String phone, String captcha,
			String code, String password, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			if (phone
					.matches("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")) {
				// 空验证
				if (Tools.isEmpty(phone) || Tools.isEmpty(captcha)
						|| Tools.isEmpty(code) || Tools.isEmpty(password)) {
					json.put(BaseConfig.RESCODE, "ER1001");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("user.ER1001").toString());
					responseAjax(json, response);
					return;
				}
				User user = new User();
				user.setLogin(phone);
				User selUser = userService.selectByUser(user);

				if (selUser == null) {
					json.put(BaseConfig.RESCODE, "ER1022");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("user.ER1022").toString());
				} else {

					boolean captchaBoolean = false;
					try {
						captchaBoolean = captchaService.validateResponseForID(
								request.getSession(false).getId(),
								captcha.toLowerCase());
						if (!captchaBoolean) {
							json.put(BaseConfig.RESCODE, "ER1004");
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("user.ER1004").toString());
						} else {
							json.put(BaseConfig.RESCODE, "0");
							json.put(BaseConfig.RESMESSAGE, "验证成功");
							// 手机验证码验证
							SysSmsCheck ssc = new SysSmsCheck();
							ssc.setMobile(phone);
							ssc.setCode(code);
							JSONObject jr = phoneValidate(ssc);
							if (jr.get(BaseConfig.RESCODE) != "0") {
								responseAjax(jr, response);
								return;
							} else {// 校验成功
								User user2 = new User();
								user2.setLogin(phone);
								user2 = userService.selectByUser(user2);
								// 待修改的用户信息
								User reUser = new User();
								reUser.setId(user2.getId());
								reUser.setPassword(Md5Encrypt.md5(Md5Encrypt
										.md5(password)));

								if (userService
										.updateByPrimaryKeySelective(reUser) > 0) {
									json.put(BaseConfig.RESCODE, "0");
									json.put(BaseConfig.RESURL, "login.html");
									json.put(BaseConfig.RESMESSAGE, "找回密码成功");
									responseAjax(json, response);
									return;
								} else {
									json.put(BaseConfig.RESCODE, "-1");
									json.put(BaseConfig.RESMESSAGE, "系统错误");
								}

							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						json.put(BaseConfig.RESCODE, "ER1013");
						json.put(BaseConfig.RESMESSAGE,
								BaseConfig.MESSAGE.get("user.ER1013")
										.toString());
					}
				}
			} else {
				json.put(BaseConfig.RESCODE, "ER1014");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1014").toString());
			}
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找回密码 Description: 第一步 验证验证码和用户是否存在
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/rePwdStep1", method = RequestMethod.POST)
	public void rePwdStep1(HttpServletRequest request,
			HttpServletResponse response, String loginName, String captcha,
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			if (loginName
					.matches("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")) {
				// 空验证
				if (Tools.isEmpty(loginName) || Tools.isEmpty(captcha)) {
					json.put(BaseConfig.RESCODE, "ER1001");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("user.ER1001").toString());
					responseAjax(json, response);
					return;
				}
				User user = new User();
				user.setLogin(loginName);
				User selUser = userService.selectByUser(user);

				if (selUser == null) {
					json.put(BaseConfig.RESCODE, "ER1022");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("user.ER1022").toString());
				} else {

					boolean captchaBoolean = false;
					try {
						captchaBoolean = captchaService.validateResponseForID(
								request.getSession(false).getId(),
								captcha.toLowerCase());
						if (!captchaBoolean) {
							json.put(BaseConfig.RESCODE, "ER1004");
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("user.ER1004").toString());
						} else {
							json.put(BaseConfig.RESCODE, "0");
							json.put(BaseConfig.RESMESSAGE, "验证成功");
							session.setAttribute("step1", true);
						}
					} catch (CaptchaServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						json.put(BaseConfig.RESCODE, "ER1013");
						json.put(BaseConfig.RESMESSAGE,
								BaseConfig.MESSAGE.get("user.ER1013")
										.toString());
					}
				}
			} else {
				json.put(BaseConfig.RESCODE, "ER1014");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1014").toString());
			}
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找回密码 Description: 第二步 验证手机验证码
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/rePwdStep2", method = RequestMethod.POST)
	public void rePwdStep2(HttpServletRequest request,
			HttpServletResponse response, String loginName, String phoneCode,
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			// 空验证
			if (Tools.isEmpty(loginName) || Tools.isEmpty(phoneCode)) {
				json.put(BaseConfig.RESCODE, "ER1001");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1001").toString());
				responseAjax(json, response);
				return;
			}
			// 手机验证码验证
			SysSmsCheck ssc = new SysSmsCheck();
			ssc.setMobile(loginName);
			ssc.setCode(phoneCode);
			JSONObject jr = phoneValidate(ssc);
			if (jr.get(BaseConfig.RESCODE) != "0") {
				responseAjax(jr, response);
				return;
			}
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "验证成功");
			session.setAttribute("step2", true);
			responseAjax(jr, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找回密码 Description: 第三步 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/rePwdStep3", method = RequestMethod.POST)
	public void rePwdStep3(HttpServletRequest request,
			HttpServletResponse response, String loginName, String loginPwd,
			HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			// 空验证
			if (Tools.isEmpty(loginName) || Tools.isEmpty(loginPwd)) {
				json.put(BaseConfig.RESCODE, "ER1001");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1001").toString());
				responseAjax(json, response);
				return;
			}
			// 修改之前验证前面步骤是否成功
			Object step1 = session.getAttribute("step1");
			Object step2 = session.getAttribute("step2");
			if (step1 == null) {
				json.put(BaseConfig.RESCODE, "ER1015");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1015").toString());
				responseAjax(json, response);
				return;
			}
			if (step2 == null) {
				json.put(BaseConfig.RESCODE, "ER1016");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1016").toString());
				responseAjax(json, response);
				return;
			}
			// 根据用户名获取用户
			User user = new User();
			user.setLogin(loginName);
			user = userService.selectByUser(user);
			// 待修改的用户信息
			User reUser = new User();
			reUser.setId(user.getId());
			reUser.setPassword(Md5Encrypt.md5(Md5Encrypt.md5(loginPwd)));

			if (userService.updateByPrimaryKeySelective(reUser) > 0) {
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "修改密码成功");
			} else {
				json.put(BaseConfig.RESCODE, "-1");
				json.put(BaseConfig.RESMESSAGE, "系统错误");
			}
			session.setAttribute("step3", true);
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询机器码是否可用
	 * 
	 * @param request
	 * @param response
	 * @param us
	 */
	@RequestMapping("/selectMachineCodeIsDel")
	public void selectMachineCodeIsDel(HttpServletRequest request,
			HttpServletResponse response, User us) {
		JSONObject json = new JSONObject();
		User user = userService.selectmachineCodeIfDel(us);
		if (user != null) {
			json.put("result", 0);
		} else {
			json.put("result", 1);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
