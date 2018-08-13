package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.data.domain.Page;

/**
 * @program: book-management-system
 * @description: 用户基本信息业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface BasicUserService {

    Boolean removeUserById(Long id);

    Boolean activeUser(String username);

    Page<BasicUser> getAllUsers(Integer page, Integer size);

    BasicUser findBasicUserByUsername(String username);
}
