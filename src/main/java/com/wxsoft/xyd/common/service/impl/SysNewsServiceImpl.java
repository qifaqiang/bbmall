package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.common.model.SysNews;
import com.wxsoft.xyd.common.mapper.SysNewsMapper;
import com.wxsoft.xyd.common.service.SysNewsService;

@Service("sysNewsService")
public class SysNewsServiceImpl implements SysNewsService {
	
	@Autowired
	private SysNewsMapper sysNewsMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysNews record) {
		return sysNewsMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysNews record) {
		return sysNewsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysNews selectByPrimaryKey(Integer id) {
		return sysNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysNews selectBySysNews(SysNews record) {
		return sysNewsMapper.selectBySysNews(record);
	}

	@Override
	public List<SysNews> listPageBySysNews(SysNews clssname) {
		// TODO Auto-generated method stub
		return sysNewsMapper.listPageBySysNews(clssname);
	}
	@Override
	public List<SysNews> getAllBySysNews(SysNews clssname) {
		return sysNewsMapper.getAllBySysNews(clssname);
	}

	

}