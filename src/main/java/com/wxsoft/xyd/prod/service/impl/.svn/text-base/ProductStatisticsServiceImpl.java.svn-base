package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.prod.model.ProductStatistics;
import com.wxsoft.xyd.prod.mapper.ProductStatisticsMapper;
import com.wxsoft.xyd.prod.service.ProductStatisticsService;

/**
 * 商品统计处理
 * 
 * @author kyz
 * 
 */
@Service("productStatisticsService")
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

	@Autowired
	private ProductStatisticsMapper productStatisticsMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productStatisticsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductStatistics record) {
		return productStatisticsMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductStatistics record) {
		return productStatisticsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductStatistics selectByPrimaryKey(Integer id) {
		return productStatisticsMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductStatistics selectByProductStatistics(ProductStatistics record) {
		return productStatisticsMapper.selectByProductStatistics(record);
	}

	@Override
	public List<ProductStatistics> listPageByProductStatistics(
			ProductStatistics clssname) {
		return productStatisticsMapper.listPageByProductStatistics(clssname);
	}

	@Override
	public List<ProductStatistics> getAllByProductStatistics(
			ProductStatistics clssname) {
		return productStatisticsMapper.getAllByProductStatistics(clssname);
	}
}