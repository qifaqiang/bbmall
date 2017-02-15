package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.RollPic;
import com.wxsoft.xyd.common.mapper.RollPicMapper;
import com.wxsoft.xyd.common.service.RollPicService;

@Service("rollPicService")
public class RollPicServiceImpl implements RollPicService {

	@Autowired
	private RollPicMapper rollPicMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return rollPicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(RollPic record) {
		return rollPicMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(RollPic record) {
		return rollPicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public RollPic selectByPrimaryKey(Integer id) {
		return rollPicMapper.selectByPrimaryKey(id);
	}

	@Override
	public RollPic selectByRollPic(RollPic record) {
		return rollPicMapper.selectByRollPic(record);
	}

	@Override
	public List<RollPic> listPageByRollPic(RollPic clssname) {
		return rollPicMapper.listPageByRollPic(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllByRollPic(RollPic clssname) {
		return rollPicMapper.getAllByRollPic(clssname);
	}
}