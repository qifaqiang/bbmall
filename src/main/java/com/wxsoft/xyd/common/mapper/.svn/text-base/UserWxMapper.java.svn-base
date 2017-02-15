package com.wxsoft.xyd.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.common.model.UserWx;;

/**
 * @文件名称: UserWxMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.UserWxMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-21 11:22:42
 */
@Repository
public interface  UserWxMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(UserWx record);

	// 根据所需更新
	int updateByPrimaryKeySelective(UserWx record);

	// 根据主键查询
	UserWx selectByPrimaryKey(Integer id);

	// 根据所需查询
	UserWx selectByUserWx(UserWx record);

	// 分页获取
	List<UserWx> listPageByUserWx(UserWx clssname);

	// 获取全部
	List<UserWx> getAllByUserWx(UserWx clssname);
}