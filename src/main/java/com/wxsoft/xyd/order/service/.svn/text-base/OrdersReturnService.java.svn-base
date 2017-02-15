package com.wxsoft.xyd.order.service;

import java.util.List;

import com.wxsoft.xyd.order.model.OrdersReturn;

/**
 * 退款订单 service
 * 
 * @author kyz
 * 
 */
public interface OrdersReturnService {
	int deleteByPrimaryKey(Integer id);

	int insert(OrdersReturn record);

	int insertSelective(OrdersReturn record, String status);

	int updateByPrimaryKeySelective(OrdersReturn record);

	OrdersReturn selectByPrimaryKey(Integer id);

	OrdersReturn selectByOrdersReturn(OrdersReturn record);

	List<OrdersReturn> listPageByOrdersReturn(OrdersReturn clssname);

	List<OrdersReturn> getAllByOrdersReturn(OrdersReturn clssname);

}