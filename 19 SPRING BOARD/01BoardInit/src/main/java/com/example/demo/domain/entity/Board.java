package com.example.demo.domain.entity;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long no;
    private String username;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private Long count;
    private String dirpath;
    private String filename;
    private String filesize;
}


