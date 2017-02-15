/**   
 * @文件名称: UsersControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.front.interfaces;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;

/**
 * @类功能说明：首页管理
 * @类修改者：kyz
 * @修改日期：2015-07-11
 * @修改说明：
 * @回复名称：kyz
 * @作者：kyz
 * @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = false, rule = "front", path = "/")
@RequestMapping("/common")
public class CommonControlloer extends BaseController {

	/**
	 * test
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView mybkorder(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/product/info/MyJsp");
		return mv;
	}
	/**
	 * 
	 * @描述: 系统错误
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/error")
	public void error(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, Integer errorcode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", errorcode);
		map.put("errorInfo", "您没有登录");
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * header
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/header")
	public ModelAndView header(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/header");
		return mv;
	}

	/**
	 * test
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/test");
		return mv;
	}
}