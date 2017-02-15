package com.wxsoft.xyd.common.service;

import java.util.List;

import com.wxsoft.xyd.common.model.SysSmsCheck;

public interface SysSmsCheckService {
	int deleteByPrimaryKey(Integer id);

	int insert(SysSmsCheck record);

	int insertSelective(SysSmsCheck record);

	int updateByPrimaryKeySelective(SysSmsCheck record);

	SysSmsCheck selectByPrimaryKey(Integer id);

	SysSmsCheck selectBySysSmsCheck(SysSmsCheck record);

	List<SysSmsCheck> listPageBySysSmsCheck(SysSmsCheck clssname);

	List<SysSmsCheck> getAllBySysSmsCheck(SysSmsCheck clssname);
}