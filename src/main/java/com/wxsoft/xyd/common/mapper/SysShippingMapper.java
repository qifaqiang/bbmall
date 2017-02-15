package com.wxsoft.xyd.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.SysShipping;

/**
 * @文件名称: SysShippingMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-26 15:19:32
 */
@Repository
public interface SysShippingMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(SysShipping record);

	// 根据所需插入
	int insertSelective(SysShipping record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysShipping record);

	// 根据主键查询
	SysShipping selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysShipping selectBySysShipping(SysShipping record);

	// 分页获取
	List<SysShipping> listPageBySysShipping(SysShipping clssname);

	// 获取全部
	List<SysShipping> getAllBySysShipping(SysShipping clssname);
}