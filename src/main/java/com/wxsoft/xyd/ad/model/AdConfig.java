package com.wxsoft.xyd.ad.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: AdConfig.java
 * @类路径: com/wxsoft/xyd/ad/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-09 22:04:10
 */
public class AdConfig extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private String name;// 名称 
	private String remark;// 备注 
	private Integer width;// 图片宽度 
	private Integer high;// 图片高度 
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
	public String getRemark(){  
		return remark;  
	}  
	public void setRemark(String remark){  
		this.remark = remark;  
	}  
	public Integer getWidth(){  
		return width;  
	}  
	public void setWidth(Integer width){  
		this.width = width;  
	}  
	public Integer getHigh(){  
		return high;  
	}  
	public void setHigh(Integer high){  
		this.high = high;  
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