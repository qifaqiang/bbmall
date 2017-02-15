package com.wxsoft.xyd.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.order.mapper.OrdersLocationMapper;
import com.wxsoft.xyd.order.model.OrdersLocation;
import com.wxsoft.xyd.order.service.OrdersLocationService;

/**
 * 订单收货地址处理
 * @author kyz
 *
 */
@Service("ordersLocationService")
public class OrdersLocationServiceImpl implements OrdersLocationService {
	@Autowired
	private OrdersLocationMapper ordersLocationMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ordersLocationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrdersLocation record) {
		return ordersLocationMapper.insert(record);
	}

	@Override
	public int insertSelective(OrdersLocation record) {
		return ordersLocationMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrdersLocation record) {
		return ordersLocationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public OrdersLocation selectByPrimaryKey(Integer id) {
		return ordersLocationMapper.selectByPrimaryKey(id);
	}

	@Override
	public OrdersLocation selectByOrdersLocation(OrdersLocation record) {
		return ordersLocationMapper.selectByOrdersLocation(record);
	}

	@Override
	public List<OrdersLocation> listPageByOrdersLocation(OrdersLocation clssname) {
		return ordersLocationMapper.listPageByOrdersLocation(clssname);
	}

	@Override
	public List<OrdersLocation> getAllByOrdersLocation(OrdersLocation clssname) {
		return ordersLocationMapper.getAllByOrdersLocation(clssname);
	}
}