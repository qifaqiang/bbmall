package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
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
import com.wxsoft.xyd.ad.model.AdConfig;
import com.wxsoft.xyd.ad.service.AdConfigService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 媒体资讯管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/adconfig")
public class AdConfigController extends BaseController {
	@Autowired
	private AdConfigService adConfigService;

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
	public ModelAndView list(AdConfig wb, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<AdConfig> rollPicList = adConfigService.listPageByAdConfig(wb);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", wb);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/adconfig/list");
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
	public ModelAndView addRollPic(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/adconfig/info");
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
	public ModelAndView updateRollPic(HttpServletRequest request,
			HttpSession session, Integer id) {
		ModelAndView mv = new ModelAndView();
		AdConfig rollPicl = adConfigService.selectByPrimaryKey(id);
		mv.addObject("rollPic", rollPicl);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/adconfig/info");
		return mv;

	}

	/**
	 * 保存
	 * 
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(AdConfig wb, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (null == wb.getId()) {
			// 添加
			int i = adConfigService.insertSelective(wb);
			if (i > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
			}

		} else {
			int i = adConfigService.updateByPrimaryKeySelective(wb);
			if (i > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
			}
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
			if (!string.equals(1)) {
				if (adConfigService
						.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
					result = "{'flag':true}";
				}
			} else {
				result = "{'flag':false}";
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
