package com.wxsoft.xyd.system.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: SysCouponsConf.java
 * @类路径: com/wxsoft/xyd/system/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-30 12:12:22
 */
public class SysCouponsConf extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private String picUrl;// 图片 
	private String content;// 规则 
	private String priceRange;// 金额范围 
	private Integer timeRange;// 有效期 
	private Integer count;// 总数量 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getPicUrl(){  
		return picUrl;  
	}  
	public void setPicUrl(String picUrl){  
		this.picUrl = picUrl;  
	}  
	public String getContent(){  
		return content;  
	}  
	public void setContent(String content){  
		this.content = content;  
	}  
	public String getPriceRange(){  
		return priceRange;  
	}  
	public void setPriceRange(String priceRange){  
		this.priceRange = priceRange;  
	}  
	public Integer getTimeRange(){  
		return timeRange;  
	}  
	public void setTimeRange(Integer timeRange){  
		this.timeRange = timeRange;  
	}  
	public Integer getCount(){  
		return count;  
	}  
	public void setCount(Integer count){  
		this.count = count;  
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