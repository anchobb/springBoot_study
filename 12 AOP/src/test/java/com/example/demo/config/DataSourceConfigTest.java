package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DataSourcConfigTest {
        @Autowired
        private DataSource dataSource;


        @Test
        void test1() throws SQLException {
                System.out.println(dataSource);
                Connection connection = dataSource.getConnection();
                connection.prepareStatement("create table tbl_test(id int primary key,name varchar(45) )").executeUpdate();
        }

        @Test
        void test2() throws SQLException {
                System.out.println(dataSource);
                Connection connection = dataSource.getConnection();
                connection.prepareStatement("insert into tbl_test values(2,'haha')").executeUpdate();

        }

}