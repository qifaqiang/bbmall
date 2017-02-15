package com.wxsoft.xyd.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.order.mapper.OrdersLogMapper;
import com.wxsoft.xyd.order.model.OrdersLog;
import com.wxsoft.xyd.order.service.OrdersLogService;

/**+
 * 订单日志处理
 * @author kyz
 *
 */
@Service("ordersLogService")
public class OrdersLogServiceImpl implements OrdersLogService {
	@Autowired
	private OrdersLogMapper ordersLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ordersLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrdersLog record) {
		return ordersLogMapper.insert(record);
	}

	@Override
	public int insertSelective(OrdersLog record) {
		return ordersLogMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrdersLog record) {
		return ordersLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public OrdersLog selectByPrimaryKey(Integer id) {
		return ordersLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public OrdersLog selectByOrdersLog(OrdersLog record) {
		return ordersLogMapper.selectByOrdersLog(record);
	}

	@Override
	public List<OrdersLog> listPageByOrdersLog(OrdersLog clssname) {
		return ordersLogMapper.listPageByOrdersLog(clssname);
	}

	@Override
	public List<OrdersLog> getAllByOrdersLog(OrdersLog clssname) {
		return ordersLogMapper.getAllByOrdersLog(clssname);
	}

	@Override
	public List<OrdersLog> getAllByOrdersLogMap(OrdersLog clssname) {
		return ordersLogMapper.getAllByOrdersLogMap(clssname);
	}
}