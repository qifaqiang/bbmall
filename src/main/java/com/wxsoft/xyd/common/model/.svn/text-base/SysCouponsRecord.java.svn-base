package com.wxsoft.xyd.common.model;

import java.math.BigDecimal;
import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * @文件名称: SysCouponsRecord.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-22 11:47:04
 */
public class SysCouponsRecord extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;//
	private Integer couponsId;// 优惠券id
	private Date addtime;//
	private Integer userId;// 领取人
	private Integer state;// 状态 1可用 2已使用 3已过期
	private Integer wFrom;// 来源 1领取 2赠送 3分享获取
	private String orderSn;//订单编号  来源为3的时候方便统计
	private CommonPage page;// 普通分页
	private AjaxPage ajaxPage;// ajax分页
	
	private Date endTime;// 结束时间
	private Date startTime;// 开始时间
	private String endTimeS;// 结束时间
	private String startTimeS;// 开始时间
	private BigDecimal needPrice;// 满多少
	private BigDecimal substractPrice;// 减多少
	private String mobile;//临时手机号

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

	public Integer getCouponsId() {
		return couponsId;
	}

	public void setCouponsId(Integer couponsId) {
		this.couponsId = couponsId;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getWFrom() {
		return wFrom;
	}

	public void setWFrom(Integer wFrom) {
		this.wFrom = wFrom;
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

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}


	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}