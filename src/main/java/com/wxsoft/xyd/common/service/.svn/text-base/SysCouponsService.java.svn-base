package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.system.model.SysLog;

/**
 * @文件名称: SysCouponsService.java
 * @类路径: com.wxsoft.xyd.common.service.SysCouponsService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-21 10:43:11
 */
public interface SysCouponsService {
	// 根据主键删除
	int deleteByPrimaryKey(SysCoupons record, SysLog syslog);

	// 根据所需插入
	int insertSelective(SysCoupons record, SysLog syslog);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysCoupons record, SysLog syslog);

	// 根据主键查询
	SysCoupons selectByPrimaryKey(Integer id);

	SysCoupons selectByPrimaryKeyCount(Integer id);

	int updateCount(SysCoupons record);

	// 根据所需查询
	SysCoupons selectBySysCoupons(SysCoupons record);

	// 分页获取
	List<SysCoupons> listPageBySysCoupons(SysCoupons clssname);

	List<Map<String, Object>> listAjaxPageSysCoupons(SysCoupons clssname);

	List<Map<String, Object>> listAjaxPageCoupons(SysCoupons clssname);

	// 获取全部
	List<SysCoupons> getAllBySysCoupons(SysCoupons clssname);
}