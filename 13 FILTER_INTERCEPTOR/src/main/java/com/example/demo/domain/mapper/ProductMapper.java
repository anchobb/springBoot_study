package com.example.demo.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

	@Insert("insert into tbl_product values (#{productCode},#{path},#{productName},#{productType})")
	public int addProduct(
			@Param("productCode")	int productCode,
			@Param("path")			String path,
			@Param("productName")	String productName,
			@Param("productType")	String productType
			);
	
}
