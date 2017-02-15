/**   
 * @文件名称: SysNoticeServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.system.model.SysNotice;
import com.wxsoft.xyd.system.mapper.SysNoticeMapper;
import com.wxsoft.xyd.system.service.SysNoticeService;

@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
	
	@Autowired
	private SysNoticeMapper sysNoticeMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysNoticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysNotice record) {
		return sysNoticeMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysNotice record) {
		return sysNoticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysNotice selectByPrimaryKey(Integer id) {
		return sysNoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysNotice selectBySysNotice(SysNotice record) {
		return sysNoticeMapper.selectBySysNotice(record);
	}

	@Override
	public List<SysNotice> listPageBySysNotice(SysNotice clssname) {
		return sysNoticeMapper.listPageBySysNotice(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllBySysNotice(SysNotice clssname) {
		return sysNoticeMapper.getAllBySysNotice(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageCoupons(SysNotice clssname) {
		// TODO Auto-generated method stub
		return sysNoticeMapper.listAjaxPageCoupons(clssname);
	}
}