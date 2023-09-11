package com.example.demo.C03KakaoAPI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//사전 어플리케이션 등록 필요


@Controller
@Slf4j
@RequestMapping("/th/kakao")
public class C01KakaoMapController {

    @GetMapping("/map")
    public void map() {

    }
}
