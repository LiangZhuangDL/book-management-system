package com.example.bookmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @program: book-management-system
 * @description: 启动类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@SpringBootApplication
@EnableJpaAuditing
public class BookManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManagementSystemApplication.class, args);
    }
}
