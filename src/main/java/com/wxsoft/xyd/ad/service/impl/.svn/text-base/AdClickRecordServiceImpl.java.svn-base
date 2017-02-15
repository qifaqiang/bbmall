package com.wxsoft.xyd.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.ad.mapper.AdClickRecordMapper;
import com.wxsoft.xyd.ad.model.AdClickRecord;
import com.wxsoft.xyd.ad.service.AdClickRecordService;

@Service("adClickRecordService")
public class AdClickRecordServiceImpl implements AdClickRecordService {

	@Autowired
	private AdClickRecordMapper adClickRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return adClickRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AdClickRecord record) {
		return adClickRecordMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(AdClickRecord record) {
		return adClickRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public AdClickRecord selectByPrimaryKey(Integer id) {
		return adClickRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public AdClickRecord selectByAdClickRecord(AdClickRecord record) {
		return adClickRecordMapper.selectByAdClickRecord(record);
	}

	@Override
	public List<AdClickRecord> getPageListAdClickRecord(AdClickRecord clssname) {
		return adClickRecordMapper.getPageListAdClickRecord(clssname);
	}

	@Override
	public List<AdClickRecord> getAllByAdClickRecord(AdClickRecord clssname) {
		return adClickRecordMapper.getAllByAdClickRecord(clssname);
	}
}