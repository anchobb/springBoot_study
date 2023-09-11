package com.example.demo.controller;

import com.example.demo.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@InitBinder //페이지에서 파라미터를 받기 전에 하는 작업!
	public void dataBinder(WebDataBinder dataBinder) {
		System.out.println("MemberController's dataBinder.." + dataBinder);
		//String("2022-01-01") -> LocalDate로 변환하는 Editor
		dataBinder.registerCustomEditor(LocalDate.class, "birthday", new MemberDtoEditor());
		//String[] 의 구분자를 # 추가. ~~~PropertyEditor 찾아보기!
		dataBinder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
	}
	
	@GetMapping("/join")
	public void get_memo() {
		log.info("GET /member/join");
	}
	
	@PostMapping("/join")
	public String post_memo(@Valid @ModelAttribute MemberDto dto, BindingResult bindingResult, Model model) {
		log.info("POST /member/join" + dto);
		if(bindingResult.hasFieldErrors()) {
			
			for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
                model.addAttribute(error.getField(),error.getDefaultMessage());
            }
			return "member/join";
		}
		
		return "";
	}
}

class MemberDtoEditor extends PropertyEditorSupport{//Dto에서 전달되는 속성을 지정된 양식에 맞게 변환할 수 있음!
	//String -> Object
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("MemberDtoEditor's setAsText : " + text); //맵핑 전에 바인더가 관여하는 부분!
		setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		//파라미터로 전달하기 전에 바인더가 text로 받아서 LocalDate 타입에 맞게 파싱해서 보내주는 과정
	} 
	
}