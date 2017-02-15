package com.wxsoft.xyd.prod.service;

import java.util.List;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;;

/**
 * @文件名称: ProductSpecificationStockService.java
 * @类路径: com.wxsoft.xyd.prod.service.ProductSpecificationStockService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-09 14:35:08
 */
public interface  ProductSpecificationStockService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductSpecificationStock record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductSpecificationStock record);

	// 根据主键查询
	ProductSpecificationStock selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductSpecificationStock selectByProductSpecificationStock(ProductSpecificationStock record);
	
	ProductSpecificationStock selectProdTotalStock(ProductSpecificationStock record);

	// 分页获取
	List<ProductSpecificationStock> listPageByProductSpecificationStock(ProductSpecificationStock clssname);

	// 获取全部
	List<ProductSpecificationStock> getAllByProductSpecificationStock(ProductSpecificationStock clssname);
	
	// 进货操作  增加库存
	int updateProdStock(ProductSpecificationStock record);
}