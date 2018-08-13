package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import org.springframework.data.domain.Page;

/**
 * @program: book-management-system
 * @description: 角色业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface AuthorityService {

    Boolean save(Authority authority);

    Boolean addAuthority(AuthorityDTO authorityDTO);

    Boolean removeAuthority(AuthorityDTO authorityDTO);

    Boolean createAuthority(String authorityName);

    Boolean removeAuthority(String authorityName);

    Page<Authority> getAllAuthorities(Integer page, Integer size);
}
