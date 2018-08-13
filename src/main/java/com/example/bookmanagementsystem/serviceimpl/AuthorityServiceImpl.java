package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;

/**
 * @program: book-management-system
 * @description: 角色业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Boolean save(Authority authority) {
        /** 
        * @Description: 保存或者更新角色 
        * @Param: [authority] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Authority result = authorityRepository.save(authority);
        return !ObjectUtils.isEmpty(result);
    }

    @Override
    public Boolean addAuthority(AuthorityDTO authorityDTO) {
        /** 
        * @Description: 给用户添加角色权限 
        * @Param: [authorityDTO] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser basicUser = basicUserRepository.findBasicUserById(authorityDTO.getUserId());
        Authority authority = authorityRepository.findAuthorityByName(authorityDTO.getAuthorityName());
        if(!ObjectUtils.isEmpty(authority) && !ObjectUtils.isEmpty(basicUser)){
            boolean tag = true;
            List<Authority> authorities = basicUser.getRoles();
            for(Authority auth : authorities){
                if((auth.getAuthority()).equals(authority.getAuthority())){
                    tag = false;
                }
            }
            if(tag){
                authorities.add(authority);
                basicUser.setAuthorities(authorities);
                BasicUser result = basicUserRepository.save(basicUser);
                return !ObjectUtils.isEmpty(result);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public Boolean removeAuthority(AuthorityDTO authorityDTO) {
        /** 
        * @Description: 给用户删除角色权限 
        * @Param: [authorityDTO] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        BasicUser basicUser = basicUserRepository.findBasicUserById(authorityDTO.getUserId());
        Authority authority = authorityRepository.findAuthorityByName(authorityDTO.getAuthorityName());
        if(!ObjectUtils.isEmpty(authority) && !ObjectUtils.isEmpty(basicUser)) {
            boolean tag = false;
            List<Authority> authorities = basicUser.getRoles();
            for (Authority auth : authorities) {
                if ((auth.getAuthority()).equals(authority.getAuthority())) {
                    tag = true;
                }
            }
            if (tag) {
                authorities.remove(authority);
                basicUser.setAuthorities(authorities);
                BasicUser result = basicUserRepository.save(basicUser);
                return !ObjectUtils.isEmpty(result);
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public Boolean createAuthority(String authorityName) {
        /** 
        * @Description: 创建新角色到角色列表 
        * @Param: [authorityName] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Authority authority = authorityRepository.findAuthorityByName(authorityName);
        if(ObjectUtils.isEmpty(authority)){
            Authority auth = new Authority(authorityName);
            return save(auth);
        }else{
            return false;
        }
    }

    @Override
    public Boolean removeAuthority(String authorityName) {
        /** 
        * @Description: 从角色列表删除角色 
        * @Param: [authorityName] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Authority authority = authorityRepository.findAuthorityByName(authorityName);
        if(!ObjectUtils.isEmpty(authority)){
            authority.setDelete(true);
            return save(authority);
        }else {
            return false;
        }
    }

    @Override
    public Page<Authority> getAllAuthorities(Integer page, Integer size) {
        /** 
        * @Description: 分页获取角色列表 
        * @Param: [page, size] 
        * @return: org.springframework.data.domain.Page<com.example.bookmanagementsystem.entity.authentication.Authority> 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        Pageable pageable = PageRequest.of(page, size);
        return authorityRepository.findAll(pageable);
    }
}
