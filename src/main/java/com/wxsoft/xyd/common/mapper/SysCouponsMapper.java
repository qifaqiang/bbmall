package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.SysCoupons;;

/**
 * @文件名称: SysCouponsMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.SysCouponsMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-21 10:43:11
 */
@Repository
public interface  SysCouponsMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);
	List<Map<String, Object>> listAjaxPageCoupons(SysCoupons clssname);
//得到所有的用户优惠券
	List<Map<String, Object>> listAjaxPageSysCoupons(SysCoupons clssname);
	// 根据所需插入
	int insertSelective(SysCoupons record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysCoupons record);
	
	// 当领取优惠券时候优惠券总数减1
	int updateCount(SysCoupons record);

	// 根据主键查询
	SysCoupons selectByPrimaryKey(Integer id);
	// 根据主键查询
	SysCoupons selectByPrimaryKeyCe(Integer id);

	// 根据所需查询
	SysCoupons selectBySysCoupons(SysCoupons record);

	// 分页获取
	List<SysCoupons> listPageBySysCoupons(SysCoupons clssname);
   
	// 获取全部
	List<SysCoupons> getAllBySysCoupons(SysCoupons clssname);
}