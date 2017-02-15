package com.wxsoft.xyd.system.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.system.model.SysCouponsConf;;

/**
 * @文件名称: SysCouponsConfMapper.java
 * @类路径: com.wxsoft.xyd.system.mapper.SysCouponsConfMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-30 12:12:22
 */
@Repository
public interface  SysCouponsConfMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysCouponsConf record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysCouponsConf record);

	// 根据主键查询
	SysCouponsConf selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysCouponsConf selectBySysCouponsConf(SysCouponsConf record);

	// 分页获取
	List<SysCouponsConf> listPageBySysCouponsConf(SysCouponsConf clssname);

	// 获取全部
	List<SysCouponsConf> getAllBySysCouponsConf(SysCouponsConf clssname);
}