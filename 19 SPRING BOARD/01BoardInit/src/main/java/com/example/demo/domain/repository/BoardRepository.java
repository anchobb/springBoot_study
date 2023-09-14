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
}
