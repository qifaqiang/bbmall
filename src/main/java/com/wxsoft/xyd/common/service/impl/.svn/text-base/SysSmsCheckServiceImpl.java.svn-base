package com.wxsoft.xyd.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.SysSmsCheckMapper;
import com.wxsoft.xyd.common.model.SysSmsCheck;
import com.wxsoft.xyd.common.service.SysSmsCheckService;

@Service("sysSmsService")
public class SysSmsCheckServiceImpl implements SysSmsCheckService {
	@Autowired
	private SysSmsCheckMapper sysSmsCheckMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysSmsCheckMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysSmsCheck record) {
		return sysSmsCheckMapper.insert(record);
	}

	@Override
	public int insertSelective(SysSmsCheck record) {
		return sysSmsCheckMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysSmsCheck record) {
		return sysSmsCheckMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysSmsCheck selectByPrimaryKey(Integer id) {
		return sysSmsCheckMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysSmsCheck selectBySysSmsCheck(SysSmsCheck record) {
		return sysSmsCheckMapper.selectBySysSmsCheck(record);
	}

	@Override
	public List<SysSmsCheck> listPageBySysSmsCheck(SysSmsCheck clssname) {
		return sysSmsCheckMapper.listPageBySysSmsCheck(clssname);
	}

	@Override
	public List<SysSmsCheck> getAllBySysSmsCheck(SysSmsCheck clssname) {
		return sysSmsCheckMapper.getAllBySysSmsCheck(clssname);
	}
}