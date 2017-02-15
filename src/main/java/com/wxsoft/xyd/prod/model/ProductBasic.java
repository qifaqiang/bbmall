package com.wxsoft.xyd.prod.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductBasic.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-25 14:47:38
 */
public class ProductBasic extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private String name;// 名称 
	private Integer type;// 是否平台商品，0不是  1是 
	private String unit;// 单位 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getName(){  
		return name;  
	}  
	public void setName(String name){  
		this.name = name;  
	}  
	public Integer getType(){  
		return type;  
	}  
	public void setType(Integer type){  
		this.type = type;  
	}  
	public String getUnit(){  
		return unit;  
	}  
	public void setUnit(String unit){  
		this.unit = unit;  
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