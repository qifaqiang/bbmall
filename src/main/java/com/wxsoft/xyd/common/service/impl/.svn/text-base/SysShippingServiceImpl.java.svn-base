package com.wxsoft.xyd.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.SysShippingMapper;
import com.wxsoft.xyd.common.model.SysShipping;
import com.wxsoft.xyd.common.service.SysShippingService;

@Service("sysShippingService")
public class SysShippingServiceImpl implements SysShippingService {
	@Autowired
	private SysShippingMapper sysShippingMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysShippingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysShipping record) {
		return sysShippingMapper.insert(record);
	}

	@Override
	public int insertSelective(SysShipping record) {
		return sysShippingMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysShipping record) {
		return sysShippingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysShipping selectByPrimaryKey(Integer id) {
		return sysShippingMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysShipping selectBySysShipping(SysShipping record) {
		return sysShippingMapper.selectBySysShipping(record);
	}

	@Override
	public List<SysShipping> listPageBySysShipping(SysShipping clssname) {
		return sysShippingMapper.listPageBySysShipping(clssname);
	}

	@Override
	public List<SysShipping> getAllBySysShipping(SysShipping clssname) {
		return sysShippingMapper.getAllBySysShipping(clssname);
	}
}