package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.RollPic;

;

/**
 * @文件名称: RollPicMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.RollPicMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-24 13:24:39
 */
@Repository
public interface RollPicMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(RollPic record);

	// 根据所需更新
	int updateByPrimaryKeySelective(RollPic record);

	// 根据主键查询
	RollPic selectByPrimaryKey(Integer id);

	// 根据所需查询
	RollPic selectByRollPic(RollPic record);

	// 分页获取
	List<RollPic> listPageByRollPic(RollPic clssname);

	// 获取全部
	List<Map<String, Object>> getAllByRollPic(RollPic clssname);
}