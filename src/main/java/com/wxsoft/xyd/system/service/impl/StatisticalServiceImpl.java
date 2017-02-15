/**   
 * @文件名称: StatisticalServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-3-29 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.prod.mapper.ProductBasicMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoMapper;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.system.mapper.CompanyMapper;
import com.wxsoft.xyd.system.mapper.StatisticalMapper;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.service.StatisticalService;

/**
 * @类功能说明：
 * 
 * @类修改者：kasiait @修改日期：2013-3-29 @修改说明：
 * @公司名称：kyz
 * @作者：kyz @创建时间：2013-3-29 下午05:50:09
 */
@Service("statisticalService")
public class StatisticalServiceImpl implements StatisticalService {
	/*
	 * mybatis代理类对象
	 */
	@Autowired
	private StatisticalMapper statisticalMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private ProductBasicMapper productBasicMapper;
	@Autowired
	private ProductSpecificationInfoMapper productSpecificationInfoMapper;

	@Override
	public List<Map<String, Object>> selectMainSql(Company c) {
		// TODO Auto-generated method stub
		String sql = "";
		if (c.getUserRole().getId() == 40) {// 基地用户
			Integer companyId = c.getCompanyId();
			sql = "(\n" + "	SELECT\n" + "		IFNULL(COUNT(*),0) counts\n"
					+ "	FROM\n" + "		orders o\n" + "	WHERE\n"
					+ "		o. STATUS IN (20, 22)\n" + "	AND company_id = "
					+ companyId
					+ ""
					+ ")\n"
					+ "UNION ALL\n"
					+ "	(\n"
					+ "		SELECT\n"
					+ "			IFNULL(COUNT(*),0) counts\n"
					+ "		FROM\n"
					+ "			orders o\n"
					+ "		WHERE\n"
					+ "			o. STATUS = 50\n"
					+ "	AND company_id = "
					+ companyId
					+ ""
					+ "	)\n"
					+ "UNION ALL\n"
					+ "	(\n"
					+ "		SELECT\n"
					+ "			IFNULL(COUNT(*),0) counts\n"
					+ "		FROM\n"
					+ "			orders o\n"
					+ "		WHERE to_days(now()) - to_days(o.addtime) = 1\n"
					+ "	AND company_id = "
					+ companyId
					+ ""
					+ "	)\n"
					+ "UNION ALL\n"
					+ "	(\n"
					+ "		SELECT\n"
					+ "			ifnull(sum(o.order_account), 0) AS counts\n"
					+ "		FROM\n"
					+ "			orders o\n"
					+ "		WHERE\n"
					+ "			o. STATUS IN (20, 22, 30,40,50,60)\n"
					+ "		AND to_days(now()) - to_days(o.addtime) = 1\n"
					+ "	AND company_id = " + companyId + "" + "	)";
		} else {// 系统用户
			sql = "(\n" + "	SELECT\n" + "		IFNULL(COUNT(*),0) counts\n"
					+ "	FROM\n" + "		orders o\n" + "	WHERE\n"
					+ "		o. STATUS IN (20, 22)\n" + ")\n" + "UNION ALL\n"
					+ "	(\n" + "		SELECT\n" + "			IFNULL(COUNT(*),0) counts\n"
					+ "		FROM\n" + "			orders o\n" + "		WHERE\n"
					+ "			o. STATUS = 50\n" + "	)\n" + "UNION ALL\n" + "	(\n"
					+ "		SELECT\n" + "			IFNULL(COUNT(*),0) counts\n"
					+ "		FROM\n" + "			orders o\n"
					+ "		WHERE to_days(now()) - to_days(o.addtime) = 1\n"
					+ "	)\n" + "UNION ALL\n" + "	(\n" + "		SELECT\n"
					+ "			ifnull(sum(o.order_account), 0) AS counts\n"
					+ "		FROM\n" + "			orders o\n" + "		WHERE\n"
					+ "			o. STATUS IN (20, 22, 30,40,50,60)\n"
					+ "		AND to_days(now()) - to_days(o.addtime) = 1\n" + "	)";
		}
		return statisticalMapper.selectModelStatistical(sql);
	}

	@Override
	public JSONObject selectMainLastOrderSql(Company c) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		Integer companyId = 0;
		if (c.getUserRole().getId() == 40) {// 基地用户
			companyId = c.getCompanyId();
		} else {// 系统用户

		}

		// 最近7天下单数
		String sql1 = "SELECT\n"
				+ "	COUNT(*) AS counts,DATE_FORMAT( addtime, \"%Y-%m-%d\" ) AS times\n"
				+ "FROM\n" + "	`orders`\n" + "WHERE\n"
				+ "	date_sub(curdate(), INTERVAL 7 DAY) <= date(`addtime`)\n"
				+ " "
				+ (companyId != 0 ? (" AND company_id=" + companyId) : "")
				+ " GROUP BY DATE_FORMAT( addtime, \"%Y-%m-%d\" )";

		// 最近7天成交数
		String sql2 = "SELECT\n"
				+ "	COUNT(*) AS counts,DATE_FORMAT( addtime, \"%Y-%m-%d\" ) AS times\n"
				+ "FROM\n" + "	`orders`\n" + "WHERE\n"
				+ "	date_sub(curdate(), INTERVAL 7 DAY) <= date(`addtime`)\n"
				+ "AND `status` IN (20, 22, 30,40,50,60) "
				+ (companyId != 0 ? (" AND company_id=" + companyId) : "")
				+ " GROUP BY DATE_FORMAT( addtime, \"%Y-%m-%d\" )";

