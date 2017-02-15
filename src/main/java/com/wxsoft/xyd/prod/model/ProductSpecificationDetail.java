package com.wxsoft.xyd.prod.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductSpecificationDetail.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-03 10:40:13
 */
public class ProductSpecificationDetail extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer prodSpecId;//  
	private String detailName;//  
	private String addtime;//  
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public Integer getProdSpecId(){  
		return prodSpecId;  
	}  
	public void setProdSpecId(Integer prodSpecId){  
		this.prodSpecId = prodSpecId;  
	}  
	public String getDetailName(){  
		return detailName;  
	}  
	public void setDetailName(String detailName){  
		this.detailName = detailName;  
	}  
	public String getAddtime(){  
		return addtime;  
	}  
	public void setAddtime(String addtime){  
		this.addtime = addtime;  
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