package com.wxsoft.xyd.front.wap;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.util.alipay.AlipayNotify;
import com.wxsoft.framework.util.unionpay.AcpService;
import com.wxsoft.framework.util.unionpay.SDKConstants;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 
 * web 端普通頁面跳转
 * 
 * @author kyz
 * @date 2015年7月17日下午8:52:23
 */
@Controller
@RequestMapping("/")
public class WebController extends BaseController {
	@Autowired
	private UserService userService;

	/**
	 * 商品页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/productDetail")
	public ModelAndView productDetail(Integer prodId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/productDetail");
		return mv;
	}


	/**
	 * 普通页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/{page}")
	public String page(@PathVariable String page) {
		return SystemConfig.SYSTEM_PATH_FRONT + "/" + page;
	}

}
