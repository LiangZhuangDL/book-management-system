package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.utils.DTOConvert;
import org.springframework.beans.BeanUtils;

public class RegisterUserDTO implements DTOConvert<BasicUser, RegisterUserDTO> {

    private String username;

    private String password;

    private String repeatPassword;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public RegisterUserDTO(String username, String password, String repeatPassword, String email) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    public RegisterUserDTO() {
    }

    @Override
    public BasicUser convert(RegisterUserDTO registerUserDTO) {

        BasicUser basicUser = new BasicUser();
        BeanUtils.copyProperties(registerUserDTO, basicUser);
        return basicUser;
    }
}
