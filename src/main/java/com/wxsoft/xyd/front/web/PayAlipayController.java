package com.wxsoft.xyd.front.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.GenerateNo;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.util.alipay.AlipayConfig;
import com.wxsoft.framework.util.alipay.AlipayNotify;
import com.wxsoft.framework.util.alipay.AlipaySubmit;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.system.service.UserService;
import com.wxsoft.xyd.system.service.WxpayRecordService;

/**
 * 
 * 支付宝在线支付
 * 
 * @author kyz
 * @date 2015年7月17日下午8:52:23
 */
@Controller
@RequestMapping("/alipay")
public class PayAlipayController extends BaseController {
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
	 * 下单
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/jstoorder", method = RequestMethod.GET)
	public ModelAndView jsToOrder(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String ordersn) {
		logger.info("~~~~~~~~~~~~~~~~支付宝 支付 开始~~~~~~~~~");
		logger.info("ordersn:" + ordersn);

		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		ModelAndView mv = new ModelAndView();
		if (null != su) {
			Orders temp = new Orders();
			temp.setOrdersn(ordersn);
			Orders orders = ordersService.selectByOrders(temp);

			mv.addObject("orderSn", orders.getOrdersn());
			mv.addObject("price", orders.getOrderAccount());
			mv.addObject("time",
					Tools.date2Str(orders.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			if (orders.getStatus().equals("11")) {
				// 判断当前用户是不是该订单的userId
				if (orders.getUserId().intValue() == su.getId().intValue()) {
					mv.setViewName("front/toPay");
					OrdersDetail od = new OrdersDetail();
					od.setOrderId(orders.getId());
					List<OrdersDetail> odlist = ordersDetailService
							.getAllByOrdersDetail(od);
					String prodname = "";
					for (OrdersDetail ordersDetail : odlist) {
						prodname += ordersDetail.getProdName() + ",";
					}

					Orders order_t2 = new Orders();
					order_t2.setId(orders.getId());
					order_t2.setTradingCode(GenerateNo.payRecordNo());//
					// 生成随机的支付流水号
					int flag = ordersService
							.updateByPrimaryKeySelective(order_t2);
					if (flag > 0) {

						// //////////////////////////////////请求参数//////////////////////////////////////

						// 商户订单号，商户网站订单系统中唯一订单号，必填
						String out_trade_no = order_t2.getTradingCode();

						// 订单名称，必填
						String subject = "齐鲁干烘茶城";

						// 付款金额，必填
						String total_fee = new String(String.valueOf(orders
								.getOrderAccount()));

						// 商品描述，可空
						String body = "";
						if (prodname.length() > 90) {
							body = prodname.substring(0, 90) + "...";
						} else {
							body = prodname.substring(0, prodname.length() - 1);
						}

						// ////////////////////////////////////////////////////////////////////////////////

						// 把请求参数打包成数组
						Map<String, String> sParaTemp = new HashMap<String, String>();
						sParaTemp.put("service", AlipayConfig.service);
						sParaTemp.put("partner", SystemConfig.partner);
						sParaTemp.put("seller_id", SystemConfig.seller_id);
						sParaTemp.put("_input_charset",
								AlipayConfig.input_charset);
						sParaTemp
								.put("payment_type", AlipayConfig.payment_type);
						sParaTemp.put("notify_url", AlipayConfig.notify_url);
						sParaTemp.put("return_url", AlipayConfig.return_url);
						sParaTemp.put("anti_phishing_key",
								AlipayConfig.anti_phishing_key);
						sParaTemp.put("exter_invoke_ip",
								AlipayConfig.exter_invoke_ip);
						sParaTemp.put("out_trade_no", out_trade_no);
						sParaTemp.put("subject", subject);
						sParaTemp.put("total_fee", total_fee);
						sParaTemp.put("body", body);
						// 建立请求
						String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,
								"get", "确认");
						logger.info("~~~~~~~~~~~~~~~~支付宝 支付 建立请求~~~~~~~~~");
						logger.info(sHtmlText);
						mv.addObject("obj", sHtmlText);
						return mv;
					} else {
						mv.addObject("error", "生成订单流水号失败，请返回我的订单列表重新付款！");
						mv.setViewName("front/payFail");
						return mv;
					}
				} else {
					mv.addObject("error", "该订单不可以支付，请返回我的订单列表重新付款！");
					mv.setViewName("front/payFail");
					return mv;
				}
			} else {
				mv.addObject("error", "订单出现异常，请返回我的订单订单列表重新付款！");
				mv.setViewName("front/payFail");
				return mv;
			}

		}
		mv.setViewName("front/payFail");
		mv.addObject("error", "您还没有登录，请登录后支付！");
		return mv;
	}

	/**
	 * 支付成功页面
	 * 
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/paySuccessAlipay")
	public ModelAndView paySuccessAlipay(HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		logger.info("~~~~~~~~~~~~~~~~支付宝 支付支付成功请求~~~~~~~~~");

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			params.put(name, valueStr);
			logger.info("name:" + name + ",valueStr:" + valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		String total_fee = new String(request.getParameter("total_fee")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);

		if (verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				Orders order = new Orders();
				order.setTradingCode(out_trade_no);
				order = ordersService.selectByOrders(order);

				mv.addObject("ordersn", order.getOrdersn());
				mv.addObject("orderPrice", order.getOrderAccount());
				mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/paySuccess");
			}

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {
			// 该页面可做页面美工编辑
			mv.addObject("error", "支付失败请联系管理员");
			mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/payFail");
		}
		mv.addObject("zhifu", "支付宝");
		return mv;

	}

	/**
	 * 支付异步通知
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/notifyurl", method = RequestMethod.POST)
	public void notifyUrl(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("~~~~~~~~~~~~~~~~支付宝 支付支付  成功  后台请求~~~~~~~~~");
		PrintWriter out = response.getWriter();// 获取PrintWriter输出流

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
			logger.info("name:" + name + ",valueStr:" + valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
				logger.info("~~~~~~~~~~~~~~~~支付宝 支付  成功  ~~~~~~~~~");

				List<Orders> orderList = ordersService
						.getAllByOrdersWithTradingCode(out_trade_no);
				for (Orders o : orderList) {
					Orders order = new Orders();
					order.setId(o.getId());
					order.setStatus("20");
					order.setPayForm(1);
					order.setPayStatus("1");
					order.setIs_over(0);
					order.setSerialid(trade_no);
					order.setPayTime(new Date());
					ordersService.updateByPayNow(order, 1);
				}
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			out.println("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			out.println("fail");
		}
	}
	
	
	
	
	/**
	 * 支付宝WAP支付
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/wapjstoorder", method = RequestMethod.GET)
	public ModelAndView wapJsToOrder(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			String orderid) {
		logger.info("~~~~~~~~~~~~~~~~支付宝WAP 支付 开始~~~~~~~~~");
		logger.info("orderid:" + orderid);

		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		ModelAndView mv = new ModelAndView();
		if (null != su) {
			Orders temp = new Orders();
			temp.setId(Integer.parseInt(orderid));
			Orders orders = ordersService.selectByOrders(temp);

			mv.addObject("orderSn", orders.getOrdersn());
			mv.addObject("price", orders.getOrderAccount());
			mv.addObject("time",
					Tools.date2Str(orders.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			if ("11".equals(orders.getStatus())) {
				// 判断当前用户是不是该订单的userId
				if (orders.getUserId().intValue() == su.getId().intValue()) {
					mv.setViewName("front/toPay");
					
					OrdersDetail od = new OrdersDetail();
					od.setOrderId(orders.getId());
					List<OrdersDetail> odlist = ordersDetailService
							.getAllByOrdersDetail(od);
					String prodname = "";
					for (OrdersDetail ordersDetail : odlist) {
						prodname += ordersDetail.getProdName() + ",";
					}
					
					
					Orders order_t2 = new Orders();
					order_t2.setId(orders.getId());
					order_t2.setTradingCode(GenerateNo.payRecordNo());//
					// 生成随机的支付流水号
					int flag = ordersService
							.updateByPrimaryKeySelective(order_t2);
					if (flag > 0) {

						// //////////////////////////////////请求参数//////////////////////////////////////
						// 商户订单号，商户网站订单系统中唯一订单号，必填
						String out_trade_no = order_t2.getTradingCode();

						// 订单名称，必填
						String subject = "齐鲁干烘茶城";

						// 付款金额，必填
						String total_fee = new String(String.valueOf(orders
								.getOrderAccount()));

						// 商品描述，可空
						String body = "";
						if (prodname.length() > 90) {
							body = prodname.substring(0, 90) + "...";
						} else {
							body = prodname.substring(0, prodname.length() - 1);
						}

						// ////////////////////////////////////////////////////////////////////////////////

						// 把请求参数打包成数组
						Map<String, String> sParaTemp = new HashMap<String, String>();
						sParaTemp.put("service", AlipayConfig.wap_service);
						sParaTemp.put("partner", SystemConfig.partner);
						sParaTemp.put("seller_id",SystemConfig.seller_id);
						sParaTemp.put("sign_type", AlipayConfig.sign_type);
						sParaTemp.put("_input_charset",AlipayConfig.input_charset);
						sParaTemp.put("payment_type", AlipayConfig.payment_type);
						sParaTemp.put("notify_url",
								AlipayConfig.wapNotify_url);
						sParaTemp.put("return_url",
								AlipayConfig.return_url);
						sParaTemp.put("anti_phishing_key",
								AlipayConfig.anti_phishing_key);
						sParaTemp.put("exter_invoke_ip",
								AlipayConfig.exter_invoke_ip);
						sParaTemp.put("out_trade_no", out_trade_no);
						sParaTemp.put("subject", subject);
						sParaTemp.put("total_fee", total_fee);
						sParaTemp.put("body", body);
//						// 建立请求
//						// String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get", "确认");
//						logger.info("~~~~~~~~~~~~~~~~支付宝  WAP支付 建立请求~~~~~~~~~");
//
//						// 待请求参数数组
//						String url = AlipaySubmit.buildRequestReturnURL(
//								sParaTemp, "get", "");
//						logger.info(url);
//						return new ModelAndView("redirect:"+url);
						// 建立请求
						String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,
								"get", "确认");
						logger.info("~~~~~~~~~~~~~~~~支付宝  WAP支付 建立请求~~~~~~~~~");
						logger.info(sHtmlText);
						mv.addObject("obj", sHtmlText);
						return mv;
						
					} else {
						mv.addObject("error", "生成订单流水号失败，请返回我的订单列表重新付款！");
						mv.setViewName("/front/wap/wxpay-error");
						return mv;
					}
				} else {
					mv.addObject("error", "该订单不可以支付，请返回我的订单列表重新付款！");
					mv.setViewName("/front/wap/wxpay-error");
					return mv;
				}
			} else {
				mv.addObject("error", "订单出现异常，请返回我的订单订单列表重新付款！");
				mv.setViewName("/front/wap/wxpay-error");
				return mv;
			}

		}
		mv.setViewName("/front/wap/wxpay-error");
		mv.addObject("error", "您还没有登录，请登录后支付！");
		return mv;
	}

	/**
	 * WAP支付异步通知
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/wapNotifyurl", method = RequestMethod.POST)
	public void wapNotifyUrl(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("~~~~~~~~~~~~~~~~支付宝  WAP支付支付  成功  后台请求~~~~~~~~~");
		PrintWriter out = response.getWriter();// 获取PrintWriter输出流

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
			logger.info("name:" + name + ",valueStr:" + valueStr);
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
				logger.info("~~~~~~~~~~~~~~~~支付宝 支付WAP  成功  ~~~~~~~~~");

				List<Orders> orderList = ordersService
						.getAllByOrdersWithTradingCode(out_trade_no);
				for (Orders o : orderList) {
					Orders order = new Orders();
					order.setId(o.getId());
					order.setStatus("20");
					order.setPayForm(4);//wap（支付宝）
					order.setPayStatus("1");
					order.setIs_over(0);
					order.setSerialid(trade_no);
					order.setPayTime(new Date());
					ordersService.updateByPayNow(order, 1);
				}
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			out.println("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			out.println("fail");
		}
	}
	
}
