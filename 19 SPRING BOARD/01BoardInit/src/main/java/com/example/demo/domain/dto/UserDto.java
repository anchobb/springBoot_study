package com.example.demo.domain.dto;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String password;
	private String email;
	private String role;

	//OAUTH2
	private String provider;
	private String providerId;
}
