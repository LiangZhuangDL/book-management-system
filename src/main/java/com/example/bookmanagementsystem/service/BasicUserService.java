package com.example.bookmanagementsystem.service;

public interface BasicUserService {

    Boolean removeUserById(Long id);

    Boolean activeUser(String username);
}
