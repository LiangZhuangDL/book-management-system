package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AddressDTO;
import com.example.bookmanagementsystem.dto.UserDetailsDTO;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.repository.UserDetailsRepository;
import com.example.bookmanagementsystem.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @program: book-management-system
 * @description: 用户详情业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 15:50
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public UserDetails getCurrentUserDetails() {
        /**
        * @Description: 返回当前登录用户的详细信息
        * @Param: []
        * @return: com.example.bookmanagementsystem.entity.user.UserDetails
        * @Author: Simon Zhuang
        * @Date: 2018/8/13
        **/
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!username.equals("anonymousUser")){
            BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
            if(!ObjectUtils.isEmpty(basicUser)){
                UserDetails userDetails = userDetailsRepository.findUserDetailsByBasicUser(basicUser);
                return userDetails;
            }else {
                return new UserDetails();
            }
        }else {
            return new UserDetails();
        }
    }

    @Override
    public UserDetails save(UserDetailsDTO userDetailsDTO, AddressDTO addressDTO) {
        /** 
        * @Description: 保存用户详细信息
        * @Param: [userDetailsDTO, addressDTO] 
        * @return: com.example.bookmanagementsystem.entity.user.UserDetails 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return null;
    }
}
