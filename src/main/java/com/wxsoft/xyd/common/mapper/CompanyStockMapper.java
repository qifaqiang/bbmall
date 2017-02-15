package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.CompanyStock;

;

/**
 * @文件名称: CompanyStockMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.CompanyStockMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-30 11:09:04
 */
@Repository
public interface CompanyStockMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(CompanyStock record);

	// 根据所需更新
	int updateByPrimaryKeySelective(CompanyStock record);

	// 根据所需增加库存
	int updateSelective(CompanyStock record);

	int updateOrderDeal(CompanyStock record);

	// 根据主键查询
	CompanyStock selectByPrimaryKey(Integer id);

	// 根据所需查询
	CompanyStock selectByCompanyStock(CompanyStock record);

	// 分页获取
	List<CompanyStock> listPageByCompanyStock(CompanyStock clssname);

	// 获取全部
	List<CompanyStock> getAllByCompanyStock(CompanyStock clssname);

	// 根据基地商户ID查询
	List<CompanyStock> selectByCompanyKey(Integer company_id);

	List<CompanyStock> selectByCompanyStockMaps(
			Map<String, Object> params);
}