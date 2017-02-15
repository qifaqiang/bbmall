package com.wxsoft.xyd.prod.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.prod.model.ProductStatistics;;

/**
 * @文件名称: ProductStatisticsMapper.java
 * @类路径: com.wxsoft.xyd.prod.mapper.ProductStatisticsMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-18 14:56:35
 */
@Repository
public interface  ProductStatisticsMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(ProductStatistics record);

	// 根据所需更新
	int updateByPrimaryKeySelective(ProductStatistics record);

	// 根据主键查询
	ProductStatistics selectByPrimaryKey(Integer id);

	// 根据所需查询
	ProductStatistics selectByProductStatistics(ProductStatistics record);

	// 分页获取
	List<ProductStatistics> listPageByProductStatistics(ProductStatistics clssname);

	// 获取全部
	List<ProductStatistics> getAllByProductStatistics(ProductStatistics clssname);
}