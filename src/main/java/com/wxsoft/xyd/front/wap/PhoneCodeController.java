package com.wxsoft.xyd.front.wap;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.SmsUtil;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysSmsCheck;
import com.wxsoft.xyd.common.service.SysSmsCheckService;

/**
 * 
 * @author kyz
 * 
 */
@Controller
public class PhoneCodeController extends BaseController {
	@Autowired
	private SysSmsCheckService sysSmsCheckService;

	/**
	 * 手机验证码
	 * 
	 * @param request
	 * @param response
	 * @param phone
	 * @param type
	 * @param session
	 */
	@RequestMapping(value = "/getPhoneCode", method = RequestMethod.POST)
	public void getPhoneCode(HttpServletRequest request,
			HttpServletResponse response, String phone, String type,
			HttpSession session) {
		JSONObject json = new JSONObject();
		boolean can = true;
		try {
			if (Tools.isEmpty(phone) || Tools.isEmpty(type)) {
				// 存在必填项未填写
				json.put(BaseConfig.RESCODE, "ER1101");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1101").toString());
				responseAjax(json, response);
				return;
			}
			if (!phone
					.matches("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")) {
				// 手机号不正确
				json.put(BaseConfig.RESCODE, "ER1014");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1014").toString());
				responseAjax(json, response);
				return;
			}
			SysSmsCheck ssc = new SysSmsCheck();
			ssc.setMobile(phone);
			// ssc.setCode(getCode());
			SysSmsCheck sysSmsCheck = sysSmsCheckService
					.selectBySysSmsCheck(ssc);
			ssc.setCode(getCode());
			ssc.setAddtime(new Date());
			if (sysSmsCheck == null) {
				if (sysSmsCheckService.insertSelective(ssc) > 0) {
				} else {
					can = false;
					json.put(BaseConfig.RESCODE, "-1");
					json.put(BaseConfig.RESMESSAGE, "系统错误");
					responseAjax(json, response);
					return;
				}
			} else {
				if (sysSmsCheck.getCount() > 10) {
					// 获取验证码过于频繁，请稍后再试
					can = false;
					json.put(BaseConfig.RESCODE, "ER1040");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("phone.ER1040").toString());
					responseAjax(json, response);
					return;
				} else {
					ssc.setId(sysSmsCheck.getId());
					if (sysSmsCheckService.updateByPrimaryKeySelective(ssc) > 0) {

					} else {
						can = false;
						json.put(BaseConfig.RESCODE, "-1");
						json.put(BaseConfig.RESMESSAGE, "系统错误");
						responseAjax(json, response);
						return;
					}
				}
			}
			if (can) {
				boolean bo = SmsUtil.sendSms(phone, Integer.parseInt(type),
						ssc.getCode());
				if (!bo) {
					// 手机验证码验证通过
					json.put(BaseConfig.RESCODE, "ER1021");
					json.put(BaseConfig.RESMESSAGE,
							BaseConfig.MESSAGE.get("user.ER1021").toString());
					responseAjax(json, response);
					return;
				}
				json.put(BaseConfig.RESCODE, "0");
				responseAjax(json, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static String getCode() {
		Random random = new Random();
		return ((int) (random.nextFloat() * 1000000)) + "";
	}

	@RequestMapping(value = "/phoneCodeValidate")
	public void phoneCodeValidate(HttpServletRequest request,
			HttpServletResponse response, String phone, String phoneCode,
			HttpSession session) {
		try {
			// 手机验证码验证
			SysSmsCheck ssc = new SysSmsCheck();
			ssc.setMobile(phone);
			ssc.setCode(phoneCode);
			JSONObject jr = phoneValidate(ssc);
			responseAjax(jr, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
