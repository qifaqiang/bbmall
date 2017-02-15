/**   
 * @文件名称: UserRoleServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.system.model.SysSearchhot;
import com.wxsoft.xyd.system.mapper.SysSearchhotMapper;
import com.wxsoft.xyd.system.service.SysSearchhotService;

@Service("sysSearchhotService")
public class SysSearchhotServiceImpl implements SysSearchhotService {
	
	@Autowired
	private SysSearchhotMapper sysSearchhotMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysSearchhotMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysSearchhot record) {
		return sysSearchhotMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysSearchhot record) {
		return sysSearchhotMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysSearchhot selectByPrimaryKey(Integer id) {
		return sysSearchhotMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysSearchhot selectBySysSearchhot(SysSearchhot record) {
		return sysSearchhotMapper.selectBySysSearchhot(record);
	}

	@Override
	public List<SysSearchhot> listPageBySysSearchhot(SysSearchhot clssname) {
		return sysSearchhotMapper.listPageBySysSearchhot(clssname);
	}

	@Override
	public List<SysSearchhot> getAllBySysSearchhot(SysSearchhot clssname) {
		return sysSearchhotMapper.getAllBySysSearchhot(clssname);
	}
}