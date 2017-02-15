package com.wxsoft.xyd.prod.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: ProductStatistics.java
 * @类路径: com/wxsoft/xyd/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-18 14:56:35
 */
public class ProductStatistics extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer prodId;// 商品id 
	private Integer salesCount;// 销售量 
	private Integer commentHigh;// 好评量 
	private Integer commentMiddle;// 中评 
	private Integer commentLow;// 差评 
	private Integer commentScore;//评分总数
	private Integer salesG;// 销售 g数 
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public Integer getProdId(){  
		return prodId;  
	}  
	public void setProdId(Integer prodId){  
		this.prodId = prodId;  
	}  
	public Integer getSalesCount(){  
		return salesCount;  
	}  
	public void setSalesCount(Integer salesCount){  
		this.salesCount = salesCount;  
	}  
	public Integer getCommentHigh(){  
		return commentHigh;  
	}  
	public void setCommentHigh(Integer commentHigh){  
		this.commentHigh = commentHigh;  
	}  
	public Integer getCommentMiddle(){  
		return commentMiddle;  
	}  
	public void setCommentMiddle(Integer commentMiddle){  
		this.commentMiddle = commentMiddle;  
	}  
	public Integer getCommentLow(){  
		return commentLow;  
	}  
	public void setCommentLow(Integer commentLow){  
		this.commentLow = commentLow;  
	}  
	public Integer getSalesG(){  
		return salesG;  
	}  
	public void setSalesG(Integer salesG){  
		this.salesG = salesG;  
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
	public Integer getCommentScore() {
		return commentScore;
	}
	public void setCommentScore(Integer commentScore) {
		this.commentScore = commentScore;
	}  

}