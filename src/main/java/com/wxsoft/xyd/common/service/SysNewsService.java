package com.wxsoft.xyd.common.service;

import java.util.List;

import com.wxsoft.xyd.common.model.SysNews;

/**
 * @文件名称: SysNewsService.java
 * @类路径: com.wxsoft.xyd.common.service.SysNewsService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 11:19:22
 */
public interface  SysNewsService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysNews record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysNews record);

	// 根据主键查询
	SysNews selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysNews selectBySysNews(SysNews record);

	// 分页获取
	List<SysNews> listPageBySysNews(SysNews clssname);
	
	// 获取全部
	List<SysNews> getAllBySysNews(SysNews clssname);
}