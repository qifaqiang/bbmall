package com.wxsoft.xyd.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.order.mapper.OrdersLogMapper;
import com.wxsoft.xyd.order.mapper.OrdersMapper;
import com.wxsoft.xyd.order.mapper.OrdersReturnMapper;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.model.OrdersLog;
import com.wxsoft.xyd.order.model.OrdersReturn;
import com.wxsoft.xyd.order.service.OrdersReturnService;

/**
 * 退款处理
 * @author kyz
 *
 */
@Service("ordersReturnService")
public class OrdersReturnServiceImpl implements OrdersReturnService {
	@Autowired
	private OrdersReturnMapper OrdersReturnMapper;
	@Autowired
	private OrdersLogMapper ordersLogMapper;

	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return OrdersReturnMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrdersReturn record) {
		return OrdersReturnMapper.insert(record);
	}

	@Override
	public int insertSelective(OrdersReturn record,String status) {
		OrdersLog ordersLog = new OrdersLog();// 订单日志
		ordersLog.setOrderId(record.getOrderId());
		ordersLog.setOperator(record.getUserId());// 操作人
		ordersLog.setOrderStatus(status);
		ordersLog.setChangedStatus("50");
		ordersLog.setRemark("退款申请");
		ordersLog.setLogTime(Tools.date2Str(new Date()));
		ordersLogMapper.insert(ordersLog);
		OrdersReturnMapper.insertSelective(record);
		// 插入之后要修改Orders表里的状态
		Orders ord = new Orders();
		ord.setId(record.getOrderId());
		ord.setStatus("50");
		return ordersMapper.updateByPrimaryKeySelective(ord);
	}

	@Override
	public int updateByPrimaryKeySelective(OrdersReturn record) {

		return OrdersReturnMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public OrdersReturn selectByPrimaryKey(Integer id) {
		return OrdersReturnMapper.selectByPrimaryKey(id);
	}

	@Override
	public OrdersReturn selectByOrdersReturn(OrdersReturn record) {
		return OrdersReturnMapper.selectByOrdersReturn(record);
	}

	@Override
	public List<OrdersReturn> listPageByOrdersReturn(OrdersReturn clssname) {
		return OrdersReturnMapper.listPageByOrdersReturn(clssname);
	}

	@Override
	public List<OrdersReturn> getAllByOrdersReturn(OrdersReturn clssname) {
		return OrdersReturnMapper.getAllByOrdersReturn(clssname);
	}
}