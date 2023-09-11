package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController2 {
    @GetMapping("/test4")
    public void f4(){
        System.out.println("GET /test4");
    }
}
