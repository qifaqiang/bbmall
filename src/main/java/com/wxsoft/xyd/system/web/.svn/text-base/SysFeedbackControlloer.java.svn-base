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
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysFeedback;
import com.wxsoft.xyd.common.service.SysFeedbackService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 意见反馈管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/sysFeedback")
public class SysFeedbackControlloer extends BaseController {
	@Autowired
	private SysFeedbackService sysFeedbackService;
	@Autowired
	private SysFeedbackService sysFeedbackCataloyService;
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
	public ModelAndView list(SysFeedback obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<SysFeedback> list = sysFeedbackService.listPageBySysFeedback(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysFeedback/list");
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
		SysFeedback pc = new SysFeedback();
		//pc.setPid(0);
		List<SysFeedback> list = sysFeedbackCataloyService.getAllBySysFeedback(pc);
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysFeedback/info");
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
		SysFeedback obj = sysFeedbackService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		List<SysFeedback> list = sysFeedbackCataloyService.getAllBySysFeedback(obj);
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysFeedback/info");
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
	public void save(SysFeedback obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getContent())) {
			if (null == obj.getId()) {
				obj.setAddtime(new Date());
				// 添加
				int i = sysFeedbackService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "添加成功", "success", "list.html");
				}

			} else {
				int i = sysFeedbackService.updateByPrimaryKeySelective(obj);
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
			if (sysFeedbackService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
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
