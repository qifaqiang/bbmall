/**   
 * @文件名称: ResponseControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.wxsoft.framework.json.JsonLibUtils;
import com.wxsoft.framework.util.GenerateNo;
import com.wxsoft.framework.util.SystemUtil;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.model.ProductPackage;
import com.wxsoft.xyd.prod.model.ProductSpecification;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.prod.model.ProductTags;
import com.wxsoft.xyd.prod.service.ProductBasicService;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductImageService;
import com.wxsoft.xyd.prod.service.ProductPackageService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoDetailService;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoService;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;
import com.wxsoft.xyd.prod.service.ProductTagsService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.service.CompanyService;

/**
 * @类功能说明：商品管理
 * @类修改者：kyz
 * @修改日期：2015-07-11
 * @修改说明：
 * @回复名称：kyz
 * @作者：kyz
 * @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/product")
public class ProductControlloer extends BaseController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductSpecificationService productSpecificationService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductSpecificationInfoService productSpecificationInfoService;
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private ProductTagsService productTagsService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductBasicService productBasicService;
	@Autowired
	private ProductPackageService productPackageService;
	@Autowired
	private ProductSpecificationInfoDetailService productSpecificationInfoDetailService;

	/**
	 * @param String
	 * 
	 * @描述: 商品列表
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/list")
	public ModelAndView list(Product prod, HttpServletRequest request,
			String sort1, String sort2, String catalogId, String mstat,
			HttpSession session, Object String) {
		prod.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());

		String orderBy = "";
		ModelAndView mv = new ModelAndView();
		if (Tools.isEmpty(orderBy)) {
			orderBy += " tmp.addtime desc";
		}
		if (prod.getName() != null && prod.getName().trim().equals("")) {
			prod.setName(null);
		}
		if (prod.getSn() != null && prod.getSn().trim().equals("")) {
			prod.setSn(null);
		}
		if (prod.getSellStatus() != null && prod.getSellStatus() == -1) {
			prod.setSellStatus(null);
		}
		if (prod.getType() != null && prod.getType() == -1) {
			prod.setType(null);
		}

		prod.setOrderBy(orderBy);
		ProductCatalog cat = new ProductCatalog();
		cat.setPid(0);
		List<ProductCatalog> rollPicList = productCatalogService
				.getAllByProductCatalog(cat);
		
		List<ProductTags> tagList = productTagsService
				.getAllByProductTags(null);
		mv.addObject("catList", rollPicList);
		mv.addObject("tagList", tagList);

		mv.addObject("obj", prod);
		mv.addObject("sort1", sort1);
		mv.addObject("sort2", sort2);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/info/list");
		return mv;
	}

	/**
	 * ajax循环商品列表
	 * 
	 * @param prod
	 * @param request
	 * @param sort1
	 * @param sort2
	 * @param response
	 * @param currentPage
	 * @param orderInfo
	 * @param catalogId
	 * @param mstat
	 * @param session
	 * @param String
	 */
	@RequestMapping("/getlist")
	public void index(Product prod, Integer showCount,
			HttpServletRequest request, String sort1, String sort2,
			HttpServletResponse response, Integer currentPage,Integer prodTagsId,
			String orderInfo, String catalogId, String name, String mstat,String timeStatus,
			HttpSession session, Object String) {
		prod.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		String orderBy = "";
		if (null == showCount || showCount == 0) {
			showCount = 10;
		}

		if (orderInfo != "" && orderInfo != null) {
			String[] ary = orderInfo.split(",");
			// 判断字段
			if (ary.length > 0) {
				if (ary[0].equals("sellStatus")) {
					prod.setSellStatus(Integer.parseInt(ary[1]));
				}
				if (ary[0].equals("type")) {
					System.out.println(ary[1] + "this is why");
					prod.setType(Integer.parseInt(ary[1]));
				}
				if (ary[0].equals("istop")) {
					prod.setIstop(Integer.parseInt(ary[1]));

				}
				if (ary[0].equals("sort1")) {
					sort1 = ary[1];
				}
				if (ary[0].equals("timeStatus")) {
					timeStatus = ary[1];
				}
				if (ary[0].equals("sort2")) {
					sort1 = ary[1];
				}
			}
		}

		if (Tools.notEmpty(sort1)) {
			switch (sort1) {// 价格
			case "1":
				orderBy += " tmp.price desc";
				break;
			case "0":
				orderBy += " tmp.price asc";
				break;
			default:

				break;
			}
		}
		if (Tools.notEmpty(sort2)) {
			switch (sort2) {// 销量
			case "1":
				orderBy += " pm.sales_count desc";
				break;
			case "0":
				orderBy += " pm.sales_count asc";
				break;

			default:
				break;
			}
		}
		if (Tools.notEmpty(timeStatus)) {
			switch (timeStatus) {// 价格
			case "1":
				orderBy += " tmp.addtime desc";
				break;
			case "2":
				orderBy += " tmp.addtime asc";
				break;
			default:

				break;
			}
		}
		if (Tools.isEmpty(orderBy)) {
			orderBy += " tmp.addtime desc";
		}
		// 判断一级,二级分类
		if (catalogId != null && catalogId.contains("cf")) {
			prod.setCatalogId1(Integer.parseInt(StringNumberFilter(catalogId)
					.replace(",", "")));
		}
		if (catalogId != null && catalogId.contains("ct")) {
			prod.setCatalogId2(Integer.parseInt(StringNumberFilter(catalogId)
					.replace(",", "")));
		}
		if (prod.getName() != null && prod.getName().trim().equals("")) {
			prod.setName(null);
		}
		if (prod.getSn() != null && prod.getSn().trim().equals("")) {
			prod.setSn(null);
		}
		prod.setOrderBy(orderBy);
		if (name != null && name != "") {
			prod.setName(name);
		}
		if (prodTagsId != null && prodTagsId != -1) {
			prod.setProdTagsId(prodTagsId);
		}else{
			prod.setProdTagsId(null);
		}
		AjaxPage ap = new AjaxPage();
		ap.setShowCount(showCount);
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("getList");
		prod.setAjaxPage(ap);
		List<Map<String, Object>> prodList = productService
				.listAjaxPageProduct(prod);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", prodList);
		System.out.println(prod.getAjaxPage());
		map.put("page", prod.getAjaxPage());
		try {
			responseAjax(map, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从字符中选出数字
	 * 
	 * @param content
	 * @return
	 */
	public static String StringNumberFilter(String content) {
		Pattern p = Pattern.compile("[0-9\\.]+");
		Matcher m = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			sb.append(m.group() + ",");

		}
		return sb.toString();

	}

	/**
	 * 设置推荐产品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param type
	 */
	@RequestMapping("/setIsTop")
	public void setIsTop(HttpServletRequest request,
			HttpServletResponse response, Integer id, Integer istop) {
		System.out.println(id + "type" + istop);
		String result = "{'flag':false}";
		Product prod = new Product();
		prod.setId(id);
		if (null == istop) {
			prod.setIstop(1);
		} else {
			prod.setIstop(istop == 1 ? 0 : 1);
		}

		if (productService.updateByPrimaryKeySelective(prod) > 0) {
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
	 * 
	 * @描述: 商品列表
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/getone")
	public ModelAndView getone(Product user, HttpServletRequest request,
			String lihe, String mstat, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		ProductSpecification ps = new ProductSpecification();// 规格信息
//		ps.setCompanyId(((Company) session
//				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		ProductCatalog pc = new ProductCatalog();
		pc.setPid(0);
		List<ProductCatalog> responseList = productCatalogService
				.getAllByProductCatalog(pc);
		mv.addObject("SClist", responseList);
		mv.addObject("proTags", productTagsService.getAllByProductTags(null));
		mv.addObject("proBasics",
				productBasicService.getAllByProductBasic(null));
		 mv.addObject("psList",
		 productSpecificationService.getAllByProductSpecification(ps));

		if (Tools.notEmpty(lihe)) {// 是礼盒商品
			mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
					+ "/info/productlihe");
		} else {
			mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
					+ "/info/productinfo");
		}
		return mv;
	}

	/**
	 * 
	 * @描述: 商品信息
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/info")
	public ModelAndView productInfo(Product pro, HttpServletRequest request,
			String lihe, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		pro.setDelFlag(0);
//		pro.setCompanyId(((Company) session
//				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());

		ProductSpecification ps = new ProductSpecification();// 规格信息
//		ps.setCompanyId(((Company) session
//				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		List<ProductSpecification> psList = productSpecificationService.getAllByProductSpecification(ps);//获得商城所有的规格
		mv.addObject("psList", psList);//商城所有的规格
		
		Product proInfo = productService.selectByProduct(pro);
		if (null != proInfo && null != proInfo.getPicuri()
				&& !proInfo.getPicuri().equals("")) {
			String pics[] = proInfo.getPicuri().split(",");
			List<String> piclist = new ArrayList<String>();
			for (String string : pics) {
				piclist.add(string);
			}
			mv.addObject("pics", piclist);
		}
		
		if("1".equals(proInfo.getIsSpecification()+"") ){//如果该商品启用规格了，就查询规格
			ProductSpecificationInfo psi = new ProductSpecificationInfo();//该产品的规格
			psi.setProductId(pro.getId());
			psi.setDelFlag("0");//未删除的
			List<ProductSpecificationInfo> proSpecInfoList = productSpecificationInfoService.getAllByProductSpecificationInfo(psi);
			//遍历一下规格lies 
			String prodSpecMainStr = "";
			String prodSpecInfoDetailStr = "";
			Set<String> prodSpecMainSet = new HashSet<String>();
			if(null != proSpecInfoList && proSpecInfoList.size()>0){
				for (ProductSpecificationInfo psit : proSpecInfoList) {
					List<ProductSpecificationInfoDetail> prodSpecInfoDetalList_t = psit.getSpecInfoDetailList();
					for (ProductSpecificationInfoDetail productSpecificationInfoDetail : prodSpecInfoDetalList_t) {
						if(!prodSpecMainSet.contains(productSpecificationInfoDetail.getSpecificatonId()+"")){
							prodSpecMainStr += productSpecificationInfoDetail.getSpecificatonId()+",";
						}
						prodSpecInfoDetailStr += productSpecificationInfoDetail.getSpecificationInfoId()+"_"+productSpecificationInfoDetail.getSpecificatonId()+"_"+productSpecificationInfoDetail.getSpecificationDetailId()+",";
					}
				}
			}
			mv.addObject("proSpecInfoList", proSpecInfoList);
			mv.addObject("prodSpecMainStr", prodSpecMainStr);//该产品的主规格
			mv.addObject("prodSpecInfoDetailStr", prodSpecInfoDetailStr);
		}
		
		ProductCatalog pc = new ProductCatalog();
		pc.setPid(0);
		List<ProductCatalog> responseList = productCatalogService
				.getAllByProductCatalog(pc);
		mv.addObject("SClist", responseList);
		mv.addObject("prodctInfo", proInfo);
		mv.addObject("prodImgs", JsonLibUtils.listToStringWithConfig(
				productImageService.getAllByProductImage(proInfo.getId()),
				new String[]{"uri","id"}));
		mv.addObject("proTags", productTagsService.getAllByProductTags(null));
		

		if (Tools.notEmpty(lihe)) {//是礼盒商品
			ProductPackage pp = new ProductPackage();
			pp.setProdId(proInfo.getId());
			mv.addObject("productPackage",productPackageService.getAllByProductPackageDetail(pp));
			mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
					+ "/info/productlihe");
		} else {
			mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
					+ "/info/productinfo");
		}
		return mv;
	}

	/**
	 * 
	 * @描述:保存商品
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/saveinfo")
	public void saveInfo(Product pro, HttpServletRequest request,
			HttpServletResponse response, String[] prodspec_ck,
			String[] values, Integer[] inventorynumbers,
			BigDecimal[] marketPrices, Integer[] skus, BigDecimal[] prices,
			String proBasicCount,String[] prod_sprc_ids, HttpSession session) {
		ResultModel rm = null;
		if (SystemUtil.isEmpty(pro.getName())
				|| SystemUtil.isEmpty(pro.getUnit())
				|| SystemUtil.isEmpty(pro.getPicuri())
				|| SystemUtil.isEmpty(pro.getSellStatus().toString())) {
			rm = new ResultModel("友情提示", "存在非必填项为空", "error", "");
		} else {
			//对动态数据进行处理 begin
			Map specInfoDetalMap = new HashMap();
			if(null != prodspec_ck && prodspec_ck.length >0){
				for (String str : prodspec_ck) {
					String[] specInfoD_t = request.getParameterValues("specDetalInpt_"+str);
					specInfoDetalMap.put("specDetalInpt_"+str, specInfoD_t);
				}
			}
			//对动态数据进行处理 end
			
			if (null != pro.getId()) {// 更新
				pro.setCompanyId(((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId());
				pro.setDelFlag(0);
				int i = productService.updateByPrimaryKeySelective(pro, values,
						inventorynumbers, marketPrices, prices, skus,
						prodspec_ck, proBasicCount,specInfoDetalMap,prod_sprc_ids);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}
			} else {// 插入
				pro.setDelFlag(0);
				pro.setSn(GenerateNo.ProductNo());
				pro.setAddtime(new Date());
				pro.setCompanyId(((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId());
				// 添加
				int i = productService.insertSelective(pro, values,
						inventorynumbers, marketPrices, prices, skus,
						prodspec_ck, proBasicCount,specInfoDetalMap,prod_sprc_ids);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}
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
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);

		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			Product p = new Product();
			p.setId(Integer.parseInt(string));
			if (productService.deleteByPrimaryKey(p) > 0) {
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
	/**
	 * 
	 */
	@RequestMapping(value = "/getSpecProdByCaId")
	public void getSpecProdByCaId(HttpServletRequest request, HttpServletResponse res,
			ProductSpecificationInfo psi,Integer currentPage,Integer showCount, HttpSession session) {
		
		AjaxPage ap = new AjaxPage();
		ap.setShowCount(showCount);
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("getSpecProdByCaId");
		
		psi.setAjaxPage(ap);
		List<ProductSpecificationInfo> prodSpecInfoList = productSpecificationInfoService.listAjaxPageGetSpecProdByCaId(psi);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", prodSpecInfoList);
		map.put("page", psi.getAjaxPage());
		try {
			responseAjax(map, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}