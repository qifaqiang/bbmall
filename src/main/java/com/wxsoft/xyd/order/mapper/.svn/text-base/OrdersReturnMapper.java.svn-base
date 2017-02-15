package com.wxsoft.xyd.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.order.model.OrdersReturn;

/**
 * @文件名称: OrdersReturnMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-03-01 23:04:56
 */
@Repository
public interface OrdersReturnMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(OrdersReturn record);

	// 根据所需插入
	int insertSelective(OrdersReturn record);

	// 根据所需更新
	int updateByPrimaryKeySelective(OrdersReturn record);

	// 根据主键查询
	OrdersReturn selectByPrimaryKey(Integer id);

	OrdersReturn selectByOrderId(Integer id);

	// 根据所需查询
	OrdersReturn selectByOrdersReturn(OrdersReturn record);

	// 分页获取
	List<OrdersReturn> listPageByOrdersReturn(OrdersReturn clssname);

	// 获取全部
	List<OrdersReturn> getAllByOrdersReturn(OrdersReturn clssname);
}