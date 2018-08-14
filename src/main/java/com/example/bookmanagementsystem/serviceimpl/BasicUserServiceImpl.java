package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.anthentication.BasicUserRepository;
import com.example.bookmanagementsystem.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @program: book-management-system
 * @description: 用户基础信息业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Boolean removeUserById(Long id) {
        /** 
        * @Description: 根据用户ID删除用户（浅删除） 
        * @Param: [id] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser basicUser = basicUserRepository.findBasicUserById(id);
        if(!ObjectUtils.isEmpty(basicUser)){
            basicUser.setDelete(true);
            BasicUser result = basicUserRepository.save(basicUser);
            return !ObjectUtils.isEmpty(result);
        }else {
            return false;
        }
    }

    @Override
    public Boolean activeUser(String username) {
        /** 
        * @Description: 根据用户名激活用户 
        * @Param: [username] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser basicUser = basicUserRepository.findBasicUserByUsernameAndIsDelete(username, true);
        if(!ObjectUtils.isEmpty(basicUser)){
            basicUser.setDelete(false);
            BasicUser result = basicUserRepository.save(basicUser);
            return !ObjectUtils.isEmpty(result);
        }else {
            return false;
        }
    }

    @Override
    public Page<BasicUser> getAllUsers(Integer page, Integer size) {
        /** 
        * @Description: 分页查询所有用户基本信息 
        * @Param: [page, size] 
        * @return: org.springframework.data.domain.Page<com.example.bookmanagementsystem.entity.authentication.BasicUser> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Pageable pageable = PageRequest.of(page, size);
        return basicUserRepository.findAll(pageable);
    }

    @Override
    public BasicUser findBasicUserByUsername(String username) {
        /** 
        * @Description: 根据用户名查找用户基本信息实体 
        * @Param: [username] 
        * @return: com.example.bookmanagementsystem.entity.authentication.BasicUser 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        return basicUserRepository.findBasicUserByUsername(username);
    }
}
