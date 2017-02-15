/**   
 * @文件名称: UserRoleControlloer.java
 * @类路径: com.wxltsoft.fxshop.system.web
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-07-11 上午10:03:52  
 */

package com.wxsoft.xyd.system.web;

import java.io.UnsupportedEncodingException;
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
import com.wxsoft.xyd.system.model.ResultModel;
import com.wxsoft.xyd.system.model.SysMenu;
import com.wxsoft.xyd.system.service.SysMenuService;

/**
 * @类功能说明：用户权限
 * @类修改者：kyz
 * @修改日期：2015-07-11
 * @修改说明：
 * @回复名称：kyz
 * @作者：kyz
 * @创建时间：2015-07-11 上午10:03:52
 */

@Controller
@Access(intercepter = true, rule = "system", path = "/system")
@RequestMapping(SystemConfig.FXSHOP_ACCESS_ANNOTATION_SYSTEM + "/sysmenu")
public class SysMenuControlloer extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 
	 * @描述: 管理权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/menulist")
	public ModelAndView menulist(HttpServletRequest request, String mstat,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(SystemConfig.SYSTEM_PATH_ADMIN + "/userrole/menuinfo");
		mv.addObject("objList", sysMenuService.getAllFirstLevelSysMenu(null));
		return mv;
	}

	/**
	 * @throws java.lang.Exception
	 * 
	 * @描述: 保存权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/saveMenu")
	public void saveMenu(SysMenu sm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws java.lang.Exception {
		ResultModel rm = null;
		if (Tools.isEmpty(sm.getName())) {
			rm = new ResultModel("友情提示", "保存失败：权限名称不能为空", "error", "");
		} else {
			if (null != sm.getId()) {
				if (sysMenuService.updateByPrimaryKeySelective(sm) > 0) {
					rm = new ResultModel("友情提示", "更新成功", "success", "");
				}

			} else {
				if (sysMenuService.insert(sm) > 0) {
					rm = new ResultModel("友情提示", sm.getId()+"", "success", "");
				}
			}
		}

		try {
			responseAjax(BaseConfig.CheckReslut(rm), response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述: 获取子权限
	 * @作者: kyz
	 * @日期:2013-3-30
	 * @修改内容
	 * @参数： @param response
	 * @参数： @return
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping("/getSubList")
	public void getSubList(Integer parentId, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		List<SysMenu> smlist = sysMenuService.getAllByParentId(parentId);
		try {
			responseAjax(smlist, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delone")
	public void delete(HttpServletRequest request, HttpServletResponse res,
			String DATA, HttpSession session) {
		JSONObject jsonObject = JSONObject.parseObject(DATA);

		String[] listId = jsonObject.getString("CTIDS").split(",");
		String result = "{'flag':false}";
		for (String string : listId) {
			sysMenuService.deleteByPrimaryKey(Integer.parseInt(string));
			result = "{'flag':true}";
		}
		try {
			responseAjax(result, res);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}