package com.wxsoft.xyd.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.order.mapper.OrdersMapper;
import com.wxsoft.xyd.order.model.Orders;
import com.wxsoft.xyd.order.service.OrdersBranchService;

/**
 * 订单cx处理
 * @author kyz
 *
 */
@Service("ordersBranchService")
public class OrdersBranchServiceImpl implements OrdersBranchService {
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public List<Map<String,Object>> selectByOrderstatus(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByOrderstatus(id);
	}
	@Override
	public List<Map<String, Object>> listAjaxPageselectOrderIds(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.listAjaxPageselectOrderIds(clssname);
	}
	@Override
	public List<Map<String, Object>> selectOrderDt(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectOrderDt(id);
	}
	@Override
	public List<Map<String, Object>> selectOrderDtzero(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectOrderDtzero(id);
	}
	@Override
	public String selectCompanName(Integer id) {
		// TODO Auto-generated method stub
		return ordersMapper.selectCompanName(id);
		
		//删除订单之前先删除orderDetail   删除
		
	}
	@Override
	public Orders selectByBKOrderInfo(Orders record) {
		// TODO Auto-generated method stub
		return ordersMapper.selectByBKOrderInfo(record);
	}
	@Override
	public List<Map<String, Object>> selectOrderlocById(Orders clssname) {
		// TODO Auto-generated method stub
		return ordersMapper.selectOrderlocById(clssname);
	}
	@Override
	public List<Map<String, Object>> listAjaxPageOrderlocById(Orders clssname) {
		// TODO Auto-generated method stub
		return   ordersMapper.listAjaxPageOrderlocById(clssname);
	}
}