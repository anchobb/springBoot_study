package com.example.demo.domain.repository;

import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void f1(){
        userRepository.save(new User("aaa","123","ROLE_USER"));
        userRepository.save(new User("aab","1234","ROLE_USER"));
        userRepository.save(new User("aac","12345","ROLE_MEMBER"));
        userRepository.save(new User("aad","123456","ROLE_ADMIN"));
        userRepository.save(new User("ccd","123456","ROLE_MEMBER"));
        userRepository.save(new User("bbd","123456","ROLE_ADMIN"));
    }
    @Test
    public void f2(){
        List<User> list =  userRepository.abc("ROLE_ADMIN");
        list.forEach(   user->System.out.println(user) );
    }


}