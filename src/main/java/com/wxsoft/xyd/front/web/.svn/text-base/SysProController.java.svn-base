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
import com.wxsoft.xyd.system.model.SysProportion;
import com.wxsoft.xyd.system.service.SysProportionService;

/**
 * 系统配置查询
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/sysProp")
public class SysProController extends BaseController {

	@Autowired
	private SysProportionService sysProportionService;

	/**
	 * 查询息
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/sysProList")
	public void sysProList(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		SysProportion list = BaseConfig.sysProportion;
		json.put("res_code", "0");
		json.put("message", "success");
		json.put("list", list);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
