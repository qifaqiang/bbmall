package com.wxsoft.xyd.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.ad.model.AdConfig;
import com.wxsoft.xyd.ad.mapper.AdConfigMapper;
import com.wxsoft.xyd.ad.service.AdConfigService;

@Service("adConfigService")
public class AdConfigServiceImpl implements AdConfigService {
	
	@Autowired
	private AdConfigMapper adConfigMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return adConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AdConfig record) {
		return adConfigMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(AdConfig record) {
		return adConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public AdConfig selectByPrimaryKey(Integer id) {
		return adConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public AdConfig selectByAdConfig(AdConfig record) {
		return adConfigMapper.selectByAdConfig(record);
	}

	@Override
	public List<AdConfig> listPageByAdConfig(AdConfig clssname) {
		return adConfigMapper.listPageByAdConfig(clssname);
	}

	@Override
	public List<AdConfig> getAllByAdConfig(AdConfig clssname) {
		return adConfigMapper.getAllByAdConfig(clssname);
	}
}