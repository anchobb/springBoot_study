package com.example.demo.restcontroller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.dto.MemoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rest")
public class RestController_01 {

	@GetMapping(value = "/getText", produces = MediaType.TEXT_PLAIN_VALUE)
	public String f1() {
		log.info("GET /rest/getText...");
		return "HELLO WORLD";
	}

	@GetMapping(value = "/getJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MemoDto f2() {
		log.info("GET /rest/getJson...");
		return new MemoDto(10, "ABCD");
	}

	@GetMapping(value = "/getXml", produces = MediaType.APPLICATION_XML_VALUE)
	public MemoDto f3() {
		log.info("GET /rest/getXml...");
		return new MemoDto(10, "ABCD");
	}

	@GetMapping(value = "/getXmlList", produces = MediaType.APPLICATION_XML_VALUE)
	public List<MemoDto> f4() {
		log.info("GET /rest/getXmlList...");
		List<MemoDto> list = new ArrayList();
		for (int i = 0; i < 50; i++) {
			list.add(new MemoDto(i, "A" + i));
		}
		return list;
	}
	//{show}가 true, false에 따라 다른 값 전달
	@GetMapping(value = "/getXmlList2/{show}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<MemoDto>> f5(@PathVariable("show") boolean show) {
		log.info("GET /rest/getXmlList2...");
		if (show) {
			List<MemoDto> list = new ArrayList();
			for (int i = 0; i < 50; i++) {
				list.add(new MemoDto(i, "A" + i));
			}
			return ResponseEntity.status(HttpStatus.OK).body(list); //상태정보 포함해서 Body부분에 list 전달
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
	}

}
