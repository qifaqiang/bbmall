package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.UserCollection;

public interface UserCollectionService {
	int deleteByPrimaryKey(Integer id);

	int deleteByPrimary(String id);

	int insert(UserCollection record);

	int insertSelective(UserCollection record);

	int updateByPrimaryKeySelective(UserCollection record);

	UserCollection selectByPrimaryKey(Integer id);

	UserCollection selectByUserCollection(UserCollection record);

	List<UserCollection> listPageByUserCollection(UserCollection clssname);

	List<Map<String, Object>> listAjaxPageByUserCollection(UserCollection clssname);

	List<Map<String, Object>> getByUserCollection(UserCollection clssname);

	List<Map<String, Object>> listByUserCollection(UserCollection clssname);

	List<UserCollection> getAllByUserCollection(UserCollection clssname);

	List<Map<String, Object>> getAllProByUserId(Integer userId);
}