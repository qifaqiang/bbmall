/**   
 * @文件名称: CompanyControlloer.java
 * @类路径: com.wxsoft.xyd.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.common.model.CompanyStockinRecord;
import com.wxsoft.xyd.common.service.CompanyStockService;
import com.wxsoft.xyd.common.service.CompanyStockinRecordService;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.service.ProductBasicService;
import com.wxsoft.xyd.system.model.ComStock;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.UserRoleAssociated;
import com.wxsoft.xyd.system.service.CompanyService;
import com.wxsoft.xyd.system.service.UserRoleAssociatedService;
import com.wxsoft.xyd.system.service.UserRoleService;

/**
 * @类功能说明：商户信息管理
 * @类修改者：kyz @修改日期：2015-07-11 @修改说明：
 * @回复名称：kyz
 * @作者：kyz @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM
		+ "/companyStockinRecord")
public class CompanyStockinRecordControlloer extends BaseController {

	@Autowired
	private CompanyStockinRecordService companyStockinRecordService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserRoleAssociatedService userRoleAssociatedService;
	@Autowired
	private CompanyStockService comanyStockService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductBasicService productBasicService;

	/**
	 * 库存进货记录表
	 * 
	 * @param company
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/inlist")
	public ModelAndView inlist(CompanyStockinRecord company,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/companyStockinRecord/inlist");
		List<CompanyStockinRecord> companyList = companyStockinRecordService
				.listPageByCompanyStockinRecord(company);
		mv.addObject("objList", companyList);
		mv.addObject("company", company);
		return mv;
	}

	/**
	 * 库存进货记录表
	 * 
	 * @param company
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/inBaselist")
	public ModelAndView inBaselist(CompanyStockinRecord company,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		company.setCompanyIdName(null);
		company.setCompanyId(((Company) session
				.getAttribute(SystemConfig.SESSION_USER)).getCompanyId());
		List<CompanyStockinRecord> companyList = companyStockinRecordService
				.listPageByCompanyStockinRecord(company);
		mv.addObject("objList", companyList);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/companyStockinRecord/inBaselist");
		mv.addObject("company", company);
		return mv;
	}


	/**
	 * 库存查询
	 * 
	 * @param company
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/kucunlist")
	public ModelAndView reserve(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/companyStockinRecord/kucunlist");
		Company company_cur = (Company) session.getAttribute(SystemConfig.SESSION_USER);//当前登录用户
		
		Company company = new Company();
		if("40".equals(company_cur.getUserRole().getId()+"")){//如果当前角色是40 说明是基地用户，则只能查询自己的库存量
			company.setCompanyId(company_cur.getCompanyId());
		}
		// 查询出所有的基地用户名称，当roleID=40时候为基地用户
		List<Company> ListCompanyName = companyService.selectCompanyName(company);
		mv.addObject("CompanyList", ListCompanyName);
		mv.addObject("companyId", company_cur.getCompanyId());
		mv.addObject("rolId", company_cur.getUserRole().getId());
		return mv;
	}


}