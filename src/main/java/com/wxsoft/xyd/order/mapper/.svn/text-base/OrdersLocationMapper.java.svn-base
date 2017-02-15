package com.wxsoft.xyd.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.order.model.OrdersLocation;

/**
 * @文件名称: OrdersLocationMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-03-02 16:45:46
 */
@Repository
public interface OrdersLocationMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(OrdersLocation record);

	// 根据所需插入
	int insertSelective(OrdersLocation record);

	// 根据所需更新
	int updateByPrimaryKeySelective(OrdersLocation record);

	// 根据主键查询
	OrdersLocation selectByPrimaryKey(Integer id);

	// 根据所需查询
	OrdersLocation selectByOrdersLocation(OrdersLocation record);

	// 分页获取
	List<OrdersLocation> listPageByOrdersLocation(OrdersLocation clssname);

	// 获取全部
	List<OrdersLocation> getAllByOrdersLocation(OrdersLocation clssname);
}