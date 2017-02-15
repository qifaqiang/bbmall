/**   
 * @文件名称: UserRoleresourceServiceImpl.java
 * @类路径: com.wxsoft.xyd.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.system.mapper.UserRoleresourceMapper;
import com.wxsoft.xyd.system.model.UserRoleresource;
import com.wxsoft.xyd.system.service.UserRoleresourceService;

/**
 * @类功能说明：
 * @类修改者：kyz
 * @修改日期：2013-8-10
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-10 下午05:50:09
 */

@Service("userRoleresourceService")
public class UserRoleresourceServiceImpl implements UserRoleresourceService {
	@Autowired
	private UserRoleresourceMapper userRoleresourceMapper;

	@Override
	public int insert(UserRoleresource record) {
		// TODO Auto-generated method stub
		return userRoleresourceMapper.insert(record);
	}

	@Override
	public int insertSelective(UserRoleresource record) {
		// TODO Auto-generated method stub
		return userRoleresourceMapper.insertSelective(record);
	}

	@Override
	public int deleteByRoleId(UserRoleresource example) {
		// TODO Auto-generated method stub
		return userRoleresourceMapper.deleteByRoleId(example);
	}

	@Override
	public List<UserRoleresource> selectByRoleId(int roleid) {
		// TODO Auto-generated method stub
		return userRoleresourceMapper.selectByRoleId(roleid);
	}


}
