package com.wxsoft.framework.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.util.TestProperties;
import com.wxsoft.framework.util.unionpay.SDKConfig;

public class BaseInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		ServletContext application = this.getServletContext();
		InputStream inputStream2 = TestProperties.class
				.getResourceAsStream("/config/domain.properties");
		InputStream messageStream = TestProperties.class
				.getResourceAsStream("/config/message.properties");
		Properties p = new Properties();
		Properties message = new Properties();
		try {
			p.load(inputStream2);
			message.load(messageStream);
			messageStream.close();
			inputStream2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BaseConfig.MESSAGE = message;
		BaseConfig.FX_DOMAIN_WWW = p.getProperty("domain");
		BaseConfig.FX_DOMAIN_IMAGE8888 = p.getProperty("imgdomain8888");
		BaseConfig.FX_DOMAIN_CDN = p.getProperty("cdn");
		BaseConfig.FX_PIC_PATH = p.getProperty("pic_path");
		BaseConfig.FX_FTL_PATH = p.getProperty("ftl_path");

		application.setAttribute("USERIMGSRC", BaseConfig.FX_DOMAIN_IMAGE8888);
		application.setAttribute("SHOPDOMAIN", BaseConfig.FX_DOMAIN_WWW);
		application.setAttribute("PIC", BaseConfig.FX_PIC_PATH);
		application.setAttribute("CDN", BaseConfig.FX_DOMAIN_CDN);
		application.setAttribute("SYSPROPORTION", BaseConfig.sysProportion);
		System.out.println("ip:" + p.getProperty("domain") + " port:"
				+ p.getProperty("imgdomain"));

		SDKConfig.getConfig().loadPropertiesFromSrc();
	}
}