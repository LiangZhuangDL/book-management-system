package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.utils.DTOConvert;
import org.springframework.beans.BeanUtils;

/**
 * @program: book-management-system
 * @description: 用户登录DTO
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class LoginUserDTO implements DTOConvert<BasicUser, LoginUserDTO> {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUserDTO() {
    }

    @Override
    public BasicUser convert(LoginUserDTO loginUserDTO) {
        BasicUser basicUser = new BasicUser();
        BeanUtils.copyProperties(loginUserDTO, basicUser);
        return basicUser;
    }
}
