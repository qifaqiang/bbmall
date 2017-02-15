package com.wxsoft.xyd.prod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.prod.mapper.ProductImageMapper;
import com.wxsoft.xyd.prod.model.ProductImage;
import com.wxsoft.xyd.prod.service.ProductImageService;

/**
 * 商品图片处理Service
 * 
 * @author kyz
 * 
 */
@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	private ProductImageMapper productImageMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductImage record) {
		return productImageMapper.insert(record);
	}

	@Override
	public int insertSelective(ProductImage record) {
		return productImageMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductImage record) {
		return productImageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductImage selectByPrimaryKey(Integer id) {
		return productImageMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductImage selectByProductImage(ProductImage record) {
		return productImageMapper.selectByProductImage(record);
	}

	@Override
	public List<ProductImage> listPageByProductImage(ProductImage clssname) {
		return productImageMapper.listPageByProductImage(clssname);
	}

	@Override
	public List<ProductImage> getAllByProductImage(Integer prodId) {
		return productImageMapper.getAllByProductImage(prodId);
	}

	@Override
	public List<String> getAllByProductImageRMap(Integer prodId) {
		// TODO Auto-generated method stub
		return productImageMapper.getAllByProductImageRMap(prodId);
	}
}