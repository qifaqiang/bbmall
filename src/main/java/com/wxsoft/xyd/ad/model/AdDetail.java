package com.wxsoft.xyd.ad.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;


/**
 * @文件名称: AdDetail.java
 * @类路径: com/wxsoft/xyd/ad/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-09 22:04:38
 */
public class AdDetail extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;// 流水号 
	private String name;// 名称 
	private Date addtime;// 
	private Date startTime;//  
	private Date endTime;//  
	private Integer state;// 1可用2禁用 
	private Integer clickCount;// 点击次数 
	private Integer type;// 对应ad_config 
	private String picUrl;//  
	private Integer showCount;// 显示次数 
	private String url;//  
	private String usepicurl;//  
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
	public Date getAddtime(){  
		return addtime;  
	}  
	public void setAddtime(Date addtime){  
		this.addtime = addtime;  
	}  
	public Date getStartTime(){  
		return startTime;  
	}  
	public void setStartTime(Date startTime){  
		this.startTime = startTime;  
	}  
	public Date getEndTime(){  
		return endTime;  
	}  
	public void setEndTime(Date endTime){  
		this.endTime = endTime;  
	}  
	public Integer getState(){  
		return state;  
	}  
	public void setState(Integer state){  
		this.state = state;  
	}  
	public Integer getClickCount(){  
		return clickCount;  
	}  
	public void setClickCount(Integer clickCount){  
		this.clickCount = clickCount;  
	}  
	public Integer getType(){  
		return type;  
	}  
	public void setType(Integer type){  
		this.type = type;  
	}  
	public String getPicUrl(){  
		return picUrl;  
	}  
	public void setPicUrl(String picUrl){  
		this.picUrl = picUrl;  
	}  
	public Integer getShowCount(){  
		return showCount;  
	}  
	public void setShowCount(Integer showCount){  
		this.showCount = showCount;  
	}  
	public String getUrl(){  
		return url;  
	}  
	public void setUrl(String url){  
		this.url = url;  
	}  
	public String getUsepicurl(){  
		return usepicurl;  
	}  
	public void setUsepicurl(String usepicurl){  
		this.usepicurl = usepicurl;  
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