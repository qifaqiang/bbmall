package com.wxsoft.xyd.common.model;

import java.math.BigDecimal;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: SysShipping.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-26 15:19:32
 */
public class SysShipping extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private String name;//
	private BigDecimal price;//
	private Integer companyId;//
	private String code;//

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}
}