package com.wxsoft.xyd.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersReturn;

/**
 * 订单Service
 * @author kyz
 *
 */
public interface OrdersService {
	int deleteByPrimaryKey(Integer id);

	int insert(Orders record);

	Map<String, Object> insertAddOrder(String cartItems, Integer companyId,
			Integer shipAddressId, Integer userShipType,
			Integer sysCoupRecordId, String remark, Integer payType,
			Integer userId, boolean isfirst)  throws Exception;

	int insertSelective(Orders record);

	int updateByPrimaryKeySelective(Orders record);

	int updateByPayNow(Orders record, Integer payType);

	Orders selectByPrimaryKey(Integer id);

	Orders selectByOrders(Orders record);

	Orders selectByReturnOrderInfo(Orders record);

	List<Orders> listPageByOrders(Orders clssname);

	List<Orders> getAllByOrders(Orders clssname);

	// 查询订单的状态信息status List<Map<String, Object>> listPageByOrderstatus(Orders
	// clssname);

	// 获得所有的预售订单
	List<Orders> getAllBKByOrders(Orders clssname);

	Orders selectByOrderInfo(Orders record);

	// 获取预售订单详情
	Orders selectByBKOrderInfo(Orders record);

	// 申请退款
	int updateByOrderReturn(Orders order, String remark);

	// 申请退款
	String updateByOrderReturnSystem(Orders order, OrdersReturn remark)
			throws Exception;

	// 发货
	int updateByFaHuo(Orders record, Integer loginuser);

	// 接单
	int updateByJieDan(Orders record, Integer loginuser);

	// 判断基地是否能接单
	String updateCompaySubtractProds(Orders order, Integer nowUser,
			Integer userType, Integer type, Integer newCompanyId)
			throws Exception;

	// 判断基地取消订单处理
	String updateCompayAddProds(Orders od, Integer nowUser, Integer userType,
			Integer newCompanyId) throws Exception;

	// 设置订单编号
	int updateByUpdateShip(Orders record, Integer loginuser);

	// 修改订单价格
	int updateByUpdatePrice(Orders record, Integer loginuser,
			BigDecimal oldOrderPrice, BigDecimal oldShipPrice,
			BigDecimal orderAccount);

	// 返回后台订单信息
	List<Orders> selectByOrderBack(Orders order);

	// 返回后台退款订单处理
	List<Orders> selectByReturnOrderBack(Orders order);

	// 分页返回订单处理
	List<Orders> listPageByOrdersReturn(Orders clssname);

	// 返回订单 退款的详情
	List<Orders> getAllByReturnOrdersInfo(Orders clssname);

	// 获取所有订单信息
	List<Orders> getAllByOrdersInfo(Orders clssname);

	// 获取所有未发货的订单信息
	List<Orders> getAllByOrdersNoShip(Orders clssname);

	// 获取所有已发货的订单信息
	List<Orders> getAllByOrdersWithShip(Orders clssname);

	// 获取所有带有自提吗的订单信息
	List<Orders> getAllByOrdersWithTradingCode(String sn);

	int selectByUserAndProductId(Map<String, String> maps);

	//查询订单数量
	int selectCount(Orders order);

	int selectEvaCount(Orders order);

}