package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductCatalog;

/**
 * @文件名称: ProductCatalogMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.ProductCatalogMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-19 10:41:05
 */
@Repository
public interface ProductCatalogMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductCatalog record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductCatalog record);

	// 根据主键查询
	ProductCatalog selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductCatalog selectByProductCatalog(ProductCatalog record);

	// 分页获取
	List<ProductCatalog> listPageByProductCatalog(ProductCatalog clssname);

	// 获取全部
	List<ProductCatalog> getAllByProductCatalog(ProductCatalog clssname);

	List<Map<String, Object>> getAllByProductCatalogIndexNavigation(
			ProductCatalog clssname);

	List<Map<String, Object>> getAllByProductCatalogIndexRecommend(
			ProductCatalog clssname);

	List<Map<String, Object>> getAllByProductCatalogFront(
			ProductCatalog clssname);
}
