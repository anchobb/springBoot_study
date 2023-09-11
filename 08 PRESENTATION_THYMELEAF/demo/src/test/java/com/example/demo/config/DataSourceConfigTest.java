package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DataSourceConfigTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void test1() throws SQLException {
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        connection.prepareStatement("create table tbl_test (id int primary key, name varchar(45))").executeUpdate();

    }

    @Test
    void test2() throws SQLException {
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        connection.prepareStatement("insert into tbl_memo values (2,'hoho')").executeUpdate();
    }
}