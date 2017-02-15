package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;;

/**
 * @文件名称: ProductSpecificationInfoDetailMapper.java
 * @类路径: com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoDetailMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-05 10:28:20
 */
@Repository
public interface  ProductSpecificationInfoDetailMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductSpecificationInfoDetail record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductSpecificationInfoDetail record);

	// 根据主键查询
	ProductSpecificationInfoDetail selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductSpecificationInfoDetail selectByProductSpecificationInfoDetail(ProductSpecificationInfoDetail record);

	// 分页获取
	List<ProductSpecificationInfoDetail> listPageByProductSpecificationInfoDetail(ProductSpecificationInfoDetail clssname);

	// 获取全部
	List<ProductSpecificationInfoDetail> getAllByProductSpecificationInfoDetail(ProductSpecificationInfoDetail clssname);
	
	int deleteBySpecificationInfoId(Integer id);
}