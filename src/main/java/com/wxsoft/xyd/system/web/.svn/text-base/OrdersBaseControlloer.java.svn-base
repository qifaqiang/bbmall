/**   
 * @文件名称: ResponseControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.view.OrderSendExcelView;
import com.wxsoft.framework.view.OrderUserExcelView;
import com.wxsoft.xyd.common.model.SysShipping;
import com.wxsoft.xyd.common.service.SysShippingService;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersLog;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersLogService;
import com.wxsoft.xyd.order.service.OrdersReturnService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.UserService;

/**
 * @类功能说明：基地订单管理
 * @类修改者：kyz
 * @修改日期：2015-07-11
 * @修改说明：
 * @回复名称：kyz
 * @作者：kyz
 * @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/ordersBase")
public class OrdersBaseControlloer extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersDetailService ordersDetailService;
	@Autowired
	private OrdersReturnService ordersReturnService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrdersLogService ordersLogService;
	@Autowired
	private SysShippingService sysShippingService;

	/**
	 * 
	 * @描述: 退货订单列表
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/returnorderslist")
	public ModelAndView returnordersList(HttpServletRequest request, Orders os,
			String ttime, Integer ty, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/orders/returnlistBase");
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (null == ty || ty == -1) {
		} else {
			os.setStatus(String.valueOf(ty));
		}
		// 判断是否检索订单编号
		if (Tools.notEmpty(os.getOrdersn())) {
			os.setOrdersn(os.getOrdersn());
		} else {
			os.setOrdersn(null);
		}

		Orders tempCount = new Orders();
		tempCount.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim() + ":00";
			String end = ttime.split("~")[1].trim() + ":00";
			tempCount.setAddtime(Tools.str2Date(start));
			tempCount.setAddendtime(Tools.str2Date(end));
			mv.addObject("ttime", ttime);
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}

		List<Orders> orlist = ordersService.selectByReturnOrderBack(tempCount);// 获取各种订单数量

		for (Orders orders : orlist) {
			mv.addObject("s" + orders.getStatus(), orders.getCount());
		}

		List<Orders> orResultlist = ordersService.listPageByOrdersReturn(os);
		mv.addObject("orResultlist", orResultlist);
		mv.addObject("os", os);
		return mv;
	}

	/**
	 * 
	 * @描述: 退货订单信息
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/ordersreturninfo")
	public ModelAndView ordersreturninfo(HttpServletRequest request,
			Integer orderid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Orders os = new Orders();
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		os.setId(orderid);
		os = ordersService.selectByReturnOrderInfo(os);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/orders/returninfo");

		mv.addObject("orders", os);
		return mv;
	}

	/**
	 * 
	 * @描述: 订单列表
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/orderslist")
	public ModelAndView ordersList(HttpServletRequest request, Orders os,
			String ttime, Integer ty, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/orders/listBase");

		// 订单状态
		if (null == ty || ty == -1) {
		} else {
			os.setStatus(String.valueOf(ty));
		}

		Orders tempCount = new Orders();
		tempCount.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());

		// 判断是否检索提货码
		if (Tools.notEmpty(os.getPickCode())) {
			tempCount.setPickCode(os.getPickCode());
		} else {
			os.setPickCode(null);
		}

		// 判断是否检索订单编号
		if (Tools.notEmpty(os.getOrdersn())) {
			tempCount.setOrdersn(os.getOrdersn());
		} else {
			os.setOrdersn(null);
		}

		// 判断是否检索支付流水号
		if (Tools.notEmpty(os.getTradingCode())) {
			tempCount.setTradingCode(os.getTradingCode());
		} else {
			os.setTradingCode(null);
		}

		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim() + ":00";
			String end = ttime.split("~")[1].trim() + ":00";
			tempCount.setAddtime(Tools.str2Date(start));
			tempCount.setAddendtime(Tools.str2Date(end));
			mv.addObject("ttime", ttime);
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}

		List<Orders> orlist = ordersService.selectByOrderBack(tempCount);// 获取各种订单数量
		for (Orders orders : orlist) {
			mv.addObject("s" + orders.getStatus(), orders.getCount());
		}

		// 获取所有基地
		Company company = new Company();
		List<Company> ListCompanyName = companyService
				.selectCompanyName(company);

		List<Orders> orResultlist = ordersService.listPageByOrders(os);
		mv.addObject("orResultlist", orResultlist);
		mv.addObject("os", os);
		mv.addObject("ListCompanyName", ListCompanyName);
		// mv.addObject("ss", ss);
		mv.addObject("pickCode", os.getPickCode());
		return mv;
	}

	/**
	 * 
	 * @描述: 订单信息
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/ordersinfo")
	public ModelAndView ordersInfo(HttpServletRequest request, Integer orderid,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Orders os = new Orders();
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		os = ordersService.selectByOrderInfo(os);

		OrdersLog ol = new OrdersLog();
		ol.setOrderId(orderid);
		List<OrdersLog> olList = ordersLogService.getAllByOrdersLog(ol);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/orders/info");
		mv.addObject("orders", os);
		mv.addObject("olList", olList);
		return mv;
	}

	/**
	 * 
	 * @描述: 打印订单
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/toprint")
	public ModelAndView toprint(HttpServletRequest request, Integer orderid,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Orders os = new Orders();
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		os = ordersService.selectByOrderInfo(os);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/orders/print");
		mv.addObject("orders", os);
		return mv;
	}

	/**
	 * 批量填写快递单号
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/addshipbatch", method = RequestMethod.POST)
	public void addshipbatch(HttpSession session, HttpServletResponse response,
			String ttime, Long shipcode) {
		Map<String, String> maps = new HashMap<String, String>();

		if (null == shipcode || shipcode == 0) {
			maps.put("Status", "0");
			maps.put("Message", "请填写初始快递单号");
		} else {

			Orders os = new Orders();
			os.setStatus("20");
			os.setCompanyId(((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
			if (Tools.notEmpty(ttime)) {
				String start = ttime.split("~")[0].trim();
				String end = ttime.split("~")[1].trim();
				os.setAddtime(Tools.str2Date(start));
				os.setAddendtime(Tools.str2Date(end));
			}
			int i = 0;
			SysShipping ss = new SysShipping();
			ss.setCompanyId(((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
			ss = sysShippingService.selectBySysShipping(ss);
			// os = ordersService.selectByOrders(os);

			StringBuffer ordersb = new StringBuffer();
			List<Orders> orlist = ordersService.getAllByOrdersNoShip(os);
			for (Orders orders : orlist) {
				orders.setShipId(ss.getCode());
				orders.setShipName(ss.getName());
				orders.setShipCode(String.valueOf(shipcode));
				if (ordersService.updateByUpdateShip(orders, ((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId()) > 0) {
					ordersb.append(orders.getId()).append(",");
				}
				shipcode++;
				i++;
			}

			if (Tools.isEmpty(ordersb.toString())) {
				maps.put("Status", "0");
				maps.put("Message", "未存在需要填写快递单号的订单");
			} else {
				maps.put("Status", "1");
				maps.put("count", i + "");
				maps.put(
						"Message",
						ordersb.toString().substring(0,
								ordersb.toString().length() - 1));
			}
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 批量发货
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/tosendbatch", method = RequestMethod.POST)
	public void tosendbatch(HttpSession session, HttpServletResponse response,
			String ttime) {
		Map<String, String> maps = new HashMap<String, String>();

		Orders os = new Orders();
		os.setStatus("22");
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim();
			String end = ttime.split("~")[1].trim();
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}
		int i = 0;
		StringBuffer ordersb = new StringBuffer();
		List<Orders> orlist = ordersService.getAllByOrdersWithShip(os);
		for (Orders orders : orlist) {
			orders.setShipTime(new Date());
			orders.setStatus("30");
			if (ordersService.updateByFaHuo(orders, ((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId()) > 0) {
				ordersb.append(orders.getId()).append(",");
				i++;
			}

		}

		if (Tools.isEmpty(ordersb.toString())) {
			maps.put("Status", "0");
			maps.put("Message", "未存在需要发货的订单");
		} else {
			maps.put("Status", "1");
			maps.put("count", i + "");
			maps.put(
					"Message",
					ordersb.toString().substring(0,
							ordersb.toString().length() - 1));
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 批量接单
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/receivingOrderBatch", method = RequestMethod.POST)
	public void receivingOrderBatch(HttpSession session,
			HttpServletResponse response, String ttime) {
		Map<String, String> maps = new HashMap<String, String>();

		Orders os = new Orders();
		os.setStatus("20");
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim();
			String end = ttime.split("~")[1].trim();
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}
		int i = 0;
		StringBuffer ordersb = new StringBuffer();
		List<Orders> orlist = ordersService.getAllByOrdersWithShip(os);
		for (Orders orders : orlist) {
			orders.setReceivingTime(new Date());
			orders.setStatus("22");
			if (ordersService.updateByJieDan(orders, ((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId()) > 0) {
				ordersb.append(orders.getId()).append(",");
				i++;
			}
		}

		if (Tools.isEmpty(ordersb.toString())) {
			maps.put("Status", "0");
			maps.put("Message", "未存在需要接单的订单");
		} else {
			maps.put("Status", "1");
			maps.put("count", i + "");
			maps.put(
					"Message",
					ordersb.toString().substring(0,
							ordersb.toString().length() - 1));
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 接单
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/receivingOrder", method = RequestMethod.POST)
	public void receivingOrder(HttpSession session,
			HttpServletResponse response, Integer orderid, String shipcode) {
		Map<String, String> maps = new HashMap<String, String>();
		Orders os = new Orders();
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		SysShipping ss = new SysShipping();
		ss.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		ss = sysShippingService.selectBySysShipping(ss);
		os = ordersService.selectByOrders(os);
		if (null == os) {
			maps.put("Status", "0");
			maps.put("Message", "未找到该订单");
		} else if (os.getStatus().equals("20")) {
			os.setStatus("22");
			os.setReceivingTime(new Date());
			if (ordersService.updateByJieDan(os, ((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId()) > 0) {
				maps.put("Status", "1");
			} else {
				maps.put("Status", "0");
				maps.put("Message", "接单失败，请联系管理员");
			}
		} else {
			maps.put("Status", "0");
			maps.put("Message", "该订单不允许接单");
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 取消订单
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public void cancelOrder(HttpSession session, HttpServletResponse response,
			Integer orderid, String shipcode) {
		Map<String, String> maps = new HashMap<String, String>();
		Orders os = new Orders();
		String result = null;
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());

		os = ordersService.selectByOrders(os);
		if (null == os) {
			maps.put("Status", "0");
			maps.put("Message", "未找到该订单");
		} else if (os.getStatus().equals("11")) {
			try {
				result = ordersService.updateCompayAddProds(os,
						((Company) session
								.getAttribute(SystemConfig.SESSION_USER))
								.getCompanyId(), 2, null);
				if (null == result) {
					maps.put("Status", "1");
				} else {
					maps.put("Status", "0");
					maps.put("Message", "取消订单：" + result);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				maps.put("Status", "0");
				maps.put("Message", "取消订单：" + e.getMessage());
			}
		} else {
			maps.put("Status", "0");
			maps.put("Message", "该订单不允许取消");
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发货
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updateship", method = RequestMethod.POST)
	public void updateship(HttpSession session, HttpServletResponse response,
			Integer orderid, String shipcode) {
		Map<String, String> maps = new HashMap<String, String>();
		Orders os = new Orders();
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		SysShipping ss = new SysShipping();
		ss.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		ss = sysShippingService.selectBySysShipping(ss);
		os = ordersService.selectByOrders(os);
		if (null == os) {
			maps.put("Status", "0");
			maps.put("Message", "未找到该订单");
		} else if (os.getStatus().equals("22")) {
			// os.setShipId(ss.getCode());
			// os.setShipName(ss.getName());
			// os.setShipCode(shipcode);
			os.setShipTime(new Date());
			os.setStatus("30");
			if (ordersService.updateByUpdateShip(os, ((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId()) > 0) {
				maps.put("Status", "1");
			} else {
				maps.put("Status", "0");
				maps.put("Message", "填写快递单号失败，请联系管理员");
			}
		} else {
			maps.put("Status", "0");
			maps.put("Message", "该订单不允许填写快递单号");
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改订单价格
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public void updatePrice(HttpSession session, HttpServletResponse response,
			BigDecimal orderPrice, BigDecimal shipPrice, Integer orderid) {
		Map<String, String> maps = new HashMap<String, String>();
		if (orderPrice.doubleValue() <= 0&&shipPrice.doubleValue()<=0) {
			maps.put("Status", "0");
			maps.put("Message", "价格必须大于等于0");
		} else {
			Orders os = new Orders();
			os.setId(orderid);
			os.setCompanyId(((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
			os = ordersService.selectByOrders(os);
			if (null == os) {
				maps.put("Status", "0");
				maps.put("Message", "未找到该订单");
			} else if (os.getStatus().equals("11")) {

				BigDecimal oldOrderPrice = os.getOrderPrice();
				BigDecimal oldShipPrice = os.getShipPrice();
				BigDecimal oldOrderAccount = os.getOrderAccount();
				os.setOrderPrice(orderPrice);
				os.setShipPrice(shipPrice);
				os.setOrderAccount(orderPrice.add(shipPrice));
				if (ordersService.updateByUpdatePrice(os, ((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId(), oldOrderPrice, oldShipPrice,
						oldOrderAccount) > 0) {
					maps.put("Status", "1");
				} else {
					maps.put("Status", "0");
					maps.put("Message", "修改价格失败，请联系管理员");
				}
			} else {
				maps.put("Status", "0");
				maps.put("Message", "该订单不允许修改价格");
			}
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发货
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/fahuo", method = RequestMethod.POST)
	public void fahuo(HttpSession session, HttpServletResponse response,
			Integer orderid) {
		Map<String, String> maps = new HashMap<String, String>();
		Orders os = new Orders();
		os.setId(orderid);
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		os = ordersService.selectByOrders(os);
		if (null == os) {
			maps.put("Status", "0");
			maps.put("Message", "未找到该订单");
		} else if ((os.getStatus().equals("22"))) {
			os.setShipTime(new Date());
			os.setStatus("30");
			if (ordersService.updateByFaHuo(os, ((Company) session
					.getAttribute(SystemConfig.SESSION_USER)).getCompanyId()) > 0) {
				maps.put("Status", "1");
			} else {
				maps.put("Status", "0");
				maps.put("Message", "发货失败，请联系管理员");
			}
		} else {
			maps.put("Status", "0");
			maps.put("Message", "该订单不允许发货");
		}
		try {
			responseAjax(maps, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toexcel")
	public ModelAndView toExcel(HttpSession session, Orders os, String ttime,
			Integer ty, String pickCode, HttpServletRequest request) {
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		if (null == ty || ty == -1) {
		} else {
			os.setStatus(String.valueOf(ty));
		}
		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim();
			String end = ttime.split("~")[1].trim();
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}
		if (Tools.notEmpty(pickCode)) {
			os.setPickCode(pickCode);
		} else {
			os.setPickCode(null);
		}
		// 判断是否检索支付流水号
		if (Tools.notEmpty(os.getTradingCode())) {
			os.setTradingCode(os.getTradingCode());
		} else {
			os.setTradingCode(null);
		}
		List<Orders> orResultlist = ordersService.getAllByOrdersInfo(os);

		List<String> headtitle = new ArrayList<String>();// 标题
		Map<String, Object> dataMap = new HashMap<String, Object>();

		headtitle.add("订单号");
		headtitle.add("基地");
		headtitle.add("收货人姓名");
		headtitle.add("收货人手机号");
		headtitle.add("商品名称");
		headtitle.add("订单金额");
		headtitle.add("收货地址");
		headtitle.add("交易状态");
		headtitle.add("交易创建时间");
		headtitle.add("付款时间");
		headtitle.add("买家留言");
		headtitle.add("用户选择配送方式");
		headtitle.add("提货码");
		dataMap.put("titles", headtitle);
		OrderUserExcelView erv = null;
		ModelAndView mv = null;
		erv = new OrderUserExcelView();
		dataMap.put("orResultlist", orResultlist);
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	/**
	 * 把待发货订单批量导出excel
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toexcelbatch")
	public ModelAndView toexcelbatch(HttpSession session, String ttime,
			String pickCode, HttpServletRequest request) {
		Orders os = new Orders();
		os.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		os.setStatus(String.valueOf(20));
		if (Tools.notEmpty(ttime)) {
			String start = ttime.split("~")[0].trim();
			String end = ttime.split("~")[1].trim();
			os.setAddtime(Tools.str2Date(start));
			os.setAddendtime(Tools.str2Date(end));
		}
		if (Tools.notEmpty(pickCode)) {
			os.setPickCode(pickCode);
		} else {
			os.setPickCode(null);
		}
		// 判断是否检索支付流水号
		if (Tools.notEmpty(os.getTradingCode())) {
			os.setTradingCode(os.getTradingCode());
		} else {
			os.setTradingCode(null);
		}
		List<Orders> orResultlist = ordersService.getAllByOrdersWithShip(os);

		List<String> headtitle = new ArrayList<String>();// 标题
		Map<String, Object> dataMap = new HashMap<String, Object>();

		headtitle.add("收货人姓名");
		headtitle.add("收货人手机号");
		headtitle.add("收货人邮编");
		headtitle.add("收货地址");
		headtitle.add("订单编号");
		headtitle.add("用户选择配送方式");
		dataMap.put("titles", headtitle);
		OrderSendExcelView erv = null;
		ModelAndView mv = null;
		erv = new OrderSendExcelView();
		dataMap.put("orResultlist", orResultlist);
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

}