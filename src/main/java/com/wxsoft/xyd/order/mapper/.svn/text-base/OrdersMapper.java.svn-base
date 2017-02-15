package com.wxsoft.xyd.order.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.order.model.Orders;

/**
 * @文件名称: OrdersMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-25 09:56:19
 */
@Repository
public interface OrdersMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(Orders record);

	// 根据所需插入
	int insertSelective(Orders record);

	// 根据所需更新
	int updateByPrimaryKeySelective(Orders record);

	// 根据主键查询
	Orders selectByPrimaryKey(Integer id);

	// 根据订单信息查询订单
	Orders selectByOrderInfo(Orders record);

	// 根据所需查询
	Orders selectByOrders(Orders record);

	// 分页获取
	List<Orders> listPageByOrders(Orders clssname);

	// 分页返回申请退款处理订单
	List<Orders> listPageByOrdersReturn(Orders clssname);

	// 查询申请退款订单
	List<Orders> selectByOrderBack(Orders order);

	// 返回申请退款订单后台获取
	List<Orders> selectByReturnOrderBack(Orders order);

	// 获取全部
	List<Orders> getAllByOrders(Orders clssname);

	// 申请退款订单详情
	List<Orders> getAllByReturnOrdersInfo(Orders clssname);

	// 获取所有订单
	List<Orders> getAllByOrdersInfo(Orders clssname);

	// 获取所有未发货的订单
	List<Orders> getAllByOrdersNoShip(Orders clssname);

	// 获取所有已发货的订单
	List<Orders> getAllByOrdersWithShip(Orders clssname);

	// 返回申请退货的订单详情
	Orders selectByReturnOrderInfo(Orders record);

	// 返回申请退货订单的提货码
	List<Orders> getAllByOrdersWithTradingCode(String sn);

	// 获得所有的预售订单
	List<Orders> getAllBKByOrders(Orders clssname);

	// 返回订单状态
	List<Map<String, Object>> selectByOrderstatus(Integer id);

	// 获取预售订单详情
	Orders selectByBKOrderInfo(Orders record);

	// 获取用户商品
	int selectByUserAndProductId(Map<String, String> maps);

	// 查询订单信息
	List<Map<String, Object>> listAjaxPageselectOrderIds(Orders clssname);

	// 查询订单详情表的信息
	List<Map<String, Object>> selectOrderDt(Integer id);

	List<Map<String, Object>> selectOrderDtzero(Integer id);

	// 查询基地名称
	String selectCompanName(Integer id);

	// 获取订单信息map
	List<Map<String, Object>> selectOrderlocById(Orders clssname);

	// 分页获取订单信息
	List<Map<String, Object>> listAjaxPageOrderlocById(Orders clssname);

	// 根据订单返回数量
	int selectCount(Orders order);

	// 根据订单返回数量
	int selectEvaCount(Orders order);
}