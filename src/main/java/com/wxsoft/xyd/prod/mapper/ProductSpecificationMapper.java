package com.wxsoft.xyd.prod.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductSpecification;

/**
 * @文件名称: ProductSpecificationMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-16 10:40:23
 */
@Repository
public interface ProductSpecificationMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(ProductSpecification record);

	// 根据所需插入
	int insertSelective(ProductSpecification record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductSpecification record);

	// 根据主键查询
	ProductSpecification selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductSpecification selectByProductSpecification(
			ProductSpecification record);

	// 分页获取
	List<ProductSpecification> listPageByProductSpecification(
			ProductSpecification clssname);

	// 获取全部
	List<ProductSpecification> getAllByProductSpecification(
			ProductSpecification clssname);
}