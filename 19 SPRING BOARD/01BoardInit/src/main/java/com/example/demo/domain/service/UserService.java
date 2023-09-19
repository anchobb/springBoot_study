package com.example.demo.domain.service;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean joinMember(UserDto dto, Model model){


        if(!dto.getPassword().equals(dto.getRepassword())){
            model.addAttribute("repassword","패스워드가 일치하지 않습니다.");
            return false;
        }
        dto.setRole("ROLE_USER");
        dto.setPassword( passwordEncoder.encode(dto.getPassword()) );
        return true;
    }
}
