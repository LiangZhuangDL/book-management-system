package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
            List<Authority> authorities = (List<Authority>) basicUser.getAuthorities();
            boolean tag = true;
            for(Authority authority: authorities){
                if((authorityDTO.getAuthorityName()).equals(authority.getAuthority())){
                    tag = false;
                }
            }
            if(tag){
                Authority authority = authorityRepository.findAuthorityByName(authorityDTO.getAuthorityName());
                if(!ObjectUtils.isEmpty(authority)){
                    authorities.add(authority);
                    basicUser.setAuthorities(authorities);
                    BasicUser result = basicUserRepository.save(basicUser);
                    if(!ObjectUtils.isEmpty(result)){
                        return true;
                    }else {
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return null;
    }

    @Override
    public Boolean removeAuthority(AuthorityDTO authorityDTO) {
        BasicUser basicUser = basicUserRepository.findBasicUserById(authorityDTO.getUserId());
        if(!ObjectUtils.isEmpty(basicUser)){
            List<Authority> authorities = (List<Authority>) basicUser.getAuthorities();
            boolean tag = true;
            for(Authority authority: authorities){
                if((authorityDTO.getAuthorityName()).equals(authority.getAuthority())){
                    tag = false;
                }
            }
            if(tag){
                Authority authority = authorityRepository.findAuthorityByName(authorityDTO.getAuthorityName());
                if(!ObjectUtils.isEmpty(authority)){
                    authorities.remove(authority);
                    basicUser.setAuthorities(authorities);
                    BasicUser result = basicUserRepository.save(basicUser);
                    if(!ObjectUtils.isEmpty(result)){
                        return true;
                    }else {
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return null;
    }
}
