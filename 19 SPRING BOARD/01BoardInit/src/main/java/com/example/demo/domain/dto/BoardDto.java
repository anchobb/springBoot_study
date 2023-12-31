package com.example.demo.domain.dto;


import com.example.demo.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long no;
    @NotBlank(message = "username을 입력하세요")
    @Email(message = "올바른 이메일 주소를 입력하세요")
    private String username;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private Long count;
    private MultipartFile[] files;



    public static BoardDto Of(Board board) {
        BoardDto dto = new BoardDto();
        dto.no = board.getNo();
        dto.title=board.getTitle();
        dto.content = board.getContent();
        dto.regdate = board.getRegdate();
        dto.count = board.getCount();
        dto.username  = board.getUsername();
        return dto;
    }
}
