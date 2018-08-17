package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.AddressDTO;
import com.example.bookmanagementsystem.dto.UserDetailsDTO;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

/**
 * @program: book-management-system
 * @description: 用户详情业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface UserDetailsService {

    @Transactional
    UserDetails getCurrentUserDetails();

    @Transactional
    UserDetails save(UserDetailsDTO userDetailsDTO, AddressDTO addressDTO) throws ParseException;
}
