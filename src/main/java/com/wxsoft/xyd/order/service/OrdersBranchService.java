package com.wxsoft.xyd.order.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.order.model.Orders;

public interface OrdersBranchService {

	List<Map<String, Object>> selectByOrderstatus(Integer id);

	// 按需查询用的信息
	List<Map<String, Object>> listAjaxPageselectOrderIds(Orders clssname);

	List<Map<String, Object>> selectOrderDt(Integer id);

	List<Map<String, Object>> selectOrderDtzero(Integer id);

	Orders selectByBKOrderInfo(Orders record);

	String selectCompanName(Integer id);

	// 查询 orders 和 orderLocal表的信息
	List<Map<String, Object>> selectOrderlocById(Orders clssname);

	List<Map<String, Object>> listAjaxPageOrderlocById(Orders clssname);

}