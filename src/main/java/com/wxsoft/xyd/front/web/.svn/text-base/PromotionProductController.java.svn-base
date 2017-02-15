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

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.service.AdDetailService;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * Title:促销活动
 * 
 * @author kyz
 * @date 2015年12月17日下午8:52:23
 */
@Controller
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_INTERFACES + "/active")
public class PromotionProductController extends BaseController {
	@Autowired
	private ProductService productService;
	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private AdDetailService adDetailService;

	/**
	 * 获取所有促销商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/activelist")
	public void activelist(HttpServletResponse response, Integer type,Integer currentPage,Integer zu,
			Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		Product pd = new Product();
		pd.setId(id);
		PromotionActivity pjd = promotionActivityService.selectByPrimaryKey(id);
		if (pjd == null) {
			//该活动已经取消！
			json.put(BaseConfig.RESCODE, "3");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("promotion.ER9004").toString());
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 促销活动
			PromotionActivity pa = new PromotionActivity();
			pa = promotionActivityService.selectByPrimaryKey(id);
			// 判断该促销活动是否正在进行
			if (pa == null) {
				//该活动已经取消！
				json.put(BaseConfig.RESCODE, "3");
				json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("promotion.ER9004").toString());
				try {
					responseAjax(json, response);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Date d = new Date();
				Date Start = pa.getStartTime();
				Date End = pa.getEndTime();
				if (Start.getTime() > d.getTime()) {
					json.put(BaseConfig.RESCODE, "3");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					json.put(BaseConfig.RESMESSAGE, "该活动还为开始，敬请期待！开始时间：("
							+ format.format(Start) + "~" + format.format(End)
							+ ")");
					try {
						responseAjax(json, response);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (End.getTime() < d.getTime()) {
					//该活动已经结束
					json.put(BaseConfig.RESCODE, "3");
					json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("promotion.ER9004").toString());
					try {
						responseAjax(json, response);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					// 获得所有商品列表
					if (0 == pjd.getAllProduct()) {
						//得到活动的对应广告
						String adDetailIds =pjd.getAdDetailIds();
						String[]  SadDetailIds= adDetailIds.split(",");
						List<AdDetail> adDetailList=adDetailService.getStockByDetailId(SadDetailIds);
						//得到参加活动商品
						AjaxPage ap = new AjaxPage();
						ap.setCurrentPage(currentPage);
						ap.setFunctionName("mycouponsCan");
						if(null==zu){
							ap.setShowCount(10);
							}else{
							ap.setShowCount(zu);
							}
						pd.setAjaxPage(ap);
						List<Map<String, Object>> pdlist = productService
								.listAjaxPageProductPromotion(pd);
						
						json.put("adDetailList",adDetailList);
						json.put("nam",currentPage);
						json.put("pageCount", pd.getAjaxPage().getTotalPage());
						json.put(BaseConfig.RESCODE, "0");
						json.put(BaseConfig.RESMESSAGE, "success");
						json.put(BaseConfig.RESLIST, pdlist);
						try {
							responseAjax(json, response);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						//得到活动的对应广告
						String adDetailIds =pjd.getAdDetailIds();
						String[]  SadDetailIds= adDetailIds.split(",");
						List<AdDetail> adDetailList=adDetailService.getStockByDetailId(SadDetailIds);
						
						//得到参加活动商品
						AjaxPage ap = new AjaxPage();
						ap.setCurrentPage(currentPage);
						ap.setFunctionName("mycouponsCan");
						if(null==zu){
							ap.setShowCount(10);
							}else{
							ap.setShowCount(zu);
							}
						pd.setAjaxPage(ap);
						List<Map<String, Object>> pdlist = productService
								.listAjaxPageAllProductPromotion(pd);
						
						json.put("adDetailList",adDetailList);
						json.put("pageCount", pd.getAjaxPage().getTotalPage());
						json.put("nam",currentPage);
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
			}
		}
	}

	/**
	 * 获取所有促销活动的推广图片
	 * 
	 * @return
	 */
	@RequestMapping("/promotionInfo")
	public void promotionInfo(HttpServletResponse response, Integer type,
			Integer id, HttpServletRequest request, HttpSession session) {
		JSONObject json = new JSONObject();
		// 促销活动
		PromotionActivity pa = new PromotionActivity();
		pa.setId(id);
		// 获得所有促销商品列表
		PromotionActivity pd = promotionActivityService.selectByPrimaryKey(id);
		json.put(BaseConfig.RESCODE, "0");
		json.put(BaseConfig.RESMESSAGE, "success");
		json.put(BaseConfig.RESDATA, pd);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
