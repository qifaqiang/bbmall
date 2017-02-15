package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.prod.model.ProductSpecificationDetail;;

/**
 * @文件名称: ProductSpecificationDetailMapper.java
 * @类路径: com.wxsoft.xyd.prod.mapper.ProductSpecificationDetailMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-03 10:40:13
 */
@Repository
public interface  ProductSpecificationDetailMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductSpecificationDetail record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductSpecificationDetail record);

	// 根据主键查询
	ProductSpecificationDetail selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductSpecificationDetail selectByProductSpecificationDetail(ProductSpecificationDetail record);

	// 分页获取
	List<ProductSpecificationDetail> listPageByProductSpecificationDetail(ProductSpecificationDetail clssname);

	// 获取全部
	List<ProductSpecificationDetail> getAllByProductSpecificationDetail(ProductSpecificationDetail clssname);
	
	//根据规格ID删除
	int deleteBySpecId(Integer id);
}