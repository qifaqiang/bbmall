package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.UserCollection;

/**
 * @文件名称: UserCollectionMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-20 10:05:39
 */
@Repository
public interface UserCollectionMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据proId删除
	int deleteByPrimary(Integer id);

	// 全部插入
	int insert(UserCollection record);

	// 根据所需插入
	int insertSelective(UserCollection record);

	// 根据所需更新
	int updateByPrimaryKeySelective(UserCollection record);

	// 根据主键查询
	UserCollection selectByPrimaryKey(Integer id);

	// 根据所需查询
	UserCollection selectByUserCollection(UserCollection record);

	// 分页获取
	List<UserCollection> listPageByUserCollection(UserCollection clssname);

	List<Map<String, Object>> getByUserCollection(UserCollection clssname);

	// ajax分页
	List<Map<String, Object>> listAjaxPageByUserCollection(UserCollection clssname);

	// 获取全部
	List<UserCollection> getAllByUserCollection(UserCollection clssname);

	List<Map<String, Object>> listByUserCollection(UserCollection clssname);

	List<Map<String, Object>> getAllProByUserId(Integer userId);
}