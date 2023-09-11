package com.example.demo.controller;

import com.example.demo.domain.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/simple")
public class SimpleController {
	
	@Autowired
	private SimpleService simpleService;
	
	@GetMapping("/get1")
	public void f1() {
		System.out.println("GET /simple/get1..");
		simpleService.get1();
	}
	@GetMapping("/get2")
	public void f2() {
		System.out.println("GET /simple/get2..");
		simpleService.get2();
	}
	@GetMapping("/get3")
	public void f3() {
		System.out.println("GET /simple/get3..");
		simpleService.get3();
	}
}
