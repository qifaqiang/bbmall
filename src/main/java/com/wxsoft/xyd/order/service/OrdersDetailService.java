package com.wxsoft.xyd.order.service;

import java.util.List;

import com.wxsoft.xyd.order.model.OrdersDetail;

/**
 * 订单详情 Service
 * 
 * @author kyz
 * 
 */
public interface OrdersDetailService {
	int deleteByPrimaryKey(Integer id);

	int insert(OrdersDetail record);

	int insertSelective(OrdersDetail record);

	int updateByPrimaryKeySelective(OrdersDetail record);

	OrdersDetail selectByPrimaryKey(Integer id);

	OrdersDetail selectByOrdersDetail(OrdersDetail record);

	List<OrdersDetail> listPageByOrdersDetail(OrdersDetail clssname);

	List<OrdersDetail> getAllByOrdersDetail(OrdersDetail clssname);
}