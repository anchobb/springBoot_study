package com.example.demo.controller;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Properties;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public void login_get(){

	}

	@GetMapping("/join")
	public void join_get() {
		log.info("GET /join");
	}

	@PostMapping("/join")
	public String join_post(@Valid UserDto dto, BindingResult bindingResult, Model model, HttpServletRequest request) {
		log.info("POST /join "+dto);

		//01

		//02
		if(bindingResult.hasFieldErrors()) {
			for( FieldError error  : bindingResult.getFieldErrors()) {
				log.info(error.getField()+ " : " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}
			return "/user/join";
		}

		//03


		boolean isjoin = userService.joinMember(dto, model, request);
		if(!isjoin) {
			return "/user/join";
		}

		//04
		return "redirect:login?msg=Join_Success!";

	}

	//메일인증
	@GetMapping(value="/auth/email/{username}")
	public @ResponseBody void email_auth(@PathVariable String username, HttpServletRequest request){ //매핑방지를 위해서 responsebody를 자료형 앞에 쓰기
		log.info("GET /user/auth/email.." + username);

		//메일설정
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("sori4132@gmail.com");
		mailSender.setPassword("rmvo tkru ixcu rhwx");

		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		mailSender.setJavaMailProperties(props);

		//난수값생성
		String tmpPassword = (int)(Math.random()*10000000)+"";

		//본문내용 설정
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(username);
		message.setSubject("[WEB_TEST] 이메일 코드 발송");
		message.setText(tmpPassword);

		//발송
		mailSender.send(message);

		//생성된 난수값을 어디에 저장할 것인가
		//ApplicationContext , Session, Cookie, DB, Request,, 등등 -- Session이 가장 좋지 않을까~
		//세션에 Code 저장
		HttpSession session = request.getSession();
		session.setAttribute("email_auth_code", tmpPassword); //서버에서 tmpPassword저장해둠

	}

	@GetMapping("/auth/confirm/{code}")
	public @ResponseBody String email_auth_confirm(@PathVariable String code, HttpServletRequest request){
		System.out.println("GET /user/auth/confirm "+code);
		HttpSession session = request.getSession();
		String auth_code = (String)session.getAttribute("email_auth_code");
		if(auth_code != null){
			if(auth_code.equals(code)){
				session.setAttribute("is_email_auth", true);
				return "success";
			}else{
				session.setAttribute("is_email_auth", false);
				return "fail";
			}
		}

		return "failed";
	}


}





