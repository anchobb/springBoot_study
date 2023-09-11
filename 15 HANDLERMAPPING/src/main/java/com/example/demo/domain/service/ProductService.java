package com.example.demo.domain.service;

import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	public void addProduct(ProductDto dto, String path) {
		System.out.println("ProductService's addProduct...");
		productMapper.addProduct(dto.getProductCode(), path, dto.getProductName(), dto.getProductType());
		
	}
}
