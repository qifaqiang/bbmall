package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserLocation;
import com.wxsoft.xyd.common.service.UserLocationService;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * 收货地址处理
 * @author kyz
 *
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/userLocation")
public class UserLocationControl extends BaseController {

	@Autowired
	private UserLocationService userLocationService;

	/**
	 * 新增收货地址
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveLocation")
	public void saveLocation(UserLocation userLocal, String urls, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();

		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		userLocal.setUserid(su.getId());
		// 添加前查询是否有默认收货地址，如果没有设置为默认收货地址
		UserLocation us = new UserLocation();
		us.setUserid(su.getId());
		us.setStatus(true);
		userLocal.setId(null);
		List<UserLocation> res = userLocationService.getAllByUserLocation(us);
		if (res.size() == 0) {
			userLocal.setStatus(true);
		}
		userLocal.setLocation(new HTMLInputFilter().filter(userLocal.getLocation()));
		if (userLocationService.insertSelective(userLocal) > 0) {
			json.put("res_code", "0");
			json.put("message", "添加成功");
			if (urls == null || urls == "") {
				json.put(BaseConfig.RESURL, "personalCenter-myAddress.html");
			} else {
				json.put(BaseConfig.RESURL, urls);
			}

		} else {
			json.put("res_code", "ER1017");
			json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1017"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增收货地址
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveLocationPc")
	public void saveLocationPc(UserLocation userLocal, String urls, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();

		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		userLocal.setUserid(su.getId());
		// 添加前查询是否有默认收货地址，如果没有设置为默认收货地址
		UserLocation us = new UserLocation();
		us.setUserid(su.getId());
		us.setStatus(true);
		userLocal.setId(null);
		List<UserLocation> res = userLocationService.getAllByUserLocation(us);
		if (res.size() == 0) {
			userLocal.setStatus(true);
		}
		userLocal.setLocation(new HTMLInputFilter().filter(userLocal.getLocation()));
		userLocal.setConsignee(new HTMLInputFilter().filter(userLocal.getConsignee()));
		if (userLocationService.insertSelective(userLocal) > 0) {
			json.put("res_code", "0");
			json.put("message", "添加成功");
			if (urls == null || urls == "") {
				json.put(BaseConfig.RESURL, "personalCenter-myAddress.html");
			} else {
				// json.put(BaseConfig.RESURL, urls);
			}

		} else {
			json.put("res_code", "ER1017");
			json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1017"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改收货地址
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "/updateLocation")
	public void updateLocation(UserLocation userLocal, String urls, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		userLocal.setUserid(su.getId());
		userLocal.setLocation(new HTMLInputFilter().filter(userLocal.getLocation()));
		if (userLocationService.updateByPrimaryKeySelective(userLocal) > 0) {
			json.put("res_code", "0");
			json.put("message", "修改成功");
			if (urls == null || urls == "") {
				json.put(BaseConfig.RESURL, "personalCenter-myAddress.html");
			} else {
				json.put(BaseConfig.RESURL, urls);
			}
		} else {
			json.put("res_code", "ER1018");
			json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1018"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改收货地址
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "/updateLocationPc")
	public void updateLocationPc(UserLocation userLocal, String urls, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		userLocal.setUserid(su.getId());
		userLocal.setLocation(new HTMLInputFilter().filter(userLocal.getLocation()));
		if (userLocationService.updateByPrimaryKeySelective(userLocal) > 0) {
			json.put("res_code", "0");
			json.put("message", "修改成功");
			if (urls == null || urls == "") {
				json.put(BaseConfig.RESURL, "personalCenter-myAddress.html");
			} else {
				// json.put(BaseConfig.RESURL, urls);
			}
		} else {
			json.put("res_code", "ER1018");
			json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1018"));
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 收货地址列表
	 * 
	 * @param response
	 * @param request
	 * @param session
	 */
	@RequestMapping("/LocationList")
	public void LocationList(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserLocation uslocal = new UserLocation();
			uslocal.setUserid(user.getId());
			List<UserLocation> list = userLocationService.getAllByUserLocation(uslocal);
			json.put("res_code", "0");
			json.put("message", "success");
			json.put(BaseConfig.RESLIST, list);
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
	 * 收货地址列表
	 * 
	 * @param response
	 * @param request
	 * @param session
	 */
	@RequestMapping("/LocationLists")
	public void LocationLists(Integer showCount, Integer currentPage, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserLocation uslocal = new UserLocation();
			uslocal.setUserid(user.getId());
			AjaxPage ap = new AjaxPage();
			ap.setShowCount(showCount);
			ap.setCurrentPage(currentPage);
			ap.setFunctionName("getLocationList");
			uslocal.setAjaxPage(ap);
			List<UserLocation> list = userLocationService.listAjaxPageByUserLocation(uslocal);
			json.put("res_code", "0");
			json.put("message", "success");
			json.put("pageStr", uslocal.getAjaxPage().getPageStr());
			json.put(BaseConfig.RESLIST, list);
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
	 * 根据id查询
	 * 
	 * @param response
	 * @param request
	 * @param session
	 */
	@RequestMapping("/seleById")
	public void seleById(Integer id, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserLocation uslocal = new UserLocation();
			uslocal.setUserid(user.getId());
			uslocal.setId(id);
			UserLocation list = userLocationService.selectById(uslocal);
			json.put("res_code", "0");
			json.put("message", "success");
			json.put(BaseConfig.RESLIST, list);
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
	 * 修改状态
	 * 
	 * @param id
	 * @param str
	 * @param response
	 * @param request
	 * @param session
	 */
	@RequestMapping("/upstatus")
	public void upstatus(Integer id, Boolean str, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		UserLocation loca = new UserLocation();
		loca.setId(id);
		loca.setStatus(str);
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			loca.setUserid(user.getId());
			// 修改状态前看是否存在
			UserLocation locas = new UserLocation();
			locas.setId(id);
			locas.setUserid(user.getId());
			UserLocation usersize = userLocationService.selectById(locas);
			if (usersize != null) {
				if (userLocationService.updateByPrimaryKeySelective(loca) > 0) {
					json.put("res_code", "0");
					json.put("message", "设置默认成功");
				} else {
					json.put("res_code", "user.ER1025");
					json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1020"));
				}
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1020"));
			}

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
	 * 删除收货地址
	 * 
	 * @param DATA
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteLocation")
	public void deleteLocation(Integer id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);

		if (user != null) {
			// 删除之前判断是否为该用户的收货地址
			UserLocation locas = new UserLocation();
			locas.setId(id);
			locas.setUserid(user.getId());
			UserLocation usersize = userLocationService.selectById(locas);
			if (usersize != null) {
				int res = userLocationService.deleteByPrimaryKey(id, user.getId());
				if (res == 0) {
					json.put("res_code", "0");
					json.put("message", "删除成功");
				} else if (res == 2) {
					json.put("res_code", "user.ER1025");
					json.put("message", "删除失败");
				}
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", BaseConfig.MESSAGE.get("userLocation.ER1020"));
			}

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
}
