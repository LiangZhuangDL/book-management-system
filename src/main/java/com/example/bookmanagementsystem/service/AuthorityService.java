package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: book-management-system
 * @description: 角色业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface AuthorityService {

    @Transactional
    Boolean save(Authority authority);

    @Transactional
    Boolean addAuthority(AuthorityDTO authorityDTO);

    @Transactional
    Boolean removeAuthority(AuthorityDTO authorityDTO);

    @Transactional
    Boolean createAuthority(String authorityName);

    @Transactional
    Boolean removeAuthority(String authorityName);

    @Transactional
    Page<Authority> getAllAuthorities(Integer page, Integer size);
}
