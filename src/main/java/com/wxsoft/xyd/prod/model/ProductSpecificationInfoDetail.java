package com.wxsoft.xyd.prod.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductSpecificationInfoDetail.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-05 10:28:20
 */
public class ProductSpecificationInfoDetail extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer specificationInfoId;//  
	private Integer specificatonId;//  
	private Integer specificationDetailId;//  
	private String createtime;//  
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
	private String specificationDetailName;//规格名称
	
	private String specificationName;
	
  
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
	public Integer getSpecificatonId(){  
		return specificatonId;  
	}  
	public void setSpecificatonId(Integer specificatonId){  
		this.specificatonId = specificatonId;  
	}  
	public Integer getSpecificationDetailId(){  
		return specificationDetailId;  
	}  
	public void setSpecificationDetailId(Integer specificationDetailId){  
		this.specificationDetailId = specificationDetailId;  
	}  
	public String getCreatetime(){  
		return createtime;  
	}  
	public void setCreatetime(String createtime){  
		this.createtime = createtime;  
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
	public String getSpecificationDetailName() {
		return specificationDetailName;
	}
	public void setSpecificationDetailName(String specificationDetailName) {
		this.specificationDetailName = specificationDetailName;
	}
	public String getSpecificationName() {
		return specificationName;
	}
	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}  

}