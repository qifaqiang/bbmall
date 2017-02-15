package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.prod.mapper.ProductTagsMapper;
import com.wxsoft.xyd.prod.model.ProductTags;
import com.wxsoft.xyd.prod.service.ProductTagsService;

/**
 * 商品标签处理
 * 
 * @author kyz
 * 
 */
@Service("productTagsService")
public class ProductTagsServiceImpl implements ProductTagsService {

	@Autowired
	private ProductTagsMapper productTagsMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productTagsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductTags record) {
		return productTagsMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductTags record) {
		return productTagsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductTags selectByPrimaryKey(Integer id) {
		return productTagsMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductTags selectByProductTags(ProductTags record) {
		return productTagsMapper.selectByProductTags(record);
	}

	@Override
	public List<ProductTags> listPageByProductTags(ProductTags clssname) {
		return productTagsMapper.listPageByProductTags(clssname);
	}

	@Override
	public List<ProductTags> getAllByProductTags(ProductTags clssname) {
		return productTagsMapper.getAllByProductTags(clssname);
	}
}