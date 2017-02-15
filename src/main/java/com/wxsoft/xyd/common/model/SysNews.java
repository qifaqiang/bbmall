package com.wxsoft.xyd.common.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: SysNews.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 11:19:22
 */
public class SysNews extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;// 主题 
	private String title;// 标题 
	private String content;// 内容 
	private Date addtime;// 录入时间 
	private String name; //父分类id
	private String pname;//父分类的根分类ID
	private Integer catalogId;// 分类ID 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getTitle(){  
		return title;  
	}  
	public void setTitle(String title){  
		this.title = title;  
	}  
	public String getContent(){  
		return content;  
	}  
	public void setContent(String content){  
		this.content = content;  
	}  
	public Date getAddtime(){  
		return addtime;  
	}  
	public void setAddtime(Date date){  
		this.addtime = date;  
	}  
	public Integer getCatalogId(){  
		return catalogId;  
	}  
	public void setCatalogId(Integer catalogId){  
		this.catalogId = catalogId;  
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