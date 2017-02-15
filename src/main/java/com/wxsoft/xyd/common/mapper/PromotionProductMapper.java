package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.PromotionProduct;

;

/**
 * @文件名称: PromotionProductMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.PromotionProductMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-01 11:02:42
 */
@Repository
public interface PromotionProductMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	int deleteByPrimary(Integer promotionId);

	// 根据所需插入
	int insertSelective(PromotionProduct record);

	// 根据所需更新
	int updateByPrimaryKeySelective(PromotionProduct record);

	// 根据主键查询
	PromotionProduct selectByPrimaryKey(Integer id);

	// 根据所需查询
	PromotionProduct selectByPromotionProduct(PromotionProduct record);

	// 分页获取
	List<PromotionProduct> listPageByPromotionProduct(PromotionProduct clssname);

	// 分页获取
	List<PromotionProduct> listAjaxPagePromotionProduct(PromotionProduct clssname);

	// 获取全部
	List<PromotionProduct> getAllByPromotionProduct(PromotionProduct clssname);

	// 获取全部
	List<PromotionActivity> getAllByPromotionProductVsInTime(Integer productId);

	List<Map<String, Object>> getAllByProdIdVsInTime(int[] ids);

	List<Map<String, Object>> listAjaxPagePromotionSelected(PromotionProduct clssname);
}