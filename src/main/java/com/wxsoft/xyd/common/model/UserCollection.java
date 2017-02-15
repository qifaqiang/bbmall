package com.wxsoft.xyd.common.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: UserCollection.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-20 10:05:39
 */
public class UserCollection extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private Integer userId;//
	private Integer prodId;// 商品id
	private Date addTime;// 录入时间
	private Product p;
	private AjaxPage ajaxPage;// ajax分页

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

}