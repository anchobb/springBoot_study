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

    public static String READ_BOARD_DIR_PATH; //파일 경로를 저장해서 어디에서나 쓸 수 있도록 하기 위해서 static으로,,

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
    public void read(Long no, Model model){
        log.info("GET /board/read no : " + no); //list에서 title을 클릭했을 때 해당 글의 no를 받아와야 함.

        //서비스 실행
        Board board =  boardService.getBoardOne(no);

        BoardDto dto = new BoardDto();
        dto.setNo(board.getNo());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setRegdate(board.getRegdate());
        dto.setUsername(board.getUsername());

        System.out.println("FILENAMES : " + board.getFilename());
        System.out.println("FILESIZES : " + board.getFilesize());



        //파일 다운로드 부분 코드 - post에서 파일 저장시 toString으로 저장했기 때문에 추가 작업이 많당
        String filenames[] = null;
        String filesizes[] = null;
        if(board.getFilename()!=null){
            //첫문자열에 [ 제거
            filenames = board.getFilename().split(","); //[a.txt, b.txt, c.txt, d.txt] ,기준으로 자르기
            filenames[0] = filenames[0].substring(1, filenames[0].length());
            //마지막 문자열에 ] 제거
            int lastIdx = filenames.length-1;
            System.out.println("filenames[lastIdx] : " + filenames[lastIdx].substring(0,filenames[lastIdx].lastIndexOf("]")));
            filenames[lastIdx] = filenames[lastIdx].substring(0,filenames[lastIdx].lastIndexOf("]"));

            model.addAttribute("filenames", filenames);
        }
        if(board.getFilesize()!=null){
            //첫문자열에 [ 제거
            filesizes = board.getFilesize().split(",");
            filesizes[0] = filesizes[0].substring(1, filesizes[0].length());
            //마지막 문자열에 ] 제거
            int lastIdx = filesizes.length-1;
            System.out.println("filesizes[lastIdx] : " + filesizes[lastIdx].substring(0,filesizes[lastIdx].lastIndexOf("]")));
            filesizes[lastIdx] = filesizes[lastIdx].substring(0,filesizes[lastIdx].lastIndexOf("]"));



            model.addAttribute("filesizes", filesizes);
        }

        if(board.getDirpath()!=null)
            READ_BOARD_DIR_PATH = board.getDirpath();


        model.addAttribute("boardDto",dto);

    }


    //-------------------
    //-------------------
    @GetMapping("/update")
    public void update(Long no, Model model){
        log.info("GET /board/update");

        //서비스 실행
        Board board =  boardService.getBoardOne(no);

        BoardDto dto = new BoardDto();
        dto.setNo(board.getNo());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setRegdate(board.getRegdate());
        dto.setUsername(board.getUsername());
        dto.setCount(board.getCount());

        System.out.println("FILENAMES : " + board.getFilename());
        System.out.println("FILESIZES : " + board.getFilesize());



        //파일 다운로드 부분 코드 - post에서 파일 저장시 toString으로 저장했기 때문에 추가 작업이 많당
        String filenames[] = null;
        String filesizes[] = null;
        if(board.getFilename()!=null){
            //첫문자열에 [ 제거
            filenames = board.getFilename().split(","); //[a.txt, b.txt, c.txt, d.txt] ,기준으로 자르기
            filenames[0] = filenames[0].substring(1, filenames[0].length());
            //마지막 문자열에 ] 제거
            int lastIdx = filenames.length-1;
            System.out.println("filenames[lastIdx] : " + filenames[lastIdx].substring(0,filenames[lastIdx].lastIndexOf("]")));
            filenames[lastIdx] = filenames[lastIdx].substring(0,filenames[lastIdx].lastIndexOf("]"));

            model.addAttribute("filenames", filenames);
        }
        if(board.getFilesize()!=null){
            //첫문자열에 [ 제거
            filesizes = board.getFilesize().split(",");
            filesizes[0] = filesizes[0].substring(1, filesizes[0].length());
            //마지막 문자열에 ] 제거
            int lastIdx = filesizes.length-1;
            System.out.println("filesizes[lastIdx] : " + filesizes[lastIdx].substring(0,filesizes[lastIdx].lastIndexOf("]")));
            filesizes[lastIdx] = filesizes[lastIdx].substring(0,filesizes[lastIdx].lastIndexOf("]"));



            model.addAttribute("filesizes", filesizes);
        }
        if(board.getDirpath()!=null)
            READ_BOARD_DIR_PATH = board.getDirpath();


        model.addAttribute("boardDto",dto);

    }

    @PostMapping("/update")
    public String update_post(@Valid BoardDto dto, BindingResult bindingResult,Model model) throws IOException {
        //유효성 검사
        if(bindingResult.hasFieldErrors()) {
            for( FieldError error  : bindingResult.getFieldErrors()) {
                log.info(error.getField()+ " : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "redirect:/board/update?no="+dto.getNo();
        }
        //서비스 실행
        boolean isUpdate = boardService.updateBoard(dto);

        if(isUpdate) {
            return "redirect:/board/read?no="+dto.getNo();
        }
        return "redirect:/board/update?no="+dto.getNo();

    }


}
