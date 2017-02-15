package com.wxsoft.xyd.common.model;

import java.math.BigDecimal;
import java.util.Date;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: PromotionActivity.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-01 17:40:46
 */
public class PromotionActivity extends BaseBean {
	private static final long serialVersionUID = 1L;

	private Integer id;//
	private String adDetailIds;//绑定的广告
	public String getAdDetailIds() {
		return adDetailIds;
	}

	public void setAdDetailIds(String adDetailIds) {
		this.adDetailIds = adDetailIds;
	}

	private Date addtime;//
	private Integer type;// 0满减 1满折
	private BigDecimal needPrice;// 满多少
	private BigDecimal substractPrice;// 减多少
	private Integer allProduct;// 是否全场商品 0否 1是
	private Integer discount;// 折扣
	private Date startTime;// 开市时间
	private Date endTime;// 结束时间
	private Integer delFlag;// 删除标志 1可用 0不可用
	private String tags;// 标签
	private String picUrl;// 推广图片1
	private String spreadUrl;// 推广图片2
	private String pcspreadUrl;// 推广图片2
	public String getPcspreadUrl() {
		return pcspreadUrl;
	}

	public void setPcspreadUrl(String pcspreadUrl) {
		this.pcspreadUrl = pcspreadUrl;
	}

	private String name;// 名称
	private Integer istop;// 是否推荐 0不推荐1推荐
	private CommonPage page;// 普通分页
	private AjaxPage ajaxPage;// ajax分页
    private String porduts;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAddtime() {
		return addtime;
	}
     
	 

	public String getPorduts() {
		return porduts;
	}

	public void setPorduts(String porduts) {
		this.porduts = porduts;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getNeedPrice() {
		return needPrice;
	}

	public void setNeedPrice(BigDecimal needPrice) {
		this.needPrice = needPrice;
	}

	public BigDecimal getSubstractPrice() {
		return substractPrice;
	}

	public void setSubstractPrice(BigDecimal substractPrice) {
		this.substractPrice = substractPrice;
	}

	public Integer getAllProduct() {
		return allProduct;
	}

	public void setAllProduct(Integer allProduct) {
		this.allProduct = allProduct;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

	public String getSpreadUrl() {
		return spreadUrl;
	}

	public void setSpreadUrl(String spreadUrl) {
		this.spreadUrl = spreadUrl;
	}

	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

}