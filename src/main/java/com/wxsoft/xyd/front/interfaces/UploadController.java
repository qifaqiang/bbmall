package com.wxsoft.xyd.front.interfaces;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.bean.Result;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.controller.BaseController;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.system.service.UserService;

/**
 * 图片上传控制器
 * @author kyz
 *
 */
@Controller
@RequestMapping("/file")
public class UploadController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/upload")
	public void addUser(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<String> filePaths = new ArrayList<String>();
		String filePath = "";
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				filePath = Tools.uploadpic(files[i], "imagess");
				filePaths.add(filePath);
				json.put("result", 2);
			}
		}
		json.put("filePaths", filePaths);
		try {
			responseAjax(json, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Constructor of the object.
	 */
	@RequestMapping("/updatetouxiangPc")
	public void updatetouxiangPc(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		JSONObject json = new JSONObject();
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();

			Result result = new Result();
			result.avatarUrls = new ArrayList();
			result.success = false;
			result.msg = "Failure!";
			// 定义一个变量用以储存当前头像的序号
			int avatarNumber = 1;
			// 取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
			String fileName = simpleDateFormat.format(new Date());
			Random random = new Random();
			String randomCode = "";
			for (int i = 0; i < 8; i++) {
				randomCode += Integer.toString(random.nextInt(36), 36);
			}
			fileName = fileName + randomCode;
			// 基于原图的初始化参数
			String initParams = "";
			BufferedInputStream inputStream;
			BufferedOutputStream outputStream;
			int i = 0;
			while (iter.hasNext()) {
				if (i == 0) {
					MultipartFile file = multiRequest.getFile(iter.next());

					String fieldName = file.getOriginalFilename();
					System.out.println(fieldName + "---------------------");
					// 是否是原始图片 file 域的名称（默认的 file
					// 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）
					Boolean isSourcePic = fieldName.equals("__source");
					// 当前头像基于原图的初始化参数（只有上传原图时才会发送该数据，且发送的方式为POST），用于修改头像时保证界面的视图跟保存头像时一致，提升用户体验度。
					// 修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
					if (fieldName.equals("__initParams")) {
						inputStream = new BufferedInputStream(file.getInputStream());
						byte[] bytes = new byte[inputStream.available()];
						inputStream.read(bytes);
						initParams = new String(bytes, "UTF-8");
						inputStream.close();
					}
					// 如果是原始图片 file
					// 域的名称或者以默认的头像域名称的部分“__avatar”打头(默认的头像域名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names)
					else if (isSourcePic || fieldName.startsWith("__avatar")) {
						String virtualPath = "/attached/userPic/jsp_avatar" + avatarNumber + "_" + fileName + ".jpg";
						// 原始图片（默认的 file
						// 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）。
						if (isSourcePic) {
							// 文件名，如果是本地或网络图片为原始文件名、如果是摄像头拍照则为 *FromWebcam.jpg
							String sourceFileName = file.getOriginalFilename();
							// 原始文件的扩展名(不包含“.”)
							String sourceExtendName = sourceFileName.substring(sourceFileName.lastIndexOf('.') + 1);
							result.sourceUrl = virtualPath = String.format("/attached/userPic/jsp_source_%s.%s", fileName, sourceExtendName);
						}
						// 头像图片（默认的 file
						// 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names）。
						else {
							result.avatarUrls.add(virtualPath);
							avatarNumber++;
						}
						String filePath = BaseConfig.FX_PIC_PATH;
						//filePath = filePath.substring(0, filePath.length() - 1);
						String virtualPaths = virtualPath.substring(0, virtualPath.lastIndexOf("/"));
						//virtualPaths.replace("/", "\\");
						filePath += virtualPaths;
						File filel = new File(filePath);
						if (!filel.exists()) {
							filel.mkdirs();
						}
						inputStream = new BufferedInputStream(file.getInputStream());
						outputStream = new BufferedOutputStream(new FileOutputStream(BaseConfig.FX_PIC_PATH + virtualPath));
						Streams.copy(inputStream, outputStream, true);
						inputStream.close();
						outputStream.flush();
						outputStream.close();
					} else {
						// 注释①
						// upload_url中传递的查询参数，如果定义的method为post请使用下面的代码，否则请删除或注释下面的代码块并使用注释②的代码
						inputStream = new BufferedInputStream(file.getInputStream());
						byte[] bytes = new byte[inputStream.available()];
						inputStream.read(bytes);
						inputStream.close();
						if (fieldName.equals("userid")) {
							result.userid = new String(bytes, "UTF-8");
						} else if (fieldName.equals("username")) {
							result.username = new String(bytes, "UTF-8");
						}
					}
					i++;
				} else {
					break;
				}
			}

			if (result.sourceUrl != null) {
				result.sourceUrl += initParams;
			}

			/*
			 * To Do...可在此处处理储存事项
			 */
			// 返回图片的保存结果（返回内容为json字符串，可自行构造，该处使用fastjson构造）
			User su = (User) session.getAttribute(SystemConfig.SESSION_FRONT_USER);
			if (su != null) {
				if (result.avatarUrls.size() != 0) {
					StringBuffer sb = new StringBuffer();
					sb.append(result.avatarUrls.get(0));
					String s = sb.toString();
					su.setPicUrl(s);
					if (userService.updateByPrimaryKeySelective(su) > 0) {
						json.put("res_code", "0");
						json.put("success", true);
						session.setAttribute(SystemConfig.SESSION_FRONT_USER, su);
					} else {
						json.put("res_code", "user.ER1025");
					}
				} else {
					json.put("res_code", "user.ER1025");
				}
			} else {
				json.put("res_code", "1");
			}
			try {
				responseAjax(json, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
