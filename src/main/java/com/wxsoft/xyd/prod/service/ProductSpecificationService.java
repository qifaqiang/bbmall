package com.wxsoft.xyd.prod.service;

import java.util.List;

import com.wxsoft.xyd.prod.model.ProductSpecification;

public interface ProductSpecificationService {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductSpecification record);

	int insertSelective(ProductSpecification record,String[] detailNames, Integer[] skus);

	int updateByPrimaryKeySelective(ProductSpecification record,String[] detailNames, Integer[] skus);

	ProductSpecification selectByPrimaryKey(Integer id);

	ProductSpecification selectByProductSpecification(
			ProductSpecification record);

	List<ProductSpecification> listPageByProductSpecification(
			ProductSpecification clssname);

	List<ProductSpecification> getAllByProductSpecification(
			ProductSpecification clssname);
}