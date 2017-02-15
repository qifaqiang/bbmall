package com.wxsoft.xyd.front.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.SysSmsCheck;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.SysCouponsService;
import com.wxsoft.xyd.common.service.SysSmsCheckService;
import com.wxsoft.xyd.common.service.UserWxService;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.system.model.SysCouponsConf;
import com.wxsoft.xyd.system.service.SysCouponsConfService;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 分享赢取红包
 * 
 * @author kyz
 * 
 */
@Controller
@RequestMapping(SystemConfig.SYSTEM_PATH_FRONT_WAP + "/wxShare")
public class WxShareRedBagController extends BaseController {
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private SysCouponsService sysCouponsService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private SysCouponsConfService sysCouponsConfService;
	@Autowired
	private SysSmsCheckService sysSmsCheckService;
	@Autowired
	private UserWxService userWxService;
	@Autowired
	private UserService userService;

	/**
	 * 转向红包处理
	 * 
	 * @param request
	 * @param response
	 * @param phone
	 * @param code
	 * @param ordersn
	 * @throws IOException
	 */
	@RequestMapping("/toWxShareCode")
	public void toWxShareCode(HttpServletRequest request,
			HttpServletResponse response, String phone, String code,
			String ordersn) throws IOException {
		JSONObject json = new JSONObject();
		try {
			SysSmsCheck ssc = new SysSmsCheck();
			ssc.setMobile(phone);
			ssc.setCode(code);
			JSONObject jr = phoneValidate(ssc);
			if (jr.get(BaseConfig.RESCODE) != "0") {
				responseAjax(jr, response);
				return;
			} else {
				String appid = SystemConfig.APPID;
				String backUri = BaseConfig.FX_DOMAIN_WWW
						+ "/wap/wxShare/getSnsInfo.html";
				// backUri = backUri + "?mobile=" +mobile;
				backUri = backUri + "?mobile=" + phone + "&ordersn=" + ordersn;
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
						+ "appid="
						+ appid
						+ "&redirect_uri="
						+ URLEncoder.encode(backUri)
						+ "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
				/* response.sendRedirect(url); */
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "领取的路上");
				json.put(BaseConfig.RESURL, url);
				responseAjax(json, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取用户的信息和处理红包
	 * 
	 * @param request
	 * @param response
	 * @param ifRmobile
	 * @param mobile
	 * @param code
	 * @param ordersn
	 * @throws IOException
	 */
	@RequestMapping("/getSnsInfo")
	public void SnsInfo(HttpServletRequest request,
			HttpServletResponse response, String ifRmobile, String mobile,
			String code, String ordersn) throws IOException {
		String url = "notfound.html";
		Orders order = new Orders();
		order.setOrdersn(ordersn);
		System.out.println(code + "-------------------------------------->");
		Orders orders = ordersService.selectByOrders(order);
		if (orders != null) {

			String status = orders.getStatus();

			if (status.equals("20") || status.equals("22")
					|| status.equals("30") || status.equals("40")) {// 判断订单
				url = sysCouponsRecordService.insertRedBag(ifRmobile, code,
						ordersn, mobile);

			}
		}
		response.sendRedirect(url);
	}

	/**
	 * 判断订单是否可以分享
	 * 
	 * @param request
	 * @param response
	 * @param orderId
	 */
	@RequestMapping("/judgmentOrderState")
	public void judgmentOrderState(HttpServletRequest request,
			HttpServletResponse response, String ordersn) {

		JSONObject json = new JSONObject();
		Orders order = new Orders();
		order.setOrdersn(ordersn);
		Orders orders = ordersService.selectByOrders(order);
		String result = "0";
		if (orders != null) {
			json.put("orders", orders);
			result = "1";
		}
		json.put("orders", orders);
		json.put("result", result);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/redBagRule")
	public void redBagRule(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		SysCouponsConf sysCouponsConf = new SysCouponsConf();
		List<SysCouponsConf> list = sysCouponsConfService
				.getAllBySysCouponsConf(sysCouponsConf);
		if (list != null && list.size() > 0) {
			json.put("conf", list.get(0));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/allRedBagByOrderSn")
	public void allRedBagByOrderSn(HttpServletRequest request,
			HttpServletResponse response, String ordersn) {
		JSONObject json = new JSONObject();
		SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
		sysCouponsRecord.setOrderSn(ordersn);
		List<Map<String, Object>> list = sysCouponsRecordService
				.listByOrderSnRedBag(sysCouponsRecord);
		json.put("list", list);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 去领取红包
	 * 
	 * @param request
	 * @param response
	 * @param phone
	 * @param pwd
	 * @param code
	 */
	@RequestMapping("/redBagReceived")
	public ModelAndView toNextCoupon(HttpServletRequest request,
			HttpServletResponse response, String mobile, String pwd,
			String ordersn, String redPrice) {
		if (redPrice.equals("aleradyG")) {
			redPrice = "已抢过";
		} else if (redPrice.equals("aleradyW")) {
			redPrice = "已抢完";
		} else {
			redPrice = redPrice + "元";
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("mobile", mobile);
		mv.addObject("ordersn", ordersn);
		mv.addObject("redPrice", redPrice);
		mv.setViewName("/front/wap/redBagReceived");
		return mv;

	}

	@RequestMapping("/selectIfHaveRecord")
	public void selectIfHaveRecord(HttpServletRequest request,
			HttpServletResponse response, String ordersn, String mobile) {

		JSONObject json = new JSONObject();
		int result = 0;
		SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
		sysCouponsRecord.setOrderSn(ordersn);
		sysCouponsRecord.setMobile(mobile);
		sysCouponsRecord = sysCouponsRecordService
				.selectBySysCouponsRecord(sysCouponsRecord);
		if (sysCouponsRecord != null) {
			result = 1;

		}
		json.put("result", result);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查询个数
	 * 
	 * @param request
	 * @param response
	 * @param ordersn
	 */
	@RequestMapping("/selecCountByOrdersn")
	public void selecCountByOrdersn(HttpServletRequest request,
			HttpServletResponse response, String ordersn) {
		JSONObject json = new JSONObject();
		SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
		sysCouponsRecord.setOrderSn(ordersn);
		int count = sysCouponsRecordService
				.selectCountByOrdersn(sysCouponsRecord);
		json.put("count", count);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 校验验证码
	 */
	@Override
	public JSONObject phoneValidate(SysSmsCheck ssc) {
		JSONObject json = new JSONObject();

		SysSmsCheck selectSsc = new SysSmsCheck();
		selectSsc.setMobile(ssc.getMobile());
		SysSmsCheck sysSmsCheck = sysSmsCheckService
				.selectBySysSmsCheck(selectSsc);
		if (sysSmsCheck == null) {
			// 没有查询到当前用户的验证码信息
			json.put(BaseConfig.RESCODE, "ER1017");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1017").toString());
		} else {
			long addtime = sysSmsCheck.getAddtime().getTime();
			long newtime = new Date().getTime();
			if (!sysSmsCheck.getCode().equals(ssc.getCode())) {
				// 手机验证码不正确
				json.put(BaseConfig.RESCODE, "ER1018");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1018").toString());
			} else if ((addtime + SystemConfig.SMS_TIMEOUT) < newtime) {
				json.put(BaseConfig.RESCODE, "ER1019");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("user.ER1019").toString());
			} else {
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "");
			}
			// sysSmsCheckService.deleteByPrimaryKey(sysSmsCheck.getId());
		}
		return json;
	}

}
