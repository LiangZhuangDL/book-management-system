package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public Boolean removeUserById(Long id) {
        BasicUser basicUser = basicUserRepository.findBasicUserById(id);
        if(!ObjectUtils.isEmpty(basicUser)){
            basicUser.setDelete(true);
            BasicUser result = basicUserRepository.save(basicUser);
            return !ObjectUtils.isEmpty(result);
        }else {
            return false;
        }
    }
}
