package com.wxsoft.xyd.common.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.SysSmsCheck;

/**
 * @文件名称: SysSmsCheckMapper.java
 * @类路径: com/wxsoft/xyd/common/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-03 17:22:57
 */
@Repository
public interface SysSmsCheckMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 全部插入
	int insert(SysSmsCheck record);

	// 根据所需插入
	int insertSelective(SysSmsCheck record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysSmsCheck record);

	// 根据主键查询
	SysSmsCheck selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysSmsCheck selectBySysSmsCheck(SysSmsCheck record);

	// 分页获取
	List<SysSmsCheck> listPageBySysSmsCheck(SysSmsCheck clssname);

	// 获取全部
	List<SysSmsCheck> getAllBySysSmsCheck(SysSmsCheck clssname);
}