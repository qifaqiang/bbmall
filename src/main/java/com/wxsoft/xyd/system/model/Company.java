/**   
 * @文件名称: Company.java
 * @类路径: com.wxltsoft.fxshop.common.model
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-29 下午04:47:05  
 */

package com.wxsoft.xyd.system.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.framework.util.XssProtect;

/**
 * @类功能说明：公司
 * @类修改者：kyz
 * @修改日期：2013-8-2 @修改说明： @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-2 上午08:29:05
 */

public class Company extends BaseBean {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;

	private Integer companyId; // 公司编号
	private String companyName; // 公司名称
	private String companyDes; // 公司描述
	private String companyService; // 公司服务
	private String companyContact;// 公司内容
	private String telephone; // 手机
	private String fax; // 传真
	private String mobile; // 手机
	private String email;// 邮箱
	private String address; // 地址 街道信息
	private String login; // 登陆名
	private String passwd;// 密码
	private int isdel;
	private UserRole userRole;
	private String addtime;
	private CommonPage page;
	private Integer provinceId;// 省id
	private Integer cityId;// 市id
	private Integer areaId;// 区id
	private Integer streetId;//街道id
	private String location;// 街道信息
	private String regionName;// 省市区名称
	private double xpostion;// x坐标
	private double ypostion;// y坐标
	private Integer saleCount;// 基地销售量
	private Integer sendPrice;// 起送价
	private Integer chargeSendPrice;// 不足起送价所收取运费
	private String qrcodeUrl;//二维码图片

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getXpostion() {
		return xpostion;
	}

	public void setXpostion(double xpostion) {
		this.xpostion = xpostion;
	}

	public double getYpostion() {
		return ypostion;
	}

	public void setYpostion(double ypostion) {
		this.ypostion = ypostion;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = XssProtect.protectAgainstltAndGt(companyName);
	}

	public String getCompanyDes() {
		return companyDes;
	}

	public void setCompanyDes(String companyDes) {
		this.companyDes = XssProtect.protectAgainstltAndGt(companyDes);
	}

	public String getCompanyService() {
		return companyService;
	}

	public void setCompanyService(String companyService) {
		this.companyService = XssProtect.protectAgainstltAndGt(companyService);
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = XssProtect.protectAgainstltAndGt(companyContact);
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = XssProtect.protectAgainstltAndGt(telephone);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = XssProtect.protectAgainstltAndGt(fax);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = XssProtect.protectAgainstltAndGt(mobile);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = XssProtect.protectAgainstltAndGt(email);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = XssProtect.protectAgainstltAndGt(address);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = XssProtect.protectAgainstltAndGt(login);
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = XssProtect.protectAgainstltAndGt(passwd);
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getSendPrice() {
		return sendPrice;
	}

	public void setSendPrice(Integer sendPrice) {
		this.sendPrice = sendPrice;
	}

	public Integer getChargeSendPrice() {
		return chargeSendPrice;
	}

	public void setChargeSendPrice(Integer chargeSendPrice) {
		this.chargeSendPrice = chargeSendPrice;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

}
