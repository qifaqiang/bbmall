package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationStockMapper;
import com.wxsoft.xyd.prod.service.ProductSpecificationStockService;

@Service("productSpecificationStockService")
public class ProductSpecificationStockServiceImpl implements ProductSpecificationStockService {
	
	@Autowired
	private ProductSpecificationStockMapper productSpecificationStockMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productSpecificationStockMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductSpecificationStock record) {
		return productSpecificationStockMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductSpecificationStock record) {
		return productSpecificationStockMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductSpecificationStock selectByPrimaryKey(Integer id) {
		return productSpecificationStockMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductSpecificationStock selectByProductSpecificationStock(ProductSpecificationStock record) {
		return productSpecificationStockMapper.selectByProductSpecificationStock(record);
	}

	@Override
	public List<ProductSpecificationStock> listPageByProductSpecificationStock(ProductSpecificationStock clssname) {
		return productSpecificationStockMapper.listPageByProductSpecificationStock(clssname);
	}

	@Override
	public List<ProductSpecificationStock> getAllByProductSpecificationStock(ProductSpecificationStock clssname) {
		return productSpecificationStockMapper.getAllByProductSpecificationStock(clssname);
	}

	// 进货操作  增加库存
	@Override
	public int updateProdStock(ProductSpecificationStock record) {
		// TODO Auto-generated method stub
		return productSpecificationStockMapper.updateProdStock(record);
	}

	@Override
	public ProductSpecificationStock selectProdTotalStock(
			ProductSpecificationStock record) {
		return productSpecificationStockMapper.selectProdTotalStock(record);
	}
}