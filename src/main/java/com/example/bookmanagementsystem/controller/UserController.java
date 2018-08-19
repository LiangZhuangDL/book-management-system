package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.AddressDTO;
import com.example.bookmanagementsystem.dto.UserDetailsDTO;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import com.example.bookmanagementsystem.enums.DefaultAvatarEnum;
import com.example.bookmanagementsystem.service.DefaultFileService;
import com.example.bookmanagementsystem.service.UserDetailsService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 用户控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DefaultFileService defaultFileService;
    
    @GetMapping(value = "/profile")
    public Response profile(){
        /** 
        * @Description: 获取当前登录用户的用户详情 
        * @Param: [] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/
        Response response = new Response();
        UserDetails userDetails = userDetailsService.getCurrentUserDetails();
        if(!ObjectUtils.isEmpty(userDetails)){
            String avatarBase64 = defaultFileService.findAvatarBase64ById(userDetails.getAvatar());
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            map.put("image", avatarBase64);
            return response.success(map);
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            map.put("image", DefaultAvatarEnum.UNKNOW.getAvatar());
            return response.success(map);
        }
    }

    @PostMapping(value = "/save")
    public Response saveUserDetails(UserDetailsDTO userDetailsDTO, AddressDTO addressDTO) throws ParseException {
        /** 
        * @Description: 保存用户详细信息 
        * @Param: [userDetailsDTO, addressDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/14 
        **/ 
        UserDetails userDetails = userDetailsService.save(userDetailsDTO, addressDTO);
        Response response = new Response();
        if(!ObjectUtils.isEmpty(userDetails)){
            Map<String, Object> map = new HashMap<>();
            map.put("data", userDetails);
            return response.success(map);
        }else {
            return response.failure();
        }
    }

    @PostMapping(value = "/uploadAvatar")
    public Response uploadAvatar(@RequestParam("file") MultipartFile file){
        /**
        * @Description: 上传头像图片到文件服务器（AJAX提交）
        * @Param: [file]
        * @return: com.example.bookmanagementsystem.utils.Response
        * @Author: Simon Zhuang
        * @Date: 2018/8/14
        **/
        Response response = new Response();
        Map<String, Object> map = defaultFileService.uploadAvatar(file);
        Boolean tag = (Boolean) map.get("success");
        if(tag){
            return response.success(map);
        }else {
            return response.failure();
        }
    }

    @GetMapping(value = "/showBorrowedBookInformation")
    public Response showBorrowedBookInformation(){
        return null;
    }

    @GetMapping(value = "/showBorrowedBookHistory")
    public Response showBorrowedBookHistory(){
        return null;
    }
}
