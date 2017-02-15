package com.wxsoft.xyd.prod.model;

import java.math.BigDecimal;
import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;

/**
 * @文件名称: ProductSpecificationInfo.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-07-16 20:07:27
 */
public class ProductSpecificationInfo extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private Integer specId;//
	private String value;//
	private BigDecimal price;// 价格
	private Integer inventorynumber;//存量 用这个和每个基地的库存量挂钩
	private BigDecimal marketPrice;// 市场价格
	private Integer productId;//
	private String delFlag;//删除标志  0未删除  1已删除
	private List<ProductSpecificationInfoDetail> specInfoDetailList;//具体的规格值
	private String productName;//商品名称
	private String unit;//单位
	
	private String type;//类型 1代表规格   0代表产品
	
	private String catalogId;//分类ID
	private AjaxPage ajaxPage;
	
	private int companyId;//公司id
	private int stockId;//库存Id
	private Integer inventorycount;//库存量  用这个 上面那个没用到
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getInventorynumber() {
		return inventorynumber;
	}

	public void setInventorynumber(Integer inventorynumber) {
		this.inventorynumber = inventorynumber;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<ProductSpecificationInfoDetail> getSpecInfoDetailList() {
		return specInfoDetailList;
	}

	public void setSpecInfoDetailList(
			List<ProductSpecificationInfoDetail> specInfoDetailList) {
		this.specInfoDetailList = specInfoDetailList;
	}

	public String getProductName() {
		return productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

	public int getCompanyId() {
		return companyId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Integer getInventorycount() {
		return inventorycount;
	}

	public void setInventorycount(Integer inventorycount) {
		this.inventorycount = inventorycount;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}