package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.UserComment;

;

/**
 * @文件名称: UserCommentService.java
 * @类路径: com.wxsoft.xyd.common.service.UserCommentService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-25 15:04:12
 */
public interface UserCommentService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(UserComment record);

	// 根据所需更新
	int updateByPrimaryKeySelective(UserComment record);

	// 根据主键查询
	UserComment selectByPrimaryKey(Integer id);

	// 根据所需查询
	UserComment selectByUserComment(UserComment record);

	// 分页获取
	List<UserComment> listPageByUserComment(UserComment clssname);

	// 获取全部
	List<UserComment> getAllByUserComment(UserComment clssname);

	//分页获取前台展示的商品评论
	List<Map<String, Object>> listAjaxPageByUserCommentFrontShow(
			UserComment clssname);

	//返回前台总条数
	int selectCountLevelFromUserComment(UserComment clssname);
}