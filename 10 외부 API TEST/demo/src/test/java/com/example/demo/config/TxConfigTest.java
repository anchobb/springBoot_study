package com.example.demo.config;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@SpringBootTest
class TxConfigTest {
    @Autowired
    private HikariDataSource dataSource2;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void txTest1() {
        System.out.println("Tx Manager : " + transactionManager);
        TransactionStatus transcationStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            memoMapper.insert(new MemoDto(12,"TX_TEST01"));
            memoMapper.insert(new MemoDto(12,"TX_TEST01"));
        }catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(transcationStatus);
        }
    }

}