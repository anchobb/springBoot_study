package com.example.demo.domain;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void SaveRepo(){
        //System.out.println(bookRepository);
        assertThat(bookRepository).isNotNull();    //NULL이 아니면 테스트통과
        Book book = Book.builder()
<<<<<<< HEAD
                .bookcode(1118L)
                .bookname("JAVA의정석")
=======
                .bookCode(1118L)
                .bookName("JAVA의정석")
>>>>>>> 6edaf29eb6988522981de1c75565a8b72b40c3aa
                .publisher("00미디어")
                .isbn("1111-2222")
                .build();
        assertThat(book).isNotNull();

        //INSERT
        Book result =  bookRepository.save(book);
        assertThat(result).isEqualTo(book);

    }

    @Test
    public void UpdateRepo(){
        Book book = Book.builder()
<<<<<<< HEAD
                .bookcode(1117L)
=======
                .bookCode(1117L)
>>>>>>> 6edaf29eb6988522981de1c75565a8b72b40c3aa
                .bookName("코드로 배우는 스프링부트")
                .publisher("와우미디어")
                .isbn("1111-2222")
                .build();
        bookRepository.save(book);
    }
    @Test
    public void DeleteRepo(){
         bookRepository.deleteById(1117L);
    }

    @Test
    public void SelectRepo(){
        List<Book> list =  bookRepository.findAll();
        list.forEach(dto->{
            System.out.println(dto);
        });
    }

    @Test
    public void SelectOneRepo(){
        Optional<Book> result =  bookRepository.findById(3L);
        Book book =  result.get();
        System.out.println(book);
    }

    // 함수추가해서 확인

    @Test
    public void SelectByBookName(){
          List<Book> list =  bookRepository.findByBookName("aa");
          list.forEach(dto->{
              System.out.println(dto);
          });
    }

    @Test
    public void SelectByWheres(){
        List<Book> list =  bookRepository.findByBookNameAndPublisher("aa","c");
        list.forEach(dto->{
            System.out.println(dto);
        });

    }

    @Test
    public void IsContaining(){
        List<Book> list =  bookRepository.findByBookNameContaining("a");
        list.forEach(dto->{
            System.out.println(dto);
        });
    }







}