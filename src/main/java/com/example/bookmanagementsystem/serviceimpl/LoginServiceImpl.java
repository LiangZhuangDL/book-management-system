package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @program: book-management-system
 * @description: 登录业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String isLogin() {
        /** 
        * @Description: 判断是否登录 
        * @Param: [] 
        * @return: java.lang.String 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
