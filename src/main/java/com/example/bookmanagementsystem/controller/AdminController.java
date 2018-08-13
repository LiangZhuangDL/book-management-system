package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.service.AuthorityService;
import com.example.bookmanagementsystem.service.BasicUserService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 管理员控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping(value = "/showAllAuthorities/{page}/{size}")
    public Page<Authority> showAllAuthorities(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        /** 
        * @Description: 分页显示全部角色的列表 
        * @Param: [page, size] 
        * @return: org.springframework.data.domain.Page<com.example.bookmanagementsystem.entity.authentication.Authority> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return authorityService.getAllAuthorities(page, size);
    }

    @GetMapping(value = "/showAllUsers/{page}/{size}")
    public Page<BasicUser> showAllUsers(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        /** 
        * @Description: 分页显示全部用户的列表 
        * @Param: [page, size] 
        * @return: org.springframework.data.domain.Page<com.example.bookmanagementsystem.entity.authentication.BasicUser> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return basicUserService.getAllUsers(page, size);
    }

    @PostMapping(value = "/createAuthority")
    public Response createAuthority(@RequestParam("authorityName")String authorityName){
        /** 
        * @Description:  管理员创建新角色
        * @Param: [authorityName] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
       Boolean result = authorityService.createAuthority(authorityName);
       Response response = new Response();
       if(result){
           Map<String, Object> map = new HashMap<>();
           map.put("data", "创建角色成功");
           return response.success(map);
       }else {
           return response.failure();
       }
    }

    @PostMapping(value = "/removeAuthority")
    public Response removeAuthority(@RequestParam("authorityName")String authorityName){
        /** 
        * @Description: 管理员删除角色 
        * @Param: [authorityName] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean result = authorityService.removeAuthority(authorityName);
        Response response = new Response();
        if(result){
            Map<String, Object> map = new HashMap<>();
            map.put("data", "删除角色成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/addAuthorityToUser")
    public Response addAuthorityToUser(AuthorityDTO authorityDTO){
        /** 
        * @Description: 管理员为用户添加角色权限 
        * @Param: [authorityDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean tag = authorityService.addAuthority(authorityDTO);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "添加角色成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/removeAuthorityFromUser")
    public Response removeAuthorityFromUser(AuthorityDTO authorityDTO){
        /** 
        * @Description: 管理员为用户删除角色权限 
        * @Param: [authorityDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean tag = authorityService.removeAuthority(authorityDTO);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "删除角色成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/activeUser")
    public Response activeUser(String username){
        /** 
        * @Description: 管理员激活用户 
        * @Param: [username] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean result = basicUserService.activeUser(username);
        Response response = new Response();
        if(result){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "激活用户成功");
            return response.success(map);
        }else {
            return response.failure();
        }
    }

    @PostMapping(value = "/removeUser")
    public Response removeUser(Long userId){
        /** 
        * @Description: 管理员删除用户 
        * @Param: [userId] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Boolean tag = basicUserService.removeUserById(userId);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "删除用户成功");
            return response.success(map);
        }else {
            return response.failure();
        }
    }

}
