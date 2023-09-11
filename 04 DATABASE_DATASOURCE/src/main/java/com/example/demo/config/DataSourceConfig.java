//package com.example.demo.config;
//
//import javax.sql.DataSource;
//import javax.swing.*;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//
//@Configuration
//@ComponentScan
//public class DataSourceConfig {
//
//	// Spring-jdbc DataSource
//	@Bean
//	public DataSource dataSource()
//	{
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/bookdb");
//		dataSource.setUsername("root");
//		dataSource.setPassword("1234");
//
//		return dataSource;
//	}
//
//	//	HikariCP DataSource
//	@Bean
//	public DataSource dataSource2()
//	{
//		return 	DataSourceBuilder.create()
//				.type(HikariDataSource.class)
//				.username("root")
//				.password("1234")
//				.driverClassName("com.mysql.cj.jdbc.Driver")
//				.url("jdbc:mysql://localhost:3306/bookdb")
//				.build();
//	}
//
//}
