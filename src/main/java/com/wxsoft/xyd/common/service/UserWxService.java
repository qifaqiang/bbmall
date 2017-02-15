package com.wxsoft.xyd.common.service;

import java.util.List;
import com.wxsoft.xyd.common.model.UserWx;;

/**
 * @文件名称: UserWxService.java
 * @类路径: com.wxsoft.xyd.common.service.UserWxService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-21 11:22:42
 */
public interface  UserWxService {
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