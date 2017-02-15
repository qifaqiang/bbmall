package com.wxsoft.xyd.front.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.GenerateNo;
import com.wxsoft.framework.util.Qrcode;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.util.wxpay.CommonUtil;
import com.wxsoft.framework.util.wxpay.ConfigUtil;
import com.wxsoft.framework.util.wxpay.PayCommonUtil;
import com.wxsoft.framework.util.wxpay.XMLUtil;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.common.service.PromotionProductService;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.UserCartService;
import com.wxsoft.xyd.common.service.UserLocationService;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersLogService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoService;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.SysProportionService;
import com.wxsoft.xyd.system.service.UserService;

/**
 * wap端订单类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(SystemConfig.SYSTEM_PATH_FRONT_WAP + "/fontOrder")
public class FrontOrderController extends BaseController {
	@Autowired
	private UserCartService userCartService;
	@Autowired
	private UserService userService;
	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private PromotionProductService promotionProductService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private UserLocationService userLocationService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersDetailService ordersDetailService;
	@Autowired
	private ProductSpecificationInfoService productSpecificationInfoService;
	@Autowired
	private ProductSpecificationService productSpecificationService;
	@Autowired
	private OrdersLogService ordersLogService;
	@Autowired
	private SysProportionService sysProportionService;

	/**
	 * 创建订单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param cartItemIds
	 * @param companyId
	 * @param userShipType
	 * @return
	 */
	@RequestMapping("/createOrder")
	public String createOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String cartItems, Integer companyId, Integer shipAddressId,
			Integer userShipType, Integer sysCoupRecordId, String remark,
			Integer payType) {
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 获得当前登录用户
		user = userService.selectByPrimaryKey(user.getId());
		boolean isFirst = false;// 是否首单
		if (user.getIsFirstOrder() == 1) {
			isFirst = true;// 是首单
		}
		Map<String, Object> retMap;
		try {
			retMap = ordersService.insertAddOrder(cartItems,
					companyId, shipAddressId, userShipType, sysCoupRecordId,
					remark, payType, user.getId(), isFirst);
			if (retMap.get("recode").toString().equals("1")) {
				if (payType == 5) {// 微信支付
					if (retMap.get("ifzero") != null
							&& !retMap.get("ifzero").toString().equals("")) {
						return "redirect:" + BaseConfig.FX_DOMAIN_WWW
								+ "/wap/wxpay-success.html?orderSn="
								+ retMap.get("orderRealySn").toString();
					}
					return "redirect:" + BaseConfig.FX_DOMAIN_WWW
							+ "/wap/wxpay/toWxPayCode.html?orderid="
							+ retMap.get("orderId");
				}else if(payType == 4){//WAP支付宝
					if (retMap.get("ifzero") != null
							&& !retMap.get("ifzero").toString().equals("")) {
						return "redirect:" + BaseConfig.FX_DOMAIN_WWW
								+ "/wap/wxpay-success.html?orderSn="
								+ retMap.get("orderRealySn").toString();
					}
					return "redirect:" + BaseConfig.FX_DOMAIN_WWW
							+ "/alipay/wapjstoorder.html?orderid="
							+  retMap.get("orderId");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//订单失败
			session.setAttribute("createOrderError", e.getMessage());
			return "redirect:" + BaseConfig.FX_DOMAIN_WWW
					+ "/wap/orderFail.html";
		}
		return null;
	}

	@RequestMapping("/createPcOrder")
	public String createPcOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String cartItems, Integer companyId, Integer shipAddressId,
			Integer userShipType, Integer sysCoupRecordId, String remark,
			Integer payType) {
		System.out.println(userShipType
				+ "配送方式-----------------------------------------------》");
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 获得当前登录用户
		user = userService.selectByPrimaryKey(user.getId());
		boolean isFirst = false;// 是否首单
		if (user.getIsFirstOrder() == 1) {
			isFirst = true;// 是首单
		}
		Map<String, Object> retMap;
		try {
			retMap = ordersService.insertAddOrder(cartItems,
					companyId, shipAddressId, userShipType, sysCoupRecordId,
					remark, payType, user.getId(), isFirst);
			if (retMap.get("recode").toString().equals("1")) {
				if (retMap.get("ifzero") != null
						&& !retMap.get("ifzero").toString().equals("")) {
					// 订单详情页
					return "redirect:" + BaseConfig.FX_DOMAIN_WWW
							+ "/myorder_details.html?id="
							+ retMap.get("orderId");

				} else {
					return "redirect:" + BaseConfig.FX_DOMAIN_WWW
							+ "/wap/fontOrder/toPcToPay.html?orderid="
							+ retMap.get("orderId") + "&orderAccount="
							+ retMap.get("orderAccount") + "&pcOrdersn="
							+ retMap.get("pcOrdersn");
				}
			}else{
				//订单失败
				session.setAttribute("createOrderError","系统异常请联系管理员！");
				return "redirect:" + BaseConfig.FX_DOMAIN_WWW
						+ "/orderFail.html";
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//订单失败
			session.setAttribute("createOrderError", e.getMessage());
			return "redirect:" + BaseConfig.FX_DOMAIN_WWW
					+ "/orderFail.html";
		}

	}

	/**
	 * For App
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param cartItems
	 * @param companyId
	 * @param shipAddressId
	 * @param userShipType
	 * @param sysCoupRecordId
	 * @param remark
	 * @param payType
	 */
	@RequestMapping("/createAppOrder")
	public void createAppOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String cartItems, Integer companyId, Integer shipAddressId,
			Integer userShipType, Integer sysCoupRecordId, String remark,
			Integer payType) {
		JSONObject json = new JSONObject();
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);
		user = userService.selectByPrimaryKey(user.getId());
		boolean isFirst = false;// 是否首单
		if (user.getIsFirstOrder() == 1) {
			isFirst = true;
		}
		Map<String, Object> retMap;
		try {
			retMap = ordersService.insertAddOrder(cartItems,
					companyId, shipAddressId, userShipType, sysCoupRecordId,
					remark, payType, user.getId(), isFirst);
			json.put("map", retMap);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/toPcToPay")
	public ModelAndView toPcToPay(HttpServletRequest request,
			HttpServletResponse response, Integer orderid, String orderAccount,
			String pcOrdersn) {
		ModelAndView mv = new ModelAndView();
		Orders orders = ordersService.selectByPrimaryKey(orderid);
		if (null != orders && orders.getStatus().equals("11")) {
			mv.addObject("orderAccount", orders.getOrderAccount());
			mv.addObject("orderid", orderid);
			mv.addObject("pcOrdersn", orders.getOrdersn());
			mv.setViewName("/front/shoppingcart_pay");
		} else {
			mv.addObject("error", "订单已经支付，请返回我的订单订单列表重新付款！");
			mv.setViewName("/front/payFail");
		}

		return mv;

	}

	@RequestMapping("/shoppingcartWxScanPay")
	public ModelAndView shoppingcartWxScanPay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Integer orderid) {

		session.setAttribute("wxPayOrderId", orderid);
		ModelAndView mv = new ModelAndView();
		Orders orders = ordersService.selectByPrimaryKey(orderid);
		if (null != orders && orders.getStatus().equals("11")) {
			mv.setViewName("/front/shoppingcart_pay_wxscan");
		} else {
			mv.addObject("error", "订单已经支付，请返回我的订单订单列表重新付款！");
			mv.setViewName("/front/payFail");
		}
		return mv;
	}

	// 优惠券
	public SysCouponsRecord getSysCoupRecord(Integer id) {
		SysCouponsRecord sysCouponsRecord = sysCouponsRecordService
				.selectByPrimaryKey(id);
		return sysCouponsRecord;
	}

	/***
	 * 
	 * 校验优惠券
	 */
	@RequestMapping("/checkMortgage")
	public void checkMortgage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String price,
			Integer id) {
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 获得当前登录用户
		JSONObject json = new JSONObject();
		int result = 0;
		SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
		sysCouponsRecord.setUserId(user.getId());
		sysCouponsRecord.setId(id);
		sysCouponsRecord = sysCouponsRecordService
				.selectBySysCouponsRecord(sysCouponsRecord);
		if (sysCouponsRecord != null) {
			BigDecimal allPrice = new BigDecimal(price);
			BigDecimal needPrice = sysCouponsRecord.getNeedPrice();
			if (allPrice.compareTo(needPrice) >= 0) {
				result = 1;
			}
		}
		json.put("result", result);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
