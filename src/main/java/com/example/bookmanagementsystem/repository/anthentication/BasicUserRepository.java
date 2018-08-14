package com.example.bookmanagementsystem.repository.anthentication;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: book-management-system
 * @description: 用户基本信息JPA接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    BasicUser findBasicUserByUsername(String username);

    BasicUser findBasicUserByActiveCode(String activeCode);

    BasicUser findBasicUserById(Long id);

    BasicUser findBasicUserByUsernameAndIsDelete(String username, Boolean delete);
}
