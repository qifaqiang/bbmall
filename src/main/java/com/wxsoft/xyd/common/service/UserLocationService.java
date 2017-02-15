package com.wxsoft.xyd.common.service;

import java.util.List;

import com.wxsoft.xyd.common.model.UserLocation;

public interface UserLocationService {
	int deleteByPrimaryKey(Integer id,Integer userId);

	int insert(UserLocation record);

	int insertSelective(UserLocation record);

	// 修改其他的状态
	int updateStatus(UserLocation record);

	int updateBySetDefault(int usrid);

	//更新状态，不更新其他的状态
	int updatestau(UserLocation record);

	int updateByPrimaryKeySelective(UserLocation record);

	UserLocation selectByPrimaryKey(Integer id);

	UserLocation selectByUserLocation(UserLocation record);

	UserLocation selectById(UserLocation record);

	UserLocation selectByUserLocationOrStatus(UserLocation userLocation);

	List<UserLocation> listAjaxPageByUserLocation(UserLocation clssname);

	List<UserLocation> getAllByUserLocation(UserLocation clssname);
	
	int updateByUerId(UserLocation record);
}