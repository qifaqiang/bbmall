package com.wxsoft.xyd.system.mapper;

import com.wxsoft.xyd.system.model.UserRole;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(UserRole record);

	List<UserRole> listPageAllRole(UserRole example);

	UserRole selectByPrimaryKey(Integer id);

	UserRole selectRoleResourceByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserRole record);

	UserRole selectByUserRoleAssociatedDeUserId(Integer id);

	List<UserRole> getAllRoleByCompanyId(UserRole example);

}