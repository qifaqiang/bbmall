package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.common.mapper.SysNewsCatalogMapper;
import com.wxsoft.xyd.common.service.SysNewsCatalogService;

@Service("sysNewsCatalogService")
public class SysNewsCatalogServiceImpl implements SysNewsCatalogService {
	
	@Autowired
	private SysNewsCatalogMapper sysNewsCatalogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysNewsCatalogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysNewsCatalog record) {
		return sysNewsCatalogMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysNewsCatalog record) {
		return sysNewsCatalogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysNewsCatalog selectByPrimaryKey(Integer id) {
		return sysNewsCatalogMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysNewsCatalog selectBySysNewsCatalog(SysNewsCatalog record) {
		return sysNewsCatalogMapper.selectBySysNewsCatalog(record);
	}

	@Override
	public List<SysNewsCatalog> listPageBySysNewsCatalog(SysNewsCatalog clssname) {
		return sysNewsCatalogMapper.listPageBySysNewsCatalog(clssname);
	}

	@Override
	public List<SysNewsCatalog> getAllBySysNewsCatalog(SysNewsCatalog clssname) {
		List<SysNewsCatalog> pclist = sysNewsCatalogMapper.getAllBySysNewsCatalog(clssname);
		for (SysNewsCatalog prodCatalog : pclist) {
			SysNewsCatalog temp = new SysNewsCatalog();
			temp.setPid(prodCatalog.getId());
			List<SysNewsCatalog> tempList = sysNewsCatalogMapper.getAllBySysNewsCatalog(temp);
			prodCatalog.setSublist(tempList);
		}
		return pclist;
	}
	
	@Override
	public List<SysNewsCatalog> getFirstProdCatalog() {
		// TODO Auto-generated method stub
		SysNewsCatalog pc = new SysNewsCatalog();
		pc.setPid(0);
		List<SysNewsCatalog> pclist = sysNewsCatalogMapper.getAllBySysNewsCatalog(pc);
		return pclist;
	}
//查询等级都等于二的帮助类型
	@Override
	public List<SysNewsCatalog> getProdCatalog() {
		// TODO Auto-generated method stub
		SysNewsCatalog pc = new SysNewsCatalog();
		pc.setLevel(2);
		List<SysNewsCatalog> pclist = sysNewsCatalogMapper.getAllBySysNewsCatalog(pc);
		return pclist;
	}

	@Override
	public int deleteByPid(Integer id) {
		// TODO Auto-generated method stub
		return sysNewsCatalogMapper.deleteByPid(id);
	}
}