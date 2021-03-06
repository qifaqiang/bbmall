package com.wxsoft.xyd.front.wap;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
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
import com.wxsoft.framework.util.wxpay.wxHttpUtil;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.system.model.WxpayRecord;
import com.wxsoft.xyd.system.service.UserService;
import com.wxsoft.xyd.system.service.WxpayRecordService;

/**
 * 
 * 微信支付
 * 
 * @author kyz
 * @date 2015年7月17日下午8:52:23
 */
@Controller
@RequestMapping("/wap/wxpay")
public class WxPayController extends BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersDetailService ordersDetailService;
	@Autowired
	private WxpayRecordService wxpayRecordService;

	/**
	 * 获取code
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/toWxPayCode")
	public void toWxPayToGetCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String orderid = request.getParameter("orderid");
		String payType = request.getParameter("payType");
		String appid = SystemConfig.APPID;
		String backUri = BaseConfig.FX_DOMAIN_WWW + "/wap/wxpay/jstoorder.html";
		backUri = backUri + "?orderid=" + orderid + "&payType=" + payType;
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="
				+ appid
				+ "&redirect_uri="
				+ URLEncoder.encode(backUri)
				+ "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
	}

	/**
	 * 下单
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jstoorder", method = RequestMethod.GET)
	public ModelAndView jsToOrder(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			Integer orderid, String payType) {
		logger.info("~~~~~~~~~~~~~~~~微信支付 JSAPI 开始~~~~~~~~~");
		ModelAndView mv = new ModelAndView();
		Orders orders = ordersService.selectByPrimaryKey(orderid);
		if (null != orders) {
			if (orders.getStatus().equals("11")) {
				mv.addObject("orderSn", orders.getOrdersn());
				mv.addObject("price", orders.getOrderAccount());
				mv.addObject("time", Tools.date2Str(orders.getAddtime(),
						"yyyy-MM-dd HH:mm:ss"));
				// 判断当前用户是不是该订单的userId
				mv.setViewName("/front/wap/wxpay");
				OrdersDetail od = new OrdersDetail();
				od.setOrderId(orderid);
				List<OrdersDetail> odlist = ordersDetailService
						.getAllByOrdersDetail(od);
				String prodname = "";
				for (OrdersDetail ordersDetail : odlist) {
					prodname += ordersDetail.getProdName() + ",";
				}
				String code = request.getParameter("code");
				System.out.println(code + "this is code ");
				String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
						+ SystemConfig.APPID
						+ "&secret="
						+ SystemConfig.APPSECRET
						+ "&code="
						+ code
						+ "&grant_type=authorization_code";
				System.out.println("this is Url" + URL);
				JSONObject jsonObject = wxHttpUtil.httpsRequest(URL, "GET",
						null);
				System.out.println("OPENID" + jsonObject.getString("openid"));
				String openId = jsonObject.getString("openid");
				if (Tools.notEmpty(openId)) {
					openId = jsonObject.getString("openid");
				} else {
					System.out.println("获取用户标志失败");
					mv.addObject("error", "微信认证失败，请重试！");
					mv.setViewName("/front/wap/wxpay-error");
					return mv;
				}

				Orders order_t2 = new Orders();
				order_t2.setId(orderid);
				order_t2.setTradingCode(GenerateNo.payRecordNo());//
				// 生成随机的支付流水号
				int flag = ordersService.updateByPrimaryKeySelective(order_t2);
				if (flag > 0) {
					SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
					parameters.put("appid", SystemConfig.APPID);
					parameters.put("mch_id", SystemConfig.PARTNER);
					parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
					parameters.put("body",
							prodname.substring(0, prodname.length() - 1));
					parameters.put("out_trade_no", order_t2.getTradingCode());

					String free = String.valueOf(orders.getOrderAccount()
							.doubleValue() * 100);

					parameters.put("total_fee",
							free.substring(0, free.lastIndexOf(".")));

					parameters.put("spbill_create_ip", request.getRemoteAddr());
					parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
					parameters.put("trade_type", "JSAPI");

					parameters.put("openid", openId);
					String sign = PayCommonUtil.createSign("UTF-8", parameters,
							SystemConfig.PARTNERKEY);
					parameters.put("sign", sign);
					System.out.println("is heree " + sign + "sign");
					String requestXML = PayCommonUtil.getRequestXml(parameters);
					String resultpay = CommonUtil.httpsRequest(
							ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
					logger.info("微信支付 签名to 微信：" + resultpay);
					Map<String, String> map = null;
					try {
						map = XMLUtil.doXMLParse(resultpay);
					} catch (JDOMException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					SortedMap<Object, Object> params = new TreeMap<Object, Object>();
					System.out.println(map);
					if (map.get("result_code").equals("FAIL")) {
						params.put("errorMessage", map.get("err_code_des")
								+ ",请取消订单后,重新下单");
					} else {
						params.put("errorMessage", "");
						params.put("appId", SystemConfig.APPID);
						params.put("timeStamp",
								Long.toString(new Date().getTime()));
						params.put("nonceStr", PayCommonUtil.CreateNoncestr());
						params.put("package",
								"prepay_id=" + map.get("prepay_id"));
						params.put("signType", ConfigUtil.SIGN_TYPE);
						String paySign = PayCommonUtil.createSign("UTF-8",
								params, SystemConfig.PARTNERKEY);
						params.put("packageValue",
								"prepay_id=" + map.get("prepay_id")); // 这里用packageValue是预防package是关键字在js获取值出错
						params.put("paySign", paySign); // paySign的生成规则和Sign的生成规则一致
						params.put("sendUrl", ConfigUtil.SUCCESS_URL); // 付款成功后跳转的页面
						String userAgent = request.getHeader("user-agent");
						char agent = userAgent.charAt(userAgent
								.indexOf("MicroMessenger") + 15);
						params.put("agent", new String(new char[] { agent }));// 微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。

					}
					String json = JSON.toJSONString(params);
					logger.info("微信支付 签名to 网页：" + json);
					mv.addObject("json", json);
					mv.addObject("ptime", System.currentTimeMillis() / 1000);
					return mv;
				} else {
					mv.addObject("error", "生成订单流水号失败，请返回我的订单列表重新付款！");
					mv.setViewName("front/payFail");
					return mv;
				}
			} else {
				mv.addObject("error", "订单出现异常，请返回我的订单订单列表重新付款！");
				mv.setViewName("/front/wap/wxpay-error");
				return mv;
			}
		} else {
			mv.addObject("error", "订单已经支付，请返回我的订单订单列表重新付款！");
			mv.setViewName("/front/wap/wxpay-error");
			return mv;
		}
	}

	/**
	 * 二维码链接
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/wxPayQrCode")
	public void testQrCode(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		String orderid = session.getAttribute("wxPayOrderId").toString();

		logger.info("~~~~~~~~~~~~~~~~微信支付 扫码 开始~~~~~~~~~");
		Orders orders = ordersService.selectByPrimaryKey(Integer
				.parseInt(orderid));
		Orders order_t2 = new Orders();
		order_t2.setId(orders.getId());
		order_t2.setTradingCode(GenerateNo.payRecordNo());// 生成随机的支付流水号
		int flag = ordersService.updateByPrimaryKeySelective(order_t2);
		if (flag > 0) {
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", SystemConfig.APPID);
			parameters.put("mch_id", SystemConfig.PARTNER);
			parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
			parameters.put("body", "齐鲁干烘茶城订单");
			parameters.put("out_trade_no", order_t2.getTradingCode());

			String free = String
					.valueOf(orders.getOrderAccount().doubleValue() * 100);

			parameters.put("total_fee",
					free.substring(0, free.lastIndexOf(".")));

			parameters.put("spbill_create_ip", request.getRemoteAddr());
			parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
			parameters.put("product_id", "123456789");
			parameters.put("trade_type", "NATIVE");

			String sign = PayCommonUtil.createSign("UTF-8", parameters,
					SystemConfig.PARTNERKEY);
			parameters.put("sign", sign);
			System.out.println("is heree " + sign + "sign");
			String requestXML = PayCommonUtil.getRequestXml(parameters);
			String resultpay = CommonUtil.httpsRequest(
					ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
			logger.info("微信支付 签名to 微信：" + resultpay);
			Map<String, String> map = null;
			try {
				map = XMLUtil.doXMLParse(resultpay);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SortedMap<Object, Object> params = new TreeMap<Object, Object>();
			System.out.println(map);
			if (map.get("result_code").equals("FAIL")) {
				params.put("errorMessage", map.get("err_code_des")
						+ ",请取消订单后,重新下单");
			} else {
				String url = map.get("code_url");
				Qrcode qr = new Qrcode();
				BufferedImage buf = qr.getImageSizeByImageReader(url);
				ImageIO.write(buf, "jpg", response.getOutputStream());
				return;
			}
		}

	}

	/**
	 * 支付异步通知
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/notifyurl", method = RequestMethod.POST)
	public void notifyUrl(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String resultpay = "";
		InputStream inStream = null;
		try {
			inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			logger.info("~~~~~~~~~~~~~~~~微信支付 付款接受~~~~~~~~~");
			outSteam.close();
			inStream.close();
			String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
			SortedMap<Object, Object> params = new TreeMap<Object, Object>();

			Map<Object, Object> map = XMLUtil.doXMLParse(result);
			for (Object keyValue : map.keySet()) {
				if (!keyValue.equals("sign")) {
					params.put(keyValue, map.get(keyValue));
				}
				logger.info(keyValue + "=" + map.get(keyValue));
			}
			String paySign = PayCommonUtil.createSign("UTF-8", params,
					SystemConfig.PARTNERKEY);
			logger.info("paySign=" + paySign);
			if (paySign.equals(map.get("sign"))) {

				WxpayRecord wr = new WxpayRecord();
				wr.setAppid(map.get("appid").toString());
				wr.setBankType(map.get("bank_type").toString());
				wr.setCashFee(map.get("cash_fee").toString());
				wr.setFeeType(map.get("fee_type").toString());
				wr.setIsSubscribe(map.get("is_subscribe").toString());
				wr.setMchId(map.get("mch_id").toString());
				wr.setNonceStr(map.get("nonce_str").toString());
				wr.setOpenid(map.get("openid").toString());
				wr.setOutTradeNo(map.get("out_trade_no").toString());
				wr.setResultCode(map.get("result_code").toString());
				wr.setReturnCode(map.get("return_code").toString());
				wr.setSign(map.get("sign").toString());
				wr.setTimeEnd(map.get("time_end").toString());
				wr.setTotalFee(map.get("total_fee").toString());
				wr.setTradeType(map.get("trade_type").toString());
				wr.setTransactionId(map.get("transaction_id").toString());
				wxpayRecordService.insertSelective(wr);
				if (map.get("result_code").toString()
						.equalsIgnoreCase("SUCCESS")
						&& map.get("return_code").toString()
								.equalsIgnoreCase("SUCCESS")) {
					logger.info("~~~~~~~~~~~~~~~~微信支付 付款成功~~~~~~~~~");
					// TODO 对数据库的操作
					resultpay = PayCommonUtil.setXML("SUCCESS", "");

					String orders = (String) map.get("out_trade_no");

					List<Orders> orderList = ordersService
							.getAllByOrdersWithTradingCode(orders);
					for (Orders o : orderList) {
						if (o.getStatus().equals("20")) {
							logger.error("当前订单已经支付：订单编号：" + orders);
						} else {
							Orders order = new Orders();
							order.setId(o.getId());
							order.setStatus("20");
							order.setPayForm(5);
							order.setPayStatus("1");
							order.setIs_over(0);
							order.setSerialid(wr.getTransactionId());
							order.setPayTime(new Date());
							ordersService.updateByPayNow(order, 5);
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			responseAjax(resultpay, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
