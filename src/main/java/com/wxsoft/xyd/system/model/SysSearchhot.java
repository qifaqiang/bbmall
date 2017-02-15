package com.wxsoft.xyd.system.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;

/**
 * @文件名称: SysSearchhot.java
 * @类路径: com/wxsoft/xyd/system/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-03 00:14:57
 */
public class SysSearchhot extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;//
	private String name;//
	private Date addtime;//
	private Integer sortn;// 从大到小
	private CommonPage page;// 普通分页
	private AjaxPage ajaxPage;// ajax分页

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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getSortn() {
		return sortn;
	}

	public void setSortn(Integer sortn) {
		this.sortn = sortn;
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