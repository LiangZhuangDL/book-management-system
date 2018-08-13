package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.RegisterUserDTO;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.service.IndexService;
import com.example.bookmanagementsystem.service.LoginService;
import com.example.bookmanagementsystem.service.MailService;
import com.example.bookmanagementsystem.service.RegisterService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 验证控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@RestController
public class AuthenticationController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private MailService mailService;

    @GetMapping(value = "/")
    public ModelAndView root(){
        /** 
        * @Description: 跳转Index页 
        * @Param: [] 
        * @return: org.springframework.web.servlet.ModelAndView 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return new ModelAndView("redirect:/index");
    }

    @GetMapping(value = "/index")
    public Response index(){
        /** 
        * @Description: 返回首页信息 
        * @Param: [] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Map<String, Object> map = indexService.getCurrentUserInfo();
        Response response = new Response();
        return response.success(map);
    }

    @GetMapping(value = "/register")
    public Response register(){
        /** 
        * @Description: 跳转注册页 
        * @Param: [] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
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
        /** 
        * @Description: 注册用户 
        * @Param: [registerUserDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        String password = registerUserDTO.getPassword();
        String repeatPassword = registerUserDTO.getRepeatPassword();
        Response response = new Response();
        if(password.equals(repeatPassword)){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String username = registerUserDTO.getUsername();
            registerUserDTO.setPassword(bCryptPasswordEncoder.encode(password));
            BasicUser user = registerUserDTO.convert(registerUserDTO);
            String activeCode = bCryptPasswordEncoder.encode(username + password);
            user.setActiveCode(activeCode);
            Boolean tag = registerService.save(user);
            if(tag){
                String message = "注册成功";
                String to = registerUserDTO.getEmail();
                String content = mailService.buildContent(activeCode);
                Boolean success = mailService.sendHtmlMail(to, "您好,请激活您的账号", content);
                if(success){
                    return response.success(message);
                }else {
                    return response.failure();
                }
            }else{
                return response.failure();
            }
        }else{
            return response.failure();
        }
    }

    @GetMapping(value = "/login")
    public Response login(){
       /** 
       * @Description: 跳转登录页 
       * @Param: [] 
       * @return: com.example.bookmanagementsystem.utils.Response 
       * @Author: Simon Zhuang
       * @Date: 2018/8/13 
       **/ 
        String username = loginService.isLogin();
        Response response = new Response();
        Map<String, Object> map = new HashMap<>();
        if(username.equals("anonymousUser")){
            map.put("username", username);
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @GetMapping(value = "/active/active-code")
    public Response activeUser(@RequestParam("activeCode") String activeCode){
        /** 
        * @Description: 激活账号 
        * @Param: [activeCode] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean tag = registerService.activeUser(activeCode);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "账号激活成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

}
