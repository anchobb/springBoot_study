package com.example.demo.exception;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(FileNotFoundException.class) //FileNotFoundException 예외만 처리해줌
	public String error1(Exception ex, Model model) {
		System.out.println("GlobalExceptionHandler FileNotFoundException.. ex " + ex);
		System.out.println("GlobalExceptionHandler FileNotFoundException.. ex ");
		model.addAttribute("ex",ex);
		return "memo/error";
	}
	
	@ExceptionHandler(ArithmeticException.class) //ArithmeticException 예외만 처리
	public String error2(Exception ex, Model model) {
		System.out.println("GlobalExceptionHandler ArithmeticException.. ex " + ex);
		System.out.println("GlobalExceptionHandler ArithmeticException.. ex ");
		model.addAttribute("ex",ex);
		return "memo/error";
	}
	
	@ExceptionHandler(Exception.class) //기타등등 예외 처리
	public String error3(Exception ex, Model model) {
		System.out.println("GlobalExceptionHandler Exception.. ex " + ex);
		System.out.println("GlobalExceptionHandler Exception.. ex ");
		model.addAttribute("ex",ex);
		return "memo/error";
	}
	
}
