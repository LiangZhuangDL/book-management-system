package com.example.bookmanagementsystem.serviceimpl;

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
}
