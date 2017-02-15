/**   
 * @文件名称: RegionServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-21 上午09:52:25  
 */

package com.wxsoft.xyd.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.system.mapper.RegionMapper;
import com.wxsoft.xyd.system.model.Region;
import com.wxsoft.xyd.system.service.RegionService;

/**
 * @类功能说明：
 * @类修改者：kasiait
 * @修改日期：2013-3-21
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-21 上午09:52:25
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {

	// 地市代理类实例化对象
	@Autowired
	private RegionMapper regionMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wxltsoft.fxshop.common.service.RegionService#listAllAreaByCityId(
	 * java.lang.Integer)
	 */

	@Override
	public List<Region> listAllAreaByCityId(Integer cityid) {
		return regionMapper.listAllAreaByCityId(cityid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wxltsoft.fxshop.common.service.RegionService#listAllCityByProvinceId
	 * (java.lang.Integer)
	 */

	@Override
	public List<Region> listAllCityByProvinceId(Integer provinceid) {
		return regionMapper.listAllCityByProvinceId(provinceid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxltsoft.fxshop.common.service.RegionService#listAllProvince()
	 */

	@Override
	public List<Region> listAllProvince() {
		return regionMapper.listAllProvince();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxltsoft.fxshop.common.service.RegionService#getRegionName(int)
	 */

	@Override
	public String getRegionName(int regionid) {
		return this.regionMapper.getRegionName(regionid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wxltsoft.fxshop.common.service.RegionService#getlist(com.wxltsoft
	 * .fxshop.system.model.SysRegion)
	 */

	@Override
	public List<Region> getlist(Region region) {
		return this.regionMapper.getlist(region);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wxltsoft.fxshop.common.service.RegionService#getById(com.wxltsoft
	 * .fxshop.common.model.Region)
	 */

	@Override
	public Region getById(String regionid) {
		return this.regionMapper.getById(regionid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxltsoft.fxshop.common.service.RegionService#getByRegionId(int)
	 */

	@Override
	public Region getByRegionId(int regionid) {
		return this.regionMapper.getByRegionId(regionid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxltsoft.fxshop.common.service.RegionService#getAlphabetic(int)
	 */

	@Override
	public String getAlphabetic(int regionid) {
		return this.regionMapper.getAlphabetic(regionid);
	}

	@Override
	public List<Region> selectByUserid(String provinceId, String cityId,
			String areaId) {
		List<Region> regionList = new ArrayList<Region>();
		Region province;
		Region city;
		Region area;
		province = this.regionMapper.getById(provinceId);
		city = this.regionMapper.getById(cityId);
		area = this.regionMapper.getById(areaId);
		regionList.add(province);
		regionList.add(city);
		regionList.add(area);
		return regionList;

	}

	@Override
	public List<Region> listAllAreaByStreeId(Integer cityid) {
		// TODO Auto-generated method stub
		return regionMapper.listAllAreaByStreeId(cityid) ;
	}

}
