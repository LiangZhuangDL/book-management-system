package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.enums.UserEnum;
import com.example.bookmanagementsystem.enums.UserTypeEnum;
import com.example.bookmanagementsystem.service.RegisterService;
import com.example.bookmanagementsystem.vo.IndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class AuthenticationController {

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/")
    public IndexVO root(){
        Map<String, Object> map = registerService.getCurrentUserInfo();
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

}
