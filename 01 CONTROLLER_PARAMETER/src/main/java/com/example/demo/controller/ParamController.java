package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/test")
public class ParamController {
	
//	@GetMapping("/param01")
	@RequestMapping(value="/param01" ,method = RequestMethod.GET)
	public void p1(@RequestParam(required = true) String name) {
		log.info("GET /test/param01.." + name);
	}
	@GetMapping("/param02")
	public void p2(@RequestParam(required = false)String name) {
		log.info("GET /test/param02.." + name);
	}
	@GetMapping("/param03")
	public void p3(@RequestParam(required = false,defaultValue = "홍길동")String name) {
		log.info("GET /test/param03.." + name);
	}
	@GetMapping("/param04")
	public void p4(String name,int age,String addr) {
		log.info("GET /test/param04.." + name+" "+age+" "+addr);
	}
	@GetMapping("/param05")
	public void p5(Person person) {
		log.info("GET /test/param05.." + person);
	}	
	@GetMapping("/param06/{name}/{age}/{addr}")
	public void p6(Person person) {
		log.info("GET /test/param06.." + person);
	}	
	@GetMapping("/param07")
	public void p7(Person person,Model model) {
		log.info("GET /test/param07.." + person);
		log.info("Model : " + model);
		model.addAttribute("name",person.getName());
		model.addAttribute("age",person.getAge());
		model.addAttribute("addr",person.getAddr());
	}	
	@GetMapping("/param08")
	public void p8(Person person,Model model) {
		log.info("GET /test/param08.." + person);
		log.info("Model : " + model);
		model.addAttribute("person",person);	 
	}	
	
	@GetMapping("/param09/{name}/{age}/{addr}")
	public String p9(Person person,Model model) {
		log.info("GET /test/param09.." + person);
		model.addAttribute("person",person);
		return "test/param09";
	}	
	@GetMapping("/param10/{name}/{age}/{addr}")
	public ModelAndView p10(Person person) {
		log.info("GET /test/param10.." + person);
		//ModelAndView 
		ModelAndView modelAndView = new ModelAndView();
		//속성추가
		modelAndView.addObject("person",person);
		//뷰경로 추가
		modelAndView.setViewName("test/sample");
		
		return modelAndView;
	}
	
//	Servlet 방식
	@GetMapping("/servlet_test")
	public void servlet_test(HttpServletRequest req,HttpServletResponse resp)
	{
		System.out.println("req : " + req);
		System.out.println("req : " + req.getParameter("id"));
		System.out.println("session : " + req.getSession());
		System.out.println("context : " + req.getSession().getServletContext());
		System.out.println("response : "+resp);
	}
	
//	
	@GetMapping("/form")
	public void form() {
		log.info("GET /test/form..");
	}
	@PostMapping("/post01")
	public void post_01(@RequestParam("id") int no,String text, String writer) {
		log.info("POST /test/post01.."+no+" "+text+" "+writer);

	}
	
}


