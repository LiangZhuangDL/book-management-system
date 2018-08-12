package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Boolean save(Authority authority) {
        Authority result = authorityRepository.save(authority);
        if(!ObjectUtils.isEmpty(result)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean addAuthority(AuthorityDTO authorityDTO) {
        BasicUser basicUser = basicUserRepository.findBasicUserById(authorityDTO.getUserId());
        if(!ObjectUtils.isEmpty(basicUser)){
            List<Authority> authorities = basicUser.getRoles();
            boolean tag = true;
            for(Authority authority: authorities){
                if((authorityDTO.getAuthorityName()).equals(authority.getAuthority())){
                    tag = false;
                }
            }
            if(tag){
                Authority authority = new Authority(authorityDTO.getAuthorityName());
                if(!ObjectUtils.isEmpty(authority)){
                    List<Authority> authorityList = basicUser.getRoles();
                    Authority auth = new Authority(authority.getAuthority());
                    authorityList.add(auth);
                    basicUser.setAuthorities(authorityList);
                    BasicUser result = basicUserRepository.save(basicUser);
                    return !ObjectUtils.isEmpty(result);
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean removeAuthority(AuthorityDTO authorityDTO) {
        BasicUser basicUser = basicUserRepository.findBasicUserById(authorityDTO.getUserId());
        if(!ObjectUtils.isEmpty(basicUser)){
            List<Authority> authorities = basicUser.getRoles();
            boolean tag = false;
            for(Authority authority: authorities){
                if((authorityDTO.getAuthorityName()).equals(authority.getAuthority())){
                    tag = true;
                }
            }
            if(tag){
                Authority authority = authorityRepository.findAuthorityByName(authorityDTO.getAuthorityName());
                if(!ObjectUtils.isEmpty(authority)){
                    authorities.remove(authority);
                    basicUser.setAuthorities(authorities);
                    BasicUser result = basicUserRepository.save(basicUser);
                    return !ObjectUtils.isEmpty(result);
                }else{
                    return false;
                }
            }
        }
        return false;
    }
}
