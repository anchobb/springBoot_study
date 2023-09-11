package com.example.demo.domain.service;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Service등록 2개. Bean으로 등록되어있는지 확인(root-context에서)
@Service
@Slf4j
public class MemoService {

	@Autowired
	private MemoMapper mapper;


	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo Call!");
		return mapper.selectAll();
	}


	public void addMemo(MemoDto dto) {
		log.info("MemoService's addMemo Call!");
		mapper.insert(dto);

	}

	@Transactional(rollbackFor = Exception.class)
	public void addMemoTx(MemoDto dto) {
		log.info("MemoService's addMemoTx Call!");
		int id = dto.getId();
		mapper.insert(dto);
		dto.setId(id);
		mapper.insert(dto);
	}
	
}
