package com.wxsoft.framework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadImg {
	public static void main(String args[]) {
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		String path = "D:/image/";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
		path += name + ".jpg";
		download(
				"http://mmbiz.qpic.cn/mmbiz/mzM9QdibXM5taAuvYV0lq3prmvm4xIiaJotN2iaMOLPIoj4cKhgOItIOb5jPzHpVpVBvDPDjuCqDdKia5eib8aybcTA/0",
				path);
	}

	public static void download(String strUrl, String path) {
		System.out.println(strUrl);
		System.out.println(path);
		URL url = null;
		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
			return;
		}

		InputStream is = null;
		try {
			is = url.openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}