package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: book-management-system
 * @description: 用户基本信息业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface BasicUserService {

    @Transactional
    Boolean removeUserById(Long id);

    @Transactional
    Boolean activeUser(String username);

    @Transactional
    Page<BasicUser> getAllUsers(Integer page, Integer size);

    @Transactional
    BasicUser findBasicUserByUsername(String username);
}
