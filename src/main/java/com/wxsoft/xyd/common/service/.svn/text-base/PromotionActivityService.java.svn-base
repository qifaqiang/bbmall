package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.system.model.SysLog;

/**
 * @文件名称: PromotionActivityService.java
 * @类路径: com.wxsoft.xyd.common.service.PromotionActivityService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-01 17:40:46
 */
public interface PromotionActivityService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);
	
	int deleteByPrimary(Integer promotionId);
	// 根据所需插入
	int insertSelective(PromotionActivity record, SysLog sl, String prodId);

	// 根据所需更新
	int updateByPrimaryKeySelective(PromotionActivity record, SysLog sl,
			String prodId);
	// 根据所需更新
    int updateByPrimaryKey(PromotionActivity record );
	// 根据主键查询
	PromotionActivity selectByPrimaryKey(Integer id);

	// 根据所需查询
	PromotionActivity selectByPromotionActivity(PromotionActivity record);

	// 分页获取
	List<PromotionActivity> listPageByPromotionActivity(
			PromotionActivity clssname);

	// 获取全部
	List<Map<String, Object>>  getAllByPromotionActivity(PromotionActivity clssname);
	// 获取
	Map<String, Object> getByPromotionActivity(PromotionActivity clssname);
	
	List<PromotionActivity>  getAllByPromotionActivityNormal(PromotionActivity clssname);
}