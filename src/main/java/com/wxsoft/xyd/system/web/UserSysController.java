package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Md5Encrypt;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 会员控制器
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/user")
public class UserSysController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 会员列表
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/userList")
	public ModelAndView userList(User user, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		/*if (user.getName() == null || user.getName().equals("")) {
			user.setName(null);
		}
		if (user.getMobile() == null || user.getMobile().equals("")) {
			user.setMobile(null);
		}*/
		List<Map<String, Object>> map = userService.listPageUserVsWx(user);
		mv.addObject("objList", map);

		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/user/userList");
		mv.addObject("user", user);
		return mv;
	}

    
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @param DATA
	 * @param session
	 */
	@RequestMapping("/resetpasswd")
	public void resetPasswd(HttpServletRequest request,

	HttpServletResponse response, String DATA, HttpSession session) {

		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			User user = new User();
			user.setPassword(Md5Encrypt.md5(Md5Encrypt.md5("123456")));
			user.setId(Integer.parseInt(string));
			int rt = userService.updateByPrimaryKeySelective(user);
			if (rt > 0) {
				result = "{'flag':true}";
			}
			try {
				responseAjax(result, response);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 设置注册用户是否禁用
	 * @param request
	 * @param res
	 * @param id
	 * @param types
	 * @param session
	 */
	@RequestMapping("/setDisable")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			int id, int types, HttpSession session) {
		String result = "{'flag':false}";
		User user = new User();
		user.setId(id);
		user.setIsDel((types == 1 ? 0 : 1));
		if (userService.updateDisable(user) > 0) {
			result = "{'flag':true}";
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
			if (userService.deleteByPrimaryKey(Integer
					.parseInt(string)) > 0) {
				result = "{'flag':true}";
			}
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	

}

	

