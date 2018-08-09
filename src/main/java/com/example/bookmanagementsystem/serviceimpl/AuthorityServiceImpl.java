package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

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
        return null;
    }

    @Override
    public Boolean removeAuthority(AuthorityDTO authorityDTO) {
        return null;
    }
}
