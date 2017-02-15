package com.wxsoft.xyd.common.service;

import java.util.List;

import com.wxsoft.xyd.common.model.SysShipping;

public interface SysShippingService {
	int deleteByPrimaryKey(Integer id);

	int insert(SysShipping record);

	int insertSelective(SysShipping record);

	int updateByPrimaryKeySelective(SysShipping record);

	SysShipping selectByPrimaryKey(Integer id);

	SysShipping selectBySysShipping(SysShipping record);

	List<SysShipping> listPageBySysShipping(SysShipping clssname);

	List<SysShipping> getAllBySysShipping(SysShipping clssname);
}