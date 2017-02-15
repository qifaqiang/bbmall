package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.mapper.ProductBasicMapper;
import com.wxsoft.xyd.prod.service.ProductBasicService;

/**
 * 商品字典表处理
 * 
 * @author kyz
 * 
 */
@Service("productBasicService")
public class ProductBasicServiceImpl implements ProductBasicService {

	@Autowired
	private ProductBasicMapper productBasicMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productBasicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductBasic record) {
		return productBasicMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductBasic record) {
		return productBasicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductBasic selectByPrimaryKey(Integer id) {
		return productBasicMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductBasic selectByProductBasic(ProductBasic record) {
		return productBasicMapper.selectByProductBasic(record);
	}

	@Override
	public List<ProductBasic> listPageByProductBasic(ProductBasic clssname) {
		return productBasicMapper.listPageByProductBasic(clssname);
	}

	@Override
	public List<ProductBasic> getAllByProductBasic(ProductBasic clssname) {
		// TODO Auto-generated method stub
		return productBasicMapper.getAllByProductBasic(clssname);
	}

}