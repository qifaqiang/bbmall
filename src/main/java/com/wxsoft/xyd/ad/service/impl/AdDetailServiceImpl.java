package com.wxsoft.xyd.ad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.mapper.AdDetailMapper;
import com.wxsoft.xyd.ad.service.AdDetailService;

@Service("adDetailService")
public class AdDetailServiceImpl implements AdDetailService {

	@Autowired
	private AdDetailMapper adDetailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return adDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AdDetail record) {
		return adDetailMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(AdDetail record) {
		return adDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public AdDetail selectByPrimaryKey(Integer id) {
		return adDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public AdDetail selectByAdDetail(AdDetail record) {
		return adDetailMapper.selectByAdDetail(record);
	}

	@Override
	public List<AdDetail> listPageByAdDetail(AdDetail clssname) {
		return adDetailMapper.listPageByAdDetail(clssname);
	}

	@Override
	public List<AdDetail> getAllByAdDetail(AdDetail clssname) {
		return adDetailMapper.getAllByAdDetail(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllByMap(AdDetail clssname) {
		// TODO Auto-generated method stub
		return adDetailMapper.getAllByMap(clssname);
	}

	@Override
	public List<AdDetail> getStockByDetailId(String[] ids) {
		// TODO Auto-generated method stub
		return adDetailMapper.getStockByDetailId(ids);
	}
}