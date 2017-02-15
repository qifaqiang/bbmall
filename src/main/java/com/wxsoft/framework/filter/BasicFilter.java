package com.wxsoft.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class BasicFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
	//  HttpServletRequest request = (HttpServletRequest) servletRequest;
	//  HttpSession session = request.getSession();

	// 指定允许其他域名访问  
	  response.addHeader("Access-Control-Allow-Origin", "*");
	// 响应类型  响应方法
	  response.addHeader("Access-Control-Allow-Methods", "POST, OPTIONS"); 
	    // 响应头设置 
	  response.addHeader("Access-Control-Allow-Headers", "POWERED-BY-FANTONG");
	  response.addHeader("Access-Control-Max-Age", "30");   
	  
	  // 需要过滤的代码
	    chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
