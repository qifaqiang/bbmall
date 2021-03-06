package com.wxsoft.xyd.prod.model;

import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: ProductSpecification.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-16 10:40:23
 */
public class ProductSpecification extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private Integer companyId;//
	private String specificationName;// 规格名称
	private String addtime;//
	private List<ProductSpecificationDetail> specDetailList;//规格具体值

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public List<ProductSpecificationDetail> getSpecDetailList() {
		return specDetailList;
	}

	public void setSpecDetailList(List<ProductSpecificationDetail> specDetailList) {
		this.specDetailList = specDetailList;
	}
}