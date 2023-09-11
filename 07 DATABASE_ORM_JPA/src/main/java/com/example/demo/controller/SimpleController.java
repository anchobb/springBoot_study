package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SimpleController {

    @GetMapping("/")
    public String home(){
        System.out.println("GET /index");

        return "index";
    }

    @PostMapping("/login")
    public void login_post(){

    }


}
