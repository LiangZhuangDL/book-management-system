package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: book-management-system
 * @description: 注册业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public String isLogin() {
        /** 
        * @Description: 判断是否登录 
        * @Param: [] 
        * @return: java.lang.String 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Boolean save(BasicUser basicUser) {
        /** 
        * @Description: 保存用户基本信息 
        * @Param: [basicUser] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser user = basicUserRepository.findBasicUserByUsername(basicUser.getUsername());
        if(ObjectUtils.isEmpty(user)){
            List<Authority> authorities = new ArrayList<>();
            Authority authority = authorityRepository.findAuthorityByName("USER");
            if(ObjectUtils.isEmpty(authority)){
                Authority result = authorityRepository.save(new Authority("USER"));
                authorities.add(result);
            }else{
                authorities.add(authority);
            }
            basicUser.setAuthorities(authorities);
            basicUser.setUpdateUser(basicUser.getUsername());
            BasicUser result = basicUserRepository.save(basicUser);
            if(ObjectUtils.isEmpty(result)){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }

    }

    @Override
    public Boolean update(BasicUser basicUser) {
        /** 
        * @Description: 更新用户基本信息 
        * @Param: [basicUser] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
       BasicUser result = basicUserRepository.save(basicUser);
        if(ObjectUtils.isEmpty(result)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean activeUser(String activeCode) {
        /** 
        * @Description: 激活账号 
        * @Param: [activeCode] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser user = basicUserRepository.findBasicUserByActiveCode(activeCode);
        if(!ObjectUtils.isEmpty(user) && !user.isEnabled()){
            user.setEnabled(true);
            Boolean tag = update(user);
            return tag;
        }else{
            return false;
        }
    }
}
