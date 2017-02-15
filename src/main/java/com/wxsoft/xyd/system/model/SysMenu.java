package com.wxsoft.xyd.system.model;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单类
 * 
 * @author kyz
 * 
 */
public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer visable;
	private String imgUrl;
	private String linkUrl;
	private Integer roleId;
	private String imgClass;
	private Integer typeId;
	private String roleIntercept;
	private Integer ord;
	private Integer level;
	private List<SysMenu> sysMenuList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getVisable() {
		return visable;
	}

	public void setVisable(Integer visable) {
		this.visable = visable;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}

	public String getImgClass() {
		return imgClass;
	}

	public void setImgClass(String imgClass) {
		this.imgClass = imgClass == null ? null : imgClass.trim();
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleIntercept() {
		return roleIntercept;
	}

	public void setRoleIntercept(String roleIntercept) {
		this.roleIntercept = roleIntercept;
	}

	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}

	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}