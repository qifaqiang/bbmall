package com.wxsoft.xyd.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: SysCoupons.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-21 10:43:11
 */
public class SysCoupons extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;//
	private String name;// 优惠券名称
	private String remark;// 备注
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Date addtime;// 录入时间
	private String endTimeS;
	private String startTimeS;
	private BigDecimal needPrice;// 满多少
	private BigDecimal substractPrice;// 减多少
	private Integer adduserid;// 录入人
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer allCount;// 总数量
	private Integer userId;
	private Integer leftCount;// 剩余数量
	private Integer type;// 是否注册赠送 0否 1是
	private CommonPage page;// 普通分页
	private Integer state;//是否可用  1进行中 2未开始  3一结束
	private AjaxPage ajaxPage;// ajax分页
	private Integer delFlag;//是否可用   1是   0不是
	private Integer validTime;//领取后有效时间
	
	private List<SysCouponsRecord> sysCouponsRecordList;

	
	public List<SysCouponsRecord> getSysCouponsRecordList() {
		return sysCouponsRecordList;
	}

	public void setSysCouponsRecordList(List<SysCouponsRecord> sysCouponsRecordList) {
		this.sysCouponsRecordList = sysCouponsRecordList;
	}

	public String getEndTimeS() {
		return endTimeS;
	}

	public void setEndTimeS(String endTimeS) {
		this.endTimeS = endTimeS;
	}

	public String getStartTimeS() {
		return startTimeS;
	}

	public void setStartTimeS(String startTimeS) {
		this.startTimeS = startTimeS;
	}
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
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public BigDecimal getNeedPrice() {
		return needPrice;
	}

	public void setNeedPrice(BigDecimal needPrice) {
		this.needPrice = needPrice;
	}

	public BigDecimal getSubstractPrice() {
		return substractPrice;
	}

	public void setSubstractPrice(BigDecimal substractPrice) {
		this.substractPrice = substractPrice;
	}

	public Integer getAdduserid() {
		return adduserid;
	}

	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public Integer getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(Integer leftCount) {
		this.leftCount = leftCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

}