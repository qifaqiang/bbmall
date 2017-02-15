/**   
 * @文件名称: Region.java
 * @类路径: com.wxltsoft.fxshop.common.model
 * @描述: 
 * @作者：kasiaits
 * @时间：2013-3-21 上午09:19:03  
 */

package com.wxsoft.xyd.system.model;

import com.wxsoft.framework.bean.BaseBean;

/**
 * @类功能说明：
 * @类修改者：kasiait
 * @修改日期：2013-3-21
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-21 上午09:19:03
 */

public class Region extends BaseBean {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer regionid;   // 地市编号
	private String  regionname; // 地市名称
	private Integer parentid;   // 父节点编号
	private Integer levels;     // 层级
	private Integer sn;         //排序字段
	private String  alphabetic; //拼音码

	public Integer getRegionid() {
		return regionid;
	}
	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getAlphabetic() {
		return alphabetic;
	}
	public void setAlphabetic(String alphabetic) {
		this.alphabetic = alphabetic;
	}
	
}
