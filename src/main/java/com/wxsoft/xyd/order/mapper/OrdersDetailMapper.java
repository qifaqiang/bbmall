package com.wxsoft.xyd.order.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.order.model.OrdersDetail;

/**
 * @文件名称: OrdersDetailMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-21 21:07:30
 */
@Repository
public interface OrdersDetailMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(OrdersDetail record);

	// 根据所需插入
	int insertSelective(OrdersDetail record);

	// 根据所需更新
	int updateByPrimaryKeySelective(OrdersDetail record);

	// 根据主键查询
	OrdersDetail selectByPrimaryKey(Integer id);

	// 根据所需查询
	OrdersDetail selectByOrdersDetail(OrdersDetail record);

	// 分页获取
	List<OrdersDetail> listPageByOrdersDetail(OrdersDetail clssname);

	// 获取全部
	List<OrdersDetail> getAllByOrdersDetail(OrdersDetail clssname);

	//获取所有订单详情，根据订单id
	List<OrdersDetail> getAllByOrdersId(Integer orderId);

	// 获取订单详情列表 带商品类型
	List<OrdersDetail> getAll2ByOrdersId(Integer orderId);
}