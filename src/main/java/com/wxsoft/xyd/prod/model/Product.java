package com.wxsoft.xyd.prod.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: Product.java
 * @类路径: com/wxsoft/prod/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-02 20:03:01
 */
public class Product extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private AjaxPage ajaxPage;
	private Integer id;//
	private List<AdDetail> addeList;//活动页面广告
	public List<AdDetail> getAddeList() {
		return addeList;
	}

	public void setAddeList(List<AdDetail> addeList) {
		this.addeList = addeList;
	}

	private String name;// 商品名称
	private BigDecimal price;// 商品价格
	private String descn;// 商品描述
	private String picuri;// 商品图片
	private Boolean status;// 商品状态 0可用
	private Integer weight;// 重量
	private String modified;// 修改日期
	private Integer specid;//规格，只有普通商品有这个
	private String created;// 创建日期
	private Boolean paycode;// 付款方式 1支付宝 2微信支付
	private Integer inventorynumber;// 库存
	private String warningnumber;//
	private Integer companyId;//
	private String unit;// 单位
	private Integer istop;// 是否置首页 0否 1是
	private String remark;// 备注
	private String sn;//
	private BigDecimal marketPrice;// 市场价格
	private Integer delFlag;// 是否删除 0未删除 1删除
	private Double freight;// 运费
	private String inventoryCalculation;// 下订单减库存方式 1下单减库存 2支付减库存
	private String sellTime;// 开始销售时间
	private String sellEndTime;// 停止销售时间
	private Integer sellStatus;// 1 立即销售 0销售时间内才可以销售 2下架
	private Integer isSpecification;// 是否开启规格 1开启规格 0不启用规格
	private Integer tempCount;
	private Date addtime;// 录入时间
	private String addtimetype;
	private Integer vState;// 审核标志 1审核通过 2不通过 3未审核
	private String vContent;// 审核内容
	private String vTime;// 审核时间
	private Integer isSubscribe;// 是否开启预约 1开启 0不开启
	private Integer catalogId1;// 一级分类id
	private Integer catalogId2;// 二级分类id
	private Integer type;// 产品类别0:普通商品 1:礼盒商品
	private Integer score;// 评分 只累计好评总分
	private Integer prodTagsId;// 标签
	private String ptname;
	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	private Integer origin;//1本地 2进口
	private String note;//小描述
	private Integer prodBasicsId;// 商品字典id
    private Integer salesCount;//销量
	// 后台统计
	private String orderBy;// 排序规则

	private Integer tempSpecId;// 临时存放规格
	private String tempSpecName;// 临时存放规格
	private Integer tempCarid;// 临时存放购物车id
	
	private Integer commentHigh;//好评
	private Integer commentMiddle;//中评
	private Integer commentLow;//差评
	
	private List<ProductPackage> productPackageList;//礼盒包商品
	
	public Integer getCommentHigh() {
		return commentHigh;
	}

	public void setCommentHigh(Integer commentHigh) {
		this.commentHigh = commentHigh;
	}

	public Integer getCommentMiddle() {
		return commentMiddle;
	}

	public void setCommentMiddle(Integer commentMiddle) {
		this.commentMiddle = commentMiddle;
	}

	public Integer getCommentLow() {
		return commentLow;
	}

	public void setCommentLow(Integer commentLow) {
		this.commentLow = commentLow;
	}

	public Date getAddtime() {
		return addtime;
	}
   
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getvState() {
		return vState;
	}
    
	public String getAddtimetype() {
		return addtimetype;
	}

	public void setAddtimetype(String addtimetype) {
		this.addtimetype = addtimetype;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

	public void setvState(Integer vState) {
		this.vState = vState;
	}
        
	public Integer getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}

	public String getvContent() {
		return vContent;
	}

	public void setvContent(String vContent) {
		this.vContent = vContent;
	}

	public String getvTime() {
		return vTime;
	}

	public void setvTime(String vTime) {
		this.vTime = vTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public String getPicuri() {
		return picuri;
	}

	public void setPicuri(String picuri) {
		this.picuri = picuri;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}


	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Boolean getPaycode() {
		return paycode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setPaycode(Boolean paycode) {
		this.paycode = paycode;
	}

	public Integer getInventorynumber() {
		return inventorynumber;
	}

	public void setInventorynumber(Integer inventorynumber) {
		this.inventorynumber = inventorynumber;
	}

	public String getWarningnumber() {
		return warningnumber;
	}

	public void setWarningnumber(String warningnumber) {
		this.warningnumber = warningnumber;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public String getInventoryCalculation() {
		return inventoryCalculation;
	}

	public void setInventoryCalculation(String inventoryCalculation) {
		this.inventoryCalculation = inventoryCalculation;
	}

	public String getSellTime() {
		return sellTime;
	}

	public void setSellTime(String sellTime) {
		this.sellTime = sellTime;
	}

	public String getSellEndTime() {
		return sellEndTime;
	}

	public void setSellEndTime(String sellEndTime) {
		this.sellEndTime = sellEndTime;
	}

	public Integer getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(Integer sellStatus) {
		this.sellStatus = sellStatus;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public Integer getTempCount() {
		return tempCount;
	}

	public void setTempCount(Integer tempCount) {
		this.tempCount = tempCount;
	}

	public Integer getIsSpecification() {
		return isSpecification;
	}

	public void setIsSpecification(Integer isSpecification) {
		this.isSpecification = isSpecification;
	}

	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public Integer getTempSpecId() {
		return tempSpecId;
	}

	public void setTempSpecId(Integer tempSpecId) {
		this.tempSpecId = tempSpecId;
	}

	public String getTempSpecName() {
		return tempSpecName;
	}

	public void setTempSpecName(String tempSpecName) {
		this.tempSpecName = tempSpecName;
	}

	public Integer getTempCarid() {
		return tempCarid;
	}

	public void setTempCarid(Integer tempCarid) {
		this.tempCarid = tempCarid;
	}

	public Integer getCatalogId1() {
		return catalogId1;
	}

	public void setCatalogId1(Integer catalogId1) {
		this.catalogId1 = catalogId1;
	}

	public Integer getCatalogId2() {
		return catalogId2;
	}

	public void setCatalogId2(Integer catalogId2) {
		this.catalogId2 = catalogId2;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getProdTagsId() {
		return prodTagsId;
	}

	public void setProdTagsId(Integer prodTagsId) {
		this.prodTagsId = prodTagsId;
	}

	public Integer getProdBasicsId() {
		return prodBasicsId;
	}

	public void setProdBasicsId(Integer prodBasicsId) {
		this.prodBasicsId = prodBasicsId;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<ProductPackage> getProductPackageList() {
		return productPackageList;
	}

	public void setProductPackageList(List<ProductPackage> productPackageList) {
		this.productPackageList = productPackageList;
	}

	public Integer getSpecid() {
		return specid;
	}

	public void setSpecid(Integer specid) {
		this.specid = specid;
	}

}