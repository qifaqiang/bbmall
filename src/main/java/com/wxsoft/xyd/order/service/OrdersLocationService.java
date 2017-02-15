package com.wxsoft.xyd.order.service;

import java.util.List;

import com.wxsoft.xyd.order.model.OrdersLocation;

/**
 * 订单收货地址Servcie
 * 
 * @author kyz
 * 
 */
public interface OrdersLocationService {
	int deleteByPrimaryKey(Integer id);

	int insert(OrdersLocation record);

	int insertSelective(OrdersLocation record);

	int updateByPrimaryKeySelective(OrdersLocation record);

	OrdersLocation selectByPrimaryKey(Integer id);

	OrdersLocation selectByOrdersLocation(OrdersLocation record);

	List<OrdersLocation> listPageByOrdersLocation(OrdersLocation clssname);

	List<OrdersLocation> getAllByOrdersLocation(OrdersLocation clssname);
}