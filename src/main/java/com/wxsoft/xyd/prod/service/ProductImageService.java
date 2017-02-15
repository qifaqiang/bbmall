package com.wxsoft.xyd.prod.service;

import java.util.List;

import com.wxsoft.xyd.prod.model.ProductImage;

public interface ProductImageService {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductImage record);

	int insertSelective(ProductImage record);

	int updateByPrimaryKeySelective(ProductImage record);

	ProductImage selectByPrimaryKey(Integer id);

	ProductImage selectByProductImage(ProductImage record);

	List<ProductImage> listPageByProductImage(ProductImage clssname);

	List<ProductImage> getAllByProductImage(Integer prodId);

	List<String> getAllByProductImageRMap(Integer prodId);
}