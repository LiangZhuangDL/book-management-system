package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String isLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
