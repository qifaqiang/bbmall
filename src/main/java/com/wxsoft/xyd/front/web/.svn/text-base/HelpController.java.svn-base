package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import com.wxsoft.framework.util.HTMLInputFilter;
import com.wxsoft.xyd.common.model.SysFeedback;
import com.wxsoft.xyd.common.model.SysNews;
import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.SysFeedbackService;
import com.wxsoft.xyd.common.service.SysNewsCatalogService;
import com.wxsoft.xyd.common.service.SysNewsService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.SysNotice;
import com.wxsoft.xyd.system.service.SysNoticeService;

/**
 * 帮助中心
 * 
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_USER + "/help")
public class HelpController extends BaseController {
	@Autowired
	private SysNewsCatalogService sysNewsCatalogService;
	@Autowired
	private SysNewsService sysNewsService;
	@Autowired
	private SysFeedbackService sysFeedbackService;
	@Autowired
	private SysNoticeService sysNoticeService;

	/**
	 * 帮助列表
	 * 
	 * @return
	 */
	@RequestMapping("/helpList")
	public void helpList(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		SysNewsCatalog sc = new SysNewsCatalog();
		sc.setIsWep(0);
		List<SysNewsCatalog> sclist = sysNewsCatalogService
				.getAllBySysNewsCatalog(sc);
		for (SysNewsCatalog s : sclist) {
			SysNews snTemp = new SysNews();
			snTemp.setCatalogId(s.getId());
			List<SysNews> snlist = sysNewsService.getAllBySysNews(snTemp);
			s.setSn(snlist);
		}
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, sclist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 帮助文章列表
	 * 
	 * @return
	 */
	@RequestMapping("/helpnewsList")
	public void helpnewsList(HttpServletResponse response,
			HttpServletRequest request, Integer id, HttpSession session) {
		JSONObject json = new JSONObject();
		SysNews sc = new SysNews();
		sc.setId(id);
		List<SysNews> sclist = sysNewsService.getAllBySysNews(sc);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, sclist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户的意见反馈录入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/feedBack", method = RequestMethod.POST)
	public void feedBack(HttpServletResponse response,
			HttpServletRequest request, String counting, HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (null == com) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
		} else {
			SysFeedback sys = new SysFeedback();
			sys.setContent(new HTMLInputFilter().filter(counting));
			sys.setUserId(com.getId());
			sys.setAddtime(new Date());
			int i = sysFeedbackService.insertSelective(sys);
			if (i > 0) {
				// 提交成功！谢谢您的支持！
				json.put(BaseConfig.RESCODE, "0");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("feedback.ER9003").toString());
			} else {
				// 系统错误
				json.put(BaseConfig.RESCODE, "2");
				json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
						.get("ER9999").toString());
			}
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取公告内容
	 * 
	 * @return
	 */
	@RequestMapping("/noticeCount")
	public void noticeCount(HttpServletResponse response, Integer id,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		SysNotice sclist = sysNoticeService.selectByPrimaryKey(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm");
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put("content", sclist);
		json.put("time", format.format(sclist.getAddtime()));
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取公告列表
	 * 
	 * @return
	 */
	@RequestMapping("/noticelist")
	public void noticelist(HttpServletResponse response, Integer id,
			Integer currentPage, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("noticelist");
		ap.setShowCount(10);
		SysNotice s = new SysNotice();
		s.setAjaxPage(ap);
		List<Map<String, Object>> sclist = sysNoticeService
				.listAjaxPageCoupons(s);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put("list", sclist);
		json.put("pageCount", s.getAjaxPage().getTotalPage());
		json.put("pageStr", s.getAjaxPage().getPageStr());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}