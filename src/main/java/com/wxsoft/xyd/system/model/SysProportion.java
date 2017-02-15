package com.wxsoft.xyd.system.model;

import java.math.BigDecimal;

import com.wxsoft.framework.bean.BaseBean;

/**
 * @文件名称: SysProportion.java
 * @类路径: com/wxsoft/xyd/system/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 18:31:51
 */
public class SysProportion extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键
	private Double androidVersion;// 安卓版本
	private String androidCode;// 安卓
	private String alipayPartner;// 
	private String alipayKey;//
	private String alipaySellerEmail;//
	private String emailHost;//
	private String emailUser;//
	private String emailPasswd;//
	private Integer emailPort;//
	private String emailAttr;//
	private String smsUser;//
	private String smsPasswd;//
	private Integer scorePoint;// 积分兑换比例
	private Integer autoAcceptTime;// 多少天自动确认收货
	private Integer autoCancelTime;// 多少小时自动取消订单
	private String mobile;// 联系电话
	private String aboutUs;// 关于我们
	private Integer highPraiseScope;// 好评
	private Integer middlePraiseScope;// 中评
	private Integer badPraiseScope;// 差评
	private BigDecimal firstSubtractPrice;// 首单减免
	private String copyrights;//版权所有
	private CommonPage page;// 普通分页
	private AjaxPage ajaxPage;// ajax分页

	private String qq;
	private Integer autoOverTime;//自动结束时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(Double androidVersion) {
		this.androidVersion = androidVersion;
	}

	public String getAndroidCode() {
		return androidCode;
	}

	public void setAndroidCode(String androidCode) {
		this.androidCode = androidCode;
	}

	public String getAlipayPartner() {
		return alipayPartner;
	}

	public void setAlipayPartner(String alipayPartner) {
		this.alipayPartner = alipayPartner;
	}

	public String getAlipayKey() {
		return alipayKey;
	}

	public void setAlipayKey(String alipayKey) {
		this.alipayKey = alipayKey;
	}

	public String getAlipaySellerEmail() {
		return alipaySellerEmail;
	}

	public void setAlipaySellerEmail(String alipaySellerEmail) {
		this.alipaySellerEmail = alipaySellerEmail;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getEmailPasswd() {
		return emailPasswd;
	}

	public void setEmailPasswd(String emailPasswd) {
		this.emailPasswd = emailPasswd;
	}

	public Integer getEmailPort() {
		return emailPort;
	}

	public void setEmailPort(Integer emailPort) {
		this.emailPort = emailPort;
	}

	public String getEmailAttr() {
		return emailAttr;
	}

	public void setEmailAttr(String emailAttr) {
		this.emailAttr = emailAttr;
	}

	public String getSmsUser() {
		return smsUser;
	}

	public void setSmsUser(String smsUser) {
		this.smsUser = smsUser;
	}

	public String getSmsPasswd() {
		return smsPasswd;
	}

	public void setSmsPasswd(String smsPasswd) {
		this.smsPasswd = smsPasswd;
	}

	public Integer getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(Integer scorePoint) {
		this.scorePoint = scorePoint;
	}

	public Integer getAutoAcceptTime() {
		return autoAcceptTime;
	}

	public void setAutoAcceptTime(Integer autoAcceptTime) {
		this.autoAcceptTime = autoAcceptTime;
	}

	public Integer getAutoCancelTime() {
		return autoCancelTime;
	}

	public void setAutoCancelTime(Integer autoCancelTime) {
		this.autoCancelTime = autoCancelTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Integer getHighPraiseScope() {
		return highPraiseScope;
	}

	public void setHighPraiseScope(Integer highPraiseScope) {
		this.highPraiseScope = highPraiseScope;
	}

	public Integer getMiddlePraiseScope() {
		return middlePraiseScope;
	}

	public void setMiddlePraiseScope(Integer middlePraiseScope) {
		this.middlePraiseScope = middlePraiseScope;
	}

	public Integer getBadPraiseScope() {
		return badPraiseScope;
	}

	public void setBadPraiseScope(Integer badPraiseScope) {
		this.badPraiseScope = badPraiseScope;
	}

	public BigDecimal getFirstSubtractPrice() {
		return firstSubtractPrice;
	}

	public void setFirstSubtractPrice(BigDecimal firstSubtractPrice) {
		this.firstSubtractPrice = firstSubtractPrice;
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

	public Integer getAutoOverTime() {
		return autoOverTime;
	}

	public void setAutoOverTime(Integer autoOverTime) {
		this.autoOverTime = autoOverTime;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCopyrights() {
		return copyrights;
	}

	public void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}

}