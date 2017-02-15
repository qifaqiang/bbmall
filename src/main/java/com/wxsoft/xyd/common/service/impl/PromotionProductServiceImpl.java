package com.wxsoft.xyd.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.PromotionProductMapper;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.PromotionProduct;
import com.wxsoft.xyd.common.service.PromotionProductService;

@Service("promotionProductService")
public class PromotionProductServiceImpl implements PromotionProductService {

	@Autowired
	private PromotionProductMapper promotionProductMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return promotionProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(PromotionProduct record) {
		return promotionProductMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(PromotionProduct record) {
		return promotionProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PromotionProduct selectByPrimaryKey(Integer id) {
		return promotionProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public PromotionProduct selectByPromotionProduct(PromotionProduct record) {
		return promotionProductMapper.selectByPromotionProduct(record);
	}

	@Override
	public List<PromotionProduct> listPageByPromotionProduct(
			PromotionProduct clssname) {
		return promotionProductMapper.listPageByPromotionProduct(clssname);
	}

	@Override
	public List<PromotionProduct> getAllByPromotionProduct(
			PromotionProduct clssname) {
		return promotionProductMapper.getAllByPromotionProduct(clssname);
	}

	@Override
	public List<PromotionProduct> listAjaxPagePromotionProduct(
			PromotionProduct clssname) {
		// TODO Auto-generated method stub
		return promotionProductMapper.listAjaxPagePromotionProduct(clssname);
	}

	@Override
	public List<PromotionActivity> getAllByPromotionProductVsInTime(Integer productId) {
		// TODO Auto-generated method stub
		return promotionProductMapper
				.getAllByPromotionProductVsInTime(productId);
	}

	@Override
	public List<Map<String,Object>> getAllByProdIdVsInTime(int[] ids) {
		// TODO Auto-generated method stub
		return promotionProductMapper.getAllByProdIdVsInTime(ids);
	}

	@Override
	public List<Map<String, Object>> listAjaxPagePromotionSelected(PromotionProduct clssname) {
		// TODO Auto-generated method stub
		return promotionProductMapper.listAjaxPagePromotionSelected(clssname);
	}
}