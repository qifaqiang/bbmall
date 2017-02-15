/**   
 * @文件名称: CompanyControlloer.java
 * @类路径: com.wxsoft.xyd.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.ConvertPassword;
import com.wxsoft.framework.util.SystemUtil;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.UserRole;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.UserRoleAssociatedService;
import com.wxsoft.xyd.system.service.UserRoleService;

/**
 * @类功能说明：商户信息管理
 * @类修改者：kyz @修改日期：2015-07-11 @修改说明：
 * @回复名称：kyz
 * @作者：kyz @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/company")
public class CompanyControlloer extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserRoleAssociatedService userRoleAssociatedService;

	/**
	 * 公司列表
	 * 
	 * @param company
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyList")
	public ModelAndView companyList(Company company,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (company.getLogin() == null || company.getLogin().equals("")) {
			company.setLogin(null);
		}
		List<Company> companyList = companyService.listPageUserCompany(company);
		mv.addObject("objList", companyList);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/companyList");
		mv.addObject("company", company);
		return mv;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addCompany(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		UserRole userRole = new UserRole();
		userRole.setCompanyId(1);
		List<UserRole> roleList = userRoleService
				.getAllRoleByCompanyId(userRole);
		mv.addObject("sclist", roleList);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/companyAdd");
		return mv;
	}

	/**
	 * 我的二维码
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/mycode")
	public ModelAndView mycode(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String qrcode = companyService.selectCompanyByQrcode(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		mv.addObject("qrcode", qrcode);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/mycode");
		return mv;
	}

	/**
	 * 重置密码
	 * 
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping("resetpasswd")
	public void resetPasswd(HttpServletRequest request,
			HttpServletResponse response, String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			Company su = new Company();
			su.setPasswd(ConvertPassword.getMyPassword("123456"));
			su.setCompanyId(Integer.parseInt(string));
			Company role = companyService.selectRoleByCompanyId(Integer
					.parseInt(string));
			// 判断当前用户是非基地用户
			if (role.getUserRole().getId().intValue() != 40) {
				if (!string.equals("1")) {
					if (companyService.updateByCompanySelective(su) > 0) {
						result = "{'flag':true}";
					}
				}
			}
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述: 我的公司信息 @作者: kyz @日期:2013-3-30 @修改内容 @参数： @param response @参数： @return @return
	 *      ModelAndView @throws
	 */
	@RequestMapping("/info")
	public ModelAndView companyInfo(Company user, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		user.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		user = companyService.getCompanyById(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/info");
		mv.addObject("company", user);
		return mv;
	}

	/**
	 * 保存
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param authCode
	 * @param session
	 */
	@RequestMapping("/save")
	public void save(Company user, HttpServletRequest request,
			HttpServletResponse response, String authCode, Integer roleId,
			HttpSession session) {

		ResultModel rm = null;
		if (SystemUtil.isEmpty(user.getCompanyContact())) {
			rm = new ResultModel("友情提示", "保存失败", "error", "");
		}
		if (SystemUtil.notEmpty(user.getMobile())) {
			if (user.getMobile().matches(
					"^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")) {
			} else {
				rm = new ResultModel("友情提示", "保存失败:手机号不正确", "error", "");
			}
		}
		if (null == rm) {
			Company userTrue = new Company();
			userTrue.setCompanyContact(user.getCompanyContact());
			userTrue.setMobile(user.getMobile());
			if (null != user.getCompanyId()) {
				userTrue.setCompanyId(user.getCompanyId());
				Company role = companyService.selectRoleByCompanyId(user
						.getCompanyId());
				// 判断当前用户是非基地用户
				if (role.getUserRole().getId().intValue() != 40) {
					if (companyService.updateByPrimaryKeySelective(userTrue,
							roleId) > 0) {
						rm = new ResultModel("友情提示", "修改成功", "success",
								"companyList.html");
					}
				}
			} else {
				Company test = new Company();
				test.setLogin(user.getMobile());
				if (companyService.ifExistByLoginName(test) == null) {
					user.setPasswd(ConvertPassword.getMyPassword("123456"));
					try {
						if (companyService.insert(user, roleId) > 0) {
							rm = new ResultModel("友情提示", "保存成功", "success",
									"companyList.html");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					rm = new ResultModel("友情提示", "保存失败:登录名已存在", "error", "");
				}
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
	 * 禁用
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping("/setDisable")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			int id, int types, HttpSession session) {
		String result = "{'flag':false}";
		Company user = new Company();
		user.setCompanyId(id);
		user.setIsdel((types == 1 ? 0 : 1));
		Company role = companyService.selectRoleByCompanyId(id);
		// 判断当前用户是非基地用户
		if (role.getUserRole().getId().intValue() != 40) {
			if (id != 1) {
				if (companyService.updatesetDisable(user) > 0) {
					result = "{'flag':true}";
				}
			}
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {

		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			Company role = companyService.selectRoleByCompanyId(Integer
					.parseInt(string));
			// 判断当前用户是非基地用户
			if (role.getUserRole().getId().intValue() != 40) {
				if (!string.equals(1)) {
					if (companyService.deleteCompany(Integer.parseInt(string)) > 0) {
						result = "{'flag':true}";
					}
				} else {
					result = "{'flag':false}";
				}
			}
		}

		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新
	 * 
	 * @param userId
	 * @param request
	 * @param mstat
	 * @param session
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(Integer companyId, HttpServletRequest request,
			String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/companyAdd");
		mv.addObject("company", companyService.getCompanyById(companyId));
		UserRole userRole = new UserRole();
		userRole.setCompanyId(1);
		List<UserRole> roleList = userRoleService
				.getAllRoleByCompanyId(userRole);
		mv.addObject("sclist", roleList);
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
	@RequestMapping("/saveupdatesecret")
	public void saveUpdatesecret(String oldpassword, String newpassword,
			String renewpassword, HttpServletRequest request, String mstat,
			HttpServletResponse response, HttpSession session) {
		ResultModel rm = null;
		Company su = (Company) session.getAttribute(SystemConfig.SESSION_USER);
		if (ConvertPassword.getMyPassword(oldpassword).equals(su.getPasswd())) {
			su.setPasswd(ConvertPassword.getMyPassword(newpassword));
			if (companyService.updateByCompanySelective(su) > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success",
						"updatesecret.html");
			}
		} else {
			rm = new ResultModel("友情提示", "原始密码不正确", "error", "");
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param res
	 * @param login
	 * @param session
	 */
	@RequestMapping(value = "/isuselogin")
	public void isUseLogin(HttpServletRequest request, HttpServletResponse res,
			String login, HttpSession session) {
		String result = "false";
		try {
			if (!SystemUtil.isEmpty(login)) {
				Company company = new Company();
				company.setLogin(login);
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
	 * 跳转更新密码
	 * 
	 * @param userRole
	 * @param request
	 * @param mstat
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatesecret")
	public ModelAndView updatesecret(UserRole userRole,
			HttpServletRequest request, String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/company/updatesecret");
		return mv;
	}

}