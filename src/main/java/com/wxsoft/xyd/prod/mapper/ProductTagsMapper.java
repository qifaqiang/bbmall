package com.wxsoft.xyd.prod.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductTags;

/**
 * @文件名称: ProductTagsMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.ProductTagsMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-23 10:25:29
 */
@Repository
public interface  ProductTagsMapper {
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