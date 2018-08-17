package com.example.bookmanagementsystem.service;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: book-management-system
 * @description: 注册业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface RegisterService {

    @Transactional
    String isLogin();

    @Transactional
    Boolean save(BasicUser basicUser);

    @Transactional
    Boolean update(BasicUser basicUser);

    @Transactional
    Boolean activeUser(String activeCode);
}
