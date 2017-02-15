package com.wxsoft.xyd.system.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.system.model.SysSearchhot;;

/**
 * @文件名称: SysSearchhotMapper.java
 * @类路径: com.wxsoft.xyd.system.mapper.SysSearchhotMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-03 00:14:57
 */
@Repository
public interface  SysSearchhotMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysSearchhot record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysSearchhot record);

	// 根据主键查询
	SysSearchhot selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysSearchhot selectBySysSearchhot(SysSearchhot record);

	// 分页获取
	List<SysSearchhot> listPageBySysSearchhot(SysSearchhot clssname);

	// 获取全部
	List<SysSearchhot> getAllBySysSearchhot(SysSearchhot clssname);
}