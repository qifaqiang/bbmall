package com.wxsoft.xyd.system.model;

import java.util.List;

import com.wxsoft.framework.bean.BaseBean;

public class UserRole extends BaseBean {
	private Integer id;

	private String name;

	private Boolean status;
	private String time;
	private String descn;

	private String rolecode;

	private Integer companyId;
	private CommonPage page;

	private List<UserRoleresource> userRoleresources;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn == null ? null : descn.trim();
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode == null ? null : rolecode.trim();
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<UserRoleresource> getUserRoleresources() {
		return userRoleresources;
	}

	public void setUserRoleresources(List<UserRoleresource> userRoleresources) {
		this.userRoleresources = userRoleresources;
	}
}