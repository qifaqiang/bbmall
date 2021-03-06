package com.wxsoft.xyd.prod.service;

import java.util.List;
import com.wxsoft.xyd.prod.model.ProductBasic;

/**
 * @文件名称: ProductBasicService.java
 * @类路径: com.wxsoft.xyd.prod.service.ProductBasicService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-25 14:47:38
 */
public interface ProductBasicService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductBasic record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductBasic record);

	// 根据主键查询
	ProductBasic selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductBasic selectByProductBasic(ProductBasic record);

	// 分页获取
	List<ProductBasic> listPageByProductBasic(ProductBasic clssname);

	// 获取全部
	List<ProductBasic> getAllByProductBasic(ProductBasic clssname);

}