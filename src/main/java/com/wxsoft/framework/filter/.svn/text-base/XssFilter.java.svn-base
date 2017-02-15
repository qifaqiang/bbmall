package com.wxsoft.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * xss过滤
 * 
 * @author kyz 在输出时用jstl的fn:excapeXml("fff")方法
 * @创建时间：2013-7-5 上午10:00:58
 */
public class XssFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// HttpServletRequest httpRequest = (HttpServletRequest) request;
		// if (httpRequest.getRequestURI().contains("login.html")) {
		chain.doFilter(request, response);
		// } else {
		// chain.doFilter(new XssHttpServletRequestWrapper(
		// (HttpServletRequest) request), response);
		// }

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
