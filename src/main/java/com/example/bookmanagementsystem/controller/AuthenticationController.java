package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.RegisterUserDTO;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.enums.UserEnum;
import com.example.bookmanagementsystem.enums.UserTypeEnum;
import com.example.bookmanagementsystem.service.IndexService;
import com.example.bookmanagementsystem.service.RegisterService;
import com.example.bookmanagementsystem.vo.IndexVO;
import com.example.bookmanagementsystem.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class AuthenticationController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/")
    public IndexVO root(){
        Map<String, Object> map = indexService.getCurrentUserInfo();
        String username = (String) map.get("username");
        String authority = (String)map.get("authority");
        if(username.equals("anonymousUser") && !authority.equals("ADMIN")){
            return new IndexVO(UserEnum.ANONYMOUSUSER.getText(), UserTypeEnum.USER.getText());
        }else if(!username.equals("anonymousUser") && authority.equals("ADMIN")){
            return new IndexVO(username, UserTypeEnum.ADMIN.getText());
        }else if(!username.equals("anonymousUser") && !authority.equals("ADMIN")){
            return new IndexVO(username, UserTypeEnum.USER.getText());
        }else{
            return new IndexVO(username, UserTypeEnum.USER.getText());
        }
    }

    @GetMapping(value = "/register")
    public RegisterVO register(){
        String username = registerService.isLogin();
        if(username.equals("anonymousUser")){
            return new RegisterVO("register", false, "进入注册页面");
        }else{
            return new RegisterVO("index", true, "已登录");
        }
    }

    @PostMapping(value = "/register-user")
    public RegisterVO registerUser(RegisterUserDTO registerUserDTO){
        String password = registerUserDTO.getPassword();
        String repeatPassword = registerUserDTO.getRepeatPassword();
        if(password.equals(repeatPassword)){
            registerUserDTO.setPassword(new BCryptPasswordEncoder().encode(password));
            BasicUser user = registerUserDTO.convert(registerUserDTO);
            Boolean tag = registerService.save(user);
            if(tag){
                return new RegisterVO("index", true, "注册成功");
            }else{
                return new RegisterVO("register", false, "注册失败，请重新注册");
            }
        }else{
            return new RegisterVO("register", false, "两次密码不相同，请重新输入");
        }
    }

}
