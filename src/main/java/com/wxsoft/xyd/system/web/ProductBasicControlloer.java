package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.common.model.CompanyStockinRecord;
import com.wxsoft.xyd.common.service.CompanyStockService;
import com.wxsoft.xyd.common.service.CompanyStockinRecordService;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;
import com.wxsoft.xyd.prod.service.ProductBasicService;
import com.wxsoft.xyd.prod.service.ProductSpecificationInfoService;
import com.wxsoft.xyd.prod.service.ProductSpecificationStockService;
import com.wxsoft.xyd.system.model.AjaxPage;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.service.CompanyService;

/**
 * 商品字典库管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/prodBasic")
public class ProductBasicControlloer extends BaseController {
	@Autowired
	private ProductBasicService productBasicService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyStockService comanyStockService;
	// 记录进货表
	@Autowired
	private CompanyStockinRecordService companyStockinRecordService;
	@Autowired
	private ProductSpecificationInfoService productSpecificationInfoService;
	@Autowired
	private ProductSpecificationStockService productSpecificationStockService;

	/**
	 * 列表
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(ProductBasic obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<ProductBasic> list = productBasicService
				.listPageByProductBasic(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
				+ "/prodBasic/list");
		return mv;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
				+ "/prodBasic/info");
		return mv;
	}

	/**
	 * 设置基地库存
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/reserve")
	public ModelAndView reserve(HttpServletRequest request, HttpSession session) {
		
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
				+ "/prodBasic/reserve");
		Company company = new Company();
		// 查询出所有的基地用户名称，当roleID=40时候为基地用户
		List<Company> CompanyList = companyService
				.selectCompanyName(company);
		mv.addObject("CompanyList", CompanyList);
		return mv;
	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, HttpSession session,
			Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductBasic obj = productBasicService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT
				+ "/prodBasic/info");
		return mv;

	}

	/**
	 * 保存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(ProductBasic obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getName())) {
			if (null == obj.getId()) {
				// 添加
				int i = productBasicService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				int i = productBasicService.updateByPrimaryKeySelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}
			}

			Map<Integer, ProductBasic> pbs = new HashMap<Integer, ProductBasic>(); // 刷新商品字典表缓存
			List<ProductBasic> pblist = productBasicService
					.getAllByProductBasic(null);
			for (ProductBasic productBasic : pblist) {
				pbs.put(productBasic.getId(), productBasic);
			}
			BaseConfig.productBasicMap = pbs;

		} else {
			rm = new ResultModel("友情提示", "存在非必填项为空", "error", "");
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			if (productBasicService
					.deleteByPrimaryKey(Integer.parseInt(string)) > 0) {
				result = "{'flag':true}";
			}
		}
		
		Map<Integer, ProductBasic> pbs = new HashMap<Integer, ProductBasic>(); // 刷新商品字典表缓存
		List<ProductBasic> pblist = productBasicService
				.getAllByProductBasic(null);
		for (ProductBasic productBasic : pblist) {
			pbs.put(productBasic.getId(), productBasic);
		}
		BaseConfig.productBasicMap = pbs;
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增库存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addStock")
	public void addStock(CompanyStock obj, HttpServletRequest request,
			HttpServletResponse response, Integer prodBasicId,
			HttpSession session) {
		ResultModel rm = null;
		Company com = new Company();
		CompanyStockinRecord csr = new CompanyStockinRecord();

		// 记录库存变化 //把修改记录存入 基地库存记录进货表中 company_stockin_record;
		com = (Company) session.getAttribute(SystemConfig.SESSION_USER);
		csr.setAdduserid(com.getCompanyId());
		csr.setCount(obj.getInventorynumber());
		csr.setBasicId(obj.getProdBasicId());
		csr.setCompanyId(obj.getCompanyId());
		csr.setAddtime(new Date());
		companyStockinRecordService.insertSelective(csr);
		if (null == obj.getId()) {
			// 添加
			int i = comanyStockService.insertSelective(obj);
			if (i > 0) {
				Integer invent = obj.getInventorynumber();
				rm = new ResultModel("友情提示", "添加库存成功", "success", "String Url",
						invent);
				// 把修改记录存入 基地库存记录进货表中 company_stockin_record;

			}

		} else {
			int i = comanyStockService.updateSelective(obj);
			if (i > 0) {
				obj = comanyStockService.selectByPrimaryKey(obj.getId());
				Integer invent = obj.getInventorynumber();
				rm = new ResultModel("友情提示", "进货成功", "success", "String Url",
						invent);
			}
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改库存
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateStock")
	public void updateStock(ProductBasic obj, HttpServletRequest request,
			HttpServletResponse response) {
		/* getSession().getAttribute("staffInfo").get("staff_id"); */
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getName())) {
			if (null == obj.getId()) {
				// 添加
				int i = productBasicService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}
			} else {
				int i = productBasicService.updateByPrimaryKeySelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}
			}
		} else {
			rm = new ResultModel("友情提示", "存在非必填项为空", "error", "");
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	private ServletRequest getSession() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Access(intercepter = false)
	@RequestMapping(value = "/getallprodbycompanyid")
	public void getallprodbycompanyid(HttpServletRequest request, HttpServletResponse res,
			ProductSpecificationInfo psi,Integer currentPage,Integer showCount, HttpSession session) {
		
		AjaxPage ap = new AjaxPage();
		ap.setShowCount(showCount);
		ap.setCurrentPage(currentPage);
		ap.setFunctionName("showallprodforpage");
		
		psi.setAjaxPage(ap);
		List<ProductSpecificationInfo> prodSpecInfoList = productSpecificationInfoService.listAjaxPageGetSpecProdByCompanyId(psi);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", prodSpecInfoList);
		map.put("page", psi.getAjaxPage());
		try {
			responseAjax(map, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增库存  new
	 * 
	 * @param wb
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addStockNew")
	public void addStockNew(ProductSpecificationStock obj,String values, HttpServletRequest request,
			HttpServletResponse response, Integer prodBasicId,
			HttpSession session) {
		ResultModel rm = null;
		Company com = new Company();
		CompanyStockinRecord csr = new CompanyStockinRecord();
		int type = 0;
		int specificationInfoId = 0;
		int productId =0;
		if(StringUtils.isNoneBlank(values) && !"null".equals(values)){
			String[] values_t = values.split("_");
			type = Integer.parseInt(values_t[0]);
			specificationInfoId = Integer.parseInt(values_t[1]);
			productId = Integer.parseInt(values_t[2]);
		}
		
		// 记录库存变化 //把修改记录存入 基地库存记录进货表中 company_stockin_record;
		com = (Company) session.getAttribute(SystemConfig.SESSION_USER);
		csr.setAdduserid(com.getCompanyId());
		csr.setCount(obj.getInventorycount());
		csr.setCompanyId(obj.getCompanyId());
		csr.setType(type);
		csr.setProductId(productId);
		if(1== type){
			csr.setSpecificationInfoId(specificationInfoId);
		}
		csr.setAddtime(new Date());
		companyStockinRecordService.insertSelective(csr);
		if (null == obj.getId() || "null".equals(obj.getId()+"") || "0".equals(obj.getId()+"")) {
			// 添加
			obj.setType(type);
			obj.setSpecificationInfoId(specificationInfoId);
			obj.setProductId(productId);
			int i = productSpecificationStockService.insertSelective(obj);
			if (i > 0) {
				Integer invent = obj.getInventorycount();
				rm = new ResultModel("友情提示", "添加库存成功", "success", "String Url",
						invent);
				// 把修改记录存入 基地库存记录进货表中 company_stockin_record;

			}

		} else {
			int i = productSpecificationStockService.updateProdStock(obj);
			if (i > 0) {
				obj = productSpecificationStockService.selectByPrimaryKey(obj.getId());
				Integer invent = obj.getInventorycount();
				rm = new ResultModel("友情提示", "进货成功", "success", "String Url",
						invent);
			}
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
