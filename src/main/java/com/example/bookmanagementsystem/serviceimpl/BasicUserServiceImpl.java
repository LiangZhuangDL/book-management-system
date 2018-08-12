package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import com.example.bookmanagementsystem.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Boolean activeUser(String username) {
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
        Pageable pageable = PageRequest.of(page, size);
        return basicUserRepository.findAll(pageable);
    }
}
