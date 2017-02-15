/**   
 * @文件名称: SysMenuServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.system.mapper.SysMenuMapper;
import com.wxsoft.xyd.system.model.SysMenu;
import com.wxsoft.xyd.system.service.SysMenuService;

/**
 * @类功能说明：
 * @类修改者：kyz
 * @修改日期：2013-8-10
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-10 下午05:50:09
 */

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysMenu record) {
		// TODO Auto-generated method stub
		if (record.getParentId() == 0 || null == record.getParentId()) {
			record.setParentId(0);
			record.setLevel(0);
		} else {
			SysMenu temp = sysMenuMapper.selectByPrimaryKey(record
					.getParentId());
			if (null != temp) {
				record.setLevel(temp.getLevel() + 1);
			}
		}
		return sysMenuMapper.insert(record);
	}

	@Override
	public List<SysMenu> getAllFirstLevelSysMenu(SysMenu example) {
		// TODO Auto-generated method stub
		List<SysMenu> list = sysMenuMapper.getAllFirstLevelSysMenu(example);
		List<SysMenu> listTemp = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : list) {
			listTemp = sysMenuMapper.getAllSecondLevelSysMenu(sysMenu);
			sysMenu.setSysMenuList(listTemp);
		}
		return list;
	}
	
	
	@Override
	public List<SysMenu> getAllByParentId(Integer pid) {
		// TODO Auto-generated method stub
		List<SysMenu> list = sysMenuMapper.getAllByParentId(pid);
		return list;
	}

	@Override
	public SysMenu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysMenu record) {
		// TODO Auto-generated method stub
		return sysMenuMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public int updateByPrimaryKey(SysMenu record) {
		// TODO Auto-generated method stub
		return sysMenuMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<Integer, List<SysMenu>> getAllSecondLevelSysMenu(SysMenu example) {
		// TODO Auto-generated method stub
		List<SysMenu> list = sysMenuMapper.getAllFirstLevelSysMenu(example);
		List<SysMenu> listTemp = new ArrayList<SysMenu>();
		Map<Integer, List<SysMenu>> map = new HashMap<Integer, List<SysMenu>>();
		for (SysMenu sysMenu : list) {
			listTemp = sysMenuMapper.getAllSecondLevelSysMenu(sysMenu);
			map.put(sysMenu.getId(), listTemp);
		}
		return map;
	}

	@Override
	public String getAllSysMenu(SysMenu example) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		List<SysMenu> list = sysMenuMapper.getAllFirstLevelSysMenu(example);
		List<SysMenu> listTemp = new ArrayList<SysMenu>();
		for (int i = 0; i < list.size(); i++) {
			sb.append("{\"text\": \"")
					.append(list.get(i).getName())
					.append("\",\"state\": {\"opened\": true},\"id\":\""
							+ list.get(i).getId() + "\", \"children\": [");
			listTemp = sysMenuMapper.getAllSecondLevelSysMenu(list.get(i));
			for (int j = 0; j < listTemp.size(); j++) {
				if (j == listTemp.size() - 1) {
					sb.append("{\"text\": \"")
							.append(listTemp.get(j).getName())
							.append("\",\"id\":\"" + listTemp.get(j).getId()
									+ "\"}");
				} else {
					sb.append("{\"text\": \"")
							.append(listTemp.get(j).getName())
							.append("\",\"id\":\"" + listTemp.get(j).getId()
									+ "\"},");
				}
			}
			if (i == list.size() - 1) {
				sb.append("]}");
			} else {
				sb.append("]},");
			}

		}
		return sb.toString();
	}

	@Override
	public String updateUserMenu(List<Integer> example) {
		StringBuffer sb = new StringBuffer();
		List<SysMenu> list = sysMenuMapper.getAllFirstLevelSysMenu(null);
		List<SysMenu> listTemp = new ArrayList<SysMenu>();
		for (int i = 0; i < list.size(); i++) {
			sb.append("{\"text\": \"")
					.append(list.get(i).getName())
					.append("\",\"state\": {\"opened\": true},\"id\":\""
							+ list.get(i).getId() + "\"");
			sb.append(", \"children\": [");
			listTemp = sysMenuMapper.getAllSecondLevelSysMenu(list.get(i));

			for (int j = 0; j < listTemp.size(); j++) {
				sb.append("{\"text\": \"").append(listTemp.get(j).getName())
						.append("\"");
				if (example.contains(listTemp.get(j).getId())) {
					sb.append(",\"state\": {\"selected\": true}");
				}
				sb.append(",\"id\":\"" + listTemp.get(j).getId() + "\"}");
				if (j != listTemp.size() - 1) {
					sb.append(",");
				}
			}

			if (i == list.size() - 1) {
				sb.append("]}");
			} else {
				sb.append("]},");
			}

		}
		return sb.toString();
	}

	@Override
	public List<String> getAllUserSysMenu(SysMenu example) {
		// TODO Auto-generated method stub
		List<SysMenu> list = sysMenuMapper.getAllUserSysMenu(example);
		String result = "";
		result += list.get(0).getLinkUrl() + ",";
		if (list.get(1) != null) {
			result += list.get(1).getLinkUrl();
		}
		List<String> listResult = Arrays.asList(result.split(","));
		return listResult;
	}

	@Override
	public List<SysMenu> getSysMenuWithRole(SysMenu example, Integer roleId) {
		// TODO Auto-generated method stub
		List<SysMenu> list = sysMenuMapper
				.getAllFirstLevelSysMenuWithRole(example);
		List<SysMenu> listTemp = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : list) {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("id", String.valueOf(sysMenu.getId()));
			maps.put("roleId", String.valueOf(roleId));
			listTemp = sysMenuMapper.getAllSecondLevelSysMenuRole(maps);
			sysMenu.setSysMenuList(listTemp);
		}
		return list;
	}
}
