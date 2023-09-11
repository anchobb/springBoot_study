package com.example.demo.restcontroller;

import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestController_03Download {
	@GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE) //다운로드형태의 기본타입 - OCTET~~
	public ResponseEntity<Resource> Download(String path) throws UnsupportedEncodingException {
		log.info("RestController_03Download's Download Call.. " + path);
		//FileSystemResource : 파일시스템의 특정 파일로부터 정보를 가져오는 데 사용
		Resource resource = new FileSystemResource(path);
		//파일명 추출
		String filename = resource.getFilename();
		//헤더 정보 추가
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
		//리소스, 파일정보가 포함된 헤더, 상태정보를 전달(∧)
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}
}
