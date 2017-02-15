package com.wxsoft.xyd.front.interfaces;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.annotation.Access;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;

@Controller
@Access(intercepter = false, rule = "front", path = "/")
@RequestMapping("/publicinterface")
public class PublicInterfaceControlloer extends BaseController {

	public static Boolean getBoolByType(String imgType) {
		System.out.println(imgType);
		int lastFile = imgType.lastIndexOf(".");
		String file = imgType.substring(lastFile + 1, imgType.length());
		if (file.equals("jpg") || file.equals("jpeg") || file.equals("png")
				|| file.equals("bmp") || file.equals("log")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @描述: 根据图片返回图片在服务器的路径
	 * @作者: JayLee
	 * @日期:2015-7-18
	 * @修改内容
	 * @return Json
	 * @throws
	 */
	@RequestMapping(value = "/getImgurlbyfile", method = RequestMethod.POST)
	public void getImgurlbyfile(HttpServletResponse response,
			HttpServletRequest request) {
		long maxSize = 3000000;// 图片的大小
		JSONObject json = new JSONObject();
		String imgtype = request.getParameter("imgtype");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile myfile = multipartRequest.getFile("imgfile");
		String filePath = null;
		if (Tools.isEmpty(imgtype)) {
			json.put("res_code", "ER1003");
			json.put("res_msg",
					BaseConfig.MESSAGE.get("publicInterface.ER1003"));
		} else {
			if (getBoolByType(myfile.getOriginalFilename().toLowerCase())) {
				if (myfile.getSize() <= maxSize) {
					if (null != myfile && !myfile.isEmpty()) {
						switch (imgtype) {// 判断上传图像类型
						case "productImg":// 商品 300 *300
							filePath = Tools.uploadpicByShow(myfile, imgtype,
									160, 50, 300, 640, 1000);
							json.put("res_code", "0");
							json.put("res_msg", "OK");
							json.put("filePath", filePath);
							break;
						case "userCommentImg":// 商品 200 *200
							filePath = Tools.uploadpicByShow(myfile, imgtype,
									200);
							json.put("res_code", "0");
							json.put("res_msg", "OK");
							json.put("filePath", filePath);
							break;
						default:
							filePath = Tools.uploadpic(myfile, imgtype);
							json.put("res_code", "0");
							json.put("res_msg", "OK");
							json.put("filePath", filePath);
							break;
						}
					} else {
						json.put("res_code", "ER1001");
						json.put("res_msg", BaseConfig.MESSAGE
								.get("publicInterface.ER1001"));
					}
				} else {
					json.put("res_code", "ER1008");
					json.put("res_msg",
							BaseConfig.MESSAGE.get("publicInterface.ER1008"));
				}
			} else {
				json.put("res_code", "ER1007");
				json.put("res_msg",
						BaseConfig.MESSAGE.get("publicInterface.ER1007"));
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
	 * @描述: 安卓版本
	 * @作者: JayLee
	 * @日期:2015-7-18
	 * @修改内容
	 * @return Json
	 * @throws
	 */
	@RequestMapping(value = "/getAndroidVersion", method = RequestMethod.GET)
	public void getAndroidVersion(HttpServletResponse response,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("res_code", "0000");
		json.put("res_msg", "OK");
		json.put("version", BaseConfig.sysProportion.getAndroidVersion());
		json.put("versionCode", BaseConfig.sysProportion.getAndroidCode());
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: 多图片上传
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author ljx
	 * @date 2015年7月28日下午5:11:34
	 */

	@RequestMapping("/multipartUpload")
	public void addUser(@RequestParam("file") CommonsMultipartFile[] files,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response, boolean isCheck) {
		JSONObject json = new JSONObject();
		List<Map<String, Object>> filePaths = new ArrayList<Map<String, Object>>();
		String filePath = "";
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				if (getBoolByType(files[i].getContentType())) {
					filePath = Tools.uploadpic(files[i], "imagess");
					mapTemp.put("res_code", 0);
					mapTemp.put("filePath", filePath);
					filePaths.add(mapTemp);
				} else {
					mapTemp.put("res_code", "ER1007");
					mapTemp.put("filePath", filePath);
					mapTemp.put("errorMessage",
							BaseConfig.MESSAGE.get("publicInterface.ER1007"));
					filePaths.add(mapTemp);
				}
			}
		}
		json.put("filePaths", filePaths);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
