package com.wxsoft.xyd.common.model;

import java.util.Date;
import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: SysNewsCatalog.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-20 10:13:49
 */
public class SysNewsCatalog extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private String pic;//帮助图片
	private Integer isWep;//是否wep端显示，0，显示---1，不显示
	private Integer id;//  
	private String name;//  
	private Integer pid;//  
	private Date addtime;//  
	private Integer level;//  
	private Integer sortn;//  
	private CommonPage page;// 普通分页 
	private String namepid;
	private AjaxPage ajaxPage;// ajax分页 
	private List<SysNewsCatalog> sublist;
	private List<SysNews> sn;
	
	
	public List<SysNewsCatalog> getSublist() {
		return sublist;
	}
	public void setSublist(List<SysNewsCatalog> sublist) {
		this.sublist = sublist;
	}
	public String getNamepid() {
		return namepid;
	}
	public void setNamepid(String namepid) {
		this.namepid = namepid;
	}
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
	public Integer getPid(){  
		return pid;  
	}  
	public void setPid(Integer pid){  
		this.pid = pid;  
	}  
	public Date getAddtime(){  
		return addtime;  
	}  
	public void setAddtime(Date date){  
		this.addtime = date;  
	}  
	public Integer getLevel(){  
		return level;  
	}  
	public void setLevel(Integer level){  
		this.level = level;  
	}  
	public Integer getSortn(){  
		return sortn;  
	}  
	public void setSortn(Integer sortn){  
		this.sortn = sortn;  
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getIsWep() {
		return isWep;
	}
	public void setIsWep(Integer isWep) {
		this.isWep = isWep;
	}
	public List<SysNews> getSn() {
		return sn;
	}
	public void setSn(List<SysNews> sn) {
		this.sn = sn;
	}

}