package com.example.bookmanagementsystem.service;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;

/**
 * @program: book-management-system
 * @description: 注册业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface RegisterService {
    String isLogin();

    Boolean save(BasicUser basicUser);

    Boolean update(BasicUser basicUser);

    Boolean activeUser(String activeCode);
}
