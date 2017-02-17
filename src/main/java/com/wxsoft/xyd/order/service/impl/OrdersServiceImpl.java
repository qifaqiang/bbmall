package com.wxsoft.xyd.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.util.GenerateNo;
import com.wxsoft.framework.util.HTMLInputFilter;
import com.wxsoft.framework.util.SmsUtil;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.mapper.CompanyStockMapper;
import com.wxsoft.xyd.common.mapper.PromotionActivityMapper;
import com.wxsoft.xyd.common.mapper.PromotionProductMapper;
import com.wxsoft.xyd.common.mapper.SysCouponsRecordMapper;
import com.wxsoft.xyd.common.mapper.SysShippingMapper;
import com.wxsoft.xyd.common.mapper.UserCartMapper;
import com.wxsoft.xyd.common.mapper.UserLocationMapper;
import com.wxsoft.xyd.common.mapper.UserMapper;
import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserLocation;
import com.wxsoft.xyd.order.mapper.OrdersDetailMapper;
import com.wxsoft.xyd.order.mapper.OrdersLocationMapper;
import com.wxsoft.xyd.order.mapper.OrdersLogMapper;
import com.wxsoft.xyd.order.mapper.OrdersMapper;
import com.wxsoft.xyd.order.mapper.OrdersReturnMapper;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.order.model.OrdersLocation;
import com.wxsoft.xyd.order.model.OrdersLog;
import com.wxsoft.xyd.order.model.OrdersReturn;
import com.wxsoft.xyd.order.service.OrdersService;
import com.wxsoft.xyd.prod.mapper.ProductBasicMapper;
import com.wxsoft.xyd.prod.mapper.ProductMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationStockMapper;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.model.ProductPackage;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;
import com.wxsoft.xyd.system.mapper.CompanyMapper;
import com.wxsoft.xyd.system.mapper.SysProportionMapper;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.SysProportion;

