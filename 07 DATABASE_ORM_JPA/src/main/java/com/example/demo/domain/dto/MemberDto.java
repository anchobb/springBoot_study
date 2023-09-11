package com.example.demo.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
	private String userid;
	private String password;
	private String name;
	private String phone;
	private String address;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate  birthday;
	private String [] hobbys;
	private String [] hobbys2;
}
