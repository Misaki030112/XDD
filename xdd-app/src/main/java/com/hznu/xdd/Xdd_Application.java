package com.hznu.xdd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.hznu.xdd.dao")
public class Xdd_Application {
    public static void main(String[] args) {
        SpringApplication.run(Xdd_Application.class, args);
    }
}
