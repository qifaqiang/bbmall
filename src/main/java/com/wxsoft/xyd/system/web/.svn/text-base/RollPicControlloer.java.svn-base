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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.RollPic;
import com.wxsoft.xyd.common.service.RollPicService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 轮播图管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/rollPic")
public class RollPicControlloer extends BaseController {
	@Autowired
	private RollPicService rollPicService;
	@Autowired
	private RollPicService rollPicCataloyService;

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
	public ModelAndView list(RollPic obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<RollPic> list = rollPicService.listPageByRollPic(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/rollPic/list");
		return mv;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/rollPic/info");
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
	public ModelAndView update(HttpServletRequest request, HttpSession session,
			Integer id) {
		ModelAndView mv = new ModelAndView();
		RollPic obj = rollPicService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/rollPic/info");
		return mv;

	}

	/**
	 * 保存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(RollPic obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		if (null != myfile && !myfile.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile, "rollPic",
					new Integer[] { 640,1920 }, new Integer[] { 300,350 });
			if (null != filePath) {
				obj.setPicUrl(filePath);
			}
		}
		if (Tools.notEmpty(obj.getTitle())) {
			if (null == obj.getId()) {
				obj.setAddtime(new Date());
				// 添加
				int i = rollPicService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "添加成功", "success", "list.html");
				}

			} else {
				int i = rollPicService.updateByPrimaryKeySelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "修改成功", "success", "list.html");
				}
			}
		} else {
			rm = new ResultModel("友情提示", "存在非必填项为空", "error", "");
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

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
			if (rollPicService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
				result = "{'flag':true}";
			}
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
