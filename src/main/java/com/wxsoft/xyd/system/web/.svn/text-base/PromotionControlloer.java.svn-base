package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.service.AdDetailService;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.PromotionProduct;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.common.service.PromotionProductService;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 活动促销管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/promotion")
public class PromotionControlloer extends BaseController {

	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private PromotionProductService promotionProductService;
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AdDetailService adDetailService;

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
	public ModelAndView list(PromotionActivity obj, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<PromotionActivity> list = promotionActivityService.listPageByPromotionActivity(obj);

		mv.addObject("objList", list);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/promotion/list");
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
		ProductCatalog cat = new ProductCatalog();
		cat.setPid(0);
		List<ProductCatalog> rollPicList = productCatalogService.getAllByProductCatalog(cat);
		mv.addObject("catList", rollPicList);
		/*
		 * String selected = null; mv.addObject("selected", selected);
		 */
		AdDetail sele = new AdDetail();
		sele.setType(8);
		List<AdDetail> adDetil = adDetailService.getAllByAdDetail(sele);
		mv.addObject("adDetil", adDetil);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/promotion/info");
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
	public ModelAndView update(HttpServletRequest request, HttpSession session, Integer id) {
		ModelAndView mv = new ModelAndView();
		PromotionActivity obj = promotionActivityService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		// 商品列表
		ProductCatalog cat = new ProductCatalog();
		cat.setPid(0);
		List<ProductCatalog> rollPicList = productCatalogService.getAllByProductCatalog(cat);
		mv.addObject("catList", rollPicList);
		
		AdDetail sele = new AdDetail();
		sele.setType(8);
		List<AdDetail> adDetil = adDetailService.getAllByAdDetail(sele);
		mv.addObject("adDetil", adDetil);
		
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/promotion/info");
		return mv;

	}

	@RequestMapping("/getselect")
	public void getselect(Integer id, Integer showCount, Integer currentPage, HttpServletRequest request, HttpServletResponse response) {
		if (null == showCount || showCount == 0) {
			showCount = 20;
		}
		String orderBy = "";
		if (Tools.isEmpty(orderBy)) {
			orderBy += " tmp.addtime desc";
		}

		// 获取已选择商品列表
		// List<Map<String, Object>> prodList = new ArrayList<Map<String,
		// Object>>();
		// Product proact = new Product();
		// 根据商品id获取所有的promotion_id
		PromotionProduct selec = new PromotionProduct();
		selec.setId(id);

		AjaxPage ap = new AjaxPage();
		ap.setShowCount(showCount);
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("getProList");
		selec.setAjaxPage(ap);

		List<Map<String, Object>> selected = promotionProductService.listAjaxPagePromotionSelected(selec);
		/*
		 * List<JSONObject> listroll = new ArrayList<JSONObject>(); //
		 * 遍历selected,逐条取出promotion_activity for (PromotionProduct
		 * promotionProduct : selected) {
		 * proact.setId(promotionProduct.getProdId()); Product roll =
		 * productService.selectByProduct(proact);
		 * listroll.add(JsonLibUtils.ObjectToJSONWithConfig(roll,
		 * "id,picuri,name,price")); }
		 */

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", selected);
		map.put("page", selec.getAjaxPage());
		try {
			responseAjax(map, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 保存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(PromotionActivity obj, String starttime, String prodId, HttpServletRequest request, HttpServletResponse response) {
		if (Tools.notEmpty(starttime)) {
			String[] tempTime = starttime.split(" ~ ");
			obj.setStartTime(Tools.str2Date(tempTime[0] + " 00:00:00"));
			obj.setEndTime(Tools.str2Date(tempTime[1] + " 23:59:59"));
		}
		// 获取两张图片
		ResultModel rm = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile1 = multipartRequest.getFile("myfile1");
		if (null != myfile1 && !myfile1.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile1, "PromotionActivity", new Integer[] { 300 }, new Integer[] { 300 });
			if (null != filePath) {
				obj.setPicUrl(filePath);
			}
		}
		MultipartFile myfile2 = multipartRequest.getFile("myfile2");
		if (null != myfile2 && !myfile2.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile2, "PromotionActivity", new Integer[] { 640 }, new Integer[] { 251 });
			if (null != filePath) {
				obj.setSpreadUrl((filePath));
			}

		}
		MultipartFile myfile3 = multipartRequest.getFile("myfile3");
		if (null != myfile3 && !myfile3.isEmpty()) {
			String filePath = Tools.uploadpicByRectangle(myfile3, "PromotionActivity", new Integer[] { 1920 }, new Integer[] { 350 });
			if (null != filePath) {
				obj.setPcspreadUrl((filePath));
			}

		}
		// 判断是否全场商品 0否 1是 0话就添加关联
		if (obj.getType() == 1) {
			obj.setSubstractPrice(null);
		}
		if (obj.getType() == 0) {
			obj.setDiscount(0);
		}

		if (obj.getId() == null) {
			int i = promotionActivityService.insertSelective(obj, getSysLog(request, "添加促销"), prodId);
			if (i > 0) {
				rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
			}
		} else {
			int i = promotionActivityService.updateByPrimaryKeySelective(obj, getSysLog(request, "修改促销"), prodId);
			if (i > 0) {
				rm = new ResultModel("友情提示", "修改成功", "success", "list.html");
			}
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/setIsTop")
	public void setIsTop(HttpServletRequest request, HttpServletResponse response, Integer id, Integer istop) {
		String result = "{'flag':false}";
		PromotionActivity proac = new PromotionActivity();
		if (null == istop) {
			proac.setIstop(1);
		} else {
			proac.setIstop(istop == 1 ? 0 : 1);
		}
		proac.setId(id);
		int res = promotionActivityService.updateByPrimaryKey(proac);
		if (res > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 删除
	 * 
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res, String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			if (promotionActivityService.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
				result = "{'flag':true}";
			}
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
