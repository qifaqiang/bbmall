package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.prod.model.ProductPackage;

;

/**
 * @文件名称: ProductPackageMapper.java
 * @类路径: com.wxsoft.xyd.prod.mapper.ProductPackageMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-26 15:38:24
 */
@Repository
public interface ProductPackageMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	int deleteByProdId(Integer id);

	// 根据所需插入
	int insertSelective(ProductPackage record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductPackage record);

	// 根据主键查询
	ProductPackage selectByPrimaryKey(Integer id);

	ProductPackage selectByProductId(Integer id);

	// 根据所需查询
	ProductPackage selectByProductPackage(ProductPackage record);

	// 分页获取
	List<ProductPackage> listPageByProductPackage(ProductPackage clssname);

	// 获取全部
	List<ProductPackage> getAllByProductPackage(ProductPackage clssname);
	
	List<ProductPackage> getAllByProductPackageDetail(ProductPackage clssname);

}