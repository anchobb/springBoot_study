package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/th")
public class SimpleController {

    @GetMapping("/test1")
    public void f1(){
        System.out.println("GET /test1");
    }

    @GetMapping("/test2")
    public void f2(Model model){
        System.out.println("GET /test2");
        model.addAttribute("name", "hong");
        model.addAttribute("memo", new MemoDto(1010,"TESTMEMO"));
        model.addAttribute("isMember", false);

        List<MemoDto> list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add(new MemoDto(i, "TESTMEMO"+i));
        }
        model.addAttribute("memoList", list);
        model.addAttribute("pageNo", 1234);
    }
}
