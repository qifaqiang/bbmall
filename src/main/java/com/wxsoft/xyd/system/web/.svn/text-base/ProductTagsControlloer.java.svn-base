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
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.prod.model.ProductTags;
import com.wxsoft.xyd.prod.service.ProductTagsService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 商品标签管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/prodTags")
public class ProductTagsControlloer extends BaseController {
	@Autowired
	private ProductTagsService productTagsService;

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
	public ModelAndView list(ProductTags obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<ProductTags> list = productTagsService.listPageByProductTags(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodTags/list");
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
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodTags/info");
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
		ProductTags obj = productTagsService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodTags/info");
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
	public void save(ProductTags obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getName())) {
			if (null == obj.getId()) {
				// 添加
				int i = productTagsService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				int i = productTagsService.updateByPrimaryKeySelective(obj);
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
			if (productTagsService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
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
