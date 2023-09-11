package com.example.demo.restcontroller;

import java.util.List;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/memo")
public class RestController_02Memo {
	
	@Autowired
	private MemoService memoService;
	
	//메모확인전체
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> getAll() {
		log.info("GET /memo/getAll...");
		return memoService.getAllMemo();
		
	}
	//메모확인단건
	@GetMapping("/get/{id}") //매핑주소 내의 {}이름과 변수의 이름이 같아야 함! PathVariable로 맵핑. 다를 경우 PathVariable("")로 지정해야함!
	public void get(@PathVariable int id) {
		log.info("GET /memo/get... " + id);
		
	}
	//메모쓰기
	@PostMapping("/add")	//http://localhost:8080/app/memo/add
	public void add(@RequestBody MemoDto dto) { //restful 방식에서 post방식으로 값을 받을 때 RequestBody 애노테이션 사용
		log.info("POST /memo/add..." + dto);
		memoService.addMemo(dto);
	}
	//메모수정
	@PutMapping("/put/{id}/{text}")
	public void put(MemoDto dto) {
		log.info("PUT /memo/put..." + dto);	
		
	}

	@PutMapping("/put2")	//http://localhost:8080/app/memo/put2
	public void put2(@RequestBody MemoDto dto) {
		log.info("PUT /memo/put2..." + dto);	
		memoService.modifyMemo(dto);
	}
	
	@PatchMapping("/patch/{id}/{text}")
	public void patch(MemoDto dto) {
		log.info("PATCH /memo/patch..." + dto);	
	}
	
	//메모삭제
	@DeleteMapping("/remove/{id}")	//http://localhost:8080/app/memo/remove/{id}
	public void remove(@PathVariable int id) {
		log.info("DELETE /memo/remove..." + id);
		memoService.removeMemo(id);
	}
	
}
