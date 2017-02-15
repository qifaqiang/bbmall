package com.wxsoft.xyd.common.model;

import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: UserComment.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-25 15:04:11
 */
public class UserComment extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;// 主键 
	private String content;// 内容 
	private Integer prodId;// 商品id 
	private Integer starCount;// 星星数量 
	private String name;//用户姓名
	private String picurl;// 图片 
	private Integer vState;// 审核状态1审核通过，2审核不通过，3未审核 
	private String vComment;// 审核信息 
	private Date vTime;// 审核时间 
	private Integer userId;// 用户ID 
	private Date addtime;// 用户评价时间 
	private CommonPage page;// 普通分页 
	private Integer  orderDetailId;//订单详情ID
	private Integer commentLevel;//评论等级 1 好评 2中评 3差评
	private String productName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getvState(){  
		return vState;  
	}  
	public void setvState(Integer vState){  
		this.vState = vState;  
	} 
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getContent(){  
		return content;  
	}  
	public void setContent(String content){  
		this.content = content;  
	}  
	public Integer getProdId(){  
		return prodId;  
	}  
	public void setProdId(Integer prodId){  
		this.prodId = prodId;  
	}  
	public Integer getStarCount(){  
		return starCount;  
	}  
	public void setStarCount(Integer starCount){  
		this.starCount = starCount;  
	}  
	public String getPicurl(){  
		return picurl;  
	}  
	public void setPicurl(String picurl){  
		this.picurl = picurl;  
	}  
	public String getvComment(){  
		return vComment;  
	}  
	public void setvComment(String vComment){  
		this.vComment = vComment;  
	}  
	public Date getvTime(){  
		return vTime;  
	}  
	public void setvTime(Date vTime){  
		this.vTime = vTime;  
	}  
	public Integer getUserId(){  
		return userId;  
	}  
	public void setUserId(Integer userId){  
		this.userId = userId;  
	}  
	public Date getAddtime(){  
		return addtime;  
	}  
	public void setAddtime(Date addtime){  
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
	public Integer getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(Integer commentLevel) {
		this.commentLevel = commentLevel;
	}  

}