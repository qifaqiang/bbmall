package com.wxsoft.xyd.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.Company;

/**
 * @文件名称: Orders.java
 * @类路径: com/wxltsoft/schoolmgr/extra/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-25 09:56:19
 */
public class Orders extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;//
	private BigDecimal orderPrice;// 订单价格
	private BigDecimal shipPrice;// 运费价格
	private BigDecimal orderAccount;// 订单总价
	private BigDecimal promotionPrice;// 促销费用
	private BigDecimal couponsPrice;// 优惠券优惠
	private BigDecimal firstOrderPrice;//首单减免
	private String shipName;//发货公司
	private String shipCode;//物流编号
	private Date addendtime;

	private String status;// 0 取消 11未付款 20 已付款 22商家确认 30发货 40 完成
	private String payStatus;// 0未支付 1已支付
	private Integer userId;//
	private Integer shipAddressId;//
	private Integer payType;// 0微信支付 1支付宝支付  没有用*****
	private Integer companyId;//
	private String remark;// 备注
	private Integer count;// 统计数量
	private Integer is_over;// 0 可退货 1不可退货
	private Date shipTime;// 发货时间
	private String shipId;
	private Date acceptTime;// 收货时间
	private Date sysTime;// 系统确认时间
	private Date receivingTime;// 系统确认订单时间
	private String pickCode;// 提货码
	private Date addtime;// 下单时间
	private Date payTime;// 支付时间
	private Date cancelTime;// 订单取消时间

	private List<OrdersDetail> od;
	private OrdersLocation ul;
	private OrdersReturn rg;
	private String ordersn;
	private Date overTime;
	private Integer payForm;// 支付来源 1pc(支付宝) 3pc（银联支付） 4wap（支付宝）
							// 5微信 6wap（银联支付） 8订单价格为0
	private String tradingCode;// 交易流水号 随机生成
	private String serialid;// 支付返回编号
	private Integer couponsRecordId;// 优惠券id
	private Integer userShipType;// 用户选择 快递方式 1自提 2 商家配送
	private Integer sysShipType;// 系统选择快递方式 1商家配送 2第三方配送
	private Integer isComment;// 是否评价 0否 1是
	private AjaxPage ajaxPage;// ajax分页
	private Company company;

	public Integer getPayForm() {
		return payForm;
	}

	public void setPayForm(Integer payForm) {
		this.payForm = payForm;
	}
   
	public BigDecimal getFirstOrderPrice() {
		return firstOrderPrice;
	}

	public void setFirstOrderPrice(BigDecimal firstOrderPrice) {
		this.firstOrderPrice = firstOrderPrice;
	}

	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	public String getSerialid() {
		return serialid;
	}

	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}

	public Integer getCouponsRecordId() {
		return couponsRecordId;
	}

	public void setCouponsRecordId(Integer couponsRecordId) {
		this.couponsRecordId = couponsRecordId;
	}

	public Integer getUserShipType() {
		return userShipType;
	}

	public void setUserShipType(Integer userShipType) {
		this.userShipType = userShipType;
	}

	public Integer getSysShipType() {
		return sysShipType;
	}

	public void setSysShipType(Integer sysShipType) {
		this.sysShipType = sysShipType;
	}

	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	public Date getAddendtime() {
		return addendtime;
	}

	public void setAddendtime(Date addendtime) {
		this.addendtime = addendtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(BigDecimal shipPrice) {
		this.shipPrice = shipPrice;
	}

	public BigDecimal getOrderAccount() {
		return orderAccount;
	}

	public void setOrderAccount(BigDecimal orderAccount) {
		this.orderAccount = orderAccount;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getShipAddressId() {
		return shipAddressId;
	}

	public void setShipAddressId(Integer shipAddressId) {
		this.shipAddressId = shipAddressId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public List<OrdersDetail> getOd() {
		return od;
	}

	public void setOd(List<OrdersDetail> od) {
		this.od = od;
	}

	public Integer getIs_over() {
		return is_over;
	}

	public void setIs_over(Integer is_over) {
		this.is_over = is_over;
	}

	public OrdersLocation getUl() {
		return ul;
	}

	public void setUl(OrdersLocation ul) {
		this.ul = ul;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public OrdersReturn getRg() {
		return rg;
	}

	public void setRg(OrdersReturn rg) {
		this.rg = rg;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public BigDecimal getCouponsPrice() {
		return couponsPrice;
	}

	public void setCouponsPrice(BigDecimal couponsPrice) {
		this.couponsPrice = couponsPrice;
	}

	public Date getReceivingTime() {
		return receivingTime;
	}

	public void setReceivingTime(Date receivingTime) {
		this.receivingTime = receivingTime;
	}

	public String getPickCode() {
		return pickCode;
	}

	public void setPickCode(String pickCode) {
		this.pickCode = pickCode;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public AjaxPage getAjaxPage() {
		return ajaxPage;
	}

	public void setAjaxPage(AjaxPage ajaxPage) {
		this.ajaxPage = ajaxPage;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

}