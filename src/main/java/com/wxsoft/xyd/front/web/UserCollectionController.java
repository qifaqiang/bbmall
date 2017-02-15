package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserCollection;
import com.wxsoft.xyd.common.service.UserCollectionService;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * 用户收藏處理
 * @author kyz
 *
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/userCollection")
public class UserCollectionController extends BaseController {
	@Autowired
	private UserCollectionService userCollectser;
	@Autowired
	private ProductService productService;

	/**
	 * 查询收藏列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/colleList")
	public void colleList(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 先要查出该用户的id;根据ID查询该用户的所有商品id，然后根据所有商品的id查询出所有的商品

		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserCollection usercol = new UserCollection();
			usercol.setUserId(user.getId());
			List<Map<String, Object>> list = userCollectser.getAllProByUserId(user.getId());

			json.put("res_code", "0");
			json.put("message", "success");
			json.put("len", list.size());
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
	 * 查询收藏列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/colleLists")
	public void colleLists(Integer showCount, Integer currentPage, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 先要查出该用户的id;根据ID查询该用户的所有商品id，然后根据所有商品的id查询出所有的商品
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserCollection usercol = new UserCollection();
			usercol.setUserId(user.getId());
			AjaxPage ap = new AjaxPage();
			ap.setCurrentPage(currentPage);
			ap.setFunctionName("getall");
			ap.setShowCount(showCount);
			usercol.setAjaxPage(ap);
			List<Map<String, Object>> list = userCollectser.listAjaxPageByUserCollection(usercol);
			json.put("pageStr", usercol.getAjaxPage().getPageStr());
			json.put("res_code", "0");
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
	 * 查询个人中心的收藏列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/colleListall")
	public void colleListall(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 先要查出该用户的id;根据ID查询该用户的所有商品id，然后根据所有商品的id查询出所有的商品
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			UserCollection usercol = new UserCollection();
			usercol.setUserId(user.getId());
			List<Map<String, Object>> list = userCollectser.getByUserCollection(usercol);
			json.put("res_code", "0");
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
	 * 删除收藏
	 * 
	 * @param DATA
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteCollect")
	public void deleteCollect(String id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (user != null) {
			// 否是删除某个收藏
			if (userCollectser.deleteByPrimary(id) > 0) {
				json.put("res_code", "0");
				json.put("message", "删除收藏成功");
			} else {
				json.put("res_code", "user.ER1025");
				json.put("message", BaseConfig.MESSAGE.get("userCollect.ER1017"));
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
	 * 删除收藏
	 * 
	 * @param DATA
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteCollectPc")
	public void deleteCollectPc(String id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		Product pro = productService.selectByPrimaryKey(Integer.parseInt(id));
		String proName = pro.getName();
		if (user != null) {
			// 否是删除某个收藏
			if (userCollectser.deleteByPrimary(id) > 0) {
				json.put("res_code", "0");
				json.put("message", "删除收藏成功");
				json.put("resName", proName);
			} else {
				json.put("res_code", "user.ER1025");
				json.put("resName", proName);
				json.put("message", BaseConfig.MESSAGE.get("userCollect.ER1017"));
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
	 * 添加收藏
	 * 
	 * @param request
	 * @param resposne
	 * @param session
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addCart")
	public void addCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer prodId) throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户
		if (user == null) {// 用户未登录
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {// 用户已经登录
			UserCollection usercollection = new UserCollection();
			usercollection.setProdId(prodId);
			usercollection.setUserId(user.getId());
			usercollection.setAddTime(new Date());
			String prodIds = "" + prodId;
			List<Integer> canNotSaleProduct = productService.getCangProductVsUserId(prodIds, user.getId());
			int i;
			if (canNotSaleProduct.size() == 0) {
				i = userCollectser.insertSelective(usercollection);
			} else {
				i = -1;
			}
			if (i > 0) {
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE, "success");
			} else if (i == -1) {
				json.put(BaseConfig.RESCODE, "3");
				json.put(BaseConfig.RESMESSAGE, "已经收藏过该商品！");
			} else {
				json.put(BaseConfig.RESCODE, "3");
				json.put(BaseConfig.RESMESSAGE, "收藏失败！请刷新后重试");
			}
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
