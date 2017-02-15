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
import com.wxsoft.xyd.common.model.SysNews;
import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.common.service.SysNewsCatalogService;
import com.wxsoft.xyd.common.service.SysNewsService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 媒体资讯管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/sysNews")
public class SysNewsControlloer extends BaseController {
	@Autowired
	private SysNewsService sysNewsService;
	@Autowired
	private SysNewsCatalogService sysNewsCataloyService;
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
	public ModelAndView list(SysNews obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<SysNews> list = sysNewsService.listPageBySysNews(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
		
		//查询出所有的帮助列表的一级分类名称和二级分类名称
		SysNewsCatalog ob=new SysNewsCatalog();
		ob.setPid(0);
		List<SysNewsCatalog> rollPicList = sysNewsCataloyService.getAllBySysNewsCatalog(ob);
		mv.addObject("rollPicList", rollPicList);
		
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNews/list");
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
		SysNewsCatalog pc = new SysNewsCatalog();
		pc.setPid(0);
		List<SysNewsCatalog> list = sysNewsCataloyService.getAllBySysNewsCatalog(pc);
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNews/info");
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
		SysNews obj = sysNewsService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		SysNewsCatalog pc = new SysNewsCatalog();
		pc.setPid(0);
		List<SysNewsCatalog> list = sysNewsCataloyService.getAllBySysNewsCatalog(pc);
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNews/info");
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
	public void save(SysNews obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getTitle())) {
			if (null == obj.getId()) {
				obj.setAddtime(new Date());
				// 添加
				int i = sysNewsService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "添加成功", "success", "list.html");
				}

			} else {
				int i = sysNewsService.updateByPrimaryKeySelective(obj);
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
			if (sysNewsService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
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
