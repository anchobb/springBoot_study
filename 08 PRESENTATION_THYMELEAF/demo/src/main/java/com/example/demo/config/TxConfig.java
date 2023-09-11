package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class TxConfig {

    @Autowired
    private HikariDataSource dataSource2;
    @Bean
    public DataSourceTransactionManager transactionManager() {
        System.out.println("Tx dataSource2 :" + dataSource2.toString());
        return new DataSourceTransactionManager(dataSource2);
    }
}
