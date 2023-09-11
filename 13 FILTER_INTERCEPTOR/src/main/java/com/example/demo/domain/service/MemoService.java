package com.example.demo.domain.service;

import java.util.List;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

//Service등록 2개. Bean으로 등록되어있는지 확인(root-context에서)
@Service
@Slf4j
public class MemoService {
	
	@Autowired //Service와 mapper연결을 위한 의존주입
	private MemoMapper mapper;

	//전체메모가져오기
	public List<MemoDto> getAllMemo(){
		//log.info("MemoService's getAllMemo Call!");
		return mapper.selectAll();
	}
	//메모작성

	public void addMemo(MemoDto dto) {
		//log.info("MemoService's addMemo Call!");
		mapper.insert(dto);
		
	}
	
	@Transactional(rollbackFor = Exception.class) //service에서는 무조건 transaction 붙여주는 게 좋다!
	public void addMemoTx(MemoDto dto) {
		//log.info("MemoService's addMemoTx Call!");
		int id = dto.getId();
		mapper.insert(dto); //01 INSERT
		dto.setId(id); //PK오류 발생 예정
		mapper.insert(dto); //02 PK오류!
	}

	//메모수정
	@Transactional(rollbackFor = Exception.class)
	public void modifyMemo(MemoDto dto) {
		//log.info("MemoService's modifyMemo Call!");
		mapper.update(dto);
	}

	//메모삭제
	@Transactional(rollbackFor = Exception.class)
	public void removeMemo(int id) {
		//log.info("MemoService's removeMemo Call!");
		mapper.delete(id);
	}
	
}
