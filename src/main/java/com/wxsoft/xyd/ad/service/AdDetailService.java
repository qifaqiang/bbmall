package com.wxsoft.xyd.ad.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.ad.model.AdDetail;

;

/**
 * @文件名称: AdDetailService.java
 * @类路径: com.wxsoft.xyd.ad.service.AdDetailService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-09 22:04:38
 */
public interface AdDetailService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(AdDetail record);

	// 根据所需更新
	int updateByPrimaryKeySelective(AdDetail record);

	// 根据主键查询
	AdDetail selectByPrimaryKey(Integer id);

	// 根据所需查询
	AdDetail selectByAdDetail(AdDetail record);

	// 分页获取
	List<AdDetail> listPageByAdDetail(AdDetail clssname);
	// 根据ID获取广告图片
	List<AdDetail> getStockByDetailId(String[] ids);
	// 获取全部
	List<AdDetail> getAllByAdDetail(AdDetail clssname);

	List<Map<String, Object>> getAllByMap(AdDetail clssname);

}