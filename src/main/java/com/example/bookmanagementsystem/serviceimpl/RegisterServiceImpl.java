package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public String isLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Boolean save(BasicUser basicUser) {
        BasicUser user = basicUserRepository.save(basicUser);
        if(ObjectUtils.isEmpty(user)){
            return false;
        }else {
            return true;
        }
    }
}
