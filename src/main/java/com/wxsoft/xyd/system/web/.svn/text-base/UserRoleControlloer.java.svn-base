/**   
 * @文件名称: UserRoleControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.UserRole;
import com.wxsoft.xyd.system.model.UserRoleresource;
import com.wxsoft.xyd.system.service.SysMenuService;
import com.wxsoft.xyd.system.service.UserRoleAssociatedService;
import com.wxsoft.xyd.system.service.UserRoleService;
import com.wxsoft.xyd.system.service.UserRoleresourceService;

/**
 * @类功能说明：用户权限
 * @类修改者：kyz
 * @修改日期：2015-07-11
 * @修改说明：
 * @回复名称：kyz
 * @作者：kyz
 * @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/userrole")
public class UserRoleControlloer extends BaseController {

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserRoleAssociatedService userRoleAssociatedService;
	@Autowired
	private UserRoleresourceService userRoleresourceService;
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 
	 * @描述: 角色列表
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/rolelist")
	public ModelAndView roleList(UserRole userRole, HttpServletRequest request,
			String mstat, HttpSession session) {
		userRole.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		List<UserRole> userList = userRoleService.listPageAllRole(userRole);
		ModelAndView mv = new ModelAndView();
		mv.addObject("objList", userList);
		mv.addObject("userRole", userRole);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userrole/rolelist");
 
		return mv;
	}

	/**
	 * 
	 * @描述: 添加权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/addrole")
	public ModelAndView addRole(UserRole userRole, HttpServletRequest request,
			String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userrole/addrole");
		mv.addObject("menu2", sysMenuService.getAllSysMenu(null));
		return mv;
	}
	
	/**
	 * 
	 * @描述: 更新权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/updaterole")
	public ModelAndView updateRole(Integer roleId, HttpServletRequest request,
			String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userrole/addrole");

		// UserRole r=userRoleService.selectRoleResourceByPrimaryKey(roleId);
		UserRole ur = userRoleService.selectRoleResourceByPrimaryKey(roleId);
		List<Integer> inelist = new ArrayList<Integer>();
		for (UserRoleresource idd : ur.getUserRoleresources()) {
			inelist.add(idd.getResid());
		}
		ur.setUserRoleresources(null);
		mv.addObject("role", inelist);
		mv.addObject("rolename", ur);
		mv.addObject("menu2", sysMenuService.updateUserMenu(inelist));
		return mv;
	}

	/**
	 * @throws java.lang.Exception
	 * 
	 * @描述: 保存权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/saverole")
	public void saveRole(UserRole userRole, HttpServletRequest request,HttpServletResponse response,
			String rolelist, String mstat, HttpSession session)
			throws java.lang.Exception {
		ResultModel rm = null;
		userRole.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (rolelist.equals("")) {
			rm = new ResultModel("友情提示", "保存失败", "error", "");
		}
		if (null != userRole.getId()) {
			if (userRoleService.update(userRole, rolelist.split(",")) > 0) {
				rm = new ResultModel("友情提示", "更新成功", "success",
						"rolelist.html?typeid=1");
			}

		} else {
			if (userRoleService.insert(userRole, rolelist.split(",")) > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success",
						"rolelist.html?typeid=1");
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
	 * 删除
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);

		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			userRoleService.deleteByPrimaryKey(Integer.parseInt(string));
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}