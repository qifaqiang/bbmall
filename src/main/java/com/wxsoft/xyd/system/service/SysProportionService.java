package com.wxsoft.xyd.system.service;

import java.util.List;
import com.wxsoft.xyd.system.model.SysProportion;;

/**
 * @文件名称: SysProportionService.java
 * @类路径: com.wxsoft.xyd.system.service.SysProportionService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 18:31:51
 */
public interface  SysProportionService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysProportion record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysProportion record);

	// 根据主键查询
	SysProportion selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysProportion selectBySysProportion(SysProportion record);

	// 分页获取
	List<SysProportion> listPageBySysProportion(SysProportion clssname);

	// 获取全部
	List<SysProportion> getAllBySysProportion(SysProportion clssname);
}