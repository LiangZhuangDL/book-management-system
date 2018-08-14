package com.example.bookmanagementsystem.repository.user;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: book-management-system
 * @description: 用户详情JPA接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findUserDetailsByBasicUser(BasicUser basicUser);
}
