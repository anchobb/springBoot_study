package com.example.demo.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class TxConfig {

    @Autowired
    private HikariDataSource dataSource2;
    @Bean
    public DataSourceTransactionManager transactionManager() {
        //System.out.println("TX dataSrouce2 : " + dataSource2.toString());
        return new DataSourceTransactionManager(dataSource2);
    }
}
