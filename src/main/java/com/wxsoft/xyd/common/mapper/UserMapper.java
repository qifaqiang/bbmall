package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.User;

/**
 * @文件名称: UserMapper.java
 * @类路径: com/wxsoft/xyd/common/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-03 17:23:20
 */
@Repository
public interface UserMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(User record);

	// 根据所需插入
	int insertSelective(User record);

	// 根据所需更新
	int updateByPrimaryKeySelective(User record);

	int updateDisable(User user);

	// 根据主键查询
	User selectByPrimaryKey(Integer id);

	// 根据所需查询
	User selectByUser(User record);

	// 分页获取
	List<User> listPageByUser(User clssname);

	// 查询机器码是否可用
	User selectmachineCodeIfDel(User user);

	// 获取全部
	List<User> getAllByUser(User clssname);

	List<Map<String, Object>> listPageUserVsWx(User record);

	int selectWithArchive(Integer userid);
}