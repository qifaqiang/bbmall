package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
/**
 * Title:促销活动
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES+ "/pro")
public class ProductListController extends BaseController {
	@Autowired
	private ProductCatalogService productCatalogService;
	/**
	 * 获取所有商品的分类
	 * 
	 * @return
	 */
	@RequestMapping("/product")
	public void product(HttpServletResponse response, Integer type,String name,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		ProductCatalog pd = new ProductCatalog();
		pd.setName(name);
		// pd.setPid(type);;// 0 商品分类
		List<Map<String, Object>> pdlist = productCatalogService
				.getAllByProductCatalogIndexRecommend(pd);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, pdlist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
