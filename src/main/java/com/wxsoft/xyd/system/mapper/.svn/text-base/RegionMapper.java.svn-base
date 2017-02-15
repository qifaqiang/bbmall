/**   
 * @文件名称: RegionMapper.java
 * @类路径: com.wxltsoft.fxshop.common.mapper
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-21 上午09:23:26  
 */

package com.wxsoft.xyd.system.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.system.model.Region;


/**
 * @类功能说明：
 * @类修改者：kasiait
 * @修改日期：2013-3-21
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-21 上午09:23:26
 */
@Repository
public interface RegionMapper {
	
	/**
	 * 
	 * @描述: 查询所有省份信息
	 * @作者: kasiaits
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @return    
	 * @return List<Region>   
	 * @throws
	 */
	List<Region> listAllProvince();
	
	/**
	 * 
	 * @描述: 根据省份编号查询所有城市信息
	 * @作者: kasiaits
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @param provinceid
	 * @参数： @return    
	 * @return List<Region>   
	 * @throws
	 */
	List<Region> listAllCityByProvinceId(Integer provinceid);
	

	/**
	 * 
	 * @描述: 根据城市编号查询所有地区信息
	 * @作者: kasiaits
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @param provinceid
	 * @参数： @return    
	 * @return List<Region>   
	 * @throws
	 */
	List<Region> listAllAreaByCityId(Integer cityid);
	
	/**
	 * 
	 * @描述: 根据地市编号查询地市信息
	 * @作者: kasiaits
	 * @日期:2013-6-22
	 * @修改内容
	 * @参数： @param id
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	String    getRegionName(int regionid);
   List<Region>	listAllAreaByStreeId(Integer cityid);
	/**
	 * 
	 * @描述: 根据地市信息查询地市列表
	 * @作者: kasiaits
	 * @日期:2013-6-22
	 * @修改内容
	 * @参数： @param sysRegion
	 * @参数： @return    
	 * @return List<SysRegion>   
	 * @throws
	 */
	List<Region> getlist(Region region);
	/**
	 * 
	 * @描述: 根据地市编号查询地市信息
	 * @作者: kasiaits
	 * @日期:2013-6-22
	 * @修改内容
	 * @参数： @param id
	 * @参数： @return    
	 * @return SysRegion   
	 * @throws
	 */
	Region getById(String regionid);
	
	/**
	 * 
	 * @描述: 根据编号获取地市名称
	 * @作者: kasiaits
	 * @日期:2013-6-22
	 * @修改内容
	 * @参数： @param regionid
	 * @参数： @return    
	 * @return Region   
	 * @throws
	 */
	Region getByRegionId(int regionid);
	
	/**
	 * 
	 * @描述: 获取缩写字符串
	 * @作者: kasiaits
	 * @日期:2013-7-9
	 * @修改内容
	 * @参数： @param regionid
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	String getAlphabetic(int regionid);
}
