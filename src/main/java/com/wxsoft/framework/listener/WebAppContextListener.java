package com.wxsoft.framework.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemDef;

/**
 * 
 * @类功能说明：spring初始化自定义
 * @类修改者：kasiait
 * @修改日期：2013-3-26
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-26 下午04:59:06
 */
public class WebAppContextListener implements ServletContextListener {

	

	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 框架销毁处理代码
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	/**
	 * 初始化系统配置文件
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		SystemDef.WEB_APP_CONTEXT = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());

		JdbcTemplate jdbcTemplate = (JdbcTemplate) SystemDef.WEB_APP_CONTEXT
				.getBean("jdbcTemplate");
		BaseConfig.initCache(jdbcTemplate);
		// List<BaseConfigBean> configList = BaseConfig.getBaseConfigList();
		// for (BaseConfigBean bean : configList) {
		// logger.debug("key=[" + bean.getKey() + "]  ====> value=["
		// + bean.getValue() + "]");
		// }

		logger.debug("======== Get Spring WebApplicationContext.... =======");
	}

}
