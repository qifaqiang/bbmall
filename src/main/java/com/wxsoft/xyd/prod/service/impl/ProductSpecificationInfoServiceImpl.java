package com.wxsoft.xyd.prod.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoMapper;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoService;

/**
 * 商品规格处理 Service
 * 
 * @author kyz
 * 
 */
@Service("productSpecificationInfoService")
public class ProductSpecificationInfoServiceImpl implements
		ProductSpecificationInfoService {
	@Autowired
	private ProductSpecificationInfoMapper productSpecificationInfoMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productSpecificationInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductSpecificationInfo record) {
		return productSpecificationInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(ProductSpecificationInfo record) {
		return productSpecificationInfoMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductSpecificationInfo record) {
		return productSpecificationInfoMapper
				.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductSpecificationInfo selectByPrimaryKey(Integer id) {
		return productSpecificationInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductSpecificationInfo selectByProductSpecificationInfo(
			ProductSpecificationInfo record) {
		return productSpecificationInfoMapper
				.selectByProductSpecificationInfo(record);
	}

	@Override
	public List<ProductSpecificationInfo> listPageByProductSpecificationInfo(
			ProductSpecificationInfo clssname) {
		return productSpecificationInfoMapper
				.listPageByProductSpecificationInfo(clssname);
	}

	@Override
	public List<ProductSpecificationInfo> getAllByProductSpecificationInfo(
			ProductSpecificationInfo clssname) {
		return productSpecificationInfoMapper
				.getAllByProductSpecificationInfo(clssname);
	}
	@Override
	public List<ProductSpecificationInfo> getAllByPSIForWeb(
			ProductSpecificationInfo clssname) {
		return productSpecificationInfoMapper
				.getAllByPSIForWeb(clssname);
	}

	@Override
	public int updateKuCunJian(ProductSpecificationInfo classname) {
		return productSpecificationInfoMapper.updateKuCunJian(classname);
	}

	@Override
	public List<ProductSpecificationInfo> listAjaxPageGetSpecProdByCaId(ProductSpecificationInfo psi) {
		return productSpecificationInfoMapper.listAjaxPageGetSpecProdByCaId(psi);//获取已经开启规格的产品
	}
	@Override
	public List<ProductSpecificationInfo> listAjaxPageGetSpecProdByCompanyId(ProductSpecificationInfo psi) {
		return productSpecificationInfoMapper.listAjaxPageGetSpecProdByCompanyId(psi);//获取已经开启规格的产品
	}

    @Override
    public int selectTotalInvcountByProid(Integer prodId) {
        return productSpecificationInfoMapper.selectTotalInvcountByProid(prodId);
    }
}