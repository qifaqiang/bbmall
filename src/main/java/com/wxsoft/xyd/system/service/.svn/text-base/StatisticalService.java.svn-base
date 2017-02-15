package com.wxsoft.xyd.system.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.xyd.system.model.Company;

/**
 * @文件名称: StatisticalService.java
 * @类路径: com/wxltsoft/scancode/system/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-05-28 14:04:49
 */
public interface StatisticalService {
	// 首页统计四块
	List<Map<String, Object>> selectMainSql(Company c);

	// 首页统计 最近7天订单数据
	JSONObject selectMainLastOrderSql(Company c);

	// 首页统计 最近7天交易数据
	JSONObject selectMainLastChargeSql(Company c);

	// 会员统计 统计昨天的数据
	JSONObject selectMemberLastOneDay(Company c);

	// 会员统计 普通 统计昨天的数据
	JSONObject selectMemberComonLastOneDay(Company c);

	// 会员统计 统计时间范围的数据
	JSONObject selectMemberRangeDay(String type, String times);

	JSONObject selectTransactionLastDay(Company c);

	JSONObject selectTransaction(Company c, String terms);

	JSONObject selectTransactionRangeDay(Company c, String type, String terms,
			String times);

	JSONObject selectTransactionRandeDayAll(Company c, String type, String times);

	// 会员统计普通 统计时间范围的数据
	JSONObject selectMemberCommonRangeDay(String type, String times,
			Integer companyId);

	// 销售统计范围数据
	JSONObject selectSalesRangeDay(Integer companyId, String times);

	// 所有基地信息
	List<Company> listCompanyName();

	JSONObject selectTransactionRangeDayToExcel(Company c, String times);

	// 销售统计范围数据toexcel
	JSONObject selectSalesRangeDayToExcel(Integer companyIds, String times);

	// 会员统计范围数据toexcel
	Map<String, Object> memberadmintToExcel(String times);

	// 入账资金统计
	JSONObject selectMoney(Company c, String terms);

	// 支付资金统计
	JSONObject selectMoneyPay(Company c, String terms);

}