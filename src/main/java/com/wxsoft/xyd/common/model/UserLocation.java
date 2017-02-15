package com.wxsoft.xyd.common.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: UserLocation.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-23 16:19:08
 */
public class UserLocation extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private Integer userid;//
	private String consignee;// 收货人姓名
	private Integer country;//
	private Integer provinceId;//
	private Integer cityId;//
	private Integer areaId;//
	private String location;//
	private String mobile;//
	private Boolean status;// 0 不默认 1默认
	private String regionName;// 收货地址全称
	private String zipcode;//
	private String building;//
	private String created;//
	private AjaxPage ajaxPage;// ajax分页

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

}