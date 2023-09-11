package com.example.demo.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private int productCode;		//제품코드
	private MultipartFile[] files;	//이미지파일
	private String productName;		//제품이름
	private String productType;		//제품종류
}
