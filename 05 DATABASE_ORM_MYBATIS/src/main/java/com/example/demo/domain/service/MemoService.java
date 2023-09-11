package com.example.demo.domain.service;



import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemoService {
	
	@Autowired
	private MemoMapper mapper;
	
	//전체메모가져오기
	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo Call! ");
		return  mapper.selectAll(); 
	}
	//메모작성
	public int addMemo(MemoDto dto) {
		log.info("MemoService's addMemo Call! ");
		return mapper.insert(dto);
	}	
	
		
	//메모수정
	//메모삭제



}
