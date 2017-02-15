package com.wxsoft.xyd.common.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;

/**
 * @文件名称: RollPic.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-24 13:24:39
 */
public class RollPic extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;// 主题
	private String title;// 标题
	private String picUrl;// 图片地址
	private String linkUrl;// 链接地址
	private Integer sorts;// 排序
	private Integer isVisable;// 是否可用
	private Date addtime;// 录入时间
	private CommonPage page;// 普通分页
	private AjaxPage ajaxPage;// ajax分页
	private Integer type;// 1pc 2wap

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public Integer getIsVisable() {
		return isVisable;
	}

	public void setIsVisable(Integer isVisable) {
		this.isVisable = isVisable;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}