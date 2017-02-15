package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;

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
import com.wxsoft.framework.util.HTMLInputFilter;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserComment;
import com.wxsoft.xyd.common.service.UserCommentService;
import com.wxsoft.xyd.order.service.OrdersDetailService;

/**
 * 评价
 * 
 * @author cx
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/comment")
public class CommentWebController extends BaseController {

	@Autowired
	private UserCommentService userCommentService;
	@Autowired
	private OrdersDetailService ordersDetailService;

	/**
	 * 提交评论
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/commentComit", method = RequestMethod.POST)
	public void commentComit(UserComment userCom, String picuri,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (su != null) {
			userCom.setName(su.getName());
			userCom.setUserId(su.getId());
			UserComment u = new UserComment();
			u.setUserId(su.getId());
			u.setOrderDetailId(userCom.getOrderDetailId());
			u.setProdId(userCom.getProdId());
			UserComment user = userCommentService.selectByUserComment(u);
			if (user == null) {
				userCom.setPicurl(picuri);

				if (userCom.getStarCount() == null) {
					userCom.setStarCount(0);
				}
				// 好评
				if (userCom.getStarCount() > BaseConfig.sysProportion
						.getMiddlePraiseScope()) {
					userCom.setCommentLevel(1);
				}
				// 中评
				if (userCom.getStarCount() > BaseConfig.sysProportion
						.getBadPraiseScope()
						&& userCom.getStarCount() <= BaseConfig.sysProportion
								.getMiddlePraiseScope()) {
					userCom.setCommentLevel(2);
				}
				// 差评
				if (userCom.getStarCount() <= BaseConfig.sysProportion
						.getBadPraiseScope()) {
					userCom.setCommentLevel(3);
				}
				userCom.setvState(3);
				userCom.setContent(new HTMLInputFilter().filter(userCom
						.getContent()));
				if (userCommentService.insertSelective(userCom) > 0) {
					json.put("res_code", "0");
					json.put("message", "评论成功");
					json.put(BaseConfig.RESURL, BaseConfig.FX_DOMAIN_WWW
							+ "/wap/personalCenter-myOrder-w.html?url=4");
				} else {
					// 评论失败
					json.put("res_code", "ER6001");
					json.put("message", BaseConfig.MESSAGE
							.get("comment.ER6001").toString());
					json.put(BaseConfig.RESURL, BaseConfig.FX_DOMAIN_WWW
							+ "/wap/personalCenter-myOrder-w.html?url=4");
				}
			} else {
				// 请勿重新提交评论
				json.put("res_code", "ER6002");
				json.put("message", BaseConfig.MESSAGE.get("comment.ER6002")
						.toString());
				json.put(BaseConfig.RESURL, BaseConfig.FX_DOMAIN_WWW
						+ "/wap/personalCenter-myOrder-w.html?url=4");
			}
		} else {
			// 未登录
			json.put("res_code", "1");
			json.put("message", BaseConfig.MESSAGE.get("user.ER1025"));
			json.put(BaseConfig.RESURL, BaseConfig.FX_DOMAIN_WWW
					+ "/wap/personalCenter-myOrder-w.html?url=4");
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 提交评论
	 * 
	 * @param userLocal
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/commentComitPc", method = RequestMethod.POST)
	public void commentComitPc(Integer starCount, String content,
			String picuri, Integer orderDetailId, Integer ProdId,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		UserComment userCom = new UserComment();
		if (su != null) {
			userCom.setName(su.getName());
			userCom.setUserId(su.getId());
			userCom.setStarCount(starCount);
			userCom.setContent(content);
			userCom.setOrderDetailId(orderDetailId);
			userCom.setProdId(ProdId);
			UserComment u = new UserComment();
			u.setUserId(su.getId());
			u.setOrderDetailId(orderDetailId);
			u.setProdId(ProdId);
			UserComment user = userCommentService.selectByUserComment(u);
			if (user == null) {
				userCom.setPicurl(picuri);
				if (starCount == null) {
					userCom.setStarCount(0);
				}
				// 好评
				if (starCount > BaseConfig.sysProportion.getMiddlePraiseScope()) {
					userCom.setCommentLevel(1);
				}
				// 中评
				if (starCount > BaseConfig.sysProportion.getBadPraiseScope()
						&& starCount <= BaseConfig.sysProportion
								.getMiddlePraiseScope()) {
					userCom.setCommentLevel(2);
				}
				// 差评
				if (starCount <= BaseConfig.sysProportion.getBadPraiseScope()) {
					userCom.setCommentLevel(3);
				}
				userCom.setvState(3);
				userCom.setContent(new HTMLInputFilter().filter(userCom
						.getContent()));
				if (userCommentService.insertSelective(userCom) > 0) {
					json.put("res_code", "0");
					json.put("message", "评论成功");
				} else {
					// 评论失败
					json.put("res_code", "ER6001");
					json.put("message", BaseConfig.MESSAGE
							.get("comment.ER6001").toString());
				}
			} else {
				// 请勿重新提交评论
				json.put("res_code", "ER6002");
				json.put("message", BaseConfig.MESSAGE.get("comment.ER6002")
						.toString());
			}
		} else {
			// 未登录
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