package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
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
import com.wxsoft.xyd.ad.model.AdConfig;
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.service.AdConfigService;
import com.wxsoft.xyd.ad.service.AdDetailService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 媒体资讯管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/addetail")
public class AdDetailController extends BaseController {
	@Autowired
	private AdDetailService adDetailService;
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
	public ModelAndView list(AdDetail wb, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<AdDetail> rollPicList = adDetailService.listPageByAdDetail(wb);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", wb);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/addetail/list");
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
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/addetail/info");
		List<AdConfig> adlist = adConfigService.getAllByAdConfig(null);
		mv.addObject("adlist", adlist);
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
		AdDetail rollPicl = adDetailService.selectByPrimaryKey(id);
		mv.addObject("rollPic", rollPicl);
		List<AdConfig> adlist = adConfigService.getAllByAdConfig(null);
		mv.addObject("adlist", adlist);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/ad/addetail/info");
		return mv;

	}

	/**
	 * 保存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @throws ParseException
	 */
	@RequestMapping("/save")
	public void save(AdDetail wb, HttpServletRequest request, String setime,
			HttpServletResponse response) throws ParseException {
		ResultModel rm = null;
		AdConfig ad = adConfigService.selectByPrimaryKey(wb.getType());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		if (null != myfile && !myfile.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile, "ad",
					new Integer[]{ad.getWidth()}, new Integer[]{ad.getHigh()});
			if (null != filePath) {
				wb.setPicUrl(filePath);
				wb.setUsepicurl(Tools.imgZuhe(filePath, "_"+ad.getWidth()+"_"+ad.getHigh()));
			}
		}

		String[] tempTime = setime.split("~");
		wb.setStartTime(DateUtils.parseDate(tempTime[0].trim() + " 00:00:00",
				"yyyy-MM-dd HH:mm:ss"));
		wb.setEndTime(DateUtils.parseDate(tempTime[1].trim() + " 23:59:59",
				"yyyy-MM-dd HH:mm:ss"));
		if (null == wb.getId()) {
			wb.setAddtime(new Date());
			// 添加
			int i = adDetailService.insertSelective(wb);
			if (i > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
			}

		} else {
			int i = adDetailService.updateByPrimaryKeySelective(wb);
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
				if (adDetailService
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
