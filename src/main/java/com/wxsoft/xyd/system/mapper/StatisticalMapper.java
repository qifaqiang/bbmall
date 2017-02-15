package com.wxsoft.xyd.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @文件名称: StatisticalMapper.java
 * @类路径: com/wxltsoft/scancode/system/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-05-28 14:04:49
 */
@Repository
public interface StatisticalMapper {
	// 查询统计demo
	List<Map<String, Object>> selectModelStatistical(
			@Param(value = "sql") String sql);
	
	List<Map<String, Object>> selectModelStatisticalNew(Map map);
}