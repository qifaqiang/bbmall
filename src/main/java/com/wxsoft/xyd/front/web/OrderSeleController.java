package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.HTMLInputFilter;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.UserCollectionService;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.order.model.OrdersLog;
import com.wxsoft.xyd.order.model.OrdersReturn;
import com.wxsoft.xyd.order.service.OrdersBranchService;
import com.wxsoft.xyd.order.service.OrdersDetailService;
import com.wxsoft.xyd.order.service.OrdersLogService;
import com.wxsoft.xyd.order.service.OrdersReturnService;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.SysCouponsConf;
import com.wxsoft.xyd.system.service.SysCouponsConfService;
import com.wxsoft.xyd.system.service.SysProportionService;

/**
 * 前台订单操作
 * @author kyz
 *
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/orderSele")
public class OrderSeleController extends BaseController {
	@Autowired
	private UserCollectionService userCollectser;
	@Autowired
	private OrdersBranchService ordersBranchService;
	@Autowired
	private OrdersReturnService ordersReturnService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersDetailService ordersDetilService;
	@Autowired
	private SysCouponsConfService sysCouponsConfService;
	@Autowired
	private OrdersLogService ordersLogService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private SysProportionService sysProportionService;

	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/orderList")
	public void orderList(Integer cho, Integer currentPage, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 查询不同的集合， 待付款11 待收货20 22 30 待评价 40 已完成 40
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 先查询该用户的所有订单
			// 根据订单id查询 订单详情的 地址关联 名称 价格 总价
			Orders ord = new Orders();
			AjaxPage ap = new AjaxPage();
			ap.setShowCount(10);
			ap.setCurrentPage(currentPage);
			ap.setFunctionName("getList");
			ord.setAjaxPage(ap);
			Map ma = new HashMap();
			List<Map<String, Object>> ordersDt = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			String companyName = "";
			if (cho == 0) {
				ord.setUserId(user.getId());
				ord.setAjaxPage(ap);
			} else if (cho == 1) {
				// 查询待付款--------------------------------
				ord = new Orders();
				ord.setStatus("(11)");
				ord.setUserId(user.getId());
				ord.setAjaxPage(ap);
			} else if (cho == 2) {
				// 查询待收货
				ord = new Orders();
				ord.setStatus("(20,22,30)");
				ord.setUserId(user.getId());
				ord.setAjaxPage(ap);
			} else if (cho == 4) {
				// 查询待评价
				ord = new Orders();
				ord.setStatus("(40)");
				ord.setUserId(user.getId());
				ord.setAjaxPage(ap);
			} else if (cho == 3) {
				// 查询已完成
				ord = new Orders();
				ord.setStatus("(40)");
				// ord.setIsComment(1);
				ord.setUserId(user.getId());
				ord.setAjaxPage(ap);
			}
			list = ordersBranchService.listAjaxPageselectOrderIds(ord);

			int shaixuan = 0;
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					if (cho != 4) {
						ordersDt = ordersBranchService.selectOrderDt((Integer) map.get("id"));

						// 筛选
					} else if (cho == 4) {
						shaixuan++;
						ordersDt = ordersBranchService.selectOrderDtzero((Integer) map.get("id"));
					}
					map.put("addtime", Tools.date2Str((Date) map.get("addtime"), "yyyy-MM-dd HH:mm:ss"));
					companyName = ordersBranchService.selectCompanName((Integer) map.get("company_id"));
					map.put("companyName", companyName);
					map.put("nextlist", ordersDt);
				}
			} else {
				ma.put("nextlist", null);
				list.add(ma);
			}

			json.put("res_code", "0");
			json.put("message", "success");
			json.put("choose", cho);
			json.put("shaixuan", shaixuan);
			json.put(BaseConfig.RESLIST, list);
			json.put("pageCounts", ord.getAjaxPage().getPageStr());
			json.put("pageCount", ord.getAjaxPage().getTotalPage());
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单id查询订单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/orderDetailsByOrdersn")
	public void orderDetailsByOrdersn(Integer cho, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 查询订单详情
			// 先查询出订单id,然后去订单详情去查信息
			String companyName = "";
			Orders or = new Orders();
			or = ordersService.selectByPrimaryKey(cho);
			or.setId(cho);
			or.setUserId(user.getId());
			List<Map<String, Object>> list = ordersBranchService.selectOrderlocById(or);
			List<Map<String, Object>> ordersDt = ordersBranchService.selectByOrderstatus(cho);
			for (Map<String, Object> map : list) {
				map.put("orders", ordersDt);
				companyName = ordersBranchService.selectCompanName((Integer) map.get("company_id"));
				map.put("companyName", companyName);
			}
			// 红包个数
			SysCouponsConf scc = sysCouponsConfService.selectByPrimaryKey(1);
			json.put("regBagCount", scc.getCount());
			json.put("ordersn", or.getOrdersn());
			json.put("redstate", or.getStatus());
			json.put("systim", BaseConfig.sysProportion.getAutoCancelTime());
			json.put("res_code", "0");
			json.put(BaseConfig.RESLIST, list);
			json.put("message", "success");
			// json.put("page", ord.getAjaxPage());
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单id查询 详情并分页
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/orderDetailsByOrderId")
	public void orderDetailsByOrderId(Integer id, Integer currentPage, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 查询订单详情
			// 先查询出订单id,然后去订单详情去查信息
			String companyName = "";
			Orders or = new Orders();
			AjaxPage ap = new AjaxPage();
			ap.setShowCount(20);
			ap.setCurrentPage(currentPage);
			ap.setFunctionName("getList");
			or.setAjaxPage(ap);
			or.setId(id);
			or.setUserId(user.getId());
			;
			List<Map<String, Object>> list = ordersBranchService.listAjaxPageOrderlocById(or);

			List<Map<String, Object>> ordersDt = ordersBranchService.selectByOrderstatus(id);
			for (Map<String, Object> map : list) {
				map.put("orders", ordersDt);
				companyName = ordersBranchService.selectCompanName((Integer) map.get("company_id"));
				map.put("companyName", companyName);
				map.put("addtime", Tools.date2Str((Date) list.get(0).get("addtime"), "yyyy-MM-dd"));
			}
			json.put("res_code", "0");
			json.put(BaseConfig.RESLIST, list);
			json.put("message", "success");
			json.put("pageCount", or.getAjaxPage().getTotalPage());
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单sn 查询支付成功页面
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getPaySuccess")
	public void getPaySuccess(String orderSn, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 查询订单详情
			Orders or = new Orders();
			or.setOrdersn(orderSn);
			or.setUserId(user.getId());
			or = ordersService.selectByOrderInfo(or);

			SysCouponsConf scc = sysCouponsConfService.selectByPrimaryKey(1);

			json.put("res_code", "0");
			json.put("Consignee", or.getUl().getConsignee());
			json.put("Mobile", or.getUl().getMobile());
			json.put("orderId", or.getId());
			json.put("regBagCount", scc.getCount());
			json.put("AddressName", or.getUl().getAddressName());
			json.put("OrderAccount", or.getOrderAccount());
			json.put("message", "success");
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 退款
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/refuondOrd")
	public void refuondOrd(OrdersReturn ordretu, String webchoo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 添加退款申请
			OrdersReturn ore = new OrdersReturn();
			ore.setOrderId(ordretu.getOrderId());
			ore.setUserId(user.getId());
			// 申请退款前应该判断是否申请过退款
			// 订单号 userId
			Orders orders = new Orders();
			orders.setId(ordretu.getOrderId());
			orders.setUserId(user.getId());
			Orders ord = ordersService.selectByOrders(orders);
			if ("20".equals(ord.getStatus()) || "22".equals(ord.getStatus()) || "30".equals(ord.getStatus()) || "40".equals(ord.getStatus())) {
				List<OrdersReturn> ordRetOrNo = ordersReturnService.getAllByOrdersReturn(ore);
				if (ordRetOrNo.size() == 0) {
					ore.setStatus(0);
					ore.setRemark(new HTMLInputFilter().filter(ordretu.getRemark()));
					if (ordersReturnService.insertSelective(ore, ord.getStatus()) > 0) {
						json.put("res_code", "0");
						json.put("message", "退款申请已经提交,请等待卖家处理");
						if (!"1".equals(webchoo)) {
							json.put(BaseConfig.RESURL, "personalCenter-orderDetails.html?id=" + ordretu.getOrderId() + "&usr=1");
						} else {
							json.put(BaseConfig.RESURL, "refundmanagement_applyinfor.html?ords=" + ordretu.getOrderId());
						}

					} else {
						json.put("res_code", "user.ER1025");
						json.put("message", "退款失败");
						if (!"1".equals(webchoo)) {
							json.put(BaseConfig.RESURL, "personalCenter-orderDetails.html?id=" + ordretu.getOrderId() + "&usr=1");
						} else {
							json.put(BaseConfig.RESURL, "refundmanagement_applyinfor.html?ords=" + ordretu.getOrderId());
						}
					}
				} else {
					json.put("res_code", "user.ER1025");
					json.put("message", "您的申请已经提交，请勿重复提交");
					if (!"1".equals(webchoo)) {
						json.put(BaseConfig.RESURL, "personalCenter-orderDetails.html?id=" + ordretu.getOrderId() + "&usr=1");
					} else {
						json.put(BaseConfig.RESURL, "refundmanagement_applyinfor.html?ords=" + ordretu.getOrderId());
					}
				}
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", "不允许退款");
				if (!"1".equals(webchoo)) {
					json.put(BaseConfig.RESURL, "personalCenter-orderDetails.html?id=" + ordretu.getOrderId() + "&usr=1");
				} else {
					json.put(BaseConfig.RESURL, "refundmanagement_applyinfor.html?ords=" + ordretu.getOrderId());
				}
			}
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 我的退款列表
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/refuondOrdList")
	public void refuondOrdList(Integer currentPage, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			Orders ord = new Orders();
			ord = new Orders();
			ord.setStatus("(50,60)");
			ord.setUserId(user.getId());
			AjaxPage ap = new AjaxPage();
			ap.setShowCount(10);
			ap.setCurrentPage(currentPage);
			ap.setFunctionName("getList");
			ord.setAjaxPage(ap);
			List<Map<String, Object>> ordersDt = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> list = ordersBranchService.listAjaxPageselectOrderIds(ord);
			String companyName = "";
			OrdersReturn ore = new OrdersReturn();
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					ordersDt = ordersBranchService.selectOrderDt((Integer) map.get("id"));
					map.put("nextlist", ordersDt);
					companyName = ordersBranchService.selectCompanName((Integer) map.get("company_id"));
					map.put("companyName", companyName);
					ore = new OrdersReturn();
					ore.setOrderId((Integer) map.get("id"));
					ore.setUserId(user.getId());
					OrdersReturn re = ordersReturnService.selectByOrdersReturn(ore);
					if (re != null) {
						map.put("orderRetstatus", re.getStatus());
					}

				}
			}
			json.put("res_code", "0");
			json.put("message", "success");
			json.put(BaseConfig.RESLIST, list);
			json.put("pageCount", ord.getAjaxPage().getTotalPage());
			json.put("pageStr", ord.getAjaxPage().getPageStr());
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单号查询退款信息
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/refuondOrdById")
	public void refuondOrdById(Integer id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 查询订单列表
			OrdersReturn ore = new OrdersReturn();
			ore.setOrderId(id);
			ore.setUserId(user.getId());
			Orders ord = new Orders();
			OrdersReturn re = ordersReturnService.selectByOrdersReturn(ore);
			ord.setId(id);
			ord.setUserId(user.getId());
			Orders od = ordersService.selectByOrders(ord);

			json.put("orders", od);
			json.put("OrdersReturn", re);
			json.put("res_code", "0");
			json.put("message", "success");
			// json.put(BaseConfig.RESLIST, list);
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 取消订单
	 * 
	 * @param DATA
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteOrders")
	public void deleteOrders(Integer id, String cho, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			Orders ord = new Orders();
			ord.setId(id);
			ord.setUserId(user.getId());
			Orders od = ordersService.selectByOrders(ord);
			String result = "";
			if ("11".equals(od.getStatus())) {
				try {
					result = ordersService.updateCompayAddProds(od, user.getId(), 1, null);
					if (result == null || result == "") {
						json.put("message", "订单已取消");
					} else {
						json.put("message", result);
					}
					json.put("res_code", "0");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					json.put("res_code", "user.ER1025");
					json.put("message", "订单取消失败");
					e.printStackTrace();
				}

			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", "请在支付前取消订单!!");
				// json.put(BaseConfig.RESURL,
				// "personalCenter-myOrder-w.html?url=2");
			}
		} else {
			json.put("res_code", "user.ER1025");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 查询价格
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/selePrice")
	public void selePrice(Integer id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			Orders ord = new Orders();
			ord.setId(id);
			ord.setUserId(user.getId());
			Orders od = ordersService.selectByOrders(ord);
			json.put("res_code", "0");
			json.put("price", od.getOrderAccount());
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 评价
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/getpro")
	public void getpro(Integer id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			OrdersDetail ord = new OrdersDetail();
			ord.setId(id);
			OrdersDetail od = ordersDetilService.selectByOrdersDetail(ord);
			json.put("res_code", "0");
			json.put("OrdersDt", od);
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 确认收货
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/confirmReceipt")
	public void confirmReceipt(Integer id, String status, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			Orders ord = new Orders();
			ord.setId(id);
			ord.setUserId(user.getId());
			ord.setStatus("40");
			ord.setAcceptTime(new Date());
			if (ordersService.updateByPrimaryKeySelective(ord) > 0) {
				OrdersLog ordersLog = new OrdersLog();// 订单日志
				ordersLog.setOrderId(id);
				ordersLog.setOperator(user.getId());// 操作人
				ordersLog.setOrderStatus(status);
				ordersLog.setChangedStatus("40");
				ordersLog.setRemark("确认收货");
				ordersLog.setLogTime(Tools.date2Str(new Date()));
				ordersLogService.insert(ordersLog);
				json.put("res_code", "0");
				json.put("message", "确认收货成功");
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", "确认收货失败");
			}
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 确认收货Pc端
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/confirmReceiptPc")
	public void confirmReceiptPc(Integer id, String status, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			Orders ord = new Orders();
			ord.setId(id);
			ord.setUserId(user.getId());
			ord.setStatus("40");
			ord.setAcceptTime(new Date());
			if (ordersService.updateByPrimaryKeySelective(ord) > 0) {
				OrdersLog ordersLog = new OrdersLog();// 订单日志
				ordersLog.setOrderId(id);
				ordersLog.setOperator(user.getId());// 操作人
				ordersLog.setOrderStatus(status);
				ordersLog.setChangedStatus("40");
				ordersLog.setRemark("确认收货");
				ordersLog.setLogTime(Tools.date2Str(new Date()));
				ordersLogService.insert(ordersLog);
				json.put("res_code", "0");
				json.put("message", "确认收货成功");
				json.put(BaseConfig.RESURL, "myorder_details.html?id=" + id);
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", "确认收货失败");
			}
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) { // TODO
			e.printStackTrace();
		}
	}

	/**
	 * 查询订单
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/orderperson")
	public void orderperson(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();

		// 查询所有的条数，然后查询所有的
		// 查询不同的集合， 待付款11 待收货20 22 30 待评价 40 已完成 40
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {

			Orders ord = new Orders();
			ord.setUserId(user.getId());
			AjaxPage ap = new AjaxPage();
			ap.setShowCount(2);
			ap.setCurrentPage(1);
			ap.setFunctionName("getList");
			ord.setAjaxPage(ap);
			Map ma = new HashMap();
			List<Map<String, Object>> ordersDt = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = ordersBranchService.listAjaxPageselectOrderIds(ord);
			if (list.size() > 0) {
				for (Map<String, Object> map : list) {
					ordersDt = ordersBranchService.selectOrderDt((Integer) map.get("id"));
					map.put("addtime", Tools.date2Str((Date) map.get("addtime"), "yyyy-MM-dd HH:mm:ss"));
					map.put("nextlist", ordersDt);
				}
			} else {
				ma.put("nextlist", null);
				list.add(ma);
			}

			// 查询待付款--------------------------------
			ord.setStatus("(11)");
			int countone = ordersService.selectCount(ord);
			// 查询待收货
			ord.setStatus("(22,30)");
			int counttwo = ordersService.selectCount(ord);
			// 查询已完成
			ord.setStatus("(40)");
			int countthr = ordersService.selectCount(ord);
			// 待评价
			int countEva = ordersService.selectEvaCount(ord);

			SysCouponsRecord pd = new SysCouponsRecord();
			pd.setUserId(user.getId());
			List<SysCouponsRecord> cousys = sysCouponsRecordService.listBySysCouponsRecordCan(pd);

			json.put("res_code", "0");
			json.put("message", "success");

			json.put("countone", countone);
			json.put("counttwo", counttwo);
			json.put("countthr", countthr);
			json.put("countEva", countEva);
			json.put("cousys", cousys.size());
			json.put(BaseConfig.RESLIST, list);
		} else {
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// 0
			e.printStackTrace();
		}
	}

}
