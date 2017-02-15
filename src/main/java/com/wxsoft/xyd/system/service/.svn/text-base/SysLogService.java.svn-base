package com.wxsoft.xyd.system.service;

import java.util.List;
import com.wxsoft.xyd.system.model.SysLog;;

/**
 * @文件名称: SysLogService.java
 * @类路径: com.wxsoft.xyd.system.service.SysLogService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-21 14:34:53
 */
public interface  SysLogService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysLog record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysLog record);

	// 根据主键查询
	SysLog selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysLog selectBySysLog(SysLog record);

	// 分页获取
	List<SysLog> listPageBySysLog(SysLog clssname);

	// 获取全部
	List<SysLog> getAllBySysLog(SysLog clssname);
}