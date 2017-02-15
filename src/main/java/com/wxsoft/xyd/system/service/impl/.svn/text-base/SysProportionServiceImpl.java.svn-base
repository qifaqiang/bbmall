/**   
 * @文件名称: SysProportionServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.system.model.SysProportion;
import com.wxsoft.xyd.system.mapper.SysProportionMapper;
import com.wxsoft.xyd.system.service.SysProportionService;

@Service("sysProportionService")
public class SysProportionServiceImpl implements SysProportionService {
	
	@Autowired
	private SysProportionMapper sysProportionMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysProportionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysProportion record) {
		return sysProportionMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysProportion record) {
		return sysProportionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysProportion selectByPrimaryKey(Integer id) {
		return sysProportionMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysProportion selectBySysProportion(SysProportion record) {
		return sysProportionMapper.selectBySysProportion(record);
	}

	@Override
	public List<SysProportion> listPageBySysProportion(SysProportion clssname) {
		return sysProportionMapper.listPageBySysProportion(clssname);
	}

	@Override
	public List<SysProportion> getAllBySysProportion(SysProportion clssname) {
		return sysProportionMapper.getAllBySysProportion(clssname);
	}
}