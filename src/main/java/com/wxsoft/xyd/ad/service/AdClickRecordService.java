package com.wxsoft.xyd.ad.service;

import java.util.List;

import com.wxsoft.xyd.ad.model.AdClickRecord;

/**
 * @文件名称: AdClickRecordService.java
 * @类路径: com.dhct.background.ad.service.AdClickRecordService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-10-27 09:11:09
 */
public interface  AdClickRecordService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(AdClickRecord record);

	// 根据所需更新
	int updateByPrimaryKeySelective(AdClickRecord record);

	// 根据主键查询
	AdClickRecord selectByPrimaryKey(Integer id);

	// 根据所需查询
	AdClickRecord selectByAdClickRecord(AdClickRecord record);

	// 分页获取
	List<AdClickRecord> getPageListAdClickRecord(AdClickRecord clssname);

	// 获取全部
	List<AdClickRecord> getAllByAdClickRecord(AdClickRecord clssname);
}