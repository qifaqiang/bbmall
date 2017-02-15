package com.wxsoft.xyd.ad.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.ad.model.AdDetail;

;

/**
 * @文件名称: AdDetailMapper.java
 * @类路径: com.wxsoft.xyd.ad.mapper.AdDetailMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-09 22:04:38
 */
@Repository
public interface AdDetailMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(AdDetail record);

	// 根据ID获取广告图片
	List<AdDetail> getStockByDetailId(String[] ids);
		
	// 根据所需更新
	int updateByPrimaryKeySelective(AdDetail record);

	// 根据主键查询
	AdDetail selectByPrimaryKey(Integer id);

	// 根据所需查询
	AdDetail selectByAdDetail(AdDetail record);

	// 分页获取
	List<AdDetail> listPageByAdDetail(AdDetail clssname);

	// 获取全部
	List<AdDetail> getAllByAdDetail(AdDetail clssname);

	List<Map<String, Object>> getAllByMap(AdDetail clssname);
}