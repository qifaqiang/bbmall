/**   
 * @文件名称: SysMenuService.java
 * @类路径: com.wxltsoft.fxshop.common.service
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-12 下午05:49:23  
 */

package com.wxsoft.xyd.system.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.system.model.SysMenu;

/**
 * @类功能说明：SysMenuService
 * @类修改者：kyz
 * @修改日期：2013-9-12
 * @修改说明：
 * @名称：kyz
 * @作者：kyz
 * @创建时间：2013-9-12 下午05:49:23
 */

public interface SysMenuService {

	int deleteByPrimaryKey(Integer id);

	int insert(SysMenu record);

	List<SysMenu> getAllFirstLevelSysMenu(SysMenu example);

	List<SysMenu> getSysMenuWithRole(SysMenu example, Integer roleId);

	List<String> getAllUserSysMenu(SysMenu example);

	String getAllSysMenu(SysMenu example);

	String updateUserMenu(List<Integer> example);

	Map<Integer, List<SysMenu>> getAllSecondLevelSysMenu(SysMenu example);

	SysMenu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	List<SysMenu> getAllByParentId(Integer pid);

}
