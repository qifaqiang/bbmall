package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.common.model.SysFeedback;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.SysFeedbackService;

/**
 * 意见反馈
 * @author kyz
 *
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES
		+ "/sysFeedback")
public class SysFeedbackFrontController extends BaseController {

	@Autowired
	private SysFeedbackService sysFeedbackService;

	/**
	 * 新增个人建议
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sysFeedAdd")
	public void sysFeedAdd(SysFeedback sysFeedback,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		sysFeedback.setUserId(null == su ? 0 : su.getId());
		if (sysFeedbackService.insertSelective(sysFeedback) > 0) {
			json.put("res_code", "0");
			json.put("message", "感谢您对我们的支持");
			json.put(BaseConfig.RESURL, "personalCenter.html");
		} else {
			json.put("res_code", "ER1017");
			json.put("message", BaseConfig.MESSAGE.get("sysFeedback.ER1017"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
