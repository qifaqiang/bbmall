package com.wxsoft.framework.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionResolver implements HandlerExceptionResolver {
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.debug("==============异常开始=============");
		ex.printStackTrace();
		logger.debug("---->exception:=" + ex.toString());
		logger.debug("==============异常结束=============");
		ModelAndView mv = new ModelAndView("common/error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}
}
