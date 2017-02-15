package com.wxsoft.xyd.system.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.User;

public interface UserService {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	int updateByPrimaryKeySelective(User record);

	User selectByPrimaryKey(Integer id);

	// 查询机器码是否可用
	User selectmachineCodeIfDel(User user);

	User selectByUser(User record);

	List<User> listPageByUser(User clssname);

	List<User> getAllByUser(User clssname);

	List<Map<String, Object>> listPageUserVsWx(User record);

	int updateDisable(User user);

	int selectWithArchive(Integer userid);
}