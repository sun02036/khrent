package com.kh.semi.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

  
	/**
	 * FilterChain에서 실행순서
	 * 1. web.xml에 선언된 순서
	 * 2. @WebFilter annotation을 사용하는 경우. 필터이름순서로 처리
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		// servlet 가기전!
		// 사용자입력값 encoding처리
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8 encoding처리!]");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		// servlet 다녀온후!
		
	}

}
