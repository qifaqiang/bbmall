package com.wxsoft.xyd.order.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: OrdersLocation.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-03-02 16:45:46
 */
public class OrdersLocation extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private Integer orderId;//订单id
	private String consignee;// 收货人姓名
	private String mobile;//手机
	private String addressName;// 收货地址全称
	private String created;//创建时间
	private String zipcode;//邮编

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}