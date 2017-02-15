package com.wxsoft.xyd.common.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: User.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-03 17:23:20
 */
public class User extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;// id
	private String name;// 用户姓名
	private String mobile;// 手机
	private String email;// 邮箱
	private String picUrl;// 头像
	private Integer allowedPush;// 是否允许接受推送1允许 0不允许
	private String login;// login
	private String password;// password
	private Integer wxId;// 微信id
	private Integer isDel;// 是否可用1是0不是
	private String machineCode;// 机器码
	private Integer isFirstOrder;// 是否首单 0否 1是
	private String ip;// ip地址
	private String addtime;
	private Integer sex; // 0男 1女
	private Integer score;// 积分
	private Integer companyId;// 基地id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getAllowedPush() {
		return allowedPush;
	}

	public void setAllowedPush(Integer allowedPush) {
		this.allowedPush = allowedPush;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getWxId() {
		return wxId;
	}

	public void setWxId(Integer wxId) {
		this.wxId = wxId;
	}

	public Integer getIsFirstOrder() {
		return isFirstOrder;
	}

	public void setIsFirstOrder(Integer isFirstOrder) {
		this.isFirstOrder = isFirstOrder;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}