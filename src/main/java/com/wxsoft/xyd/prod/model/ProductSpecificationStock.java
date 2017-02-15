package com.wxsoft.xyd.prod.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductSpecificationStock.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-09 14:35:08
 */
public class ProductSpecificationStock extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer specificationInfoId;//  
	private Integer inventorycount;// 库存 
	private Integer productId;//  
	private Integer companyId;//  
	private Integer type;// 0是产品  1是规格 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public Integer getSpecificationInfoId(){  
		return specificationInfoId;  
	}  
	public void setSpecificationInfoId(Integer specificationInfoId){  
		this.specificationInfoId = specificationInfoId;  
	}  
	public Integer getInventorycount(){  
		return inventorycount;  
	}  
	public void setInventorycount(Integer inventorycount){  
		this.inventorycount = inventorycount;  
	}  
	public Integer getProductId(){  
		return productId;  
	}  
	public void setProductId(Integer productId){  
		this.productId = productId;  
	}  
	public Integer getCompanyId(){  
		return companyId;  
	}  
	public void setCompanyId(Integer companyId){  
		this.companyId = companyId;  
	}  
	public Integer getType(){  
		return type;  
	}  
	public void setType(Integer type){  
		this.type = type;  
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

}