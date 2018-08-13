package com.example.bookmanagementsystem.config;

import com.example.bookmanagementsystem.utils.Response;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: book-management-system
 * @description: 认证失败控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Component
public class SelfAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        Response response = new Response();
        httpServletResponse.getWriter().write(new Gson().toJson(response.failure()));
    }
}
