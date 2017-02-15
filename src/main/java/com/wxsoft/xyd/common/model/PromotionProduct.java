package com.wxsoft.xyd.common.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: PromotionProduct.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-01 11:02:42
 */
public class PromotionProduct extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//  
	private Integer prodId;// 商品id 
	private Integer promotionId;// 促销活动id 
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
	public Integer getPromotionId(){  
		return promotionId;  
	}  
	public void setPromotionId(Integer promotionId){  
		this.promotionId = promotionId;  
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