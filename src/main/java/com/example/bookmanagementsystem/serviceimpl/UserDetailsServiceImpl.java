package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AddressDTO;
import com.example.bookmanagementsystem.dto.UserDetailsDTO;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.user.Address;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import com.example.bookmanagementsystem.repository.user.AddressRepository;
import com.example.bookmanagementsystem.repository.anthentication.BasicUserRepository;
import com.example.bookmanagementsystem.repository.user.UserDetailsRepository;
import com.example.bookmanagementsystem.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    private AddressRepository addressRepository;

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
                return userDetailsRepository.findUserDetailsByBasicUser(basicUser);
            }else {
                return new UserDetails();
            }
        }else {
            return new UserDetails();
        }
    }

    @Override
    public UserDetails save(UserDetailsDTO userDetailsDTO, AddressDTO addressDTO) throws ParseException {
        /** 
        * @Description: 保存用户详细信息
        * @Param: [userDetailsDTO, addressDTO] 
        * @return: com.example.bookmanagementsystem.entity.user.UserDetails 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/
        Address address = addressDTO.convert(addressDTO);
        Address result = addressRepository.save(address);
        if(ObjectUtils.isEmpty(result)){
            UserDetails userDetails = userDetailsDTO.convert(userDetailsDTO);
            userDetails.setAddress(result);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition parsePosition = new ParsePosition(0);
            Date birthday = dateFormat.parse(userDetailsDTO.getBirthday(), parsePosition);
            userDetails.setBirthday(birthday);
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            BasicUser basicUser = basicUserRepository.findBasicUserByUsername(currentUsername);
            userDetails.setBasicUser(basicUser);
            return userDetailsRepository.save(userDetails);
        }else {
            return new UserDetails();
        }
    }
}
