package com.wxsoft.xyd.system.model;

import java.util.List;

import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;

public class ResultModel {
	public ResultModel(String title, String content, String type, String url) {
		super();
		this.title = title;
		this.content = content;
		this.url = url;
		this.type = type;
	}
	
	//俎春叶添加Invent属性，用来局部刷新库存信息表中的库存
	public ResultModel(String title, String content, String type, String url,Integer invent) {
		super();
		this.title = title;
		this.content = content;
		this.url = url;
		this.type = type;
		this.invent=invent;
	}
	//俎春叶添加List<Product>属性构造器，用来得到要删除的商品分类下的商品
	public ResultModel(String title, String content, String type, String url,List<Product> productList) {
		super();
		this.title = title;
		this.content = content;
		this.url = url;
		this.type = type;
		this.productList=productList;
	}
	//俎春叶添加List<Product>属性构造器，用来得到要删除的商品分类下的分类
	public ResultModel(String title, String content, String type, String url,List<ProductCatalog> productCatalogList,Integer invent) {
			super();
			this.title = title;
			this.content = content;
			this.url = url;
			this.type = type;
			this.invent=invent;
			this.productCatalogList=productCatalogList;
		}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public ResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String title;
	private String content;
	private String url;
	private String type;
	private Integer invent;
	private List<Product> productList;
	private List<ProductCatalog> productCatalogList;
	public List<ProductCatalog> getProductCatalogList() {
		return productCatalogList;
	}

	public void setProductCatalogList(List<ProductCatalog> productCatalogList) {
		this.productCatalogList = productCatalogList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Integer getInvent() {
		return invent;
	}
	public void setInvent(Integer invent) {
		this.invent = invent;
	}
}
