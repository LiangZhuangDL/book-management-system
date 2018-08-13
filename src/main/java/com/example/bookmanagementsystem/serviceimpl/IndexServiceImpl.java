package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.IndexService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 首页业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public Map<String, Object> getCurrentUserInfo() {
        /** 
        * @Description: 获取当前登录用户 
        * @Param: [] 
        * @return: java.util.Map<java.lang.String,java.lang.Object> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Map<String, Object> map = new HashMap<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        map.put("authorities", authorities);
        map.put("username", username);
        return map;
    }
}
