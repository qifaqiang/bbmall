package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
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
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.service.AdDetailService;
import com.wxsoft.xyd.common.model.RollPic;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.common.service.RollPicService;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.SysCouponsService;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.SysNotice;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.SysNoticeService;

/**
 * 
 * Title: 主页接口 Description: Company:
 * 
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES)
public class IndexController extends BaseController {
	@Autowired
	private RollPicService rollPicService;
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AdDetailService adDetailService;
	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private SysCouponsService sysCouponsService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private SysNoticeService sysNoticeService;

	/**
	 * 首页轮播图
	 * 
	 * @return
	 */
	@RequestMapping("/rollpic")
	public void rollPic(HttpServletResponse response, Integer type,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		RollPic rp = new RollPic();
		rp.setType(type);// 1pc 2wap
		List<Map<String, Object>> rplist = rollPicService.getAllByRollPic(rp);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, rplist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 首页公告
	 * 
	 * @return
	 */
	@RequestMapping("/noticeList")
	public void noticeList(HttpServletResponse response, Integer type,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		SysNotice rp = new SysNotice();
		List<Map<String, Object>> rplist = sysNoticeService
				.getAllBySysNotice(rp);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, rplist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 8大分类模块
	 * 
	 * @return
	 */
	@RequestMapping("/productcataloglist")
	public void productCatalogList(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		ProductCatalog pc = new ProductCatalog();
		pc.setIsTop(1);
		pc.setLevel(1);
		List<Map<String, Object>> pclist = productCatalogService
				.getAllByProductCatalogIndexNavigation(pc);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, pclist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 首页下方推荐
	 * 
	 * @return
	 */
	@RequestMapping("/productcatalogrecommended")
	public void productCatalogRecommended(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		ProductCatalog pc = new ProductCatalog();
		pc.setIsRecommended(1);
		pc.setLevel(1);
		List<Map<String, Object>> pclist = productCatalogService
				.getAllByProductCatalogIndexRecommend(pc);

		for (Map<String, Object> map : pclist) {
			ProductCatalog pcSub = new ProductCatalog();
			// 放置二级分类
			pcSub.setPid(Integer.parseInt(map.get("id").toString()));
			List<Map<String, Object>> sublist = productCatalogService
					.getAllByProductCatalogFront(pcSub);
			map.put("sublist", sublist);

			// 放置推荐商品
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("catalogId1", Integer.parseInt(map.get("id").toString()));
			p.put("isTop", 1);
			List<Map<String, Object>> plist = productService
					.selectProductFrontByShowList(p);
			map.put("prodlist", plist);

		}
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, pclist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取基地坐标位置
	 * 
	 * @return
	 */
	@RequestMapping("/getCompanyXY")
	public void getCompanyXY(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		List<Map<String, Object>> xylist = companyService
				.selectCompanyByXY(null);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, xylist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取基地坐标位置
	 * 
	 * @return
	 */
	@RequestMapping("/getCompanyXYOne")
	public void getCompanyXYOne(HttpServletResponse response,
			HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		List<Map<String, Object>> xylist = companyService.selectCompanyByXY(null);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		Map<String, Object> temp0 = xylist.get(0);
		json.put("sys_base_companyId", temp0.get("company_id"));
		json.put("sys_company_name", temp0.get("company_name"));
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取基地id坐标位置
	 * 
	 * @return
	 */
	@RequestMapping("/getCompanyXYById")
	public void getCompanyXYById(HttpServletResponse response,
			Integer companyId, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		List<Map<String, Object>> xylist = companyService
				.selectCompanyByXY(companyId);
		if (xylist.size() > 0) {
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			Map<String, Object> temp0 = xylist.get(0);
			json.put("sys_base_companyId", temp0.get("company_id"));
			json.put("sys_company_name", temp0.get("company_name"));
		} else {
			//该基地不存在
			json.put(BaseConfig.RESCODE, "ER7001");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("company.ER7001").toString());
		}

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据商品id获取库存信息
	 * 
	 * @returnproductCatalog
	 */
	@RequestMapping("/getKuCunByProductId")
	public void getKuCunByProductId(HttpServletResponse response,
			String prodIds, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		List<Integer> canNotSaleProduct = productService
				.getKuCunByProductIdsVsCompanyId(prodIds, null, null, null);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, canNotSaleProduct);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据商品id获取是否在购物车中
	 * 
	 * @returnproductCatalog
	 */
	@RequestMapping("/getCarProduct")
	public void getCarProduct(HttpServletResponse response,
			String prodIds, Integer userId, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if(com==null){
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		}else{
		List<Integer> canNotSaleProduct = productService
				.getCarProductVsUserId(prodIds, com.getId());
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, canNotSaleProduct);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据商品id获取是否已经收藏
	 * @returnproductCatalog
	 */
	@RequestMapping("/getCangProduct")
	public void getCangProduct(HttpServletResponse response,
			String prodIds, Integer userId, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User com = new User();
		// 得到当前登陆用户的信息
		com = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if(com==null){
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		}else{
		List<Integer> canNotSaleProduct = productService
				.getCangProductVsUserId(prodIds, com.getId());
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, canNotSaleProduct);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取商品下的详细商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productlist", method = RequestMethod.POST)
	public void productlist(HttpServletResponse response, Integer catalogId1,Integer origin,BigDecimal price,
			Integer currentPage, Integer ty, Integer salesCount, String name,Integer zu,
			Integer catalogId2, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		Product pd = new Product();
		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("productlist");
		if(null==zu){
			ap.setShowCount(10);
			}else{
			ap.setShowCount(zu);
			}
		pd.setAjaxPage(ap);
		pd.setCatalogId1(catalogId1);
		if(catalogId2!=null){
		pd.setCatalogId2(catalogId2);
		}
		pd.setPrice(price);
		pd.setOrigin(origin);
		pd.setSalesCount(salesCount);
		pd.setType(ty);
		pd.setName(name);
		List<Map<String, Object>> list = productService
				.listAjaxPageProductList(pd);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, list);

		json.put("pageCount", pd.getAjaxPage().getTotalPage());
		json.put("pageStr", pd.getAjaxPage().getPageStr());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取商品下的详细商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productCatalog", method = RequestMethod.POST)
	public void productCatalog(HttpServletResponse response, Integer catalogId1, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		ProductCatalog pd = new ProductCatalog();
		pd.setPid(catalogId1);
		pd.setLevel(2);
		List<ProductCatalog> list=productCatalogService.getAllByProductCatalog(pd);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, list);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 新品推荐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newproductList", method = RequestMethod.POST)
	public void newproductlist(HttpServletResponse response, Integer catalogId1,
			Integer currentPage, Integer ty, Integer salesCount, String name,Integer zu,
			Integer catalogId2, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		Product pd = new Product();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("prodTagsId", 1);
		maps.put("limit", "8");
		List<Map<String, Object>> tuiProduct = productService
				.selectProductFrontByShowList(maps);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, tuiProduct);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 广告列表
	 * 
	 * @return
	 */
	@RequestMapping("/getXuanChuan")
	public void getXuanChuan(HttpServletResponse response, HttpServletRequest request,Integer type,
			HttpSession session) {
		JSONObject json = new JSONObject();
		AdDetail rp = new AdDetail();
		rp.setType(type);
		List<Map<String, Object>> rplist = adDetailService.getAllByMap(rp);
		// id,name,usepicurl
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESLIST, rplist);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
