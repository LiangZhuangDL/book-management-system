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
}
