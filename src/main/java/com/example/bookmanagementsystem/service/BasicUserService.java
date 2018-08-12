package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import org.springframework.data.domain.Page;

public interface BasicUserService {

    Boolean removeUserById(Long id);

    Boolean activeUser(String username);

    Page<BasicUser> getAllUsers(Integer page, Integer size);
}
