package com.example.bookmanagementsystem.config;

import com.example.bookmanagementsystem.utils.Response;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 注销成功控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Component
public class SelfLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Response response = new Response();
        Map<String,Object> map = new HashMap<>();
        map.put("status", "注销失败");
        httpServletResponse.getWriter().write(new Gson().toJson(response.success(map)));
    }
}
