package com.wxsoft.xyd.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.SysNews;
import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.system.model.Company;

/**
 * @文件名称: SysNewsMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.SysNewsMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 11:19:22
 */
@Repository
public interface  SysNewsMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysNews record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysNews record);

	// 根据主键查询
	SysNews selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysNews selectBySysNews(SysNews record);

	// 分页获取
	List<SysNews> listPageBySysNews(SysNews clssname);
	List<Company> listPageBySysNews(Company company);
	// 获取全部
	List<SysNews> getAllBySysNews(SysNews clssname);
	//获取帮助分类表
	SysNewsCatalog selectSysNewsCatalog(SysNewsCatalog record);
}