/**
 * 订单处理services
 * 
 * @author kyz
 * 
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserCartMapper userCartMapper;
	@Autowired
	private OrdersLogMapper ordersLogMapper;
	@Autowired
	private OrdersLocationMapper ordersLocationMapper;
	@Autowired
	private OrdersReturnMapper returnOrderMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private ProductSpecificationInfoMapper productSpecificationInfoMapper;
	@Autowired
	private SysShippingMapper sysShippingMapper;
	@Autowired
	private CompanyStockMapper companyStockMapper;
	@Autowired
	private SysCouponsRecordMapper sysCouponsRecordMapper;
	@Autowired
	private UserLocationMapper userLocationMapper;
	@Autowired
	private ProductSpecificationMapper productSpecificationMapper;
	@Autowired
	private PromotionProductMapper promotionProductMapper;
	@Autowired
	private PromotionActivityMapper promotionActivityMapper;
	@Autowired
	private SysProportionMapper sysProportionMapper;
	@Autowired
	private ProductBasicMapper productBasicMapper;
	@Autowired
	private ProductSpecificationStockMapper productSpecificationStockMapper;
	/**
	 * 删除订单
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ordersMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入订单
	 */
	@Override
	public int insert(Orders record) {
		return ordersMapper.insert(record);
	}

	/**
	 * 插入订单随机
	 */
	@Override
	public int insertSelective(Orders record) {
		return ordersMapper.insertSelective(record);
	}

	/**
	 * 更新订单全部
	 */
	@Override
	public int updateByPrimaryKeySelective(Orders record) {
		return ordersMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据订单id查询订单信息
	 */
	@Override
	public Orders selectByPrimaryKey(Integer id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据订单返回订单信息
	 */
	@Override
	public Orders selectByOrders(Orders record) {
		return ordersMapper.selectByOrders(record);
	}

	/**
	 * 分页返回订单信息
	 */
	@Override
	public List<Orders> listPageByOrders(Orders clssname) {
		return ordersMapper.listPageByOrders(clssname);
	}

	/**
	 * 获取所有订单
	 */
	@Override
	public List<Orders> getAllByOrders(Orders clssname) {
		return ordersMapper.getAllByOrders(clssname);
	}

	/**
	 * 根据订单信息查询订单
	 */
	@Override
	public Orders selectByOrderInfo(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrderInfo(record);
	}

	/**
	 * 插入订单返回map
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> insertAddOrder(String cartItems,
			Integer companyId, Integer shipAddressId, Integer userShipType,
			Integer sysCoupRecordId, String remark, Integer payType,
			Integer userId, boolean isFirst) throws Exception {
		Map<String, Object> retmap = new HashMap<String, Object>();
		String result = "1";
		String ids[] = cartItems.split(",");
		int cartImtes[] = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			cartImtes[i] = Integer.parseInt(ids[i]);
		}
		List<Map<String, Object>> cartList = userCartMapper
				.getUserCartByids(cartImtes);// 获得购物车
		Orders orders = new Orders();
		orders.setUserId(userId);
		orders.setAddtime(new Date());// 订单添加时间
		orders.setOrdersn(GenerateNo.nextOrdId(9));// 订单编号
		orders.setCompanyId(companyId);// 基地id
		orders.setShipAddressId(shipAddressId);// 收货地址id

		orders.setPayType(0);
		int orderCount = 0;

		String prods[] = new String[cartList.size()];
		String counts[] = new String[cartList.size()];
		String specids[] = new String[cartList.size()];
		int number = 0;

		// 设置合计
		BigDecimal orderPrice = new BigDecimal("0");// 合计
		if (cartList != null && cartList.size() > 0) {

			for (Map<String, Object> map : cartList) {

				BigDecimal count = new BigDecimal(map.get("count").toString());
				BigDecimal price = new BigDecimal(map.get("price").toString());
				orderPrice = orderPrice.add(count.multiply(price));
				orderCount += count.intValue();

			}

		}

		orders.setOrderPrice(orderPrice);// 商品价格
		orders.setCount(orderCount);// 订单数量
		System.out.println(cartItems
				+ "-------------------------------------->>>>>");
		orders.setPromotionPrice(getPromotionActivity(cartList, orderPrice));// 促销价格

		SysCouponsRecord sysCrord = getSysCoupRecord(sysCoupRecordId, userId);
		if (sysCrord != null) {

			if (orderPrice.compareTo(sysCrord.getNeedPrice()) >= 0) {
				orders.setCouponsPrice(sysCrord.getSubstractPrice());
				sysCrord.setState(2);
				sysCrord.setId(sysCoupRecordId);

				sysCouponsRecordMapper.updateByPrimaryKeySelective(sysCrord);// 改变优惠券状态为不可用
				orders.setCouponsRecordId(sysCoupRecordId);// 优惠券id
			}
		}
		BigDecimal activiPrice = new BigDecimal("0");
		// orders.setOrderAccount(orderPrice);
		if (orders.getCouponsPrice() != null) {
			activiPrice = activiPrice.add(orders.getCouponsPrice());
		}
		if (orders.getPromotionPrice() != null) {
			activiPrice = activiPrice.add(orders.getPromotionPrice());

		}
		orders.setOrderAccount(orders.getOrderPrice().subtract(activiPrice));// 保存最后的价格

		BigDecimal ifzero = new BigDecimal("0");
		if (isFirst == true) {// 是首单
			SysProportion sysProp = BaseConfig.sysProportion;
			orders.setFirstOrderPrice(sysProp.getFirstSubtractPrice());// 首单

			User user = userMapper.selectByPrimaryKey(userId);
			user.setIsFirstOrder(0);
			userMapper.updateByPrimaryKeySelective(user);
			// 更改是否首单
		}

		orders.setPayForm(payType);// 支付方式

		orders.setRemark(new HTMLInputFilter().filter(remark));

		Company company = companyMapper.getCompanyById(companyId);// 基地信息
		if (userShipType == 1) {
			BigDecimal shipPrice = new BigDecimal("0");
			orders.setUserShipType(1);
			orders.setShipPrice(shipPrice);
		} else {
			if (company.getSendPrice() >= orderPrice.intValue()) {
				BigDecimal shipPrice = new BigDecimal(company
						.getChargeSendPrice().toString());
				orders.setShipPrice(shipPrice);// 运费价格
			} else {
				BigDecimal shipPrice = new BigDecimal("0");
				orders.setShipPrice(shipPrice);
			}
			orders.setUserShipType(2);
		}

		BigDecimal fl = orders.getOrderAccount().setScale(2,
				BigDecimal.ROUND_HALF_UP);
		orders.setOrderAccount(fl);
		orders.setStatus("11");// 订单转台

		// 订单价格为零
		// 计算现在订单支付的价格
		BigDecimal resultPrice = null;
		if (null == orders.getFirstOrderPrice()) {
			resultPrice = orders.getOrderAccount().setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} else {
			resultPrice = orders.getOrderAccount()
					.subtract(orders.getFirstOrderPrice())
					.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		if (orders.getShipPrice() != null) {
			orders.setOrderAccount(orders.getOrderAccount().add(
					orders.getShipPrice()));
		}

		if (resultPrice.compareTo(ifzero) <= 0) {
			if (orders.getShipPrice() == null) {
				orders.setOrderAccount(ifzero);
				retmap.put("ifzero", "ifzero");
				orders.setStatus("20");
				orders.setPayTime(new Date());
				orders.setPayForm(8);
				retmap.put("orderRealySn", orders.getOrdersn());
			} else {
				orders.setOrderAccount(orders.getShipPrice());
			}
		}
		orders.setTradingCode(GenerateNo.payRecordNo());

		ordersMapper.insertSelective(orders);// 保存订单

		UserLocation userLocation = userLocationMapper
				.selectByPrimaryKey(shipAddressId);
		if (cartList != null && cartList.size() > 0) {
			for (Map<String, Object> map : cartList) {// 订单详情
				OrdersDetail ordersDetail = new OrdersDetail();
				ordersDetail.setOrderId(orders.getId());
				ordersDetail.setProdId(Integer.parseInt(map.get("prod_id")
						.toString()));
				ordersDetail.setProdName(map.get("name").toString());
				BigDecimal pricel = new BigDecimal(map.get("price").toString());
				ordersDetail.setProdPrice(pricel);
				ordersDetail.setProdUri(map.get("picuri").toString());
				ordersDetail.setProdCount(Integer.parseInt(map.get("count")
						.toString()));
				ordersDetail.setProdSpecId(map.get("spec_id").toString());
				//开始对规格的处理
				String specName_t ="";
				if(null == map.get("spec_id") || "0".equals(map.get("spec_id").toString())){//没有启用规格
					specName_t = "无";
				}else{
					if(null != map.get("specDetailList") && ((List<ProductSpecificationInfoDetail>)map.get("specDetailList")).size()>0){
						for (ProductSpecificationInfoDetail psi_t : (List<ProductSpecificationInfoDetail>)map.get("specDetailList")) {
							specName_t += psi_t.getSpecificationName()+":"+psi_t.getSpecificationDetailName()+"  ";
						}
					}
				}
				
				ordersDetail.setProdSpecName(specName_t);
				
				
				prods[number] = map.get("prod_id").toString();
				counts[number] = map.get("count").toString();
				specids[number] = map.get("spec_id").toString();
				number++;

				ordersDetailMapper.insertSelective(ordersDetail);// 保存订单详情
			}
			// try {
			substractCompanyStock(prods, companyId, counts, specids);// 减库存
			OrdersLog ordersLog = new OrdersLog();// 订单日志
			ordersLog.setOrderId(orders.getId());
			ordersLog.setOperator(userId);// 操作人
			ordersLog.setOrderStatus("0");
			ordersLog.setChangedStatus("20");
			ordersLog.setRemark("下单");
			ordersLog.setLogTime(Tools.date2Str(new Date()));
			ordersLogMapper.insert(ordersLog);
			OrdersLocation ordersLocation = new OrdersLocation();
			ordersLocation.setAddressName(userLocation.getRegionName()
					+ userLocation.getLocation());
			ordersLocation.setOrderId(orders.getId());
			ordersLocation.setConsignee(userLocation.getConsignee());
			ordersLocation.setMobile(userLocation.getMobile());
			ordersLocation.setZipcode(userLocation.getZipcode());
			ordersLocation.setCreated(Tools.date2Str(new Date()));
			ordersLocationMapper.insert(ordersLocation);
			System.out.println(orders.getOrderAccount());
			userCartMapper.deleteUserCartBetach(cartImtes);// 清除购物车

			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// result = e.getMessage();
			//
			// }
		}
		retmap.put("recode", result);
		retmap.put("orderAccount", orders.getOrderAccount().toString());// 订单价格
		retmap.put("orderId", orders.getId());
		retmap.put("pcOrdersn", orders.getOrdersn());
		return retmap;

	}

	/**
	 * 获得优惠券
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public SysCouponsRecord getSysCoupRecord(Integer id, Integer userId) {
		if (id != null && !id.equals("")) {
			System.out.println("THis isy whyt");
			SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
			sysCouponsRecord.setId(id);
			sysCouponsRecord.setUserId(userId);
			sysCouponsRecord = sysCouponsRecordMapper.selectByPrimaryKey(id);

			return sysCouponsRecord;
		} else {
			return null;
		}
	}

	/**
	 * 促销活动
	 * 
	 * @param cartList
	 * @param orderPricel
	 * @return
	 */
	public BigDecimal getPromotionActivity(List<Map<String, Object>> cartList,
			BigDecimal orderPricel) {

		BigDecimal promPrice = new BigDecimal("0.00");
		BigDecimal foolPrice = new BigDecimal("0.00");
		BigDecimal foolPriceT = new BigDecimal("0.00");
		BigDecimal allPrice = new BigDecimal("0.00");
		PromotionActivity pa = new PromotionActivity();
		pa.setAllProduct(1);
		List<PromotionActivity> pList2 = promotionActivityMapper
				.getAllByPromotionActivityNormal(pa);// 判断全场参加的商品

		if (pList2 != null && pList2.size() > 0) {
			for (PromotionActivity pac : pList2) {

				if (pac.getType() == 0) {// 0满减 1满折

					if (pac.getNeedPrice().doubleValue() <= orderPricel
							.doubleValue()) {
						allPrice = pac.getSubstractPrice();

					}
				} else {
					if (pac.getNeedPrice().doubleValue() <= orderPricel
							.doubleValue()) {
						allPrice = new BigDecimal("0.00");
						BigDecimal disCount = new BigDecimal(pac.getDiscount());
						BigDecimal th = new BigDecimal("100");
						promPrice = disCount.multiply(orderPricel).divide(th);
						promPrice = orderPricel.subtract(promPrice);

					}
				}
			}
		} // 全场满减
		promPrice = promPrice.add(allPrice);
		int prods[] = new int[cartList.size()];
		int number = 0;
		for (Map<String, Object> map : cartList) {
			prods[number++] = Integer.parseInt(map.get("prod_id").toString());
		}

		List<Map<String, Object>> pList = promotionProductMapper
				.getAllByProdIdVsInTime(prods);
		List<Integer> pnum;
		for (Map<String, Object> pac : pList) {
			pnum = new ArrayList<Integer>();

			// 每个促销的临时总价格
			BigDecimal tempMoney = new BigDecimal(0);
			// System.out.println(pac.get("products"));
			String pros[] = pac.get("products").toString().split(",");
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
			if (tempMoney.doubleValue() >= Double.valueOf(pac.get("need_price")
					.toString())) {
				if (pac.get("type").toString().equals("0")) {
					foolPrice = new BigDecimal("0.00");
					foolPriceT = new BigDecimal("0.00");
					foolPrice = new BigDecimal(pac.get("substract_price")
							.toString());
				} else {
					foolPriceT = new BigDecimal("0.00");
					foolPrice = new BigDecimal("0.00");
					BigDecimal th = new BigDecimal("100");
					BigDecimal discount = new BigDecimal(pac.get("discount")
							.toString());

					foolPriceT = (tempMoney.subtract((discount
							.multiply(tempMoney).divide(th))));
				}

			}

		}
		System.out.println(promPrice + "promPrice");
		promPrice = promPrice.add(foolPrice);
		promPrice = promPrice.add(foolPriceT);
		return promPrice;

	}

	/**
	 * 根据支付更新订单状态
	 */
	@Override
	public int updateByPayNow(Orders record, Integer payType) {
		// TODO Auto-generated method stub
		if (this.ordersMapper.updateByPrimaryKeySelective(record) > 0) {

			Orders orderTemp = new Orders();
			orderTemp.setId(record.getId());
			orderTemp = ordersMapper.selectByOrderInfo(orderTemp);
			StringBuffer pronames = new StringBuffer();
			for (OrdersDetail ods : orderTemp.getOd()) {
				pronames.append(ods.getProdName())
						.append("[" + ods.getProdSpecName() + "]*")
						.append(ods.getProdCount() + ",");
			}// 获取订单里面得商品

			// 用户收货信息
			String orderLocation = null;
			if (orderTemp.getUserShipType() == 1) {
				orderLocation = "自提:" + orderTemp.getUl().getConsignee() + " "
						+ orderTemp.getUl().getMobile();
			} else {
				orderLocation = orderTemp.getUl().getConsignee() + " "
						+ orderTemp.getUl().getMobile() + " "
						+ orderTemp.getUl().getAddressName();

			}
			Company c = companyMapper.getCompanyWxByCompanyId(orderTemp
					.getCompanyId());
			SmsUtil.sendSms(
					c.getMobile(),
					4,
					pronames.toString().substring(0,
							pronames.toString().length() - 1), orderLocation);

			OrdersLog ol = new OrdersLog();
			ol.setOperator(orderTemp.getUserId());
			ol.setOrderId(record.getId());
			switch (payType) {
			case 1:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("pc(支付宝)");
				break;
			case 2:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("pc（微信扫码）");
				break;
			case 3:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("pc（银联支付）");
				break;
			case 4:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("wap（支付宝）");
				break;
			case 5:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("微信支付");
				break;
			case 6:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("wap（银联支付）");
				break;
			case 7:
				ol.setOrderStatus("11");
				ol.setChangedStatus("20");
				ol.setRemark("微信支付");
				break;
			case 8:
				ol.setRemark("订单价格为0");
				break;
			default:
				break;
			}

			ordersLogMapper.insertSelective(ol);
		}
		return 0;
	}

	/**
	 * 申请退款操作
	 */
	@Override
	public int updateByOrderReturn(Orders order, String remark) {
		// TODO Auto-generated method stub
		if (this.ordersMapper.updateByPrimaryKeySelective(order) > 0) {
			OrdersLog ol = new OrdersLog();
			ol.setOperator(order.getUserId());
			ol.setOrderId(order.getId());
			ol.setOrderStatus("40");
			ol.setChangedStatus("50");
			ol.setRemark("用户申请退货");
			ordersLogMapper.insertSelective(ol);
			OrdersReturn ro = new OrdersReturn();
			ro.setOrderId(order.getId());
			ro.setRemark(remark);
			ro.setStatus(0);
			ro.setUserId(order.getUserId());
			returnOrderMapper.insertSelective(ro);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 根据订单返回信息 后台处理
	 */
	@Override
	public List<Orders> selectByOrderBack(Orders order) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrderBack(order);
	}

	/**
	 * 分页返回退款订单信息
	 */
	@Override
	public List<Orders> listPageByOrdersReturn(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.listPageByOrdersReturn(clssname);
	}

	/**
	 * 发货处理
	 */
	@Override
	public int updateByFaHuo(Orders record, Integer loginuser) {

		if (ordersMapper.updateByPrimaryKeySelective(record) > 0) {
			OrdersLog ol = new OrdersLog();
			ol.setOperator(loginuser);
			ol.setOrderId(record.getId());
			ol.setChangedStatus("30");
			ol.setOrderStatus("22");
			ol.setRemark("操作发货");
			ol.setLogTime(Tools.date2Str(new Date()));
			return ordersLogMapper.insertSelective(ol);
		}
		return 0;
	}

	/**
	 * 接单处理
	 */
	@Override
	public int updateByJieDan(Orders record, Integer loginuser) {
		// TODO Auto-generated method stub
		if (record.getUserShipType() == 1) {// 自提
			// TODO Auto-generated method stub
			String userIdTemp = record.getUserId().toString();
			String userCode = record.getUserId() < 1000 ? userIdTemp
					: userIdTemp.substring(userIdTemp.length() - 3,
							userIdTemp.length());
			String picCode = record.getCompanyId().toString()
					+ record.getId().toString().substring(4, 9) + userCode;
			record.setPickCode(picCode);
			record.setStatus("30");
			record.setShipTime(new Date());

			OrdersLocation ol = new OrdersLocation();
			ol.setOrderId(record.getId());
			ol = ordersLocationMapper.selectByOrdersLocation(ol);

			Company c = companyMapper.getCompanyWxByCompanyId(record
					.getCompanyId());

			SmsUtil.sendSms(ol.getMobile(), 3, record.getOrdersn(), picCode,
					c.getCompanyName(), c.getMobile());
		}
		if (ordersMapper.updateByPrimaryKeySelective(record) > 0) {
			OrdersLog ol = new OrdersLog();
			ol.setOperator(loginuser);
			ol.setOrderId(record.getId());
			ol.setChangedStatus(record.getStatus());
			ol.setOrderStatus("20");
			ol.setRemark("操作接单");
			ol.setLogTime(Tools.date2Str(new Date()));
			return ordersLogMapper.insertSelective(ol);
		}
		return 0;
	}

	/**
	 * 更新订单状态
	 */
	@Override
	public int updateByUpdateShip(Orders record, Integer loginuser) {
		// TODO Auto-generated method stub

		if (ordersMapper.updateByPrimaryKeySelective(record) > 0) {
			OrdersLog ol = new OrdersLog();
			ol.setOperator(loginuser);
			ol.setOrderId(record.getId());
			ol.setChangedStatus("22");
			ol.setOrderStatus("22");
			ol.setRemark("操作填写订单号:" + record.getShipCode() + "-"
					+ record.getShipName());
			ol.setLogTime(Tools.date2Str(new Date()));
			return ordersLogMapper.insertSelective(ol);
		}
		return 0;
	}

	/**
	 * 支付修改价格
	 */
	@Override
	public int updateByUpdatePrice(Orders record, Integer loginuser,
			BigDecimal oldOrderPrice, BigDecimal oldShipPrice,
			BigDecimal orderAccount) {
		// TODO Auto-generated method stub
		if (ordersMapper.updateByPrimaryKeySelective(record) > 0) {
			OrdersLog ol = new OrdersLog();
			ol.setOperator(loginuser);
			ol.setOrderId(record.getId());
			ol.setChangedStatus("20");
			ol.setOrderStatus("20");
			ol.setRemark("修改订单价格:商品价格:" + oldOrderPrice + "->"
					+ record.getOrderPrice() + ",运费价格:" + oldShipPrice + "->"
					+ record.getShipPrice() + ",订单总价:" + orderAccount + "->"
					+ record.getOrderAccount());
			ol.setLogTime(Tools.date2Str(new Date()));
			return ordersLogMapper.insertSelective(ol);
		}
		return 0;
	}

	/**
	 * 返回申请退款订单信息
	 */
	@Override
	public List<Orders> selectByReturnOrderBack(Orders order) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByReturnOrderBack(order);
	}

	/**
	 * 返回订单处理信息
	 */
	@Override
	public List<Orders> getAllByReturnOrdersInfo(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllByReturnOrdersInfo(clssname);
	}

	/**
	 * 获取所有订单信息
	 */
	@Override
	public List<Orders> getAllByOrdersInfo(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllByOrdersInfo(clssname);
	}

	/**
	 * 获取所有订单退款信息
	 */
	@Override
	public Orders selectByReturnOrderInfo(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByReturnOrderInfo(record);
	}

	/**
	 * 系统操作退款信息
	 */
	@Override
	public String updateByOrderReturnSystem(Orders order, OrdersReturn remark)
			throws Exception {
		// TODO Auto-generated method stub
		String result = null;
		if (remark.getStatus() == 1) {// 审核通过
			order.setIs_over(1);// 将订单修改结束装填
			order.setOverTime(new Date());
		}

		if (ordersMapper.updateByPrimaryKeySelective(order) > 0) {
			if (returnOrderMapper.updateByPrimaryKeySelective(remark) > 0) {
				if (remark.getStatus() == 1) {// 审核通过
					order.setIs_over(1);
					OrdersLog ol = new OrdersLog();
					ol.setOperator(order.getCompanyId());
					ol.setOrderId(order.getId());
					ol.setChangedStatus("60");
					ol.setOrderStatus("50");
					ol.setRemark("操作退货");
					ol.setLogTime(Tools.date2Str(new Date()));
					if (ordersLogMapper.insertSelective(ol) > 0) {
						OrdersDetail od = new OrdersDetail();
						od.setOrderId(order.getId());
						List<OrdersDetail> odlist = ordersDetailMapper
								.getAllByOrdersDetail(od);

						String prodIds = "";
						Integer companyId = order.getCompanyId();
						String counts = "";
						String specIds = "";

						for (OrdersDetail odetail : odlist) {
							prodIds += odetail.getProdId() + ",";
							counts += odetail.getProdCount() + ",";
							specIds += odetail.getProdSpecId() + ",";
						}

						// 临时存放
						ProductSpecificationStock pss_temp = null;
						// 记录商品规格 库存扣减失败情况下调用
						Map<Integer, String> mapRecordProductInfo = new HashMap<Integer, String>();

						String[] prodId = prodIds.split(",");
						String[] count = null == counts ? null : counts
								.split(",");
						String[] specId = null == specIds ? null : specIds
								.split(",");
						List<Product> p = productMapper
								.getPackageByProductId(prodId);
						Map<String, Product> mapProduct = new HashMap<String, Product>();
						for (Product product : p) {
							mapProduct.put(product.getId() + "", product);
						}
										
						int i = 0;
						for (String pid : prodId) {
							Product product = mapProduct.get(pid);
							
							int tempCount = 0;

							if (null != count) {
								tempCount = Integer.parseInt(count[i]);
							} else {
								throw new Exception();
							}

							if (product.getType() == 0) {// 普通商品
								if (null != specId) {// 存在规格id
									pss_temp = new ProductSpecificationStock();
									pss_temp.setCompanyId(companyId);
									pss_temp.setProductId(product.getId());
									if(null == product.getIsSpecification() || 0 == product.getIsSpecification()){//该商品没有开启规格
										pss_temp.setSpecificationInfoId(0);
										pss_temp.setType(0);
									}else{
										pss_temp.setSpecificationInfoId(Integer.parseInt(specId[i]));
										pss_temp.setType(1);
									}
									pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);
									if (null != pss_temp) {// 基地商品库存
										int cur_invencount = pss_temp.getInventorycount();
										
										pss_temp.setInventorycount(tempCount * +1);
										// 修改库存
										if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
											if (!mapRecordProductInfo.containsKey(pss_temp.getId())) {
												throw new Exception(
														"恢复："
															+ product.getName()
															+ "["
															+ cur_invencount
															+ product.getUnit()
															+ "]失败");
					
											} else {
												throw new Exception("恢复："
														+ mapRecordProductInfo.get(pss_temp.getId())
														+ "," + product.getName()+"["+cur_invencount+product.getUnit()+"]"
														+ "]失败");
											}
										}else{
											//记录一下该规格
											if(mapRecordProductInfo.containsKey(pss_temp.getId())){
												mapRecordProductInfo.put(pss_temp.getId(), mapRecordProductInfo.get(pss_temp.getId()) +","+product.getName()+"["+cur_invencount+product.getUnit()+"]");
											}else{
												mapRecordProductInfo.put(pss_temp.getId(), product.getName()+"["+cur_invencount+product.getUnit()+"]");
											}
										}

									} else {
										throw new Exception("恢复："
												+ product.getName() + "["
												+ product.getUnit() + "]失败");
									}
								} else {
									result = "由于商品：" + product.getName()
											+ "中规格不存在，无法恢复库存";
								}

							} else {// 礼盒包商品
								List<ProductPackage> pp = product
										.getProductPackageList();
								for (ProductPackage productPackage : pp) {

									pss_temp = new ProductSpecificationStock();
									pss_temp.setCompanyId(companyId);
									pss_temp.setProductId(productPackage.getProductId());
									if(0 == productPackage.getType()){//该商品没有开启规格
										pss_temp.setSpecificationInfoId(0);
										pss_temp.setType(0);
									}else{
										pss_temp.setSpecificationInfoId(productPackage.getSpecificationInfoId());
										pss_temp.setType(1);
									}
									pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);

									if (null != pss_temp) {// 基地商品库存
										int cur_invencount = pss_temp.getInventorycount();
										
										pss_temp.setInventorycount(tempCount * +1);
										// 修改库存
										if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
											if (!mapRecordProductInfo.containsKey(pss_temp.getId())) {
												throw new Exception("恢复："
														+ product.getName()
														+ "[礼盒]失败");
											} else {
												throw new Exception("恢复："
														+ mapRecordProductInfo.get(pss_temp.getId())
														+ "," + product.getName()
														+ "[礼盒]失败");
											}
										}else{
											//记录一下该规格
											if(mapRecordProductInfo.containsKey(pss_temp.getId())){
												mapRecordProductInfo.put(pss_temp.getId(), mapRecordProductInfo.get(pss_temp.getId()) +","+product.getName());
											}else{
												mapRecordProductInfo.put(pss_temp.getId(), product.getName());
											}
										}

									} else {
										result = "由于礼盒商品：" + product.getName()
												+ "中部分组合不存在，无法恢复库存";
									}
								}
							}
							i++;
						}

						if (null != order.getCouponsRecordId()
								&& order.getCouponsRecordId() != 0) {
							sysCouponsRecordMapper.updateByTimeIsOver(order
									.getCouponsRecordId());// 暂不考虑优惠券，回滚是否成功
						}
					}
				} else {
					OrdersLog ol = new OrdersLog();
					ol.setOperator(order.getCompanyId());
					ol.setOrderId(order.getId());
					ol.setChangedStatus("50");
					ol.setOrderStatus("50");
					ol.setRemark("操作退货不允许");
					ol.setLogTime(Tools.date2Str(new Date()));
					if (ordersLogMapper.insertSelective(ol) > 0) {
						return result;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 获取所有未发货的订单
	 */
	@Override
	public List<Orders> getAllByOrdersNoShip(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllByOrdersNoShip(clssname);
	}

	/**
	 * 获取所有发货的订单
	 */
	@Override
	public List<Orders> getAllByOrdersWithShip(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllByOrdersWithShip(clssname);
	}

	/**
	 * 获取所有提货码的订单
	 */
	@Override
	public List<Orders> getAllByOrdersWithTradingCode(String sn) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllByOrdersWithTradingCode(sn);
	}

	/**
	 * 获得所有的预售订单
	 */
	@Override
	public List<Orders> getAllBKByOrders(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.getAllBKByOrders(clssname);
	}

	/***
	 * 获取预售订单详情
	 */
	@Override
	public Orders selectByBKOrderInfo(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByBKOrderInfo(record);
	}

	/**
	 * 
	 */
	@Override
	public int selectByUserAndProductId(Map<String, String> maps) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByUserAndProductId(maps);
	}

	/**
	 * 会增库存
	 */
	@Override
	public String updateCompaySubtractProds(Orders order, Integer nowUser,
			Integer userType, Integer type, Integer newCompanyId)
			throws Exception {
		int tempCompanyId = order.getCompanyId();// 存放原始基地id

		order.setCompanyId(newCompanyId);
		Company c = companyMapper.getCompanyById(newCompanyId);
		// 基地开始接单判断
		String result = null;
		OrdersDetail od = new OrdersDetail();
		od.setOrderId(order.getId());
		List<OrdersDetail> odlist = ordersDetailMapper.getAllByOrdersDetail(od);

		String prodIds = "";
		Integer companyId = order.getCompanyId();
		String counts = "";
		String specIds = "";

		for (OrdersDetail odetail : odlist) {
			prodIds += odetail.getProdId() + ",";
			counts += odetail.getProdCount() + ",";
			specIds += odetail.getProdSpecId() + ",";
		}

		// 临时存放
		ProductSpecificationStock pss_temp = null;
		// 记录商品规格 库存扣减失败情况下调用
		Map<Integer, String> mapRecordProductInfo = new HashMap<Integer, String>();
		
		String[] prodId = prodIds.split(",");

		String[] count = null == counts ? null : counts.split(",");
		String[] specId = null == specIds ? null : specIds.split(",");
		List<Product> p = productMapper.getPackageByProdIds(prodId);
		Map<String, Product> mapProduct = new HashMap<String, Product>();
		for (Product product : p) {
			mapProduct.put(product.getId() + "", product);
		}

		int i = 0;
		for (String pid : prodId) {
			Product product = mapProduct.get(pid);
			int tempCount = 0;

			if (null != count) {
				tempCount = Integer.parseInt(count[i]);
			} else {
				throw new Exception("参数错误");
			}

			if (product.getType() == 0) {// 普通商品
				if (null != specId) {// 存在规格id
					pss_temp = new ProductSpecificationStock();
					pss_temp.setCompanyId(companyId);
					pss_temp.setProductId(product.getId());
					if(null == product.getIsSpecification() || 0 == product.getIsSpecification()){//该商品没有开启规格
						pss_temp.setSpecificationInfoId(0);
						pss_temp.setType(0);
					}else{
						pss_temp.setSpecificationInfoId(Integer.parseInt(specId[i]));
						pss_temp.setType(1);
					}
					pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);
					if (null != pss_temp) {// 基地商品库存
						int cur_invencount = pss_temp.getInventorycount();
						
						pss_temp.setInventorycount(tempCount * -1);
						// 修改库存
						if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
							if (!mapRecordProductInfo.containsKey(pss_temp.getId())) {
								throw new Exception(c.getCompanyName() + "基地，扣减："
										+ product.getName() + "["
										+ cur_invencount
										+ product.getUnit() + "]失败，无法修改配送基地");
	
							} else {
								throw new Exception(c.getCompanyName() + "基地，扣减："
										+ mapRecordProductInfo.get(pss_temp.getId())
										+ "," + product.getName()+"["+cur_invencount+product.getUnit()+"]"
										+ "存在冲突导致总购买数量超过系统库存，无法修改配送基地！");
							}
						}else{
							//记录一下该规格
							if(mapRecordProductInfo.containsKey(pss_temp.getId())){
								mapRecordProductInfo.put(pss_temp.getId(), mapRecordProductInfo.get(pss_temp.getId()) +","+product.getName()+"["+cur_invencount+product.getUnit()+"]");
							}else{
								mapRecordProductInfo.put(pss_temp.getId(), product.getName()+"["+cur_invencount+product.getUnit()+"]");
							}
						}

					} else {
						throw new Exception(c.getCompanyName() + "基地，扣减："
								+ product.getName() 
								+ product.getUnit() + "失败，无法修改配送基地");
					}
				} else {
					throw new Exception(c.getCompanyName() + "基地，扣减："
							+ product.getName() + "失败，无法修改配送基地");
				}
				
			} else {// 礼盒包商品
				List<ProductPackage> pp = product.getProductPackageList();
				for (ProductPackage productPackage : pp) {

					pss_temp = new ProductSpecificationStock();
					pss_temp.setCompanyId(companyId);
					pss_temp.setProductId(productPackage.getProductId());
					if(0 == productPackage.getType()){//该商品没有开启规格
						pss_temp.setSpecificationInfoId(0);
						pss_temp.setType(0);
					}else{
						pss_temp.setSpecificationInfoId(productPackage.getSpecificationInfoId());
						pss_temp.setType(1);
					}
					pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);

					if (null != pss_temp) {// 基地商品库存
						int cur_invencount = pss_temp.getInventorycount();
						
						pss_temp.setInventorycount(tempCount * -1);
						// 修改库存
						if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
							if (!mapRecordProductInfo.containsKey(pss_temp.getId())) {
								throw new Exception(c.getCompanyName() + "基地，扣减："
										+ product.getName() + "["
										+ cur_invencount
										+ product.getUnit() + "][礼盒]失败，无法修改配送基地");
							} else {
								throw new Exception(c.getCompanyName() + "基地，扣减："
										+ mapRecordProductInfo.get(pss_temp.getId())
										+ "," + product.getName()
										+ "存在冲突导致总购买数量超过系统库存，无法修改配送基地！");
							}
						}else{
							//记录一下该规格
							if(mapRecordProductInfo.containsKey(pss_temp.getId())){
								mapRecordProductInfo.put(pss_temp.getId(), mapRecordProductInfo.get(pss_temp.getId()) +","+product.getName());
							}else{
								mapRecordProductInfo.put(pss_temp.getId(), product.getName());
							}
						}

					} else {
						throw new Exception(c.getCompanyName() + "基地，扣减："
								+ product.getName() + "[礼盒]失败，无法修改配送基地");
					}
				}
			}
			i++;
		}

		if (type == 1) {// 退货基地操作 要增加库存
			order.setCompanyId(tempCompanyId);
			result = updateCompayAddProds(order, nowUser, userType,
					newCompanyId);
		}
		return result;
	}

	/**
	 * 减库存
	 * 
	 * @param prodId
	 * @param companyId
	 * @param count
	 * @param specId
	 * @throws Exception
	 */
	public void substractCompanyStock(String prodId[], Integer companyId,
			String count[], String specId[]) throws Exception {
		/*
		 * String[] prodId = prodIds.split(","); String[] count = null == counts
		 * ? null : counts.split(","); String[] specId = null == specIds ? null
		 * : specIds.split(",");
		 */
		List<Product> p = productMapper.getPackageByProdIds(prodId);
		Map<String, Product> mapProduct = new HashMap<>();
		for (Product product : p) {
			mapProduct.put(product.getId() + "", product);
		}
		// 记录商品规格 库存扣减失败情况下调用
		Map<Integer, String> mapRecordProductInfo = new HashMap<>();
		Map<Integer, String> mapRecordProductInfo2 = new HashMap<>();
		ProductSpecificationInfo productSpecificationInfo = null;
		int i = 0;
		if (null != specId) {
			for (String pid : prodId) {
				Product product = mapProduct.get(pid);

				int tempCount = 0;

				if (null != count) {
					tempCount = Integer.parseInt(count[i]);
				} else {
					throw new Exception("参数错误");
				}

				if (product.getType() == 0) {
					// 普通商品
					if(null == product.getIsSpecification() || 0 == product.getIsSpecification()){
						//该商品没有开启规格
						int cur_invencount = product.getInventorynumber();//该商品当前库存
						product.setInventorynumber(tempCount * -1);
						// 修改库存
						if (productMapper.updateOrderProdDealStock(product) <= 0) {
							if (!mapRecordProductInfo.containsKey(product.getId())) {
								throw new Exception("订单创建失败,原因："
										+ product.getName() + "["
										+ cur_invencount
										+ product.getUnit() + "] 库存不足");

							} else {
								throw new Exception("订单创建失败,原因："
										+ mapRecordProductInfo.get(product.getId())
										+ "," + product.getName()+"["+cur_invencount+product.getUnit()+"]"
										+ "存在冲突导致总购买数量超过系统库存，请分别提交订单！");
							}
						}else{
							//记录一下该规格
							if(mapRecordProductInfo.containsKey(product.getId())){
								mapRecordProductInfo.put(product.getId(), mapRecordProductInfo.get(product.getId()) +","+product.getName()+"["+cur_invencount+product.getUnit()+"]");
							}else{
								mapRecordProductInfo.put(product.getId(), product.getName()+"["+cur_invencount+product.getUnit()+"]");
							}
						}
					}else{
						//开启规格了
						productSpecificationInfo = new ProductSpecificationInfo();
						productSpecificationInfo.setId(Integer.parseInt(specId[i]));
						productSpecificationInfo.setProductId(product.getId());
						productSpecificationInfo.setDelFlag("0");//未删除的
						productSpecificationInfo = productSpecificationInfoMapper.selectByProductSpecificationInfo(productSpecificationInfo);
						if ( productSpecificationInfo != null ) {
							int cur_invencount = productSpecificationInfo.getInventorynumber();//该商品当前库存
							productSpecificationInfo.setInventorynumber(tempCount * -1);
							// 修改库存
							if (productSpecificationInfoMapper.updateKuCunJianNew(productSpecificationInfo) <= 0) {
								if (!mapRecordProductInfo2.containsKey(productSpecificationInfo.getId())) {
									throw new Exception("订单创建失败,原因："
											+ product.getName() + "["
											+ cur_invencount
											+ product.getUnit() + "] 库存不足");

								} else {
									throw new Exception("订单创建失败,原因："
											+ mapRecordProductInfo2.get(productSpecificationInfo.getId())
											+ "," + product.getName()+"["+cur_invencount+product.getUnit()+"]"
											+ "存在冲突导致总购买数量超过系统库存，请分别提交订单！");
								}
							}else{
								//记录一下该规格
								if(mapRecordProductInfo2.containsKey(productSpecificationInfo.getId())){
									mapRecordProductInfo2.put(productSpecificationInfo.getId(), mapRecordProductInfo2.get(productSpecificationInfo.getId()) +","+product.getName()+"["+cur_invencount+product.getUnit()+"]");
								}else{
									mapRecordProductInfo2.put(productSpecificationInfo.getId(), product.getName()+"["+cur_invencount+product.getUnit()+"]");
								}
							}
						} else {
							throw new Exception("订单创建失败，原因：商品 "+product.getName()+" 选择的规格不存在");
						}
					}

				} else {
					// 礼盒包商品
//				List<ProductPackage> pp = product.getProductPackageList();
//				for (ProductPackage productPackage : pp) {
//					pss_temp = new ProductSpecificationStock();
//					pss_temp.setCompanyId(companyId);
//					pss_temp.setProductId(productPackage.getProductId());
//					if(0 == productPackage.getType()){//该商品没有开启规格
//						pss_temp.setSpecificationInfoId(0);
//						pss_temp.setType(0);
//					}else{
//						pss_temp.setSpecificationInfoId(productPackage.getSpecificationInfoId());
//						pss_temp.setType(1);
//					}
//					pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);
//
//					if (null != pss_temp) {// 基地商品库存
//						int cur_invencount = pss_temp.getInventorycount();
//
//						pss_temp.setInventorycount(tempCount * -1);
//						// 修改库存
//						if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
//							if (!mapRecordProductInfo.containsKey(pss_temp.getId())) {
//								throw new Exception("订单创建失败,原因："
//										+ product.getName() + "["
//										+ cur_invencount
//										+ product.getUnit() + "] 库存不足");
//
//							} else {
//								throw new Exception("订单创建失败,原因："
//										+ mapRecordProductInfo.get(pss_temp.getId())
//										+ "," + product.getName()
//										+ "存在冲突导致总购买数量超过系统库存，请分别提交订单！");
//							}
//						}else{
//							//记录一下该规格
//							if(mapRecordProductInfo.containsKey(pss_temp.getId())){
//								mapRecordProductInfo.put(pss_temp.getId(), mapRecordProductInfo.get(pss_temp.getId()) +","+product.getName());
//							}else{
//								mapRecordProductInfo.put(pss_temp.getId(), product.getName());
//							}
//						}
//
//					} else {
//						throw new Exception("订单创建失败，原因：商品不存在");
//					}
//				}

				}
				i++;
			}
		} else {
			throw new Exception("订单创建失败，原因：参数错误");
		}
	}
	

	/**
	 * 要增加库存
	 */
	@Override
	public String updateCompayAddProds(Orders order, Integer nowUser,
			Integer userType, Integer newCompanyId) throws Exception {
		// 退货基地操作 要增加库存

		String result = null;
		OrdersDetail od = new OrdersDetail();
		od.setOrderId(order.getId());
		List<OrdersDetail> odlist = ordersDetailMapper.getAllByOrdersDetail(od);

		String prodIds = "";
		Integer companyId = order.getCompanyId();
		String counts = "";
		String specIds = "";

		for (OrdersDetail odetail : odlist) {
			prodIds += odetail.getProdId() + ",";
			counts += odetail.getProdCount() + ",";
			specIds += odetail.getProdSpecId() + ",";
		}

		// 临时存放
		ProductSpecificationStock pss_temp = null;
		
		String[] prodId = prodIds.split(",");
		String[] count = null == counts ? null : counts.split(",");
		String[] specId = null == specIds ? null : specIds.split(",");
		List<Product> p = productMapper.getPackageByProdIds(prodId);
		Map<String, Product> mapProduct = new HashMap<String, Product>();
		for (Product product : p) {
			mapProduct.put(product.getId() + "", product);
		}
		int i = 0;
		for (String pid : prodId) {
			Product product = mapProduct.get(pid);
			int tempCount = 0;

			if (null != count) {
				tempCount = Integer.parseInt(count[i]);
			} else {
				throw new Exception();
			}

			if (product.getType() == 0) {// 普通商品
				if (null != specId) {// 存在规格id
					pss_temp = new ProductSpecificationStock();
					pss_temp.setCompanyId(companyId);
					pss_temp.setProductId(product.getId());
					if(null == product.getIsSpecification() || 0 == product.getIsSpecification()){//该商品没有开启规格
						pss_temp.setSpecificationInfoId(0);
						pss_temp.setType(0);
					}else{
						pss_temp.setSpecificationInfoId(Integer.parseInt(specId[i]));
						pss_temp.setType(1);
					}
					pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);

					if (null != pss_temp) {// 基地商品库存
						pss_temp.setInventorycount(tempCount * +1);
						// 修改库存
						if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
							throw new Exception("增加：" + product.getName());
						}

					} else {
						throw new Exception("增加：" + product.getName());
					}
				} else {
					result = "由于商品：" + product.getName() + "中规格不存在，无法恢复库存";
				}

			} else {// 礼盒包商品
				
				List<ProductPackage> pp = product.getProductPackageList();
				for (ProductPackage productPackage : pp) {
					pss_temp = new ProductSpecificationStock();
					pss_temp.setCompanyId(companyId);
					pss_temp.setProductId(productPackage.getProductId());
					if(0 == productPackage.getType()){//该商品没有开启规格
						pss_temp.setSpecificationInfoId(0);
						pss_temp.setType(0);
					}else{
						pss_temp.setSpecificationInfoId(productPackage.getSpecificationInfoId());
						pss_temp.setType(1);
					}
					pss_temp = productSpecificationStockMapper.selectByProductSpecificationStock(pss_temp);

					if (null != pss_temp) {// 基地商品库存
						pss_temp.setInventorycount(tempCount * +1);
						// 修改库存
						if (productSpecificationStockMapper.updateOrderProdDeal(pss_temp) <= 0) {
							throw new Exception("增加：" + product.getName()
									+ "[礼盒]");
						}

					} else {
						result = "由于礼盒商品：" + product.getName()
								+ "中部分组合不存在，无法恢复库存";
					}
				
				}
			}
			i++;
		}
		if (p.size() == 0) {
			throw new Exception("商品不存在，取消订单失败！");
		}

		if (null != order.getCouponsRecordId()
				&& order.getCouponsRecordId() != 0) {
			sysCouponsRecordMapper.updateByTimeIsOver(order
					.getCouponsRecordId());// 暂不考虑优惠券，回滚是否成功
		}

		if (userType == 1) {// 用户取消订单
			order.setStatus("0");
			order.setCancelTime(new Date());
			if (ordersMapper.updateByPrimaryKeySelective(order) > 0) {
				OrdersLog ol = new OrdersLog();
				ol.setOperator(nowUser);
				ol.setOrderId(order.getId());
				ol.setChangedStatus("0");
				ol.setRemark("用户取消订单");
				ol.setOrderStatus(order.getStatus());
				ol.setLogTime(Tools.date2Str(new Date()));
				ordersLogMapper.insertSelective(ol);
			}
		} else if (userType == 2) {// 管理员取消订单
			order.setStatus("100");
			order.setCancelTime(new Date());
			if (ordersMapper.updateByPrimaryKeySelective(order) > 0) {
				OrdersLog ol = new OrdersLog();
				ol.setOperator(nowUser);
				ol.setOrderId(order.getId());
				ol.setChangedStatus("100");
				ol.setRemark("管理员取消订单");
				ol.setOrderStatus(order.getStatus());
				ol.setLogTime(Tools.date2Str(new Date()));
				ordersLogMapper.insertSelective(ol);
			}
		} else if (userType == 3) {// 管理员修改配送基地
			// 如果存在那么修改订单归属
			if (null != newCompanyId && newCompanyId != 0) {
				order.setCompanyId(newCompanyId);
				if (ordersMapper.updateByPrimaryKeySelective(order) > 0) {
					OrdersLog ol = new OrdersLog();
					ol.setOperator(nowUser);
					ol.setOrderId(order.getId());
					ol.setChangedStatus(order.getStatus());
					ol.setRemark("管理员修改配送基地");
					ol.setOrderStatus(order.getStatus());
					ol.setLogTime(Tools.date2Str(new Date()));
					ordersLogMapper.insertSelective(ol);
				}
			}
		}

		return result;
	}

	@Override
	public int selectCount(Orders order) {
		// TODO Auto-generated method stub
		return ordersMapper.selectCount(order);
	}

	@Override
	public int selectEvaCount(Orders order) {
		// TODO Auto-generated method stub
		return ordersMapper.selectEvaCount(order);
	}

	public ProductMapper getProductMapper() {
		return productMapper;
	}

	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public OrdersDetailMapper getOrdersDetailMapper() {
		return ordersDetailMapper;
	}

	public void setOrdersDetailMapper(OrdersDetailMapper ordersDetailMapper) {
		this.ordersDetailMapper = ordersDetailMapper;
	}

	public OrdersMapper getOrdersMapper() {
		return ordersMapper;
	}

	public void setOrdersMapper(OrdersMapper ordersMapper) {
		this.ordersMapper = ordersMapper;
	}

	public OrdersReturnMapper getReturnOrderMapper() {
		return returnOrderMapper;
	}

	public void setReturnOrderMapper(OrdersReturnMapper returnOrderMapper) {
		this.returnOrderMapper = returnOrderMapper;
	}

	public OrdersLogMapper getOrdersLogMapper() {
		return ordersLogMapper;
	}

	public void setOrdersLogMapper(OrdersLogMapper ordersLogMapper) {
		this.ordersLogMapper = ordersLogMapper;
	}
}