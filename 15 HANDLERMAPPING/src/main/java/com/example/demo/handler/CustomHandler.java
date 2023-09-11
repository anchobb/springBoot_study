package com.example.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CustomHandler implements Controller{

	//페이지를 새로 열지않고 음악재생을 할 때 사용 가능한.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[HANDLER MAPPING]CustomHandler's handleRequest!");
		return null;
	}

	public void WOW() {
		System.out.println("[HANDLER MAPPING]CustomHandler's WOW ~");
	}

}
