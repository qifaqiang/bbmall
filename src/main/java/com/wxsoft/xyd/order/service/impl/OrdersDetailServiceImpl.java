package com.wxsoft.xyd.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.order.mapper.OrdersDetailMapper;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.order.service.OrdersDetailService;

/**
 * 订单详情处理
 * @author kyz
 *
 */
@Service("ordersDetailService")
public class OrdersDetailServiceImpl implements OrdersDetailService {
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ordersDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrdersDetail record) {
		return ordersDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(OrdersDetail record) {
		return ordersDetailMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrdersDetail record) {
		return ordersDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public OrdersDetail selectByPrimaryKey(Integer id) {
		return ordersDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public OrdersDetail selectByOrdersDetail(OrdersDetail record) {
		return ordersDetailMapper.selectByOrdersDetail(record);
	}

	@Override
	public List<OrdersDetail> listPageByOrdersDetail(OrdersDetail clssname) {
		return ordersDetailMapper.listPageByOrdersDetail(clssname);
	}

	@Override
	public List<OrdersDetail> getAllByOrdersDetail(OrdersDetail clssname) {
		return ordersDetailMapper.getAllByOrdersDetail(clssname);
	}
}