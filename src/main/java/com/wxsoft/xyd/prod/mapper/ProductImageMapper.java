package com.wxsoft.xyd.prod.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductImage;

/**
 * @文件名称: ProductImageMapper.java
 * @类路径: com/wxltsoft/schoolmgr/system/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-02 20:03:59
 */
@Repository
public interface ProductImageMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(ProductImage record);

	int deleteByProdId(Integer prodId);

	// 根据所需插入
	int insertSelective(ProductImage record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductImage record);

	// 根据主键查询
	ProductImage selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductImage selectByProductImage(ProductImage record);

	// 分页获取
	List<ProductImage> listPageByProductImage(ProductImage clssname);

	// 获取全部
	List<ProductImage> getAllByProductImage(Integer prodId);

	List<String> getAllByProductImageRMap(Integer prodId);

}