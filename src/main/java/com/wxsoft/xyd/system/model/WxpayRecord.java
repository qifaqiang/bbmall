package com.wxsoft.xyd.system.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;
import com.wxsoft.xyd.system.model.AjaxPage;


/**
 * @文件名称: WxpayRecord.java
 * @类路径: com/wxsoft/xyd/system/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-18 18:05:53
 */
public class WxpayRecord extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private String isSubscribe;//  
	private Integer id;//  
	private String appid;//  
	private String feeType;//  
	private String nonceStr;//  
	private String outTradeNo;//  
	private String transactionId;//  
	private String tradeType;//  
	private String resultCode;//  
	private String sign;//  
	private String mchId;//  
	private String totalFee;//  
	private String timeEnd;//  
	private String openid;//  
	private String bankType;//  
	private String returnCode;//  
	private String cashFee;//  
	private CommonPage page;// 普通分页 
	private AjaxPage ajaxPage;// ajax分页 
  
	public String getIsSubscribe(){  
		return isSubscribe;  
	}  
	public void setIsSubscribe(String isSubscribe){  
		this.isSubscribe = isSubscribe;  
	}  
	public Integer getId(){  
		return id;  
	}  
	public void setId(Integer id){  
		this.id = id;  
	}  
	public String getAppid(){  
		return appid;  
	}  
	public void setAppid(String appid){  
		this.appid = appid;  
	}  
	public String getFeeType(){  
		return feeType;  
	}  
	public void setFeeType(String feeType){  
		this.feeType = feeType;  
	}  
	public String getNonceStr(){  
		return nonceStr;  
	}  
	public void setNonceStr(String nonceStr){  
		this.nonceStr = nonceStr;  
	}  
	public String getOutTradeNo(){  
		return outTradeNo;  
	}  
	public void setOutTradeNo(String outTradeNo){  
		this.outTradeNo = outTradeNo;  
	}  
	public String getTransactionId(){  
		return transactionId;  
	}  
	public void setTransactionId(String transactionId){  
		this.transactionId = transactionId;  
	}  
	public String getTradeType(){  
		return tradeType;  
	}  
	public void setTradeType(String tradeType){  
		this.tradeType = tradeType;  
	}  
	public String getResultCode(){  
		return resultCode;  
	}  
	public void setResultCode(String resultCode){  
		this.resultCode = resultCode;  
	}  
	public String getSign(){  
		return sign;  
	}  
	public void setSign(String sign){  
		this.sign = sign;  
	}  
	public String getMchId(){  
		return mchId;  
	}  
	public void setMchId(String mchId){  
		this.mchId = mchId;  
	}  
	public String getTotalFee(){  
		return totalFee;  
	}  
	public void setTotalFee(String totalFee){  
		this.totalFee = totalFee;  
	}  
	public String getTimeEnd(){  
		return timeEnd;  
	}  
	public void setTimeEnd(String timeEnd){  
		this.timeEnd = timeEnd;  
	}  
	public String getOpenid(){  
		return openid;  
	}  
	public void setOpenid(String openid){  
		this.openid = openid;  
	}  
	public String getBankType(){  
		return bankType;  
	}  
	public void setBankType(String bankType){  
		this.bankType = bankType;  
	}  
	public String getReturnCode(){  
		return returnCode;  
	}  
	public void setReturnCode(String returnCode){  
		this.returnCode = returnCode;  
	}  
	public String getCashFee(){  
		return cashFee;  
	}  
	public void setCashFee(String cashFee){  
		this.cashFee = cashFee;  
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