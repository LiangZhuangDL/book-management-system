package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.AddressDTO;
import com.example.bookmanagementsystem.dto.UserDetailsDTO;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import com.example.bookmanagementsystem.service.UserDetailsService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 用户控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping(value = "/profile")
    public Response profile(){
        /** 
        * @Description: 获取当前登录用户的用户详情 
        * @Param: [] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/
        Response response = new Response();
        UserDetails userDetails = userDetailsService.getCurrentUserDetails();
        if(!ObjectUtils.isEmpty(userDetails)){
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            return response.success(map);
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            return response.success(map);
        }
    }

    @PostMapping(value = "/save")
    public Response saveUserDetails(UserDetailsDTO userDetailsDTO, AddressDTO addressDTO){
        UserDetails userDetails = userDetailsService.save(userDetailsDTO, addressDTO);
        Response response = new Response();
        if(!ObjectUtils.isEmpty(userDetails)){
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            return response.success(map);
        }else {
            return response.failure();
        }
    }
}
