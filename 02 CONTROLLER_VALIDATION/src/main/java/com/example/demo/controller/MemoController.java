package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.domain.dto.MemoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	
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
}




