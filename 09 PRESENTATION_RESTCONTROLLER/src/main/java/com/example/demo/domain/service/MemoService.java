package com.example.demo.domain.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.Memo;
import com.example.demo.domain.mapper.MemoMapper;
import com.example.demo.domain.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

//Service등록 2개. Bean으로 등록되어있는지 확인(root-context에서)
@Service
@Slf4j
public class MemoService {
	
	@Autowired //Service와 mapper연결을 위한 의존주입
	private MemoRepository memoRepository;

	//전체메모가져오기
	public List<Memo> getAllMemo(){
		log.info("MemoService's getAllMemo Call!");
		return memoRepository.findAll();
	}
	
	//메모작성
	@Transactional(rollbackFor = SQLException.class)
	public void addMemo(MemoDto dto) {
		log.info("MemoService's addMemo Call!");
		Memo memo = new Memo();
		memo.setId(dto.getId());
		memo.setText(dto.getText());
		memoRepository.save(memo);
		
	}

	//메모수정
	@Transactional(rollbackFor = Exception.class)
	public void modifyMemo(MemoDto dto) {
		log.info("MemoService's modifyMemo Call!");
		Memo memo = new Memo();
		memo.setId(dto.getId());
		memo.setText(dto.getText());
		memoRepository.save(memo);
	}

	//메모삭제
	@Transactional(rollbackFor = Exception.class)
	public void removeMemo(Long id) {
		log.info("MemoService's removeMemo Call!");
		memoRepository.deleteById(id);
	}
	
}
