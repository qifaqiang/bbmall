/**   
 * @文件名称: sysCouponsConfService.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */
package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.system.model.SysCouponsConf;
import com.wxsoft.xyd.system.model.SysLog;
import com.wxsoft.xyd.system.mapper.SysCouponsConfMapper;
import com.wxsoft.xyd.system.mapper.SysLogMapper;
import com.wxsoft.xyd.system.service.SysCouponsConfService;

@Service("sysCouponsConfService")
public class SysCouponsConfServiceImpl implements SysCouponsConfService {
	
	@Autowired
	private SysCouponsConfMapper sysCouponsConfMapper;
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysCouponsConfMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysCouponsConf record, SysLog syslog) {
		sysLogMapper.insertSelective(syslog);
		return sysCouponsConfMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysCouponsConf record, SysLog syslog) {
		sysLogMapper.insertSelective(syslog);
		return sysCouponsConfMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysCouponsConf selectByPrimaryKey(Integer id) {
		return sysCouponsConfMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysCouponsConf selectBySysCouponsConf(SysCouponsConf record) {
		return sysCouponsConfMapper.selectBySysCouponsConf(record);
	}

	@Override
	public List<SysCouponsConf> listPageBySysCouponsConf(SysCouponsConf clssname) {
		return sysCouponsConfMapper.listPageBySysCouponsConf(clssname);
	}

	@Override
	public List<SysCouponsConf> getAllBySysCouponsConf(SysCouponsConf clssname) {
		return sysCouponsConfMapper.getAllBySysCouponsConf(clssname);
	}
}