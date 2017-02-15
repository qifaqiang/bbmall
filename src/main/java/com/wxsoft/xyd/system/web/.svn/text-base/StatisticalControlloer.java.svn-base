package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.view.MemberAdminExcelView;
import com.wxsoft.framework.view.MoneyAdminExcelView;
import com.wxsoft.framework.view.MoneyPayAdminExcelView;
import com.wxsoft.framework.view.SalesAdminExcelView;
import com.wxsoft.framework.view.TransactionExcelView;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.StatisticalService;

/**
 * 系统统计
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/statistic")
public class StatisticalControlloer extends BaseController {
	@Autowired
	private StatisticalService statisticalService;
	@Autowired
	private CompanyService companyService;

	/**
	 * 主页统计
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/mainStatistic")
	public void mainStatistic(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = new JSONObject();
		Company c = ((Company) session.getAttribute(SystemConfig.SESSION_USER));
		List<Map<String, Object>> obj = statisticalService.selectMainSql(c);
		json.put("statictis", obj);
		json.put("roleId", c.getUserRole().getId());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首页统计 最近7天订单数据
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/mainLastOrderSql")
	public void selectMainLastOrderSql(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Company c = ((Company) session.getAttribute(SystemConfig.SESSION_USER));
		JSONObject json = statisticalService.selectMainLastOrderSql(c);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首页统计 最近7天交易数据
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/mainLastChargeSql")
	public void mainLastChargeSql(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Company c = ((Company) session.getAttribute(SystemConfig.SESSION_USER));
		JSONObject json = statisticalService.selectMainLastChargeSql(c);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 会员统计 页面 admin
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/memberadmin")
	public ModelAndView memberadmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/statistical/memberadmin");
		return mv;
	}

	/**
	 * 会员统计 页面 普通
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/member")
	public ModelAndView member(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/statistical/member");
		return mv;
	}

	/**
	 * 交易统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/transaction")
	public ModelAndView transaction(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/statistical/transaction");
		mv.addObject("date", Tools.date2Str(new Date(), "yyyy-MM"));
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		List<Company> listCompanyName = new ArrayList<Company>();
		if (user.getUserRole().getId() == 40) {// 基地用户
			listCompanyName.add(user);
		} else {// 系统用户
			Company tempAll = new Company();
			tempAll.setCompanyId(0);
			tempAll.setCompanyName("全部");
			listCompanyName.add(tempAll);
			listCompanyName.addAll(companyService.selectCompanyName(null));
		}

		mv.addObject("listCompanyName", listCompanyName);
		return mv;

	}

	/**
	 * 入账资金统计 页面 admin
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/moneyadmin")
	public ModelAndView moneyadmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("now_month", Tools.date2Str(new Date(), "yyyy-MM"));
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/statistical/moneyadmin");
		return mv;
	}

	/**
	 * 入账资金统计 月
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/moneyadminMonth")
	public void moneyadminMonth(HttpServletRequest request,
			HttpServletResponse response, Integer companyId, String terms,
			HttpSession session) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		json = statisticalService.selectMoney(user, terms);

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 入账资金统计 月 toExcel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/moneyadminexcel")
	public ModelAndView moneyadminexcel(HttpServletRequest request,
			HttpServletResponse response, Integer companyId, String terms,
			HttpSession session) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		json = statisticalService.selectMoney(user, terms);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		MoneyAdminExcelView erv = null;
		erv = new MoneyAdminExcelView();
		dataMap.put("data", json);
		dataMap.put("times", terms);
		return  new ModelAndView(erv, dataMap);
	}

	
	/**
	 * 支付资金统计 页面 admin
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/moneypayadmin")
	public ModelAndView moneyPayadmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("now_month", Tools.date2Str(new Date(), "yyyy-MM"));
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/statistical/moneypayadmin");
		return mv;
	}

	/**
	 * 支付资金统计 月
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/moneypayadminMonth")
	public void moneyPayAdminMonth(HttpServletRequest request,
			HttpServletResponse response, Integer companyId, String terms,
			HttpSession session) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		json = statisticalService.selectMoneyPay(user, terms);

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 支付资金统计 月 toExcel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/moneypayadminexcel")
	public ModelAndView moneyPayAdminexcel(HttpServletRequest request,
			HttpServletResponse response, Integer companyId, String terms,
			HttpSession session) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		json = statisticalService.selectMoneyPay(user, terms);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		MoneyPayAdminExcelView erv = null;
		erv = new MoneyPayAdminExcelView();
		dataMap.put("data", json);
		dataMap.put("times", terms);
		return  new ModelAndView(erv, dataMap);
	}
	
	
	
	/**
	 * 查询交易统计前一天
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/transactionLastDay")
	public void transactionLastDay(HttpServletRequest request,
			HttpServletResponse response, Integer companyId, String terms,
			HttpSession session) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		if (user.getUserRole().getId() == 40) {// 基地用户
			companyId = user.getCompanyId();
			Company comp = new Company();
			comp.setCompanyId(companyId);
			json = statisticalService.selectTransaction(comp, terms);
		} else {// 系统用户
			if (companyId == null || companyId.equals("")) {
				json = statisticalService.selectTransaction(null, terms);
			} else {
				Company company = new Company();
				company.setCompanyId(companyId);
				json = statisticalService.selectTransaction(company, terms);
			}

		}

		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/transactionRangeDayAll")
	public void transactionRangeDayAll(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			Integer companyId, String type, String times) {

		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		if (user.getUserRole().getId() == 40) {
			companyId = user.getCompanyId();
			Company comp = new Company();
			comp.setCompanyId(companyId);
			json = statisticalService.selectTransactionRandeDayAll(comp, type,
					times);
		} else {
			if (companyId == null || companyId.equals("")) {
				json = statisticalService.selectTransactionRandeDayAll(null,
						type, times);
			} else {
				Company company = new Company();
				company.setCompanyId(companyId);
				json = statisticalService.selectTransactionRandeDayAll(company,
						type, times);
			}
		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/transactionLastDayAll")
	public void transactionLastDayAll(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Integer companyId) {
		JSONObject json = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		if (user.getUserRole().getId() == 40) {
			companyId = user.getCompanyId();
			Company comp = new Company();
			comp.setCompanyId(user.getCompanyId());
			json = statisticalService.selectTransactionLastDay(comp);
		} else {// 系统用户
			if (companyId == null || companyId.equals("")) {
				json = statisticalService.selectTransactionLastDay(null);
			} else {
				Company company = new Company();
				company.setCompanyId(companyId);
				json = statisticalService.selectTransactionLastDay(company);
			}

		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 会员统计 昨天
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/memberLastOneDayAdmin")
	public void memberLastOneDayAdmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JSONObject json = statisticalService.selectMemberLastOneDay(null);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 普通会员统计 昨天
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping("/memberLastOneDay")
	public void memberLastOneDayCommon(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		user.setCompanyId(34);
		JSONObject json = statisticalService.selectMemberComonLastOneDay(user);
		json.put("companyName", user.getCompanyName());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 会员统计 范围
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/memberRangeDayAdmin")
	public void memberRangeDayAdmin(HttpServletRequest request, String type,
			String times, HttpServletResponse response, HttpSession session) {
		JSONObject json = statisticalService.selectMemberRangeDay(type, times);
		try {
			responseAjax(JSON.toJSONString( json, SerializerFeature.DisableCircularReferenceDetect) , response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/transactionRangeDay")
	public void transactionRangeDay(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			Integer companyId, String terms, String type, String times) {
		// 所有基地信息
		JSONObject json = new JSONObject();
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);

		if (user.getUserRole().getId() == 40) {// 基地用户
			companyId = user.getCompanyId();
			Company comp = new Company();
			comp.setCompanyId(companyId);
			json = statisticalService.selectTransactionRangeDay(comp, type,
					terms, times);
		} else {// 系统用户
			if (companyId == null || companyId.equals("")) {
				json = statisticalService.selectTransactionRangeDay(null, type,
						terms, times);
			} else {
				Company company = new Company();
				company.setCompanyId(companyId);
				json = statisticalService.selectTransactionRangeDay(company,
						type, terms, times);
			}

		}
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/memberRangeDay")
	public void memberRangeDay(HttpServletRequest requesty, String type,
			String times, HttpServletResponse response, HttpSession session) {
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		user.setCompanyId(34);
		JSONObject json = statisticalService.selectMemberCommonRangeDay(type,
				times, user.getCompanyId());
		json.put("companyName", user.getCompanyName());

		try {
			responseAjax(json, response);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 销售统计 页面 admin
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/salesadmin")
	public ModelAndView salesadmin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("date", Tools.date2Str(new Date(), "yyyy-MM"));
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		List<Company> listCompanyName = new ArrayList<Company>();
		if (user.getUserRole().getId() == 40) {// 基地用户
			listCompanyName.add(user);
		} else {// 系统用户
			Company tempAll = new Company();
			tempAll.setCompanyId(0);
			tempAll.setCompanyName("全部");
			listCompanyName.add(tempAll);
			listCompanyName.addAll(companyService.selectCompanyName(null));
		}
		mv.addObject("listCompanyName", listCompanyName);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/statistical/salesadmin");
		return mv;
	}

	/**
	 * 销售统计 范围 普通
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/salesRangeDayAdmin")
	public void salesRangeDayAdmin(HttpServletRequest request,
			Integer companyId, String times, HttpServletResponse response,
			HttpSession session) {
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		if (user.getUserRole().getId() == 40) {// 基地用户
			companyId = user.getCompanyId();
		} else {// 系统用户
		}
		JSONObject json = statisticalService.selectSalesRangeDay(companyId,
				times);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 销售统计 范围 普通 toexcel
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/salesadmintoexcel")
	public ModelAndView salesadmintoexcel(HttpServletRequest request,
			Integer companyId, String times, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = null;
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		// 所有基地信息
		if (user.getUserRole().getId() == 40) {// 基地用户
			companyId = user.getCompanyId();
		} else {// 系统用户
		}
		JSONObject json = statisticalService.selectSalesRangeDayToExcel(
				companyId, times);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SalesAdminExcelView erv = null;
		erv = new SalesAdminExcelView();
		dataMap.put("data", json);
		dataMap.put("times", times);
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	/**
	 * 会员统计 范围 普通 toexcel
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/memberadmintoexcel")
	public ModelAndView memberadmintoexcel(HttpServletRequest request,
			Integer companyId, String times, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = null;
		Map<String, Object> json = statisticalService
				.memberadmintToExcel(times);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		MemberAdminExcelView erv = null;
		erv = new MemberAdminExcelView();
		dataMap.put("data", json);
		dataMap.put("times", times);
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	@RequestMapping("/transactionToExcel")
	public ModelAndView transactionToExcel(HttpServletRequest request,
			Integer companyId, String times, HttpSession session,
			String companyName) {
		ModelAndView mv = null;

		JSONObject json = new JSONObject();
		Company user = (Company) session
				.getAttribute(SystemConfig.SESSION_USER);
		if (user.getUserRole().getId() == 40) {
			json = statisticalService.selectTransactionRangeDayToExcel(user,
					times);
		} else {
			if (companyId == null || companyId.equals("")) {
				json = statisticalService.selectTransactionRangeDayToExcel(
						null, times);
			} else {
				Company company = new Company();
				company.setCompanyId(companyId);
				json = json = statisticalService
						.selectTransactionRangeDayToExcel(company, times);
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		TransactionExcelView erv = new TransactionExcelView();
		dataMap.put("data", json);
		dataMap.put("times", times + companyName);
		mv = new ModelAndView(erv, dataMap);
		return mv;

	}

}
