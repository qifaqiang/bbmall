package com.wxsoft.xyd.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.order.model.OrdersLog;

/**
 * @文件名称: OrdersLogMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-03-02 16:23:30
 */
@Repository
public interface OrdersLogMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(OrdersLog record);

	// 根据所需插入
	int insertSelective(OrdersLog record);

	// 根据所需更新
	int updateByPrimaryKeySelective(OrdersLog record);

	// 根据主键查询
	OrdersLog selectByPrimaryKey(Integer id);

	// 根据所需查询
	OrdersLog selectByOrdersLog(OrdersLog record);

	// 分页获取
	List<OrdersLog> listPageByOrdersLog(OrdersLog clssname);

	// 获取全部
	List<OrdersLog> getAllByOrdersLog(OrdersLog clssname);

	List<OrdersLog> getAllByOrdersLogMap(OrdersLog clssname);
}