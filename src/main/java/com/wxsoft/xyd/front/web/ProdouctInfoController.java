package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserCollection;
import com.wxsoft.xyd.common.model.UserComment;
import com.wxsoft.xyd.common.service.CompanyStockService;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.common.service.PromotionProductService;
import com.wxsoft.xyd.common.service.RollPicService;
import com.wxsoft.xyd.common.service.UserCollectionService;
import com.wxsoft.xyd.common.service.UserCommentService;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.model.ProductPackage;
import com.wxsoft.xyd.prod.model.ProductSpecification;
import com.wxsoft.xyd.prod.model.ProductSpecificationDetail;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductImageService;
import com.wxsoft.xyd.prod.service.ProductPackageService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoService;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;
import com.wxsoft.xyd.prod.service.ProductSpecificationStockService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.service.CompanyService;

/**
 * 
 * Title: 主页接口 Description: Company:
 * 
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES
		+ "/productInfo")
public class ProdouctInfoController extends BaseController {
	@Autowired
	private RollPicService rollPicService;
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private ProductSpecificationService productSpecificationService;
	@Autowired
	private ProductPackageService productPackageService;
	@Autowired
	private ProductSpecificationInfoService productSpecificationInfoService;
	@Autowired
	private ProductSpecificationStockService productSpecificationStockService;
	@Autowired
	private CompanyStockService companyStockService;
	@Autowired
	private UserCollectionService userCollectionService;
	@Autowired
	private PromotionProductService promotionProductService;
	@Autowired
	private UserCommentService userCommentService;

	/**
	 * 获取商品信息
	 * 
	 * @return
	 */
	@RequestMapping("/getProductInfo")
	public void getProductInfo(HttpServletResponse response, Integer prodId, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 获取商品信息
		Product p = new Product();
		p.setId(prodId);
		Map<String, Object> rplist = productService.selectFrontByProductInfo(p);
		if (null != rplist) {// 判断当前商品是否存在
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put("prod", rplist);
			List<String> prodImgList = productImageService
					.getAllByProductImageRMap(prodId);
			json.put("prodImgList", prodImgList);

			// 获取基地的基本信息
			Company c = companyService.getCompanyWxByCompanyId((int)rplist.get("company_id"));
			json.put("sendPrice", c.getSendPrice());
			json.put("chargeSendPrice", c.getChargeSendPrice());

		} else {
			//商品不存在
			json.put(BaseConfig.RESCODE, "ER5001");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("productInfo.ER5001").toString());
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商品信息
	 * 
	 * @return
	 */
	@RequestMapping("/getProductInfoPc")
	public void getProductInfoPc(HttpServletResponse response, Integer prodId, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 获取商品信息
		Product p = new Product();
		p.setId(prodId);
		Map<String, Object> rplist = productService.selectFrontByProductInfo(p);
		if (null != rplist) {// 判断当前商品是否存在
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put("prod", rplist);
			UserComment uc = new UserComment();
			uc.setProdId(prodId);
			uc.setvState(1);
			int countAll = userCommentService
					.selectCountLevelFromUserComment(uc);
			json.put("commentCount", countAll);
			List<String> prodImgList = productImageService
					.getAllByProductImageRMap(prodId);
			json.put("prodImgList", prodImgList);
			// 获取一级分类信息
			ProductCatalog pc1 = productCatalogService
					.selectByPrimaryKey(Integer.parseInt(rplist.get(
							"catalog_id_1").toString()));
			// 获取二级分类信息
			ProductCatalog pc2 = productCatalogService
					.selectByPrimaryKey(Integer.parseInt(rplist.get(
							"catalog_id_2").toString()));
			json.put("pc1id", pc1.getId());
			json.put("pc1name", pc1.getName());
			json.put("pc2id", pc2.getId());
			json.put("pc2name", pc2.getName());

			// 获取基地的基本信息
			Company c = companyService.getCompanyWxByCompanyId( (int)rplist.get("company_id") );
			json.put("sendPrice", c.getSendPrice());
			json.put("chargeSendPrice", c.getChargeSendPrice());
		} else {
			//商品不存在
			json.put(BaseConfig.RESCODE, "ER5001");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("productInfo.ER5001").toString());
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商品規格信息
	 * 
	 * @return
	 */
	@RequestMapping("/getProductSepcInfo")
	public void getProductSepcInfo(HttpServletResponse response,
			 Integer prodId, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		int maxInventory = 0;
		// 获取商品信息
		Product p = new Product();
		p.setId(prodId);
		Map<String, Object> rplist = productService.selectFrontByProduct(p);
		if (null != rplist) {// 判断当前商品是否存在
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");

			Map<String, Object> SKU = new HashMap<>();
			Map<String, Object> SKUAll = new HashMap<>();

			Map<String, Object> Discount = new HashMap<>();
			List<Object> DisInfoList = new ArrayList<>();

			Map<String, Object> lstSKUVal = new HashMap<>();
			List<Object> lstSKUValList = new ArrayList<>();
			Map<String, Object> lstProductJson = new HashMap<>();
			List<Object> lstProductJsonList = new ArrayList<>();
			CompanyStock csTemp = null;
			
			if (rplist.get("type").toString().equals("0")) {
				// 普通商品
				List<ProductSpecification> prodSpecList = new ArrayList<>();//该商品的主规格
				List<ProductSpecificationDetail> prodSpecDetailList = new ArrayList<>();//该商品的详细规格
				List<Object> prodSpecInfoList = new ArrayList<>();//该规格的具体值包括价格、库存等
				
				Set<String> mainSpecSet = new HashSet<>();
				Set<String> detalSpecSet = new HashSet<>();
				// 规格信息
				ProductSpecificationInfo psi = new ProductSpecificationInfo();
				psi.setProductId(prodId);
				psi.setDelFlag("0");//未删除的
				List<ProductSpecificationInfo> psiList = productSpecificationInfoService.getAllByPSIForWeb(psi);
				for (ProductSpecificationInfo productSpecificationInfo : psiList) {
					//贵规格里面具体的值进行处理
					if(null != productSpecificationInfo.getSpecInfoDetailList() && productSpecificationInfo.getSpecInfoDetailList().size()>0){
						String id_val = "";//组装的代表该规格唯一标识
						String specname_val = "";
						for (ProductSpecificationInfoDetail psid : productSpecificationInfo.getSpecInfoDetailList()) {
							if(!mainSpecSet.contains(String.valueOf(psid.getSpecificatonId()))){
								mainSpecSet.add(String.valueOf(psid.getSpecificatonId()));
								ProductSpecification ps_t = new ProductSpecification();
								ps_t.setId(psid.getSpecificatonId());
								ps_t.setSpecificationName(psid.getSpecificationName());
								prodSpecList.add(ps_t);
							}
							
							if(!detalSpecSet.contains(String.valueOf(psid.getSpecificationDetailId()))){
								detalSpecSet.add(String.valueOf(psid.getSpecificationDetailId()));
								ProductSpecificationDetail psd_t = new ProductSpecificationDetail();
								psd_t.setId(psid.getSpecificationDetailId());
								psd_t.setProdSpecId(psid.getSpecificatonId());
								psd_t.setDetailName(psid.getSpecificationDetailName());
								prodSpecDetailList.add(psd_t);
							}
							id_val += psid.getSpecificatonId()+"_"+psid.getSpecificationDetailId()+";";
							specname_val += psid.getSpecificationDetailName()+"、";
						}
						
						Map<String, Object> id_val_map = new HashMap<>();
						id_val_map.put("id_val", id_val);
						id_val_map.put("specname_val",specname_val.length()>0 ? specname_val.substring(0, (specname_val.length()-1)) : specname_val);
						id_val_map.put("specInfoId", productSpecificationInfo.getId());
						id_val_map.put("price", productSpecificationInfo.getPrice());
						id_val_map.put("marketPrice", productSpecificationInfo.getMarketPrice());
						id_val_map.put("inventorycount", productSpecificationInfo.getInventorynumber());

						/*//获取该基地 该产品 该规格的库存
						ProductSpecificationStock pss_t = new ProductSpecificationStock();
						pss_t.setCompanyId(companyid);
						pss_t.setProductId(prodId);
						pss_t.setSpecificationInfoId(productSpecificationInfo.getId());
						pss_t = productSpecificationStockService.selectByProductSpecificationStock(pss_t);
						if(null == pss_t || pss_t.getInventorycount() == null){
							id_val_map.put("inventorycount", 0);
						}else{
							id_val_map.put("inventorycount", pss_t.getInventorycount());
						}*/
						prodSpecInfoList.add(id_val_map);
					}
				}
				//获取该基地里面该产品的总库存  获取该商品的总库存
				/*ProductSpecificationStock prodTotalStock = new ProductSpecificationStock();
				prodTotalStock.setCompanyId(companyid);
				prodTotalStock.setProductId(prodId);
				prodTotalStock =  productSpecificationStockService.selectProdTotalStock(prodTotalStock);
				if(null != prodTotalStock && prodTotalStock.getInventorycount() != null){
					maxInventory = prodTotalStock.getInventorycount();
				}*/
				if ( "1".equals( String.valueOf(rplist.get("is_specification")) ) ) {
					//开启规格
					maxInventory = productSpecificationInfoService.selectTotalInvcountByProid(prodId);
				} else {
					//没有开启规格
					maxInventory = (int)rplist.get("inventorynumber");
				}

				json.put("prodSpecList", prodSpecList);
				json.put("prodSpecDetailList", prodSpecDetailList);
				json.put("prodSpecInfoList", prodSpecInfoList);
				
			} else {
				// 礼盒商品
				ProductPackage pp = new ProductPackage();
				pp.setProdId(prodId);
				List<ProductPackage> pplist = productPackageService
						.getAllByProductPackage(pp);
				//跟里面包含的产品的库存来判断该礼盒是否有库存,如果有库存找到最小的库存量作为最大购买量
				boolean flag = true;//商品包里面第一个产品
				for (ProductPackage productPackage : pplist) {
					ProductSpecificationStock pss_t = new ProductSpecificationStock();
//					pss_t.setCompanyId(companyid);
					pss_t.setSpecificationInfoId(productPackage.getSpecificationInfoId());
					pss_t.setProductId(productPackage.getProductId());
					pss_t.setType(productPackage.getType());
					ProductSpecificationStock resultPss = productSpecificationStockService.selectByProductSpecificationStock(pss_t);
					if(resultPss == null || resultPss.getInventorycount() <= 0){
						maxInventory = 0;
						break;
					}else{
						if(flag){
							flag = false;
							maxInventory = resultPss.getInventorycount();
						}else{
							if(resultPss.getInventorycount() < maxInventory){
								maxInventory = resultPss.getInventorycount();
							}
						}
					}
				}
			}

			// 促销信息
			List<PromotionActivity> paList = promotionProductService
					.getAllByPromotionProductVsInTime(prodId);// 获取该商品参加的所有促销活动
			int tempPromotionCount = 0;
			PromotionActivity pa = new PromotionActivity();
			pa.setAllProduct(1);
			List<PromotionActivity> PaList2 = promotionActivityService
					.getAllByPromotionActivityNormal(pa);// 判断全场参加的商品
			paList.addAll(PaList2);
			for (PromotionActivity paTemp1 : paList) {
				Map<String, Object> DisInfo = new HashMap<String, Object>();
				DisInfo.put("Title", paTemp1.getName());
				DisInfo.put("TimeStart",
						Tools.date2Str(paTemp1.getStartTime(), "yyyy年MM月dd日"));
				DisInfo.put("TimeEnd",
						Tools.date2Str(paTemp1.getEndTime(), "yyyy年MM月dd日"));

				Map<String, Object> Activity = new HashMap<String, Object>();
				List<Object> ActivityList = new ArrayList<Object>();
				if (paTemp1.getType() == 1) {// 0满减 1满折
					Activity.put("Activity", "满" + paTemp1.getNeedPrice()
							+ "元 打" + paTemp1.getDiscount() + "折");
				} else {
					Activity.put("Activity", "满" + paTemp1.getNeedPrice()
							+ "元 减" + paTemp1.getSubstractPrice() + "元");
				}
				ActivityList.add(Activity);
				DisInfo.put("Description", ActivityList);
				DisInfoList.add(DisInfo);
				tempPromotionCount++;
			}
			Discount.put("DisInfo", DisInfoList);
			Discount.put("total", tempPromotionCount);

			SKU.put("lstSKUVal", lstSKUValList);
			SKU.put("lstProductJson", lstProductJsonList);
			SKUAll.put("SKU", SKU);
			SKUAll.put("Label", "");
			SKUAll.put("Discount", Discount);

			json.put("maxInventory", maxInventory);
			json.put("prodType", rplist.get("type"));
			json.put("isSpecification", rplist.get("is_specification"));

			json.put("Data", SKUAll);

		} else {
			//商品不存在
			json.put(BaseConfig.RESCODE, "ER5001");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("productInfo.ER5001").toString());
		}

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商品促销信息
	 * 
	 * @return
	 */
	@RequestMapping("/getProductPromotionInfo")
	public void getProductPromotionInfo(HttpServletResponse response,
			Integer prodId, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 获取商品信息
		Product p = new Product();
		Map<String, Object> rplist = productService.selectFrontByProduct(p);

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
	 * 点击收藏商品
	 * 
	 * @return
	 */
	@RequestMapping("/changeProductCollectionState")
	public void changeProductCollectionState(HttpServletResponse response,
			Integer prodId, boolean type, HttpServletRequest request,
			HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		if (null != su) {
			// 获取商品信息
			UserCollection u = new UserCollection();
			u.setProdId(prodId);
			u.setUserId(su.getId());
			UserCollection uTemp = userCollectionService
					.selectByUserCollection(u);
			if (null != uTemp) {// 已收藏
				if (!type) {
					userCollectionService.deleteByPrimaryKey(u.getId());
					json.put(BaseConfig.RESCODE, "0");
					json.put(BaseConfig.RESMESSAGE, "取消收藏成功");
				} else {
					json.put(BaseConfig.RESCODE, "0");
					json.put(BaseConfig.RESMESSAGE, "添加收藏成功");
				}
			} else {
				if (type) {
					Product p = productService.selectProductByCart(prodId);
					if (null != p) {
						u.setAddTime(new Date());
						userCollectionService.insertSelective(u);
						json.put(BaseConfig.RESCODE, "0");
						json.put(BaseConfig.RESMESSAGE, "添加收藏成功");
					} else {
						json.put(BaseConfig.RESCODE, "ER5001");
						json.put(BaseConfig.RESMESSAGE,
								BaseConfig.MESSAGE.get("productInfo.ER5001")
										.toString());
					}

				} else {
					userCollectionService.deleteByPrimaryKey(u.getId());
					json.put(BaseConfig.RESCODE, "0");
					json.put(BaseConfig.RESMESSAGE, "取消收藏成功");
				}
			}
		} else {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("user.ER1025").toString());
		}

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断用户是否收藏商品
	 * 
	 * @return
	 */
	@RequestMapping("/getProductCollectionState")
	public void getProductCollectionState(HttpServletResponse response,
			Integer prodId, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
		UserCollection uTemp = null;
		if (null != su) {
			// 获取商品信息
			UserCollection u = new UserCollection();
			u.setProdId(prodId);
			u.setUserId(su.getId());
			uTemp = userCollectionService.selectByUserCollection(u);
		}
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "");
		json.put(BaseConfig.RESTYPE, null != uTemp ? "1" : "0");
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取评论信息
	 * 
	 * @return
	 */
	@RequestMapping("/getComment")
	public void getComment(HttpServletResponse response, Integer prodId,
			Integer pageIndex, HttpServletRequest request, HttpSession session,
			Integer type, String from) {
		JSONObject json = new JSONObject();
		// 获取商品信息
		Product p = new Product();
		p.setId(prodId);
		// Map<String, Object> rplist = productService.selectFrontByProduct(p);
		UserComment uc = new UserComment();
		uc.setProdId(prodId);
		uc.setvState(1);
		int countAll = 0;
		if (Tools.isEmpty(from)) {
			countAll = userCommentService.selectCountLevelFromUserComment(uc);
		}

		uc.setCommentLevel(1);
		int countA = userCommentService.selectCountLevelFromUserComment(uc);
		uc.setCommentLevel(2);
		int countB = userCommentService.selectCountLevelFromUserComment(uc);
		uc.setCommentLevel(3);
		int countC = userCommentService.selectCountLevelFromUserComment(uc);

		UserComment ucList = new UserComment();

		AjaxPage ap = new AjaxPage();
		ap.setCurrentPage(pageIndex);
		ap.setFunctionName("getCommentList");
		ap.setShowCount(10);
		ucList.setAjaxPage(ap);
		if (type != 0) {
			ucList.setCommentLevel(type);
		}
		ucList.setProdId(prodId);
		List<Map<String, Object>> Data = userCommentService
				.listAjaxPageByUserCommentFrontShow(ucList);

		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put("Data", Data);
		json.put("pageStr", ucList.getAjaxPage().getPageStr());
		json.put("countAll", countAll);
		json.put("countA", countA);
		json.put("countB", countB);
		json.put("countC", countC);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
