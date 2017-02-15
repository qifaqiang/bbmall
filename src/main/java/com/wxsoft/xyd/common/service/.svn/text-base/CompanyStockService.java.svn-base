package com.wxsoft.xyd.common.service;

import java.util.List;
import com.wxsoft.xyd.common.model.CompanyStock;;

/**
 * @文件名称: CompanyStockService.java
 * @类路径: com.wxsoft.xyd.common.service.CompanyStockService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-30 11:09:04
 */
public interface  CompanyStockService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(CompanyStock record);

	// 根据所需更新
	int updateByPrimaryKeySelective(CompanyStock record);

	// 根据主键查询
	CompanyStock selectByPrimaryKey(Integer id);

	// 根据所需查询
	CompanyStock selectByCompanyStock(CompanyStock record);

	// 分页获取
	List<CompanyStock> listPageByCompanyStock(CompanyStock clssname);

	// 获取全部
	List<CompanyStock> getAllByCompanyStock(CompanyStock clssname);
	
	// 根据基地商户的ID查询
	List<CompanyStock> selectByCompanyKey(Integer company_id);
	
	// 根据所需增加库存
	int updateSelective(CompanyStock record);
}