package com.wxsoft.xyd.front.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.GenerateNo;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.util.unionpay.AcpService;
import com.wxsoft.framework.util.unionpay.DemoBase;
import com.wxsoft.framework.util.unionpay.SDKConfig;
import com.wxsoft.framework.util.unionpay.SDKConstants;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.system.service.UserService;
import com.wxsoft.xyd.system.service.WxpayRecordService;

/**
 * 
 * wap 端普通頁面跳转 银联支付
 * 
 * @author kyz
 * @date 2015年7月17日下午8:52:23
 */
@Controller
@RequestMapping("/unionpay")
public class PayUnionController extends BaseController {
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
		logger.info("~~~~~~~~~~~~~~~~银联支付 开始~~~~~~~~~");
		logger.info("orderid:" + ordersn);
		ModelAndView mv = new ModelAndView();
		Orders temp = new Orders();
		temp.setOrdersn(ordersn);
		Orders orders = ordersService.selectByOrders(temp);
		if (null != orders) {
			mv.addObject("orderSn", orders.getOrdersn());
			mv.addObject("price",
					String.format("%.2f", orders.getOrderAccount()));
			mv.addObject("time",
					Tools.date2Str(orders.getAddtime(), "yyyy-MM-dd HH:mm:ss")); // 判断当前用户是不是该订单的userId
			if (orders.getStatus().equals("11")) {

				mv.setViewName("front/toPay");
				String prodname = "电商平台";
				Orders changeTradingCode = new Orders();
				changeTradingCode.setId(orders.getId());
				changeTradingCode.setTradingCode(GenerateNo.payRecordNo());//
				// 生成随机的支付流水号
				int flag = ordersService
						.updateByPrimaryKeySelective(changeTradingCode);
				if (flag > 0) {
					// 前台页面传过来的

					String free = String.valueOf(orders.getOrderAccount()
							.doubleValue() * 100);
					String txnAmt = free.substring(0, free.lastIndexOf("."));

					Map<String, String> requestData = new HashMap<String, String>();

					/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
					requestData.put("version", DemoBase.version); // 版本号，全渠道默认值
					requestData.put("encoding", DemoBase.encoding_UTF8); // 字符集编码，可以使用UTF-8,GBK两种方式
					requestData.put("signMethod", "01"); // 签名方法，只支持
															// 01：RSA方式证书加密
					requestData.put("txnType", "01"); // 交易类型 ，01：消费
					requestData.put("txnSubType", "01"); // 交易子类型，
															// 01：自助消费
					requestData.put("bizType", "000201"); // 业务类型，B2C网关支付，手机wap支付
					requestData.put("channelType", "07"); // 渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板
															// 08：手机

					/*** 商户接入参数 ***/
					requestData.put("merId", SystemConfig.UNION_MID); // 商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
					requestData.put("accessType", "0"); // 接入类型，0：直连商户
					requestData.put("orderId",
							changeTradingCode.getTradingCode()); // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
					requestData.put("txnTime", DemoBase.getCurrentTime()); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
					requestData.put("currencyCode", "156"); // 交易币种（境内商户一般是156
															// 人民币）
					requestData.put("txnAmt", txnAmt); // 交易金额，单位分，不要带小数点
					// requestData.put("reqReserved", "透传字段");
					// //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节
					requestData.put("orderDesc", prodname);

					// 前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面
					// 点击“返回商户”按钮的时候将异步通知报文post到该地址
					// 如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
					// 异步通知参数详见open.unionpay.com帮助中心 下载 产品接口规范
					// 网关支付产品接口规范 消费交易 商户通知
					requestData.put("frontUrl", BaseConfig.FX_DOMAIN_WWW
							+ "/unionpay/paySuccessUnion.html");

					// 后台通知地址（需设置为【外网】能访问 http
					// https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
					// 后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范
					// 网关支付产品接口规范 消费交易 商户通知
					// 注意:1.需设置为外网能访问，否则收不到通知 2.http https均可
					// 3.收单后台通知后需要10秒内返回http200或302状态码
					// 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
					// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
					// 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
					requestData.put("backUrl", BaseConfig.FX_DOMAIN_WWW
							+ "/unionpay/notifyurl.html");

					// ////////////////////////////////////////////////
					//
					// 报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
					//
					// ////////////////////////////////////////////////

					/** 请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面 **/
					Map<String, String> submitFromData = AcpService.sign(
							requestData, DemoBase.encoding_UTF8); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

					String requestFrontUrl = SDKConfig.getConfig()
							.getFrontRequestUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
					String html = AcpService.createAutoFormHtml(
							requestFrontUrl, submitFromData,
							DemoBase.encoding_UTF8); // 生成自动跳转的Html表单

					logger.info("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
					// 将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
					mv.addObject("obj", html);
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
			mv.addObject("error", "订单出现异常，请返回我的订单列表重新付款！");
			mv.setViewName("front/payFail");
			return mv;
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
	@RequestMapping(value = "/notifyurl", method = RequestMethod.POST)
	public void notifyUrl(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("~~~~~~~~~~~~~~~~银联支付 接受~~~~~~~~~");
		try {
			request.setCharacterEncoding("ISO-8859-1");
			String encoding = request.getParameter(SDKConstants.param_encoding);
			// 获取银联通知服务器发送的后台通知参数
			Map<String, String> reqParam = getAllRequestParam(request);

			Map<String, String> valideData = null;
			if (null != reqParam && !reqParam.isEmpty()) {
				Iterator<Entry<String, String>> it = reqParam.entrySet()
						.iterator();
				valideData = new HashMap<String, String>(reqParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes("ISO-8859-1"), encoding);
					valideData.put(key, value);
					logger.info("key=" + key + " ,value=" + value);
				}
			}

			// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
			if (!AcpService.validate(valideData, encoding)) {
				logger.info("验证签名结果[失败].");
				// 验签失败，需解决验签问题

			} else {
				logger.info("验证签名结果[成功].");
				// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

				String respCode = valideData.get("respCode"); // 获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
				if (respCode.equals("00")) {
					// 消费
					if (valideData.get("txnType").equals("01")) {
						logger.info("~~~~~~~~~~~~~~~~银联支付 消费~~~~~~~~~");
						String serialid = valideData.get("queryId"); // 获取后台通知的数据，其他字段也可用类似方式获取
						String orders = valideData.get("orderId");

						List<Orders> orderList = ordersService
								.getAllByOrdersWithTradingCode(orders);
						for (Orders o : orderList) {
							if (o.getStatus().equals("20")) {
								logger.error("当前订单已经支付：订单编号：" + orders);
							} else {
								Orders order = new Orders();
								order.setId(o.getId());
								order.setStatus("20");
								order.setPayForm(3);
								order.setPayStatus("1");
								order.setIs_over(0);
								order.setSerialid(serialid);
								order.setPayTime(new Date());
								ordersService.updateByPayNow(order, 3);
							}
						}
					}

				}
			}
			logger.info("BackRcvResponse接收后台通知结束");
			// 返回给银联服务器http 200 状态码
			try {
				response.getWriter().print("ok");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	/**
	 * 支付成功页面 银联支付
	 * 
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/paySuccessUnion")
	public ModelAndView paySuccessUnion(HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		request.setCharacterEncoding("ISO-8859-1");
		String encoding = request.getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = Tools.getAllRequestParam(request);

		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("ISO-8859-1"), encoding);
				valideData.put(key, value);
			}
		}

		// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(valideData, encoding)) {
			// 验签失败，需解决验签问题
			mv.addObject("error", "支付失败请联系管理员");
			mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/payFail");
		} else {
			// 【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

			String respCode = valideData.get("respCode"); // 获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
			if (respCode.equals("00")) {
				Orders order = new Orders();
				order.setTradingCode(reqParam.get("orderId"));
				order = ordersService.selectByOrders(order);
				mv.addObject("ordersn", order.getOrdersn());
				mv.addObject("orderPrice", order.getOrderAccount());
				mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/paySuccess");
			} else {
				mv.addObject("error", "支付失败请联系管理员");
				mv.setViewName(SystemConfig.SYSTEM_PATH_FRONT + "/payFail");
			}
		}
		mv.addObject("zhifu", "银联支付");
		return mv;

	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				// System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
