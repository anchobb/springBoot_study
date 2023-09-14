package com.example.demo.domain.service;


import com.example.demo.domain.dto.BoardDto;
import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.PageDto;
import com.example.demo.domain.entity.Board;
import com.example.demo.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BoardService {

    private String uploadDir = "c:\\upload";

    @Autowired
    private BoardRepository boardRepository;


    @Transactional(rollbackFor = SQLException.class)
    public Map<String,Object> GetBoardList(Criteria criteria) {

        Map<String,Object> returns = new HashMap<String,Object>();


        //전체게시물 건수 받기
        int totalcount = (int)boardRepository.count();
        System.out.println("COUNT  :" + totalcount);

        //PageDto 만들기
        PageDto pagedto = new PageDto(totalcount,criteria);

        //시작 게시물 번호 구하기(수정) - OFFSET
        int offset =(criteria.getPageno()-1) * criteria.getAmount();    //1page = 0, 2page = 10

        List<Board> list  =  boardRepository.findBoardAmountStart(criteria.getAmount(),offset);

        returns.put("list",list);
        returns.put("pageDto",pagedto);

        return returns;

    }

    @Transactional(rollbackFor = SQLException.class)
    public boolean addBoard(BoardDto dto) throws IOException {

        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setRegdate(LocalDateTime.now());
        board.setUsername(dto.getUsername());
        board.setCount(0L);

        MultipartFile [] files = dto.getFiles();
//        System.out.println("FILES's LENGTH : " +files.length);
        System.out.println("FILES's SIZE : "  + files[0].getSize());
        List<String> filenames = new ArrayList<String>();
        List<String> filesizes = new ArrayList<String>();


        //파일업로드
        if(dto.getFiles().length >= 1 && dto.getFiles()[0].getSize()!=0L)
         {
            //Upload Dir 미존재시 생성
            String path = uploadDir+ File.separator+dto.getUsername()+File.separator+ UUID.randomUUID();
            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            for(MultipartFile file  : dto.getFiles())
            {
                System.out.println("--------------------");
                System.out.println("FILE NAME : " + file.getOriginalFilename());
                System.out.println("FILE SIZE : " + file.getSize() + " Byte");
                System.out.println("--------------------");

                //파일명 추출
                String filename = file.getOriginalFilename();
                //파일객체 생성
                File fileobj = new File(path,filename);
                //업로드
                file.transferTo(fileobj);
                //filenames 저장
                filenames.add(filename);
                filesizes.add(file.getSize()+"");
            }

            board.setDirpath(path);
        }

        board.setFilename(filenames.toString());
        board.setFilesize(filesizes.toString());

        board =    boardRepository.save(board);

        boolean issaved =  boardRepository.existsById(board.getNo());
        return issaved;
    }
}
