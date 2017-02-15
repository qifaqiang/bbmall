package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.wxsoft.xyd.prod.model.ProductSpecification;
import com.wxsoft.xyd.prod.service.ProductSpecificationService;
import com.wxsoft.xyd.quartz.model.SysScheduleJob;
import com.wxsoft.xyd.quartz.service.SysScheduleJobService;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.ResultModel;

/**
 * 商品规格管理
 * 
 * @author qfq
 * 
 */
@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/scheduleJob")
public class SysScheduleJobControlloer extends BaseController {
	@Autowired
	private ProductSpecificationService productSpecificationService;
	
	@Autowired
	private SysScheduleJobService  sysScheduleJobService;
	

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
	public ModelAndView list(SysScheduleJob obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN + "/quartz/list");
		List<SysScheduleJob> list = sysScheduleJobService.listPageBySysScheduleJob(obj);
		mv.addObject("objList", list);
		mv.addObject("objs", obj);
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
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/quartz/info");
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
		ModelAndView mv = new ModelAndView(SystemConfig.SYSTEM_PATH_ADMIN + "/quartz/info");
		SysScheduleJob obj = sysScheduleJobService.selectByPrimaryKey(id);
		mv.addObject("obj", obj);
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
	public void save(SysScheduleJob obj, HttpServletRequest request,HttpSession session,
			HttpServletResponse response) {
		ResultModel rm = null;
		if (Tools.notEmpty(obj.getName()) && Tools.notEmpty(obj.getJobGroupName())) {
			obj.setCompanyId(((Company) session
						.getAttribute(SystemConfig.SESSION_USER))
						.getCompanyId());
			
			if (null == obj.getJobId()) {
				// 添加
				int i = sysScheduleJobService.insertSelective(obj);
				if (i > 0) {
					rm = new ResultModel("友情提示", "保存成功", "success", "list.html");
				}

			} else {
				int i = sysScheduleJobService.updateByPrimaryKeySelective(obj);
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
		if (sysScheduleJobService.deleteByPrimaryKey(Integer.parseInt(listId[0])) > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 暂停
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/pauseone")
	public void pauseone(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		if (sysScheduleJobService.updatePauseOne(Integer.parseInt(listId[0])) > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/resumeone")
	public void resumeone(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		if (sysScheduleJobService.updateRestartOne(Integer.parseInt(listId[0])) > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重启
	 * 
	 * @param request
	 * @param res
	 * @param DATA
	 * @param session
	 */
	@RequestMapping(value = "/restartone")
	public void restartone(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);
		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		if (sysScheduleJobService.updateRestartOne(Integer.parseInt(listId[0])) > 0) {
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
