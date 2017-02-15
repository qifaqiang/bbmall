package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoDetailMapper;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoDetailService;

@Service("productSpecificationInfoDetailService")
public class ProductSpecificationInfoDetailServiceImpl implements ProductSpecificationInfoDetailService {
	
	@Autowired
	private ProductSpecificationInfoDetailMapper productSpecificationInfoDetailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productSpecificationInfoDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductSpecificationInfoDetail record) {
		return productSpecificationInfoDetailMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductSpecificationInfoDetail record) {
		return productSpecificationInfoDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductSpecificationInfoDetail selectByPrimaryKey(Integer id) {
		return productSpecificationInfoDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductSpecificationInfoDetail selectByProductSpecificationInfoDetail(ProductSpecificationInfoDetail record) {
		return productSpecificationInfoDetailMapper.selectByProductSpecificationInfoDetail(record);
	}

	@Override
	public List<ProductSpecificationInfoDetail> listPageByProductSpecificationInfoDetail(ProductSpecificationInfoDetail clssname) {
		return productSpecificationInfoDetailMapper.listPageByProductSpecificationInfoDetail(clssname);
	}

	@Override
	public List<ProductSpecificationInfoDetail> getAllByProductSpecificationInfoDetail(ProductSpecificationInfoDetail clssname) {
		return productSpecificationInfoDetailMapper.getAllByProductSpecificationInfoDetail(clssname);
	}
}