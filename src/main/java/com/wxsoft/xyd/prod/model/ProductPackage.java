package com.wxsoft.xyd.prod.model;

import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductPackage.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-26 15:38:24
 */
public class ProductPackage extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer prodBaseicId;// 基本字典id 
	private Integer inventorynumber;// 规格库存 
	private Integer prodId;// 商品id 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
	
	private int specificationInfoId;//该礼盒包里面包括的产品规格ID
	private int productId;//该礼盒包里面包括的产品ID
	private String productName;//该礼盒包里面包括的产品的名称
	private List<ProductSpecificationInfoDetail> psidList;//该规格下的具体规格值
	private int type;//类型 0代表着产品  1代表着规格
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public Integer getProdBaseicId(){  
		return prodBaseicId;  
	}  
	public void setProdBaseicId(Integer prodBaseicId){  
		this.prodBaseicId = prodBaseicId;  
	}  
	public Integer getInventorynumber(){  
		return inventorynumber;  
	}  
	public void setInventorynumber(Integer inventorynumber){  
		this.inventorynumber = inventorynumber;  
	}  
	public Integer getProdId(){  
		return prodId;  
	}  
	public void setProdId(Integer prodId){  
		this.prodId = prodId;  
	}  
	public CommonPage getPage(){  
		return page;  
	}  
	public void setPage(CommonPage page){  
		this.page = page;  
	}  
	public AjaxPage getAjaxPage(){  
		return ajaxPage;  
	}  
	public void setAjaxPage(AjaxPage ajaxPage){  
		this.ajaxPage = ajaxPage;  
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<ProductSpecificationInfoDetail> getPsidList() {
		return psidList;
	}
	public void setPsidList(List<ProductSpecificationInfoDetail> psidList) {
		this.psidList = psidList;
	}
	public int getSpecificationInfoId() {
		return specificationInfoId;
	}
	public void setSpecificationInfoId(int specificationInfoId) {
		this.specificationInfoId = specificationInfoId;
	}  

}