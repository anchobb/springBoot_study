package com.example.demo.controller;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	@ExceptionHandler(Exception.class)
	public void MemoExceptionHandler(Exception ex){

	}


	@Autowired //controller와 service연결을 위한 의존주입
	private MemoService service;

	@GetMapping("/showMemo")
	public void f1(Model model){
		log.info("GET /memo/showMemo");
		List<Memo> list = service.getAllMemo();
		list.forEach((memo)->{System.out.println(memo);});
		model.addAttribute("list",list);
	};

	@PostMapping("/addMemo")
	public void f2(MemoDto dto){
		log.info("POST /memo/addMemo.." + dto);
		service.addMemo(dto);
		log.info("POST /memo/addMemo..nextid : " + dto.getId());
	};
	
	
}





