package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoMapperTest {

    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void test() {
        System.out.println(memoMapper);
        memoMapper.insert(new MemoDto(1,"aaaaa"));
    }

}