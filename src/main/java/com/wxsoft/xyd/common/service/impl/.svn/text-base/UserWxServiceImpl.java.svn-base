package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.common.model.UserWx;
import com.wxsoft.xyd.common.mapper.UserWxMapper;
import com.wxsoft.xyd.common.service.UserWxService;

@Service("userWxService")
public class UserWxServiceImpl implements UserWxService {
	
	@Autowired
	private UserWxMapper userWxMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userWxMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserWx record) {
		return userWxMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserWx record) {
		return userWxMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserWx selectByPrimaryKey(Integer id) {
		return userWxMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserWx selectByUserWx(UserWx record) {
		return userWxMapper.selectByUserWx(record);
	}

	@Override
	public List<UserWx> listPageByUserWx(UserWx clssname) {
		return userWxMapper.listPageByUserWx(clssname);
	}

	@Override
	public List<UserWx> getAllByUserWx(UserWx clssname) {
		return userWxMapper.getAllByUserWx(clssname);
	}
}