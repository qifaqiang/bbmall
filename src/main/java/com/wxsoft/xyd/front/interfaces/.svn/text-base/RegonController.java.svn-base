package com.wxsoft.xyd.front.interfaces;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.json.JsonLibUtils;
import com.wxsoft.xyd.system.model.Region;
import com.wxsoft.xyd.system.service.RegionService;

/**
 * 
 * @类功能说明：前端商城页面
 * @类修改者：kyz
 * @修改日期：2013-8-10
 * @修改说明：
 * @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-10 下午03:47:55
 */
@Controller
@Access(intercepter = false, rule = "customer", path = "/front", weixinfrom = false)
@RequestMapping(SystemConfig.FXSHOP_ANNOTATION_FRONT + "/region")
public class RegonController extends BaseController {

	@Autowired
	private RegionService regionService;

	/**
	 * 查询中国所有的省份
	 * 
	 * @author ljx
	 * @date 2015年7月16日下午3:45:59
	 */
	@RequestMapping(value = "/getprovincByLevel")
	public void getProvince(HttpSession session, HttpServletResponse response,
			Integer countryId) {
		List<Region> provinceList = regionService.listAllProvince();
		JSONArray result = JsonLibUtils.listToStringWithConfig(provinceList,
				new String[] { "regionname", "alphabetic", "levels",
						"parentid", "sn" ,"regionid"});
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据省份编号查询地市信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getprovinceid")
	public void getRegion(HttpSession session, HttpServletResponse response,
			Integer regionid) {
		List<Region> regionList = regionService
				.listAllCityByProvinceId(regionid);
		JSONArray result = JsonLibUtils.listToStringWithConfig(regionList,
				new String[] { "regionname", "alphabetic", "levels",
						"parentid", "sn" ,"regionid"});
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据地市编号查询地区信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getcityid")
	public void getCityRegion(HttpSession session,
			HttpServletResponse response, Integer regionid) {
		List<Region> regionList = regionService.listAllAreaByCityId(regionid);
		JSONArray result = JsonLibUtils.listToStringWithConfig(regionList,
				new String[] { "regionname", "alphabetic", "levels",
						"parentid", "sn","regionid" });
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getSteetId")
	public void getStreetRegion(HttpServletResponse response, Integer streetId) {
		List<Region> regionList = regionService.listAllAreaByStreeId(streetId);
		JSONArray result = JsonLibUtils.listToStringWithConfig(regionList,
				new String[] { "regionname", "alphabetic", "levels",
						"parentid", "sn" ,"regionid"});
		try {
			responseAjax(result, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getRegionById")
	public void getRegionById(HttpServletResponse response, Integer regionid) {
		Region region = regionService.getByRegionId(regionid);
		JSONObject json = new JSONObject();
		json.put("region", region);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
