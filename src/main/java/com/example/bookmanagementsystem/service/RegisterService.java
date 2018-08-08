package com.example.bookmanagementsystem.service;


import com.example.bookmanagementsystem.entity.authentication.BasicUser;

public interface RegisterService {
    String isLogin();

    Boolean save(BasicUser basicUser);

    Boolean update(BasicUser basicUser);

    Boolean activeUser(String activeCode);
}
