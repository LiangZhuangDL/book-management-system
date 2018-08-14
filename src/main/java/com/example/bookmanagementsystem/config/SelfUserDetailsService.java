package com.example.bookmanagementsystem.config;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.anthentication.BasicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @program: book-management-system
 * @description: 用户认证业务类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
        * @Description: 认证用户是否能成功登录
        * @Param: [username]
        * @return: org.springframework.security.core.userdetails.UserDetails
        * @Author: Simon Zhuang
        * @Date: 2018/8/13
        **/
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        if(ObjectUtils.isEmpty(basicUser)){
            return new BasicUser();
        }else {
            return basicUser;
        }
    }
}
