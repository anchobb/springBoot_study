package com.example.demo.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER  1:n  RENTAL
    @ManyToOne
    @JoinColumn(name = "username",foreignKey = @ForeignKey(name="FK_RENTAL_USER",
    foreignKeyDefinition="FOREIGN KEY(username) REFERENCES USER(username) ON DELETE CASCADE ON UPDATE CASCADE"))
    private User user;

    // BOOK  1:1  RENTAL
    @OneToOne
    @JoinColumn(name = "bookcode",foreignKey = @ForeignKey(name="FK_RENTAL_BOOK",
            foreignKeyDefinition="FOREIGN KEY(bookcode) REFERENCES BOOK(bookcode) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Book book;

    private LocalDate regDate;

    private LocalDate returnDate;
}
