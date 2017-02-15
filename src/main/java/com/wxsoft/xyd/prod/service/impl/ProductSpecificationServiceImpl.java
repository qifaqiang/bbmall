package com.wxsoft.xyd.prod.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.prod.mapper.ProductSpecificationDetailMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationMapper;
import com.wxsoft.xyd.prod.model.ProductSpecification;
import com.wxsoft.xyd.prod.model.ProductSpecificationDetail;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;

@Service("productSpecificationService")
public class ProductSpecificationServiceImpl implements
		ProductSpecificationService {
	@Autowired
	private ProductSpecificationMapper productSpecificationMapper;
	
	@Autowired
	private ProductSpecificationDetailMapper productSpecificationDetailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		//先删掉该规格的具体规格值
		int result = productSpecificationDetailMapper.deleteBySpecId(id);
		return productSpecificationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProductSpecification record) {
		return productSpecificationMapper.insert(record);
	}

	@Override
	public int insertSelective(ProductSpecification record,String[] detailNames, Integer[] skus) {
		
		int result = productSpecificationMapper.insertSelective(record);
		if(result > 0){
			if(null != detailNames && detailNames.length>0){
				if (skus == null || skus.length == 0) {
					skus = new Integer[1];
				}
				for(int i=0;i<skus.length;i++){
					String detailName_t = detailNames[i].trim();
					if(StringUtils.isNotBlank(detailName_t) && !"null".equals(detailName_t)){
						ProductSpecificationDetail psd = productSpecificationDetailMapper.selectByPrimaryKey(skus[i]);
						if(null == psd){
							psd = new ProductSpecificationDetail();
							psd.setProdSpecId(record.getId());
							psd.setDetailName(detailName_t);
							psd.setAddtime("1");//时间用的数据库函数
							productSpecificationDetailMapper.insertSelective(psd);
						}else{
							psd.setProdSpecId(record.getId());
							psd.setDetailName(detailName_t);
							psd.setAddtime("1");//时间用的数据库函数
							productSpecificationDetailMapper.updateByPrimaryKeySelective(psd);
						}
					}
				}
			}
			
		}
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(ProductSpecification record,String[] detailNames, Integer[] skus) {
		ProductSpecificationDetail psd_t = new ProductSpecificationDetail();
		psd_t.setProdSpecId(record.getId());
		List<ProductSpecificationDetail> psdList = productSpecificationDetailMapper.getAllByProductSpecificationDetail(psd_t);
		Map<String, String> maps = new HashMap<String, String>();
		for (ProductSpecificationDetail productSpecificationDetail : psdList) {
			maps.put("p" + productSpecificationDetail.getId(), "");
		}
		
		if(null != detailNames && detailNames.length>0){
			if (skus == null || skus.length == 0) {
				skus = new Integer[1];
			}
			for(int i=0;i<skus.length;i++){
				String detailName_t = detailNames[i].trim();
				if(StringUtils.isNotBlank(detailName_t) && !"null".equals(detailName_t)){
					ProductSpecificationDetail psd = productSpecificationDetailMapper.selectByPrimaryKey(skus[i]);
					if(null == psd){
						psd = new ProductSpecificationDetail();
						psd.setProdSpecId(record.getId());
						psd.setDetailName(detailNames[i]);
						psd.setAddtime("1");//时间用的数据库函数
						productSpecificationDetailMapper.insertSelective(psd);
					}else{
						psd.setProdSpecId(record.getId());
						psd.setDetailName(detailNames[i]);
						psd.setAddtime("1");//时间用的数据库函数
						productSpecificationDetailMapper.updateByPrimaryKeySelective(psd);
					}
					maps.remove("p" + skus[i]);
				}
			}
		}
		for (Map.Entry<String, String> entry : maps.entrySet()) {
			productSpecificationDetailMapper.deleteByPrimaryKey(Integer
					.parseInt(entry.getKey().replace("p", "")));
		}
		return productSpecificationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ProductSpecification selectByPrimaryKey(Integer id) {
		return productSpecificationMapper.selectByPrimaryKey(id);
	}

	@Override
	public ProductSpecification selectByProductSpecification(
			ProductSpecification record) {
		return productSpecificationMapper.selectByProductSpecification(record);
	}

	@Override
	public List<ProductSpecification> listPageByProductSpecification(
			ProductSpecification clssname) {
		return productSpecificationMapper
				.listPageByProductSpecification(clssname);
	}

	@Override
	public List<ProductSpecification> getAllByProductSpecification(
			ProductSpecification clssname) {
		return productSpecificationMapper
				.getAllByProductSpecification(clssname);
	}
}