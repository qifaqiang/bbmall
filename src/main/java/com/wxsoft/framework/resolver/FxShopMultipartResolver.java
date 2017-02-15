/**   
 * @文件名称: FxShopMultipartResolver.java
 * @类路径: com.wxsoft.framework.resolver
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-21 下午06:40:24  
 */

package com.wxsoft.framework.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wxsoft.framework.config.SystemConfig;

/**
 * @类功能说明：重写springmvc上传文件
 * @类修改者：kasiait
 * @修改日期：2013-3-21
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-21 下午06:40:24
 */

public class FxShopMultipartResolver extends CommonsMultipartResolver {
	/**
	 * 
	 * @描述: 重写springmvc默认的文件上传处理函数，根据url匹配排除掉我们自己的富文本上传工具的场景，其余还是有springmvc的默认上传处理
	 * @作者: kasiaits
	 * @日期:2013-3-21
	 * @修改内容
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	@Override
	public boolean isMultipart(HttpServletRequest request) 
	{
		/**
		 * 如果url符合我们自定义上传规则，则不由springmvc处理
		 */
		if(request.getRequestURI().contains(SystemConfig.FXSHOP_FRAMEWORK_FILEUPLOAD_FILTER))
			return false;
		
		return  super.isMultipart(request);
	}
}
