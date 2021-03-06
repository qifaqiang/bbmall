package com.wxsoft.xyd.common.service;

import java.util.List;
import com.wxsoft.xyd.common.model.CompanyStockinRecord;;

/**
 * @文件名称: CompanyStockinRecordService.java
 * @类路径: com.wxsoft.xyd.common.service.CompanyStockinRecordService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-02 09:54:53
 */
public interface  CompanyStockinRecordService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(CompanyStockinRecord record);

	// 根据所需更新
	int updateByPrimaryKeySelective(CompanyStockinRecord record);

	// 根据主键查询
	CompanyStockinRecord selectByPrimaryKey(Integer id);

	// 根据所需查询
	CompanyStockinRecord selectByCompanyStockinRecord(CompanyStockinRecord record);

	// 分页获取
	List<CompanyStockinRecord> listPageByCompanyStockinRecord(CompanyStockinRecord clssname);

	// 获取全部
	List<CompanyStockinRecord> getAllByCompanyStockinRecord(CompanyStockinRecord clssname);
}