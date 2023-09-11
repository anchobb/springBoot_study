package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MemoInterceptor implements HandlerInterceptor{

	//MemoController로 보내기 전에 처리하는 로직
	//return false라면 MemoController로 이동 X 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("[INTERCEPTOR] MemoInterceptor's preHandle...");
		return true;
	}

	//MemoController의 작업이 끝난 이후 처리하는 로직
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("[INTERCEPTOR] MemoInterceptor's postHandle...");
	}

	//View Page 랜더링 된 이후 처리되는 로직
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[INTERCEPTOR] MemoInterceptor's afterConpletion...");
	}
	
	
}
