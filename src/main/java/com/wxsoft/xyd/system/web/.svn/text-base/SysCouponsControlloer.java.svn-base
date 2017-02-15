package com.wxsoft.xyd.system.web;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.SysCouponsService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.SysCouponsConf;
import com.wxsoft.xyd.system.service.SysCouponsConfService;

/**
 * 优惠券管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/sysCoupons")
public class SysCouponsControlloer extends BaseController {

	@Autowired
	private SysCouponsService sysCouponsService;
	@Autowired
	private SysCouponsConfService sysCouponsConfService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;

	/**
	 * 优惠券列表
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SysCoupons obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<SysCoupons> list = sysCouponsService.listPageBySysCoupons(obj);
		mv.addObject("objList", list);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysCoupons/list");
		return mv;
	}

	/**
	 * 分享赢优惠券配置
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/conf")
	public ModelAndView conf(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		SysCouponsConf obj = sysCouponsConfService.selectByPrimaryKey(1);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysCoupons/conf");
		return mv;
	}

	/**
	 * 保存分享赢优惠券配置
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveconf")
	public void saveconf(SysCouponsConf obj, HttpServletRequest request,
			Double priceRangel, Double priceRangeh,
			HttpServletResponse response, HttpSession session) {
		ResultModel rm = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		if (null != myfile && !myfile.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile, "sysCoupons",
					new Integer[] { 640 }, new Integer[] { 700 });
			if (null != filePath) {
				obj.setPicUrl(Tools.imgZuhe(filePath, "_640_700"));
			}
		}
		if (Tools.notEmpty(obj.getContent()) || null != obj.getTimeRange()
				|| obj.getTimeRange() == 0 || obj.getCount() == 0
				|| priceRangeh <= priceRangel) {
			obj.setPriceRange(priceRangel + "-" + priceRangeh);
			if (null == obj.getId()) {
				// 添加
				int i = sysCouponsConfService.insertSelective(obj,
						getSysLog(request, "修改分享赢优惠券配置"));
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "conf.html");
				}
			} else {

				int i = sysCouponsConfService.updateByPrimaryKeySelective(obj,
						getSysLog(request, "修改分享赢优惠券配置"));
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "conf.html");
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
	 * 已领取优惠券列表
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/receivelist")
	public ModelAndView receivelist(SysCouponsRecord obj,
			HttpServletRequest request, Integer couponsId,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// 获取当前优惠券信息
		SysCoupons sc = sysCouponsService.selectByPrimaryKey(couponsId);
		obj.setCouponsId(couponsId);
		List<Map<String, Object>> list = sysCouponsRecordService
				.listPageBySysCouponsRecordWithUser(obj);

		mv.addObject("objList", list);
		mv.addObject("obj", obj);
		mv.addObject("coupons", sc.getName());
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/sysCoupons/receivelist");
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
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysCoupons/info");
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
		SysCoupons obj = sysCouponsService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysCoupons/info");
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
	public void save(SysCoupons obj, HttpServletRequest request,
			String starttime, HttpServletResponse response) {
		ResultModel rm = null;
		Company company = (Company) request.getSession().getAttribute(
				SystemConfig.SESSION_USER);
		if (Tools.notEmpty(obj.getName()) || Tools.notEmpty(obj.getRemark())
				|| Tools.notEmpty(starttime)
				|| obj.getNeedPrice().doubleValue() > 0
				|| obj.getSubstractPrice().doubleValue() > 0) {
			if (obj.getType() == 1) {
				if (!Tools.notEmpty(starttime)) {
					rm = new ResultModel("友情提示", "请填写允许使用时间", "error", "");
				}
			} else {
				obj.setAllCount(0);
				if (null == obj.getValidTime() || obj.getValidTime() <= 0) {
					rm = new ResultModel("友情提示", "请填写领取后有效时间", "error", "");
				}
			}
			if (null == rm) {
				if (obj.getType() == 1) {
					String[] tempTime = starttime.split(" ~ ");
					obj.setStartTime(Tools.str2Date(tempTime[0] + " 00:00:00"));
					obj.setEndTime(Tools.str2Date(tempTime[1] + " 23:59:59"));
				}
				if (null == obj.getId()) {
					obj.setAddtime(new Date());
					obj.setLeftCount(obj.getAllCount());
					obj.setAdduserid(company.getCompanyId());
					// 添加
					int i = sysCouponsService.insertSelective(obj,
							getSysLog(request, "新增优惠券:" + obj.getName()));
					if (i > 0) {
						rm = new ResultModel("友情提示", "保存成功", "success",
								"list.html");
					}
				} else {

					int i = sysCouponsService.updateByPrimaryKeySelective(
							obj,
							getSysLog(request, "修改优惠券:" + obj.getName() + ","
									+ obj.getId()));
					if (i > 0) {
						rm = new ResultModel("友情提示", "保存成功", "success",
								"list.html");
					}
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
			SysCoupons sc = new SysCoupons();
			sc.setId(Integer.parseInt(string));
			sc.setDelFlag(0);
			SysCoupons scTemp = sysCouponsService
					.selectByPrimaryKey(sc.getId());
			if (sysCouponsService.deleteByPrimaryKey(sc,
					getSysLog(request, "删除优惠券:" + scTemp.getName())) > 0) {
				result = "{'flag':true}";
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
