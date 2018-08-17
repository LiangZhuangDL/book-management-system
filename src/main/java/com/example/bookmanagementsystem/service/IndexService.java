package com.example.bookmanagementsystem.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @program: book-management-system
 * @description: 首页业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface IndexService {

    @Transactional
    Map<String, Object> getCurrentUserInfo();
}
