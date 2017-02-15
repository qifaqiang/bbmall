package com.wxsoft.xyd.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.SysCouponsMapper;
import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.common.service.SysCouponsService;
import com.wxsoft.xyd.system.mapper.SysLogMapper;
import com.wxsoft.xyd.system.model.SysLog;

@Service("sysCouponsService")
public class SysCouponsServiceImpl implements SysCouponsService {

	@Autowired
	private SysCouponsMapper sysCouponsMapper;
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int deleteByPrimaryKey(SysCoupons record,SysLog syslog) {
		sysLogMapper.insertSelective(syslog);
		return sysCouponsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(SysCoupons record, SysLog syslog) {
		sysLogMapper.insertSelective(syslog);
		return sysCouponsMapper.insertSelective(record);
	}
 
	@Override
	public int updateByPrimaryKeySelective(SysCoupons record, SysLog syslog) {
		sysLogMapper.insertSelective(syslog);
		SysCoupons old=sysCouponsMapper.selectByPrimaryKey(record.getId());
		if(old.getAllCount().intValue()==record.getAllCount().intValue()){
			return sysCouponsMapper.updateByPrimaryKeySelective(record);
		}else if(old.getAllCount().intValue()>record.getAllCount().intValue()){
			int newTemp=old.getLeftCount()-(old.getAllCount()-record.getAllCount());
			if(newTemp>0){
				record.setLeftCount(newTemp);
			}else{
				record.setLeftCount(0);
			}
			return sysCouponsMapper.updateByPrimaryKeySelective(record);
		}else{
			record.setLeftCount(old.getLeftCount()+(record.getAllCount()-old.getAllCount()));
			return sysCouponsMapper.updateByPrimaryKeySelective(record);
		}
		
	}

	@Override
	public SysCoupons selectByPrimaryKey(Integer id) {
			return sysCouponsMapper.selectByPrimaryKey(id);
	}
	@Override
	public SysCoupons selectByPrimaryKeyCount(Integer id) {
		return sysCouponsMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysCoupons selectBySysCoupons(SysCoupons record) {
		return sysCouponsMapper.selectBySysCoupons(record);
	}

	@Override
	public List<SysCoupons> listPageBySysCoupons(SysCoupons clssname) {
		return sysCouponsMapper.listPageBySysCoupons(clssname);
	}

	@Override
	public List<SysCoupons> getAllBySysCoupons(SysCoupons clssname) {
		return sysCouponsMapper.getAllBySysCoupons(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageSysCoupons(SysCoupons clssname) {
		// TODO Auto-generated method stub
		return sysCouponsMapper.listAjaxPageSysCoupons(clssname);
	}

	@Override
	public  int updateCount(SysCoupons record) {
			return sysCouponsMapper.updateCount(record);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageCoupons(SysCoupons clssname) {
		// TODO Auto-generated method stub
		return sysCouponsMapper.listAjaxPageCoupons(clssname);
	}

}