		// 最近7天维权数
		String sql3 = "SELECT\n"
				+ "	COUNT(*) AS counts,DATE_FORMAT( ore.addtime, \"%Y-%m-%d\" ) AS times\n"
				+ "FROM\n"
				+ "	`orders_return` ore\n LEFT JOIN orders o ON ore.order_id = o.id "
				+ "WHERE\n"
				+ "	date_sub(curdate(), INTERVAL 7 DAY) <= date(ore.addtime)\n"
				+ (companyId != 0 ? (" AND o.company_id=" + companyId) : "")
				+ " GROUP BY DATE_FORMAT( ore.addtime, \"%Y-%m-%d\" )";

		List<String> dateList = Tools.getLastSevenDaysList();// 带有年份2014-02-05
		List<String> dateListNoYears = Tools.getLastSevenDaysListNoYears();// 不带有年份05-05

		// 前台展示不显示年份
		json.put("date", dateListNoYears);
		json.put("dateSE", dateList.get(0) + "至" + dateList.get(6));// 开始日期到结束日期
		List<Object> sql1List = new ArrayList<Object>(7);
		List<Object> sql2List = new ArrayList<Object>(7);
		List<Object> sql3List = new ArrayList<Object>(7);

		// 记录各种数量和
		Integer sql1Count = 0;
		Integer sql2Count = 0;
		Integer sql3Count = 0;

