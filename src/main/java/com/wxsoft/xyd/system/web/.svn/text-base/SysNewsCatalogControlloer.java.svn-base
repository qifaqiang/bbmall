package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.common.service.SysNewsCatalogService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 媒体资讯管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM
		+ "/sysNewsCatalog")
public class SysNewsCatalogControlloer extends BaseController {
	@Autowired
	private SysNewsCatalogService sysNewsCatalogService;

	/**
	 * 列表
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SysNewsCatalog obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		obj.setPid(0);
		List<SysNewsCatalog> rollPicList = sysNewsCatalogService.getAllBySysNewsCatalog(obj);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNewsCatalog/list");
		return mv;
	}

	/**
	 * 添加
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<SysNewsCatalog> list = sysNewsCatalogService.getFirstProdCatalog();
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNewsCatalog/info");
		return mv;
	}

	/**
	 * 更新,编辑修改新闻类型
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
		SysNewsCatalog obj = sysNewsCatalogService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		List<SysNewsCatalog> list = sysNewsCatalogService.getFirstProdCatalog();
		//在下拉列表中去掉自己
		List<SysNewsCatalog> rem=new ArrayList<SysNewsCatalog>();
		for (SysNewsCatalog stu : list) {    
	        if (stu.getId() == id)     
	        	rem.add(stu);    
	    }   
		list.removeAll(rem);
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/sysNewsCatalog/info");
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
	public void save(SysNewsCatalog obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		if (null != myfile && !myfile.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile, "sysnews",
					new Integer[] { 28 }, new Integer[] { 28 });
			if (null != filePath) {
				obj.setPic(filePath);
			}
		}
		if (Tools.notEmpty(obj.getName())) {
			if (null == obj.getId()) {
				obj.setAddtime(new Date());
				if (null == obj.getPid() || obj.getPid() == 0) {
					obj.setLevel(1);
					obj.setPid(0);
				} else {
					obj.setLevel(2);
				}
				// 添加
				int i =sysNewsCatalogService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				if (null == obj.getPid() || obj.getPid() == 0) {
					obj.setLevel(1);
					obj.setPid(0);
				}
				else{
					obj.setLevel(2);
				}
				
				int i = sysNewsCatalogService.updateByPrimaryKeySelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
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
			SysNewsCatalog rem =sysNewsCatalogService.selectByPrimaryKey(Integer.parseInt(string));
			if(rem.getLevel()==1){
				sysNewsCatalogService.deleteByPid(rem.getPid());
			}
			if (sysNewsCatalogService.deleteByPrimaryKey(Integer
					.parseInt(string)) > 0) {
				result = "{'flag':true}";
				//判断删除的字段等级，如果是一级，则删除其下的2级元素
				}
			}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
