package com.example.demo.domain.repository;


import com.example.demo.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{

    @Query(value = "SELECT * FROM bookdb.board ORDER BY no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Board> findBoardAmountStart(@Param("amount") int amount, @Param("offset") int offset);


    // Type , Keyword 로 필터링된 count 계산 -> 페이징처리를 위해서
    @Query("SELECT COUNT(b) FROM Board b WHERE b.title LIKE %:keyWord%")
    Integer countWhereTitleKeyword(@Param("keyWord")String keyWord);

    @Query("SELECT COUNT(b) FROM Board b WHERE b.username LIKE %:keyWord%")
    Integer countWhereUsernameKeyword(@Param("keyWord")String keyWord);

    @Query("SELECT COUNT(b) FROM Board b WHERE b.content LIKE %:keyWord%")
    Integer countWhereContentKeyword(@Param("keyWord")String keyWord);

    @Query(value = "SELECT * FROM bookdb.board b WHERE b.title LIKE %:keyWord%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Board> findBoardTitleAmountStart(@Param("keyWord")String keyword, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM bookdb.board b WHERE b.username LIKE %:keyWord%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Board> findBoardUsernameAmountStart(@Param("keyWord")String keyword, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM bookdb.board b WHERE b.content LIKE %:keyWord%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Board> findBoardContentsAmountStart(@Param("keyWord")String keyword, @Param("amount") int amount,@Param("offset") int offset);


}
