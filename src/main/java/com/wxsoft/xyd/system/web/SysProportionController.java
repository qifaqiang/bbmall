package com.wxsoft.xyd.system.web;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.ad.model.AdDetail;
import com.wxsoft.xyd.ad.service.AdDetailService;
import com.wxsoft.xyd.common.model.RollPic;
import com.wxsoft.xyd.common.model.SysNews;
import com.wxsoft.xyd.common.model.SysNewsCatalog;
import com.wxsoft.xyd.common.service.RollPicService;
import com.wxsoft.xyd.common.service.SysNewsCatalogService;
import com.wxsoft.xyd.common.service.SysNewsService;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.service.ProductCatalogService;
import com.wxsoft.xyd.prod.service.ProductService;
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.SysProportion;
import com.wxsoft.xyd.system.model.SysSearchhot;
import com.wxsoft.xyd.system.service.SysProportionService;
import com.wxsoft.xyd.system.service.SysSearchhotService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 系統配置管理
 * 
 * @author kyz
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/sysProportion")
public class SysProportionController extends BaseController {

	@Autowired
	private SysProportionService sysProportionService;
	@Autowired
	private SysNewsService sysNewsService;
	@Autowired
	private SysNewsCatalogService sysNewsCatalogService;
	@Autowired
	private ProductCatalogService productCatalogService;
	@Autowired
	private SysSearchhotService sysSearchhotService;
	@Autowired
	private ProductService productService;
	@Autowired
	private RollPicService rollPicService;
	@Autowired
	private AdDetailService adDetailService;

	/**
	 * 获取信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/sysProportionList")
	public ModelAndView updateSysPropor(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		SysProportion sp = sysProportionService.selectByPrimaryKey(1);
		mv.addObject("SysProportion", sp);
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN
				+ "/sysProportion/sysProportionAdd");
		return mv;
	}

	/**
	 * 刷新系统缓存
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/sysRefresh")
	public void sysRefresh(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		Configuration cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(BaseConfig.FX_FTL_PATH
					+ "/WEB-INF/classes/freemarkTemplate/"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");
			/* Get or create a template */
			Template temp = cfg.getTemplate("top.ftl");
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("TIME", Tools.date2Str(new Date()));
			root.put("SHOPDOMAIN", BaseConfig.FX_DOMAIN_WWW);
			root.put("USERIMGSRC", BaseConfig.FX_DOMAIN_IMAGE8888);
			// 热点搜索
			List<SysSearchhot> sshLIst = sysSearchhotService
					.getAllBySysSearchhot(null);
			root.put("hotSearchs", sshLIst);
			// 商品分类
			ProductCatalog pc = new ProductCatalog();
			pc.setLevel(1);
			List<ProductCatalog> productCatalogLIst = productCatalogService
					.getAllByProductCatalog(pc);
			root.put("productCatalogLIst", productCatalogLIst);
			this.fileWrite(temp, BaseConfig.FX_FTL_PATH + "/WEB-INF/jsp/front/"
					+ "topShop.jsp", root);

			Template tempFoot = cfg.getTemplate("foot.ftl");
			// 帮助中心
			SysNewsCatalog sc = new SysNewsCatalog();
			sc.setIsWep(0);
			List<SysNewsCatalog> sclist = sysNewsCatalogService
					.getAllBySysNewsCatalog(sc);
			for (SysNewsCatalog s : sclist) {
				SysNews snTemp = new SysNews();
				snTemp.setCatalogId(s.getId());
				List<SysNews> snlist = sysNewsService.getAllBySysNews(snTemp);
				s.setSn(snlist);
			}
			root.put("sclist", sclist);
			root.put("sp", BaseConfig.sysProportion);
			this.fileWrite(tempFoot, BaseConfig.FX_FTL_PATH
					+ "/WEB-INF/jsp/front/" + "footShop.jsp", root);

			Template tempMain = cfg.getTemplate("main.ftl");

			// 推荐商品

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("prodTagsId", 3);
			maps.put("limit", "8");
			List<Map<String, Object>> tuiProduct = productService
					.selectProductFrontByShowList(maps);
			root.put("tuiProduct", tuiProduct);

			// 分类商品
			for (ProductCatalog s : productCatalogLIst) {
				maps = new HashMap<String, Object>();
				maps.put("catalogId1", s.getId());
				maps.put("limit", "10");
				List<Map<String, Object>> pcProduct = productService
						.selectProductFrontByShowList(maps);
				s.setProductList(pcProduct);
			}

			// 广告 长条
			AdDetail adDetail = new AdDetail();
			adDetail.setType(5);
			List<Map<String, Object>> adlist = adDetailService
					.getAllByMap(adDetail);

			// 广告 分类
			AdDetail adDetail7 = new AdDetail();
			adDetail7.setType(7);
			List<Map<String, Object>> adlist7 = adDetailService
					.getAllByMap(adDetail7);

			// 轮播图显示
			RollPic rp = new RollPic();
			rp.setType(1);
			List<Map<String, Object>> rplist = rollPicService
					.getAllByRollPic(rp);

			root.put("adlist", adlist);
			root.put("adlist7", adlist7);
			root.put("rplist", rplist);
			root.put("sp", BaseConfig.sysProportion);
			this.fileWrite(tempMain, BaseConfig.FX_FTL_PATH
					+ "/WEB-INF/jsp/front/" + "index.jsp", root);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				responseAjax(e1.getMessage(), response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			responseAjax("success", response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 保存
	 * 
	 * @param sysProportion
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(SysProportion sysProportion, HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = null;
		sysProportion.setId(1);
		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		// MultipartFile myfile = multipartRequest.getFile("myfile");
		// if (null != myfile && !myfile.isEmpty()) {
		// String filePath = Tools.uploadpic(myfile, "uploadSysprotion");
		// if (null != filePath) {
		// sysProportion.setLogo(filePath);
		// }
		// }
		// DESPlus desPlus;
		// String result = "";
		// try {
		// desPlus = new DESPlus(SystemConfig.DES_MICHI);
		// result = desPlus.encrypt(sysProportion.getEmailPasswd());
		// sysProportion.setEmailPasswd(result);
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		if (null != sysProportion.getAutoAcceptTime()
				|| sysProportion.getAutoAcceptTime() == 0
				|| null != sysProportion.getAutoCancelTime()
				|| sysProportion.getAutoCancelTime() == 0
				|| sysProportion.getFirstSubtractPrice().doubleValue() < 0) {
			rm = new ResultModel("友情提示", "保存失败", "error", "");
		}
		if (null != rm) {
			int i = sysProportionService
					.updateByPrimaryKeySelective(sysProportion);
			if (i > 0) {
				SysProportion sp = sysProportionService.selectByPrimaryKey(1);// 重新把系统配置放在application中
				// try {
				// desPlus = new DESPlus(SystemConfig.DES_MICHI);
				// result = desPlus.decrypt(sysProportion.getEmailPasswd());
				// sp.setEmailPasswd(result);
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				BaseConfig.sysProportion = sp;
				request.getSession().setAttribute("SYSPROPORTION", sp);
				rm = new ResultModel("友情提示", "保存成功", "success",
						"sysProportionList.html");
			}
		}
		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void fileWrite(Template temp, String filename,
								 Map<String, Object> root) {
		File file = new File(filename);
		FileOutputStream out;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(filename);
			try {
				temp.process(root, new OutputStreamWriter(out,"utf-8"));
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
