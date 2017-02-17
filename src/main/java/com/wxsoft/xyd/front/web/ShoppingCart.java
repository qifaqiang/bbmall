package com.wxsoft.xyd.front.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserCart;
import com.wxsoft.xyd.common.model.UserLocation;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.common.service.PromotionProductService;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.common.service.UserCartService;
import com.wxsoft.xyd.common.service.UserLocationService;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.SysProportion;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.SysProportionService;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 购物车处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(SystemConfig.SYSTEM_PATH_FRONT_WAP + "/shopCart")
public class ShoppingCart extends BaseController {
	@Autowired
	private UserCartService userCartService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserLocationService userLocationService;
	@Autowired
	private SysCouponsRecordService sysCouponsRecordService;
	@Autowired
	private PromotionActivityService promotionActivityService;
	@Autowired
	private PromotionProductService promotionProductService;
	@Autowired
	private SysProportionService sysProportionService;

	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param session
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addCart")
	public void addCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Integer prodId,
			Integer specId, Integer count)
			throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		if (null == count || count < 1) {
			//添加购物车失败：库存数量不足
			json.put(BaseConfig.RESCODE, "ER6101");
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("cart.ER6101").toString());
		}
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户
		if (user == null) {// 用户未登录
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		} else {// 用户已经登录
			Product p = productService.selectProductByCart(prodId);
			if (null != p) {
				if (p.getType() == 1) {
					// 礼盒
//					UserCart userCart = new UserCart();
//					userCart.setUserId(user.getId());
//					userCart.setProdId(prodId);
//					userCart.setSpecId(null);
//					UserCart userCartInfo = userCartService
//							.selectByUserCart(userCart);// 登录用户的购物车和该商品的id
//
//					if (null != userCartInfo) {// 购物车中存在当前的商品
//						Map<String, Integer> mapTemp = productService
//								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
//										prodId + "", companyid,
//										(userCartInfo.getCount() + count) + "",
//										"");
//						if (null != mapTemp && mapTemp.size() > 0) {
//							//购物车添加失败
//							json.put(BaseConfig.RESCODE, "ER6001");
//							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
//									.get("cart.ER6001").toString());
//						} else {
//							userCartInfo.setCount(userCartInfo.getCount()
//									+ count);
//							if (userCartService
//									.updateByPrimaryKeySelective(userCartInfo) > 0) {
//								json.put(BaseConfig.RESCODE, "0");
//								json.put(BaseConfig.RESMESSAGE, "success");
//								responseAjax(json, response);
//								return;
//							}
//						}
//					} else {// 购物车中没有该商品
//						userCart.setCount(count);
//						userCart.setAddTime(new Date());
//						if (userCartService.insertSelective(userCart) > 0) {
//							json.put(BaseConfig.RESCODE, "0");
//							json.put(BaseConfig.RESMESSAGE, "success");
//							responseAjax(json, response);
//							return;
//						}
//					}

				} else {
					// 普通商品
					specId = null == specId ? 0 : specId;
					UserCart userCart = new UserCart();
					userCart.setUserId(user.getId());
					userCart.setProdId(prodId);
					userCart.setSpecId(null == specId ? 0 : specId);
					UserCart userCartInfo = userCartService
							.selectByUserCart(userCart);// 登录用户的购物车和该商品的id
														// 或者规格
					if (null != userCartInfo) {
						// 购物车中存在当前规格的商品
						Map<String, Integer> mapTemp = productService
								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
										prodId + "",
										(userCartInfo.getCount() + count) + "",
										specId + "");
						if (null != mapTemp && mapTemp.size() > 0) {
							//购物车添加失败
							json.put(BaseConfig.RESCODE, "ER6001");
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("cart.ER6001").toString());
						} else {
							userCartInfo.setCount(userCartInfo.getCount()
									+ count);
							if (userCartService
									.updateByPrimaryKeySelective(userCartInfo) > 0) {
								json.put(BaseConfig.RESCODE, "0");
								json.put(BaseConfig.RESMESSAGE, "添加购物车成功");
								responseAjax(json, response);
								return;
							} else {
								//购物车添加失败
								json.put(BaseConfig.RESCODE, "ER6001");
								json.put(BaseConfig.RESMESSAGE,
										BaseConfig.MESSAGE.get("cart.ER6001")
												.toString());
								responseAjax(json, response);
								return;
							}
						}
					} else {
						// 购物车中没有该商品
						Map<String, Integer> mapTemp = productService
								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
										prodId + "",  (count) + "",
										specId + "");
						if (null != mapTemp && mapTemp.size() > 0) {
							//购物车添加失败
							json.put(BaseConfig.RESCODE, "ER6001");
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("cart.ER6001").toString());
						} else {
							userCart.setCount(count);
							userCart.setAddTime(new Date());
							if (userCartService.insertSelective(userCart) > 0) {
								json.put(BaseConfig.RESCODE, "0");
								json.put(BaseConfig.RESMESSAGE, "添加购物车成功");
								responseAjax(json, response);
								return;
							} else {
								//购物车添加失败
								json.put(BaseConfig.RESCODE, "ER6001");
								json.put(BaseConfig.RESMESSAGE,
										BaseConfig.MESSAGE.get("cart.ER6001")
												.toString());
								responseAjax(json, response);
								return;
							}
						}
					}

				}

			} else {
				//不存在该商品
				json.put(BaseConfig.RESCODE, "ER5001");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("productInfo.ER5001").toString());
			}
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param session
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addCarts")
	public void addCarts(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Integer prodId,
			Integer specId,  Integer count)
			throws UnsupportedEncodingException {
		JSONObject json = new JSONObject();
		// 根据商品id查询商品的名称
		Product pro = productService.selectByPrimaryKey(prodId);
		String proName = pro.getName();
		if (null == count || count < 1) {
			//添加购物车失败：库存数量不足
			json.put(BaseConfig.RESCODE, "ER6101");
			json.put("resName", proName);
			json.put(BaseConfig.RESMESSAGE,
					BaseConfig.MESSAGE.get("cart.ER6101").toString());
		}
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户
		if (user == null) {// 用户未登录
			json.put(BaseConfig.RESCODE, "1");
		} else {// 用户已经登录
			Product p = productService.selectProductByCart(prodId);
			if (null != p) {
				if (p.getType() == 1) {// 礼盒
					UserCart userCart = new UserCart();
					userCart.setUserId(user.getId());
					userCart.setProdId(prodId);
					userCart.setSpecId(null);
					UserCart userCartInfo = userCartService
							.selectByUserCart(userCart);// 登录用户的购物车和该商品的id

					if (null != userCartInfo) {// 购物车中存在当前的商品
						Map<String, Integer> mapTemp = productService
								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
										prodId + "",
										(userCartInfo.getCount() + count) + "",
										"");
						if (null != mapTemp && mapTemp.size() > 0) {
							//添加购物车失败
							json.put(BaseConfig.RESCODE, "ER6001");
							json.put("resName", proName);
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("cart.ER6001").toString());
						} else {
							userCartInfo.setCount(userCartInfo.getCount()
									+ count);
							if (userCartService
									.updateByPrimaryKeySelective(userCartInfo) > 0) {
								json.put(BaseConfig.RESCODE, "0");
								json.put(BaseConfig.RESMESSAGE, "success");
								json.put("resName", proName);
								responseAjax(json, response);
								return;
							}
						}
					} else {// 购物车中没有该商品
						userCart.setCount(count);
						userCart.setAddTime(new Date());
						if (userCartService.insertSelective(userCart) > 0) {
							json.put(BaseConfig.RESCODE, "0");
							json.put(BaseConfig.RESMESSAGE, "success");
							json.put("resName", proName);
							responseAjax(json, response);
							return;
						}
					}

				} else {// 普通商品
					specId = null == specId ? 0 : specId;
					UserCart userCart = new UserCart();
					userCart.setUserId(user.getId());
					userCart.setProdId(prodId);
					userCart.setSpecId(null == specId ? 0 : specId);
					UserCart userCartInfo = userCartService
							.selectByUserCart(userCart);// 登录用户的购物车和该商品的id
														// 或者规格
					if (null != userCartInfo) {
						// 购物车中存在当前规格的商品
						Map<String, Integer> mapTemp = productService
								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
										prodId + "",
										(userCartInfo.getCount() + count) + "",
										specId + "");
						if (null != mapTemp && mapTemp.size() > 0) {
							//添加购物车失败
							json.put(BaseConfig.RESCODE, "ER6001");
							json.put("resName", proName);
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("cart.ER6001").toString());
						} else {
							userCartInfo.setCount(userCartInfo.getCount()
									+ count);
							if (userCartService
									.updateByPrimaryKeySelective(userCartInfo) > 0) {
								json.put(BaseConfig.RESCODE, "0");
								json.put(BaseConfig.RESMESSAGE, "添加购物车成功");
								json.put("resName", proName);
								responseAjax(json, response);
								return;
							} else {
								//添加购物车失败
								json.put(BaseConfig.RESCODE, "ER6001");
								json.put(BaseConfig.RESMESSAGE,
										BaseConfig.MESSAGE.get("cart.ER6001")
												.toString());
								responseAjax(json, response);
								return;
							}
						}
					} else {
						// 购物车中没有该商品
						Map<String, Integer> mapTemp = productService
								.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
										prodId + "", (count) + "",
										specId + "");
						if (null != mapTemp && mapTemp.size() > 0) {
							//添加购物车失败
							json.put(BaseConfig.RESCODE, "ER6001");
							json.put("resName", proName);
							json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE
									.get("cart.ER6001").toString());
						} else {
							userCart.setCount(count);
							userCart.setAddTime(new Date());
							if (userCartService.insertSelective(userCart) > 0) {
								json.put(BaseConfig.RESCODE, "0");
								json.put(BaseConfig.RESMESSAGE, "添加购物车成功");
								json.put("resName", proName);
								responseAjax(json, response);
								return;
							} else {
								//添加购物车失败
								json.put(BaseConfig.RESCODE, "ER6001");
								json.put("resName", proName);
								json.put(BaseConfig.RESMESSAGE,
										BaseConfig.MESSAGE.get("cart.ER6001")
												.toString());
								responseAjax(json, response);
								return;
							}
						}
					}

				}

			} else {
				//商品不存在
				json.put(BaseConfig.RESCODE, "ER5001");
				json.put(BaseConfig.RESMESSAGE,
						BaseConfig.MESSAGE.get("productInfo.ER5001").toString());
			}
			// json.put(BaseConfig.RESCODE, "ER9999");
			// json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("ER9999")
			// .toString());
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前用户的购物车数量
	 * 
	 * @param request
	 * @param session
	 */
	@RequestMapping("/getCountByUserId")
	public void getCountByUserId(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户

		if (user == null) {// 用户未登录
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		} else {// 用户已经登录
			int count = userCartService.selectCountByUser(user.getId());// 登录用户的购物车
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESCOUNT, count);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户的购物车
	 * 
	 * @param request
	 * @param session
	 */
	@RequestMapping("/userCart")
	public void userCart(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户
//		if (companyId != null && companyId != "") {
//			Company company = companyService.getCompanyById(Integer
//					.parseInt(companyId));
//			json.put("Company", company);
//		}

		if (user == null) {// 用户未登录
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());

		} else {// 用户已经登录
			UserCart userCart = new UserCart();
			userCart.setUserId(user.getId());
			List<Map<String, Object>> userCartList = userCartService
					.getUserCart(userCart);// 登录用户的购物车
			json.put(BaseConfig.RESCODE, "0");
			json.put(BaseConfig.RESMESSAGE, "success");
			json.put(BaseConfig.RESLIST, userCartList);

		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新购物车数量
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param userCart
	 * @param prodId
	 */
	@RequestMapping("/updateQuantity")
	public void updateQuantity(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			UserCart userCart, Integer prodId) {
		JSONObject json = new JSONObject();
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);
		int result = 0;
		result = userCartService.updateByPrimaryKeySelective(userCart);
		if (result > 0) {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
			userCart = userCartService.selectByPrimaryKey(userCart.getId());
			json.put("quantity", userCart.getCount());
			Product product = productService.selectByPrimaryKey(prodId);
			BigDecimal v2 = new BigDecimal(Double.toString(userCart.getCount()));
			double subtotal = product.getPrice().multiply(v2).doubleValue();
			json.put("subtotal", subtotal);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/ifAboveStock")
	public void ifAboveStock(HttpServletResponse response, String prodIds,
							 String counts, String specIds) {
		JSONObject json = new JSONObject();
		Map<String, Integer> map = productService.getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(prodIds, counts, specIds);
		json.put("map", map);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 显示购物车中所选条目
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping("/showCartItem")
	public void showCartItem(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);
		String cartItems = (String) session.getAttribute("cartItemIds");

		if (cartItems != null && cartItems != "") {
			String id[] = cartItems.split(",");
			int cartIds[] = new int[id.length];
			json.put(BaseConfig.RESCODE, "0");
			for (int i = 0; i < id.length; i++) {
				cartIds[i] = Integer.parseInt(id[i]);
			}
			List<Map<String, Object>> cartList = userCartService.getUserCartByids(cartIds);// 购物车选中条目
			json.put(BaseConfig.RESLIST, cartList);

			if (cartList != null && cartList.size() > 0 ) {
				json.put("companyId", cartList.get(0).get("company_id"));
			}
		} else {
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		}

		if (user != null) {
			user = userService.selectByPrimaryKey(user.getId());
			UserLocation userLocation = new UserLocation();
			userLocation.setUserid(user.getId());
			userLocation.setStatus(true);
			userLocation = userLocationService
					.selectByUserLocationOrStatus(userLocation);
			json.put("userLocation", userLocation);
			/*if (user.getIsFirstOrder() == 1) {
				// 判断是首单
				SysProportion sysProp = new SysProportion();
				List<SysProportion> list = sysProportionService
						.getAllBySysProportion(sysProp);
				if (list != null && list.size() > 0) {
					json.put("firstSubstraPrice", list.get(0)
							.getFirstSubtractPrice().toString());
				}
			}*/
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存购物车条目到sessson中
	 * 
	 * @param session
	 */
	@RequestMapping("/saveCartItems")
	public void saveToSessionCartItems(HttpServletResponse response,
			HttpSession session, String cartItemIds) {
		session.setAttribute("cartItemIds", cartItemIds);
		System.out.println(cartItemIds);
		JSONObject json = new JSONObject();
		json.put(BaseConfig.RESCODE, "1");
		json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获得优惠活动价格
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param cartItemIds
	 */
	@RequestMapping("/getPromotionActivity")
	public void getPromotionActivity(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String cartItemIds) {

		BigDecimal promPrice = new BigDecimal("0.00");
		BigDecimal foolPrice = new BigDecimal("0.00");
		BigDecimal foolPriceT = new BigDecimal("0.00");
		BigDecimal allPrice = new BigDecimal("0.00");
		JSONObject json = new JSONObject();
		if (cartItemIds != null && !cartItemIds.equals("")) {
			String id[] = cartItemIds.split(",");
			int cartIds[] = new int[id.length];

			for (int i = 0; i < id.length; i++) {
				cartIds[i] = Integer.parseInt(id[i]);
			}
			BigDecimal orderPrice = new BigDecimal("0");

			List<Map<String, Object>> cartList = userCartService
					.getUserCartByids(cartIds);// 购物车选中条目

			if (cartList != null && cartList.size() > 0) {

				for (Map<String, Object> map : cartList) {

					BigDecimal count = new BigDecimal(map.get("count")
							.toString());
					BigDecimal price = new BigDecimal(map.get("price")
							.toString());
					orderPrice = orderPrice.add(count.multiply(price));

				}

			}

			PromotionActivity pa = new PromotionActivity();
			pa.setAllProduct(1);
			List<PromotionActivity> pList2 = promotionActivityService
					.getAllByPromotionActivityNormal(pa);// 判断全场参加的商品

			if (pList2 != null && pList2.size() > 0) {
				for (PromotionActivity pac : pList2) {

					if (pac.getType() == 0) {// 0满减 1满折

						if (pac.getNeedPrice().doubleValue() <= orderPrice
								.doubleValue()) {
							allPrice = pac.getSubstractPrice();

						}
					} else {

						if (pac.getNeedPrice().doubleValue() <= orderPrice
								.doubleValue()) {
							allPrice = new BigDecimal("0.00");
							BigDecimal disCount = new BigDecimal(
									pac.getDiscount());
							BigDecimal th = new BigDecimal("100");
							promPrice = disCount.multiply(orderPrice)
									.divide(th);
							System.out.println(promPrice + "满折");
							promPrice = orderPrice.subtract(promPrice);

						}
					}
				}
			} // 全场满减
			System.out.println(allPrice + "全场满减");
			promPrice = promPrice.add(allPrice);

			System.out.println(promPrice + "全场");
			int prods[] = new int[cartList.size()];
			int number = 0;
			for (Map<String, Object> map : cartList) {
				prods[number++] = Integer.parseInt(map.get("prod_id")
						.toString());
			}
			List<Map<String, Object>> pList = promotionProductService
					.getAllByProdIdVsInTime(prods);
			List<Integer> pnum;
			for (Map<String, Object> pac : pList) {
				pnum = new ArrayList<Integer>();

				// 每个促销的临时总价格
				BigDecimal tempMoney = new BigDecimal(0);

				String pros[] = pac.get("products").toString().split(",");
				if (pros.length > 0) {

				}
				for (String str : pros) {
					pnum.add(Integer.parseInt(str));

				}
				for (Map<String, Object> map : cartList) {

					if (pnum.contains(Integer.parseInt(map.get("prod_id")
							.toString()))) {
						BigDecimal Pricel = new BigDecimal(map.get("price")
								.toString());
						BigDecimal count = new BigDecimal(map.get("count")
								.toString());

						tempMoney = tempMoney.add(Pricel.multiply(count));
					}

				}
				if (tempMoney.doubleValue() >= Double.valueOf(pac.get(
						"need_price").toString())) {
					if (pac.get("type").toString().equals("0")) {
						foolPrice = new BigDecimal("0.00");
						foolPriceT = new BigDecimal("0.00");
						foolPrice = new BigDecimal(pac.get("substract_price")
								.toString());
						System.out.println(foolPrice + "foolPrice");
					} else {
						foolPriceT = new BigDecimal("0.00");
						foolPrice = new BigDecimal("0.00");
						BigDecimal th = new BigDecimal("100");
						BigDecimal discount = new BigDecimal(pac
								.get("discount").toString());

						foolPriceT = (tempMoney.subtract((discount
								.multiply(tempMoney).divide(th))));

					}

				}

			}

		}
		System.out.println(foolPriceT + "finally");

		promPrice = promPrice.add(foolPrice);
		promPrice = promPrice.add(foolPriceT);
		json.put("promPrice", promPrice.toString());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获得优惠券详情
	 * 
	 * @param reuqest
	 * @param response
	 * @param session
	 * @param id
	 */
	@RequestMapping("/getMortgagePrice")
	public void getMortgagePrice(HttpServletRequest reuqest,
			HttpServletResponse response, HttpSession session, Integer id) {
		JSONObject json = new JSONObject();
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户
		if (user != null) {
			SysCouponsRecord sysCRod = sysCouponsRecordService
					.selectByPrimaryKey(id);
			json.put("sysCRod", sysCRod);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取基地的信息满减，配送费
	 * 
	 * @param request
	 * @param response
	 * @param companyId
	 */
	@RequestMapping("/getCompanyInfo")
	public void getCompanyInfo(HttpServletRequest request,
			HttpServletResponse response, Integer companyId) {
		JSONObject json = new JSONObject();
		Company company = null;
		if (companyId != null) {
			company = companyService.getCompanyById(companyId);

		}
		json.put("company", company);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户的优惠券
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping("/getSysCouposRecordByUserId")
	public void getSysCouposRecordByUserId(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String neddPrice) {
		System.out.println(neddPrice
				+ "----------------------------------------->>");
		JSONObject json = new JSONObject();
		List<SysCouponsRecord> listCoupon = null;
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);// 登录用户

		if (user != null) {
			System.out.println(user.getId() + "userid");
			SysCouponsRecord sysRecord = new SysCouponsRecord();
			sysRecord.setUserId(user.getId());
			sysRecord.setState(1);
			if (neddPrice != null && !neddPrice.equals("")) {
				BigDecimal nedPrice = new BigDecimal(neddPrice);
				sysRecord.setNeedPrice(nedPrice);
			}
			listCoupon = sysCouponsRecordService
					.getUserCouponsRecord(sysRecord);
			json.put(BaseConfig.RESLIST, listCoupon);
			json.put(BaseConfig.RESCODE, "0");
		} else {
			json.put(BaseConfig.RESLIST, listCoupon);
			json.put(BaseConfig.RESCODE, "1");
			json.put(BaseConfig.RESMESSAGE, BaseConfig.MESSAGE.get("user.ER1025").toString());
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 单个删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deleteUserCartItem")
	public void deleteUserCartItem(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Integer id) {
		JSONObject json = new JSONObject();
		int result = 0;
		User user = (User) session
				.getAttribute(SystemConfig.SESSION_FRONT_USER);

		UserCart userCart = new UserCart();
		userCart.setId(id);
		userCart.setUserId(user.getId());
		result = userCartService.deleteByPrimaryKey(userCart);
		if (result > 0) {
			json.put("flag", true);
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   /**
    * 批量删除购物车条目
    * @param request
    * @param response
    */
	@RequestMapping("/deleteUserCartItemBatch")
	public void deleteUserCartItemBatch(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int result = 0;
		String cartItem = request.getParameter("ids");
		String cartItems[] = cartItem.split(",");
		int ids[] = new int[cartItems.length];
		for (int i = 0; i < cartItems.length; i++) {
			ids[i] = Integer.parseInt(cartItems[i]);
		}
		result = userCartService.deleteUserCartBetach(ids);
		if (result > 0) {
			json.put("flag", "1");
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
