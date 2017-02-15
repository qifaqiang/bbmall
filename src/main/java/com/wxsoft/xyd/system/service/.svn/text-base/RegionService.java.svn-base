/**   
 * @文件名称: RegionService.java
 * @类路径: com.wxltsoft.fxshop.common.service
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-3-21 上午09:49:20  
 */

package com.wxsoft.xyd.system.service;

import java.util.List;

import com.wxsoft.xyd.system.model.Region;

/**
 * @类功能说明：地市信息表接口定义
 * @类修改者：kyz
 * @修改日期：2013-3-21
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-3-21 上午09:49:20
 */

public interface RegionService {

	/**
	 * 
	 * @描述: 查询所有省份信息
	 * @作者: kyz
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
	 * @作者: kyz
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @param provinceid
	 * @参数： @return    
	 * @return List<Resgion>   
	 * @throws
	 */
	List<Region> listAllCityByProvinceId(Integer provinceid);
	
   List<Region>	listAllAreaByStreeId(Integer cityid);
	/**
	 * 
	 * @描述: 根据城市编号查询所有地区信息
	 * @作者: kyz
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @param cityid
	 * @参数： @return    
	 * @return List<Resgion>   
	 * @throws
	 */
	List<Region> listAllAreaByCityId(Integer cityid);
	
	/**
	 * 
	 * @描述: 根据地区编号获取地区名称
	 * @作者: kyz
	 * @日期:2013-6-21
	 * @修改内容
	 * @参数： @param id
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	String       getRegionName(int regionid);
	
	/**
	 * @描述: 获取所有地市信息
	 * @作者: kyz
	 * @日期:2013-6-22
	 * @修改内容
	 * @参数： @param sysRegion
	 * @参数： @return    
	 * @return List<SysRegion>   
	 * @throws
	 */
	List<Region> getlist(Region region);
	
	List<Region> selectByUserid(String provinceId,String cityId,String areaId);
		
	 
	
	/**
	 * 
	 * @描述: 根据地市编号查询地市信息
	 * @作者: kyz
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
	 * @作者: kyz
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
	 * @作者: kyz
	 * @日期:2013-7-9
	 * @修改内容
	 * @参数： @param regionid
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	String getAlphabetic(int regionid);
}
