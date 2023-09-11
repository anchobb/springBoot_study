package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    //JPA 함수 Naming 부여(https://velog.io/@hongseoda/%EB%82%B4%EB%B0%B0%EC%BA%A0TIL614DATA-JPA-%EB%A9%94%EC%86%8C%EB%93%9C-%EC%9D%B4%EB%A6%84%EC%9C%BC%EB%A1%9C-%ED%95%A8%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0)
    List<Book> findByBookname(String bookName);
    List<Book> findByPublisher(String bookPublisher);
    List<Book> findByIsbn(String isbn);

    List<Book> findByBooknameAndPublisher(String bookName,String publisher);

    //포함문자열 검색
    List<Book> findByBooknameContaining(String bookKeyword);

    List<Book> findByPublisherStartingWith(String prefix);

    //책이름이 동일한 행의 개수
    int countByBookname(String bookName);
    //책이름이 특정 문자열을 포함하는 행의 개수
    int countByBooknameContaining(String bookName);

    //책이름으로 삭제
    void deleteByBookname(String bookName);
}



//명명규칙 참고
    // 07 JPA 명명규칙 확인 ------------------------------
//    // 책 이름으로 책 검색
//    List<Book> findByBookname(String bookname);
//    // 출판사로 책 검색
//    List<Book> findByPublisher(String publisher);
//    // ISBN으로 책 검색
//    Book findByIsbn(String isbn);
//    // 책 코드 범위로 책 검색
//    List<Book> findByBookcodeBetween(long start, long end);
//
//    // 책 이름이나 출판사로 책 검색
//    List<Book> findByBooknameOrPublisher(String bookname, String publisher);
//
//    // 책 이름이나 출판사로 책 검색, 정렬하여 가져오기
//    List<Book> findByBooknameOrPublisherOrderByBooknameAsc(String bookname, String publisher);
//
//    // 책 이름에 특정 문자열이 포함된 책 검색
//    List<Book> findByBooknameContaining(String keyword);
//
//    // 출판사가 특정 문자열로 시작하는 책 검색
//    List<Book> findByPublisherStartingWith(String prefix);
//
//    // 책 이름으로 책 개수 가져오기
//    int countByBookname(String bookname);
//
//    // 출판사로 책 개수 가져오기
//    int countByPublisher(String publisher);
//
//    // 책 이름으로 책 삭제
//    void deleteByBookname(String bookname);
// 07 JPA 명명규칙 확인  끝-----------------------------
