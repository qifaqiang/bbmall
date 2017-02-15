/**   
 * @文件名称: UserRoleAssociatedServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.system.mapper.UserRoleAssociatedMapper;
import com.wxsoft.xyd.system.model.UserRoleAssociated;
import com.wxsoft.xyd.system.service.UserRoleAssociatedService;

/**
 * @类功能说明：
 * @类修改者：kyz
 * @修改日期：2013-8-10
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-10 下午05:50:09
 */

@Service("userRoleAssociatedService")
public class UserRoleAssociatedServiceImpl implements UserRoleAssociatedService {
	@Autowired
	private UserRoleAssociatedMapper userRoleAssociatedMapper;

	@Override
	public int insert(UserRoleAssociated record) {
		// TODO Auto-generated method stub
		return userRoleAssociatedMapper.insert(record);
	}

	@Override
	public int deleteByRoleId(UserRoleAssociated example) {
		// TODO Auto-generated method stub
		return userRoleAssociatedMapper.deleteByRoleId(example);
	}

	@Override
	public int deleteByUserId(UserRoleAssociated example) {
		// TODO Auto-generated method stub
		return userRoleAssociatedMapper.deleteByUserId(example);
	}

	@Override
	public int updateByUserId(UserRoleAssociated example) {
		// TODO Auto-generated method stub
		return userRoleAssociatedMapper.updateByUserId(example);
	}

	@Override
	public int updateRoleIdByUserId(UserRoleAssociated example) {
		return userRoleAssociatedMapper.updateRoleIdByUserId(example);
	}

	@Override
	public UserRoleAssociated selectRoleId(Integer id) {
		// TODO Auto-generated method stub
		return userRoleAssociatedMapper.selectRoleId(id);
	}

}
