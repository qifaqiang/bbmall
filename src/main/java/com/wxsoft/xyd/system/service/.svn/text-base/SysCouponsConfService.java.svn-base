package com.wxsoft.xyd.system.service;

import java.util.List;

import com.wxsoft.xyd.system.model.SysCouponsConf;
import com.wxsoft.xyd.system.model.SysLog;

/**
 * @文件名称: SysCouponsConfService.java
 * @类路径: com.wxsoft.xyd.system.service.SysCouponsConfService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-30 12:12:22
 */
public interface  SysCouponsConfService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysCouponsConf record, SysLog syslog);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysCouponsConf record, SysLog syslog);

	// 根据主键查询
	SysCouponsConf selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysCouponsConf selectBySysCouponsConf(SysCouponsConf record);

	// 分页获取
	List<SysCouponsConf> listPageBySysCouponsConf(SysCouponsConf clssname);

	// 获取全部
	List<SysCouponsConf> getAllBySysCouponsConf(SysCouponsConf clssname);
}