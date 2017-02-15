/**   
 * @文件名称: SysLogServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */
package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.system.model.SysLog;
import com.wxsoft.xyd.system.mapper.SysLogMapper;
import com.wxsoft.xyd.system.service.SysLogService;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysLog record) {
		return sysLogMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysLog record) {
		return sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysLog selectByPrimaryKey(Integer id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysLog selectBySysLog(SysLog record) {
		return sysLogMapper.selectBySysLog(record);
	}

	@Override
	public List<SysLog> listPageBySysLog(SysLog clssname) {
		return sysLogMapper.listPageBySysLog(clssname);
	}

	@Override
	public List<SysLog> getAllBySysLog(SysLog clssname) {
		return sysLogMapper.getAllBySysLog(clssname);
	}
}