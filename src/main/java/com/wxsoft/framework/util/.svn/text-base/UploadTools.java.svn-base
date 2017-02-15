package com.wxsoft.framework.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wxsoft.framework.config.BaseConfig;

public class UploadTools {

	/*
	 * 上传处理方法
	 */
	public static void processUpload(HttpServletRequest request,
			HttpServletResponse response) {

		String savePath = generateDir();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 缓存大小为512KB
		factory.setSizeThreshold(524288);
		// 临时文件夹
		factory.setRepository(new File(savePath + "/temp"));
		// 初始化上传控件
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 文件大小最大3MB
		upload.setFileSizeMax(3145728);
		upload.setHeaderEncoding("UTF-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String filename = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				if (name != null && !name.trim().equals("")) {
					filename = generateFileName(name);
					File file = new File(savePath + "/" + filename);
					try {
						item.write(file);
						String imgstr = request.getContextPath();
						imgstr = imgstr + "/upload/" + filename;
						response.getWriter().write("http://localhost" + imgstr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/*
	 * 生成文件名
	 */
	private static String generateFileName(String name) {
		long currentTime = System.currentTimeMillis();
		int i = (int) (Math.random() * 1000D + 1.0D);
		long result = currentTime + i;
		String filename = String.valueOf(result) + getFileExt(name);
		return filename;
	}

	/*
	 * 获取文件格式
	 */
	private static String getFileExt(String name) {
		int pos = name.lastIndexOf(".");
		if (pos > 0) {
			return name.substring(pos);
		} else {
			return name;
		}
	}

	/*
	 * 初始化文件存储路径
	 */
	public static String generateDir() {
		String pathString = BaseConfig.getString("SYS_DOC_BIG_ROOTPATH")
				+ "/upload";
		String tempString = BaseConfig.getString("SYS_DOC_BIG_ROOTPATH")
				+ "/upload/temp";
		File dirPath = new File(pathString);
		File dirTemp = new File(tempString);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		if (!dirTemp.exists()) {
			dirTemp.mkdirs();
		}
		return pathString;
	}
}
