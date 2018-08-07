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


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public String isLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Boolean save(BasicUser basicUser) {
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
}
