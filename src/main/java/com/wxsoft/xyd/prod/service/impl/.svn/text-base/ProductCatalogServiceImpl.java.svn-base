package com.wxsoft.xyd.prod.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.prod.mapper.ProductCatalogMapper;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;

/**
 * 商品分类信息处理
 * 
 * @author kyz
 * 
 */
@Service("productCatalogService")
public class ProductCatalogServiceImpl implements ProductCatalogService {

	@Autowired
	private ProductCatalogMapper productCatalogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productCatalogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ProductCatalog record) {
		return productCatalogMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductCatalog record) {
		return productCatalogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductCatalog selectByPrimaryKey(Integer id) {
		return productCatalogMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductCatalog selectByProductCatalog(ProductCatalog record) {
		return productCatalogMapper.selectByProductCatalog(record);
	}

	@Override
	public List<ProductCatalog> listPageByProductCatalog(ProductCatalog clssname) {
		return productCatalogMapper.listPageByProductCatalog(clssname);
	}

	@Override
	public List<ProductCatalog> getAllByProductCatalog(ProductCatalog clssname) {
		List<ProductCatalog> pclist = productCatalogMapper
				.getAllByProductCatalog(clssname);
		for (ProductCatalog prodCatalog : pclist) {
			ProductCatalog temp = new ProductCatalog();
			temp.setPid(prodCatalog.getId());
			List<ProductCatalog> tempList = productCatalogMapper
					.getAllByProductCatalog(temp);
			prodCatalog.setSublist(tempList);
		}
		return pclist;
	}

	@Override
	public List<ProductCatalog> getFirstProductCatalog() {
		// TODO Auto-generated method stub
		ProductCatalog pc = new ProductCatalog();
		pc.setPid(0);
		List<ProductCatalog> pclist = productCatalogMapper
				.getAllByProductCatalog(pc);
		return pclist;
	}

	@Override
	public List<Map<String, Object>> getAllByProductCatalogIndexNavigation(
			ProductCatalog clssname) {
		// TODO Auto-generated method stub
		return productCatalogMapper
				.getAllByProductCatalogIndexNavigation(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllByProductCatalogIndexRecommend(
			ProductCatalog clssname) {
		// TODO Auto-generated method stub
		return productCatalogMapper
				.getAllByProductCatalogIndexRecommend(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllByProductCatalogFront(
			ProductCatalog clssname) {
		// TODO Auto-generated method stub
		return productCatalogMapper.getAllByProductCatalogFront(clssname);
	}

}