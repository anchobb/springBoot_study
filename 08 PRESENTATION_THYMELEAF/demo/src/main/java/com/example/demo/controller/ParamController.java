package com.example.demo.controller;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.dto.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//파라미터 받기~

@Controller
@Slf4j
@RequestMapping("/test") //http://localhost:8080/app/test/ (테스트는 폴더!!)
public class ParamController {
	@GetMapping("/param01")  //파라미터를 전달받지 않으면 맵핑이 안됨(required=true)
	public void p1(@RequestParam(required = true) String name) { 
		log.info("GET /test/param01.."+name);
	}
	
	@GetMapping("/param02") //파라미터가 없어도 맵핑 됨 (기본값 false)
	public void p2(@RequestParam(required = false) String name) {
		log.info("GET /test/param02.."+name);
	}

	@GetMapping("/param03") //defaultValue는 기본 파라미터값을 지정해주는 것
	public void p3(@RequestParam(required = false,defaultValue = "홍길동") String name) {
		log.info("GET /test/param03.."+name);
	}

	@GetMapping("/param04") //여러개의 파라미터 전달
	public void p4(String name, int age, String addr) {
		log.info("GET /test/param04.."+name+" "+age+" "+addr);
	}
	
	@GetMapping("/param05") //Object형태의 파라미터도 전달 가능! (뒤에 파라미터 적어주어야 함. 안적으면 null값 들어감)
	public void p5(Person person) {
		log.info("GET /test/param05.."+person);
	}
	
	@GetMapping("/param06/{name}/{age}/{addr}") //파라미터 전달방식 바꾸기(~~/param06/홍길동/55/대구)
	public void p6(Person person) {
		log.info("GET /test/param06.."+person);
	}
	
	//view로 연결하는 작업
//	@GetMapping("/param07/{name}/{age}/{addr}") //url경로가 view파일과 같을 경우에는 void로 두어도 맵핑 가능함
//	public String p7(Person person) {
//		log.info("GET /test/param07.."+person);
//		return "test/param07";	//메서드 형식을 String으로 바꾸고 return에 jsp파일 경로 적기(jsp파일이 url경로와 다른 이름을 가진 파일일 경우에!)
//	}

	@GetMapping("/param07") //파라미터를 view로 전달 (각각 파라미터 전달)
	public void p7(Person person, Model model) {
		log.info("GET /test/param07.."+person);
		log.info("Model : " + model);
		model.addAttribute("name",person.getName());
		model.addAttribute("age",person.getAge());
		model.addAttribute("addr",person.getAddr());
	}

	@GetMapping("/param08") //파라미터를 view로 전달(object전체로 전달)
	public void p8(Person person, Model model) {
		log.info("GET /test/param08.."+person);
		log.info("Model : " + model);
		model.addAttribute("person",person);
	}
	
	@GetMapping("/param09/{name}/{age}/{addr}")
	public String p9(Person person, Model model) {
		log.info("GET /test/param09.."+person);
		model.addAttribute("person",person);
		return "test/param09"; //중괄호로 파라미터를 받을 때 경로 잡아주기
	}
	
	@GetMapping("/param10/{name}/{age}/{addr}")
	public ModelAndView p10(Person person) { //ModelAndView객체로 파라미터 받기 (자료형을 ModelAndView로)
		log.info("GET /test/param10.."+person);
		//ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//속성추가
		modelAndView.addObject("person",person);
		//뷰경로 추가
		modelAndView.setViewName("test/sample"); 
		
		return modelAndView; 
	}
	
	//Servlet 방식으로 받아보기
	@GetMapping("/servlet_test")
	public void servlet_test(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("req : " + req);
		System.out.println("req : " + req.getParameter("id"));
		System.out.println("context : " + req.getSession().getServletContext());
		System.out.println("resp : " + resp);
	}
	
	@GetMapping("/form")
	public void form() {
		log.info("GET /test/form.."); //1번 순서! -> 뷰 form.jsp(2번 순서)
	}
//	@PostMapping("/post01")
//	public void post_01(int id, String text, String writer) {
//		log.info("POST /test/post01.." + id +" "+text+" "+writer);
//	}
	@PostMapping("/post01")
	public void post_01(@RequestParam("id") int no, String text, String writer) { //jsp파일의 요청파라미터(id)와 받는 파라미터의 이름(no)이 다를 경우 RequestParam으로 맵핑!)
		log.info("POST /test/post01.." + no +" "+text+" "+writer);
	}
	
	@GetMapping("/form2")
	public void form2() {
		log.info("GET /test/form.."); //1번 순서! -> 뷰 form.jsp로 이동(2번 순서)
	}
	@PostMapping("/post02") //2번에서 받은 파라미터를 3번 순서(여기)로 전달
	public void post_02(@ModelAttribute("memo") MemoDto dto) {
		log.info("POST /test/post02.." +dto);
	}
	
}
