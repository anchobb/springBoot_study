package com.example.demo.controller;

import com.example.demo.domain.dto.MemoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

//	@ExceptionHandler(FileNotFoundException.class)
//	public String error1(Exception ex,Model model) {
//		System.out.println("GlobalExceptionHandler FileNotFoundException... ex " + ex);
//		//System.out.println("GlobalExceptionHandler FileNotFoundException... ex ");
//		model.addAttribute("ex",ex);
//		return "memo/error";
//	}
//	@ExceptionHandler(ArithmeticException.class)
//	public String error2(Exception ex,Model model) {
//		System.out.println("GlobalExceptionHandler ArithmeticException... ex " + ex);
//		System.out.println("GlobalExceptionHandler ArithmeticException... ex ");
//		model.addAttribute("ex",ex);
//		return "memo/error";
//	}
//	@ExceptionHandler(Exception.class)
//	public String error3(Exception ex,Model model) {
//		System.out.println("GlobalExceptionHandler Exception... ex " + ex);
//		System.out.println("GlobalExceptionHandler Exception... ex ");
//		model.addAttribute("ex",ex);
//		return "memo/error";
//	}


	@GetMapping("/form")
	public void get() {
		log.info("GET /memo/form...");
	}

	@PostMapping("/post")
	public String post(@Valid MemoDto dto, BindingResult bindingResult, Model model) {
		log.info("POST /memo/post... "+dto);
		log.info("POST /memo/post... isError "+bindingResult.getFieldError("id"));
		if(bindingResult.hasFieldErrors()) {
//			log.info("POST /memo/post... isError "+bindingResult.getFieldError("id").getDefaultMessage());
			for( FieldError error  : bindingResult.getFieldErrors()) {
				log.info(error.getField()+ " : " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());		
			}
			return "memo/form";
		}
			
		return "";
		
	}
	@GetMapping("/list1")
	public void list1() throws Exception{
		System.out.println("GET /memo/list1");
		throw new FileNotFoundException("파일이 없다..");
	}

	@GetMapping("/list2")
	public void list2() throws Exception{
		System.out.println("GET /memo/list2");
		throw new ArithmeticException("계산똑바로해..");

	}

	@GetMapping("/list3/{n1}/{n2}")
	public void list3(@PathVariable int n1, @PathVariable int n2) {
		System.out.println("GET /memo/list3");
		System.out.println("계산결과 : " + (n1/n2));

	}
	@GetMapping("/list4")
	public void list4() {
		System.out.println("GET /memo/list4");
		throw new NullPointerException();
	}




}




