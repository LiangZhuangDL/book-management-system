package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.RegisterUserDTO;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.service.IndexService;
import com.example.bookmanagementsystem.service.RegisterService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthenticationController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/")
    public Response root(){
        Map<String, Object> map = indexService.getCurrentUserInfo();
        Response response = new Response();
        String username = (String) map.get("username");
        String authority = (String) map.get("authority");
        if(authority.equals("ADMIN") && username.equals("anonymousUser")){
            return response.failure();
        }else{
            return response.success(map);
        }
    }

    @GetMapping(value = "/register")
    public Response register(){
        String username = registerService.isLogin();
        Response response = new Response();
        Map<String, Object> map = new HashMap<>();
        if(username.equals("anonymousUser")){
            map.put("username", username);
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/register-user")
    public Response registerUser(RegisterUserDTO registerUserDTO){
        String password = registerUserDTO.getPassword();
        String repeatPassword = registerUserDTO.getRepeatPassword();
        Response response = new Response();
        if(password.equals(repeatPassword)){
            registerUserDTO.setPassword(new BCryptPasswordEncoder().encode(password));
            BasicUser user = registerUserDTO.convert(registerUserDTO);
            Boolean tag = registerService.save(user);
            if(tag){
                String message = "注册成功";
                return response.success(message);
            }else{
                return response.failure();
            }
        }else{
            return response.failure();
        }
    }

}
