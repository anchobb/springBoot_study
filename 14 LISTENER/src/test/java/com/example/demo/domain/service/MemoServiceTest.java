package com.example.demo.domain.service;


import com.example.demo.domain.dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoServiceTest {

    @Autowired
    private MemoService memoService;

    @Test
    void test() {
        memoService.addMemo(new MemoDto(2,"WOWOW"));
    }

}