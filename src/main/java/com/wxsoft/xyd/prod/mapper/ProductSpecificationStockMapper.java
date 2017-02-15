package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductSpecificationStock;;

/**
 * @文件名称: ProductSpecificationStockMapper.java
 * @类路径: com.wxsoft.xyd.prod.mapper.ProductSpecificationStockMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-09 14:35:08
 */
@Repository
public interface  ProductSpecificationStockMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductSpecificationStock record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductSpecificationStock record);
	
	//更新库存
	int updateOrderProdDeal(ProductSpecificationStock record);

	// 根据主键查询
	ProductSpecificationStock selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductSpecificationStock selectByProductSpecificationStock(ProductSpecificationStock record);
	
	ProductSpecificationStock selectProdTotalStock(ProductSpecificationStock record);

	// 分页获取
	List<ProductSpecificationStock> listPageByProductSpecificationStock(ProductSpecificationStock clssname);

	// 获取全部
	List<ProductSpecificationStock> getAllByProductSpecificationStock(ProductSpecificationStock clssname);
	
	int updateProdStock(ProductSpecificationStock record);
	
	List<Map<String,Object>> selectStockByClass(ProductSpecificationStock clssname);
}