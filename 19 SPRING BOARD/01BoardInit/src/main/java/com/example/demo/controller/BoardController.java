package com.example.demo.controller;


import com.example.demo.domain.dto.BoardDto;
import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.PageDto;
import com.example.demo.domain.entity.Board;
import com.example.demo.domain.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    //-------------------
    //-------------------
    @GetMapping("/list")
    public String list(Integer pageNo, Model model){
        log.info("GET /board/list");
        //----------------
        //PageDto  Start
        //----------------
        Criteria criteria = null;
        if(pageNo==null) {
            //최초 /board/list 접근
            criteria = new Criteria();  //pageno=1 , amount=10
            pageNo=1;
        }
        else {
            criteria = new Criteria(pageNo,10); //페이지이동 요청 했을때
        }

        //서비스 실행
        Map<String,Object> map = boardService.GetBoardList(criteria);

        PageDto pageDto = (PageDto) map.get("pageDto");
        List<Board> list = (List<Board>) map.get("list");


        //Entity -> Dto
        List<BoardDto>  boardList =  list.stream().map(board -> BoardDto.Of(board)).collect(Collectors.toList());
        System.out.println(boardList);

        //View 전달
        model.addAttribute("boardList",boardList);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("pageDto",pageDto);

        return "board/list";

    }



    //-------------------
    //-------------------
    @GetMapping("/post")
    public void post_get(){log.info("GET /board/post");}

    @PostMapping("/post")
    public String post_post(@Valid BoardDto dto, BindingResult bindingResult,Model model) throws IOException {
        log.info("POST /board/post dto : " + dto);

        //유효성 검사
        if(bindingResult.hasFieldErrors()) {
            for( FieldError error  : bindingResult.getFieldErrors()) {
                log.info(error.getField()+ " : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "/board/post";
        }
        //서비스 실행
        boolean isadded = boardService.addBoard(dto);

        return "redirect:/board/list";

    }

    //-------------------
    //-------------------
    @GetMapping("/read")
    public void read(){
        log.info("GET /board/read");
    }


    //-------------------
    //-------------------
    @GetMapping("/update")
    public void update(){log.info("GET /board/update");}

}
