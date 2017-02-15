package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.common.model.UserComment;
import com.wxsoft.xyd.common.service.UserCommentService;
import com.wxsoft.xyd.prod.service.ProductStatisticsService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 用户评价管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/userComment")
public class UserCommentControlloer extends BaseController {
	@Autowired
	private UserCommentService userCommentService;
	@Autowired
	private ProductStatisticsService productStatisticsService;

	/**
	 * 列表
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(UserComment obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		obj.setvState(3);
		List<UserComment> rollPicList = userCommentService
				.listPageByUserComment(obj);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userComment/list");
		return mv;
	}

	@RequestMapping("/overList")
	public ModelAndView overList(UserComment obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		obj.setvState(1);
		ModelAndView mv = new ModelAndView();
		List<UserComment> rollPicList = userCommentService
				.listPageByUserComment(obj);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userComment/overList");
		return mv;
	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public void update(UserComment obj, HttpServletRequest request,
			HttpSession session, HttpServletResponse response)
			throws java.lang.Exception {
		ResultModel rm = null;
		obj.setvTime(new Date());
		// 保存审核后的内容
		if (obj.getId() != null) {
			userCommentService.updateByPrimaryKeySelective(obj);
			rm = new ResultModel("友情提示", "更新成功", "success", "");
		} else {
			rm = new ResultModel("友情提示", "更新出错，请重新输入", "success", "");
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public ModelAndView save(UserComment obj, HttpServletRequest request,
			HttpServletResponse response, String vComment, Integer vState) {
		ModelAndView mv = new ModelAndView();
		List<UserComment> rollPicList = userCommentService
				.listPageByUserComment(obj);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", obj);
		obj.setvComment(vComment);
		obj.setvState(vState);
		// 实时记录审核时间
		obj.setvTime(new Date());
		// 保存审核后的内容
		if (obj.getId() != null) {
			userCommentService.updateByPrimaryKeySelective(obj);
		}
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userComment/list");
		return mv;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			if (userCommentService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
				result = "{'flag':true}";
			}
		}
		System.out.println(result);
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
