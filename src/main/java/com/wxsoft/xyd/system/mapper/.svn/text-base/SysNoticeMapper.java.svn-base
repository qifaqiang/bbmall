package com.wxsoft.xyd.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.system.model.SysNotice;

;

/**
 * @文件名称: SysNoticeMapper.java
 * @类路径: com.wxsoft.xyd.system.mapper.SysNoticeMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-02 23:55:38
 */
@Repository
public interface SysNoticeMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysNotice record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysNotice record);

	// 根据主键查询
	SysNotice selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysNotice selectBySysNotice(SysNotice record);

	// 分页获取
	List<SysNotice> listPageBySysNotice(SysNotice clssname);
	
	List<Map<String, Object>> listAjaxPageCoupons(SysNotice clssname);

	// 获取全部
	List<Map<String, Object>> getAllBySysNotice(SysNotice clssname);
}