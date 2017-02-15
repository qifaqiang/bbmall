package com.wxsoft.xyd.system.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.BinaryTest;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 商品分类管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/prodcatalog")
public class ProductCatalogController extends BaseController {
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private ProductService productService;

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
	public ModelAndView list(ProductCatalog obj, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		obj.setPid(0);
		List<ProductCatalog> rollPicList = productCatalogService.getAllByProductCatalog(obj);
		mv.addObject("objList", rollPicList);
		mv.addObject("objs", obj);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodCatalog/list");
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
		List<ProductCatalog> list = productCatalogService.getFirstProductCatalog();
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodCatalog/info");
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
	public ModelAndView update(HttpServletRequest request, HttpSession session, Integer id) {
		ModelAndView mv = new ModelAndView();
		ProductCatalog obj = productCatalogService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
		List<ProductCatalog> list = productCatalogService.getFirstProductCatalog();
		mv.addObject("list", list);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN_PRODUCT + "/prodCatalog/info");
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
	public void save(ProductCatalog obj, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("myfile");
		if (null != myfile && !myfile.isEmpty()) {
			String filePath = Tools.uploadpicByShow(myfile, "prodCatalog", 200);
			if (null != filePath) {
				obj.setPic(filePath);
			}
		}
		MultipartFile myfilewap = multipartRequest.getFile("myfilewap");
		if (null != myfilewap && !myfilewap.isEmpty()) {
			String filePath = Tools.uploadpicByShow(myfilewap, "prodCatalog",
					100);
			if (null != filePath) {
				obj.setWapPic(filePath);
			}
		}
		
		MultipartFile myfilepc = multipartRequest.getFile("myfilepc");
		if (null != myfilepc && !myfilepc.isEmpty()) {
			String filePath = Tools.uploadpicByShow(myfilepc, "prodCatalog",
					100,24);
			if (null != filePath) {
				obj.setPcPic(filePath);
				try {
					Tools.cpFile(BaseConfig.FX_PIC_PATH+filePath.replace(".", "_100."), BaseConfig.FX_PIC_PATH+filePath.replace(".", "_100GRAY."));
					BinaryTest.Binarys((new File(BaseConfig.FX_PIC_PATH+filePath.replace(".", "_100GRAY."))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		if (Tools.notEmpty(obj.getName())) {
			if (null == obj.getId()) {
				obj.setAddtime(new Date());
				if (null == obj.getPid() || obj.getPid() == 0) {
					obj.setLevel(1);
				} else {
					obj.setLevel(2);
				}
				// 添加
				int i = productCatalogService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				if (null == obj.getPid() || obj.getPid() == 0) {
					obj.setLevel(1);
				} else {
					obj.setLevel(2);
				}
				int i = productCatalogService.updateByPrimaryKeySelective(obj);
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

	/**
	 * 删除二级分类
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res, HttpServletResponse response, Integer id,
			HttpSession session) {
		ResultModel rm = null;
		Product p = new Product();
		p.setCatalogId2(id);
		List<Product> productList = productService.getAllByProduct(p);
		rm = new ResultModel("友情提示", "添加库存成功", "success", "String Url", productList);
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一级分类
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delone1")
	public void delete1(HttpServletRequest request, HttpServletResponse res, HttpServletResponse response, Integer id,
			HttpSession session) {
		ResultModel rm = null;
		ProductCatalog p = new ProductCatalog();
		p.setPid(id);
		List<ProductCatalog> productList = productCatalogService.getAllByProductCatalog(p);
		rm = new ResultModel("友情提示", "添加库存成功", "success", "String Url", productList, 0);
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除二级分类
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/delete")
	public void deleteD(HttpServletRequest request, HttpServletResponse res, HttpServletResponse response, Integer id,
			HttpSession session) {
		ResultModel rm = null;
		int i = productCatalogService.deleteByPrimaryKey(id);
		if (i > 0) {
			rm = new ResultModel("友情提示", "删除成功", "success", "String Url");
		} else {
			rm = new ResultModel("友情提示", "删除失败", "error", "String Url");
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
