package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.prod.model.ProductSpecificationDetail;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationDetailMapper;
import com.wxsoft.xyd.prod.service.ProductSpecificationDetailService;

@Service("productSpecificationDetailService")
public class ProductSpecificationDetailServiceImpl implements ProductSpecificationDetailService {
	
	@Autowired
	private ProductSpecificationDetailMapper productSpecificationDetailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productSpecificationDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductSpecificationDetail record) {
		return productSpecificationDetailMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductSpecificationDetail record) {
		return productSpecificationDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductSpecificationDetail selectByPrimaryKey(Integer id) {
		return productSpecificationDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductSpecificationDetail selectByProductSpecificationDetail(ProductSpecificationDetail record) {
		return productSpecificationDetailMapper.selectByProductSpecificationDetail(record);
	}

	@Override
	public List<ProductSpecificationDetail> listPageByProductSpecificationDetail(ProductSpecificationDetail clssname) {
		return productSpecificationDetailMapper.listPageByProductSpecificationDetail(clssname);
	}

	@Override
	public List<ProductSpecificationDetail> getAllByProductSpecificationDetail(ProductSpecificationDetail clssname) {
		return productSpecificationDetailMapper.getAllByProductSpecificationDetail(clssname);
	}
}