package com.wxsoft.xyd.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.system.model.SysMenu;

@Repository
public interface SysMenuMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysMenu record);

	/**
	 * 获取一级菜单
	 * 
	 * @param example
	 * @return
	 */
	List<SysMenu> getAllFirstLevelSysMenu(SysMenu example);

	/**
	 * 获取二级菜单
	 * 
	 * @param example
	 * @return
	 */
	List<SysMenu> getAllSecondLevelSysMenu(SysMenu example);

	List<SysMenu> getAllSecondLevelSysMenuRole(Map<String, String> map);

	SysMenu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	List<SysMenu> getAllUserSysMenu(SysMenu example);

	List<SysMenu> getAllFirstLevelSysMenuWithRole(SysMenu example);

	List<SysMenu> getAllByParentId(Integer pid);
}