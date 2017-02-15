package com.wxsoft.xyd.front.wap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 
 * wap 端普通頁面跳转
 * 
 * @author kyz
 * @date 2015年7月17日下午8:52:23
 */
@Controller
@RequestMapping("/wap")
public class WapController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 普通页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/{page}")
	public String page(@PathVariable String page) {
		return SystemConfig.SYSTEM_PATH_FRONT+SystemConfig.SYSTEM_PATH_FRONT_WAP + page;
	}

}
