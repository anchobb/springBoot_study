package com.example.demo.C05GoogleAPI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

@Controller
@Slf4j
@RequestMapping("/google/mail")
public class GoogleMailAPIController {

    //App Secret : jkkdwkiknxsnndwv
    @GetMapping("/request")
    public void sendmail(String email) {
        log.info("GET /google/mail/request");

        //메일설정
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("sori4132@gmail.com");
        mailSender.setPassword("jkkdwkiknxsnndwv");

        //보안설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(props);

        //난수값생성
        String tmpPassword = (int)(Math.random() * 10000000) + "";

        //본문내용 설정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[WEB_TEST] 임시 패스워드");
        message.setText(tmpPassword);

        //발송
        mailSender.send(message);
    }
}
