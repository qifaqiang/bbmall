package com.wxsoft.xyd.ad.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.ad.model.AdConfig;;

/**
 * @文件名称: AdConfigMapper.java
 * @类路径: com.wxsoft.xyd.ad.mapper.AdConfigMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-09 22:04:10
 */
@Repository
public interface  AdConfigMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(AdConfig record);

	// 根据所需更新
	int updateByPrimaryKeySelective(AdConfig record);

	// 根据主键查询
	AdConfig selectByPrimaryKey(Integer id);

	// 根据所需查询
	AdConfig selectByAdConfig(AdConfig record);

	// 分页获取
	List<AdConfig> listPageByAdConfig(AdConfig clssname);

	// 获取全部
	List<AdConfig> getAllByAdConfig(AdConfig clssname);
}