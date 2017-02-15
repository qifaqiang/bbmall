package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.wxsoft.xyd.prod.model.ProductSpecification;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 商品规格管理
 * 
 * @author qfq
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/prodSpec")
public class ProductSpecControlloer extends BaseController {
	@Autowired
	private ProductSpecificationService productSpecificationService;

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
	public ModelAndView list(ProductSpecification obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodSpec/list");
		List<ProductSpecification> list = productSpecificationService.listPageByProductSpecification(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
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
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodSpec/info");
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
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodSpec/info");
		ProductSpecification obj = productSpecificationService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
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
	public void save(ProductSpecification obj, HttpServletRequest request,HttpSession session,
			HttpServletResponse response,String[] detailNames, Integer[] skus) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getSpecificationName())) {
			obj.setCompanyId(((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId());
			
			if (null == obj.getId()) {
				// 添加
				int i = productSpecificationService.insertSelective(obj,detailNames,skus);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				int i = productSpecificationService.updateByPrimaryKeySelective(obj,detailNames,skus);
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
		if (productSpecificationService.deleteByPrimaryKey(Integer.parseInt(listId[0])) > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
