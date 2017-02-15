package com.wxsoft.xyd.prod.service;

import java.util.List;
import com.wxsoft.xyd.prod.model.ProductPackage;;

/**
 * @文件名称: ProductPackageService.java
 * @类路径: com.wxsoft.xyd.prod.service.ProductPackageService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-26 15:38:24
 */
public interface  ProductPackageService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductPackage record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductPackage record);

	// 根据主键查询
	ProductPackage selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductPackage selectByProductPackage(ProductPackage record);

	// 分页获取
	List<ProductPackage> listPageByProductPackage(ProductPackage clssname);

	// 获取全部
	List<ProductPackage> getAllByProductPackage(ProductPackage clssname);
	
	List<ProductPackage> getAllByProductPackageDetail(ProductPackage clssname);
}