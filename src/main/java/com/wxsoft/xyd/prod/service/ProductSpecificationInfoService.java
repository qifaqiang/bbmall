package com.wxsoft.xyd.prod.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;

public interface ProductSpecificationInfoService {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductSpecificationInfo record);

	int insertSelective(ProductSpecificationInfo record);

	int updateByPrimaryKeySelective(ProductSpecificationInfo record);

	ProductSpecificationInfo selectByPrimaryKey(Integer id);

	ProductSpecificationInfo selectByProductSpecificationInfo(
			ProductSpecificationInfo record);

	List<ProductSpecificationInfo> listPageByProductSpecificationInfo(
			ProductSpecificationInfo clssname);

	List<ProductSpecificationInfo> getAllByProductSpecificationInfo(
			ProductSpecificationInfo clssname);
	List<ProductSpecificationInfo> getAllByPSIForWeb(
			ProductSpecificationInfo clssname);

	// 商品减库存
	int updateKuCunJian(ProductSpecificationInfo classname);
	
	List<ProductSpecificationInfo> listAjaxPageGetSpecProdByCaId(ProductSpecificationInfo psi);
	
	List<ProductSpecificationInfo> listAjaxPageGetSpecProdByCompanyId(ProductSpecificationInfo psi);

    int selectTotalInvcountByProid(Integer prodId);
}