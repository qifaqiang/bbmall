package com.wxsoft.xyd.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.UserLocation;

/**
 * @文件名称: UserLocationMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-23 16:19:08
 */
@Repository
public interface UserLocationMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(UserLocation record);

	// 根据所需插入
	int insertSelective(UserLocation record);

	// 更新其他的状态
	int updateStatus(UserLocation record);
	
	int updateBySetDefault(int usrid);

	// 根据所需更新
	int updateByPrimaryKeySelective(UserLocation record);

	// 根据主键查询
	UserLocation selectByPrimaryKey(Integer id);

	// 根据所需查询
	UserLocation selectByUserLocation(UserLocation record);

	UserLocation selectById(UserLocation record);

	// 根据默认值
	UserLocation selectByUserLocationOrStatus(UserLocation userLocation);

	// 分页获取
	List<UserLocation> listAjaxPageByUserLocation(UserLocation clssname);

	// 获取全部
	List<UserLocation> getAllByUserLocation(UserLocation clssname);
}