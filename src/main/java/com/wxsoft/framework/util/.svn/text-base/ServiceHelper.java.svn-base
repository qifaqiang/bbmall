package com.wxsoft.framework.util;

import com.wxsoft.framework.config.SystemDef;
import com.wxsoft.xyd.system.service.RegionService;


/**
 * @author kasiaits@gmail.com
 * 返回类型为声明的接口类型
 * 如果是实现类类型则会报caseException异常
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return SystemDef.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static RegionService getRegionService(){
		return (RegionService) SystemDef.WEB_APP_CONTEXT.getBean("regionService");
	}
}
