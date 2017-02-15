package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.json.JsonLibUtils;
import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.SysCouponsService;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * Title优惠券
 * 
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/Coup")
public class CouponController extends BaseController {
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private SysCouponsService sysCouponsService;

	/**
	 * 领取优惠券中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sysCoupons", method = RequestMethod.POST)
	public void sysCoupons(HttpServletResponse response, Integer type,
			Integer currentPage, Integer zu, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		SysCoupons scr = new SysCoupons();
		json.put("obj", scr);
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("getlist");
		if (null == zu) {
			ap.setShowCount(10);
		} else {
			ap.setShowCount(zu);
		}
		scr.setAjaxPage(ap);
		// 得到当前登陆用户的信息
		User com = new User();
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (com == null) {
			// 得到所有优惠券信息
			List<Map<String, Object>> list = sysCouponsService
					.listAjaxPageCoupons(scr);
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, list);
			json.put("pageCount", scr.getAjaxPage().getTotalPage());
			json.put("pageStr", scr.getAjaxPage().getPageStr());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			scr.setUserId(com.getId());
			// 得到当前用户领取优惠券的信息记录
			List<Map<String, Object>> list = sysCouponsService
					.listAjaxPageSysCoupons(scr);
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, list);
			json.put("pageCount", scr.getAjaxPage().getTotalPage());
			json.put("pageStr", scr.getAjaxPage().getPageStr());
			json.put("list", JsonLibUtils
					.listToStringWithConfig(list, new String[] { "ajaxPage",
							"allCount", "endTime", "endTimeS", "id",
							"needPrice", "page", "startTime", "startTimeS",
							"state", "substractPrice", "userId" }));
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 领取优惠券行为记录
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/sysCouponsLing", method = RequestMethod.POST)
	public void sysCouponsLing(Integer id, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ParseException {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (com == null) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			SysCoupons sys = new SysCoupons();
			// 判断用户是否领取过优惠券
			sys = sysCouponsService.selectByPrimaryKeyCount(id);
			SysCouponsRecord obj = new SysCouponsRecord();
			obj.setCouponsId(id);
			obj.setUserId(com.getId());
			obj.setStartTime(sys.getStartTime());
			obj.setEndTime(sys.getEndTime());
			obj.setNeedPrice(sys.getNeedPrice());
			obj.setSubstractPrice(sys.getSubstractPrice());
			obj.setAddtime(new Date());
			obj.setState(1);// 默认可用 状态 1可用 2已使用 3已过期
			obj.setWFrom(1);// 默认领取 来源 1领取 2赠送 3分享获取
			int list = sysCouponsRecordService.insertSelective(obj);
			if (list == 1) {
				// 领取成功
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("coupon.ER9001").toString());
				json.put(BaseConfig.RESLIST, list);
				try {
					responseAjax(json, response);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// 您已经领取过该优惠券
				json.put(BaseConfig.RESCODE, "2");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("coupon.ER9002").toString());

				try {
					responseAjax(json, response);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 我的优惠券列表 可以使用的优惠券
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mycouponsCan", method = RequestMethod.POST)
	public void mycouponsCan(HttpServletResponse response, Integer type,
			String name, Integer currentPage, BigDecimal pirce, Integer zu,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		SysCouponsRecord pd = new SysCouponsRecord();
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("mycouponsCan");
		if (null == zu) {
			ap.setShowCount(10);
		} else {
			ap.setShowCount(zu);
		}
		pd.setAjaxPage(ap);
		if (com == null) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
			json.put(BaseConfig.RESLIST, 1);
			json.put(BaseConfig.RESTYPE, 0);
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pd.setUserId(com.getId());
			pd.setNeedPrice(pirce);
			List<Map<String, Object>> list = sysCouponsRecordService
					.listAjaxPageBySysCouponsRecordCan(pd);
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, list);
			List<SysCouponsRecord> li = sysCouponsRecordService
					.listBySysCouponsRecordCan(pd);
			json.put(BaseConfig.RESTYPE, li.size());
			json.put("list", JsonLibUtils.listToStringWithConfig(list,
					new String[] { "entrySet", "keySet", "loadFactor",
							"modCount", "size", "table", "threshold", "value",
							"endTimeS", "startTimeS", "substractPrice",
							"needPrice", "id" }));
			json.put("pageCount", pd.getAjaxPage().getTotalPage());
			json.put("pageStr", pd.getAjaxPage().getPageStr());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 我的优惠券列表 过期失效的优惠券
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mycouponsExpire", method = RequestMethod.POST)
	public void mycouponsExpire(HttpServletResponse response, Integer type,
			Integer currentPage, Integer zu, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		SysCouponsRecord pd = new SysCouponsRecord();
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("mycouponsExpire");
		if (null == zu) {
			ap.setShowCount(10);
		} else {
			ap.setShowCount(zu);
		}
		pd.setAjaxPage(ap);
		if (com == null) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
			json.put(BaseConfig.RESLIST, 1);
			json.put(BaseConfig.RESTYPE, 0);
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pd.setUserId(com.getId());
			List<Map<String, Object>> list = sysCouponsRecordService
					.listAjaxPageBySysCouponsRecordExpire(pd);
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, list);
			List<SysCouponsRecord> li = sysCouponsRecordService
					.listBySysCouponsRecordExpire(pd);
			json.put(BaseConfig.RESTYPE, li.size());
			json.put(
					"list",
					JsonLibUtils.listToStringWithConfig(list, new String[] {
							"entrySet", "keySet", "loadFactor", "modCount",
							"size", "table", "threshold", "value", "endTimeS",
							"startTimeS", "substractPrice", "needPrice" }));
			json.put("pageCount", pd.getAjaxPage().getTotalPage());
			json.put("pageStr", pd.getAjaxPage().getPageStr());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 我的优惠券列表 已经使用的优惠券
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mycouponsOver", method = RequestMethod.POST)
	public void mycouponsOver(HttpServletResponse response, Integer type,
			String name, Integer currentPage, Integer zu,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		SysCouponsRecord pd = new SysCouponsRecord();
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("mycouponsOver");
		if (null == zu) {
			ap.setShowCount(10);
		} else {
			ap.setShowCount(zu);
		}
		pd.setAjaxPage(ap);
		if (com == null) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
			json.put(BaseConfig.RESLIST, 1);
			json.put(BaseConfig.RESTYPE, 0);
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pd.setUserId(com.getId());
			List<Map<String, Object>> list = sysCouponsRecordService
					.listAjaxPageBySysCouponsRecordOver(pd);
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, list);
			List<SysCouponsRecord> li = sysCouponsRecordService
					.listBySysCouponsRecordOver(pd);
			json.put(BaseConfig.RESTYPE, li.size());
			json.put(
					"list",
					JsonLibUtils.listToStringWithConfig(list, new String[] {
							"entrySet", "keySet", "loadFactor", "modCount",
							"size", "table", "threshold", "value", "endTimeS",
							"startTimeS", "substractPrice", "needPrice" }));
			json.put("pageCount", pd.getAjaxPage().getTotalPage());
			json.put("pageStr", pd.getAjaxPage().getPageStr());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
