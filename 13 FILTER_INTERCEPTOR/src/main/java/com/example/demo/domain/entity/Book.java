package com.example.demo.domain.entity;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
//@Table(name="tbl_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length=100)
    private Long bookcode;
    @Column(length=255)
    private String bookname;
    @Column(length=255)
    private String publisher;
    @Column()
    private String isbn;
}
