package com.wxsoft.xyd.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.UserCollectionMapper;
import com.wxsoft.xyd.common.model.UserCollection;
import com.wxsoft.xyd.common.service.UserCollectionService;

@Service("userCollectionService")
public class UserCollectionServiceImpl implements UserCollectionService {
	@Autowired
	private UserCollectionMapper userCollectionMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userCollectionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserCollection record) {
		return userCollectionMapper.insert(record);
	}

	@Override
	public int insertSelective(UserCollection record) {
		return userCollectionMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserCollection record) {
		return userCollectionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserCollection selectByPrimaryKey(Integer id) {
		return userCollectionMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserCollection selectByUserCollection(UserCollection record) {
		return userCollectionMapper.selectByUserCollection(record);
	}

	@Override
	public List<UserCollection> listPageByUserCollection(UserCollection clssname) {
		return userCollectionMapper.listPageByUserCollection(clssname);
	}

	@Override
	public List<UserCollection> getAllByUserCollection(UserCollection clssname) {
		return userCollectionMapper.getAllByUserCollection(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllProByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userCollectionMapper.getAllProByUserId(userId);
	}

	@Override
	public int deleteByPrimary(String id) {
		// TODO Auto-generated method stub
		int count = 0;
		if (id.indexOf(",") >= 0) {
			String[] ary = id.split(",");
			for (int i = 0; i < ary.length; i++) {
				if (userCollectionMapper.deleteByPrimary(Integer.parseInt(ary[i])) > 0) {
					count = count + 1;
				}
			}
		} else {
			if (userCollectionMapper.deleteByPrimary(Integer.parseInt(id)) > 0) {
				count = count + 1;
			}
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> listAjaxPageByUserCollection(UserCollection clssname) {
		// TODO Auto-generated method stub
		return userCollectionMapper.listAjaxPageByUserCollection(clssname);
	}

	@Override
	public List<Map<String, Object>> listByUserCollection(UserCollection clssname) {
		// TODO Auto-generated method stub
		return userCollectionMapper.listByUserCollection(clssname);
	}

	@Override
	public List<Map<String, Object>> getByUserCollection(UserCollection clssname) {
		// TODO Auto-generated method stub
		return 	userCollectionMapper.getByUserCollection( clssname);
	}
}