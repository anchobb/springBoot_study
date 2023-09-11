package com.example.demo.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@MapperScan("com.example.demo.domain.mapper")
@EnableAspectJAutoProxy //AOP autoproxy추가!
public class RootConfig {
}
