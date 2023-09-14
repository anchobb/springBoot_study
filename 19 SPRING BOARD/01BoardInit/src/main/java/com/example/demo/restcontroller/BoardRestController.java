package com.example.demo.restcontroller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardRestController {

    //-------------------
    // 수정하기
    //-------------------
    @PutMapping("/put")
    public void put(){log.info("PUT /board/put");}
    //-------------------
    // 삭제하기
    //-------------------
    @DeleteMapping("/delete")
    public void delete(){log.info("DELETE /board/delete");}
    
}
