package com.wxsoft.xyd.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.SysNewsCatalog;;

/**
 * @文件名称: SysNewsCatalogMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.SysNewsCatalogMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 10:13:49
 */
@Repository
public interface  SysNewsCatalogMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);
	
	//当要删除的数据是一级数据时删除其下的二级数据
		int deleteByPid(Integer id);

	// 根据所需插入
	int insertSelective(SysNewsCatalog record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysNewsCatalog record);

	// 根据主键查询
	SysNewsCatalog selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysNewsCatalog selectBySysNewsCatalog(SysNewsCatalog record);

	// 分页获取
	List<SysNewsCatalog> listPageBySysNewsCatalog(SysNewsCatalog clssname);

	// 获取全部
	List<SysNewsCatalog> getAllBySysNewsCatalog(SysNewsCatalog clssname);
}