		// 临时存放数据
		Map<String, Object> sql1Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql1), "times",
				"counts");
		Map<String, Object> sql2Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql2), "times",
				"counts");
		Map<String, Object> sql3Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql3), "times",
				"counts");
		for (String date : dateList) {
			if (null != sql1Temp.get(date)) {
				sql1Count += Integer
						.parseInt(String.valueOf(sql1Temp.get(date)));
				sql1List.add(sql1Temp.get(date));
			} else {
				sql1List.add(0);
			}

			if (null != sql2Temp.get(date)) {
				sql2Count += Integer
						.parseInt(String.valueOf(sql2Temp.get(date)));
				sql2List.add(sql2Temp.get(date));
			} else {
				sql2List.add(0);
			}

			if (null != sql3Temp.get(date)) {
				sql3Count += Integer
						.parseInt(String.valueOf(sql3Temp.get(date)));
				sql3List.add(sql3Temp.get(date));
			} else {
				sql3List.add(0);
			}

		}
		json.put("sql1", sql1List);
		json.put("sql2", sql2List);
		json.put("sql3", sql3List);
		json.put("sql1Count", sql1Count);
		json.put("sql2Count", sql2Count);
		json.put("sql3Count", sql3Count);
		return json;
	}

	@Override
	public JSONObject selectMainLastChargeSql(Company c) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		Integer companyId = 0;
		if (c.getUserRole().getId() == 40) {// 基地用户
			companyId = c.getCompanyId();
		} else {// 系统用户

		}

		// 最近7天成交额
		String sql1 = "SELECT\n"
				+ "	SUM(o.order_account) as orderaccount,\n"
				+ "	DATE_FORMAT(o.pay_time, \"%Y-%m-%d\") as times\n"
				+ "FROM\n"
				+ "	orders o\n"
				+ "WHERE\n"
				//+ "	o. STATUS IN (20, 22, 30, 40, 50 ,60)\n AND"
				+ " date_sub(curdate(), INTERVAL 7 DAY) <= date(o.pay_time)\n "
				+ (companyId != 0 ? (" AND o.company_id=" + companyId) : "")
				+ " " + "GROUP BY\n" + "	DATE_FORMAT(o.pay_time, \"%Y-%m-%d\")";

		// 最近7天维权金额
		String sql2 = "SELECT\n"
				+ "	SUM(o.order_account)-SUM(o.ship_price) as orderaccount,\n"
				+ "	DATE_FORMAT(ore.recordtime, \"%Y-%m-%d\") as times\n"
				+ "FROM\n"
				+ "	orders o\n"
				+ "LEFT JOIN orders_return ore ON ore.order_id = o.id\n"
				+ "WHERE\n"
				+ "	ore.`status` = 1\n"
				+ "AND date_sub(curdate(), INTERVAL 7 DAY) <= date(ore.recordtime)\n "
				+ (companyId != 0 ? (" AND o.company_id=" + companyId) : "")
				+ " " + "GROUP BY\n"
				+ "	DATE_FORMAT(ore.recordtime, \"%Y-%m-%d\")";

		List<String> dateList = Tools.getLastSevenDaysList();// 带有年份2014-02-05
		List<String> dateListNoYears = Tools.getLastSevenDaysListNoYears();// 不带有年份05-05

		// 前台展示不显示年份
		json.put("date", dateListNoYears);
		json.put("dateSE", dateList.get(0) + "至" + dateList.get(6));// 开始日期到结束日期
		List<Object> sql1List = new ArrayList<Object>(7);
		List<Object> sql2List = new ArrayList<Object>(7);

		// 记录各种数量和
		Double sql1Count = 0d;
		Double sql2Count = 0d;

		// 临时存放数据
		Map<String, Object> sql1Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql1), "times",
				"orderaccount");
		Map<String, Object> sql2Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql2), "times",
				"orderaccount");
		for (String date : dateList) {
			if (null != sql1Temp.get(date)) {
				sql1Count += Double.parseDouble(String.valueOf(sql1Temp
						.get(date)));
				sql1List.add(sql1Temp.get(date));
			} else {
				sql1List.add(0);
			}

			if (null != sql2Temp.get(date)) {
				double temp = Double.parseDouble(String.valueOf(sql2Temp
						.get(date)));
				sql2Count += temp;
				sql2List.add(temp * -1);
			} else {
				sql2List.add(0);
			}

		}
		json.put("sql1", sql1List);
		json.put("sql2", sql2List);
		json.put("sql1Count", sql1Count);
		json.put("sql2Count", sql2Count);
		return json;
	}

	@Override
	public JSONObject selectMemberLastOneDay(Company c) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		// 所有基地信息
		List<Company> listCompanyName = companyMapper.selectCompanyName(null);

		Company other = new Company();
		other.setCompanyId(0);
		other.setCompanyName("其他");
		listCompanyName.add(other);
		// 所有基地的名称
		List<String> companyName = new ArrayList<String>();
		// 最终数据存放
		List<Object> sql1List = new ArrayList<Object>();
		String sql1 = "SELECT\n" + "	count(*) as counts,company_id\n"
				+ "FROM\n" + "	`user` u\n" + "WHERE\n"
				+ "	to_days(now()) - to_days(u.addtime) = 1\n" + "GROUP BY\n"
				+ "	company_id;";
		String sql2 = "SELECT count(*) counts FROM `user`;";
		Map<String, Object> sql1Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql1), "company_id",
				"counts");
		List<Map<String, Object>> sql2Temp = statisticalMapper
				.selectModelStatistical(sql2);

		for (Company company : listCompanyName) {
			companyName.add(company.getCompanyName());
			Object tempCountObj = sql1Temp.get(String.valueOf(company
					.getCompanyId()));
			if (null != tempCountObj) {
				sql1List.add(tempCountObj);
			} else {
				sql1List.add(0);
			}
		}

		// 统计所有基地的会员信息
		String sql3 = "SELECT\n"
				+ "	temps.counts,\n"
				+ "	IFNULL(c.company_id, 0) AS company_id "
				+ "FROM\n"
				+ "	company c\n"
				+ "RIGHT JOIN (\n"
				+ "	SELECT\n"
				+ "		COUNT(*) AS counts,\n"
				+ "		IFNULL(company_id, 0) company_id\n"
				+ "	FROM\n"
				+ "		`user`\n"
				+ "	GROUP BY\n"
				+ "		company_id\n"
				+ ") temps ON c.company_id = temps.company_id ORDER BY counts desc";
		LinkedHashMap<String, Object> sql3Temp = Tools.ListToLinkedHashMap(
				statisticalMapper.selectModelStatistical(sql3), "company_id",
				"counts", "");
		Map<String, Integer> companyAllCount = new HashMap<String, Integer>();

		for (Company company : listCompanyName) {
			Object tempAllCountObj = sql3Temp.get(String.valueOf(company
					.getCompanyId()));
			companyAllCount.put(company.getCompanyName(), Integer
					.parseInt(String.valueOf(tempAllCountObj == null ? 0
							: tempAllCountObj)));
		}

		json.put("companyMemberCount", Tools.sortMap(companyAllCount));
		json.put("companyName", companyName);
		json.put("sql1", sql1List);
		json.put("allcount", sql2Temp.get(0).get("counts"));
		json.put("date", Tools.date2Str(new Date(), "yyyy-MM"));
		return json;
	}

	@Override
	public JSONObject selectMemberComonLastOneDay(Company c) {
		JSONObject json = new JSONObject();
		String sql = "SELECT\n" + "	count(*) as counts,company_id\n" + "FROM\n"
				+ "	`user` u\n" + "WHERE\n"
				+ "	to_days(now()) - to_days(u.addtime) = 1\n"
				+ "AND company_id= " + c.getCompanyId() + " GROUP BY\n"
				+ "	company_id;";
		List<Map<String, Object>> sqlTemp = statisticalMapper
				.selectModelStatistical(sql);

		json.put("sql", sqlTemp);
		return json;
	}

	@Override
	public JSONObject selectTransactionLastDay(Company c) {
		JSONObject json = new JSONObject();
		String sql1;
		String sql2;
		String sql3;
		String sql4;
		if (c == null) {
			sql1 = "  SELECT COUNT(*) count, DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors where to_days(now()) - to_days(ors.addtime) = 1 ";
			sql2 = " SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1";
			sql3 = " SELECT count(*) as count ,ADDTIME FROM ( SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME "
					+ "FROM orders ors "
					+ "WHERE TO_DAYS(NOW()) - TO_DAYS(ors.addtime) = 1  GROUP BY ors.user_id) as aa";
			sql4 = "  SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1  ";
		} else {
			sql1 = "  SELECT COUNT(*) count, DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors where to_days(now()) - to_days(ors.addtime) = 1 and company_id= "
					+ c.getCompanyId();
			sql2 = " SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1 and company_id= "
					+ c.getCompanyId();
			sql3 = "  SELECT count(*) as count ,ADDTIME FROM ("
					+ " SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME "
					+ "FROM orders ors WHERE TO_DAYS(NOW()) - TO_DAYS(ors.addtime) = 1  "
					+ "and company_id= "
					+ c.getCompanyId() +" GROUP BY ors.user_id) as aa" ;
			sql4 = "  SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1  and company_id= "
					+ c.getCompanyId();
		}

		List<Map<String, Object>> allOrder = statisticalMapper
				.selectModelStatistical(sql1);
		List<Map<String, Object>> placeOrder = statisticalMapper
				.selectModelStatistical(sql2);
		List<Map<String, Object>> aleradyPay = statisticalMapper
				.selectModelStatistical(sql3);
		List<Map<String, Object>> turnover = statisticalMapper
				.selectModelStatistical(sql4);
		json.put("allOrder", allOrder);// 总订单数:
		json.put("placeOrder", placeOrder);//付款订单数:
		json.put("aleradyPay", aleradyPay);//下单人数:
		json.put("turnover", turnover);//成交额:
		return json;

	}

	@Override
	public JSONObject selectTransaction(Company c, String terms) {
		JSONObject json = new JSONObject();
		String sql1 = null;
		if (c == null) {
			if (terms.equals("allOrder")) { // 总订单数
				sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') "
						+ "ADDTIME FROM orders ors where  to_days(now()) - to_days(ors.addtime) = 1  GROUP BY DATE_FORMAT(ors.addtime,'%Y-%m-%d')";
			}
			if (terms.equals("turnover")) {// 成交额统计
				sql1 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1  "
						+ "GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}
			if (terms.equals("aleradyPay")) {// 付款订单数
				sql1 = " SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
						+ "  TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1 "
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}
			if (terms.equals("placeOrder")) {// 下单人数
				sql1 = " SELECT count(*) as count ,ADDTIME FROM ( "
						+ "SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " TO_DAYS(NOW()) - TO_DAYS(ors.addtime) = 1  GROUP BY ors.user_id) as aa"
						+ "   GROUP BY DATE_FORMAT(aa.addtime,'%Y-%m-%d')";
			}

		} else {
			if (terms.equals("allOrder")) { // 总订单数
				sql1 = "SELECT COUNT(*)count,  DATE_FORMAT(ors.addtime,'%m-%d')"
						+ "ADDTIME FROM orders ors WHERE    to_days(now()) - to_days(ors.addtime) = 1 and company_id="
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.addtime,'%Y-%m-%d')";
			}
			if (terms.equals("turnover")) {// 成交额统计
				sql1 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
						+ "  TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1  AND company_id="
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}
			if (terms.equals("aleradyPay")) {// 付款订单数
				sql1 = " SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " TO_DAYS(NOW()) - TO_DAYS(ors.pay_time) = 1  AND company_id="
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}
			if (terms.equals("placeOrder")) {// 下单人数
				sql1 = " SELECT count(*) as count ,ADDTIME FROM (  SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE  "
						+ "TO_DAYS(NOW()) - TO_DAYS(ors.addtime) = 1 AND company_id="
						+ c.getCompanyId()
						+ " GROUP BY ors.user_id) as aa"
						+ " GROUP BY DATE_FORMAT(aa.addtime,'%Y-%m-%d')";
			}

		}

		List<Map<String, Object>> countList = statisticalMapper
				.selectModelStatistical(sql1);
		json.put("countList", countList);
		return json;
	}

	@Override
	public JSONObject selectMemberRangeDay(String type, String times) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		// 所有基地信息
		List<Company> listCompanyName = companyMapper.selectCompanyName(null);
		Company other = new Company();
		other.setCompanyId(0);
		other.setCompanyName("其他");
		listCompanyName.add(other);
		List<String> dateList = null;// 带有年份2014-02-05
		List<String> dateListNoYears = null;// 不带有年份05-05
		LinkedHashMap<String, Integer> dateListMap = new LinkedHashMap<String, Integer>();
		switch (type) {
		case "2":
			dateList = Tools.getLastSevenDaysList();
			dateListNoYears = Tools.getLastSevenDaysListNoYears();
			break;
		case "3":
			dateList = Tools.getLastMonthDaysList();
			dateListNoYears = Tools.getLastMonthDaysListNoYears();
			break;
		case "4":
			dateList = Tools.getMonthDaysList(times);
			dateListNoYears = Tools.getMonthDaysListNoYears(times);
			break;
		default:
			break;
		}

		for (String string : dateList) {
			dateListMap.put(string, 0);
		}

		// 所有基地的名称或者id
		List<String> companyName = new ArrayList<String>();
		List<Integer> companyId = new ArrayList<Integer>();
		String sql1 = "SELECT\n" + "	addtime,company_id\n" + "FROM\n"
				+ "	`user` u\n" + "WHERE\n" + " u.addtime between '"
				+ dateList.get(0) + " 00:00:00'\n" + "AND '"
				+ dateList.get(dateList.size() - 1) + " 23:59:59';";
		Map<String, LinkedHashMap<String, Integer>> sql1Temp = Tools
				.ListToMapString(
						statisticalMapper.selectModelStatistical(sql1),
						"company_id", "addtime", dateListMap);

		for (Company company : listCompanyName) {
			companyName.add(company.getCompanyName());
			companyId.add(company.getCompanyId());
			LinkedHashMap<String, Integer> tempCountObj = sql1Temp.get(String
					.valueOf(company.getCompanyId()));
			if (null == tempCountObj) {
				sql1Temp.put(String.valueOf(company.getCompanyId()),
						dateListMap);
			}
		}
		json.put("companyName", companyName);
		json.put("companyId", companyId);
		json.put("sql1", sql1Temp);
		json.put("dateListNoYears", dateListNoYears);
		return json;
	}

	@Override
	public JSONObject selectMemberCommonRangeDay(String type, String times,
			Integer companyId) {
		JSONObject json = new JSONObject();
		List<String> dateList = null;// 带有年份2014-02-05
		List<String> dateListNoYears = null;// 不带有年份05-05
		switch (type) {
		case "2":
			dateList = Tools.getLastSevenDaysList();
			dateListNoYears = Tools.getLastSevenDaysListNoYears();
			break;
		case "3":
			dateList = Tools.getLastMonthDaysList();
			dateListNoYears = Tools.getLastMonthDaysListNoYears();
			break;
		case "4":
			dateList = Tools.getMonthDaysList(times);
			dateListNoYears = Tools.getMonthDaysListNoYears(times);
			break;
		default:
			break;
		}
		String sql = "SELECT COUNT(*)AS counts ,DATE_FORMAT(u.addtime,'%Y-%m-%d')"
				+ " ADDTIME,company_id FROM `user` u WHERE u.addtime BETWEEN '"
				+ dateList.get(0)
				+ " 00:00:00' AND '"
				+ dateList.get(dateList.size() - 1)
				+ " 23:59:59'"
				+ " AND company_id="
				+ companyId
				+ " GROUP BY DATE_FORMAT(u.addtime,'%Y-%m-%d')";
		List<Map<String, Object>> sqlTemp = statisticalMapper
				.selectModelStatistical(sql);
		json.put("sql", sqlTemp);
		return json;
	}

	@Override
	public JSONObject selectSalesRangeDay(Integer companyIds, String times) {
		// TODO Auto-generated method stub
		
		JSONObject json = new JSONObject();
		List<ProductSpecificationInfo> psiList = productSpecificationInfoMapper.getAllByPSI(null);
		
		String selectCompanyId = null;
		List<String> dateList = null;// 带有年份2014-02-05
		dateList = Tools.getMonthDaysList(times);
		if (0 != companyIds.intValue()) {
			selectCompanyId = companyIds+"";
		}
		String beginTime = dateList.get(0) + " 00:00:00";
		String endTime = dateList.get(dateList.size() - 1) + " 23:59:59";
		Map paraMap = new HashMap();
		paraMap.put("selectCompanyId", selectCompanyId);
		paraMap.put("beginTime", beginTime);
		paraMap.put("endTime", endTime);
		
		List<Map<String, Object>> resultList = statisticalMapper.selectModelStatisticalNew(paraMap);
		LinkedHashMap<String, Object> sql1Temp = new LinkedHashMap<String, Object>();
		if(null != resultList && resultList.size()>0){
			for (Map<String, Object> map : resultList) {
				String key_t = "a"+map.get("type")+"_"+map.get("id")+"_"+map.get("product_id");
				String val_t = map.get("counts")+"";
				sql1Temp.put(key_t,val_t);
			}
		}
		
		Map<String, String> mapsPB = new HashMap<String, String>();
		for (ProductSpecificationInfo psi : psiList) {
			if("1".equals(psi.getType())){
				String guigezhi ="";
				List<ProductSpecificationInfoDetail> list_t = psi.getSpecInfoDetailList();
				if(null != list_t && list_t.size()>0){
					for (ProductSpecificationInfoDetail pi : list_t) {
						guigezhi += pi.getSpecificationDetailName()+" ";
					}
				}else{
					guigezhi = "存在规格";
				}
				mapsPB.put("a" + psi.getType() + "_" +psi.getId() + "_"+psi.getProductId(), psi.getProductName()+"("+guigezhi+")");
			}else{
				mapsPB.put("a" + psi.getType() + "_" +psi.getId() + "_"+psi.getProductId(), psi.getProductName());
			}
		}
		
		json.put("mapsPB", mapsPB);
		json.put("sql1", sql1Temp);
		return json;
	}

	@Override
	public List<Company> listCompanyName() {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyName(null);
	}

	@Override
	public JSONObject selectTransactionRangeDay(Company c, String type,
			String terms, String times) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		List<String> dateList = null;// 带有年份2014-02-05
		List<String> dateListNoYears = null;// 不带有年份05-05
		switch (type) {
		case "2":
			dateList = Tools.getLastSevenDaysList();
			dateListNoYears = Tools.getLastSevenDaysListNoYears();
			break;
		case "3":
			dateList = Tools.getLastMonthDaysList();
			dateListNoYears = Tools.getLastMonthDaysListNoYears();
			break;
		case "4":
			dateList = Tools.getMonthDaysList(times);
			dateListNoYears = Tools.getMonthDaysListNoYears(times);
			break;
		default:
			break;
		}
		String sql1 = null;
		if (c == null) {
			if (terms.equals("allOrder")) { // 总订单数
				sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`addtime` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'  GROUP BY DATE_FORMAT(ors.addtime,'%Y-%m-%d')";
			}
			if (terms.equals("aleradyPay")) {// 付款单数
				sql1 = "SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
						+ " ors.`pay_time` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59' "
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}
			if (terms.equals("placeOrder")) {// 下单人数
				sql1 = "SELECT count(*) as count ,ADDTIME FROM ( SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`addtime` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'  GROUP BY ors.user_id) as aa   GROUP BY DATE_FORMAT(aa.addtime,'%Y-%m-%d')";
			}
			if (terms.equals("turnover")) {// 成交额统计
				sql1 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`pay_time` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'  "
						+ "GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}

		} else {
			if (terms.equals("allOrder")) { // 总订单数
				sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`addtime` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'  and company_id="
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.addtime,'%Y-%m-%d')";
			}

			if (terms.equals("aleradyPay")) {// 付款单数
				sql1 = "SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
						+ " ors.`addtime` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'"
						+ " and company_id= "
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}

			if (terms.equals("placeOrder")) {// 下单人数
				sql1 = "SELECT count(*) as count ,ADDTIME FROM ( SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`addtime` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59' "
						+ "  and company_id= "
						+ c.getCompanyId()
						+ "  GROUP BY ors.user_id) as aa GROUP BY DATE_FORMAT(aa.addtime,'%Y-%m-%d')";
			}
			if (terms.equals("turnover")) {// 成交额统计
				sql1 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
						+ " ors.`pay_time` BETWEEN '"
						+ dateList.get(0)
						+ " 00:00:00' AND '"
						+ dateList.get(dateList.size() - 1)
						+ " 23:59:59'  and company_id= "
						+ c.getCompanyId()
						+ " GROUP BY DATE_FORMAT(ors.pay_time,'%Y-%m-%d')";
			}

		}

		List<Map<String, Object>> countList = statisticalMapper
				.selectModelStatistical(sql1);
		json.put("countList", countList);

		return json;
	}

	@Override
	public JSONObject selectTransactionRandeDayAll(Company c, String type,
			String times) {
		JSONObject json = new JSONObject();
		String sql1;
		String sql2;
		String sql3;
		String sql4;
		List<String> dateList = null;// 带有年份2014-02-05
		List<String> dateListNoYears = null;// 不带有年份05-05
		switch (type) {
		case "2":
			dateList = Tools.getLastSevenDaysList();
			dateListNoYears = Tools.getLastSevenDaysListNoYears();
			break;
		case "3":
			dateList = Tools.getLastMonthDaysList();
			dateListNoYears = Tools.getLastMonthDaysListNoYears();
			break;
		case "4":
			dateList = Tools.getMonthDaysList(times);
			dateListNoYears = Tools.getMonthDaysListNoYears(times);
			break;
		default:
			break;
		}

		if (c == null) {
			sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' ";// 总订单数
			sql2 = "SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE"
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' ";// 付款单数
			sql3 = " SELECT count(*) as count ,ADDTIME FROM (SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'  GROUP BY ors.user_id) as aa ";// 下单人数
			sql4 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`pay_time` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' ";// 成交额
		} else {
			sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' and company_id= " + c.getCompanyId();// 总订单数
			sql2 = "SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE"
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'  and company_id= "
					+ c.getCompanyId();// 付款单数
			sql3 = "SELECT count(*) as count ,ADDTIME FROM ( SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'    and company_id= "
					+ c.getCompanyId() +" GROUP BY ors.user_id) as aa";// 下单人数
			sql4 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`pay_time` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' and company_id= "
					+ c.getCompanyId();// 成交额

		}
		List<Map<String, Object>> allOrder = statisticalMapper
				.selectModelStatistical(sql1);
		List<Map<String, Object>> placeOrder = statisticalMapper
				.selectModelStatistical(sql2);
		List<Map<String, Object>> aleradyPay = statisticalMapper
				.selectModelStatistical(sql3);
		List<Map<String, Object>> turnover = statisticalMapper
				.selectModelStatistical(sql4);
		json.put("allOrder", allOrder);
		json.put("placeOrder", placeOrder);
		json.put("aleradyPay", aleradyPay);
		json.put("turnover", turnover);
		return json;

	}

	@Override
	public JSONObject selectSalesRangeDayToExcel(Integer companyIds,
			String times) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();

		List<ProductBasic> pblist = productBasicMapper
				.getAllByProductBasic(null);
		String selectCompanyId = "";
		List<String> dateList = null;// 带有年份2014-02-05
		dateList = Tools.getMonthDaysList(times);

		Map<String, LinkedHashMap<String, Object>> result = new HashMap<String, LinkedHashMap<String, Object>>();
		if (0 != companyIds.intValue()) {
			Company companyInfo = companyMapper.getCompanyById(companyIds);
			selectCompanyId = "company_id=" + companyIds + " and ";
			// 最终数据存放
			String sql1 = "SELECT\n" + "	id,ifnull(counts,0) as  counts\n"
					+ "FROM\n" + "	product_basic pb\n" + "LEFT JOIN (\n"
					+ "	SELECT\n" + "		SUM(sales_count) AS counts,\n"
					+ "		basic_id\n" + "	FROM\n"
					+ "		product_basic_statistics_all\n" + "	WHERE\n" + "		"
					+ selectCompanyId + "\n" + "	addtime BETWEEN '"
					+ dateList.get(0) + " 00:00:00'\n" + "	AND '"
					+ dateList.get(dateList.size() - 1) + " 23:59:59'\n"
					+ "	GROUP BY\n" + "		basic_id\n" + "	ORDER BY\n"
					+ "		SUM(sales_count) DESC\n"
					+ ") as tempSql on pb.id=tempSql.basic_id";
			LinkedHashMap<String, Object> sql1Temp = Tools.ListToLinkedHashMap(
					statisticalMapper.selectModelStatistical(sql1), "id",
					"counts", "a");
			result.put(
					companyInfo.getCompanyId() + "==="
							+ companyInfo.getCompanyName(), sql1Temp);
		} else {
			// 所有基地信息
			List<Company> listCompanyName = companyMapper
					.selectCompanyName(null);
			for (Company company : listCompanyName) {
				Company companyInfo = companyMapper.getCompanyById(company
						.getCompanyId());
				selectCompanyId = "company_id=" + company.getCompanyId()
						+ " and ";
				// 最终数据存放
				String sql1 = "SELECT\n" + "	id,ifnull(counts,0) as  counts\n"
						+ "FROM\n" + "	product_basic pb\n" + "LEFT JOIN (\n"
						+ "	SELECT\n" + "		SUM(sales_count) AS counts,\n"
						+ "		basic_id\n" + "	FROM\n"
						+ "		product_basic_statistics_all\n" + "	WHERE\n"
						+ "		" + selectCompanyId + "\n" + "	addtime BETWEEN '"
						+ dateList.get(0) + " 00:00:00'\n" + "	AND '"
						+ dateList.get(dateList.size() - 1) + " 23:59:59'\n"
						+ "	GROUP BY\n" + "		basic_id\n" + "	ORDER BY\n"
						+ "		SUM(sales_count) DESC\n"
						+ ") as tempSql on pb.id=tempSql.basic_id";
				LinkedHashMap<String, Object> sql1Temp = Tools
						.ListToLinkedHashMap(
								statisticalMapper.selectModelStatistical(sql1),
								"id", "counts", "a");
				result.put(
						companyInfo.getCompanyId() + "==="
								+ companyInfo.getCompanyName(), sql1Temp);
			}

		}

		Map<String, String> mapsPB = new HashMap<String, String>();
		for (ProductBasic pb : pblist) {
			mapsPB.put("a" + pb.getId() + "", pb.getName());
		}

		json.put("mapsPB", mapsPB);
		json.put("sql1", result);
		return json;
	}

	@Override
	public Map<String, Object> memberadmintToExcel(String times) {
		// TODO Auto-generated method stub
		List<String> dateList = null;// 带有年份2014-02-05
		dateList = Tools.getMonthDaysList(times);

		String sql1 = "SELECT\n" + "	temps.counts,\n"
				+ "	IFNULL(c.company_name,\"其他\") as company_name\n" + "FROM\n"
				+ "	company c\n" + "RIGHT JOIN (\n" + "	SELECT\n"
				+ "		COUNT(*) AS counts,\n"
				+ "		IFNULL(company_id, 0) company_id\n" + "	FROM\n"
				+ "		`user`\n" + "	WHERE addtime BETWEEN '" + dateList.get(0)
				+ " 00:00:00'\n" + "	AND '" + dateList.get(dateList.size() - 1)
				+ " 23:59:59'\n" + "	GROUP BY\n" + "		company_id\n"
				+ ") temps ON c.company_id = temps.company_id";
		Map<String, Object> sql1Temp = Tools.ListToMap(
				statisticalMapper.selectModelStatistical(sql1), "company_name",
				"counts");
		return sql1Temp;
	}

	@Override
	public JSONObject selectTransactionRangeDayToExcel(Company c, String times) {
		JSONObject json = new JSONObject();
		List<String> dateList = null;// 带有年份2014-02-05
		dateList = Tools.getMonthDaysList(times);
		String sql1;
		String sql2;
		String sql3;
		String sql4;
		if (c == null) {
			sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' ";// 总订单数
			sql2 = "SELECT COUNT(*) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
					+ " ors.`pay_time` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'";// 付款单数
			sql3 = " SELECT count(*) as count ,ADDTIME FROM (SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' GROUP BY ors.user_id) as aa";// 下单人数
			sql4 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`pay_time` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'  ";// 成交额
		} else {
			sql1 = "SELECT COUNT(*) count,  DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' and company_id= " + c.getCompanyId();// 总订单数
			sql2 = "SELECT COUNT(pay_time) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE"
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59' and company_id= "
					+ c.getCompanyId();// 付款单数
			sql3 = " SELECT count(*) as count ,ADDTIME FROM (SELECT COUNT(*) count,DATE_FORMAT(ors.addtime,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`addtime` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'   and company_id= "
					+ c.getCompanyId()+" GROUP BY ors.user_id) as aa";// 下单人数
			sql4 = " SELECT SUM(order_account) count,DATE_FORMAT(ors.pay_time,'%m-%d') ADDTIME FROM orders ors WHERE "
					+ " ors.`pay_time` BETWEEN '"
					+ dateList.get(0)
					+ " 00:00:00' AND '"
					+ dateList.get(dateList.size() - 1)
					+ " 23:59:59'  and company_id= "
					+ c.getCompanyId();// 成交额

		}
		List<Map<String, Object>> allOrder = statisticalMapper
				.selectModelStatistical(sql1);
		List<Map<String, Object>> placeOrder = statisticalMapper
				.selectModelStatistical(sql2);
		List<Map<String, Object>> aleradyPay = statisticalMapper
				.selectModelStatistical(sql3);
		List<Map<String, Object>> turnover = statisticalMapper
				.selectModelStatistical(sql4);
		json.put("总订单数", allOrder.get(0).get("count").toString());
		json.put("下单人数", placeOrder.get(0).get("count").toString());
		json.put("付款订单数", aleradyPay.get(0).get("count").toString());
		if (turnover.get(0) == null) {
			json.put("成交额", "0");
		} else {
			json.put("成交额", turnover.get(0).get("count").toString());
		}
		return json;
	}

	//入账资金统计
	@Override
	public JSONObject selectMoney(Company c, String terms) {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		List<String> dateList = Tools.getMonthDaysStartEnd(terms);
		String companySelect = "";
		if (c.getUserRole().getId() == 40) {// 基地用户
			companySelect = " and o.company_id=" + c.getCompanyId();
		}

		String sql1 = "(\n"
				+ "	SELECT\n"
				+ "		sum(o.order_account) AS moneys, c.company_name,\n"
				+ "		o.company_id,\n"
				+ "		o.pay_form,\n"
				+ "		'1' AS type\n"
				+ "	FROM\n"
				+ "		orders o\n left join company c on c.company_id=o.company_id"
				+ "	WHERE\n" + "		o.is_over = 1\n" + "	AND over_time BETWEEN\n '"
				+ dateList.get(0)
				+ "'	AND\n '"
				+ dateList.get(1)+"'"
				+ companySelect
				+ "	GROUP BY\n"
				+ "		o.company_id,\n"
				+ "		o.pay_form\n"
				+ ")\n"
				+ "UNION\n"
				+ "	(\n"
				+ "		SELECT\n"
				+ "			sum(o.order_account) AS moneys,c.company_name,\n"
				+ "			o.company_id,\n"
				+ "			o.pay_form,\n"
				+ "			'2' AS type\n"
				+ "		FROM\n"
				+ "			orders o\n left join company c on c.company_id=o.company_id"
				+ "		RIGHT JOIN orders_return ore ON ore.order_id = o.id\n"
				+ "		WHERE\n"
				+ "			o.is_over = 1\n"
				+ "		AND o.`status` = 50\n"
				+ "		AND ore.`status` = 2\n"
				+ "		AND over_time BETWEEN\n '"
				+ dateList.get(0)
				+ "	 '	AND\n  '"
				+ dateList.get(1)+"'"
				+ companySelect
				+ "		GROUP BY\n"
				+ "			o.company_id,\n" + "			o.pay_form\n" + "	)";
		List<Map<String, Object>> mapResult = statisticalMapper
				.selectModelStatistical(sql1);
		Map<String, Map<String, Map<String, Object>>> mapAll = new HashMap<String, Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> zhifuLeixing = new HashMap<String, Map<String, Object>>();
		Map<String, Object> feiyongLeixing = new HashMap<String, Object>();

		Map<String, String> mapCompanyName = new HashMap<String, String>();// 基地基本信息

		for (Map<String, Object> map : mapResult) {
			mapCompanyName.put(map.get("company_id").toString(),
					map.get("company_name").toString());

			// 临时存放支付类型
			Map<String, Map<String, Object>> zhifuLeixingTemp = mapAll
					.get(map.get("company_id").toString());
			if (null == zhifuLeixingTemp) {// 所有的里面不包含基地id
				zhifuLeixing = new HashMap<String, Map<String, Object>>();
				feiyongLeixing = new HashMap<String, Object>();
				feiyongLeixing.put("yinshou", map.get("moneys"));

				zhifuLeixing
						.put(map.get("pay_form").toString(), feiyongLeixing);
				mapAll.put(map.get("company_id").toString(), zhifuLeixing);
			} else {
				// 临时存放费用类型
				Map<String, Object> feiyongLeixingTemp = zhifuLeixingTemp
						.get(map.get("pay_form").toString());

				if (null == feiyongLeixingTemp) {
					feiyongLeixing = new HashMap<String, Object>();
					if(map.get("type").toString().equals("1")){
						feiyongLeixing.put("yinshou", map.get("moneys"));
					}else{
						feiyongLeixing.put("tuikuan", map.get("moneys"));
					}
					
					zhifuLeixingTemp.put(map.get("pay_form").toString(),
							feiyongLeixing);
				} else {
					if(map.get("type").toString().equals("1")){
						if(null==feiyongLeixingTemp.get("yinshou")){
							feiyongLeixingTemp.put("yinshou",  map.get("moneys"));
						}
					}else{
						if(null==feiyongLeixingTemp.get("tuikuan")){
							feiyongLeixingTemp.put("tuikuan",  map.get("moneys"));
						}
					}
					
					zhifuLeixingTemp.put(map.get("pay_form").toString(), feiyongLeixingTemp);
					mapAll.put(map.get("company_id").toString(), zhifuLeixingTemp);
				}

			}
		}
		json.put("mapCompanyName", mapCompanyName);
		json.put("mapAll", mapAll);
		
		return json;
	}
	
	//支付资金统计
	@Override
	public JSONObject selectMoneyPay(Company c, String terms) {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		List<String> dateList = Tools.getMonthDaysStartEnd(terms);
		String companySelect = "";
		if (c.getUserRole().getId() == 40) {// 基地用户
			companySelect = " and o.company_id=" + c.getCompanyId();
		}

		String sql1 = "(\n"
				+ "	SELECT\n"
				+ "		sum(o.order_account) AS moneys, c.company_name,\n"
				+ "		o.company_id,\n"
				+ "		o.pay_form,\n"
				+ "		'1' AS type\n"
				+ "	FROM\n"
				+ "		orders o\n left join company c on c.company_id=o.company_id"
				+ "	WHERE\n"  + " pay_time BETWEEN\n '"
				+ dateList.get(0)
				+ "'	AND\n '"
				+ dateList.get(1)+"'"
				+ companySelect
				+ "	GROUP BY\n"
				+ "		o.company_id,\n"
				+ "		o.pay_form\n"
				+ ")\n";
		
//				+ "UNION\n"
//				+ "	(\n"
//				+ "		SELECT\n"
//				+ "			sum(o.order_account) AS moneys,c.company_name,\n"
//				+ "			o.company_id,\n"
//				+ "			o.pay_form,\n"
//				+ "			'2' AS type\n"
//				+ "		FROM\n"
//				+ "			orders o\n left join company c on c.company_id=o.company_id"
//				+ "		RIGHT JOIN orders_return ore ON ore.order_id = o.id\n"
//				+ "		WHERE\n"
//				+ "			o.is_over = 1\n"
//				+ "		AND o.`status` = 50\n"
//				+ "		AND ore.`status` = 2\n"
//				+ "		AND pay_time BETWEEN\n '"
//				+ dateList.get(0)
//				+ "	 '	AND\n  '"
//				+ dateList.get(1)+"'"
//				+ companySelect
//				+ "		GROUP BY\n"
//				+ "			o.company_id,\n" + "			o.pay_form\n" + "	)";
		List<Map<String, Object>> mapResult = statisticalMapper
				.selectModelStatistical(sql1);
		Map<String, Map<String, Map<String, Object>>> mapAll = new HashMap<String, Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> zhifuLeixing = new HashMap<String, Map<String, Object>>();
		Map<String, Object> feiyongLeixing = new HashMap<String, Object>();

		Map<String, String> mapCompanyName = new HashMap<String, String>();// 基地基本信息

		for (Map<String, Object> map : mapResult) {
			mapCompanyName.put(map.get("company_id").toString(),
					map.get("company_name").toString());

			// 临时存放支付类型
			Map<String, Map<String, Object>> zhifuLeixingTemp = mapAll
					.get(map.get("company_id").toString());
			if (null == zhifuLeixingTemp) {// 所有的里面不包含基地id
				zhifuLeixing = new HashMap<String, Map<String, Object>>();
				feiyongLeixing = new HashMap<String, Object>();
				feiyongLeixing.put("yinshou", map.get("moneys"));

				zhifuLeixing
						.put(map.get("pay_form").toString(), feiyongLeixing);
				mapAll.put(map.get("company_id").toString(), zhifuLeixing);
			} else {
				// 临时存放费用类型
				Map<String, Object> feiyongLeixingTemp = zhifuLeixingTemp
						.get(map.get("pay_form").toString());

				if (null == feiyongLeixingTemp) {
					feiyongLeixing = new HashMap<String, Object>();
					if(map.get("type").toString().equals("1")){
						feiyongLeixing.put("yinshou", map.get("moneys"));
					}else{
						feiyongLeixing.put("tuikuan", map.get("moneys"));
					}
					
					zhifuLeixingTemp.put(map.get("pay_form").toString(),
							feiyongLeixing);
				} else {
					if(map.get("type").toString().equals("1")){
						if(null==feiyongLeixingTemp.get("yinshou")){
							feiyongLeixingTemp.put("yinshou",  map.get("moneys"));
						}
					}else{
						if(null==feiyongLeixingTemp.get("tuikuan")){
							feiyongLeixingTemp.put("tuikuan",  map.get("moneys"));
						}
					}
					
					zhifuLeixingTemp.put(map.get("pay_form").toString(), feiyongLeixingTemp);
					mapAll.put(map.get("company_id").toString(), zhifuLeixingTemp);
				}

			}
		}
		json.put("mapCompanyName", mapCompanyName);
		json.put("mapAll", mapAll);
		
		return json;
	}
}