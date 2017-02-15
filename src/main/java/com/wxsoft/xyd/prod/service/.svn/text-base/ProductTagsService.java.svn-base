package com.wxsoft.xyd.prod.service;

import java.util.List;

import com.wxsoft.xyd.prod.model.ProductTags;

/**
 * @文件名称: ProductTagsService.java
 * @类路径: com.wxsoft.xyd.common.service.ProductTagsService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-23 10:25:29
 */
public interface  ProductTagsService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductTags record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductTags record);

	// 根据主键查询
	ProductTags selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductTags selectByProductTags(ProductTags record);

	// 分页获取
	List<ProductTags> listPageByProductTags(ProductTags clssname);

	// 获取全部
	List<ProductTags> getAllByProductTags(ProductTags clssname);
}