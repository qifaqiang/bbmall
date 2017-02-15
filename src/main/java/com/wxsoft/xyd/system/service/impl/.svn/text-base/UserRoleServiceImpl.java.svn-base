/**   
 * @文件名称: UserRoleServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.system.mapper.UserRoleMapper;
import com.wxsoft.xyd.system.mapper.UserRoleresourceMapper;
import com.wxsoft.xyd.system.model.UserRole;
import com.wxsoft.xyd.system.model.UserRoleresource;
import com.wxsoft.xyd.system.service.UserRoleService;

/**
 * @类功能说明：
 * @类修改者：kyz
 * @修改日期：2015-8-10
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2015-8-10 下午05:50:09
 */

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserRoleresourceMapper userRoleresourceMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserRole record, String[] resouceIds) {
		// TODO Auto-generated method stub
		UserRoleresource urs = new UserRoleresource();
		int i = 0;
		if (userRoleMapper.insert(record) > 0) {
			urs.setRoleid(record.getId());
			for (String string : resouceIds) {
				if (!string.equals("")) {
					urs.setResid(Integer.parseInt(string));
					userRoleresourceMapper.insert(urs);
				}
			}
			i = 1;
		}
		return i;
	}

	@Override
	public List<UserRole> listPageAllRole(UserRole example) {
		// TODO Auto-generated method stub
		return userRoleMapper.listPageAllRole(example);
	}

	@Override
	public UserRole selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(UserRole record, String[] resouceIds) {
		// TODO Auto-generated method stub
		UserRoleresource urs = new UserRoleresource();
		urs.setRoleid(record.getId());
		int res = userRoleMapper.updateByPrimaryKeySelective(record);
		if (res > 0) {
			res = userRoleresourceMapper.deleteByRoleId(urs);
			if (res > 0) {
				urs.setRoleid(record.getId());
				for (String string : resouceIds) {
					urs.setResid(Integer.parseInt(string));
					userRoleresourceMapper.insert(urs);
				}
				res = 1;
			}
		}
		return res;
	}

	@Override
	public UserRole selectRoleResourceByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		UserRole ur = userRoleMapper.selectRoleResourceByPrimaryKey(id);
		ur.setUserRoleresources(userRoleresourceMapper.selectByRoleId(ur
				.getId()));
		return ur;
	}

	@Override
	public List<UserRole> getAllRoleByCompanyId(UserRole example) {
		// TODO Auto-generated method stub
		return userRoleMapper.getAllRoleByCompanyId(example);
	}

}
